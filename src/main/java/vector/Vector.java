package vector;

import java.io.Serializable;

import exceptions.GetDistanceException;
import exceptions.VectorInitializationException;

public class Vector implements Serializable{

	private static final long serialVersionUID = -1190255226278518719L;
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
		
		if(vector == null) {
			throw new GetDistanceException("The vector is null");
		}
		
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
