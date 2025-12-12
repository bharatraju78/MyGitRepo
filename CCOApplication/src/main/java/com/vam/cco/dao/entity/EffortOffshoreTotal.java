package com.vam.cco.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EFFORT_OFFSHORE_TOTAL")
public class EffortOffshoreTotal implements Serializable {

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

    // ... other years omitted for brevity, same fields as EffortOnsiteTotal ...

    private double overallTotal;

    @OneToOne(mappedBy = "offshoreTotal")
    private EffortDetail effortDetail;

    public EffortOffshoreTotal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getYearOneJanTotal() { return yearOneJanTotal; }
    public void setYearOneJanTotal(double yearOneJanTotal) { this.yearOneJanTotal = yearOneJanTotal; }

    // ... other getters/setters should be added mirroring EffortOnsiteTotal ...

    public double getOverallTotal() { return overallTotal; }
    public void setOverallTotal(double overallTotal) { this.overallTotal = overallTotal; }

    public EffortDetail getEffortDetail() { return effortDetail; }
    public void setEffortDetail(EffortDetail effortDetail) { this.effortDetail = effortDetail; }
}