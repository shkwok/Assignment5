package simpledatabase;

import java.util.*;

public class Sort extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	public Sort(Operator child, String orderPredicate) {
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();

	}

	/**
	 * The function is used to return the sorted tuple
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {
		// ArrayList<Tuple> temp = new ArrayList<Tuple>();
		ArrayList<String> list = new ArrayList<String>();
		Tuple temp = null;

		if (tuplesResult.isEmpty()) {
			Tuple tp = child.next();
			while (tp != null) {
				tuplesResult.add(tp);
				tp = child.next();
			}

			if (tuplesResult.isEmpty())
				return null;
		}

		for (int i = 0; i < tuplesResult.size(); i++) {
			for (int j = 0; j < tuplesResult.get(i).getAttributeList().size(); j++) {
				if (tuplesResult.get(i).getAttributeName(j).equals(orderPredicate)) {
					list.add(String.valueOf(tuplesResult.get(i).getAttributeValue(j)));
					break;
				}
			}
		}

		Collections.sort(list);

		while (!tuplesResult.isEmpty()) {
			for (int i = 0; i < tuplesResult.size(); i++) {
				for (int j = 0; j < tuplesResult.get(i).getAttributeList().size(); j++) {
					if (tuplesResult.get(i).getAttributeValue(j).toString().equals(list.get(0))) {
						temp = tuplesResult.get(i);
						list.remove(0);
						tuplesResult.remove(tuplesResult.get(i));
						return temp;
					}
				}
			}
		}

		return null;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return child.getAttributeList();
	}

}