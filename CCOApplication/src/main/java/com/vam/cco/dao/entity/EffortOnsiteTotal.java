package com.vam.cco.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EFFORT_ONSITE_TOTAL")
public class EffortOnsiteTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double yearOneJanTotal;
    private double yearOneFebTotal;
    private double yearOneMarTotal;
    private double yearOneAprTotal;
    private double yearOneMayTotal;
    private double yearOneJunTotal;
    private double yearOneJulTotal;
    private double yearOneAugTotal;
    private double yearOneSepTotal;
    private double yearOneOctTotal;
    private double yearOneNovTotal;
    private double yearOneDecTotal;
    private double yearOneOverallTotal;

    private double yearTwoJanTotal;
    private double yearTwoFebTotal;
    private double yearTwoMarTotal;
    private double yearTwoAprTotal;
    private double yearTwoMayTotal;
    private double yearTwoJunTotal;
    private double yearTwoJulTotal;
    private double yearTwoAugTotal;
    private double yearTwoSepTotal;
    private double yearTwoOctTotal;
    private double yearTwoNovTotal;
    private double yearTwoDecTotal;
    private double yearTwoOverallTotal;

    private double yearThreeJanTotal;
    private double yearThreeFebTotal;
    private double yearThreeMarTotal;
    private double yearThreeAprTotal;
    private double yearThreeMayTotal;
    private double yearThreeJunTotal;
    private double yearThreeJulTotal;
    private double yearThreeAugTotal;
    private double yearThreeSepTotal;
    private double yearThreeOctTotal;
    private double yearThreeNovTotal;
    private double yearThreeDecTotal;
    private double yearThreeOverallTotal;

    private double yearFourJanTotal;
    private double yearFourFebTotal;
    private double yearFourMarTotal;
    private double yearFourAprTotal;
    private double yearFourMayTotal;
    private double yearFourJunTotal;
    private double yearFourJulTotal;
    private double yearFourAugTotal;
    private double yearFourSepTotal;
    private double yearFourOctTotal;
    private double yearFourNovTotal;
    private double yearFourDecTotal;
    private double yearFourOverallTotal;

    private double overallTotal;

    @OneToOne(mappedBy = "onsiteTotal")
    private EffortDetail effortDetail;

    public EffortOnsiteTotal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getYearOneJanTotal() { return yearOneJanTotal; }
    public void setYearOneJanTotal(double yearOneJanTotal) { this.yearOneJanTotal = yearOneJanTotal; }
    public double getYearOneFebTotal() { return yearOneFebTotal; }
    public void setYearOneFebTotal(double yearOneFebTotal) { this.yearOneFebTotal = yearOneFebTotal; }
    public double getYearOneMarTotal() { return yearOneMarTotal; }
    public void setYearOneMarTotal(double yearOneMarTotal) { this.yearOneMarTotal = yearOneMarTotal; }
    public double getYearOneAprTotal() { return yearOneAprTotal; }
    public void setYearOneAprTotal(double yearOneAprTotal) { this.yearOneAprTotal = yearOneAprTotal; }
    public double getYearOneMayTotal() { return yearOneMayTotal; }
    public void setYearOneMayTotal(double yearOneMayTotal) { this.yearOneMayTotal = yearOneMayTotal; }
    public double getYearOneJunTotal() { return yearOneJunTotal; }
    public void setYearOneJunTotal(double yearOneJunTotal) { this.yearOneJunTotal = yearOneJunTotal; }
    public double getYearOneJulTotal() { return yearOneJulTotal; }
    public void setYearOneJulTotal(double yearOneJulTotal) { this.yearOneJulTotal = yearOneJulTotal; }
    public double getYearOneAugTotal() { return yearOneAugTotal; }
    public void setYearOneAugTotal(double yearOneAugTotal) { this.yearOneAugTotal = yearOneAugTotal; }
    public double getYearOneSepTotal() { return yearOneSepTotal; }
    public void setYearOneSepTotal(double yearOneSepTotal) { this.yearOneSepTotal = yearOneSepTotal; }
    public double getYearOneOctTotal() { return yearOneOctTotal; }
    public void setYearOneOctTotal(double yearOneOctTotal) { this.yearOneOctTotal = yearOneOctTotal; }
    public double getYearOneNovTotal() { return yearOneNovTotal; }
    public void setYearOneNovTotal(double yearOneNovTotal) { this.yearOneNovTotal = yearOneNovTotal; }
    public double getYearOneDecTotal() { return yearOneDecTotal; }
    public void setYearOneDecTotal(double yearOneDecTotal) { this.yearOneDecTotal = yearOneDecTotal; }
    public double getYearOneOverallTotal() { return yearOneOverallTotal; }
    public void setYearOneOverallTotal(double yearOneOverallTotal) { this.yearOneOverallTotal = yearOneOverallTotal; }

    // ... similar getters/setters for other years and totals ...

    public double getOverallTotal() { return overallTotal; }
    public void setOverallTotal(double overallTotal) { this.overallTotal = overallTotal; }

    public EffortDetail getEffortDetail() { return effortDetail; }
    public void setEffortDetail(EffortDetail effortDetail) { this.effortDetail = effortDetail; }
}