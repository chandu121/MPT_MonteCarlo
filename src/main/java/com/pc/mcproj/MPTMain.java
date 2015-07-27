package com.pc.mcproj;

import java.text.NumberFormat;

import com.pc.mcproj.impl.MonteCarloSimulationImpl;
import com.pc.mcproj.model.PortfolioModel;
import com.pc.mcproj.service.PortfolioSimulationService;

/**
 * Main class for the Monte Carlo Simulation 
 * @author Chandra Gangireddy
 *
 */
public class MPTMain {
	
	public static void main(String[] args) throws Exception{
		final int iYears = 20;
		final int iNoOfSimulations = 10000;
		final float fInflation = 3.5f; 
		
		NumberFormat currencyformat = NumberFormat.getCurrencyInstance();
		
		PortfolioSimulationService mcSimulation = new MonteCarloSimulationImpl();
		
	    System.out.println("--------------------------------------------------------");
	    System.out.println("Monte Carlo Simulation for Aggressive Portfolio");
	    System.out.println("--------------------------------------------------------");
	    long startTime = System.nanoTime();
		PortfolioModel aggPortfolioModel = new PortfolioModel(100000, 9.4324f, 15.675f,fInflation);		
		mcSimulation.simulatePortfolio(aggPortfolioModel, iYears, iNoOfSimulations);
		
		long endTime = System.nanoTime();
	    double duration = (double) ((endTime - startTime)/Math.pow(10, 9));
	    System.out.format("Execution time in seconds  =  %,.2f\n",duration);
	    
		System.out.println("A - Aggressive --> Median Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(50)));
		System.out.println("A - Aggressive --> 10% Best Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(90)));
		System.out.println("A - Aggressive --> 10% Worst Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(10)));
	    
		
	    
	    
	    System.out.println("--------------------------------------------------------");
	    System.out.println("Monte Carlo Simulation for Very Conservative Portfolio");
	    System.out.println("--------------------------------------------------------");
		
	    startTime = System.nanoTime();
	    PortfolioModel vConPortfolioModel = new PortfolioModel(100000, 6.189f, 6.3438f,fInflation);
	    mcSimulation.simulatePortfolio(vConPortfolioModel, iYears, iNoOfSimulations);
	    
	    endTime = System.nanoTime();
	    duration = (double) ((endTime - startTime)/Math.pow(10, 9));
	    System.out.format("Execution time in seconds  =  %,.2f\n",duration);
	    
	    System.out.println("I - VeryConservative --> Median Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(50)));
		System.out.println("I - VeryConservative --> 10% Best Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(90)));
		System.out.println("I - VeryConservative --> 10% Worst Value after "+ iYears+" years is "+currencyformat.format(mcSimulation.percentilePorfolioValue(10)));
	}

}
