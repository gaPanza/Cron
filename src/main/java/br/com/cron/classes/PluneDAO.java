package br.com.cron.classes;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.cron.resources.Emailspedido;

public class PluneDAO {

	private static PluneDAO instance;
	protected static EntityManager entityManagerPlune;

	public static PluneDAO getInstance() {
		if (instance == null) {
			instance = new PluneDAO();
		}
		return instance;
	}

	private PluneDAO() {
		entityManagerPlune = Persistence.createEntityManagerFactory("CRUD").createEntityManager();
	}

	// Retorna o Pedido pela ID
	public PedidosPluneDTO getById(final long id) {
		return entityManagerPlune.find(PedidosPluneDTO.class, id);

	}

	// Verifica o Banco em busca de pedidos pendentes
	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPluneDTO> chkPending() {

		return (ArrayList<PedidosPluneDTO>) entityManagerPlune
				.createNativeQuery("SELECT * FROM pedidosplune WHERE SendingStatus = 'PENDENTE'", "PendingStatus")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPluneDTO> chkStatus(String user, String status) {

		return (ArrayList<PedidosPluneDTO>) entityManagerPlune
				.createNativeQuery(
						"SELECT * FROM PedidosPlune WHERE SendingStatus = 'PENDENTE' AND statusPedidoValue = " + "'"
								+ status + "'" + "AND representanteIdResolved = " + "'" + user + "'",
						"PendingStatus")
				.getResultList();

	}

	// Insere um Pedido no Banco
	@SuppressWarnings("unchecked")
	public String persist(PedidosPluneDTO pedido) {
		try {
			ArrayList<PedidosPluneDTO> x = (ArrayList<PedidosPluneDTO>) entityManagerPlune.createNativeQuery(
					"SELECT * FROM PedidosPlune WHERE _632fc7c16idvalue = " + "'" + pedido.get_32fc7c16_IdValue() + "'"
							+ "AND x991_idresolved = " + "'" + pedido.getX991_IdResolved() + "'",
					"PendingStatus").getResultList();

			if (x.size() ==0) {
				entityManagerPlune.getTransaction().begin();
				entityManagerPlune.persist(pedido);
				entityManagerPlune.getTransaction().commit();
				return pedido.getStatusPedidoValue();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
			return null;

		}
	}

	// Insere e atualiza um Pedido no Banco
	public void merge(PedidosPluneDTO pedido) {
		try {
			entityManagerPlune.getTransaction().begin();
			entityManagerPlune.merge(pedido);
			entityManagerPlune.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
		}
	}

	// Remove um Pedido do Banco
	public void remove(PedidosPluneDTO pedido) {
		try {
			entityManagerPlune.getTransaction().begin();
			entityManagerPlune.remove(pedido);
			entityManagerPlune.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
		}
	}
	
	public void modifySts(){
		try{
			entityManagerPlune.getTransaction().begin();
			entityManagerPlune.createNativeQuery("UPDATE pedidosplune SET sendingstatus = 'ENVIADO' WHERE sendingstatus = 'PENDENTE'").executeUpdate();
			entityManagerPlune.getTransaction().commit();
		} catch (Exception e){
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
		}
	}
	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPluneDTO> checkDb(){
		try{
			return (ArrayList<PedidosPluneDTO>) entityManagerPlune.createNativeQuery("SELECT * FROM pedidosplune","PendingStatus").getResultList();
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public boolean fillEmails(Emailspedido x){
		try{
		entityManagerPlune.getTransaction().begin();
		entityManagerPlune.merge(x);
		entityManagerPlune.getTransaction().commit();
		return true;
		}catch(Exception e){
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
			return false;
		}
		
	}

}
