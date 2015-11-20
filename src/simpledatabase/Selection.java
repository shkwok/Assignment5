package simpledatabase;

import java.util.ArrayList;

public class Selection extends Operator {

	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate,
			String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}

	/**
	 * Get the tuple which match to the where condition
	 * 
	 * @return the tuple
	 */
	@Override
	public Tuple next() {
		if (child.from.equals(whereTablePredicate) == false)
			return child.next();
		
		Tuple tp = child.next();
		ArrayList<Attribute> list = new ArrayList<Attribute>();
		if (tp == null)
			return null;
		if (child.from.equals(whereTablePredicate)) {
			while (tp != null) {
				for (int i = 0; i < tp.getAttributeList().size(); i++) {
					if (tp.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate)
							&& tp.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)) {
						return tp;
					}
				}
				tp = child.next();
			}
			
		}
		return null;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return the attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {

		return (child.getAttributeList());
	}

}