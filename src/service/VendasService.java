package service;

import java.io.File;

public class VendasService {
	
	private String CAMINHO = "HOMEPATH/data/out"; 
	//private String CAMINHO = "C:/Users/guigu/Desktop/Java/data/out/";
			
	public Boolean verificaArquivo(String nomeArquivo) {
		
		String arquivo = CAMINHO + nomeArquivo;
		
		File file = new File(arquivo);
	
	    if(file.exists()){
	    	return true;
	    }
	    return false;
	}

}
