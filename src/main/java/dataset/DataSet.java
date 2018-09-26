package dataset;

import java.util.ArrayList;

import exceptions.DataSetInitializationException;
import exceptions.calculateNearestException;
import vector.Vector;

public class DataSet {

	private ArrayList<Vector> vectors;

	public DataSet() throws DataSetInitializationException {
		throw new DataSetInitializationException("The vector must be initialized with an ArrayList of vectors");
	}

	public DataSet(ArrayList<Vector> vectors) {
		this.vectors = vectors;
	}

	public ArrayList<Vector> calculateNearest(Vector target, int k) throws calculateNearestException{
		
		if(k < 1 || k > this.vectors.size()) {
			throw new calculateNearestException("k parameter must be a between 1 and the number of vectors in the dataset (vectors.size())");
		}

		ArrayList<Vector> sortedVectors = new ArrayList<>(this.vectors);
		sortedVectors.sort((v1,v2) -> new Float(v1.getDistance(target)).compareTo(v2.getDistance(target)));
		
		ArrayList<Vector> sortedVectorsSublist = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			sortedVectorsSublist.add(sortedVectors.get(i));
		}
		
		return sortedVectorsSublist;
	}
	

	public ArrayList<Vector> getVectors() {
		return vectors;
	}

	public void setVectors(ArrayList<Vector> vectors) {
		this.vectors = vectors;
	}

}
