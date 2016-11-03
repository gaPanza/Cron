package br.com.cron.resources;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="tarefa")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="comun_id")
	private Integer comunId;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tarefa_cronconfig", joinColumns = @JoinColumn(name = "tarefa_id"))
	@Column (name="cronconfig", columnDefinition = "text")
	private List<String> cronconfig;

	@Column(name="data_criacao")
	private Timestamp dataCriacao;

	@Column(length=255)
	private String descricao;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tarefaEmail", joinColumns = @JoinColumn(name = "tarefa_id"))
	@Column (name="emails", columnDefinition = "text")
	private List<String> email;

	private Integer option;

	private Boolean profarma;
	
	private Boolean plune;
	
	public Tarefa() {
	}
	
	public Boolean getPlune(){
		return this.plune;
	}
	
	public void setPlune(Boolean plune){
		this.plune = plune;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getComunId() {
		return this.comunId;
	}

	public void setComunId(Integer comunId) {
		this.comunId = comunId;
	}

	public List<String> getCronconfig() {
		return this.cronconfig;
	}

	public void setCronconfig(List<String> cronconfig) {
		this.cronconfig = cronconfig;
	}

	public Timestamp getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getEmail() {
		return this.email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public Integer getOption() {
		return this.option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public Boolean getProfarma() {
		return this.profarma;
	}

	public void setProfarma(Boolean profarma) {
		this.profarma = profarma;
	}
}