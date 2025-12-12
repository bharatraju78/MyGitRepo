package com.vam.cco.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vam.cco.dao.entity.CutOffParameters;
import com.vam.cco.model.CutOffParametersModel;

public interface CutOffParametersRepository extends JpaRepository<CutOffParameters, Long> {

    @Query(value = "SELECT distinct new com.vam.cco.model.CutOffParametersModel(c.id, c.name, c.cutPercentage, c.projectManagerPercentage, c.businessAnalystPercentage, c.developmentPercentage, c.qualityAssurancePercentage, c.assetsAndAcceleratorPercentage, c.genAIPercentage, c.totalPercentage) FROM CutOffParameters c order by c.name")
    Page<CutOffParametersModel> findAllCutOffParameters(Pageable pageable);

    // Find by name (exact match)
    CutOffParameters findByName(String name);

    // Case-insensitive find by name
    CutOffParameters findByNameIgnoreCase(String name);
}