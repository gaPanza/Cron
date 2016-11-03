package br.com.cron.resources;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the status_pedido database table.
 * 
 */
@Entity
@Table(name="status_pedido")
@NamedQuery(name="StatusPedido.findAll", query="SELECT s FROM StatusPedido s")
public class StatusPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_status", unique=true, nullable=false)
	private Long idStatus;

	@Column(name="email_destinatario", length=500)
	private String emailDestinatario;

	@Column(nullable=false, length=255)
	private String status;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="statusPedido")
	private List<Pedido> pedidos;

	public StatusPedido() {
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getEmailDestinatario() {
		return this.emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setStatusPedido(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setStatusPedido(null);

		return pedido;
	}

}