package br.com.cron.plune.DTO;
/*
 * Class by Gabriel Panza 08/11
 */
public class SendToCompanyRequestsDTO {
	private String pedido;
	private String qtd;
	private String tipo;
	private String motivoFechamento;
	private String clientId;
	private String OpportunityID;
	private String Observação;
	
	public String getMotivoFechamento() {
		return motivoFechamento;
	}
	public void setMotivoFechamento(String motivoFechamento) {
		this.motivoFechamento = motivoFechamento;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getOpportunityID() {
		return OpportunityID;
	}
	public void setOpportunityID(String opportunityID) {
		OpportunityID = opportunityID;
	}
	public String getObservação() {
		return Observação;
	}
	public void setObservação(String observação) {
		Observação = observação;
	}
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
	
	public SendToCompanyRequestsDTO(){
		
	}
	public SendToCompanyRequestsDTO(String pedido, String qtd, String tipo, String motivoFechamento, String clientId,
			String opportunityID, String observação) {
		this.pedido = pedido;
		this.qtd = qtd;
		this.tipo = tipo;
		this.motivoFechamento = motivoFechamento;
		this.clientId = clientId;
		OpportunityID = opportunityID;
		Observação = observação;
	}

}
