package com.pc.mcproj.impl;

import java.util.Random;
import java.util.stream.IntStream;

import com.pc.mcproj.model.PortfolioModel;
import com.pc.mcproj.service.PortfolioSimulationService;
import com.pc.mcproj.constants.MPTContants;

/**
 * This class implements the portfolio simulation service for Monte Carlo porfolio simulation implementation
 * @author Chandra Gangireddy
 *
 */
public class MonteCarloSimulationImpl implements PortfolioSimulationService {

	private Random fRandom;
	private int iRetYears;
	private double[] simulSortedValues;
		
	public MonteCarloSimulationImpl(){
		fRandom = new Random();		
	};

	public double[] simulatePortfolio(final PortfolioModel pModel, final int iYears,final int iNoOfSimulations) throws Exception {
		
		//validations for the input arguments
		if(pModel == null) throw new Exception(MPTContants.PROVIDE_PORTFOLIO);
		
		if(pModel.getInvestment() <= 0) throw new Exception(MPTContants.PROVIDE_INVSTMENT);
		
		if(iYears <= 0 ) throw new Exception(MPTContants.PROVIDE_YEARS);
		
		if(iNoOfSimulations <= 0 ) throw new Exception(MPTContants.PROVIDE_SIMULATIONS);
		
		this.iRetYears = iYears;
        this.simulSortedValues = IntStream.range(0, iNoOfSimulations)
        								  .parallel() //default is 3 to change default -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        								  .mapToDouble(k -> InvstSingleIteration(pModel))
        								  .sorted()
        								  .toArray();
		return simulSortedValues;
	}

	/**
	 * This method takes the percentile value and returns the corresponding percentile value from the simulated sorted array.
	 * @param percentile
	 * @return double
	 */
	public double percentilePorfolioValue(final int percentile) throws Exception {
				
		if(simulSortedValues == null) throw new Exception(MPTContants.RUN_SIMULATIONS);
        if(percentile <= 0 || percentile > 100 ) throw new Exception(MPTContants.VALID_PERCENTILE);
        
        //code to get the correct median value if simulated values arrays is even then the median         
        //is the average of the middle two values
        
        if(percentile==MPTContants.MEDIAN_PERCENTILE){
          double pos1 = Math.floor((simulSortedValues.length - 1.0) / 2.0);
  	      double pos2 = Math.ceil((simulSortedValues.length - 1.0) / 2.0);  	      
  	      if (pos1 == pos2 ) {
  	         return (int) simulSortedValues[(int)pos1];
  	      } else {  	    	 
  	         return (simulSortedValues[(int)pos1] + simulSortedValues[(int)pos2]) / 2.0 ;
  	      }
        	
        }
        else return  simulSortedValues[(int)(simulSortedValues.length/100)*percentile-1]; 
	}
	
	/**
	 * This method takes the portfolio Model and returns the investement value for the given number of years
	 * after adjusting to inflation
	 * @param pModel
	 * @return double
	 */
	private  double InvstSingleIteration(PortfolioModel pModel){
		double dInvestment = pModel.getInvestment(); 
	    for (int idx = 1; idx <= this.iRetYears; ++idx){	    	
	    	dInvestment = dInvestment*(1+getGaussianReturn(pModel.getInvstReturn(), pModel.getInvstRisk()));
	    }	    
	    return dInvestment/(Math.pow((1+pModel.getInflation()),iRetYears));
		
	}
	
	/**
	 * This method takes the Return and Risk values and generates the random return based on Gaussian random value
	 * @param dReturn
	 * @param dRisk
	 * @return double
	 */
	private double getGaussianReturn(double dReturn, double dRisk){
		
		return dReturn + fRandom.nextGaussian() * dRisk;
	}
	
	
	
	

}
