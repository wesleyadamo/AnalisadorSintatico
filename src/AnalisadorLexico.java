import java.util.ArrayList;
import java.util.Stack;

public class AnalisadorLexico {

	Stack<Token> proximo = new Stack<>();
	
	Token temp ;

	public AnalisadorLexico() {

		
		proximo.push(new Token("$"));
		proximo.push(new Token("id"));
		
	}

	public Token nextToken() {
		
		return proximo.pop();


	}

}
