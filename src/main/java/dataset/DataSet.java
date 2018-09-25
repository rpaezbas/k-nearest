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

		float[] distances = this.calculateDistances(target);

		// Initialize minorDistances
		float[] minorDistances = new float[k];
		for (int i = 0; i < k; i++) {
			minorDistances[i] = distances[i];
		}

		// Initialize kNearest list
		ArrayList<Vector> kNearest = new ArrayList<Vector>();
		for (int i = 0; i < k; i++) {
			kNearest.add(this.vectors.get(i));
		}

		// Initially, minorDitances must be sorted from less to more
		for (int i = 0; i < minorDistances.length; i++) {
			for (int j = 0 + i; j < minorDistances.length; j++) {
				if (minorDistances[i] > minorDistances[j]) {

					// Exchange values
					float aux = minorDistances[j];
					minorDistances[j] = minorDistances[i];
					minorDistances[i] = aux;

					Vector auxVector = kNearest.get(j);
					kNearest.set(j, kNearest.get(i));
					kNearest.set(i, auxVector);
				}
			}
		}

		// Get vectors with less distance
		for (int i = k; i < distances.length; i++) {
			for (int j = 0; j < minorDistances.length; j++) {
				if (distances[i] < minorDistances[j]) {
					minorDistances[j] = distances[i];
					kNearest.set(j, this.vectors.get(i));
					break;
				}
			}
		}

		return kNearest;
	}
	

	public ArrayList<Vector> getVectors() {
		return vectors;
	}

	public void setVectors(ArrayList<Vector> vectors) {
		this.vectors = vectors;
	}

}
