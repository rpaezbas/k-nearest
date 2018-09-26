package com.rpaezbas.knearest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import dataset.DataSet;
import exceptions.VectorInitializationException;
import org.junit.Test;
import vector.Vector;

public class VectorTest{

	ArrayList<Vector> vectors;
	DataSet dataSet;
	Vector target;
	float epsilon = 0.001F; // This is the float error range

	@Test
	public void testGetDistances() throws VectorInitializationException {
		
		Vector vectorA = new Vector(new float[] { 3.0F, 3.0F });
		Vector vectorB = new Vector(new float[] { 2.0F, 2.0F });

		assertEquals(vectorA.getDistance(vectorB),
				Math.sqrt(Math.pow(vectorA.getValues()[0] - vectorB.getValues()[0], 2)
						+ Math.pow(vectorA.getValues()[1] - vectorB.getValues()[1], 2)),
				epsilon);
		
		 assertEquals(vectorA.getDistance(vectorB),Math.sqrt(2),epsilon);
		 
		 assertEquals(vectorA.getDistance(vectorB),vectorB.getDistance(vectorA),epsilon);
	}
	
	
	@Test(expected = VectorInitializationException.class) 
	public void VectorInitialization() throws VectorInitializationException {
		Vector vectorA = new Vector(null);
	}


}
