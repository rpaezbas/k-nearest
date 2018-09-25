package com.rpaezbas.knearest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;

import dataset.DataSet;
import exceptions.VectorInitializationException;
import exceptions.calculateNearestException;
import org.junit.Test;
import vector.Vector;

public class DataSetTest{

	ArrayList<Vector> vectors;
	DataSet dataSet;
	Vector target;
	float epsilon; // This is the float error range

	@Before
	public void setUp() {

		vectors = new ArrayList<Vector>();

		try {
			vectors.add(new Vector(new float[] { 2.0F, 2.0F }));
			vectors.add(new Vector(new float[] { 1.0F, 15.0F }));
			vectors.add(new Vector(new float[] { 5.0F, 3.0F }));
			vectors.add(new Vector(new float[] { 8.0F, 8.0F }));
			vectors.add(new Vector(new float[] { 60.0F, 60.0F }));
			target = new Vector(new float[] { 4.0F, 3.0F });
		} catch (VectorInitializationException e) {
			System.out.println(e.getMessage());
		}

		dataSet = new DataSet(vectors);
		epsilon = 0.001F;

	}

	@Test
	public void testCalculateDistances() {

		float[] distances = dataSet.calculateDistances(target);

		assertEquals(distances.length, vectors.size());
		assertEquals(distances[0],
				Math.sqrt(Math.pow(target.getValues()[0] - dataSet.getVectors().get(0).getValues()[0], 2)
						+ Math.pow(target.getValues()[1] - dataSet.getVectors().get(0).getValues()[1], 2)),
				epsilon);
		assertEquals(distances[1],
				Math.sqrt(Math.pow(target.getValues()[0] - dataSet.getVectors().get(1).getValues()[0], 2)
						+ Math.pow(target.getValues()[1] - dataSet.getVectors().get(1).getValues()[1], 2)),
				epsilon);
	}

	@Test
	public void testCalculateNearest() throws calculateNearestException {

		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 4);
		assertEquals(nearestVectors.get(0).getValues()[0], 5.0F, epsilon); // The nearest point to 4,3 is 5,3
		assertEquals(nearestVectors.get(1).getValues()[0], 2.0F, epsilon); // The second is 2,2
		assertEquals(nearestVectors.get(2).getValues()[0], 8.0F, epsilon); // The third is 8,8
		assertEquals(nearestVectors.get(3).getValues()[0], 1.0F, epsilon); // The forth is 1,15

		nearestVectors = dataSet.calculateNearest(target, 5);
		assertEquals(nearestVectors.get(0).getValues()[0], 5.0F, epsilon); // The nearest point to 4,3 is 5,3
		assertEquals(nearestVectors.get(1).getValues()[0], 2.0F, epsilon); // The second is 2,2
		assertEquals(nearestVectors.get(2).getValues()[0], 8.0F, epsilon); // The third is 8,8
		assertEquals(nearestVectors.get(3).getValues()[0], 1.0F, epsilon); // The forth is 1,15
		assertEquals(nearestVectors.get(4).getValues()[0], 60.0F, epsilon); // The fifth is 60,60

		nearestVectors = dataSet.calculateNearest(target, 1);
		assertEquals(nearestVectors.get(0).getValues()[0], 5.0F, epsilon); // The nearest point to is 5,3
	}
	
	
	@Test(expected = calculateNearestException.class) 
	public void calculateZeroNearest() throws calculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 0);
	}
	
	@Test(expected = calculateNearestException.class) 
	public void calculateTooManyNearest() throws calculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 100);
	}
	
	@Test(expected = calculateNearestException.class) 
	public void calculateMinusNearest() throws calculateNearestException {
		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, -1);
	}

}
