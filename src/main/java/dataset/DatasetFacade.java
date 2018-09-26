package dataset;

import java.io.Serializable;
import vector.Vector;

public class DatasetFacade implements Serializable{

	private static final long serialVersionUID = 2360992297716182605L;
	
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
