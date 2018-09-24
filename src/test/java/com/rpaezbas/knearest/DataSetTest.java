package com.rpaezbas.knearest;

import java.util.ArrayList;

import dataset.DataSet;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import vector.Vector;

public class DataSetTest extends TestCase {

	ArrayList<Vector> vectors;
	DataSet dataSet;
	Vector target;
	float epsilon; // This is the float error range

	public DataSetTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(DataSetTest.class);
	}

	@Override
	public void setUp() {

		vectors = new ArrayList<Vector>();
		vectors.add(new Vector(new float[] { 2.0F, 2.0F }));
		vectors.add(new Vector(new float[] { 1.0F, 15.0F }));
		vectors.add(new Vector(new float[] { 5.0F, 3.0F }));
		vectors.add(new Vector(new float[] { 8.0F, 8.0F }));
		dataSet = new DataSet(vectors);
		target = new Vector(new float[] { 4.0F, 3.0F });
		epsilon = 0.001F;

	}

	public void testCalculateDistances() {

		float[] distances = dataSet.calculateDistances(target);

		assertEquals (distances.length, vectors.size());
		assertEquals(distances[0],
				Math.sqrt(Math.pow(target.getValues()[0] - dataSet.getVectors().get(0).getValues()[0], 2)
						+ Math.pow(target.getValues()[1] - dataSet.getVectors().get(0).getValues()[1], 2)),
				epsilon);
		assertEquals(distances[1],
				Math.sqrt(Math.pow(target.getValues()[0] - dataSet.getVectors().get(1).getValues()[0], 2)
						+ Math.pow(target.getValues()[1] - dataSet.getVectors().get(1).getValues()[1], 2)),
				epsilon);
	}

	public void testCalculateNearest() {

		ArrayList<Vector> nearestVectors = dataSet.calculateNearest(target, 3);
		
		assertEquals(nearestVectors.get(0).getValues()[0],5.0F,epsilon); //The nearest point to 4,3 is 5,3
		assertEquals(nearestVectors.get(1).getValues()[0],2.0F,epsilon); //The second is 2,2
		assertEquals(nearestVectors.get(2).getValues()[0],8.0F,epsilon); //The third is 8,8 
		// assertEquals(nearestVectors.get(3).getValues()[0],1.0F,epsilon); //The third is 8,8 
	}

}
