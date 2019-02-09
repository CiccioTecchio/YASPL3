package visitor;
import syntax_tree.comp.Node;

public interface Visitor <E> {

	E visit(Node n);
}
