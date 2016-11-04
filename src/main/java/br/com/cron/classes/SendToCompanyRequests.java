package br.com.cron.classes;

public class SendToCompanyRequests {
	private String pedido;
	private String qtd;
	private String tipo;
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public String getQtd() {
		return qtd;
	}
	public void setQtd(String qtd) {
		this.qtd = qtd;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public SendToCompanyRequests(String pedido, String qtd, String tipo) {
		this.pedido = pedido;
		this.qtd = qtd;
		this.tipo = tipo;
	}
	public SendToCompanyRequests(){
		
	}

}
