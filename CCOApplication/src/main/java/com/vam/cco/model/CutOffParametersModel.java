package com.vam.cco.model;

public class CutOffParametersModel {
    private Long id;
    private String name;
    private double cutPercentage;
    private double projectManagerPercentage;
    private double businessAnalystPercentage;
    private double developmentPercentage;
    private double qualityAssurancePercentage;
    private double assetsAndAcceleratorPercentage;
    private double genAIPercentage;
    private double totalPercentage;

    public CutOffParametersModel(Long id, String name, double cutPercentage, double projectManagerPercentage,
            double businessAnalystPercentage, double developmentPercentage, double qualityAssurancePercentage,
            double assetsAndAcceleratorPercentage, double genAIPercentage, double totalPercentage) {
        this.id = id;
        this.name = name;
        this.cutPercentage = cutPercentage;
        this.projectManagerPercentage = projectManagerPercentage;
        this.businessAnalystPercentage = businessAnalystPercentage;
        this.developmentPercentage = developmentPercentage;
        this.qualityAssurancePercentage = qualityAssurancePercentage;
        this.assetsAndAcceleratorPercentage = assetsAndAcceleratorPercentage;
        this.genAIPercentage = genAIPercentage;
        this.totalPercentage = totalPercentage;
    }

    // getters and setters

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
}
