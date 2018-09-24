package vector;

import exceptions.VectorInitializationException;

public class Vector {
	
	private float[] values;
	
	public Vector() throws VectorInitializationException {
		throw new VectorInitializationException("The vector must be initialized with an array of values");
	}
	
	public Vector(float[] values) {
		this.values = values;
	}

	public float[] getValues() {
		return values;
	}

	public void setValues(float[] values) {
		this.values = values;
	}

}