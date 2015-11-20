package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator {
	private BufferedReader br = null;
	private boolean getAttribute = false;
	private Tuple tuple;
	private String row1;
	private String row2;
	private String row3;

	public Table(String from) {
		this.from = from;

		// Create buffer reader
		try {
			br = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/datafile/" + from + ".csv")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create a new tuple and return the tuple to its parent. Set the attribute
	 * list if you have not prepare the attribute list
	 * 
	 * @return the tuple
	 */
	@Override
	public Tuple next() {
		if (getAttribute == false) {
			try {
				row1 = br.readLine();
				row2 = br.readLine();
				row3 = br.readLine();
				tuple = new Tuple(row1, row2, row3);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				row3 = br.readLine();
				if (row3 != null) {
					tuple = new Tuple(row1, row2, row3);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
				} else
					return null;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return tuple;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return the attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return tuple.getAttributeList();

	}

	

}
