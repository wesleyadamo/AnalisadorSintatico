import java.util.ArrayList;
import java.util.Stack;

public class AnalisadorSintatico {
/*
	// indices dos tokens
	ArrayList<String> indice = new ArrayList<>();

	Stack<Integer> estado = new Stack<Integer>();
	Stack<String> simbolo = new Stack();
	Stack<Token> input = new Stack();

	// tabela sintatica
	String[][] tabela = new String[12][9];

	ArrayList<Producao> producao = new ArrayList<>();

	public String reduzir(Producao p) {

		// retira top da pilha de simbolo a produção a ser reduzida
		simbolo.pop();
		// empilha cabeça da produção a ser reduzida
		simbolo.push(p.getCabeca());

		System.out.println("reduzindo: " + p.getCorpo());
		System.out.println("Para: " + p.getCabeca());
		System.out.println();

		
		 * De acordo com o tamanho da produção, reduz a quantidade de estados da
		 * pilha de estados
		 * 
		 
		for (int i = 0; i < p.getTamanho(); i++) {
			estado.pop();
		}

		// recupera estado no top da pilha de estados
		int st = estado.peek();

		// pega o indice da tabela do simbolo no top da pilha de simbolos
		int in = indice.indexOf(simbolo.peek());

		// empilha estado de acordo com a tabela sintática
		estado.push(Integer.parseInt(tabela[st][in]));

		return "r" + p.getNumero();

	}

	public String shift(int n) {
		// empilha estado passado como parametro
		estado.push(n);
		// empilha token da entrada
		simbolo.push(input.peek().token);
		// retira token da entrada
		input.pop();

		System.out.println("shift" + n);

		return "shift" + n;

	}

	public void criarTabela() {

		// vetor com as produções da gramática
		producao.add(new Producao("E", "E+T", 3, 1));
		producao.add(new Producao("E", "T", 1, 2));
		producao.add(new Producao("T", "T*F", 3, 3));
		producao.add(new Producao("T", "F", 1, 4));
		producao.add(new Producao("F", "(E)", 3, 5));
		producao.add(new Producao("F", "id", 1, 6));

		// tabela para pegar os indices dos terminais e não-terminais
		indice.add("id");
		indice.add("+");
		indice.add("*");
		indice.add("(");
		indice.add(")");
		indice.add("$");
		indice.add("E");
		indice.add("T");
		indice.add("F");

		tabela[0][0] = "5";// shift(5); // "5";
		tabela[0][3] = "4";// shift(4); // "4";
		// action
		tabela[0][6] = "1";
		tabela[0][7] = "2";
		tabela[0][8] = "3";

		tabela[1][1] = "6";// shift(6); // "6";
		tabela[1][5] = "acc";

		tabela[2][1] = "r2";// reduzir(producao.get(2 - 1));// "r2";
		tabela[2][2] = "7";
		tabela[2][4] = "r2"; // reduzir(producao.get(2 - 1));// "r2";
		tabela[2][5] = "r2"; // reduzir(producao.get(2 - 1));// "r2";

		tabela[3][1] = "r4";// reduzir(producao.get(3)); // "r4";
		tabela[3][2] = "r4";// reduzir(producao.get(3)); // "r4";
		tabela[3][4] = "r4";// reduzir(producao.get(3)); // "r4";
		tabela[3][5] = "r4";// reduzir(producao.get(3)); // "r4";

		tabela[5][1] = "r6"; // reduzir(producao.get(5)); // "r6";
		tabela[5][2] = "r6"; // reduzir(producao.get(5)); // "r6";
		tabela[5][4] = "r6"; // reduzir(producao.get(5)); // "r6";
		tabela[5][5] = "r6"; // reduzir(producao.get(5)); // "r6";

		tabela[6][0] = "5";// shift(5); // "5";
		tabela[6][3] = "4";// shift(4); // "4";
		// action
		tabela[6][7] = "9";
		// action
		tabela[6][8] = "3";

		tabela[7][0] = "5"; // shift(5);// "5";
		tabela[7][3] = "4"; // shift(4); // "4";
		// action
		tabela[7][8] = "10";

		tabela[9][1] = "r1"; // reduzir(producao.get(0)); // "r1";
		tabela[9][2] = "7";// shift(7);// "7";
		tabela[9][4] = "r1";// reduzir(producao.get(0)); // "r1";
		tabela[9][5] = "r1"; // reduzir(producao.get(0)); // "r1";

		tabela[10][1] = "r3";// reduzir(producao.get(2)); // "r3";
		tabela[10][2] = "r3";// reduzir(producao.get(2)); // "r3";
		tabela[10][4] = "r3";// reduzir(producao.get(2)); // "r3";
		tabela[10][5] = "r3";// reduzir(producao.get(2)); // "r3";

		// tabela [0][0]
	}

	public String action(int i, int j) {

		System.out.println(i + " " + j);

		// recupera ação a ser realizada
		String ac = tabela[i][j];

		// verifica se a entrada é null
		if (ac == null) {
			return "erro";
		} else {

			// redução
			if (ac.contains("r")) {
				// chama função para reduzir passando como paramentro a produção
				// Recupera produção de acordo com o valor da tabela
				String a = reduzir(producao.get(Integer.parseInt(ac.substring(1, ac.length())) - 1));
				return a;

			} else if (ac.contains("acc")) {
				return "acc";

			} else {

				shift(Integer.parseInt(ac));
				return ac;
			}

		}
	}

	public static void main(String[] args) {
		AnalisadorSintatico an = new AnalisadorSintatico();
		an.criarTabela();

		// pega o indice que será usado para acessar a tabela
		// int indice = an.indice.indexOf(entrada);

		
		 * an.input.push(new Token("$")); an.input.push(new Token("id"));
		 * an.input.push(new Token("+")); an.input.push(new Token("id"));
		 * an.input.push(new Token("*")); an.input.push(new Token("id"));
		 

		// estado inicial
		an.estado.push(0);
		// simbolo.push("id");

		// pega qual será a acao ( reduzir, shift ou action)
		// String action = an.tabela[estado.peek()][indice];

		AnalisadorLexico al = new AnalisadorLexico();

		an.input.push(al.nextToken());

		while (!an.input.isEmpty()) {

			// recupera estado inicial
			int st = an.estado.peek();

			System.out.println("Token analisado: " + an.input.peek().token);

			// recupera o indice do token na tabela
			int in = an.indice.indexOf(an.input.peek().token);

			// chamada de método para realizar a ação passando linha e coluna da
			// tabela
			String result = an.action(st, in);

			// verifica se a tabela foi aceita
			if (result.equals("acc")) {
				System.out.println("ACEITADO");
				an.input.pop();
				System.out.println(an.input.size());
			} else if (result.equals("erro")) {
				System.out.println("HOUVE UM ERRO");
				break;
				// se houve um shift, obtem o próximo token
				// se foi uma redução, continua com o mesmo token na entrada
			} else if (!(result.contains("r"))) {
				an.input.push(al.nextToken());

			}

		}

	}*/

}
