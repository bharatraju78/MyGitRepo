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

import com.vam.cco.dao.entity.RateMaster;
import com.vam.cco.model.RateMasterModel;
import com.vam.cco.services.AccountService;
import com.vam.cco.services.RateMasterService;
import com.vam.cco.util.ProjectRoleEnum;
import com.vam.cco.util.StatusEnum;
import com.vam.cco.util.TechnologySolutionCenterEnum;

@Controller
@RequestMapping("/admin/rates")
public class RateMasterController {

    private static final Logger logger = LoggerFactory.getLogger(RateMasterController.class);
    private final RateMasterService rateMasterService;
    
    private final AccountService accountService;

    @Autowired
    public RateMasterController(RateMasterService rateMasterService, AccountService accountService) {
        this.rateMasterService = rateMasterService;
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public String list(Model model,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false, name = "successMessage") String successMessage,
                        @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RateMasterModel> pageData = rateMasterService.findAllRates(pageable);
        model.addAttribute("rateModels", pageData.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", pageData.getTotalPages());
        if (successMessage != null) model.addAttribute("successMessage", successMessage);
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        return "users/rateList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        RateMasterModel rm = new RateMasterModel();
        rm.setRateStatus(StatusEnum.ACTIVE.getStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            getBasicDetails(rm, authentication);
        }
        model.addAttribute("rate", rm);
        return "users/rateForm";
    }

	private void getBasicDetails(RateMasterModel rm, Authentication authentication) {
		if(rm.getCreatedBy() == null) {
			rm.setCreatedBy(authentication.getName());
		}
		if(rm.getCreatedDate() == null) {
			rm.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		rm.setModifiedBy(authentication.getName());
		rm.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		rm.getAccountList().add("---Select---");
		rm.getAccountList().addAll(accountService.getAllActiveAccountNames());
		rm.setProjectRoleList(ProjectRoleEnum.getAllProjectRoles());
		rm.setTechCenterList(TechnologySolutionCenterEnum.getAllTechCenters());
	}

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        RateMaster r = rateMasterService.findById(id).orElseThrow(() -> new RuntimeException("Rate not found"));
        RateMasterModel rm = new RateMasterModel();
        rm.setRateId(r.getRateId());
        rm.setTechnologyServiceCenter(r.getTechnologyServiceCenter());
        rm.setAccountName(r.getAccountName());
        rm.setSkill(r.getSkill());
        rm.setRole(r.getRole());
        rm.setRateAmount(r.getRateAmount());
        rm.setEffectiveFrom(r.getEffectiveFrom());
        rm.setEffectiveTo(r.getEffectiveTo());
        rm.setRateStatus(r.getRateStatus());
        rm.setCreatedBy(r.getCreatedBy());
        rm.setModifiedBy(r.getModifiedBy());
        rm.setCreatedDate(r.getCreatedDate());
        rm.setModifiedDate(r.getModifiedDate());
        getBasicDetails(rm, SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("rate", rm);
        return "users/rateForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RateMasterModel rateMasterModel, Model model) {
        try {
            RateMaster r = new RateMaster();
            Long id = rateMasterModel.getRateId();
            r.setRateId(rateMasterModel.getRateId());
            r.setTechnologyServiceCenter(rateMasterModel.getTechnologyServiceCenter());
            r.setAccountName(rateMasterModel.getAccountName());
            r.setSkill(rateMasterModel.getSkill());
            r.setRole(rateMasterModel.getRole());
            r.setRateAmount(rateMasterModel.getRateAmount());
            r.setEffectiveFrom(rateMasterModel.getEffectiveFrom());
            r.setEffectiveTo(rateMasterModel.getEffectiveTo());
            r.setRateStatus(rateMasterModel.getRateStatus());
            r.setCreatedBy(rateMasterModel.getCreatedBy());
            r.setCreatedDate(rateMasterModel.getCreatedDate());
            r.setModifiedBy(rateMasterModel.getModifiedBy());
            r.setModifiedDate(rateMasterModel.getModifiedDate());
            rateMasterService.save(r);
            if (id == null) model.addAttribute("successMessage", "Rate saved successfully"); else model.addAttribute("successMessage", "Rate updated successfully");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving Rate: " + e.getMessage());
            logger.error("Error saving Rate", e);
            logger.error("Rate Data: {}", rateMasterModel);
        }
        return "redirect:/admin/rates/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rateMasterService.deleteById(id);
        return "redirect:/admin/rates/list";
    }
}