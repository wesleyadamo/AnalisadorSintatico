package compiladoresAS;

public class StackInput {

	String entrada;
	String pilha;
	Producao02 p = new Producao02();

	public void setarProducoa(String[] pr) {
		for (String p1 : pr)
			p.producao.add(p1);
	}
	
	public void setarProducoa(String pr) {
		
			p.producao.add(pr);
	}

}
