package rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import dataset.DataSet;
import dataset.DatasetFacade;
import exceptions.CalculateNearestException;
import util.Config;
import vector.Vector;

@Stateless
@Path("/dataset")
public class Resource {
	
	Response response;
	Properties properties = new Properties();

	@POST
	@Path("/calculate")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calculateKnearest(final DatasetFacade datasetFacade) {

		ArrayList<Vector> vectorsList = new ArrayList<Vector>();
		for (Vector vector : datasetFacade.getVectors()) {
			vectorsList.add(vector);
		}

		DataSet dataset = new DataSet(vectorsList);

		try {
			ArrayList<Vector> resultList = dataset.calculateNearest(datasetFacade.getTarget(), datasetFacade.getK());
			Vector[] resultArray = resultList.toArray(new Vector[resultList.size()]);
			return Response.status(200).entity(resultArray).build();
		} catch (CalculateNearestException e) {
			return Response.status(200).entity("K is not correct").build();
		}

	}

	@POST
	@Path("/serialize")
	@Consumes("application/json")
	@Produces("application/json")
	public Response serializeDataset(final DatasetFacade datasetFacade) throws FileNotFoundException, IOException {
		
		try {
			// FileOutputStream <- ObjectOutputStream <- datasetFade  
			FileOutputStream fileOut = new FileOutputStream(
					Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(datasetFacade);
			out.close();
			fileOut.close();
			System.out.printf(
					"Serialized data is saved in " + Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		return Response.status(200).entity(datasetFacade).build();
	}

	@POST
	@Path("/calculate-ser")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calculateKnearestWithSerialized(final DatasetFacade datasetFacade) {

		try {
			// Open serialized dataset
			FileInputStream fileIn = new FileInputStream(
					Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			DatasetFacade serializedDataset = (DatasetFacade) in.readObject();
			in.close();
			fileIn.close();
			// Set the vectors of the serialized dataset
			datasetFacade.setVectors(serializedDataset.getVectors());
			return this.calculateKnearest(datasetFacade);
		} catch (IOException | ClassNotFoundException i) {
			i.printStackTrace();
			return Response.status(500).entity("The dataset name was not found").build();
		}

	}

}
