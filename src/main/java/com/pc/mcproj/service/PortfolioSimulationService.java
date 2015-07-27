package com.pc.mcproj.service;

import com.pc.mcproj.model.PortfolioModel;

/**
 * This is the interface for the portfolio simulation service
 * @author Chandra Gangireddy
 *
 */
public interface PortfolioSimulationService {

	public double[] simulatePortfolio(PortfolioModel portfolioModel,int iYears,int iNoOfSimulations) throws Exception;
	
	public double percentilePorfolioValue(int percentile) throws Exception; 
	
}
