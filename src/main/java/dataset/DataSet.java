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


	public float[] calculateDistances(Vector target) {

		float[] distances = new float[this.vectors.size()];
		for (int i = 0; i < vectors.size(); i++) {
			float sum = 0;
			for (int j = 0; j < target.getValues().length; j++) {
				float[] vectorX = target.getValues();
				float[] vectorY = this.vectors.get(i).getValues();
				sum += Math.pow(vectorX[j] - vectorY[j], 2);
			}
			distances[i] = (float) Math.sqrt(sum);
		}
		return distances;
		
	}

	public ArrayList<Vector> calculateNearest(Vector target, int k) throws calculateNearestException{
		
		if(k < 1 || k > this.vectors.size()) {
			throw new calculateNearestException("k parameter must be a between 1 and the number of vectors in the dataset (vectors.size())");
		}

		ArrayList<Vector> sortedVectors = new ArrayList<>(this.vectors);
		sortedVectors.sort((v1,v2) -> new Float(v1.getDistance(target)).compareTo(v2.getDistance(target)));
		return sortedVectors;
	}
	

	public ArrayList<Vector> getVectors() {
		return vectors;
	}

	public void setVectors(ArrayList<Vector> vectors) {
		this.vectors = vectors;
	}

}
