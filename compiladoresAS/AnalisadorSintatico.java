package compiladoresAS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import analisadorLexico.Scan;
import analisadorLexico.Token;

public class AnalisadorSintatico {

	Map<String, Producao> tabela = new HashMap<String, Producao>();

	Stack<String> pilha = new Stack<String>();
	Stack<String> entrada = new Stack<String>();

	public void setarTabela() {

		// setando a entrada e a pilha
		pilha.push("$");
		entrada.push("$");

		// estado inicial pilha
		pilha.push("funcao");

		//////////////////////////////////////// // funcao - > tipo id
		//////////////////////////////////////// (Lista_Argumento ) CompStmt
		StackInput si = new StackInput();
		String[] vet = { "CompStmt", ")", "Lista_Argumento", "(", "id", "tipo" };
		si.setarProducoa(vet);
		tabela.put("funcao int", si.p);

		////////////////// // tipo - > int
		si = new StackInput();
		si.setarProducoa("int");
		tabela.put("tipo int", si.p);

		///////////////////////////////// // listaArgumeto -> ( ->>
		///////////////////////////////// listaArgumeto - > &
		
		// esse é o cado que a produção é vazia, no caso, Lista-Argumento -> ∈, então o corpo da produção é
		// uma string vazia
		si = new StackInput();
		si.setarProducoa(" "); 
		tabela.put("Lista_Argumento )", si.p);

		si = new StackInput();
		vet = new String[] { "Lista_Argumento1", "Argumento" };
		si.setarProducoa(vet);
		tabela.put("Lista_Argumento int", si.p);

		si = new StackInput();
		vet = new String[] { "Lista_Argumento1" };
		si.setarProducoa(vet);
		tabela.put("Lista_Argumento ,", si.p);

		//////////////////////////////////////

		si = new StackInput();
		vet = new String[] { "Lista_Argumento1", "Argumento" };
		si.setarProducoa(vet);
		tabela.put("Lista_Argumento1 ,", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("Lista_Argumento1", si.p);

		///////////////////////////////////// // COmpStmt -> { StmtList }
		si = new StackInput();
		vet = new String[] { "}", "StmtLista", "{" };
		si.setarProducoa(vet);
		tabela.put("CompStmt {", si.p);
		/////////////////////////////////////////////////////////

		// Argumento
		si = new StackInput();
		vet = new String[] { "id", "tipo" };
		si.setarProducoa(vet);
		tabela.put("Argumento int", si.p);

		//////////////////////////////////////////////////////////////////

		si = new StackInput();
		vet = new String[] { "id", "tipo" };
		si.setarProducoa(vet);
		tabela.put("Declaracao int", si.p);

		si = new StackInput();
		vet = new String[] { "Expressao", "=", "id" };
		si.setarProducoa(vet);
		tabela.put("Atribuicao id", si.p);

		si = new StackInput();
		vet = new String[] { "Atribuicao", "int" };
		si.setarProducoa(vet);
		tabela.put("Atribuicao int", si.p);

		si = new StackInput();
		vet = new String[] { "Expressao", "=", "id" };
		si.setarProducoa(vet);
		tabela.put("Atribuicao ;", si.p);

		si = new StackInput();
		vet = new String[] { "Expressao", "=", "id" };
		si.setarProducoa(vet);
		tabela.put("Atribuicao )", si.p);

		si = new StackInput();
		vet = new String[] { "id", "tipo" };
		si.setarProducoa(vet);
		tabela.put("Argumento int", si.p);

		si = new StackInput();
		vet = new String[] { "Expressao", "=", "id" };
		si.setarProducoa(vet);
		tabela.put("Expressao ;", si.p);

		si = new StackInput();
		vet = new String[] { "Expressao", "=", "id" };
		si.setarProducoa(vet);
		tabela.put("Expressao )", si.p);

		///////////////////// // {StmtLista}
		si = new StackInput();
		vet = new String[] { "StmtLista1" };
		si.setarProducoa(vet);
		tabela.put("StmtLista for", si.p);
		tabela.put("StmtLista while", si.p);
		tabela.put("StmtLista id", si.p);
		tabela.put("StmtLista (", si.p);
		tabela.put("StmtLista -", si.p);
		tabela.put("StmtLista +", si.p);
		tabela.put("StmtLista num", si.p);
		tabela.put("StmtLista if", si.p);
		tabela.put("StmtLista {", si.p);
		tabela.put("StmtLista ;", si.p);
		tabela.put("StmtLista int", si.p);
		tabela.put("StmtLista =", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("StmtLista }", si.p);

		/////////////////////////////// //StmtLista1

		si = new StackInput();
		vet = new String[] { "StmtLista1", "Stmt" };
		si.setarProducoa(vet);
		tabela.put("StmtLista1 for", si.p);
		tabela.put("StmtLista1 while", si.p);
		tabela.put("StmtLista1 id", si.p);
		tabela.put("StmtLista1 (", si.p);
		tabela.put("StmtLista1 -", si.p);
		tabela.put("StmtLista1 +", si.p);
		tabela.put("StmtLista1 num", si.p);
		tabela.put("StmtLista1 if", si.p);
		tabela.put("StmtLista1 {", si.p);
		tabela.put("StmtLista1 ;", si.p);
		tabela.put("StmtLista1 int", si.p);
		tabela.put("StmtLista1 =", si.p);
		tabela.put("StmtLista1 /", si.p);
		tabela.put("StmtLista1 )", si.p);
		tabela.put("StmtLista1 *", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("StmtLista1 else", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("StmtLista1 }", si.p);

		/////////////////////////////////// Stmt - > forStmt
		si = new StackInput();

		si.setarProducoa("id");
		tabela.put("Stmt id", si.p);

		si = new StackInput();
		vet = new String[] { "int" };
		si.setarProducoa("int");
		tabela.put("Stmt int", si.p);

		si = new StackInput();
		vet = new String[] { "num" };
		si.setarProducoa(vet);
		tabela.put("Stmt num", si.p);

		si = new StackInput();
		vet = new String[] { "id" };
		si.setarProducoa(vet);
		tabela.put("Stmt id", si.p);

		si = new StackInput();
		vet = new String[] { "+" };
		si.setarProducoa(vet);
		tabela.put("Stmt +", si.p);

		si = new StackInput();
		vet = new String[] { "-" };
		si.setarProducoa(vet);
		tabela.put("Stmt -", si.p);

		si = new StackInput();
		vet = new String[] { "(" };
		si.setarProducoa(vet);
		tabela.put("Stmt (", si.p);

		si = new StackInput();
		vet = new String[] { ";" };
		si.setarProducoa(vet);
		tabela.put("Stmt ;", si.p);

		si = new StackInput();
		vet = new String[] { "*" };
		si.setarProducoa(vet);
		tabela.put("Stmt *", si.p);

		si = new StackInput();
		vet = new String[] { "CompStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt {", si.p);

		si = new StackInput();
		vet = new String[] { "ForStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt for", si.p);

		si = new StackInput();
		vet = new String[] { "/" };
		si.setarProducoa(vet);
		tabela.put("Stmt /", si.p);

		si = new StackInput();
		vet = new String[] { "ForStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt for", si.p);

		si = new StackInput();
		vet = new String[] { ")" };
		si.setarProducoa(vet);
		tabela.put("Stmt )", si.p);

		si = new StackInput();
		vet = new String[] { "WhileStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt while", si.p);

		si = new StackInput();
		vet = new String[] { "IfStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt if", si.p);

		si = new StackInput();
		vet = new String[] { "=" };
		si.setarProducoa(vet);
		tabela.put("Stmt =", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("Stmt else", si.p);

		si = new StackInput();
		vet = new String[] { "Stmt", ")", "Atribuicao", ";", "OptExpressao", ";", "Atribuicao", "(", "for" };
		si.setarProducoa(vet);
		tabela.put("ForStmt for", si.p);

		si = new StackInput();
		vet = new String[] { "Stmt", ")", "Expressao", "(", "while" };
		si.setarProducoa(vet);
		tabela.put("WhileStmt while", si.p);

		si = new StackInput();
		vet = new String[] { "ElseOpt", "Stmt", ")", "Expressao", "(", "if" };
		si.setarProducoa(vet);
		tabela.put("IfStmt if", si.p);

		/////////////////////////////////////////////////////////////////////////////

		/// FATOR

		si = new StackInput();
		vet = new String[] { ")", "Expressao", "(" };
		si.setarProducoa(vet);
		tabela.put("Fator (", si.p);

		si = new StackInput();
		vet = new String[] { "Fator", "+" };
		si.setarProducoa(vet);
		tabela.put("Fator +", si.p);

		si = new StackInput();
		vet = new String[] { "Fator", "-" };
		si.setarProducoa(vet);
		tabela.put("Fator -", si.p);

		si = new StackInput();
		vet = new String[] { "id" };
		si.setarProducoa(vet);
		tabela.put("Fator id", si.p);

		si = new StackInput();
		vet = new String[] { "num" };
		si.setarProducoa(vet);
		tabela.put("Fator num", si.p);

		//////////////////////////////////////////////////////////////

		// Termo 1

		si = new StackInput();
		vet = new String[] { "Termo1", "Fator", "*" };
		si.setarProducoa(vet);
		tabela.put("Termo1 *", si.p);

		si = new StackInput();
		vet = new String[] { "Termo1", "Fator", "/" };
		si.setarProducoa(vet);
		tabela.put("Termo1 /", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("Termo1 +", si.p);
		tabela.put("Termo1 -", si.p);
		tabela.put("Termo1 =", si.p);
		tabela.put("Termo1 <", si.p);
		tabela.put("Termo1 >", si.p);
		tabela.put("Termo1 !", si.p);
		tabela.put("Termo1 )", si.p);
		tabela.put("Termo1 <=", si.p);
		tabela.put("Termo1 >=", si.p);
		tabela.put("Termo1 !=", si.p);
		tabela.put("Termo1 ==", si.p);
		tabela.put("Termo1 ;", si.p);
		//////////////////////////////////////////////////////////////////////

		// Termo

		si = new StackInput();
		vet = new String[] { "Termo1", "Fator" };
		si.setarProducoa(vet);
		tabela.put("Termo +", si.p);
		tabela.put("Termo -", si.p);
		tabela.put("Termo id", si.p);
		tabela.put("Termo (", si.p);
		tabela.put("Termo num", si.p);
		//////////////////////////////////////////////////////////////////////

		// MAG1
		si = new StackInput();
		vet = new String[] { "Mag1", "Termo" };
		si.setarProducoa(vet);
		tabela.put("Mag1 -", si.p);
		tabela.put("Mag1 +", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("Mag1 =", si.p);
		tabela.put("Mag1 <", si.p);
		tabela.put("Mag1 >", si.p);
		tabela.put("Mag1 !", si.p);
		tabela.put("Mag1 )", si.p);
		tabela.put("Mag1 ;", si.p);
		tabela.put("Mag1 <=", si.p);
		tabela.put("Mag1 >=", si.p);
		tabela.put("Mag1 ==", si.p);
		tabela.put("Mag1 !=", si.p);
		////////////////////////////////////////////////////////////////////

		// MAG
		si = new StackInput();
		vet = new String[] { "Mag1", "Termo" };
		si.setarProducoa(vet);
		tabela.put("Mag +", si.p);
		tabela.put("Mag -", si.p);
		tabela.put("Mag id", si.p);
		tabela.put("Mag (", si.p);
		tabela.put("Mag num", si.p);
		//////////////////////////////////////////////////////////////////

		// Comp

		si = new StackInput();
		si.setarProducoa(">=");
		tabela.put("Comp >=", si.p);

		si = new StackInput();
		si.setarProducoa("<=");
		tabela.put("Comp <=", si.p);

		si = new StackInput();
		si.setarProducoa("!=");
		tabela.put("Comp !=", si.p);

		si = new StackInput();
		si.setarProducoa("==");
		tabela.put("Comp ==", si.p);

		si = new StackInput();
		si.setarProducoa("<");
		tabela.put("Comp <", si.p);

		si = new StackInput();
		si.setarProducoa(">");
		tabela.put("Comp >", si.p);
		/////////////////////////////////////////////////////////////////////////

		// Rvalue1

		si = new StackInput();
		vet = new String[] { "Rvalue1", "Mag", "Comp" };
		si.setarProducoa(vet);

		tabela.put("Rvalue1 >", si.p);
		tabela.put("Rvalue1 <", si.p);
		tabela.put("Rvalue1 !", si.p);
		tabela.put("Rvalue1 >=", si.p);
		tabela.put("Rvalue1 !=", si.p);
		tabela.put("Rvalue1 ==", si.p);
		tabela.put("Rvalue1 =", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("Rvalue1 )", si.p);
		tabela.put("Rvalue1 ;", si.p);
		///////////////////////////////////////////////////////////////////////////////////////////

		si = new StackInput();
		vet = new String[] { "Rvalue1", "Mag" };
		si.setarProducoa(vet);

		tabela.put("Rvalue id", si.p);
		tabela.put("Rvalue (", si.p);
		tabela.put("Rvalue -", si.p);
		tabela.put("Rvalue +", si.p);
		tabela.put("Rvalue num", si.p);
		///////////////////////////////////////////////////////////////////////////////////////
		// Expressao

		si = new StackInput();
		vet = new String[] { "Rvalue" };
		si.setarProducoa(vet);

		tabela.put("Expressao id", si.p);
		tabela.put("Expressao (", si.p);
		tabela.put("Expressao -", si.p);
		tabela.put("Expressao +", si.p);
		tabela.put("Expressao num", si.p);
		///////////////////////////////////////////////////

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("ElseOpt for", si.p);
		tabela.put("ElseOpt while", si.p);
		tabela.put("ElseOpt if", si.p);
		tabela.put("ElseOpt int", si.p);
		tabela.put("ElseOpt id", si.p);
		tabela.put("ElseOpt num", si.p);
		tabela.put("ElseOpt -", si.p);
		tabela.put("ElseOpt +", si.p);

		tabela.put("ElseOpt ;", si.p);
		tabela.put("ElseOpt {", si.p);
		tabela.put("ElseOpt }", si.p);

		si = new StackInput();
		vet = new String[] { "Stmt", "else" };
		si.setarProducoa(vet);
		tabela.put("ElseOpt else", si.p);
		/////////////////////////////////////////////////////////////////////
		/// OptExpressao

		si = new StackInput();
		si.setarProducoa("Expressao");
		tabela.put("OptExpressao id", si.p);
		tabela.put("OptExpressao (", si.p);
		tabela.put("OptExpressao -", si.p);
		tabela.put("OptExpressao +", si.p);
		tabela.put("OptExpressao num", si.p);

		si = new StackInput();
		si.setarProducoa(" ");
		tabela.put("OptExpressao )", si.p);
		tabela.put("OptExpressao ;", si.p);
		//////////////////////////////////////////////////////////////////////

	}

	public void iterar() {

		int tam = pilha.size();
		String s = "";

		for (int i = tam - 1; i >= 0; i--) {
			s += " " + pilha.get(i);
		}

		tam = entrada.size();
		String s2 = "";

		for (int i = tam - 1; i >= 0; i--) {
			s2 += " " + entrada.get(i);
		}

		System.out.println("PILHA   : " + s);
		System.out.println("ENTRADA : " + s2);

		// System.out.println();
		// System.out.println();

	}

	// retorna o que deve fazer
	public Producao action(String e, String p) {
		System.out.println("tabela  : [" + p + "][" + e + "]");
		return tabela.get(p + " " + e);
	}

	public static void main(String[] args) throws IOException {
		AnalisadorSintatico as = new AnalisadorSintatico();
		// cria a  tabela e inicia a pilha de simbolos e da entrada
		as.setarTabela();
                // classe que faz a análise léxica
		Scan s = new Scan();
		// obtem um array de tokens
		ArrayList<Token> tokens = s.obterTokens();
		// verifica se não deu erro léxico
		if (tokens != null) {


			System.out.println();
			int i;
			/* adiciona todos os tokens na pilha da entrada 
			inicia do final do array de tokens até o inicio, para que o primeiro token que foi reconhecido na
			analise léxica fique no top da pilha de entrada
			*/
			for (i = tokens.size() - 1; i >= 0; i--) {
				as.entrada.push(tokens.get(i).getAtributo());
			}

			Producao p2; // = as.action(as.entrada.peek(), as.pilha.peek());

			String saida = "";
			// enquanto as pilhas não estiverem vazias
			while (!as.pilha.isEmpty() && !as.entrada.peek().equals("$")) {
				// chama o método action e o retorno é uma produção que será colocada no lugar 
				// do simbolo atual da pilha de simbolos
				p2 = as.action(as.entrada.peek(), as.pilha.peek());
				saida = "";
				// mostra os estados das pilhas
				as.iterar();
				// verifica se o retorno foi vazio, configurando um erro, pois não foi achada entrada na
				// tabela de acordo com os as entradas passada para o método
				if (p2 == null) {
					System.out.println("\nErro Sintático");

					as.iterar();
					break;
				}

				// quando a produção retornada não é do tipo algumSimbolo -> ∈
				if (!(p2.producao.get(0).equalsIgnoreCase(" "))) {
 
					saida = as.pilha.peek() + " -> ";
					// retira o simbolo do topo da pilha
					as.pilha.pop();

				}
                                // iterar sobre a produção retornada
				for (String p : p2.producao) {
					// se for do tipo algumSimbolo -> ∈
					if (p.equals(" ")) {
						// as.interar();
						saida = as.pilha.peek() + " -> ∈ ";
                                                // apenas desempilho o tempo 
						as.pilha.pop();
				        // se não for, coloco a produção retorna na pilha de simbolos					
					} else {
						saida += " " + p;
						as.pilha.push(p);
					}
				}

				System.out.println("Saida   : " + saida + "\n");

				System.out.println();

				// compara se os topos das pilhas são iguais
				// enquanto forem , apenas desempilha os topos
				while (as.pilha.peek().equals(as.entrada.peek()) && !as.pilha.peek().equals("$")) {

					as.iterar();

					as.pilha.pop();
					as.entrada.pop();
					System.out.println();

				}

			}// fim do while

			// aceita a entrada se os topos das pilhas estiverem $
			if(as.pilha.peek().equals("$") && as.entrada.peek().equals("$")){
				System.out.println("\n\n Entrada aceita!");
				System.out.println("Pilha: " + as.pilha.peek());
				System.out.println("entrada: " + as.entrada.peek());
			}
			

		}
	}

}
