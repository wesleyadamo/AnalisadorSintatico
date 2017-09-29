package compiladoresAS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AnalisadorSintatico {

	Map<String, Producao02> tabela = new HashMap<String, Producao02>();

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
		vet = new String[] { "CompStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt {", si.p);

		si = new StackInput();
		vet = new String[] { "ForStmt" };
		si.setarProducoa(vet);
		tabela.put("Stmt for", si.p);

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
		vet = new String[] { "Stmt",")", "Atribuicao",";", "OptExpressao",";", "Atribuicao","(", "for" };
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

	public void interar() {

		int tam = pilha.size();
		String s = "";

		for (int i = 0; i < tam; i++) {
			s += " "+pilha.get(i);
		}
		
		tam = entrada.size();
		String s2 = "";

		for (int i = 0; i < tam; i++) {
			s2 += " "+entrada.get(i);
		}
		

		System.out.println("ESTADO DA PILHA: " + s);
		System.out.println("ESTADO DA entrada: " + s2);

		System.out.println();
		System.out.println();

	}

	// retorna o que deve fazer
	public Producao02 action(String e, String p) {
		System.out.println("COMPARAR: " + p + " " + e);
		return tabela.get(p + " " + e);
	}

	public static void main(String[] args) {
		AnalisadorSintatico as = new AnalisadorSintatico();
		
		
		
		
		
		
		
		
		as.setarTabela();
		as.entrada.push("}");
		as.entrada.push("}");
		as.entrada.push("}");
		
		as.entrada.push("}");
		as.entrada.push(";");
		
		as.entrada.push("num");
		as.entrada.push("+");
		as.entrada.push("id");
		
		as.entrada.push("=");
		as.entrada.push("id");
		as.entrada.push("{");
		
		

		as.entrada.push(")");

		as.entrada.push("num");

		as.entrada.push("!=");
		as.entrada.push("id");
		as.entrada.push("(");
		
		as.entrada.push("while");

		as.entrada.push("{");
		as.entrada.push(")");

		as.entrada.push("num");
		as.entrada.push("+");
		
		
		as.entrada.push("id");
		as.entrada.push("=");
		as.entrada.push("id");
		
		as.entrada.push(";");
		as.entrada.push("num");
		as.entrada.push("<");
		
		as.entrada.push("id");
		as.entrada.push(";");
		as.entrada.push("num");
		as.entrada.push("=");
		as.entrada.push("id");
		as.entrada.push("(");
		
		
		as.entrada.push("for");
		as.entrada.push("{");
		as.entrada.push("else");
		as.entrada.push("}");
		as.entrada.push(";");
		as.entrada.push("num");
		as.entrada.push("+");
		as.entrada.push("num");
		as.entrada.push("=");
		as.entrada.push("id");
as.entrada.push("{");
		as.entrada.push(")");
		as.entrada.push("num");
		as.entrada.push(">=");
		as.entrada.push("id");
		as.entrada.push("(");

		as.entrada.push("if");
		as.entrada.push("id");as.entrada.push("id");
		as.entrada.push("{");
		as.entrada.push(")");
		as.entrada.push("(");
		as.entrada.push("id");
		as.entrada.push("int");

		Producao02 p2 = as.action(as.entrada.peek(), as.pilha.peek());

		// retira da pilha
		as.pilha.pop();

		// empilha a nova producao
		for (String p : p2.producao) {
			as.pilha.push(p);
		}

		as.interar();
		// testa se as entrada são iguais
		while (!as.pilha.isEmpty() && !as.entrada.peek().equals("$")) {
			p2 = as.action(as.entrada.peek(), as.pilha.peek());

			as.interar();
			System.out.println("PILHA NO TOPO ANTES DE REMOVER: " + as.pilha.peek()+""+as.entrada.peek());

			if (!(p2.producao.get(0).equalsIgnoreCase(" "))){
				System.out.println("aqui pra desempilhar");
				System.out.println("Retorno: "+p2.producao.get(0));
				as.pilha.pop();

			}
			System.out.println("PILHA NO TOPO DEPOIS DE REMOVER: " + as.pilha.peek());

			as.interar();

			for (String p : p2.producao) {
				if (p.equals(" ")) {
					as.interar();
					as.pilha.pop();
					System.out.println("DESIMPILHAR " + as.entrada.peek());
					//as.entrada.pop();
					as.interar();

				} else{
					as.pilha.push(p);
				}
			}

			// System.out.println("PILHA TOPO" + as.pilha.peek());
			// System.out.println("entrada TOPO" + as.entrada.peek());

			// compara se as entrada são iguais
			while (as.pilha.peek().equals(as.entrada.peek()) && !as.pilha.peek().equals("$")) {

				as.interar();
				System.out.println("entrou no igual");
				System.out.println("PILHA TOPO: " + as.pilha.peek());
				System.out.println("entrada TOPO: " + as.entrada.peek());
				System.out.println();
				as.pilha.pop();
				as.entrada.pop();

			}

		}

		System.out.println("PILHA NO TOPO FINAL: " + as.pilha.peek());
		System.out.println("entrada: " + as.entrada.peek());

	}

}
