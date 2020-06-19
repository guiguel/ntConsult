package service;

import java.io.File;

public class VendasService {
	
	private String CAMINHO_ENTRADA = "HOMEPATH/data/in/"; 
	private String CAMINHO_SAIDA = "HOMEPATH/data/out/";
	//private String CAMINHO_ENTRADA = "C:/Users/guigu/Desktop/Java/data/in/"; 
	//private String CAMINHO_SAIDA = "C:/Users/guigu/Desktop/Java/data/out/";
	
			
	public Boolean verificaArquivo(String nomeArquivo) {
		
		String arquivo = CAMINHO_SAIDA + nomeArquivo;
		
		File file = new File(arquivo);
	
	    if(file.exists()){
	    	return true;
	    }
	    return false;
	}
		
	public String getCaminhoEntrada() {
		return CAMINHO_ENTRADA;	
	}
	
	public String getCaminhoSaida() {
		return CAMINHO_SAIDA;	
	}
}
