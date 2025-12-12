package com.vam.cco.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.mapper.TeaEstimateMapper;
import com.vam.cco.model.EstimateDetailModel;
import com.vam.cco.model.EstimationItemModel;
import com.vam.cco.model.ResourceDetailModel;
import com.vam.cco.model.ResourceInfoModel;
import com.vam.cco.model.TeaEstimateModel;
import com.vam.cco.services.CutOffParametersService;
import com.vam.cco.services.EstimationItemService;
import com.vam.cco.services.ResourceInfoService;
import com.vam.cco.services.impl.TeaEstimateServiceImpl;
import com.vam.cco.util.EmployeeTypeEnum;
import com.vam.cco.util.LocationEnum;
import com.vam.cco.util.RoleTypeEnum;
import com.vam.cco.util.ShiftEnum;

@Controller
@RequestMapping("/admin/tea-estimate")
public class TeaEstimateController {

    private static final Logger logger = LoggerFactory.getLogger(TeaEstimateController.class);

    private final TeaEstimateServiceImpl teaEstimateService;

    @Autowired(required = false)
    private EstimationItemService estimationItemService; // optional for test slices
    
    @Autowired(required = false)
    private ResourceInfoService resourceInfoService; // optional for test slices
    
    @Autowired(required = false)
    private CutOffParametersService cutOffParametersService; // optional for test slices

    @Autowired(required = false)
    private com.vam.cco.services.EffortInfoService effortInfoService; // new optional service

    @Autowired
    public TeaEstimateController(TeaEstimateServiceImpl teaEstimateService) {
        this.teaEstimateService = teaEstimateService;
    }

