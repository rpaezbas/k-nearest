package vector;

import exceptions.VectorInitializationException;

public class Vector {

	private float[] values;

	public Vector()  {
	}

	public Vector(float[] values) throws VectorInitializationException {
		if (values == null) {
			throw new VectorInitializationException("The vector must be initialized with an array of values not null");
		}
		this.values = values;
	}
	
	public float getDistance(final Vector vector){
		
		return (float) Math.sqrt(Math.pow(this.getValues()[0] - vector.getValues()[0], 2)
				+ Math.pow(this.getValues()[1] - vector.getValues()[1], 2));
	}

	public float[] getValues() {
		return values;
	}

	public void setValues(float[] values) {
		this.values = values;
	}

}
