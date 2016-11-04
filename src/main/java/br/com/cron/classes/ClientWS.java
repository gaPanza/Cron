package br.com.cron.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
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
import br.com.cron.controller.Builder;
import br.com.cron.enums.StatusEnum;
import br.com.cron.resources.Emailspedido;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.*;
import br.com.cron.util.JavaMail;

public class ClientWS {
	JavaMail sendEmail = new JavaMail();

	static Logger log = Logger.getLogger(ClientWS.class.getName());

	public static void initialize() {
		String url = "https://ipnet.plune.com.br/JSON/Venda.PedidoItem/Browse?&_AuthToken=Ultra.Users:100-29-3822329&_Venda.PedidoItem.BrowseSequence=Id,29d7b0266a52eedb3fbd8af632fc7c16%23StatusPedido,29d7b0266a52eedb3fbd8af632fc7c16%23RepresentanteId,29d7b0266a52eedb3fbd8af632fc7c16%23Id,579e2d75a12f6766438b7350f27500ee%23NomRazaoSocial,x1_Dominio,x1_ContatoTecnicoId,x1_EmailTecnico,x1_TelefoneTecnico,x1_GerenteProjetoId,x1_GerenteProjetoEmail,x1_GerenteProjetoTelefone,x991_Id,ProdutoId,Quantidade,29d7b0266a52eedb3fbd8af632fc7c16%23TipoContratoId,x1_NivelAcompanhamento,x1_Documento,579e2d75a12f6766438b7350f27500ee%23CEPPrincipal,579e2d75a12f6766438b7350f27500ee%23PaisPrincipalId,579e2d75a12f6766438b7350f27500ee%23UFPrincipalId,579e2d75a12f6766438b7350f27500ee%23CidadePrincipalId,579e2d75a12f6766438b7350f27500ee%23BairroPrincipal,579e2d75a12f6766438b7350f27500ee%23EnderecoPrincipal,579e2d75a12f6766438b7350f27500ee%23NumeroPrincipal,579e2d75a12f6766438b7350f27500ee%23ComplementoPrincipal&_Venda.PedidoItem.BrowseLimit=10000&_Venda.PedidoItem.Order=%22Venda%22.%22PedidoItem%22.%2229d7b0266a52eedb3fbd8af632fc7c16#Id\"&__debug__=1";
		// Passa a string URL e transforma em uma conexao http
		try {
			sendGet(url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ClientWS.shotEmails();
		} catch (MessagingException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	// Logica para ler o http ou o arquivo json e persistir no banco
	public static void sendGet(String chamadaWS) throws IOException {
		File dir = new File(System.getenv("APPDATA") + "\\Cron");
		if (!dir.exists()) {
			dir.mkdir();
		}

		URL url = new URL(chamadaWS);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Content-Type", "application/json");
		int responseCode = con.getResponseCode();
		log.log(Level.INFO,
				"\n_____________________________________________________________________________\n"
						+ "|                                                                             |\n"
						+ "|                    INICIO DA VERIFICAÇÃO DA VIEW PLUNE                      |\n"
						+ "|_____________________________________________________________________________|\n");

		log.log(Level.INFO, "Enviando requisição para a URL: " + url);
		log.log(Level.INFO, "Response Code : " + responseCode);

		try {
			con.connect();
			// BufferedReader in = new BufferedReader(
			// new InputStreamReader(new FileInputStream("View JSON.json"),
			// "Cp1252"));
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "Cp1252"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			log.log(Level.INFO, "JSON Parseado, iniciado filtro");

			// Filtrando o JSON

			String string = response.toString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(string);
			JsonNode data = rootNode.get("data");
			JsonNode row = data.get("row");
			JsonNode getRow = row.get(1);
			ArrayList<PedidosPluneDTO> sendRequests = new ArrayList<PedidosPluneDTO>();

			int newRequests = 0;

			/*
			 * Propriedades do JSON e de um Pedido Plune Individual:
			 * Inicialmente no JSON tem 6588 Pedidos; Cada pedido tem 37
			 * propriedades, com subpropriedades; Em geral as propriedades tem 2
			 * subpropriedades, Value & Resolved.
			 */

			/*
			 * Persistir os pedidos novos verificando na base de dados.
			 * Verificar nos pedidos novos quais necessitam de envio e quais nao
			 * Nos que não necessitam de envio colocar estado não enviado. Nos
			 * que necessitam de envio colocar estado pendente.
			 *
			 * Ao executar, verificar o estado pendente dos pedidos. Nos que
			 * possuem estado Pendente filtrar por empresa. Depois filtrar por
			 * vendedor ipnet. Depois concatenar os pedidos. Realizar o envio.
			 * 
			 * 
			 */
			int i = 1;
			while (row.get(i) != null) {
				getRow = row.get(i);
				PedidosPluneDTO x = new PedidosPluneDTO();
				for (int j = 1; j < 38; j++) {
					x = getField(j, x, getRow);
				}

				String k = PluneDAO.getInstance().persist(x);

				if (k == null) {
					log.log(Level.WARN, "Pedido de ID : " + x.get_32fc7c16_IdResolved() + " não persistido");
				} else {
					log.log(Level.INFO, "Pedido de ID : " + x.get_32fc7c16_IdResolved() + " persistido");
					newRequests++;
					sendRequests.add(x);
				}
				i++;
			}

			log.log(Level.INFO, "Foram Adicionados : " + newRequests + " novos Pedidos.");
			log.log(Level.INFO,
					"\n_____________________________________________________________________________\n"
							+ "|                                                                             |\n"
							+ "|             FIM DO RECEBIMENTO DE ATUALIZAÇÕES DA VIEW PLUNE                |\n"
							+ "|_____________________________________________________________________________|\n");

		} catch (Exception e) {
			log.log(Level.FATAL, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void postEmail(SendToCompanyDTO send) throws MessagingException, URISyntaxException, IOException {

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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ipnetcron@gmail.com"));
			String emails = null;
			emails = TarefaDAO.getInstance().listEmailsById(send.getStatusId());

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails));
			message.setSubject("Pedidos Plune");

			BodyPart messageBodyPart = new MimeBodyPart();
			StringBuffer x = new StringBuffer();

			x.append("Novo Pedido na Base Plune!");
			x.append("<tr><font color=\"green\"><b>" + ((send.getNameUser() == null || send.getNameUser() == "null")
					? "Usuário IPNET: Não há" : "Usuário IPNET: " + send.getNameUser()) + "</b></font></tr>");
			x.append("<tr><font color=\"red\"><b>" + ((send.getPedidos() == null || send.getPedidos() == "null")
					? "Pedidos: Não há" : "Pedidos: " + send.getPedidos()) + "</b></font></tr>");
			x.append("<tr>" + ((send.getNameCompany() == null || send.getNameCompany() == "null") ? "Empresa: Não há"
					: "Empresa: " + send.getNameCompany()) + "</tr>");
			x.append("<tr>" + ((send.getDominio() == null || send.getDominio() == "null") ? "Dominio: Não há"
					: "Dominio: " + send.getDominio()) + "</tr>");
			x.append("<tr>" + ((send.getContatoTecnico() == null || send.getContatoTecnico() == "null")
					? "Contato Técnico: Não há" : "Contato Técnico: " + send.getContatoTecnico()) + "</tr>");
			x.append("<tr>" + ((send.getEmailContatoTecnico() == null || send.getEmailContatoTecnico() == "null")
					? "Email Contato Técnico: Não há" : "Email Contato Técnico: " + send.getEmailContatoTecnico())
					+ "</tr>");
			x.append("<tr>" + ((send.getTelefoneContatoTecnico() == null || send.getTelefoneContatoTecnico() == "null")
					? "Tel. Contato Técnico: Não há" : "Tel. Contato Técnico: " + send.getTelefoneContatoTecnico())
					+ "</tr>");
			x.append("<tr>"
					+ ((send.getContatoGerencial() == null || send.getContatoGerencial() == "null")
							? "Contato Gerencial: Não há" : "Contato Gerencial: " + send.getContatoGerencial())
					+ "</tr>");
			x.append("<tr>" + ((send.getEmailContatoGerencial() == null || send.getEmailContatoGerencial() == "null")
					? "Email Contato Gerencial: Não há" : "Email Contato Gerencial: " + send.getEmailContatoGerencial())
					+ "</tr>");
			x.append("<tr>"
					+ ((send.getTelefoneContatoGerencial() == null || send.getTelefoneContatoGerencial() == "null")
							? "Tel. Contato Gerencial: Não há"
							: "Tel. Contato Gerencial: " + send.getTelefoneContatoGerencial())
					+ "</tr>");
			
			ArrayList<SendToCompanyRequests> conc = send.getPedido();
			
			for (int i = 0; i < conc.size(); i++) {
				x.append("<tr><font color=\"red\">" + "Pedido: " + (conc.get(i).getPedido() + "</tr>"));
				x.append("<tr>" + "Quantidade: " + (conc.get(i).getQtd() + "</tr>"));
				x.append("<tr>" + "Tipo de Pedido: " + (conc.get(i).getTipo() + "</tr>"));
			}
			x.append("<tr>" + ((send.getLinkPropostaComercial() == null || send.getLinkPropostaComercial() == "null")
					? "Link Proposta Comercial: Não há" : "Link Proposta Comercial: " + send.getLinkPropostaComercial())
					+ "</tr>");
			x.append("<tr>" + ((send.getEndereco() == null || send.getEndereco() == "null") ? "Endereço: Não há"
					: "Endereço: " + send.getEndereco()) + "</tr>");
			x.append("<tr>"
					+ ((send.getCep() == null || send.getCep() == "null") ? "CEP: Não há" : "CEP: " + send.getCep())
					+ "</tr>");
			x.append("<tr>" + ((send.getEstado() == null || send.getEstado() == "null") ? "Estado: Não há"
					: "Estado: " + send.getEstado()) + "</tr>");
			x.append("<tr>" + ((send.getCidade() == null || send.getCidade() == "null") ? "Cidade: Não há"
					: "Cidade: " + send.getCidade()) + "</tr>");

			messageBodyPart.setContent(x.toString(), "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

			log.log(Level.INFO, "Email enviado:\n" + send.getPedidos());

		} catch (MessagingException e) {
			log.log(Level.WARN, "Email não enviado.");
			throw new RuntimeException(e);
		}

	}

	public static void shotEmails() throws MessagingException, URISyntaxException, IOException {
		File dir = new File(System.getenv("APPDATA") + "\\Cron");
		if (!dir.exists()) {
			dir.mkdir();
		}
		log.log(Level.INFO,
				"\n_____________________________________________________________________________\n"
						+ "|                                                                             |\n"
						+ "|                    INICIO DO PROCESSO DE ENVIO DE EMAILS                    |\n"
						+ "|_____________________________________________________________________________|\n");

		ArrayList<PedidosPluneDTO> pending = PluneDAO.getInstance().chkPending();
		log.log(Level.INFO, pending.size() + " Estão com o status PENDENTE e o envio será realizado");
		log.log(Level.INFO, "Iniciando o Filtro por usuário IPNET");

		ArrayList<String> users = new ArrayList<String>();

		for (PedidosPluneDTO x : pending) {
			if (!(users.contains(x.getRepresentanteIdResolved())))
				users.add(x.getRepresentanteIdResolved());
		}

		ArrayList<ArrayList<PedidosPluneDTO>> filtredByUsers = new ArrayList<ArrayList<PedidosPluneDTO>>();

		List<String> status = Arrays.asList("15", "25", "10", "12", "17", "16", "19");
		for (int i = 0; i < users.size(); i++) {
			for (int j = 0; j < status.size(); j++) {
				if (!(PluneDAO.getInstance().chkStatus(users.get(i), status.get(j))).isEmpty()) {
					filtredByUsers.add(PluneDAO.getInstance().chkStatus(users.get(i), status.get(j)));
				}
			}
		}

		// Varias listas com um usuario cada
		// Concatenar por empresa e definir o envio sendToCompany
		System.out.println(filtredByUsers.size());

		ArrayList<SendToCompanyDTO> sends = new ArrayList<SendToCompanyDTO>();

		for (int i = 0; i < filtredByUsers.size(); i++) {
			ArrayList<PedidosPluneDTO> x = filtredByUsers.get(i);
			SendToCompanyDTO send = new SendToCompanyDTO();
			ArrayList<SendToCompanyRequests> conc = new ArrayList<SendToCompanyRequests>();
			PedidosPluneDTO pedido = x.get(0);

			send.setNameCompany(pedido.getNomRazaoSocialResolved());
			send.setNameUser(pedido.getRepresentanteIdResolved());
			send.setDominio(pedido.getX1_DominioResolved());
			send.setContatoTecnico(pedido.getX1_ContatoTecnicoIdResolved());
			send.setEmailContatoTecnico(pedido.getX1_EmailTecnicoResolved());
			send.setTelefoneContatoTecnico(pedido.getX1_TelefoneTecnicoResolved());
			send.setContatoGerencial(pedido.getX1_GerenteProjetoIdResolved());
			send.setEmailContatoGerencial(pedido.getX1_GerenteProjetoEmailResolved());
			send.setTelefoneContatoGerencial(pedido.getX1_GerenteProjetoTelefoneResolved());
			send.setLinkPropostaComercial(pedido.getX1_DocumentoResolved());
			send.setEndereco(pedido.getEnderecoPrincipalResolved());
			send.setCep(pedido.getCepPrincipalResolved());
			send.setEstado(pedido.getUfPrincipalIdResolved());
			send.setCidade(pedido.getCidadePrincipalIdResolved());
			send.setStatusId(pedido.getStatusPedidoValue());

			String pedidos = new String();
			ArrayList<String> requests = new ArrayList<String>();

			for (int j = 0; j < x.size(); j++) {
				if (j == 0) {
					pedidos = x.get(j).get_32fc7c16_IdResolved() + " " + x.get(j).getDescricaoResolved();
					requests.add(pedidos);
				} else if (j != 0) {
					pedidos = pedidos + " - " + x.get(j).get_32fc7c16_IdResolved() + " - "
							+ x.get(j).getDescricaoResolved();
					requests.add(x.get(j).get_32fc7c16_IdResolved() + " - " + x.get(j).getDescricaoResolved());
				}

			}
			// Filling the CompanyRequests.java (---- NEW ---- v1.1)
			for (int h = 0; h < requests.size(); h++) {
				conc.add(new SendToCompanyRequests(requests.get(h), x.get(h).getQuantidadeResolved(),
						x.get(h).getTipoContratoIdResolved()));
			}
			send.setPedido(conc);
			send.setPedidos(pedidos);
			sends.add(send);

		}
		for (SendToCompanyDTO h : sends) {
			postEmail(h);
		}
		PluneDAO.getInstance().modifySts();

		log.log(Level.INFO,
				"\n_____________________________________________________________________________\n"
						+ "|                                                                             |\n"
						+ "|                     FIM DO PROCESSO DE ENVIO DE EMAILS                      |\n"
						+ "|_____________________________________________________________________________|\n");

		Builder.main(null);

	}

	// Utilizada pela sendGet
	private static PedidosPluneDTO getField(int i, PedidosPluneDTO x, JsonNode getRow) {
		JsonNode getSubRow, value, resolved;
		switch (i) {
		case 1:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#ComplementoPrincipal");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setComplementoPrincipalValue(value.asText());
				x.setComplementoPrincipalResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setComplementoPrincipalResolved(null);
			}
			return x;
		case 2:
			try {
				getSubRow = getRow.findValue("x1_Documento");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_DocumentoValue(value.asText());
				x.setX1_DocumentoResolved(resolved.asText());
				return x;
			} catch (NullPointerException e) {
				x.setX1_DocumentoResolved(null);
			}
		case 3:
			try {
				getSubRow = getRow.findValue("ClienteId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setClienteIdValue(value.asText());
				x.setClienteIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setClienteIdResolved(null);
			}
			return x;
		case 4:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#BranchId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.set_2fc7c16_BranchIdValue(value.asText());
				x.set_2fc7c16_BranchIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.set_2fc7c16_BranchIdResolved(null);
			}
			return x;

		case 5:
			try {
				getSubRow = getRow.findValue("x1_NivelAcompanhamento");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_NivelAcompanhamentoValue(value.asText());
				x.setX1_NivelAcompanhamentoResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_NivelAcompanhamentoResolved(null);
			}
			return x;

		case 6:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#Id");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.set_32fc7c16_IdValue(value.asText());
				x.set_32fc7c16_IdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.set_32fc7c16_IdResolved(null);
			}
			return x;

		case 7:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#CidadePrincipalId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setCidadePrincipalIdValue(value.asText());
				x.setCidadePrincipalIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setCidadePrincipalIdResolved(null);
			}
			return x;

		case 8:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#RepresentanteId");
				value = getSubRow.findValue("value");
				x.setRepresentanteIdValue(value.asText());
				resolved = getSubRow.findValue("resolved");
				x.setRepresentanteIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setRepresentanteIdResolved(null);
			}
			return x;

		case 9:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#ClienteId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setClienteIdValue(value.asText());
				x.setClienteIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setClienteIdResolved(null);
			}
			return x;

		case 10:
			try {
				getSubRow = getRow.findValue("PedidoId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setPedidoIdValue(value.asText());
				x.setPedidoIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setPedidoIdResolved(null);
			}
			return x;

		case 11:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#EnderecoPrincipal");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setEnderecoPrincipalValue(value.asText());
				x.setEnderecoPrincipalResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setEnderecoPrincipalResolved(null);
			}
			return x;

		case 12:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#CEPPrincipal");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setCepPrincipalValue(value.asText());
				x.setCepPrincipalResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setCepPrincipalResolved(null);
			}
			return x;

