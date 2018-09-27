package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataset.DatasetFacade;

public class SerialUtil {

	public static DatasetFacade deserializeDataset (String datasetName) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(
		util.Config.DATASETS_PATH.getValue() + datasetName + ".ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		DatasetFacade dataset = (DatasetFacade) in.readObject();
		in.close();
		fileIn.close();
		return dataset;
	}
	
	public static DatasetFacade serializeDataset(DatasetFacade datasetFacade) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(util.Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(datasetFacade);
		out.close();
		fileOut.close();
		System.out.printf("Serialized data is saved in " + util.Config.DATASETS_PATH.getValue() + datasetFacade.getName() + ".ser");
		return datasetFacade;
	}
}
