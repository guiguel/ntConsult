package entities;

public class Cliente {
	
	private Long cnpj;
	private String areaNegocio;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private Long getCnpj() {
		return cnpj;
	}
	private void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}
	private String getAreaNegocio() {
		return areaNegocio;
	}
	private void setAreaNegocio(String areaNegocio) {
		this.areaNegocio = areaNegocio;
	}
	
	public Cliente( Long cnpj, String nome, String areaNegocio ) {
		this.cnpj = cnpj;
		this.areaNegocio = areaNegocio;
		this.nome = nome;		
	}
}
	