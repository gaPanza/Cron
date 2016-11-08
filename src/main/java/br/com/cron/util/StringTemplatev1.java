package br.com.cron.util;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.stringtemplate.v4.ST;

import br.com.cron.DAO.TarefaDAO;
import br.com.cron.resources.Tarefa;

public class StringTemplatev1 {

	public static String StringTemplate(long id) throws URISyntaxException, IOException {
		StringTemplatev1 stHelper = new StringTemplatev1();
		ST emailTemplate = new ST(
				new String(Files.readAllBytes(Paths.get(StringTemplatev1.class.getResource("email.html").toURI()))),
				'$', '$');
		return stHelper.processTemplate(emailTemplate, id);

	}

	private String processTemplate(ST emailTemplate, long id) throws IOException, URISyntaxException {
		
		// Variaveis Tarefa Generica
		Tarefa tarefa = TarefaDAO.getInstance().getById(id);
		List<String> FN = tarefa.getEmail();
		
		//Busca da Tarefa o Nome
		String name = null;
		for (int i = 0; i < FN.size(); i++) {
			int j = FN.get(i).indexOf(".");
			name = FN.get(i).substring(0, 1).toUpperCase() + FN.get(i).substring(1, j);
		}
		addAttributeToEmailTemplate(emailTemplate, "firstName", name);
		
		//Adiciona ao Template
		String emailContentBoasVindas = "Um novo evento Cron executou! Veja o ID e descrição abaixo!";
		String emailContentTask = tarefa.getDescricao();
		String emailContentID = tarefa.getId().toString();
		
		String contentFormat = "<tr style=\"font-weight: 300; background: #c2c2a3; color: white;\"><td style=\"color: white; padding: 0 0 0 20px;\">"+emailContentID+"</td><td style=\"padding: 0 0 0 30px;\" width=\"75%\">"+emailContentTask+"</td></tr>";
		
		addAttributeToEmailTemplate(emailTemplate, "contentBoasVindas", emailContentBoasVindas);
		addAttributeToEmailTemplate(emailTemplate, "contentSize", contentFormat);
		addAttributeToEmailTemplate(emailTemplate, "senderFullName", "IPNET Cron");
		return emailTemplate.render();
	}

	public void addAttributeToEmailTemplate(ST emailTemplate, String attributeName, String attributeValue) {
		try {
			emailTemplate.remove(attributeName);
		} catch (Exception e) {
		}

		emailTemplate.add(attributeName, attributeValue);
	}

}
