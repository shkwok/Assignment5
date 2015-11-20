package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple tp = leftChild.next();
		Tuple tp2 = rightChild.next();
		Tuple temp = null;
		
		while (tp != null){
			tuples1.add(tp);
			tp =leftChild.next();
		}
		
		while(tp2 != null){
			for (int i = 0 ; i<tuples1.size();i++){
				tp = tuples1.get(i);
				for (int j = 0; j <tp.getAttributeList().size();j++){
					for (int k = 0 ; k< tp2.getAttributeList().size();k++){
						if(tp.getAttributeName(j).equals(tp2.getAttributeName(k)) 
							&& tp.getAttributeValue(j).equals(tp2.getAttributeValue(k)) ){
								ArrayList<Attribute> list = new ArrayList<Attribute>();
								for(int  x = 0; x <tp.getAttributeList().size() ;x++){
									list.add(tp.getAttributeList().get(x));
								}
								for(int y = 0; y <tp2.getAttributeList().size() ;y++){
									if(y != j) list.add(tp2.getAttributeList().get(y));
								}
								return new Tuple(list);
						}

					}
				}
			}
			tp2 = rightChild.next();
		}
		
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}