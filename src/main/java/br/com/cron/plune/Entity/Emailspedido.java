package br.com.cron.plune.Entity;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.Serializable;
import javax.persistence.*;


@SqlResultSetMapping(name="Emails",
entities={
    @EntityResult(entityClass=br.com.cron.plune.Entity.Emailspedido.class)
})
@Entity
@NamedQuery(name="Emailspedido.findAll", query="SELECT e FROM Emailspedido e")
public class Emailspedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idpedido;

	private String emails;

	public Emailspedido() {
	}

	public String getIdpedido() {
		return this.idpedido;
	}

	public void setIdpedido(String idpedido) {
		this.idpedido = idpedido;
	}

	public String getEmails() {
		return this.emails;
	}

	public Emailspedido(String idpedido, String emails) {
		this.idpedido = idpedido;
		this.emails = emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

}