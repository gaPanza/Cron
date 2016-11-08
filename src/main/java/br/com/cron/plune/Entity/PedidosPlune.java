package br.com.cron.plune.Entity;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.Serializable;
import javax.persistence.*;

@SqlResultSetMapping(name="PendingStatus",
entities={
    @EntityResult(entityClass=br.com.cron.plune.Entity.PedidosPlune.class)
})
@Entity
@Table(name="PedidosPlune")
@NamedQuery(name="PedidosPlune.findAll", query="SELECT p FROM PedidosPlune p")
public class PedidosPlune implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true, nullable=false)
	private Long id;
	
	@Column(name="x1_EmailValue", length=500)
	private String x1_EmailValue;
	
	@Column(name="x1_EmailResolved", length=500)
	private String x1_EmailResolved;
	
	@Column(name="_32fc7c16OportunidadeIdValue", length=500)
	private String _32fc7c16OportunidadeIdValue;
	
	@Column(name="_32fc7c16OportunidadeIdResolved", length=500)
	private String _32fc7c16OportunidadeIdResolved;
	
	@Column(name="_32fc7c16MotivoFechamentoIdValue", length=500)
	private String _32fc7c16MotivoFechamentoIdValue;

	@Column(name="_32fc7c16MotivoFechamentoIdResolved", length=500)
	private String _32fc7c16MotivoFechamentoIdResolved;
	
	@Column(name="x1_ObservacaoValue", length=5000)
	private String x1_ObservacaoValue;
	
	@Column(name="x1_ObservacaoResolved", length=5000)
	private String x1_ObservacaoResolved;
	
	@Column(name="x1_OpportunidIDValue", length=500)
	private String x1_OpportunidIDValue;
	
	@Column(name="x1_OpportunidIDResolved", length=500)
	private String x1_OpportunidIDResolved;
	
	@Column(name="x1_ClientIDValue", length=255)
	private String x1_ClientIDValue;
	
	@Column(name="x1_ClientIDResolved", length=255)
	private String x1_ClientIDResolved;
	
	@Column(name="_g_canDelete", length=255)
	private String _g_canDelete;

	@Column(name="_g_canUpdateResolved", length=255)
	private String _g_canUpdateResolved;

	@Column(name="_32fc7c16BranchIdResolved", length=255)
	private String _2fc7c16_BranchIdResolved;

	@Column(name="_32fc7c16BranchIdValue", length=255)
	private String _2fc7c16_BranchIdValue;

	@Column(name="_632fc7c16CompanyIdResolved", length=255)
	private String _32fc7c16_CompanyIdResolved;

	@Column(name="_632fc7c16CompanyIdValue", length=255)
	private String _32fc7c16_CompanyIdValue;

	@Column(name="_632fc7c16IdResolved", length=255)
	private String _32fc7c16_IdResolved;

	@Column(name="_632fc7c16IdValue", length=255)
	private String _32fc7c16_IdValue;

	@Column(name="bairroPrincipalResolved", length=255)
	private String bairroPrincipalResolved;

	@Column(name="bairroPrincipalValue", length=255)
	private String bairroPrincipalValue;

	@Column(name="branchIdResolved", length=255)
	private String branchIdResolved;

	@Column(name="branchIdValue", length=255)
	private String branchIdValue;

	@Column(name="cepPrincipalResolved", length=255)
	private String cepPrincipalResolved;

	@Column(name="cepPrincipalValue", length=255)
	private String cepPrincipalValue;

	@Column(name="cidadePrincipalIdResolved", length=255)
	private String cidadePrincipalIdResolved;

	@Column(name="cidadePrincipalIdValue", length=255)
	private String cidadePrincipalIdValue;

	@Column(name="clienteIdResolved", length=255)
	private String clienteIdResolved;

	@Column(name="clienteIdValue", length=255)
	private String clienteIdValue;

	@Column(name="companyIdResolved", length=255)
	private String companyIdResolved;

	@Column(name="companyIdValue", length=255)
	private String companyIdValue;

	@Column(name="complementoPrincipalResolved", length=255)
	private String complementoPrincipalResolved;

	@Column(name="complementoPrincipalValue", length=255)
	private String complementoPrincipalValue;

	@Column(name="descricaoResolved", length=255)
	private String descricaoResolved;

	@Column(name="descricaoValue", length=255)
	private String descricaoValue;

	@Column(name="empresaIdResolved", length=255)
	private String empresaIdResolved;

	@Column(name="empresaIdValue", length=255)
	private String empresaIdValue;

	@Column(name="enderecoPrincipalResolved", length=255)
	private String enderecoPrincipalResolved;

	@Column(name="enderecoPrincipalValue", length=255)
	private String enderecoPrincipalValue;

	@Column(name="IdResolved", length=255)
	private String idResolved;

	@Column(name="IdValue", length=255)
	private String idValue;

	@Column(name="nomRazaoSocialResolved", length=255)
	private String nomRazaoSocialResolved;

	@Column(name="nomRazaoSocialValue", length=255)
	private String nomRazaoSocialValue;

	@Column(name="numeroPrincipalResolved", length=255)
	private String numeroPrincipalResolved;

	@Column(name="numeroPrincipalValue", length=255)
	private String numeroPrincipalValue;

	@Column(name="paisPrincipalIdResolved", length=255)
	private String paisPrincipalIdResolved;

	@Column(name="paisPrincipalIdValue", length=255)
	private String paisPrincipalIdValue;

	@Column(name="parceiroIdResolved", length=255)
	private String parceiroIdResolved;

	@Column(name="parceiroIdValue", length=255)
	private String parceiroIdValue;

	@Column(name="pedidoIdResolved", length=255)
	private String pedidoIdResolved;

	@Column(name="pedidoIdValue", length=255)
	private String pedidoIdValue;

	@Column(name="produtoIdResolved", length=255)
	private String produtoIdResolved;

	@Column(name="produtoIdValue", length=255)
	private String produtoIdValue;

	@Column(name="QuantidadeResolved", length=255)
	private String quantidadeResolved;

	@Column(name="QuantidadeValue", length=255)
	private String quantidadeValue;

	@Column(name="representanteIdResolved", length=255)
	private String representanteIdResolved;

	@Column(name="representanteIdValue", length=255)
	private String representanteIdValue;

	@Column(name="statusPedidoResolved", length=255)
	private String statusPedidoResolved;

	@Column(name="statusPedidoValue", length=255)
	private String statusPedidoValue;

	@Column(name="tipoContratoIdResolved", length=255)
	private String tipoContratoIdResolved;

	@Column(name="tipoContratoIdValue", length=255)
	private String tipoContratoIdValue;

	@Column(name="ufPrincipalIdResolved", length=255)
	private String ufPrincipalIdResolved;

	@Column(name="ufPrincipalIdValue", length=255)
	private String ufPrincipalIdValue;

	@Column(name="x1_ContatoTecnicoIdResolved", length=255)
	private String x1_ContatoTecnicoIdResolved;

	@Column(name="x1_DocumentoResolved", length=255)
	private String x1_DocumentoResolved;

	@Column(name="x1_DocumentoValue", length=255)
	private String x1_DocumentoValue;

	@Column(name="x1_DominioResolved", length=255)
	private String x1_DominioResolved;

	@Column(name="x1_DominioValue", length=255)
	private String x1_DominioValue;

	@Column(name="x1_EmailTecnicoResolved", length=255)
	private String x1_EmailTecnicoResolved;

	@Column(name="x1_EmailTecnicoValue", length=255)
	private String x1_EmailTecnicoValue;

	@Column(name="x1_GerenteProjetoEmailResolved", length=255)
	private String x1_GerenteProjetoEmailResolved;

	@Column(name="x1_GerenteProjetoEmailValue", length=255)
	private String x1_GerenteProjetoEmailValue;

	@Column(name="x1_GerenteProjetoIdResolved", length=255)
	private String x1_GerenteProjetoIdResolved;

	@Column(name="x1_GerenteProjetoTelefoneResolved", length=255)
	private String x1_GerenteProjetoTelefoneResolved;

	@Column(name="x1_GerenteProjetoTelefoneValue", length=255)
	private String x1_GerenteProjetoTelefoneValue;

	@Column(name="x1_NivelAcompanhamentoResolved", length=255)
	private String x1_NivelAcompanhamentoResolved;

	@Column(name="x1_NivelAcompanhamentoValue", length=255)
	private String x1_NivelAcompanhamentoValue;

	@Column(name="x1_TelefoneTecnicoResolved", length=255)
	private String x1_TelefoneTecnicoResolved;

	@Column(name="x1_TelefoneTecnicoValue", length=255)
	private String x1_TelefoneTecnicoValue;

	@Column(name="x991_IdResolved", length=255)
	private String x991_IdResolved;

	public String getSendingStatus() {
		return SendingStatus;
	}

	public void setSendingStatus(String sendingStatus) {
		SendingStatus = sendingStatus;
	}

	@Column(name="x991_IdValue", length=255)
	private String x991_IdValue;
	
	@Column(name="SendingStatus", length=255)
	private String SendingStatus;
	
	public PedidosPlune() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_g_canDelete() {
		return this._g_canDelete;
	}

	public void set_g_canDelete(String _g_canDelete) {
		this._g_canDelete = _g_canDelete;
	}

	public String get_g_canUpdateResolved() {
		return this._g_canUpdateResolved;
	}

	public void set_g_canUpdateResolved(String _g_canUpdateResolved) {
		this._g_canUpdateResolved = _g_canUpdateResolved;
	}

	public String get_2fc7c16_BranchIdResolved() {
		return this._2fc7c16_BranchIdResolved;
	}

	public void set_2fc7c16_BranchIdResolved(String _2fc7c16_BranchIdResolved) {
		this._2fc7c16_BranchIdResolved = _2fc7c16_BranchIdResolved;
	}

	public String get_2fc7c16_BranchIdValue() {
		return this._2fc7c16_BranchIdValue;
	}

	public void set_2fc7c16_BranchIdValue(String _2fc7c16_BranchIdValue) {
		this._2fc7c16_BranchIdValue = _2fc7c16_BranchIdValue;
	}

	public String get_32fc7c16_CompanyIdResolved() {
		return this._32fc7c16_CompanyIdResolved;
	}

	public void set_32fc7c16_CompanyIdResolved(String _32fc7c16_CompanyIdResolved) {
		this._32fc7c16_CompanyIdResolved = _32fc7c16_CompanyIdResolved;
	}

	public String get_32fc7c16_CompanyIdValue() {
		return this._32fc7c16_CompanyIdValue;
	}

	public void set_32fc7c16_CompanyIdValue(String _32fc7c16_CompanyIdValue) {
		this._32fc7c16_CompanyIdValue = _32fc7c16_CompanyIdValue;
	}

	public String get_32fc7c16_IdResolved() {
		return this._32fc7c16_IdResolved;
	}

	public void set_32fc7c16_IdResolved(String _32fc7c16_IdResolved) {
		this._32fc7c16_IdResolved = _32fc7c16_IdResolved;
	}

	public String get_32fc7c16_IdValue() {
		return this._32fc7c16_IdValue;
	}

	public void set_32fc7c16_IdValue(String _32fc7c16_IdValue) {
		this._32fc7c16_IdValue = _32fc7c16_IdValue;
	}

	public String getBairroPrincipalResolved() {
		return this.bairroPrincipalResolved;
	}

	public void setBairroPrincipalResolved(String bairroPrincipalResolved) {
		this.bairroPrincipalResolved = bairroPrincipalResolved;
	}

	public String getBairroPrincipalValue() {
		return this.bairroPrincipalValue;
	}

	public void setBairroPrincipalValue(String bairroPrincipalValue) {
		this.bairroPrincipalValue = bairroPrincipalValue;
	}

	public String getBranchIdResolved() {
		return this.branchIdResolved;
	}

	public void setBranchIdResolved(String branchIdResolved) {
		this.branchIdResolved = branchIdResolved;
	}

	public String getBranchIdValue() {
		return this.branchIdValue;
	}

	public void setBranchIdValue(String branchIdValue) {
		this.branchIdValue = branchIdValue;
	}

	public String getCepPrincipalResolved() {
		return this.cepPrincipalResolved;
	}

	public void setCepPrincipalResolved(String cepPrincipalResolved) {
		this.cepPrincipalResolved = cepPrincipalResolved;
	}

	public String getCepPrincipalValue() {
		return this.cepPrincipalValue;
	}

	public void setCepPrincipalValue(String cepPrincipalValue) {
		this.cepPrincipalValue = cepPrincipalValue;
	}

	public String getCidadePrincipalIdResolved() {
		return this.cidadePrincipalIdResolved;
	}

	public void setCidadePrincipalIdResolved(String cidadePrincipalIdResolved) {
		this.cidadePrincipalIdResolved = cidadePrincipalIdResolved;
	}

	public String getCidadePrincipalIdValue() {
		return this.cidadePrincipalIdValue;
	}

	public void setCidadePrincipalIdValue(String cidadePrincipalIdValue) {
		this.cidadePrincipalIdValue = cidadePrincipalIdValue;
	}

	public String getClienteIdResolved() {
		return this.clienteIdResolved;
	}

	public void setClienteIdResolved(String clienteIdResolved) {
		this.clienteIdResolved = clienteIdResolved;
	}

	public String getClienteIdValue() {
		return this.clienteIdValue;
	}

	public void setClienteIdValue(String clienteIdValue) {
		this.clienteIdValue = clienteIdValue;
	}

	public String getCompanyIdResolved() {
		return this.companyIdResolved;
	}

	public void setCompanyIdResolved(String companyIdResolved) {
		this.companyIdResolved = companyIdResolved;
	}

	public String getCompanyIdValue() {
		return this.companyIdValue;
	}

	public void setCompanyIdValue(String companyIdValue) {
		this.companyIdValue = companyIdValue;
	}

	public String getComplementoPrincipalResolved() {
		return this.complementoPrincipalResolved;
	}

	public void setComplementoPrincipalResolved(String complementoPrincipalResolved) {
		this.complementoPrincipalResolved = complementoPrincipalResolved;
	}

	public String getComplementoPrincipalValue() {
		return this.complementoPrincipalValue;
	}

	public void setComplementoPrincipalValue(String complementoPrincipalValue) {
		this.complementoPrincipalValue = complementoPrincipalValue;
	}

	public String getDescricaoResolved() {
		return this.descricaoResolved;
	}

	public void setDescricaoResolved(String descricaoResolved) {
		this.descricaoResolved = descricaoResolved;
	}

	public String getDescricaoValue() {
		return this.descricaoValue;
	}

	public void setDescricaoValue(String descricaoValue) {
		this.descricaoValue = descricaoValue;
	}

	public String getEmpresaIdResolved() {
		return this.empresaIdResolved;
	}

	public void setEmpresaIdResolved(String empresaIdResolved) {
		this.empresaIdResolved = empresaIdResolved;
	}

	public String getEmpresaIdValue() {
		return this.empresaIdValue;
	}

	public void setEmpresaIdValue(String empresaIdValue) {
		this.empresaIdValue = empresaIdValue;
	}

	public String getEnderecoPrincipalResolved() {
		return this.enderecoPrincipalResolved;
	}

	public void setEnderecoPrincipalResolved(String enderecoPrincipalResolved) {
		this.enderecoPrincipalResolved = enderecoPrincipalResolved;
	}

	public String getEnderecoPrincipalValue() {
		return this.enderecoPrincipalValue;
	}

	public void setEnderecoPrincipalValue(String enderecoPrincipalValue) {
		this.enderecoPrincipalValue = enderecoPrincipalValue;
	}

	public String getIdResolved() {
		return this.idResolved;
	}

	public void setIdResolved(String idResolved) {
		this.idResolved = idResolved;
	}

	public String getIdValue() {
		return this.idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getNomRazaoSocialResolved() {
		return this.nomRazaoSocialResolved;
	}

	public void setNomRazaoSocialResolved(String nomRazaoSocialResolved) {
		this.nomRazaoSocialResolved = nomRazaoSocialResolved;
	}

	public String getNomRazaoSocialValue() {
		return this.nomRazaoSocialValue;
	}

	public void setNomRazaoSocialValue(String nomRazaoSocialValue) {
		this.nomRazaoSocialValue = nomRazaoSocialValue;
	}

	public String getNumeroPrincipalResolved() {
		return this.numeroPrincipalResolved;
	}

	public void setNumeroPrincipalResolved(String numeroPrincipalResolved) {
		this.numeroPrincipalResolved = numeroPrincipalResolved;
	}

	public String getNumeroPrincipalValue() {
		return this.numeroPrincipalValue;
	}

	public void setNumeroPrincipalValue(String numeroPrincipalValue) {
		this.numeroPrincipalValue = numeroPrincipalValue;
	}

	public String getPaisPrincipalIdResolved() {
		return this.paisPrincipalIdResolved;
	}

	public void setPaisPrincipalIdResolved(String paisPrincipalIdResolved) {
		this.paisPrincipalIdResolved = paisPrincipalIdResolved;
	}

	public String getPaisPrincipalIdValue() {
		return this.paisPrincipalIdValue;
	}

	public void setPaisPrincipalIdValue(String paisPrincipalIdValue) {
		this.paisPrincipalIdValue = paisPrincipalIdValue;
	}

	public String getParceiroIdResolved() {
		return this.parceiroIdResolved;
	}

	public void setParceiroIdResolved(String parceiroIdResolved) {
		this.parceiroIdResolved = parceiroIdResolved;
	}

	public String getParceiroIdValue() {
		return this.parceiroIdValue;
	}

	public void setParceiroIdValue(String parceiroIdValue) {
		this.parceiroIdValue = parceiroIdValue;
	}

	public String getPedidoIdResolved() {
		return this.pedidoIdResolved;
	}

	public void setPedidoIdResolved(String pedidoIdResolved) {
		this.pedidoIdResolved = pedidoIdResolved;
	}

	public String getPedidoIdValue() {
		return this.pedidoIdValue;
	}

	public void setPedidoIdValue(String pedidoIdValue) {
		this.pedidoIdValue = pedidoIdValue;
	}

	public String getProdutoIdResolved() {
		return this.produtoIdResolved;
	}

	public void setProdutoIdResolved(String produtoIdResolved) {
		this.produtoIdResolved = produtoIdResolved;
	}

	public String getProdutoIdValue() {
		return this.produtoIdValue;
	}

	public void setProdutoIdValue(String produtoIdValue) {
		this.produtoIdValue = produtoIdValue;
	}

	public String getQuantidadeResolved() {
		return this.quantidadeResolved;
	}

	public void setQuantidadeResolved(String quantidadeResolved) {
		this.quantidadeResolved = quantidadeResolved;
	}

	public String getQuantidadeValue() {
		return this.quantidadeValue;
	}

	public void setQuantidadeValue(String quantidadeValue) {
		this.quantidadeValue = quantidadeValue;
	}

	public String getRepresentanteIdResolved() {
		return this.representanteIdResolved;
	}

	public void setRepresentanteIdResolved(String representanteIdResolved) {
		this.representanteIdResolved = representanteIdResolved;
	}

	public String getRepresentanteIdValue() {
		return this.representanteIdValue;
	}

	public void setRepresentanteIdValue(String representanteIdValue) {
		this.representanteIdValue = representanteIdValue;
	}

	public String getStatusPedidoResolved() {
		return this.statusPedidoResolved;
	}

	public void setStatusPedidoResolved(String statusPedidoResolved) {
		this.statusPedidoResolved = statusPedidoResolved;
	}

	public String getStatusPedidoValue() {
		return this.statusPedidoValue;
	}

	public void setStatusPedidoValue(String statusPedidoValue) {
		this.statusPedidoValue = statusPedidoValue;
	}

	public String getTipoContratoIdResolved() {
		return this.tipoContratoIdResolved;
	}

	public void setTipoContratoIdResolved(String tipoContratoIdResolved) {
		this.tipoContratoIdResolved = tipoContratoIdResolved;
	}

	public String getTipoContratoIdValue() {
		return this.tipoContratoIdValue;
	}

	public void setTipoContratoIdValue(String tipoContratoIdValue) {
		this.tipoContratoIdValue = tipoContratoIdValue;
	}

	public String getUfPrincipalIdResolved() {
		return this.ufPrincipalIdResolved;
	}

	public void setUfPrincipalIdResolved(String ufPrincipalIdResolved) {
		this.ufPrincipalIdResolved = ufPrincipalIdResolved;
	}

	public String getUfPrincipalIdValue() {
		return this.ufPrincipalIdValue;
	}

	public void setUfPrincipalIdValue(String ufPrincipalIdValue) {
		this.ufPrincipalIdValue = ufPrincipalIdValue;
	}

	public String getX1_ContatoTecnicoIdResolved() {
		return this.x1_ContatoTecnicoIdResolved;
	}

	public void setX1_ContatoTecnicoIdResolved(String x1_ContatoTecnicoIdResolved) {
		this.x1_ContatoTecnicoIdResolved = x1_ContatoTecnicoIdResolved;
	}

	public String getX1_DocumentoResolved() {
		return this.x1_DocumentoResolved;
	}

	public void setX1_DocumentoResolved(String x1_DocumentoResolved) {
		this.x1_DocumentoResolved = x1_DocumentoResolved;
	}

	public String getX1_DocumentoValue() {
		return this.x1_DocumentoValue;
	}

	public void setX1_DocumentoValue(String x1_DocumentoValue) {
		this.x1_DocumentoValue = x1_DocumentoValue;
	}

	public String getX1_DominioResolved() {
		return this.x1_DominioResolved;
	}

	public void setX1_DominioResolved(String x1_DominioResolved) {
		this.x1_DominioResolved = x1_DominioResolved;
	}

	public String getX1_DominioValue() {
		return this.x1_DominioValue;
	}

	public void setX1_DominioValue(String x1_DominioValue) {
		this.x1_DominioValue = x1_DominioValue;
	}

	public String getX1_EmailTecnicoResolved() {
		return this.x1_EmailTecnicoResolved;
	}

	public void setX1_EmailTecnicoResolved(String x1_EmailTecnicoResolved) {
		this.x1_EmailTecnicoResolved = x1_EmailTecnicoResolved;
	}

	public String getX1_EmailTecnicoValue() {
		return this.x1_EmailTecnicoValue;
	}

	public void setX1_EmailTecnicoValue(String x1_EmailTecnicoValue) {
		this.x1_EmailTecnicoValue = x1_EmailTecnicoValue;
	}

	public String getX1_GerenteProjetoEmailResolved() {
		return this.x1_GerenteProjetoEmailResolved;
	}

	public void setX1_GerenteProjetoEmailResolved(String x1_GerenteProjetoEmailResolved) {
		this.x1_GerenteProjetoEmailResolved = x1_GerenteProjetoEmailResolved;
	}

	public String getX1_GerenteProjetoEmailValue() {
		return this.x1_GerenteProjetoEmailValue;
	}

	public void setX1_GerenteProjetoEmailValue(String x1_GerenteProjetoEmailValue) {
		this.x1_GerenteProjetoEmailValue = x1_GerenteProjetoEmailValue;
	}

	public String getX1_GerenteProjetoIdResolved() {
		return this.x1_GerenteProjetoIdResolved;
	}

	public void setX1_GerenteProjetoIdResolved(String x1_GerenteProjetoIdResolved) {
		this.x1_GerenteProjetoIdResolved = x1_GerenteProjetoIdResolved;
	}

	public String getX1_GerenteProjetoTelefoneResolved() {
		return this.x1_GerenteProjetoTelefoneResolved;
	}

	public void setX1_GerenteProjetoTelefoneResolved(String x1_GerenteProjetoTelefoneResolved) {
		this.x1_GerenteProjetoTelefoneResolved = x1_GerenteProjetoTelefoneResolved;
	}

	public String getX1_GerenteProjetoTelefoneValue() {
		return this.x1_GerenteProjetoTelefoneValue;
	}

	public void setX1_GerenteProjetoTelefoneValue(String x1_GerenteProjetoTelefoneValue) {
		this.x1_GerenteProjetoTelefoneValue = x1_GerenteProjetoTelefoneValue;
	}

	public String getX1_NivelAcompanhamentoResolved() {
		return this.x1_NivelAcompanhamentoResolved;
	}

	public void setX1_NivelAcompanhamentoResolved(String x1_NivelAcompanhamentoResolved) {
		this.x1_NivelAcompanhamentoResolved = x1_NivelAcompanhamentoResolved;
	}

	public String getX1_NivelAcompanhamentoValue() {
		return this.x1_NivelAcompanhamentoValue;
	}

	public void setX1_NivelAcompanhamentoValue(String x1_NivelAcompanhamentoValue) {
		this.x1_NivelAcompanhamentoValue = x1_NivelAcompanhamentoValue;
	}

	public String getX1_TelefoneTecnicoResolved() {
		return this.x1_TelefoneTecnicoResolved;
	}

	public void setX1_TelefoneTecnicoResolved(String x1_TelefoneTecnicoResolved) {
		this.x1_TelefoneTecnicoResolved = x1_TelefoneTecnicoResolved;
	}

	public String getX1_TelefoneTecnicoValue() {
		return this.x1_TelefoneTecnicoValue;
	}

	public void setX1_TelefoneTecnicoValue(String x1_TelefoneTecnicoValue) {
		this.x1_TelefoneTecnicoValue = x1_TelefoneTecnicoValue;
	}

	public String getX991_IdResolved() {
		return this.x991_IdResolved;
	}

	public void setX991_IdResolved(String x991_IdResolved) {
		this.x991_IdResolved = x991_IdResolved;
	}

	public String getX991_IdValue() {
		return this.x991_IdValue;
	}

	public void setX991_IdValue(String x991_IdValue) {
		this.x991_IdValue = x991_IdValue;
	}

	public String get_32fc7c16OportunidadeIdValue() {
		return _32fc7c16OportunidadeIdValue;
	}

	public void set_32fc7c16OportunidadeIdValue(String _32fc7c16OportunidadeIdValue) {
		this._32fc7c16OportunidadeIdValue = _32fc7c16OportunidadeIdValue;
	}

	public String get_32fc7c16OportunidadeIdResolved() {
		return _32fc7c16OportunidadeIdResolved;
	}

	public void set_32fc7c16OportunidadeIdResolved(String _32fc7c16OportunidadeIdResolved) {
		this._32fc7c16OportunidadeIdResolved = _32fc7c16OportunidadeIdResolved;
	}

	public String get_32fc7c16MotivoFechamentoIdValue() {
		return _32fc7c16MotivoFechamentoIdValue;
	}

	public void set_32fc7c16MotivoFechamentoIdValue(String _32fc7c16MotivoFechamentoIdValue) {
		this._32fc7c16MotivoFechamentoIdValue = _32fc7c16MotivoFechamentoIdValue;
	}

	public String get_32fc7c16MotivoFechamentoIdResolved() {
		return _32fc7c16MotivoFechamentoIdResolved;
	}

	public void set_32fc7c16MotivoFechamentoIdResolved(String _32fc7c16MotivoFechamentoIdResolved) {
		this._32fc7c16MotivoFechamentoIdResolved = _32fc7c16MotivoFechamentoIdResolved;
	}

	public String getX1_ObservacaoValue() {
		return x1_ObservacaoValue;
	}

	public void setX1_ObservacaoValue(String x1_ObservacaoValue) {
		this.x1_ObservacaoValue = x1_ObservacaoValue;
	}

	public String getX1_ObservacaoResolved() {
		return x1_ObservacaoResolved;
	}

	public void setX1_ObservacaoResolved(String x1_ObservacaoResolved) {
		this.x1_ObservacaoResolved = x1_ObservacaoResolved;
	}

	public String getX1_OpportunidIDValue() {
		return x1_OpportunidIDValue;
	}

	public void setX1_OpportunidIDValue(String x1_OpportunidIDValue) {
		this.x1_OpportunidIDValue = x1_OpportunidIDValue;
	}

	public String getX1_OpportunidIDResolved() {
		return x1_OpportunidIDResolved;
	}

	public void setX1_OpportunidIDResolved(String x1_OpportunidIDResolved) {
		this.x1_OpportunidIDResolved = x1_OpportunidIDResolved;
	}

	public String getX1_ClientIDValue() {
		return x1_ClientIDValue;
	}

	public void setX1_ClientIDValue(String x1_ClientIDValue) {
		this.x1_ClientIDValue = x1_ClientIDValue;
	}

	public String getX1_ClientIDResolved() {
		return x1_ClientIDResolved;
	}

	public void setX1_ClientIDResolved(String x1_ClientIDResolved) {
		this.x1_ClientIDResolved = x1_ClientIDResolved;
	}

	public String getX1_EmailValue() {
		return x1_EmailValue;
	}

	public void setX1_EmailValue(String x1_EmailValue) {
		this.x1_EmailValue = x1_EmailValue;
	}

	public String getX1_EmailResolved() {
		return x1_EmailResolved;
	}

	public void setX1_EmailResolved(String x1_EmailResolved) {
		this.x1_EmailResolved = x1_EmailResolved;
	}

	public PedidosPlune(Long id, String x1_EmailValue, String x1_EmailResolved, String _32fc7c16OportunidadeIdValue,
			String _32fc7c16OportunidadeIdResolved, String _32fc7c16MotivoFechamentoIdValue,
			String _32fc7c16MotivoFechamentoIdResolved, String x1_ObservacaoValue, String x1_ObservacaoResolved,
			String x1_OpportunidIDValue, String x1_OpportunidIDResolved, String x1_ClientIDValue,
			String x1_ClientIDResolved, String _g_canDelete, String _g_canUpdateResolved,
			String _2fc7c16_BranchIdResolved, String _2fc7c16_BranchIdValue, String _32fc7c16_CompanyIdResolved,
			String _32fc7c16_CompanyIdValue, String _32fc7c16_IdResolved, String _32fc7c16_IdValue,
			String bairroPrincipalResolved, String bairroPrincipalValue, String branchIdResolved, String branchIdValue,
			String cepPrincipalResolved, String cepPrincipalValue, String cidadePrincipalIdResolved,
			String cidadePrincipalIdValue, String clienteIdResolved, String clienteIdValue, String companyIdResolved,
			String companyIdValue, String complementoPrincipalResolved, String complementoPrincipalValue,
			String descricaoResolved, String descricaoValue, String empresaIdResolved, String empresaIdValue,
			String enderecoPrincipalResolved, String enderecoPrincipalValue, String idResolved, String idValue,
			String nomRazaoSocialResolved, String nomRazaoSocialValue, String numeroPrincipalResolved,
			String numeroPrincipalValue, String paisPrincipalIdResolved, String paisPrincipalIdValue,
			String parceiroIdResolved, String parceiroIdValue, String pedidoIdResolved, String pedidoIdValue,
			String produtoIdResolved, String produtoIdValue, String quantidadeResolved, String quantidadeValue,
			String representanteIdResolved, String representanteIdValue, String statusPedidoResolved,
			String statusPedidoValue, String tipoContratoIdResolved, String tipoContratoIdValue,
			String ufPrincipalIdResolved, String ufPrincipalIdValue, String x1_ContatoTecnicoIdResolved,
			String x1_DocumentoResolved, String x1_DocumentoValue, String x1_DominioResolved, String x1_DominioValue,
			String x1_EmailTecnicoResolved, String x1_EmailTecnicoValue, String x1_GerenteProjetoEmailResolved,
			String x1_GerenteProjetoEmailValue, String x1_GerenteProjetoIdResolved,
			String x1_GerenteProjetoTelefoneResolved, String x1_GerenteProjetoTelefoneValue,
			String x1_NivelAcompanhamentoResolved, String x1_NivelAcompanhamentoValue,
			String x1_TelefoneTecnicoResolved, String x1_TelefoneTecnicoValue, String x991_IdResolved,
			String x991_IdValue, String sendingStatus) {
		this.id = id;
		this.x1_EmailValue = x1_EmailValue;
		this.x1_EmailResolved = x1_EmailResolved;
		this._32fc7c16OportunidadeIdValue = _32fc7c16OportunidadeIdValue;
		this._32fc7c16OportunidadeIdResolved = _32fc7c16OportunidadeIdResolved;
		this._32fc7c16MotivoFechamentoIdValue = _32fc7c16MotivoFechamentoIdValue;
		this._32fc7c16MotivoFechamentoIdResolved = _32fc7c16MotivoFechamentoIdResolved;
		this.x1_ObservacaoValue = x1_ObservacaoValue;
		this.x1_ObservacaoResolved = x1_ObservacaoResolved;
		this.x1_OpportunidIDValue = x1_OpportunidIDValue;
		this.x1_OpportunidIDResolved = x1_OpportunidIDResolved;
		this.x1_ClientIDValue = x1_ClientIDValue;
		this.x1_ClientIDResolved = x1_ClientIDResolved;
		this._g_canDelete = _g_canDelete;
		this._g_canUpdateResolved = _g_canUpdateResolved;
		this._2fc7c16_BranchIdResolved = _2fc7c16_BranchIdResolved;
		this._2fc7c16_BranchIdValue = _2fc7c16_BranchIdValue;
		this._32fc7c16_CompanyIdResolved = _32fc7c16_CompanyIdResolved;
		this._32fc7c16_CompanyIdValue = _32fc7c16_CompanyIdValue;
		this._32fc7c16_IdResolved = _32fc7c16_IdResolved;
		this._32fc7c16_IdValue = _32fc7c16_IdValue;
		this.bairroPrincipalResolved = bairroPrincipalResolved;
		this.bairroPrincipalValue = bairroPrincipalValue;
		this.branchIdResolved = branchIdResolved;
		this.branchIdValue = branchIdValue;
		this.cepPrincipalResolved = cepPrincipalResolved;
		this.cepPrincipalValue = cepPrincipalValue;
		this.cidadePrincipalIdResolved = cidadePrincipalIdResolved;
		this.cidadePrincipalIdValue = cidadePrincipalIdValue;
		this.clienteIdResolved = clienteIdResolved;
		this.clienteIdValue = clienteIdValue;
		this.companyIdResolved = companyIdResolved;
		this.companyIdValue = companyIdValue;
		this.complementoPrincipalResolved = complementoPrincipalResolved;
		this.complementoPrincipalValue = complementoPrincipalValue;
		this.descricaoResolved = descricaoResolved;
		this.descricaoValue = descricaoValue;
		this.empresaIdResolved = empresaIdResolved;
		this.empresaIdValue = empresaIdValue;
		this.enderecoPrincipalResolved = enderecoPrincipalResolved;
		this.enderecoPrincipalValue = enderecoPrincipalValue;
		this.idResolved = idResolved;
		this.idValue = idValue;
		this.nomRazaoSocialResolved = nomRazaoSocialResolved;
		this.nomRazaoSocialValue = nomRazaoSocialValue;
		this.numeroPrincipalResolved = numeroPrincipalResolved;
		this.numeroPrincipalValue = numeroPrincipalValue;
		this.paisPrincipalIdResolved = paisPrincipalIdResolved;
		this.paisPrincipalIdValue = paisPrincipalIdValue;
		this.parceiroIdResolved = parceiroIdResolved;
		this.parceiroIdValue = parceiroIdValue;
		this.pedidoIdResolved = pedidoIdResolved;
		this.pedidoIdValue = pedidoIdValue;
		this.produtoIdResolved = produtoIdResolved;
		this.produtoIdValue = produtoIdValue;
		this.quantidadeResolved = quantidadeResolved;
		this.quantidadeValue = quantidadeValue;
		this.representanteIdResolved = representanteIdResolved;
		this.representanteIdValue = representanteIdValue;
		this.statusPedidoResolved = statusPedidoResolved;
		this.statusPedidoValue = statusPedidoValue;
		this.tipoContratoIdResolved = tipoContratoIdResolved;
		this.tipoContratoIdValue = tipoContratoIdValue;
		this.ufPrincipalIdResolved = ufPrincipalIdResolved;
		this.ufPrincipalIdValue = ufPrincipalIdValue;
		this.x1_ContatoTecnicoIdResolved = x1_ContatoTecnicoIdResolved;
		this.x1_DocumentoResolved = x1_DocumentoResolved;
		this.x1_DocumentoValue = x1_DocumentoValue;
		this.x1_DominioResolved = x1_DominioResolved;
		this.x1_DominioValue = x1_DominioValue;
		this.x1_EmailTecnicoResolved = x1_EmailTecnicoResolved;
		this.x1_EmailTecnicoValue = x1_EmailTecnicoValue;
		this.x1_GerenteProjetoEmailResolved = x1_GerenteProjetoEmailResolved;
		this.x1_GerenteProjetoEmailValue = x1_GerenteProjetoEmailValue;
		this.x1_GerenteProjetoIdResolved = x1_GerenteProjetoIdResolved;
		this.x1_GerenteProjetoTelefoneResolved = x1_GerenteProjetoTelefoneResolved;
		this.x1_GerenteProjetoTelefoneValue = x1_GerenteProjetoTelefoneValue;
		this.x1_NivelAcompanhamentoResolved = x1_NivelAcompanhamentoResolved;
		this.x1_NivelAcompanhamentoValue = x1_NivelAcompanhamentoValue;
		this.x1_TelefoneTecnicoResolved = x1_TelefoneTecnicoResolved;
		this.x1_TelefoneTecnicoValue = x1_TelefoneTecnicoValue;
		this.x991_IdResolved = x991_IdResolved;
		this.x991_IdValue = x991_IdValue;
		SendingStatus = sendingStatus;
	}

	
	}

	

	

