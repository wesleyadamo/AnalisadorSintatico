package analisadorLexico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scan {

	static Map<String, Token> tabelaSimbolos = new HashMap<String, Token>();
	static StringBuffer strFinal = new StringBuffer();
	
	static ArrayList<Token> tokens =  new ArrayList<Token>();

	public static void criarTabela() {
		tabelaSimbolos.put("int", new Token("int", "int"));
		tabelaSimbolos.put("{", new Token("{", "{"));
		tabelaSimbolos.put("}", new Token("}", "}"));
		tabelaSimbolos.put(";", new Token(";", ";"));
		tabelaSimbolos.put("(", new Token("(", "("));
		tabelaSimbolos.put(")", new Token(")", ")"));
		tabelaSimbolos.put("while", new Token("palavra reservada while", "while"));
		tabelaSimbolos.put("for", new Token("palavra reservada for", "for"));
		tabelaSimbolos.put("if", new Token("palavra reservada if", "if"));
		tabelaSimbolos.put("else", new Token("palavra reservada else", "else"));
		tabelaSimbolos.put("=", new Token("operador de atribuição", "="));
		tabelaSimbolos.put("==", new Token("operador de igualdade", "=="));
		tabelaSimbolos.put(">", new Token("operador maior que", ">"));
		tabelaSimbolos.put("<", new Token("operador menor que", "<"));
		tabelaSimbolos.put("<=", new Token("operador menor ou igual", "<="));
		tabelaSimbolos.put(">=", new Token("operador maior ou igual", ">="));
		tabelaSimbolos.put("!=", new Token("operador diferente que ", "!="));
		tabelaSimbolos.put("+", new Token("operador de soma", "+"));
		tabelaSimbolos.put("-", new Token("operador de subtração", "-"));
		tabelaSimbolos.put("/", new Token("operador de divisão", "/"));
		tabelaSimbolos.put("*", new Token("operador de multiplicação", "*"));
		tabelaSimbolos.put("**", new Token("operador de exponenciação", "**"));
		tabelaSimbolos.put("++", new Token("operador de incremento", "++"));
		tabelaSimbolos.put("--", new Token("operador de decremento", "--"));
		tabelaSimbolos.put("main", new Token("palavra reservada main", "id"));
	}

	public static void tabelaSimbolos(String str) {

		System.out.println("str: "+ str);
		if (tabelaSimbolos.containsKey(str)) {
			strFinal.append("< " + tabelaSimbolos.get(str).getNome() + " , " + tabelaSimbolos.get(str).getAtributo() + " >  ");
			tokens.add(tabelaSimbolos.get(str));

		} else {
			System.out.println("STR ADD: +" );
			Token tk = new Token(str, "id");
			tabelaSimbolos.put(str, tk);
			strFinal.append("<"+str+", id>");
			tokens.add(tk);

		}

	}

  public ArrayList<Token> obterTokens() throws IOException{
	  char[] aux = null;
		
		try {

			criarTabela();

			File arquivo = new File("entrada.txt");
			FileReader fr = new FileReader(arquivo);
			char[] buffer = new char[(int) arquivo.length()];

			int i = 0;
			while ((fr.read(buffer)) != -1) {
			}

			aux = buffer;

			StringBuffer str = new StringBuffer();
			int estado = 1;

			for (i = 0; i < buffer.length - 1; i++) {

				switch (estado) {
				case 1: // identificador ou palavra reservada
					if (Character.isLetter(buffer[i])) {

						// enquanto caracter ou número
						while (Character.isLetterOrDigit(buffer[i])) {
							str.append(buffer[i]);
							i++;
						}

						i--;
						// System.out.println("Valor lido: "+str.toString());
						tabelaSimbolos(str.toString()); // pesquisa na tabela de símbolos
						str.delete(0, str.length()); // limpa a string auxiliar
						estado = 1;

					} else {
						estado = 2;
						i--;
					} // ativa próximo diagrama

					break;
				case 2: // atributos relacionais

					boolean found = false;
					if (buffer[i] == ';' || buffer[i] == '{' || buffer[i] == '}' || buffer[i] == '(' || buffer[i] == ')') {

						tabelaSimbolos(str.append(buffer[i]).toString());
						str.delete(0, str.length());
						found = true;
						estado = 1;
						break;

					} else if (buffer[i] == '=') {
						str.append("=");

						i++;
						if (buffer[i] == '=')
							str.append("=");
						if (buffer[i] == '>')
							str.append(">");

						tabelaSimbolos(str.toString());
						if (str.length() == 1)
							i--; // caso de =
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;
					}

					if (buffer[i] == '!')
						if (buffer[++i] == '=') {
							str.append("!=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							strFinal.append("Erro léxico em !");
							i--;
						}

					if (buffer[i] == '<')
						if (buffer[++i] == '=') {
							str.append("<=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							str.append('<');
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							--i;
							found = true;
							estado = 1;
							break;

						}

					if (buffer[i] == '>')
						if (buffer[++i] == '=') {
							str.append(">=");
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							estado = 1;
							found = true;
							break;
						} else {
							str.append('>');
							tabelaSimbolos(str.toString());
							str.delete(0, str.length());
							--i;
							found = true;
							estado = 1;
							break;

						}

					if (!found) {
						estado++;
						i--;

					}

					break;
				case 3:

					found = false;
					if (buffer[i] == '+') {
						str.append('+');
						if (buffer[++i] == '+')
							str.append('+');
						else
							--i;

						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;

					}

					if (buffer[i] == '-') {
						str.append('-');
						if (buffer[++i] == '-')
							str.append('-');
						else
							--i;

						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;

					}

					if (buffer[i] == '*') {
						str.append('*');
						if (buffer[++i] == '*')
							str.append('*');
						else
							--i;

						tabelaSimbolos(str.toString());
						str.delete(0, str.length());
						estado = 1;
						found = true;
						break;

					}

					if (buffer[i] == '/') {
						if (buffer[++i] == '/') {

							while ((int) buffer[i] != 10) {
								i++;

							}
						} else {
							--i;
							//tabelaSimbolos(str.toString());
							Token tk =  new Token("/", "/");
							tokens.add(tk);
							str.delete(0, str.length());
							estado = 1;
							found = true;
							
							break;
						}

					}

					if (!found) {
						--i;
						estado++;
					}

					break;

				case 4: // reconhecimento de número inteiro
					if (Character.isDigit(buffer[i])) {
						while (Character.isDigit(buffer[i])) {
							str.append(buffer[i]);
							i++;
						}

						
						strFinal.append("<"+ str.toString() + ", num >\n");
						Token tk = new Token(str.toString(), "num");
						tokens.add(tk);
						str.delete(0, str.length());
						estado = 1;
						i--;
						break;
					}

					estado++;
					--i;
					break;

				case 5: // reconhecimento de espaço, /n e começo de linha
					if ((int) buffer[i] == 32 || (int) buffer[i] == 10 || (int) buffer[i]==9) {
						
						while ((int) buffer[i] == ' ' || (int) buffer[i] == 10 || (int) buffer[i] == 12 || (int) buffer[i]==9) {
							i++;
						}
						i--;
						estado = 1;
					} else {
						--i;
						estado++;
						break;
					}

					break;
				
				case 6 : 
					strFinal.append("erro léxico: "+ buffer[i]+"   ");
					estado =1;
					break;

				default:
					break;
				}

			}

		} catch (Exception e) {		}

		OutputStream os = new FileOutputStream("saida.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);

		bw.write("Entrada\n===========================================================================\n"
				+ String.copyValueOf(aux));
		bw.write("\n==========================================================================\n");
		bw.write("Saída");
		bw.write("\n==========================================================================\n");
		bw.write(strFinal.toString());

		bw.close();

		//System.out.println(strFinal.toString());
		
		return tokens;
		

	}

  }
