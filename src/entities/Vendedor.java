package entities;

public class Vendedor {
	private Long cpf;
	private String nome;
	private double salario;
	
	public Vendedor(Long cpf, String nome, double salario) {		
		this.cpf = cpf;
		this.nome =  nome;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private Long getCpf() {
		return cpf;
	}
	
	private void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	private double getSalario() {
		return salario;
	}
	
	private void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
