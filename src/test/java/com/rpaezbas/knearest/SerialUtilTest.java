package com.rpaezbas.knearest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dataset.DatasetFacade;
import exceptions.VectorInitializationException;
import util.Config;
import util.SerialUtil;
import vector.Vector;

public class SerialUtilTest {
	
	DatasetFacade datasetFacade = new DatasetFacade();
	Vector target;
	ArrayList<Vector> vectors;
	
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
		datasetFacade.setVectors(vectors.toArray(new Vector[vectors.size()])); //Convert from array list to array
	}
	
	@Test
	public void serializeAndDeserializeTest() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		datasetFacade.setName("testName");
		SerialUtil.serializeDataset(datasetFacade);
		File f = new File(Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
		assertEquals(f.exists(),true);
		
		DatasetFacade deserializedDataset = SerialUtil.deserializeDataset(datasetFacade.getName());
		assertEquals(deserializedDataset.getName(),datasetFacade.getName());
		assertEquals(deserializedDataset.getVectors().length,5);
		
	}
	
	@After
	public void cleanUp() {
		File file = new File(Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
		file.delete();
	}

}
