
public class Producao {
	
	private String cabeca;
	private String corpo;
	private int tamanho;
	private int numero;

	public Producao(String cabeca, String corpo, int tamanho, int numero) {
		
		this.cabeca = cabeca;
		this.corpo = corpo;
		this.tamanho = tamanho;
		this.numero = numero;
	}

	public String getCabeca() {
		return cabeca;
	}

	public void setCabeca(String cabeca) {
		this.cabeca = cabeca;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
