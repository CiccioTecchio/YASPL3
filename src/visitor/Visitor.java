package visitor;
import syntaxTree.comp.Node;

public interface Visitor <E> {

	E visit(Node n);
}
