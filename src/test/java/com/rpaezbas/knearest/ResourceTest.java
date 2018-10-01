package com.rpaezbas.knearest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dataset.DataSet;
import dataset.DatasetFacade;
import exceptions.CalculateNearestException;
import exceptions.VectorInitializationException;
import rest.Resource;
import vector.Vector;

@RunWith(MockitoJUnitRunner.class)
public class ResourceTest {

	@Mock
	DataSet dataset;

	@InjectMocks
	Resource resource;

	@Test
	public void testCalculateKnearest() throws CalculateNearestException, VectorInitializationException {
		
		// DatasetFacade initialization
		Vector target = new Vector(new float[] { 4.0F, 3.0F });
		ArrayList<Vector> vectors = new ArrayList<Vector>();
		vectors.add(new Vector(new float[] { 2.0F, 2.0F }));
		vectors.add(new Vector(new float[] { 1.0F, 15.0F }));
		DatasetFacade datasetFacade = new DatasetFacade();
		datasetFacade.setVectors(vectors.toArray(new Vector[vectors.size()]));
		datasetFacade.setTarget(target);
		datasetFacade.setK(1);

		doReturn(vectors).when(dataset).calculateNearest(datasetFacade.getTarget(), datasetFacade.getK());

		resource.calculateKnearest(datasetFacade);

		verify(dataset, times(1)).calculateNearest(datasetFacade.getTarget(), datasetFacade.getK());
	}

}
