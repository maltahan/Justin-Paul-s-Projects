

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

class IExpressionParser {
	IExpression parse(String s) {
		ANTLRStringStream input = new ANTLRStringStream(s);
		ExpressionParser parser = new ExpressionParser(
				new CommonTokenStream(new ExpressionLexer(input)));
		ExpressionParser.expr_return r;
		try {
			r = parser.expr();
			ExprTree walker = new ExprTree(new CommonTreeNodeStream((CommonTree) r.getTree()));
			return walker.expression();
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new RuntimeException("The parser has experienced a problem.");
		}
	}
}
