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

import br.com.cron.DAO.ProfarmaDAO;
import br.com.cron.DAO.TarefaDAO;
import br.com.cron.resources.MyteamsCustomFieldQuery;
import br.com.cron.resources.Tarefa;
	
//Classe preparada para criar mais um template de envio de email
public class StringTemplatev2 {
	public static String StringTemplateProfarma(long id) {
		StringTemplatev2 stHelper = new StringTemplatev2();
		ST emailTemplate;
		try {
			emailTemplate = new ST(
					new String(Files
							.readAllBytes(Paths.get(StringTemplatev2.class.getResource("emailnew.html").toURI()))),
					'$', '$');
			return stHelper.processTemplate(emailTemplate, id);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String processTemplate(ST emailTemplate, long id) throws IOException, URISyntaxException {

		// Variaveis Tarefa com Profarma
		Tarefa tarefa = TarefaDAO.getInstance().getById(id);
		List <String> FN = tarefa.getEmail();
		List<MyteamsCustomFieldQuery> NullList = ProfarmaDAO.getInstance().findAll();
		
		
		// Busca da Tarefa o Nome
		String name = null;
		for (int i = 0; i < FN.size(); i++) {
			int j = FN.get(i).indexOf(".");
			name = FN.get(i).substring(0, 1).toUpperCase() + FN.get(i).substring(1, j);
		}
		addAttributeToEmailTemplate(emailTemplate, "firstName", name);
		
		// Adiciona ao Template
		String emailContentBoasVindas = "Segue os detalhes dos custom fields para o tempo requisitado";
		int emailContentSize = NullList.size();
		int[] contentID = new int[emailContentSize];
		String[] contentName = new String[emailContentSize];
		String contentFormat = "<tr style=\"font-weight: 300; background: #c2c2a3; color: white;\"><td style=\"color: white; padding: 0 0 0 20px;\">Teste ID 01</td><td style=\"padding: 0 0 0 30px;\" width=\"75%\">Não há campos nulos =))</td></tr>";
		short color = 1;
		
		for(int i = 0; i<NullList.size();i++, color++){
			contentID[i] = (int)(long)NullList.get(i).getId();
			contentName[i] = NullList.get(i).getName();
			contentFormat = contentFormat + " " + "<tr style=\"background: #e0e0d1; color: white;\"><td style=\"color: white; padding: 0 0 0 20px;\"><td colspan=\"2\" style=\"width: 100%; padding: 0px; box-sizing: border-box;\">"+contentID[i]+"</td><td colspan=\"2\" style=\"width: 100%; padding: 0px; box-sizing: border-box;\">"+contentName[i]+"</td></tr>";
			if(color %2 == 0)
				contentFormat = contentFormat + " "+"<tr style=\"background: #c2c2a3; color: white;\"><td style=\"color: white; padding: 0 0 0 20px;\"><td colspan=\"2\" style=\"width: 100%; padding: 0px; box-sizing: border-box;\">"+contentID[i]+"</td><td colspan=\"2\" style=\"width: 100%; padding: 0px; box-sizing: border-box;\">"+contentName[i]+"</td></tr>";
			
		}
		
		// Adiciona os Atributos Profarma no Template
		addAttributeToEmailTemplate(emailTemplate, "contentBoasVindas", emailContentBoasVindas);
		addAttributeToEmailTemplate(emailTemplate, "contentSize", contentFormat);
		return emailTemplate.render();
	}

	public void addAttributeToEmailTemplate(ST emailTemplate, String attributeName, String attributeValue) {
		try {
			emailTemplate.remove(attributeName);
		} catch (Exception e) {
		}

		emailTemplate.add(attributeName, attributeValue);
	}
	public void addAttributeToEmailTemplate(ST emailTemplate, String attributeName, int attributeValue){
		try{
			emailTemplate.remove(attributeName);
		}catch(Exception e){
		}
		emailTemplate.add(attributeName, attributeValue);
	}
}
