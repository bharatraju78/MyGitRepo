package com.vam.cco.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OFFSHORE_TOTAL")
public class OffshoreTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579291690197555919L;
	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

	private int yearOneJanTotal;
	private int yearOneFebTotal;
	private int yearOneMarTotal;
	private int yearOneAprTotal;
	private int yearOneMayTotal;
	private int yearOneJunTotal;
	private int yearOneJulTotal;
	private int yearOneAugTotal;
	private int yearOneSepTotal;
	private int yearOneOctTotal;
	private int yearOneNovTotal;
	private int yearOneDecTotal;
	private int yearOneOverallTotal;
	
	private int yearTwoJanTotal;
	private int yearTwoFebTotal;
	private int yearTwoMarTotal;
	private int yearTwoAprTotal;
	private int yearTwoMayTotal;
	private int yearTwoJunTotal;
	private int yearTwoJulTotal;
	private int yearTwoAugTotal;
	private int yearTwoSepTotal;
	private int yearTwoOctTotal;
	private int yearTwoNovTotal;
	private int yearTwoDecTotal;
	private int yearTwoOverallTotal;
	
	private int yearThreeJanTotal;
	private int yearThreeFebTotal;
	private int yearThreeMarTotal;
	private int yearThreeAprTotal;
	private int yearThreeMayTotal;
	private int yearThreeJunTotal;
	private int yearThreeJulTotal;
	private int yearThreeAugTotal;
	private int yearThreeSepTotal;
	private int yearThreeOctTotal;
	private int yearThreeNovTotal;
	private int yearThreeDecTotal;
	private int yearThreeOverallTotal;
	
	private int yearFourJanTotal;
	private int yearFourFebTotal;
	private int yearFourMarTotal;
	private int yearFourAprTotal;
	private int yearFourMayTotal;
	private int yearFourJunTotal;
	private int yearFourJulTotal;
	private int yearFourAugTotal;
	private int yearFourSepTotal;
	private int yearFourOctTotal;
	private int yearFourNovTotal;
	private int yearFourDecTotal;
	private int yearFourOverallTotal;
	
	private int overallTotal;
	
	@OneToOne(mappedBy = "offshoreTotal")
	private ResourceDetail resourceDetail;
	
	public OffshoreTotal() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYearOneJanTotal() {
		return yearOneJanTotal;
	}

	public void setYearOneJanTotal(int yearOneJanTotal) {
		this.yearOneJanTotal = yearOneJanTotal;
	}

	public int getYearOneFebTotal() {
		return yearOneFebTotal;
	}

	public void setYearOneFebTotal(int yearOneFebTotal) {
		this.yearOneFebTotal = yearOneFebTotal;
	}

	public int getYearOneMarTotal() {
		return yearOneMarTotal;
	}

	public void setYearOneMarTotal(int yearOneMarTotal) {
		this.yearOneMarTotal = yearOneMarTotal;
	}

	public int getYearOneAprTotal() {
		return yearOneAprTotal;
	}

	public void setYearOneAprTotal(int yearOneAprTotal) {
		this.yearOneAprTotal = yearOneAprTotal;
	}

	public int getYearOneMayTotal() {
		return yearOneMayTotal;
	}

	public void setYearOneMayTotal(int yearOneMayTotal) {
		this.yearOneMayTotal = yearOneMayTotal;
	}

	public int getYearOneJunTotal() {
		return yearOneJunTotal;
	}

	public void setYearOneJunTotal(int yearOneJunTotal) {
		this.yearOneJunTotal = yearOneJunTotal;
	}

	public int getYearOneJulTotal() {
		return yearOneJulTotal;
	}

	public void setYearOneJulTotal(int yearOneJulTotal) {
		this.yearOneJulTotal = yearOneJulTotal;
	}

	public int getYearOneAugTotal() {
		return yearOneAugTotal;
	}

	public void setYearOneAugTotal(int yearOneAugTotal) {
		this.yearOneAugTotal = yearOneAugTotal;
	}

	public int getYearOneSepTotal() {
		return yearOneSepTotal;
	}

	public void setYearOneSepTotal(int yearOneSepTotal) {
		this.yearOneSepTotal = yearOneSepTotal;
	}

	public int getYearOneOctTotal() {
		return yearOneOctTotal;
	}

	public void setYearOneOctTotal(int yearOneOctTotal) {
		this.yearOneOctTotal = yearOneOctTotal;
	}

	public int getYearOneNovTotal() {
		return yearOneNovTotal;
	}

	public void setYearOneNovTotal(int yearOneNovTotal) {
		this.yearOneNovTotal = yearOneNovTotal;
	}

	public int getYearOneDecTotal() {
		return yearOneDecTotal;
	}

	public void setYearOneDecTotal(int yearOneDecTotal) {
		this.yearOneDecTotal = yearOneDecTotal;
	}

	public int getYearOneOverallTotal() {
		return yearOneOverallTotal;
	}

	public void setYearOneOverallTotal(int yearOneOverallTotal) {
		this.yearOneOverallTotal = yearOneOverallTotal;
	}

	public int getYearTwoJanTotal() {
		return yearTwoJanTotal;
	}

	public void setYearTwoJanTotal(int yearTwoJanTotal) {
		this.yearTwoJanTotal = yearTwoJanTotal;
	}

	public int getYearTwoFebTotal() {
		return yearTwoFebTotal;
	}

	public void setYearTwoFebTotal(int yearTwoFebTotal) {
		this.yearTwoFebTotal = yearTwoFebTotal;
	}

	public int getYearTwoMarTotal() {
		return yearTwoMarTotal;
	}

	public void setYearTwoMarTotal(int yearTwoMarTotal) {
		this.yearTwoMarTotal = yearTwoMarTotal;
	}

	public int getYearTwoAprTotal() {
		return yearTwoAprTotal;
	}

	public void setYearTwoAprTotal(int yearTwoAprTotal) {
		this.yearTwoAprTotal = yearTwoAprTotal;
	}

	public int getYearTwoMayTotal() {
		return yearTwoMayTotal;
	}

	public void setYearTwoMayTotal(int yearTwoMayTotal) {
		this.yearTwoMayTotal = yearTwoMayTotal;
	}

	public int getYearTwoJunTotal() {
		return yearTwoJunTotal;
	}

	public void setYearTwoJunTotal(int yearTwoJunTotal) {
		this.yearTwoJunTotal = yearTwoJunTotal;
	}

	public int getYearTwoJulTotal() {
		return yearTwoJulTotal;
	}

	public void setYearTwoJulTotal(int yearTwoJulTotal) {
		this.yearTwoJulTotal = yearTwoJulTotal;
	}

	public int getYearTwoAugTotal() {
		return yearTwoAugTotal;
	}

	public void setYearTwoAugTotal(int yearTwoAugTotal) {
		this.yearTwoAugTotal = yearTwoAugTotal;
	}

	public int getYearTwoSepTotal() {
		return yearTwoSepTotal;
	}

	public void setYearTwoSepTotal(int yearTwoSepTotal) {
		this.yearTwoSepTotal = yearTwoSepTotal;
	}

	public int getYearTwoOctTotal() {
		return yearTwoOctTotal;
	}

	public void setYearTwoOctTotal(int yearTwoOctTotal) {
		this.yearTwoOctTotal = yearTwoOctTotal;
	}

	public int getYearTwoNovTotal() {
		return yearTwoNovTotal;
	}

	public void setYearTwoNovTotal(int yearTwoNovTotal) {
		this.yearTwoNovTotal = yearTwoNovTotal;
	}

	public int getYearTwoDecTotal() {
		return yearTwoDecTotal;
	}

	public void setYearTwoDecTotal(int yearTwoDecTotal) {
		this.yearTwoDecTotal = yearTwoDecTotal;
	}

	public int getYearTwoOverallTotal() {
		return yearTwoOverallTotal;
	}

	public void setYearTwoOverallTotal(int yearTwoOverallTotal) {
		this.yearTwoOverallTotal = yearTwoOverallTotal;
	}

	public int getYearThreeJanTotal() {
		return yearThreeJanTotal;
	}

	public void setYearThreeJanTotal(int yearThreeJanTotal) {
		this.yearThreeJanTotal = yearThreeJanTotal;
	}

	public int getYearThreeFebTotal() {
		return yearThreeFebTotal;
	}

	public void setYearThreeFebTotal(int yearThreeFebTotal) {
		this.yearThreeFebTotal = yearThreeFebTotal;
	}

	public int getYearThreeMarTotal() {
		return yearThreeMarTotal;
	}

	public void setYearThreeMarTotal(int yearThreeMarTotal) {
		this.yearThreeMarTotal = yearThreeMarTotal;
	}

	public int getYearThreeAprTotal() {
		return yearThreeAprTotal;
	}

	public void setYearThreeAprTotal(int yearThreeAprTotal) {
		this.yearThreeAprTotal = yearThreeAprTotal;
	}

	public int getYearThreeMayTotal() {
		return yearThreeMayTotal;
	}

	public void setYearThreeMayTotal(int yearThreeMayTotal) {
		this.yearThreeMayTotal = yearThreeMayTotal;
	}

	public int getYearThreeJunTotal() {
		return yearThreeJunTotal;
	}

	public void setYearThreeJunTotal(int yearThreeJunTotal) {
		this.yearThreeJunTotal = yearThreeJunTotal;
	}

	public int getYearThreeJulTotal() {
		return yearThreeJulTotal;
	}

	public void setYearThreeJulTotal(int yearThreeJulTotal) {
		this.yearThreeJulTotal = yearThreeJulTotal;
	}

	public int getYearThreeAugTotal() {
		return yearThreeAugTotal;
	}

	public void setYearThreeAugTotal(int yearThreeAugTotal) {
		this.yearThreeAugTotal = yearThreeAugTotal;
	}

	public int getYearThreeSepTotal() {
		return yearThreeSepTotal;
	}

	public void setYearThreeSepTotal(int yearThreeSepTotal) {
		this.yearThreeSepTotal = yearThreeSepTotal;
	}

	public int getYearThreeOctTotal() {
		return yearThreeOctTotal;
	}

	public void setYearThreeOctTotal(int yearThreeOctTotal) {
		this.yearThreeOctTotal = yearThreeOctTotal;
	}

	public int getYearThreeNovTotal() {
		return yearThreeNovTotal;
	}

	public void setYearThreeNovTotal(int yearThreeNovTotal) {
		this.yearThreeNovTotal = yearThreeNovTotal;
	}

	public int getYearThreeDecTotal() {
		return yearThreeDecTotal;
	}

	public void setYearThreeDecTotal(int yearThreeDecTotal) {
		this.yearThreeDecTotal = yearThreeDecTotal;
	}

	public int getYearThreeOverallTotal() {
		return yearThreeOverallTotal;
	}

	public void setYearThreeOverallTotal(int yearThreeOverallTotal) {
		this.yearThreeOverallTotal = yearThreeOverallTotal;
	}

	public int getYearFourJanTotal() {
		return yearFourJanTotal;
	}

	public void setYearFourJanTotal(int yearFourJanTotal) {
		this.yearFourJanTotal = yearFourJanTotal;
	}

	public int getYearFourFebTotal() {
		return yearFourFebTotal;
	}

	public void setYearFourFebTotal(int yearFourFebTotal) {
		this.yearFourFebTotal = yearFourFebTotal;
	}

	public int getYearFourMarTotal() {
		return yearFourMarTotal;
	}

	public void setYearFourMarTotal(int yearFourMarTotal) {
		this.yearFourMarTotal = yearFourMarTotal;
	}

	public int getYearFourAprTotal() {
		return yearFourAprTotal;
	}

	public void setYearFourAprTotal(int yearFourAprTotal) {
		this.yearFourAprTotal = yearFourAprTotal;
	}

	public int getYearFourMayTotal() {
		return yearFourMayTotal;
	}

	public void setYearFourMayTotal(int yearFourMayTotal) {
		this.yearFourMayTotal = yearFourMayTotal;
	}

	public int getYearFourJunTotal() {
		return yearFourJunTotal;
	}

	public void setYearFourJunTotal(int yearFourJunTotal) {
		this.yearFourJunTotal = yearFourJunTotal;
	}

	public int getYearFourJulTotal() {
		return yearFourJulTotal;
	}

	public void setYearFourJulTotal(int yearFourJulTotal) {
		this.yearFourJulTotal = yearFourJulTotal;
	}

	public int getYearFourAugTotal() {
		return yearFourAugTotal;
	}

	public void setYearFourAugTotal(int yearFourAugTotal) {
		this.yearFourAugTotal = yearFourAugTotal;
	}

	public int getYearFourSepTotal() {
		return yearFourSepTotal;
	}

	public void setYearFourSepTotal(int yearFourSepTotal) {
		this.yearFourSepTotal = yearFourSepTotal;
	}

	public int getYearFourOctTotal() {
		return yearFourOctTotal;
	}

	public void setYearFourOctTotal(int yearFourOctTotal) {
		this.yearFourOctTotal = yearFourOctTotal;
	}

	public int getYearFourNovTotal() {
		return yearFourNovTotal;
	}

	public void setYearFourNovTotal(int yearFourNovTotal) {
		this.yearFourNovTotal = yearFourNovTotal;
	}

	public int getYearFourDecTotal() {
		return yearFourDecTotal;
	}

	public void setYearFourDecTotal(int yearFourDecTotal) {
		this.yearFourDecTotal = yearFourDecTotal;
	}

	public int getYearFourOverallTotal() {
		return yearFourOverallTotal;
	}

	public void setYearFourOverallTotal(int yearFourOverallTotal) {
		this.yearFourOverallTotal = yearFourOverallTotal;
	}

	public int getOverallTotal() {
		return overallTotal;
	}

	public void setOverallTotal(int overallTotal) {
		this.overallTotal = overallTotal;
	}

	public ResourceDetail getResourceDetail() {
		return resourceDetail;
	}

	public void setResourceDetail(ResourceDetail resourceDetail) {
		this.resourceDetail = resourceDetail;
	}

}
