package com.pc.mcproj.model;

/**
 * This is the portfolio model used for the simulation
 * @author Chandra Gangireddy
 *
 */
public class PortfolioModel {
	
	private float invstReturn;
	private float invstRisk;
	private double investment;
	private float inflation;
	
	public PortfolioModel(double investment,float invstReturn,float invstRisk,float inflation) {		
	    this.investment = investment;
	    this.invstReturn = invstReturn/100;
	    this.invstRisk = invstRisk/100;
	    this.inflation = inflation/100;
	}

	public float getInvstReturn() {
		return invstReturn;
	}

	public void setInvstReturn(float invstReturn) {
		this.invstReturn = invstReturn/100;
	}

	public float getInvstRisk() {
		return invstRisk;
	}

	public void setInvstRisk(float invstRisk) {
		this.invstRisk = invstRisk/100;
	}

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public float getInflation() {
		return inflation;
	}

	public void setInflation(float inflation) {
		this.inflation = inflation/100;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(inflation);
		long temp;
		temp = Double.doubleToLongBits(investment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(invstReturn);
		result = prime * result + Float.floatToIntBits(invstRisk);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortfolioModel other = (PortfolioModel) obj;
		if (Float.floatToIntBits(inflation) != Float
				.floatToIntBits(other.inflation))
			return false;
		if (Double.doubleToLongBits(investment) != Double
				.doubleToLongBits(other.investment))
			return false;
		if (Float.floatToIntBits(invstReturn) != Float
				.floatToIntBits(other.invstReturn))
			return false;
		if (Float.floatToIntBits(invstRisk) != Float
				.floatToIntBits(other.invstRisk))
			return false;
		return true;
	}


}
