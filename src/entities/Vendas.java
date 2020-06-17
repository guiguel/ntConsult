package entities;

import java.util.List;

public class Vendas {
	private int codVenda;
	private int codItem;
	private int totItem;
	private double valoritem;
	private String nome;
	
	public Vendas(int codVenda, int codItem, int totItem, double valoritem, String nome) {
		
		this.codVenda = codVenda;
		this.codItem = codItem;
		this.totItem = totItem;
		this.valoritem = valoritem;
		this.nome = nome;
	}
	private int getCodVenda() {
		return codVenda;
	}
	private void setCodVenda(int codVenda) {
		this.codVenda = codVenda;
	}
	
	private int getCodItem() {
		return codItem;
	}
	private void setCodItem(int codItem) {
		this.codItem = codItem;
	}
	
	private int getTotItem() {
		return totItem;
	}
	private void setTotItem(int totItem) {
		this.totItem = totItem;
	}
	
	private double getValoritem() {
		return valoritem;
	}
	private void setValoritem(double valoritem) {
		this.valoritem = valoritem;
	}
	private String getNome() {
		return nome;
	}
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	public int melhorVenda(List<Vendas> lista) {
		
		String nomeAtual = null;
		double valorAtual = 0, valorTotalAnt = 0;
		int codMaiorVenda = 0;
		
		for(int i=0;i<lista.size();i++){ 
			if( i == 0) {
				codMaiorVenda = lista.get(i).getCodVenda();
				nomeAtual = lista.get(i).getNome();
			}
			if(lista.get(i).getNome()==nomeAtual) {
				valorAtual += lista.get(i).getTotItem() * lista.get(i).getValoritem();
			} else {				
				if(valorTotalAnt < valorAtual) {
					valorTotalAnt = valorAtual;
					codMaiorVenda = lista.get(i-1).getCodVenda();
					valorAtual = 0;
					nomeAtual = lista.get(i).getNome();
				}
				
			}
					
		}
		return codMaiorVenda;
	}
	
	

}
