package rest;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import dataset.DataSet;
import exceptions.VectorInitializationException;
import exceptions.calculateNearestException;
import vector.Vector;

@Stateless
@Path("/test")
public class Resource {

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calculateKnearest(final DatasetFacade datasetFacade) {

		Response response;

		ArrayList<Vector> vectorsList = new ArrayList<Vector>();
		for (Vector vector : datasetFacade.getVectors()) {
			vectorsList.add(vector);
		}

		DataSet dataset = new DataSet(vectorsList);

		try {
			ArrayList<Vector> resultList = dataset.calculateNearest(datasetFacade.getTarget(), datasetFacade.getK());
			Vector[] resultArray = resultList.toArray(new Vector[resultList.size()]); // Conversion to Vector[]																					
			return Response.status(200).entity(resultArray).build();
		} catch (calculateNearestException e) {
			return Response.status(200).entity("K is not correct").build();
		}

	}
	
	
}