		case 13:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#NomRazaoSocial");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setNomRazaoSocialValue(value.asText());
				x.setNomRazaoSocialResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setNomRazaoSocialResolved(null);
			}
			return x;

		case 14:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#TipoContratoId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setTipoContratoIdValue(value.asText());
				x.setTipoContratoIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setTipoContratoIdResolved(null);
			}
			return x;

		case 15:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#Descricao");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setDescricaoValue(value.asText());
				x.setDescricaoResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setDescricaoResolved(null);
			}
			return x;

		case 16:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#NumeroPrincipal");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setNumeroPrincipalValue(value.asText());
				x.setNumeroPrincipalResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setNumeroPrincipalResolved(null);
			}
			return x;

		case 17:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#ParceiroId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setParceiroIdValue(value.asText());
				x.setParceiroIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setParceiroIdResolved(null);
			}
			return x;

		case 18:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#UFPrincipalId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setUfPrincipalIdValue(value.asText());
				x.setUfPrincipalIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setUfPrincipalIdResolved(null);
			}
			return x;

		case 19:
			try {
				getSubRow = getRow.findValue("x1_TelefoneTecnico");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_TelefoneTecnicoValue(value.asText());
				x.setX1_TelefoneTecnicoResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_TelefoneTecnicoResolved(null);
			}
			return x;

		case 20:
			try {
				getSubRow = getRow.findValue("ProdutoId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setProdutoIdValue(value.asText());
				x.setProdutoIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setProdutoIdResolved(null);
			}
			return x;

		case 21:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#StatusPedido");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setStatusPedidoValue(value.asText());
				x.setSendingStatus(statusDefine(value.asInt()));
				x.setStatusPedidoResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setStatusPedidoResolved(null);
			}

			return x;

		case 22:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#BairroPrincipal");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setBairroPrincipalValue(value.asText());
				x.setBairroPrincipalResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setBairroPrincipalResolved(null);
			}
			return x;

		case 23:
			try {
				getSubRow = getRow.findValue("x991_Id");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX991_IdValue(value.asText());
				x.setX991_IdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX991_IdResolved(null);
			}
			return x;

		case 24:
			try {
				getSubRow = getRow.findValue("_g.canUpdate");
				resolved = getSubRow;
				x.set_g_canUpdateResolved((resolved.asText()));
			} catch (NullPointerException e) {
				x.set_g_canUpdateResolved(null);
			}
			return x;

		case 25:
			try {
				getSubRow = getRow.findValue("x1_GerenteProjetoEmail");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_GerenteProjetoEmailValue(value.asText());
				x.setX1_GerenteProjetoEmailResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_GerenteProjetoEmailResolved(null);
			}
			return x;

		case 26:
			try {
				getSubRow = getRow.findValue("CompanyId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setCompanyIdValue(value.asText());
				x.setCompanyIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setCompanyIdResolved(null);
			}
			return x;

		case 27:
			try {
				getSubRow = getRow.findValue("Id");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setIdValue(value.asText());
				x.setIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setIdResolved(null);
			}
			return x;

		case 28:
			try {
				getSubRow = getRow.findValue("x1_ContatoTecnicoId");
				resolved = getSubRow.findValue("resolved");
				x.setX1_ContatoTecnicoIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_ContatoTecnicoIdResolved(null);
			}
			return x;

		case 29:
			try {
				getSubRow = getRow.findValue("x1_GerenteProjetoId");
				resolved = getSubRow.findValue("value");
				x.setX1_GerenteProjetoIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_GerenteProjetoIdResolved(null);
			}
			return x;

		case 30:
			try {
				getSubRow = getRow.findValue("29d7b0266a52eedb3fbd8af632fc7c16#CompanyId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.set_32fc7c16_CompanyIdValue(value.asText());
				x.set_32fc7c16_CompanyIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.set_32fc7c16_CompanyIdResolved(null);
			}
			return x;

		case 31:
			try {
				getSubRow = getRow.findValue("Quantidade");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setQuantidadeValue(value.asText());
				x.setQuantidadeResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setQuantidadeResolved(null);
			}
			return x;

		case 32:
			try {
				getSubRow = getRow.findValue("_g.canDelete");
				resolved = getSubRow;
				x.set_g_canDelete((resolved.asText()));
			} catch (NullPointerException e) {
				x.set_g_canDelete(null);
			}
			return x;

		case 33:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#PaisPrincipalId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setPaisPrincipalIdValue(value.asText());
				x.setPaisPrincipalIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setPaisPrincipalIdResolved(null);
			}
			return x;

		case 34:
			try {
				getSubRow = getRow.findValue("BranchId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setBranchIdValue(value.asText());
				x.setBranchIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setBranchIdResolved(null);
			}
			return x;

		case 35:
			try {
				getSubRow = getRow.findValue("579e2d75a12f6766438b7350f27500ee#EmpresaId");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setEmpresaIdValue(value.asText());
				x.setEmpresaIdResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setEmpresaIdResolved(null);
			}
			return x;

		case 36:
			try {
				getSubRow = getRow.findValue("x1_EmailTecnico");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_EmailTecnicoValue(value.asText());
				x.setX1_EmailTecnicoResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_EmailTecnicoResolved(null);
			}
			return x;

		case 37:
			try {
				getSubRow = getRow.findValue("x1_Dominio");
				value = getSubRow.findValue("value");
				resolved = getSubRow.findValue("resolved");
				x.setX1_DominioValue(value.asText());
				x.setX1_DominioResolved(resolved.asText());
			} catch (NullPointerException e) {
				x.setX1_DominioResolved(null);
			}
			return x;

		}
		return null;

	}

	private static String statusDefine(int status) {

		if (status == 18 || status == 24 || status == 13 || status == 23 || status == 11) {
			return StatusEnum.NAOENVIADO.toString();
		} else if (status == 15 || status == 25 || status == 10 || status == 17 || status == 16 || status == 19) {
			return StatusEnum.PENDENTE.toString();
		} else if (status == 12) {
			return StatusEnum.PENDENTE.toString();
		}

		return StatusEnum.NAOENVIADO.toString();
	}

	public static void populatedB(String chamadaWS) throws IOException {
		File dir = new File(System.getenv("APPDATA") + "\\Cron");
		if (!dir.exists()) {
			dir.mkdir();
		}

		URL url = new URL(chamadaWS);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Content-Type", "application/json");
		int responseCode = con.getResponseCode();
		log.log(Level.INFO,
				"\n_____________________________________________________________________________\n"
						+ "|                                                                             |\n"
						+ "|                     CONFIGURANDO CRON PELA PRIMEIRA VEZ                     |\n"
						+ "|_____________________________________________________________________________|\n");

		log.log(Level.INFO, "Enviando requisição para a URL: " + url);
		log.log(Level.INFO, "Response Code : " + responseCode);

		try {
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "Cp1252"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			log.log(Level.INFO, "JSON Parseado, iniciado filtro");

			String string = response.toString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(string);
			JsonNode data = rootNode.get("data");
			JsonNode row = data.get("row");
			JsonNode getRow = row.get(1);
			ArrayList<PedidosPluneDTO> sendRequests = new ArrayList<PedidosPluneDTO>();

			int newRequests = 0;

			int i = 1;
			while (row.get(i) != null) {
				getRow = row.get(i);
				PedidosPluneDTO x = new PedidosPluneDTO();
				for (int j = 1; j < 38; j++) {
					x = getField(j, x, getRow);
					x.setSendingStatus(StatusEnum.NAOENVIADO.toString());
				}

				String k = PluneDAO.getInstance().persist(x);

				if (k == null) {
					log.log(Level.WARN, "Pedido de ID : " + x.get_32fc7c16_IdResolved() + " não persistido");
				} else {
					log.log(Level.INFO, "Pedido de ID : " + x.get_32fc7c16_IdResolved() + " persistido");
					newRequests++;
					sendRequests.add(x);
				}
				i++;
			}

			log.log(Level.INFO, "Foram Adicionados : " + newRequests + " novos Pedidos.");
			log.log(Level.INFO,
					"\n_____________________________________________________________________________\n"
							+ "|                                                                             |\n"
							+ "|                 FIM DA PRIMEIRA CONFIGURAÇÃO DO CRON - PLUNE                |\n"
							+ "|_____________________________________________________________________________|\n");

		} catch (Exception e) {
			log.log(Level.FATAL, e.getMessage());
			e.printStackTrace();
		}
	}

	public static void populateMail() {
		log.log(Level.INFO, "Populando banco de emails");
		ArrayList<String> status = new ArrayList<String>(
				Arrays.asList("18", "15", "11", "25", "24", "13", "23", "10", "12", "17", "16", "19", "27", "null"));
		String email = "";
		for (String x : status) {
			switch (x) {
			case ("18"):
				email = "";
				break;
			case ("15"):
				email = "tozzi@ipnetsolucoes.com.br,david@ipnetsolucoes.com.br";
				break;
			case ("11"):
				email = "";
				break;
			case ("25"):
				email = "tozzi@ipnetsolucoes.com.br,david@ipnetsolucoes.com.br";
				break;
			case ("24"):
				email = "";
				break;
			case ("13"):
				email = "";
				break;
			case ("23"):
				email = "";
				break;
			case ("10"):
				email = "renovações@ipnetsoluções.com.br";
				break;
			case ("12"):
				email = "renovações@ipnetsoluções.com.br";
				break;
			case ("17"):
				email = "renovações@ipnetsoluções.com.br";
				break;
			case ("16"):
				email = "tozzi@ipnetsolucoes.com.br,david@ipnetsolucoes.com.br";
				break;
			case ("19"):
				email = "tozzi@ipnetsolucoes.com.br,david@ipnetsolucoes.com.br";
				break;
			case ("27"):
				email = "";
				break;
			case ("null"):
				email = "";
				break;
			}
			Emailspedido newStatus = new Emailspedido(x, email);
			boolean persisted = PluneDAO.getInstance().fillEmails(newStatus);
			if (persisted)
				log.log(Level.INFO, "Status: " + x + " Persistido com sucesso para os emails: " + email);
			else if (!persisted) {
				log.log(Level.WARN, "Status: " + x + " NÃO PERSISTIDO");
			}

		}
	}
}
