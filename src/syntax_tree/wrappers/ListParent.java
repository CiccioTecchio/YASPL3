package syntax_tree.wrappers;

import java.util.ArrayList;
import syntax_tree.comp.Node;
import syntax_tree.comp.Internal;

public class ListParent <E> extends Internal {

	private ArrayList<E> list;
	
	public ListParent (String op, Node...sons){
		super(op, sons);
		this.list = new ArrayList();
	}
	
	public  ListParent<E> addChild(E node) {
		this.list.add(node);
		return this;
	}
	
}
