package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vam.cco.dao.entity.Opportunity;
import com.vam.cco.model.OpportunityModel;
import com.vam.cco.services.OpportunityService;
import com.vam.cco.util.IdGenerator;
import com.vam.cco.util.StatusEnum;

@Controller
@RequestMapping("/admin/opportunity")
public class OpportunityController {

    private static final Logger logger = LoggerFactory.getLogger(OpportunityController.class);

    private final OpportunityService opportunityService;
    private final IdGenerator idGenerator;

    @Autowired
    public OpportunityController(OpportunityService opportunityService, IdGenerator idGenerator) {
        this.opportunityService = opportunityService;
        this.idGenerator = idGenerator;
    }

    @GetMapping("/opportunity-list")
    public String listOpportunities(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false, name = "successMessage") String successMessage,
                                   @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("Fetching opportunities with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Opportunity> oppPage = opportunityService.findAll(pageable);
        model.addAttribute("opportunities", oppPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", oppPage.getTotalPages());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total opportunities found: {}", oppPage.getTotalElements());
        return "users/opportunityList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add opportunity form");
        String oppNumber = idGenerator.generateAccountId();
        OpportunityModel opportunityModel = new OpportunityModel();
        opportunityModel.setOppNo(oppNumber);
        opportunityModel.setOppStatusList(StatusEnum.getAllStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logger.info("OpportunityController::generateOppNumber::Authentication found: {}", authentication.getName());
            opportunityModel.setCreatedBy(authentication.getName());
            opportunityModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            opportunityModel.setModifiedBy(authentication.getName());
            opportunityModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        } else {
            logger.info("OpportunityController::generateOppNumber::No authentication found");
        }
        model.addAttribute("opportunity", opportunityModel);
        return "users/opportunityForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for opportunity id: {}", id);
        Opportunity opportunity = opportunityService.findById(id).orElseThrow(() -> new RuntimeException("Opportunity not found"));
        OpportunityModel opportunityModel = new OpportunityModel();
        opportunityModel.setOppNo(opportunity.getOppNo());
        opportunityModel.setOpportunityId(opportunity.getOpportunityId());
        opportunityModel.setOppName(opportunity.getOppName());
        opportunityModel.setOppStatus(opportunity.getOppStatus());
        opportunityModel.setCreatedBy(opportunity.getCreatedBy());
        if (null != opportunity.getCreatedDate()) {
            opportunityModel.setCreatedDate(opportunity.getCreatedDate());
        } else {
            opportunityModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (null == opportunityModel.getCreatedBy()) {
                opportunityModel.setCreatedBy(authentication.getName());
            }
            if (null == opportunityModel.getModifiedBy()) {
                opportunityModel.setModifiedBy(authentication.getName());
            }
        }
        if (null != opportunityModel.getModifiedDate()) {
            opportunityModel.setModifiedDate(opportunity.getModifiedDate());
        } else {
            opportunityModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }
        opportunityModel.setOppStatusList(StatusEnum.getAllStatus());
        logger.info("Showing edit form for opportunity opportunityModel: {}", opportunityModel);
        model.addAttribute("opportunity", opportunityModel);
        return "users/opportunityForm";
    }

    @PostMapping("/opportunity-save")
    public String saveOpportunity(@ModelAttribute OpportunityModel opportunityModel, Model model) {
        logger.info("Saving opportunity: {}", opportunityModel.getOppName());
        try {
            Opportunity opportunity = new Opportunity();
            Long oppId = opportunityModel.getOpportunityId();
            opportunity.setOpportunityId(opportunityModel.getOpportunityId());
            opportunity.setOppNo(opportunityModel.getOppNo());
            opportunity.setOppName(opportunityModel.getOppName());
            opportunity.setOppStatus(opportunityModel.getOppStatus());
            opportunity.setCreatedBy(opportunityModel.getCreatedBy());
            opportunity.setCreatedDate(opportunityModel.getCreatedDate());
            opportunity.setModifiedBy(opportunityModel.getModifiedBy());
            opportunity.setModifiedDate(opportunityModel.getModifiedDate());
            opportunityService.save(opportunity);
            if (null == oppId) {
                model.addAttribute("successMessage", "Opportunity saved successfully");
            } else {
                model.addAttribute("successMessage", "Opportunity updated successfully");
            }

        } catch (Exception e) {
            logger.error("Error saving opportunity: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error creating Opportunity:" + e.getMessage());
        }

        logger.info("Opportunity saved successfully");
        return "redirect:/admin/opportunity/opportunity-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOpportunity(@PathVariable Long id) {
        logger.info("Deleting opportunity with id: {}", id);
        opportunityService.deleteById(id);
        logger.info("Opportunity deleted successfully");
        return "redirect:/admin/opportunity/opportunity-list";
    }
}
