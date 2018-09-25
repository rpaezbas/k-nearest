package dataset;

import javax.persistence.Entity;
import vector.Vector;

@Entity
public class DatasetFacade {
	
	private Vector[] vectors;
	private Vector target;
	private int k;
	
	public Vector[] getVectors() {
		return vectors;
	}
	public void setVectors(Vector[] vectors) {
		this.vectors = vectors;
	}
	public Vector getTarget() {
		return target;
	}
	public void setTarget(Vector target) {
		this.target = target;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}

}
