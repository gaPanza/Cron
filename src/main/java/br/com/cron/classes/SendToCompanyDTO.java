package br.com.cron.classes;

import java.util.ArrayList;

public class SendToCompanyDTO {
	
	private String nameCompany;
	private String nameUser;
	private String dominio;
	private String contatoTecnico;
	private String emailContatoTecnico;
	private String telefoneContatoTecnico;
	private String contatoGerencial;
	private String emailContatoGerencial;
	private String telefoneContatoGerencial;
	private String linkPropostaComercial;
	private String endereco;
	private String cep;
	private String estado;
	private String cidade;
	private String pedidos;
	private String statusId;
	private ArrayList<SendToCompanyRequests> pedido;
	
	public ArrayList<SendToCompanyRequests> getPedido() {
		return pedido;
	}
	public void setPedido(ArrayList<SendToCompanyRequests> pedido) {
		this.pedido = pedido;
	}
	private String tipoPedido;
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getContatoTecnico() {
		return contatoTecnico;
	}
	public void setContatoTecnico(String contatoTecnico) {
		this.contatoTecnico = contatoTecnico;
	}
	public String getEmailContatoTecnico() {
		return emailContatoTecnico;
	}
	public void setEmailContatoTecnico(String emailContatoTecnico) {
		this.emailContatoTecnico = emailContatoTecnico;
	}
	public String getTelefoneContatoTecnico() {
		return telefoneContatoTecnico;
	}
	public void setTelefoneContatoTecnico(String telefoneContatoTecnico) {
		this.telefoneContatoTecnico = telefoneContatoTecnico;
	}
	public String getContatoGerencial() {
		return contatoGerencial;
	}
	public void setContatoGerencial(String contatoGerencial) {
		this.contatoGerencial = contatoGerencial;
	}
	public String getEmailContatoGerencial() {
		return emailContatoGerencial;
	}
	public void setEmailContatoGerencial(String emailContatoGerencial) {
		this.emailContatoGerencial = emailContatoGerencial;
	}
	public String getTelefoneContatoGerencial() {
		return telefoneContatoGerencial;
	}
	public void setTelefoneContatoGerencial(String telefoneContatoGerencial) {
		this.telefoneContatoGerencial = telefoneContatoGerencial;
	}
	public String getLinkPropostaComercial() {
		return linkPropostaComercial;
	}
	public void setLinkPropostaComercial(String linkPropostaComercial) {
		this.linkPropostaComercial = linkPropostaComercial;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPedidos() {
		return pedidos;
	}
	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}

	
	
	
	
}