package com.rpaezbas.knearest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dataset.DataSet;
import exceptions.CalculateNearestException;
import exceptions.VectorInitializationException;
import vector.Vector;

public class DataSetExceptionsTest {
	
	ArrayList<Vector> vectors;
	DataSet dataSet;
	Vector target;
	
	@Before
	public void setUp() {

		vectors = new ArrayList<Vector>();

		try {
			vectors.add(new Vector(new float[] { 2.0F, 2.0F }));
			vectors.add(new Vector(new float[] { 1.0F, 15.0F }));
			target = new Vector(new float[] { 4.0F, 3.0F });
		} catch (VectorInitializationException e) {
			System.out.println(e.getMessage());
		}

		dataSet = new DataSet(vectors);

	}

	
	@Test(expected = CalculateNearestException.class) 
	public void calculateZeroNearest() throws CalculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 0);
	}
	
	@Test(expected = CalculateNearestException.class) 
	public void calculateTooManyNearest() throws CalculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 100);
	}
	
	@Test(expected = CalculateNearestException.class) 
	public void calculateMinusNearest() throws CalculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, -1);
	}
	
	
	@Test(expected = CalculateNearestException.class) 
	public void calculateNullTargetNearest() throws CalculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(null, -1);
	}
}
