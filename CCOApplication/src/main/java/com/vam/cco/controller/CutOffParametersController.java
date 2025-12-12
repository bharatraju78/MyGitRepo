package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.model.CutOffParametersModel;
import com.vam.cco.services.CutOffParametersService;

@Controller
@RequestMapping("/admin/cutoff")
public class CutOffParametersController {

    private static final Logger logger = LoggerFactory.getLogger(CutOffParametersController.class);

    private final CutOffParametersService service;

    @Autowired
    public CutOffParametersController(CutOffParametersService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false, name = "successMessage") String successMessage,
                       @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CutOffParametersModel> p = service.findAllCutOffParameters(pageable);
        model.addAttribute("items", p.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", p.getTotalPages());
        if (successMessage != null) model.addAttribute("successMessage", successMessage);
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        return "users/cutoffList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        CutOffParametersModel m = new CutOffParametersModel(null, "", 0,0,0,0,0,0,0,0);
        model.addAttribute("cutoff", m);
        return "users/cutoffForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CutOffParameters co = service.findById(id).orElseThrow(() -> new RuntimeException("CutOffParameters not found"));
        CutOffParametersModel m = new CutOffParametersModel(co.getId(), co.getName(), co.getCutPercentage(), co.getProjectManagerPercentage(), co.getBusinessAnalystPercentage(), co.getDevelopmentPercentage(), co.getQualityAssurancePercentage(), co.getAssetsAndAcceleratorPercentage(), co.getGenAIPercentage(), co.getTotalPercentage());
        model.addAttribute("cutoff", m);
        return "users/cutoffForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CutOffParametersModel cutoffModel, Model model) {
        try {
            // Recompute total from submitted role percentages to prevent tampering
            double sum = 0.0;
            sum += cutoffModel.getProjectManagerPercentage();
            sum += cutoffModel.getBusinessAnalystPercentage();
            sum += cutoffModel.getDevelopmentPercentage();
            sum += cutoffModel.getQualityAssurancePercentage();
            sum += cutoffModel.getAssetsAndAcceleratorPercentage();
            sum += cutoffModel.getGenAIPercentage();
            // round to 2 decimals
            sum = Math.round(sum * 100.0) / 100.0;
            cutoffModel.setTotalPercentage(sum);
            // Validate individual role percentages are not greater than 1
            if (cutoffModel.getProjectManagerPercentage() > 1.0) {
                model.addAttribute("errorMessage", "Project Manager percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            if (cutoffModel.getBusinessAnalystPercentage() > 1.0) {
                model.addAttribute("errorMessage", "Business Analyst percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            if (cutoffModel.getDevelopmentPercentage() > 1.0) {
                model.addAttribute("errorMessage", "Development percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            if (cutoffModel.getQualityAssurancePercentage() > 1.0) {
                model.addAttribute("errorMessage", "Quality Assurance percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            if (cutoffModel.getAssetsAndAcceleratorPercentage() > 1.0) {
                model.addAttribute("errorMessage", "Assets & Accelerator percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            if (cutoffModel.getGenAIPercentage() > 1.0) {
                model.addAttribute("errorMessage", "GenAI percentage must be <= 1 (1 = 100%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            // Total must equal 1.0 (i.e., 100%). Allow a tiny epsilon for floating rounding.
            double eps = 0.001;
            if (Math.abs(sum - 1.0) > eps) {
                model.addAttribute("errorMessage", "Total percentage of roles must equal 100% (current=" + (sum * 100) + "%)");
                model.addAttribute("cutoff", cutoffModel);
                return "users/cutoffForm";
            }
            CutOffParameters co = new CutOffParameters();
            co.setId(cutoffModel.getId());
            co.setName(cutoffModel.getName());
            co.setCutPercentage(cutoffModel.getCutPercentage());
            co.setProjectManagerPercentage(cutoffModel.getProjectManagerPercentage());
            co.setBusinessAnalystPercentage(cutoffModel.getBusinessAnalystPercentage());
            co.setDevelopmentPercentage(cutoffModel.getDevelopmentPercentage());
            co.setQualityAssurancePercentage(cutoffModel.getQualityAssurancePercentage());
            co.setAssetsAndAcceleratorPercentage(cutoffModel.getAssetsAndAcceleratorPercentage());
            co.setGenAIPercentage(cutoffModel.getGenAIPercentage());
            co.setTotalPercentage(cutoffModel.getTotalPercentage());
            service.save(co);
            model.addAttribute("successMessage", "Cut off parameters saved successfully");
        } catch (Exception e) {
            logger.error("Error saving cutoff parameters: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error saving: " + e.getMessage());
        }
        return "redirect:/admin/cutoff/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/cutoff/list";
    }

    // JSON endpoint to get CutOffParameters by name (classification)
    @GetMapping("/by-name")
    @ResponseBody
    public ResponseEntity<CutOffParametersModel> getByName(@RequestParam("name") String name) {
        try {
            CutOffParameters co = service.findByName(name);
            if (co == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            CutOffParametersModel model = new CutOffParametersModel(co.getId(), co.getName(), co.getCutPercentage(), co.getProjectManagerPercentage(), co.getBusinessAnalystPercentage(), co.getDevelopmentPercentage(), co.getQualityAssurancePercentage(), co.getAssetsAndAcceleratorPercentage(), co.getGenAIPercentage(), co.getTotalPercentage());
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching CutOffParameters by name {}: {}", name, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Path-style lookup variant: /admin/cutoff/by-name/{name}
    @GetMapping("/by-name/{name}")
    @ResponseBody
    public ResponseEntity<CutOffParametersModel> getByNamePath(@PathVariable("name") String name) {
        return getByName(name);
    }
}