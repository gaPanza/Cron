package br.com.cron.resources;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.Serializable;
import javax.persistence.*;

@SqlResultSetMapping(
	       name="ResultsQuery",
	       classes={
	          @ConstructorResult(
	               targetClass=br.com.cron.resources.MyteamsCustomFieldQuery.class,
	                 columns={
	                    @ColumnResult(name="id", type = Long.class),
	                    @ColumnResult(name="name")
	                    }
	          )
	       }
	      )
@Entity
@Table(name="myteams_custom_field_def")
@NamedQuery(name="MyteamsCustomFieldDef.findAll", query="SELECT m FROM MyteamsCustomFieldDef m")
public class MyteamsCustomFieldDef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean enabled;

	@Column(name="field_length")
	private Integer fieldLength;

	@Column(name="field_order")
	private Integer fieldOrder;

	private String name;

	@Column(name="read_only")
	private Boolean readOnly;

	@Column(name="required_for_check_out")
	private Boolean requiredForCheckOut;

	private String type;

	//bi-directional many-to-one association to SecurityEntityObject
	@ManyToOne
	@JoinColumn(name="team")
	private SecurityEntityObject securityEntityObject;

	public MyteamsCustomFieldDef() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getFieldLength() {
		return this.fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public Integer getFieldOrder() {
		return this.fieldOrder;
	}

	public void setFieldOrder(Integer fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Boolean getRequiredForCheckOut() {
		return this.requiredForCheckOut;
	}

	public void setRequiredForCheckOut(Boolean requiredForCheckOut) {
		this.requiredForCheckOut = requiredForCheckOut;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SecurityEntityObject getSecurityEntityObject() {
		return this.securityEntityObject;
	}

	public void setSecurityEntityObject(SecurityEntityObject securityEntityObject) {
		this.securityEntityObject = securityEntityObject;
	}

}