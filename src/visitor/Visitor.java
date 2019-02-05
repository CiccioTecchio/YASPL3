package visitor;
import syntax_tree.*;

public interface Visitor {

	Object visit(Node n);
}
