package br.com.cron.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cron.plune.Entity.Emailspedido;
import br.com.cron.resources.Tarefa;
import br.com.cron.resources.Usuario;
/*
 * Class by Gabriel Panza 08/11
 */
public class TarefaDAO {
	private static TarefaDAO instance;
	protected static EntityManager entityManager;

	public static TarefaDAO getInstance() {
		if (instance == null) {
			instance = new TarefaDAO();
		}
		return instance;
	}

	private TarefaDAO() {
		entityManager = getEntityManager();
	}

	// Cria o Manager
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CRUD");
		return factory.createEntityManager();
	}

	// Retorna a tarefa pela ID
	public Tarefa getById(final long id) {
		return entityManager.find(Tarefa.class, id);

	}

	// Retorna uma lista de tarefas
	@SuppressWarnings("unchecked")
	public List<Tarefa> findAll() {
		return entityManager.createQuery("FROM " + Tarefa.class.getName()).getResultList();
	}
	
	//Retorna uma lista de tarefas Plune
	@SuppressWarnings("unchecked")
	public List<Tarefa> findAllPlune(){
		return entityManager.createQuery("FROM "+Tarefa.class.getName()+" WHERE plune = true").getResultList();
	}

	// Insere um registro no banco
	public void persist(Tarefa tarefa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(tarefa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();

		}
	}

	// Insere e atualiza um registro no banco
	public void merge(Tarefa tarefa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(tarefa);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	// Remove uma Tarefa do Banco (recebendo o objeto)
	public void remove(Tarefa tarefa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(tarefa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Retornando ao Menu");
			entityManager.getTransaction().rollback();
		}
	}
	// Remove uma Tarefa do Banco (recebendo uma ID)
	public void removeById(final long l) {
		try {
			Tarefa tarefa = getById(l);
			remove(tarefa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario getByName(String name) {
		return entityManager.find(Usuario.class, name);
	}

	public void addUser(Usuario user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
			System.out.println("Usuario adicionado");
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Emailspedido> listemails(){
		return entityManager.createNativeQuery("SELECT * FROM emailspedido ORDER BY idpedido ASC ", "Emails").getResultList();
	}
	public String listEmailsById(String idpedido){
		return (String) entityManager.createNativeQuery("SELECT emails FROM emailspedido WHERE idpedido =" + "'"+idpedido+"'").getSingleResult();
	}
	
	public void modifyEmails(Emailspedido x){
		entityManager.getTransaction().begin();
		entityManager.merge(x);
		entityManager.getTransaction().commit();
		System.out.println("Pedido de Status" +x.getIdpedido()+" modificado para: " + x.getEmails());
	}
	public void removeAll(){
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery("DELETE FROM tarefa WHERE plune = true").executeUpdate();
		entityManager.getTransaction().commit();
	}
}
