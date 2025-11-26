package com.vam.cco.services;

import java.util.List;

import com.vam.cco.dao.entity.EmployeeAllocation;
import com.vam.cco.model.MapAssociatesModel;

public interface MapAssociatesService {

	public MapAssociatesModel saveMapAssociatesModel(MapAssociatesModel mapAssociatesModel);
	
	public MapAssociatesModel fetchMappedAssociates(Long accountId, Long projectId) throws Exception;
	
	public void deleteMappedAssociates(Long empAllId) throws Exception;
	
	public MapAssociatesModel findEmpAllToEdit(MapAssociatesModel mapAssociatesModel) throws Exception;
	
}
