package poly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolyTest {
	static double[][] coefs = {
			{1,2},{0},{5,8,8,0,0},
			{4}, {7,8,1,2}
	};
	Poly[] polys;

	@Before
	public void setUp() throws Exception {
		this.polys = new Poly[coefs.length];
		for(int i=0; i<coefs.length; i++) {
			polys[i] = new Poly(coefs[i]);
		}
			
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void construct() {
		double[][] result = {
				{1,2},{0},{5,8,8},
				{4}, {7,8,1,2}
		};
		double[][] verif = coefs.clone();
		for(int i=0; i<coefs.length; i++) {
			verif[i] = polys[i].getCoef();
		}
		assertArrayEquals(verif, result);
	}
	
	@Test
	public void derivTest() {
		double[][] result = {
				{2},{0},{8,16},
				{0}, {8,2,6}
		};
		double[][] verif = coefs.clone();
		for(int i=0; i<coefs.length; i++) {
			verif[i] = polys[i].derivee().getCoef();
		}
		assertArrayEquals(verif, result);
	}

}
