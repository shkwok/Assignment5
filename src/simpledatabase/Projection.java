package simpledatabase;
import java.util.ArrayList;

import javax.swing.text.AsyncBoxView.ChildLocator;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tp = child.next();
		ArrayList<Attribute> list = new ArrayList<Attribute>(); 
		if (tp == null) return null;
		
		for (int i = 0 ; i < tp.getAttributeList().size() ; i++ ){
			if (tp.getAttributeList().get(i).getAttributeName().equals (attributePredicate)){
				list.add(tp.getAttributeList().get(i));
				return new Tuple (list);
				}
		}
		return null;
	}


	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}
}
