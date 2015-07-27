package com.pc.mcproj.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pc.mcproj.constants.MPTContants;
import com.pc.mcproj.model.PortfolioModel;
import com.pc.mcproj.service.PortfolioSimulationService;


/**
 * This is the test class for MonteCarloSimulationImpl class
 * @author Chandra Gangireddy
 *
 */
public class MonteCarloSimulationImplTest {

	private static PortfolioSimulationService porfolioSimulatorService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		porfolioSimulatorService = new MonteCarloSimulationImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		porfolioSimulatorService = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZeroInvestment() {
		PortfolioModel portfolioModel = new PortfolioModel(0, 9.4324f, 15.675f,3.5f);
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 20, 10000);
		} catch (Exception e) {			
			assertEquals(MPTContants.PROVIDE_INVSTMENT, e.getMessage());
		}
		
	}
	
	@Test
	public void testZeroYears() {
		PortfolioModel portfolioModel = new PortfolioModel(1000, 9.4324f, 15.675f,3.5f);
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 0, 10000);
		} catch (Exception e) {			
			assertEquals(MPTContants.PROVIDE_YEARS, e.getMessage());
		}
	}

	@Test
	public void testZeroSimulations() {
		PortfolioModel portfolioModel = new PortfolioModel(1000, 9.4324f, 15.675f,3.5f);
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 10, 0);
		} catch (Exception e) {			
			assertEquals(MPTContants.PROVIDE_SIMULATIONS, e.getMessage());
		}
	}
	
	@Test
	public void testNullPortfolioModel() {
		PortfolioModel portfolioModel = null;
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 10, 1000);
		} catch (Exception e) {			
			assertEquals(MPTContants.PROVIDE_PORTFOLIO, e.getMessage());
		}
	}

	@Test
	public void testPercentileValue() {
		PortfolioModel portfolioModel = new PortfolioModel(1000, 9.4324f, 15.675f,3.5f);
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 10, 1000);
			porfolioSimulatorService.percentilePorfolioValue(0);
		} catch (Exception e) {			
			assertEquals(MPTContants.VALID_PERCENTILE, e.getMessage());
		}
	}
	
	@Test
	public void testPortfolioMedianAggr() {
		PortfolioModel portfolioModel = new PortfolioModel(100000, 9.4324f, 15.675f,3.5f);
		double medianValue = 0;
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 20, 10000);
			medianValue = porfolioSimulatorService.percentilePorfolioValue(50);
		} catch (Exception e) {			
			assertEquals(MPTContants.VALID_PERCENTILE, e.getMessage());
		}
		assertEquals(250000, medianValue,6000);
	}
	
	@Test
	public void testPortfolioBest10Agg() {
		PortfolioModel portfolioModel = new PortfolioModel(100000, 9.4324f, 15.675f,3.5f);
		double Best10Value = 0;
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 20, 10000);
			Best10Value = porfolioSimulatorService.percentilePorfolioValue(90);
		} catch (Exception e) {			
			assertEquals(MPTContants.VALID_PERCENTILE, e.getMessage());
		}
		assertEquals(569000, Best10Value, 10000);
	}


	@Test
	public void testPortfolioMedianVCon() {
		PortfolioModel portfolioModel = new PortfolioModel(100000, 6.189f, 6.3438f,3.5f);
		double medianValue = 0;
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 20, 10000);
			medianValue = porfolioSimulatorService.percentilePorfolioValue(50);
		} catch (Exception e) {			
			assertEquals(MPTContants.VALID_PERCENTILE, e.getMessage());
		}
		assertEquals(160000, medianValue, 6000);
	}

	@Test
	public void testPortfolioBest10VCon() {
		PortfolioModel portfolioModel = new PortfolioModel(100000, 6.189f, 6.3438f,3.5f);
		double Best10Value = 0;
		try {
			porfolioSimulatorService.simulatePortfolio(portfolioModel, 20, 10000);
			Best10Value = porfolioSimulatorService.percentilePorfolioValue(90);
		} catch (Exception e) {			
			assertEquals(MPTContants.VALID_PERCENTILE, e.getMessage());
		}
		assertEquals(225000, Best10Value, 6000);
	}

	
}
