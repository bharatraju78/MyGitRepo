package com.vam.cco.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vam.cco.dao.entity.EmpUpdateRequest;
import com.vam.cco.dao.entity.Employee;
import com.vam.cco.dao.entity.EmployeeCompensation;
import com.vam.cco.dao.entity.EmployeeDesignation;
import com.vam.cco.dao.entity.EmployeeGrade;
import com.vam.cco.dao.entity.EmployeeOffBoading;
import com.vam.cco.dao.repository.EmpUpdateRequestRepository;
import com.vam.cco.dao.repository.EmployeeCompensationRepository;
import com.vam.cco.dao.repository.EmployeeDesignationRepository;
import com.vam.cco.dao.repository.EmployeeGradeRepository;
import com.vam.cco.dao.repository.EmployeeOffBoadingRepository;
import com.vam.cco.dao.repository.EmployeeRepository;
import com.vam.cco.model.EmployeeModel;
import com.vam.cco.services.EmployeeService;
import com.vam.cco.util.StatusEnum;
import com.vam.cco.util.WorkItemStatus;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
    
    private final EmployeeRepository employeeRepository;
    
    private EmployeeDesignationRepository employeeDesignationRepository;
    private EmployeeGradeRepository employeeGradeRepository;
    private EmployeeCompensationRepository employeeCompensationRepository;
    private EmployeeOffBoadingRepository employeeOffBoadingRepository;
    private EmpUpdateRequestRepository empUpdateRequestRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, 
							   EmployeeDesignationRepository employeeDesignationRepository,
							   EmployeeGradeRepository employeeGradeRepository,
							   EmployeeCompensationRepository employeeCompensationRepository,
							   EmployeeOffBoadingRepository employeeOffBoadingRepository,
							   EmpUpdateRequestRepository empUpdateRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDesignationRepository = employeeDesignationRepository;
        this.employeeGradeRepository = employeeGradeRepository;
        this.employeeCompensationRepository = employeeCompensationRepository;
        this.employeeOffBoadingRepository = employeeOffBoadingRepository;
        this.empUpdateRequestRepository = empUpdateRequestRepository;
    }

    
    public static boolean isBCryptEncoded(String potentialHash) {
        if (potentialHash == null) {
            return false;
        }
        return BCRYPT_PATTERN.matcher(potentialHash).matches();
    }

    @Override
    public EmployeeModel save(EmployeeModel employeeModel) {
        logger.info("Saving Employee: {}", employeeModel);
        Employee entity = toEntity(employeeModel);
        Employee saved = employeeRepository.save(entity);
        logger.info("Employee saved with ID: {}", saved.getEmployeeId());
        Long requestId = employeeModel.getRequestId();
        String requestType = employeeModel.getRequestType();
        logger.info("EmployeeServiceImpl::save::requestId= {}, requestType = {} ", requestId, requestType);
        if (null != requestId) {
			saveEmpUpdateRequest(requestId);
		}
        return toModel(saved);
    }

    @Override
    public Optional<EmployeeModel> findById(Long id) {
        logger.info("Finding Employee by id: {}", id);
        return employeeRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<EmployeeModel> findAll() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public EmployeeModel update(Long id, EmployeeModel employeeModel) {
        logger.info("Updating Employee with id: {}", id);
        Employee entity = toEntity(employeeModel);
        entity.setEmployeeId(id);
        Employee updated = employeeRepository.save(entity);
        return toModel(updated);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        logger.info("Deleting Employee with id: {}", id);
        try {
			employeeRepository.deleteById(id);
			employeeDesignationRepository.deleteById(id);
			employeeGradeRepository.deleteById(id);
			employeeCompensationRepository.deleteById(id);
			employeeOffBoadingRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Error deleting Employee with id: {}", id, e);
			throw new Exception("Error deleting Employee with id: " + id, e);
		}
        logger.info("Deleted Employee with id: {}", id);
    }

    @Override
    public EmployeeDesignation saveEmployeeDesignation(EmployeeDesignation employeeDesignation) {
        logger.info("Saving EmployeeDesignation: {}", employeeDesignation);
        Long requestId = employeeDesignation.getRequestId();
		String requestType = employeeDesignation.getRequestType();
		logger.info("EmployeeServiceImpl::saveEmployeeGrade::requestId= {}, requestType = {} ",
				requestId, requestType);
        List<EmployeeDesignation> existingDesignations =  employeeDesignationRepository.findByEmpId(employeeDesignation.getEmployeeId(), null);
        if (existingDesignations != null && !existingDesignations.isEmpty()) {
			logger.info("Existing designations found for employeeId: {}, size = {}", employeeDesignation.getEmployeeId(), existingDesignations.size());
			existingDesignations.stream().forEach(designation -> {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				designation.setModifiedBy(authentication.getName());
				designation.setModifiedDate(Calendar.getInstance().getTime());
				designation.setStatus(StatusEnum.INACTIVE.getStatus());
				Calendar cal = Calendar.getInstance();
				cal.setTime(employeeDesignation.getStartDate());
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
				designation.setEndDate(cal.getTime());
			});
			employeeDesignationRepository.saveAll(existingDesignations);
		}
        if(null != employeeDesignation) {
        	employeeDesignation.setStatus(StatusEnum.ACTIVE.getStatus());
        }
        employeeDesignationRepository.save(employeeDesignation);
        if(null != requestId) {
			saveEmpUpdateRequest(requestId);
		}
        logger.info("EmployeeServiceImpl::saveEmployeeDesignation::employeeDesignation: {}", employeeDesignation);
        return employeeDesignation;
    }

    @Override
    public EmployeeGrade saveEmployeeGrade(EmployeeGrade employeeGrade) {
        logger.info("Saving EmployeeGrade: {}", employeeGrade);
        Long requestId = employeeGrade.getRequestId();
		String requestType = employeeGrade.getRequestType();
		logger.info("EmployeeServiceImpl::saveEmployeeGrade::requestId= {}, requestType = {} ",
				requestId, requestType);
        List<EmployeeGrade> existingGrades = employeeGradeRepository.findByEmployeeId(employeeGrade.getEmployeeId(), null);
        if (existingGrades != null && !existingGrades.isEmpty()) {
			logger.info("Existing grades found for employeeId: {}, size = {}", employeeGrade.getEmployeeId(), existingGrades.size());
			existingGrades.stream().forEach(grade -> {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				grade.setModifiedBy(authentication.getName());
				grade.setModifiedDate(Calendar.getInstance().getTime());
				grade.setStatus(StatusEnum.INACTIVE.getStatus());
				Calendar cal = Calendar.getInstance();
				cal.setTime(employeeGrade.getStartDate());
				cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
				grade.setEndDate(cal.getTime());
			});
			employeeGradeRepository.saveAll(existingGrades);
		}
        employeeGrade.setStatus(StatusEnum.ACTIVE.getStatus());
        employeeGradeRepository.save(employeeGrade);
        if(null != requestId) {
			saveEmpUpdateRequest(requestId);
		}
        logger.info("EmployeeServiceImpl::saveEmployeeGrade::employeeGrade: {}", employeeGrade);
        return employeeGrade;
    }

    @Override
    public EmployeeCompensation saveEmployeeCompensation(EmployeeCompensation employeeCompensation) {
        logger.info("EmployeeServiceImpl::saveEmployeeCompensation:start:: {}", employeeCompensation);
        Long requestId = employeeCompensation.getRequestId();
		String requestType = employeeCompensation.getRequestType();
		logger.info("EmployeeServiceImpl::saveEmployeeCompensation::requestId= {}, requestType = {} ",
				requestId, requestType);
		List<EmployeeCompensation> existingCompenstion = employeeCompensationRepository.findByEmployeeId(employeeCompensation.getEmployeeId(), null);
		if(null != existingCompenstion && !existingCompenstion.isEmpty()) {
			logger.info("EmployeeServiceImpl::saveEmployeeCompensation::Existing compensation found for employeeId: {}, size = {}",
					employeeCompensation.getEmployeeId(), existingCompenstion.size());
			existingCompenstion.stream().forEach(compensation -> {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				compensation.setModifiedBy(authentication.getName());
				compensation.setModifiedDate(Calendar.getInstance().getTime());
				compensation.setStatus(StatusEnum.INACTIVE.getStatus());
				if(null != employeeCompensation.getEmployeeCompensations() && !employeeCompensation.getEmployeeCompensations().isEmpty()) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(employeeCompensation.getEmployeeCompensations().get(0).getStartDate());
					cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
					compensation.setEndDate(cal.getTime());
				} else {
					compensation.setEndDate(new Date());
				}
			});
			employeeCompensationRepository.saveAll(existingCompenstion);
		}
		
		List<EmployeeCompensation> compensations = employeeCompensation.getEmployeeCompensations();
		if (null != compensations && !compensations.isEmpty()) {
			logger.info("EmployeeServiceImpl::saveEmployeeCompensation::compensations size = {}",
					compensations.size());
			int count = 0;
            for (EmployeeCompensation compensation : compensations) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                compensation.setEmployeeId(employeeCompensation.getEmployeeId());
                compensation.setCreatedBy(authentication.getName());
                compensation.setCreatedDate(Calendar.getInstance().getTime());
                compensation.setModifiedBy(authentication.getName());
                compensation.setModifiedDate(Calendar.getInstance().getTime());
                compensation.setStatus(StatusEnum.ACTIVE.getStatus());
                
                if(count < compensations.size() - 1) {
                	Calendar cal = Calendar.getInstance();
                	cal.setTime(compensations.get(count+1).getStartDate());
                	cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -1);
                	compensation.setEndDate(cal.getTime());
                }
                if(compensations.size() == 1) {
                	Calendar cal = Calendar.getInstance();
                	cal.setTime(compensations.get(0).getStartDate());
                	cal.set(Calendar.MONTH, Calendar.DECEMBER);
                	cal.set(Calendar.DAY_OF_MONTH, 31);
                	compensation.setEndDate(cal.getTime());
                }
                if(count == compensations.size() - 1) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(compensation.getStartDate());
					if(cal.get(Calendar.MONTH) > Calendar.MARCH) {
						cal.set(Calendar.MONTH, Calendar.DECEMBER);
					}
					//cal.set(Calendar.DAY_OF_MONTH, 31);
					if(compensation.getStartDate().before(cal.getTime())) {
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
						cal.set(Calendar.MONTH, Calendar.MARCH);
						compensation.setEndDate(cal.getTime());
					} else if(compensation.getStartDate().after(cal.getTime())) {
						compensation.setEndDate(cal.getTime());
					}
				}
                count++;
            }
            employeeCompensationRepository.saveAll(compensations);
        } 
		
		if(null != requestId) {
			saveEmpUpdateRequest(requestId);
		}
		logger.info("EmployeeServiceImpl::saveEmployeeCompensation:start");
        return employeeCompensation;
    }
    
    private void saveEmpUpdateRequest(Long requestId) {
    	logger.info("EmployeeServiceImpl::saveEmpUpdateRequest::start requestId: {}", requestId);
    	EmpUpdateRequest empUpdateRequest = empUpdateRequestRepository.findById(requestId).get();
    	if (empUpdateRequest != null) {
			empUpdateRequest.setStatus(WorkItemStatus.COMPLETED.toString());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empUpdateRequest.setUpdatedBy(authentication.getName());
			empUpdateRequest.setUpdatedAt(Calendar.getInstance().getTime());
			empUpdateRequest.setEndDate(Calendar.getInstance().getTime());
			empUpdateRequestRepository.save(empUpdateRequest);
		} else {
			logger.warn("EmpUpdateRequest with id {} not found", requestId);
		}
    	
    	logger.info("EmployeeServiceImpl::saveEmpUpdateRequest::start");
    }

    @Override
    public EmployeeOffBoading saveEmployeeOffBoading(EmployeeOffBoading employeeOffBoading) {
        logger.info("Saving EmployeeOffBoading: {}", employeeOffBoading);
        List<EmployeeOffBoading> existingOffBoardings = employeeOffBoadingRepository.findByEmployeeId(employeeOffBoading.getEmployeeId(), null);
        if (existingOffBoardings != null && !existingOffBoardings.isEmpty()) {
			logger.info("Existing off-boardings found for employeeId: {}, size = {}", employeeOffBoading.getEmployeeId(), existingOffBoardings.size());
			existingOffBoardings.stream().forEach(offboarding -> {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				offboarding.setModifiedBy(authentication.getName());
				offboarding.setModifiedDate(Calendar.getInstance().getTime());
				offboarding.setStatus(StatusEnum.INACTIVE.getStatus());
			});
			employeeOffBoadingRepository.saveAll(existingOffBoardings);
		}
        employeeOffBoading.setStatus(StatusEnum.ACTIVE.getStatus());
        employeeOffBoadingRepository.save(employeeOffBoading);
        logger.info("EmployeeServiceImpl::saveEmployeeOffBoading::employeeOffBoading: {}", employeeOffBoading);
        Long requestId = employeeOffBoading.getRequestId();
        if(null != requestId) {
        	saveEmpUpdateRequest(requestId);
        }
        return employeeOffBoading;
    }
    
    @Override
    public List<EmployeeDesignation> findEmployeeDesignationById(Long id) {
        logger.info("Finding EmployeeDesignation by id: {}", id);
        Pageable pageable = PageRequest.of(0, 1);
        return employeeDesignationRepository.findByEmpId(id, pageable);
    }

    @Override
    public List<EmployeeGrade> findEmployeeGradeById(Long id) {
        logger.info("Finding EmployeeGrade by id: {}", id);
        Pageable pageable = PageRequest.of(0, 1);
        return employeeGradeRepository.findByEmployeeId(id, pageable);
    }

    @Override
    public List<EmployeeCompensation> findEmployeeCompensationById(Long id) {
        logger.info("Finding EmployeeCompensation by id: {}", id);
        //Pageable pageable = PageRequest.of(0, 1);
        return employeeCompensationRepository.findByEmployeeId(id, null);
    }

    @Override
    public List<EmployeeOffBoading> findEmployeeOffBoadingById(Long id) {
        logger.info("Finding EmployeeOffBoading by id: {}", id);
        Pageable pageable = PageRequest.of(0, 1);
        return employeeOffBoadingRepository.findByEmployeeId(id, pageable);
    }
    
    @Override
	public List<EmployeeModel> findEmployeesByProjectId(Long projectId) {
		return employeeRepository.findEmployeesByProjectId(projectId);
	}
    
    @Override
	public List<EmployeeModel> findEmployeesByProjectId(Long projectId, List<Long> projectRoleIds) {
		return employeeRepository.findEmployeesByProjectId(projectId, projectRoleIds);
	}
    
    @Override
    public Employee convertToEntity(EmployeeModel employeeModel) {
    	return toEntity(employeeModel);
    }
    
    @Override
    public Employee findByName(String name) {
    	return employeeRepository.findByName(name);
    }
    
    @Override
    public List<EmployeeModel> findByDesignationName(String designationName) {
    	return employeeRepository.findByDesignationName(designationName);
    }
    
    @Override
    public List<EmployeeModel> findByDesignationName(List<String> designationNames) {
    	return employeeRepository.findByDesignationNames(designationNames);
    }
    
    @Override
    public List<EmployeeDesignation> findEmpDesgHis(Long employeeId) {
    	return employeeDesignationRepository.findEmpDesgHis(employeeId);
    }
    
    @Override
    public List<EmployeeGrade> findEmpGradeHis(Long employeeId) {
    	return employeeGradeRepository.findEmpGradeHis(employeeId);
    }
    
    @Override
    public List<EmployeeCompensation> findEmpCompHis(Long id) {
    	return employeeCompensationRepository.findEmpCompHis(id);
    }
    
    @Override
    public List<EmployeeOffBoading> findEmpOffBoardingHis(Long id) {
    	return employeeOffBoadingRepository.findEmpOffBoardingHis(id);
    }
    
    @Override
    public Page<EmployeeModel> findAll(Pageable pageable) {
    	return employeeRepository.findAll(pageable)
				.map(this::toModel);
    }
    
    @Override
    public List<String> findLeadersAndAssociates(List<String> designationNames) {
    	return employeeDesignationRepository.findLeadersAndAssociates(designationNames);
    }
    
    @Override
    public List<EmployeeModel> findByProjectId(Long projectId) {
    	return employeeRepository.findByProjectId(projectId);
    }
    
    private EmployeeModel toModel(Employee entity) {
        if (entity == null) return null;
        EmployeeModel model = new EmployeeModel();
        model.setEmployeeId(entity.getEmployeeId());
        model.setName(entity.getName());
        model.setVamId(entity.getVamId());
        model.setDesignationId(entity.getDesignationId());
        model.setGradeId(entity.getGradeId());
        model.setEmailId(entity.getEmailId());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        model.setDojSearch(dateFormat.format(entity.getDoj()));
        model.setStatus(entity.getStatus());
        model.setSkillDataFromLD(entity.getSkillDataFromLD());
        model.setCurrentSkill(entity.getCurrentSkill());
        model.setStatusWithDays(entity.getStatusWithDays());
        model.setVamExp(entity.getVamExp());
        model.setTotalExp(entity.getTotalExp());
        model.setFinalStatus(entity.getFinalStatus());
        model.setResignationDate(entity.getResignationDate());
        model.setAttritionRisk(entity.getAttritionRisk());
        model.setCtc(entity.getCtc() != null ? entity.getCtc().doubleValue() : null);
        model.setTodo(entity.getTodo());
        model.setYearOneHike(entity.getYearOneHike());
        model.setCreatedBy(entity.getCreatedBy());
        model.setCreatedDate(entity.getCreatedDate());
        model.setModifiedBy(entity.getModifiedBy());
        model.setModifiedDate(entity.getModifiedDate());
        model.setPassword(entity.getPassword()); // Ensure password is handled securely
        // Add more fields as needed
        return model;
    }

    private Employee toEntity(EmployeeModel model) {
        if (model == null) return null;
        Employee entity = null;
        try {
			entity = new Employee();
			entity.setEmployeeId(model.getEmployeeId());
			entity.setName(model.getName());
			entity.setVamId(model.getVamId());
			entity.setDesignationId(model.getDesignationId());
			entity.setGradeId(model.getGradeId());
			entity.setEmailId(model.getEmailId());
			entity.setDoj(model.getDoj()); // Parse string to Date
			entity.setStatus(model.getStatus());
			entity.setSkillDataFromLD(model.getSkillDataFromLD());
			entity.setCurrentSkill(model.getCurrentSkill());
			entity.setStatusWithDays(model.getStatusWithDays());
			entity.setVamExp(model.getVamExp());
			entity.setTotalExp(model.getTotalExp());
			entity.setFinalStatus(model.getFinalStatus());
			entity.setResignationDate(model.getResignationDate());
			entity.setAttritionRisk(model.getAttritionRisk());
			entity.setCtc(model.getCtc() != null ? java.math.BigDecimal.valueOf(model.getCtc()) : null);
			entity.setTodo(model.getTodo());
			entity.setYearOneHike(model.getYearOneHike());
			entity.setCreatedBy(model.getCreatedBy());
			entity.setCreatedDate(model.getCreatedDate());
			entity.setModifiedBy(model.getModifiedBy());
			entity.setModifiedDate(model.getModifiedDate());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(null == entity.getEmployeeId()) {
				logger.info("Employee ID is null, encoding password 0 for new employee");
				entity.setPassword(passwordEncoder.encode(model.getPassword()));
			} else {
				if (isBCryptEncoded(model.getPassword())) {
					logger.info("Employee ID is null, encoding password 1 for new employee");
					entity.setPassword(model.getPassword());
				} else {
					logger.info("Employee ID is null, encoding password 2 for new employee");
					entity.setPassword(passwordEncoder.encode(model.getPassword()));
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
        return entity;
    }

}
