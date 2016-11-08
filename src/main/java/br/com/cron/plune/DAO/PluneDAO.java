package br.com.cron.plune.DAO;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import br.com.cron.plune.Entity.Emailspedido;
import br.com.cron.plune.Entity.PedidosPlune;
/*
 * Class by Gabriel Panza 08/11
 */
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
	public PedidosPlune getById(final long id) {
		return entityManagerPlune.find(PedidosPlune.class, id);

	}

	// Verifica o Banco em busca de pedidos pendentes
	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPlune> chkPending() {

		return (ArrayList<PedidosPlune>) entityManagerPlune
				.createNativeQuery("SELECT * FROM pedidosplune WHERE SendingStatus = 'PENDENTE'", "PendingStatus")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPlune> chkStatus(String user, String status) {

		return (ArrayList<PedidosPlune>) entityManagerPlune
				.createNativeQuery(
						"SELECT * FROM PedidosPlune WHERE SendingStatus = 'PENDENTE' AND statusPedidoValue = " + "'"
								+ status + "'" + "AND representanteIdResolved = " + "'" + user + "'",
						"PendingStatus")
				.getResultList();

	}

	// Insere um Pedido no Banco
	public void persist(PedidosPlune pedido) {
		try {
				entityManagerPlune.getTransaction().begin();
				entityManagerPlune.persist(pedido);
				entityManagerPlune.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManagerPlune.getTransaction().rollback();
		}
	}
	
	public String persistNull(PedidosPlune pedido){
		entityManagerPlune.getTransaction().begin();
		entityManagerPlune.persist(pedido);
		entityManagerPlune.getTransaction().commit();
		return pedido.getStatusPedidoValue();
	}

	// Insere e atualiza um Pedido no Banco
	public void merge(PedidosPlune pedido) {
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
	public void remove(PedidosPlune pedido) {
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
	public ArrayList<PedidosPlune> checkDb(){
		try{
			return (ArrayList<PedidosPlune>) entityManagerPlune.createNativeQuery("SELECT * FROM pedidosplune","PendingStatus").getResultList();
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<PedidosPlune> returnAll(){
		return (ArrayList<PedidosPlune>) entityManagerPlune
				.createNativeQuery("SELECT * FROM pedidosplune", "PendingStatus")
				.getResultList();
	}

}
