package br.com.cron.resources;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@Table(name="pedido")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"", unique=true, nullable=false)
	private Long id;

	@Column(name="\"BairroPrincipal\"", length=255)
	private String bairroPrincipal;

	@Column(name="\"CEPPrincipal\"", length=255)
	private String CEPPrincipal;

	@Column(name="\"CidadePrincipal\"", length=255)
	private String cidadePrincipal;

	@Column(name="\"ComplementoPrincipal\"", length=255)
	private String complementoPrincipal;

	@Column(name="\"EnderecoPrincipal\"", length=500)
	private String enderecoPrincipal;

	@Column(name="\"NomRazaoSocial\"", length=500)
	private String nomRazaoSocial;

	@Column(name="\"NumeroPrincipal\"", length=255)
	private String numeroPrincipal;

	@Column(name="\"PaisPrincipalId\"", length=255)
	private String paisPrincipalId;

	@Column(name="\"ProdutoId\"")
	private Long produtoId;

	@Column(name="\"Quantidade\"")
	private Long quantidade;

	@Column(name="\"RepresentanteId\"", length=255)
	private String representanteId;

	@Column(name="\"TipoContratoId\"", length=255)
	private String tipoContratoId;

	@Column(name="\"UFPrincipalId\"", length=255)
	private String UFPrincipalId;

	@Column(name="\"x1_ContatoTecnicoId\"", length=255)
	private String x1_ContatoTecnicoId;

	@Column(name="\"x1_Documento\"", length=500)
	private String x1_Documento;

	@Column(name="\"x1_Dominio\"", length=255)
	private String x1_Dominio;

	@Column(name="\"x1_EmailTecnico\"", length=255)
	private String x1_EmailTecnico;

	@Column(name="\"x1_GerenteProjetoEmail\"", length=255)
	private String x1_GerenteProjetoEmail;

	@Column(name="\"x1_GerenteProjetoId\"", length=255)
	private String x1_GerenteProjetoId;

	@Column(name="\"x1_GerenteProjetoTelefone\"", length=255)
	private String x1_GerenteProjetoTelefone;

	@Column(name="\"x1_NivelAcompanhamento\"", length=255)
	private String x1_NivelAcompanhamento;

	@Column(name="\"x1_TelefoneTecnico\"", length=255)
	private String x1_TelefoneTecnico;

	//bi-directional many-to-one association to StatusPedido
	@ManyToOne
	@JoinColumn(name="id_status")
	private StatusPedido statusPedido;

	public Pedido() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairroPrincipal() {
		return this.bairroPrincipal;
	}

	public void setBairroPrincipal(String bairroPrincipal) {
		this.bairroPrincipal = bairroPrincipal;
	}

	public String getCEPPrincipal() {
		return this.CEPPrincipal;
	}

	public void setCEPPrincipal(String CEPPrincipal) {
		this.CEPPrincipal = CEPPrincipal;
	}

	public String getCidadePrincipal() {
		return this.cidadePrincipal;
	}

	public void setCidadePrincipal(String cidadePrincipal) {
		this.cidadePrincipal = cidadePrincipal;
	}

	public String getComplementoPrincipal() {
		return this.complementoPrincipal;
	}

	public void setComplementoPrincipal(String complementoPrincipal) {
		this.complementoPrincipal = complementoPrincipal;
	}

	public String getEnderecoPrincipal() {
		return this.enderecoPrincipal;
	}

	public void setEnderecoPrincipal(String enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public String getNomRazaoSocial() {
		return this.nomRazaoSocial;
	}

	public void setNomRazaoSocial(String nomRazaoSocial) {
		this.nomRazaoSocial = nomRazaoSocial;
	}

	public String getNumeroPrincipal() {
		return this.numeroPrincipal;
	}

	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	public String getPaisPrincipalId() {
		return this.paisPrincipalId;
	}

	public void setPaisPrincipalId(String paisPrincipalId) {
		this.paisPrincipalId = paisPrincipalId;
	}

	public Long getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public String getRepresentanteId() {
		return this.representanteId;
	}

	public void setRepresentanteId(String representanteId) {
		this.representanteId = representanteId;
	}

	public String getTipoContratoId() {
		return this.tipoContratoId;
	}

	public void setTipoContratoId(String tipoContratoId) {
		this.tipoContratoId = tipoContratoId;
	}

	public String getUFPrincipalId() {
		return this.UFPrincipalId;
	}

	public void setUFPrincipalId(String UFPrincipalId) {
		this.UFPrincipalId = UFPrincipalId;
	}

	public String getX1_ContatoTecnicoId() {
		return this.x1_ContatoTecnicoId;
	}

	public void setX1_ContatoTecnicoId(String x1_ContatoTecnicoId) {
		this.x1_ContatoTecnicoId = x1_ContatoTecnicoId;
	}

	public String getX1_Documento() {
		return this.x1_Documento;
	}

	public void setX1_Documento(String x1_Documento) {
		this.x1_Documento = x1_Documento;
	}

	public String getX1_Dominio() {
		return this.x1_Dominio;
	}

	public void setX1_Dominio(String x1_Dominio) {
		this.x1_Dominio = x1_Dominio;
	}

	public String getX1_EmailTecnico() {
		return this.x1_EmailTecnico;
	}

	public void setX1_EmailTecnico(String x1_EmailTecnico) {
		this.x1_EmailTecnico = x1_EmailTecnico;
	}

	public String getX1_GerenteProjetoEmail() {
		return this.x1_GerenteProjetoEmail;
	}

	public void setX1_GerenteProjetoEmail(String x1_GerenteProjetoEmail) {
		this.x1_GerenteProjetoEmail = x1_GerenteProjetoEmail;
	}

	public String getX1_GerenteProjetoId() {
		return this.x1_GerenteProjetoId;
	}

	public void setX1_GerenteProjetoId(String x1_GerenteProjetoId) {
		this.x1_GerenteProjetoId = x1_GerenteProjetoId;
	}

	public String getX1_GerenteProjetoTelefone() {
		return this.x1_GerenteProjetoTelefone;
	}

	public void setX1_GerenteProjetoTelefone(String x1_GerenteProjetoTelefone) {
		this.x1_GerenteProjetoTelefone = x1_GerenteProjetoTelefone;
	}

	public String getX1_NivelAcompanhamento() {
		return this.x1_NivelAcompanhamento;
	}

	public void setX1_NivelAcompanhamento(String x1_NivelAcompanhamento) {
		this.x1_NivelAcompanhamento = x1_NivelAcompanhamento;
	}

	public String getX1_TelefoneTecnico() {
		return this.x1_TelefoneTecnico;
	}

	public void setX1_TelefoneTecnico(String x1_TelefoneTecnico) {
		this.x1_TelefoneTecnico = x1_TelefoneTecnico;
	}

	public StatusPedido getStatusPedido() {
		return this.statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

}