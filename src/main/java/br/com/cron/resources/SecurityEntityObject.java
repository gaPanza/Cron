package br.com.cron.resources;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the security_entity_object database table.
 * 
 */
@Entity
@Table(name="security_entity_object")
@NamedQuery(name="SecurityEntityObject.findAll", query="SELECT s FROM SecurityEntityObject s")
public class SecurityEntityObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="can_delete")
	private Boolean canDelete;

	private String code;

	private String email;

	@Column(name="id_logo")
	private Long idLogo;

	@Column(name="id_role")
	private Long idRole;

	@Column(name="id_view_port")
	private Long idViewPort;

	private String login;

	private String name;

	private String password;

	private String status;

	@Column(name="type_entity")
	private String typeEntity;

	//bi-directional many-to-one association to MyteamsCustomFieldDef
	@OneToMany(mappedBy="securityEntityObject")
	private List<MyteamsCustomFieldDef> myteamsCustomFieldDefs;

	public SecurityEntityObject() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCanDelete() {
		return this.canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdLogo() {
		return this.idLogo;
	}

	public void setIdLogo(Long idLogo) {
		this.idLogo = idLogo;
	}

	public Long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Long getIdViewPort() {
		return this.idViewPort;
	}

	public void setIdViewPort(Long idViewPort) {
		this.idViewPort = idViewPort;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeEntity() {
		return this.typeEntity;
	}

	public void setTypeEntity(String typeEntity) {
		this.typeEntity = typeEntity;
	}

	public List<MyteamsCustomFieldDef> getMyteamsCustomFieldDefs() {
		return this.myteamsCustomFieldDefs;
	}

	public void setMyteamsCustomFieldDefs(List<MyteamsCustomFieldDef> myteamsCustomFieldDefs) {
		this.myteamsCustomFieldDefs = myteamsCustomFieldDefs;
	}

	public MyteamsCustomFieldDef addMyteamsCustomFieldDef(MyteamsCustomFieldDef myteamsCustomFieldDef) {
		getMyteamsCustomFieldDefs().add(myteamsCustomFieldDef);
		myteamsCustomFieldDef.setSecurityEntityObject(this);

		return myteamsCustomFieldDef;
	}

	public MyteamsCustomFieldDef removeMyteamsCustomFieldDef(MyteamsCustomFieldDef myteamsCustomFieldDef) {
		getMyteamsCustomFieldDefs().remove(myteamsCustomFieldDef);
		myteamsCustomFieldDef.setSecurityEntityObject(null);

		return myteamsCustomFieldDef;
	}

}