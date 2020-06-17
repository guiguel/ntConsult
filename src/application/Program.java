package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.Cliente;
import entities.Vendas;
import entities.Vendedor;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Program {

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, InterruptedException {
		
		//String sourceFileStr = "HOMEPATH/data/in";		
		File arquivos[];
		File diretorioCaminho = new File("HOMEPATH/data/in");
		
		String STRING_DELIMITER = "ç";
		String TRACE_DELIMITER = "-";
		String COMMA_DELIMITER = ",";
		
		String piorVendedor="";
		
		float maiorValor=0;
		float valorAtual=0;	
		
		List<Cliente> listCliente = new ArrayList<>();
		List<Vendedor> listVendedor = new ArrayList<>();
		List<Vendas> listVendas = new ArrayList<>();
		
		arquivos = diretorioCaminho.listFiles();
				
		WatchService watcher = FileSystems.getDefault().newWatchService();
	    //Diretório que será verificado se o arquivo foi criado
	    Path diretorio = Paths.get("HOMEPATH/data/in");
	    //registra o serviço criado
	    diretorio.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
	     
	    while (true) {
       	 
	    	 WatchKey key = watcher.take();
	         Optional<WatchEvent<?>> watchEvent= key.pollEvents().stream().findFirst();
	         
	         if (watchEvent.isPresent()) {
	        	
	             if  (watchEvent.get().kind() == StandardWatchEventKinds.OVERFLOW) {
		             continue;
	             }
	        	 
	             Path path = (Path) watchEvent.get().context();
	          
	             //arquivos = path.getFileName().toFile();
	             
	            //Verifica se o arquivo possui a extensão csv
	             /* if (FilenameUtils.getExtension(path.toString()).equalsIgnoreCase("csv")) { 
	                 System.out.println(path);
	             }*/
	         }

	         
	         boolean valid = key.reset();
	         if (!valid) {
	             break;
	         }
	         
	         arquivos = diretorioCaminho.listFiles();
	
			 for(int i = 0; i < arquivos.length; i++){
				 if (arquivos[i].getName().endsWith("csv")) {
					
					try (BufferedReader br = new BufferedReader(new FileReader(arquivos[i]))) {
						
						String line;
					    while ((line = br.readLine()) != null) {
					    	if(line.startsWith("001")) { //dados do vendedor	
					    		String[] values = line.split(STRING_DELIMITER);
															
								Long cpf = Long.parseLong(values[1]);
								String nome = values[2];
								double salario = Double.parseDouble(values[3]);
	
								listVendedor.add(new Vendedor(cpf, nome, salario));
								
					    	} else if (line.startsWith("002")) { // dados do cliente				    		
								String[] values = line.split(STRING_DELIMITER);
									
								Long cnpj = Long.parseLong(values[1]);
								String nome = values[2];
								String areaNegocio = values[3];
	
								listCliente.add(new Cliente( cnpj, nome, areaNegocio ));
								
					    	} else { //dados da venda
					    		
					    		String[] values = line.split(STRING_DELIMITER);
					    		values[2] = values[2].replaceAll("[\\p{Ps}\\p{Pe}]", "");//remover Colchetes antes do split
					    		String[] linhas = values[2].split(COMMA_DELIMITER);
					    		
					    		int codVenda =  Integer.parseInt(values[1]);
					    		String nome = values[3];
					    		piorVendedor = nome;
					    		
					    		for(int cont = 0; cont<linhas.length; cont++) {
					    			
					    			String[] vendas = linhas[cont].split(TRACE_DELIMITER);
					    			int codItem = Integer.parseInt(vendas[0]);
					    			int totItem = Integer.parseInt(vendas[1]); 
					    			double preco = Double.parseDouble(vendas[2]);
					    			/*Valor total de venda por vendedor(piorVendedor)*/
					    			valorAtual += totItem * preco;
					    			
					    			listVendas.add(new Vendas(codVenda, codItem, totItem, preco, nome));
					    		}
					    		if (valorAtual < maiorValor) {
					    			piorVendedor = nome;				    			
					    		}
					    	}
							
					    }
					    
					    String targetFileStr = diretorioCaminho + "\\out\\summary.csv";
					    try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
	
							bw.write("Total Clientes=" + listCliente.size());
							bw.newLine();
							bw.write("Total Vendedores=" + listVendedor.size());
							bw.newLine();
							
							Vendas v = new Vendas(0, 0, 0, 0, null);
							bw.write("id melhor venda=" + v.melhorVenda(listVendas));
							bw.newLine();
							bw.write("Pior Vendedor=" + piorVendedor);											
							
							System.out.println(targetFileStr + " CREATED!");						
												
						} catch (IOException e) {
							System.out.println("Error writing file: " + e.getMessage());
						}					    				    
					    				    
					}  catch (IOException e) {
						System.out.println("Error reading file: " + e.getMessage());
					}
				}			    
			}
	    }
	    watcher.close();

	}
}
