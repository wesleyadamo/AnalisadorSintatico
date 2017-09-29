package analisadorLexico;

public class Teste {
	
	public static void main(String[] args) {
		char []buffer = new char [100] ;
		buffer[0] = ' ';
		buffer[1]  = 'b';
		
		int temp =0;
		
		
		System.out.println(!Character.isWhitespace(buffer[temp]));
		
		System.out.println(buffer[temp] == '+' || !(Character.isLetterOrDigit(buffer[temp])) || !Character.isSpaceChar(buffer[temp]) );
		
	}

}
