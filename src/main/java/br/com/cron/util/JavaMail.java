package br.com.cron.util;

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.cron.DAO.TarefaDAO;
import br.com.cron.resources.Tarefa;

//Pendencias
//Checkar e fixar os protocolos de segurança do google ou habilitar o acesso de apps menos seguros.

public class JavaMail {
	public void postEmail(long id) throws MessagingException, URISyntaxException, IOException {
		Properties props = new Properties();

		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.ssl.enable", "false");
		props.setProperty("java.net.preferIPv4Stack", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ipnetcron@gmail.com", "Ipnet1807");
			}
		});

		try {
			Tarefa tarefa = TarefaDAO.getInstance().getById(id);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ipnetcron@gmail.com"));
			List<String> emails = tarefa.getEmail();
			String sendadress = emails.get(0);

			for (int i = 1; i < emails.size(); i++) {
				sendadress = sendadress + "," + emails.get(i);
			}
			System.out.println(sendadress);

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendadress));
			message.setSubject("A sua Tarefa Cron foi Ativada");

			String content = null;

			if (tarefa.getProfarma()) {
				BodyPart messageBodyPart = new MimeBodyPart();

				content = br.com.cron.util.StringTemplatev2.StringTemplateProfarma(id);

				messageBodyPart.setContent(content, "text/html; charset=utf-8");

				Multipart multipart = new MimeMultipart();

				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				String filename = "C:/Users/Gabriel.Panza/Documents/Workspace/Cron/RelatorioProfarma.xls";
				DataSource source = new FileDataSource(filename);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new File(filename).getName());
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);

			} else {
				content = br.com.cron.util.StringTemplatev1.StringTemplate(id);
				message.setContent(content, "text/html; charset=utf-8");
			}

			Transport.send(message);

			System.out.println("Email enviado com sucesso!");
			br.com.cron.controller.Builder.main(null);

		} catch (MessagingException e) {
			System.out.println("Email não enviado.");
			throw new RuntimeException(e);
		}

	}

}