    @GetMapping("/list")
    public String listTeaEstimates(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(required = false, name = "successMessage") String successMessage,
                                  @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        logger.info("Fetching Tea Estimates with pagination. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<TeaEstimateModel> teaPage = teaEstimateService.findAllTeaEstimates(pageable);
        model.addAttribute("teaEstimateModels", teaPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", teaPage.getTotalPages());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        logger.info("Total tea estimates found: {}", teaPage.getTotalElements());
        return "users/teaEstimateList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        logger.info("Showing add Tea Estimate form");
        TeaEstimateModel teaModel = new TeaEstimateModel();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            teaModel.setCreatedBy(authentication.getName());
            teaModel.setModifiedBy(authentication.getName());
            teaModel.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            teaModel.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            
            
            EstimateDetailModel detail = teaModel.getEstimateDetail();
            EstimationItemModel item = new EstimationItemModel();
            item.setOptimisticPersonHours(0);
            item.setPessimisticPersonHours(0);
            item.setMeanPersonHours(0);
            item.setThreePointEstimate(0);
            item.setEffortInPersonHours(0);
            item.setProjectManagementEffort(0);
            item.setBusinessAnalysisEffort(0);
            item.setDevelopmentEffort(0);
            item.setQualityAssuranceEffort(0);
            item.setAssetsAndAcceleratorsEffort(0);
            item.setGenAIEffort(0);
            item.setPersonHours(0);
            item.setPersonDays(0);
            item.setPersonMonths(0);
            
            detail.getEstimationItems().add(item);
            detail.setTotalEffortInPersonHours(0);
            detail.setTotalProjectManagementEffort(0);
        	detail.setTotalBusinessAnalysisEffort(0);
        	detail.setTotalDevelopmentEffort(0);
        	detail.setTotalQualityAssuranceEffort(0);
        	detail.setTotalAssetsAndAcceleratorsEffort(0);
        	detail.setTotalGenAIEffort(0);
        	detail.setTotalPersonHours(0);
        	detail.setTotalPersonDays(0);
        	detail.setTotalPersonMonths(0);
            teaModel.setEstimateDetail(detail);
            
            ResourceDetailModel resourceDetail = teaModel.getResourceDetail();
            
            
            ResourceInfoModel resourceInfo = new ResourceInfoModel();
            
            resourceInfoService.getDataFromMasters(resourceDetail, resourceInfo);
            
            resourceDetail.getResourceInfos().add(resourceInfo);
            teaModel.setResourceDetail(resourceDetail);

            // initialize effort detail with one effort info
            com.vam.cco.model.EffortDetailModel effortDetail = teaModel.getEffortDetail();
            com.vam.cco.model.EffortInfoModel effortInfo = new com.vam.cco.model.EffortInfoModel();
            effortDetail.getEffortInfos().add(effortInfo);
            teaModel.setEffortDetail(effortDetail);
        }
        model.addAttribute("teaEstimate", teaModel);
        return "users/teaEstimateForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing edit form for tea estimate id: {}", id);
        TeaEstimate teaEstimate = teaEstimateService.findById(id).orElseThrow(() -> new RuntimeException("TeaEstimate not found"));
        TeaEstimateModel teaModel = TeaEstimateMapper.toModel(teaEstimate);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (teaModel.getCreatedBy() == null) {
                teaModel.setCreatedBy(authentication.getName());
            }
            if (teaModel.getModifiedBy() == null) {
                teaModel.setModifiedBy(authentication.getName());
            }
        }
        model.addAttribute("teaEstimate", teaModel);
        return "users/teaEstimateForm";
    }

    @PostMapping("/save")
    public String saveTeaEstimate(@Valid @ModelAttribute("teaEstimate") TeaEstimateModel teaModel, BindingResult bindingResult, Model model) {
        logger.info("Saving tea estimate: {}", teaModel.getEngagementName());
        try {
            if (bindingResult.hasErrors()) {
                // Return the form with validation errors
                model.addAttribute("teaEstimate", teaModel);
                return "users/teaEstimateForm";
            }
            Long id = teaModel.getId();
            // Server-side compute: ensure derived values are recalculated and totals validated before save
            try {
                if (teaModel != null && teaModel.getEstimateDetail() != null) {
                    EstimateDetailModel computed = teaEstimateService.computeEstimationDetails(teaModel.getEstimateDetail());
                    teaModel.setEstimateDetail(computed);
                }
            } catch (Exception e) {
                logger.warn("Computation before non-AJAX save failed: {}", e.getMessage());
            }
            teaEstimateService.saveFromModel(teaModel);
            if (id == null) {
                model.addAttribute("successMessage", "Tea Estimate saved successfully");
            } else {
                model.addAttribute("successMessage", "Tea Estimate updated successfully");
            }
        } catch (Exception e) {
            logger.error("Error saving tea estimate: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error creating Tea Estimate:" + e.getMessage());
            return "users/teaEstimateForm";
        }
        return "redirect:/admin/tea-estimate/list";
    }

    @PostMapping(value = "/compute", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> computeEstimateDetail(@RequestBody EstimateDetailModel detail) {
        try {
            EstimateDetailModel computed = teaEstimateService.computeEstimationDetails(detail);
            return ResponseEntity.ok(computed);
        } catch (Exception e) {
            logger.error("Error computing estimate detail: {}", e.getMessage());
            Map<String,Object> resp = new HashMap<>();
            resp.put("success", false);
            resp.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @PostMapping(value = "/add-item")
    public String addEstimationItem(@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, BindingResult bindingResult, Model model) {
    	EstimateDetailModel detail = teaModel.getEstimateDetail();
    	System.out.println("Step 1: In addEstimationItemHtml: detail = " + detail.toString());
        if (estimationItemService == null) {
            model.addAttribute("errorMessage", "EstimationItemService not available");
            return "users/teaEstimateForm";
        }
        System.out.println("Step 2: In addEstimationItemHtml: detail = " + detail.toString());
        detail = estimationItemService.addItemToDetail(detail);
        System.out.println("Step 3: In addEstimationItemHtml: detail = " + detail.toString());
        detail = estimationItemService.calculate(detail);
        System.out.println("Step 4: In addEstimationItemHtml: detail = " + detail.toString());
        teaModel.setEstimateDetail(detail);
        model.addAttribute("teaEstimate", teaModel);
        return "users/teaEstimateForm";
    }

    @PostMapping(value = "/remove-item")
    public String removeEstimationItem (@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, @RequestParam(name = "index", required = false) int index,  BindingResult bindingResult, Model model) {
        try {
        	EstimateDetailModel detail = teaModel.getEstimateDetail();
            detail = estimationItemService.removeItemFromDetail(detail, index);
            detail = estimationItemService.calculate(detail);
            teaModel.setEstimateDetail(detail);
            return "users/teaEstimateForm";
        } catch (Exception e) {
            logger.error("Error removing estimation item", e);
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            Map<String,Object> resp = new HashMap<>(); resp.put("success", false); resp.put("message", e.getMessage()); resp.put("stack", sw.toString());
            return "users/teaEstimateForm";
        }
    }
    
    @PostMapping(value = "/recalculate-item")
    public String recalculateItems (@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, BindingResult bindingResult, Model model) {
        try {
        	EstimateDetailModel detail = teaModel.getEstimateDetail();
            detail = estimationItemService.calculate(detail);
            teaModel.setEstimateDetail(detail);
            return "users/teaEstimateForm";
        } catch (Exception e) {
            logger.error("Error removing estimation item", e);
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            Map<String,Object> resp = new HashMap<>(); resp.put("success", false); resp.put("message", e.getMessage()); resp.put("stack", sw.toString());
            return "users/teaEstimateForm";
        }
    }
    
    @GetMapping("/cutoff-parameters")
    @ResponseBody
    public CutOffParameters getCutOffParameters(@RequestParam("classification") String classification) {
    	CutOffParameters params = null;
    	try {
    		params = cutOffParametersService.findByName(classification);
    	}catch(Exception e) {
    		logger.error("Error fetching cutoff parameters for classification {}: {}", classification, e.getMessage());
		}
		return params;
    }
    
    @PostMapping(value = "/add-resource")
    public String addResourceInfo(@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, BindingResult bindingResult, Model model) {
    	ResourceDetailModel detail = teaModel.getResourceDetail();
    	System.out.println("Step 1: In addEstimationItemHtml: detail = " + detail.toString());
        if (resourceInfoService == null) {
            model.addAttribute("errorMessage", "EstimationItemService not available");
            return "users/teaEstimateForm";
        }
        System.out.println("Step 2: In addEstimationItemHtml: detail = " + detail.toString());
        detail = resourceInfoService.addResourceToDetail(detail);
        System.out.println("Step 3: In addEstimationItemHtml: detail = " + detail.toString());
        detail = resourceInfoService.calculate(detail);
        System.out.println("Step 4: In addEstimationItemHtml: detail = " + detail.toString());
        teaModel.setResourceDetail(detail);
        model.addAttribute("teaEstimate", teaModel);
        return "users/teaEstimateForm";
    }
    
    @PostMapping(value = "/remove-resource")
    public String removeResourceInfo (@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, @RequestParam(name = "index", required = false) int index,  BindingResult bindingResult, Model model) {
        try {
        	ResourceDetailModel detail = teaModel.getResourceDetail();
        	if (resourceInfoService == null) {
                model.addAttribute("errorMessage", "ResourceInfoService not available");
                return "users/teaEstimateForm";
            }
            detail = resourceInfoService.removeResourceFromDetail(detail, index);
            detail = resourceInfoService.calculate(detail);
            teaModel.setResourceDetail(detail);
            return "users/teaEstimateForm";
        } catch (Exception e) {
            logger.error("Error removing estimation item", e);
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            Map<String,Object> resp = new HashMap<>(); resp.put("success", false); resp.put("message", e.getMessage()); resp.put("stack", sw.toString());
            return "users/teaEstimateForm";
        }
    }

    @PostMapping(value = "/add-effort")
    public String addEffortInfo(@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, BindingResult bindingResult, Model model) {
        com.vam.cco.model.EffortDetailModel detail = teaModel.getEffortDetail();
        if (effortInfoService == null) {
            model.addAttribute("errorMessage", "EffortInfoService not available");
            return "users/teaEstimateForm";
        }
        detail = effortInfoService.addEffortToDetail(detail);
        detail = effortInfoService.calculate(detail);
        teaModel.setEffortDetail(detail);
        model.addAttribute("teaEstimate", teaModel);
        return "users/teaEstimateForm";
    }

    @PostMapping(value = "/remove-effort")
    public String removeEffortInfo (@ModelAttribute("teaEstimate") TeaEstimateModel teaModel, @RequestParam(name = "index", required = false) int index,  BindingResult bindingResult, Model model) {
        try {
            com.vam.cco.model.EffortDetailModel detail = teaModel.getEffortDetail();
            if (effortInfoService == null) {
                model.addAttribute("errorMessage", "EffortInfoService not available");
                return "users/teaEstimateForm";
            }
            detail = effortInfoService.removeEffortFromDetail(detail, index);
            detail = effortInfoService.calculate(detail);
            teaModel.setEffortDetail(detail);
            return "users/teaEstimateForm";
        } catch (Exception e) {
            logger.error("Error removing effort item", e);
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            Map<String,Object> resp = new HashMap<>(); resp.put("success", false); resp.put("message", e.getMessage()); resp.put("stack", sw.toString());
            return "users/teaEstimateForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTeaEstimate(@PathVariable Long id) {
        logger.info("Deleting tea estimate with id: {}", id);
        teaEstimateService.deleteById(id);
        logger.info("Tea Estimate deleted successfully");
        return "redirect:/admin/tea-estimate/list";
    }
}
