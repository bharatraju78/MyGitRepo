package com.vam.cco.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUTOFF_PARAMETERS")
public class CutOffParameters implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "cut_percentage")
    private double cutPercentage;

    @Column(name = "project_manager_percentage")
    private double projectManagerPercentage;

    @Column(name = "business_analyst_percentage")
    private double businessAnalystPercentage;

    @Column(name = "development_percentage")
    private double developmentPercentage;

    @Column(name = "quality_assurance_percentage")
    private double qualityAssurancePercentage;

    @Column(name = "assets_and_accelerator_percentage")
    private double assetsAndAcceleratorPercentage;

    @Column(name = "genai_percentage")
    private double genAIPercentage;

    @Column(name = "total_percentage")
    private double totalPercentage;

    public CutOffParameters() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCutPercentage() {
        return cutPercentage;
    }

    public void setCutPercentage(double cutPercentage) {
        this.cutPercentage = cutPercentage;
    }

    public double getProjectManagerPercentage() {
        return projectManagerPercentage;
    }

    public void setProjectManagerPercentage(double projectManagerPercentage) {
        this.projectManagerPercentage = projectManagerPercentage;
    }

    public double getBusinessAnalystPercentage() {
        return businessAnalystPercentage;
    }

    public void setBusinessAnalystPercentage(double businessAnalystPercentage) {
        this.businessAnalystPercentage = businessAnalystPercentage;
    }

    public double getDevelopmentPercentage() {
        return developmentPercentage;
    }

    public void setDevelopmentPercentage(double developmentPercentage) {
        this.developmentPercentage = developmentPercentage;
    }

    public double getQualityAssurancePercentage() {
        return qualityAssurancePercentage;
    }

    public void setQualityAssurancePercentage(double qualityAssurancePercentage) {
        this.qualityAssurancePercentage = qualityAssurancePercentage;
    }

    public double getAssetsAndAcceleratorPercentage() {
        return assetsAndAcceleratorPercentage;
    }

    public void setAssetsAndAcceleratorPercentage(double assetsAndAcceleratorPercentage) {
        this.assetsAndAcceleratorPercentage = assetsAndAcceleratorPercentage;
    }

    public double getGenAIPercentage() {
        return genAIPercentage;
    }

    public void setGenAIPercentage(double genAIPercentage) {
        this.genAIPercentage = genAIPercentage;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(double totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    @Override
    public String toString() {
        return "CutOffParameters [id=" + id + ", name=" + name + ", cutPercentage=" + cutPercentage + ", projectManagerPercentage=" + projectManagerPercentage
                + ", businessAnalystPercentage=" + businessAnalystPercentage + ", developmentPercentage=" + developmentPercentage
                + ", qualityAssurancePercentage=" + qualityAssurancePercentage + ", assetsAndAcceleratorPercentage=" + assetsAndAcceleratorPercentage
                + ", genAIPercentage=" + genAIPercentage + ", totalPercentage=" + totalPercentage + "]";
    }
}
