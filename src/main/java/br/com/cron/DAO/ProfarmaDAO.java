package br.com.cron.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.cron.resources.MyteamsCustomFieldQuery;

public class ProfarmaDAO {
	private static ProfarmaDAO instance;
	protected static EntityManager entityManager;

	public static ProfarmaDAO getInstance() {
		if (instance == null) {
			instance = new ProfarmaDAO();
		}
		return instance;
	}

	private ProfarmaDAO() {
		entityManager = getEntityManager();
	}

	// Cria o Manager
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("profarma");
		return factory.createEntityManager();
	}

	// Retorna uma lista de tarefas

	@SuppressWarnings("unchecked")
	public List<MyteamsCustomFieldQuery> findAll() {
		return entityManager.createNativeQuery("select a.id, a.name from security_entity_object as a left join myteams_custom_field_def as b on a.id = b.team where b.team is null;", "ResultsQuery").getResultList();
	}

}
