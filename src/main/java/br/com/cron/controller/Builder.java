package br.com.cron.controller;

import java.io.IOException;
import java.util.*;
import br.com.cron.DAO.TarefaDAO;
import br.com.cron.classes.PedidosPluneDTO;
import br.com.cron.classes.PluneDAO;
import br.com.cron.resources.Emailspedido;
import br.com.cron.resources.Tarefa;
import br.com.cron.services.Agendador;
import br.com.cron.util.Initial;
import br.com.cron.util.MenuUtil;
import it.sauronsoftware.cron4j.Scheduler;

public class Builder {
	static List<Tarefa> tarefaList = new ArrayList<Tarefa>();
	static Scanner scanner = new Scanner(System.in);
	static List<Scheduler> schedulerList = new ArrayList<Scheduler>();

	public static void main(String[] args) {
		// schedule();
		TarefaDAO.getInstance();
		try {
			int iterator = 0;
			while (iterator != 20) {
				iterator = 0;
				System.out.println("\n_____________________________________________________________________________\n"
						+ "|                                                                             |\n"
						+ "|                                MENU CRON                                    |\n"
						+ "|_____________________________________________________________________________|\n");
				System.out.println("1- Visualizar Tarefas");
				System.out.println("2- Adicionar  Tarefas");
				System.out.println("3- Remover    Tarefas");
				System.out.println("4- Modificar  Tarefas");
				System.out.println("5- Modificar  PLUNE");

				iterator = scanner.nextInt();

				switch (iterator) {
				case 1:
					scanner.nextLine();
					consultar();
					break;
				case 2:
					scanner.nextLine();
					adicionar();
					schedule();
					break;
				case 3:
					scanner.nextLine();
					remover();
					break;
				case 4:
					scanner.nextLine();
					modificar();
					break;
				case 5:
					scanner.nextLine();
					menuPlune();
					break;
				default:
					System.out.println("Opção inválida");
				}
			}
		} catch (Exception e) {
		}

	}

	public static void adicionar() {

		TarefaDAO.getInstance();
		Tarefa tarefa = new Tarefa();
		int opcao = br.com.cron.util.Initial.OPX(tarefa); // Puxa OPX da
															// classe
															// Initial

		// Logica para Tarefa por dia
		if (opcao == 1) {
			br.com.cron.util.Initial.OP1(tarefa); // Puxa OP1 da classe
													// Initial
		}

		// Logica para Tempo Fixo

		else if (opcao == 2) {
			br.com.cron.util.Initial.OP2(tarefa); // Puxa OP2 da classe
													// Initial
		}

		// Logica para Dia Fixo

		else if (opcao == 3) {
			br.com.cron.util.Initial.OP3(tarefa); // Puxa OP3 da classe
													// Initial
		}
	}

	public static void consultar() {
		br.com.cron.util.MenuUtil.listar();
	}

	public static void remover() {
		consultar();
		System.out.println("Digite a ID da tarefa a ser removida.");
		TarefaDAO.getInstance().removeById(scanner.nextLong());
		System.out.println("Tarefa removida com sucesso.");
		scanner.nextLine();
	}

	public static void modificar() { // Não funcional.(necessário esforço de
										// 2hs)
		// consultar();
		// System.out.println("Digite a id da tarefa a ser modificada.");
		// int option = 0;
		// Long id = scanner.nextLong();
		// Tarefa x = TarefaDAO.getInstance().getById(id);
		// if (x != null) {
		// System.out.println("O que deve ser modificado:\n 1 - Descrição\n 2 -
		// Email \n 3 - Data do alerta\n");
		// option = scanner.nextInt();
		// }
		//
		// if (option == 1) {
		// System.out.println("Digite uma nova descrição: ");
		// scanner.nextLine();
		// String description = scanner.nextLine();
		// x.setDescricao(description);
		// TarefaDAO.getInstance().merge(x);
		// System.out.println("Tarefa modificada com sucesso.");
		// }
		//
		// else if (option == 2) {
		// System.out.println(
		// "O que deseja fazer: \n 1 - Adicionar outro endereço de email. \n 2 -
		// Substituir o endereço de email atual.");
		// scanner.nextLine();
		// int emailOption = scanner.nextInt();
		//
		// if (emailOption == 1) {
		// List<String> taskEmail = new ArrayList<String>();
		// boolean it = true;
		// while (it) {
		// taskEmail = x.getEmail();
		// System.out.println("Digite o endereço de email");
		// scanner.nextLine();
		// String newEmail = scanner.nextLine();
		// taskEmail.add(newEmail);
		// System.out.println("Deseja adicionar outro email?\nY/N");
		// String opt = scanner.nextLine().trim().toLowerCase();
		// if (opt.equals("n")) {
		// it = false;
		// } else if (opt.equals("y")) {
		// it = true;
		// } else {
		// System.out.println("Caracter Inválido digite Y ou N");
		// it = false;
		// }
		// }
		// x.setEmail(taskEmail);
		// TarefaDAO.getInstance().merge(x);
		// System.out.println("Email alterado com sucesso");
		//
		// }
		// }
		//
		// else if (option == 3) {
		// System.out.println("Procurando a opção da tarefa...");
		// int taskOption = TarefaDAO.getInstance().getById(id).getOption();
		//
		// if (taskOption == 1) {
		// Calendar taskCalendar = Calendar.getInstance();
		// taskCalendar = br.com.cron.util.MenuUtil.Menu_1(taskCalendar);
		// String cronConfig = (taskCalendar.get(Calendar.MINUTE) + " " +
		// taskCalendar.get(Calendar.HOUR) + " * "
		// + "* " + taskCalendar.get(Calendar.DAY_OF_WEEK - 1));
		//
		// // x.setCronconfig(cronConfig);
		//
		// System.out.println("Data alterada com sucesso");
		//
		// }
		//
		// else if (taskOption == 2) {
		// // String cronConfig = br.com.cron.util.MenuUtil.Menu_2();
		// // x.setCronconfig(cronConfig);
		//
		// System.out.println("Data alterada com sucesso");
		// }
		//
		// else if (taskOption == 3) {
		// Calendar taskCalendar = Calendar.getInstance();
		// taskCalendar = br.com.cron.util.MenuUtil.Menu_3(taskCalendar);
		// String cronConfig = (taskCalendar.get(Calendar.MINUTE) + " " +
		// taskCalendar.get(Calendar.HOUR) + " "
		// + taskCalendar.get(Calendar.DAY_OF_MONTH) + " " +
		// taskCalendar.get(Calendar.MONTH) + " *");
		//
		// // x.setCronconfig(cronConfig);
		//
		// System.out.println("Data alterada com sucesso");
		// }
		// }
		//
	}

	public static void menuPlune() {
		int st = 1;
		while (st != 4) {
			st = 4;
			System.out.println("\n_____________________________________________________________________________\n"
					+ "|                                                                             |\n"
					+ "|                                  MENU PLUNE                                 |\n"
					+ "|_____________________________________________________________________________|\n");
			System.out.println("1- Cadastrar Tarefa");
			System.out.println("2- Deletar   Tarefa");
			System.out.println("3- Modificar Emails");
			System.out.println("4- Voltar  ao  CRON");

			st = scanner.nextInt();

			switch (st) {
			case (1):

				System.out.print("Digite as horas: ");
				int horas = scanner.nextInt();
				System.out.println();

				if (horas < 0 || horas > 24) {
					System.out.println("Hora fora da faixa");
					break;
				}

				System.out.print("Digite os minutos: ");
				int minutos = scanner.nextInt();
				System.out.println();
				if (minutos < 0 || minutos > 59) {
					System.out.println("Minutos fora da faixa");
					break;
				}

				Tarefa tarefa = new Tarefa();
				tarefa = Initial.OPP(tarefa);
				List<String> cronConf = new ArrayList<String>();
				cronConf.add(MenuUtil.Menu_Plune(horas, minutos));
				tarefa.setCronconfig(cronConf);
				TarefaDAO.getInstance().persist(tarefa);
				schedule();

				ArrayList<PedidosPluneDTO> h = PluneDAO.getInstance().checkDb();
				if (h.size() == 0) {
					try {
						br.com.cron.classes.ClientWS.populateMail();
						br.com.cron.classes.ClientWS.populatedB(
								"https://ipnet.plune.com.br/JSON/Venda.PedidoItem/Browse?&_AuthToken=Ultra.Users:100-29-3822329&_Venda.PedidoItem.BrowseSequence=Id,29d7b0266a52eedb3fbd8af632fc7c16%23StatusPedido,29d7b0266a52eedb3fbd8af632fc7c16%23RepresentanteId,29d7b0266a52eedb3fbd8af632fc7c16%23Id,579e2d75a12f6766438b7350f27500ee%23NomRazaoSocial,x1_Dominio,x1_ContatoTecnicoId,x1_EmailTecnico,x1_TelefoneTecnico,x1_GerenteProjetoId,x1_GerenteProjetoEmail,x1_GerenteProjetoTelefone,x1_Email,x1_ClientID,x1_OpportunidID,x1_Observacao,29d7b0266a52eedb3fbd8af632fc7c16%23OportunidadeId,29d7b0266a52eedb3fbd8af632fc7c16%23MotivoFechamentoId,x991_Id,ProdutoId,Quantidade,29d7b0266a52eedb3fbd8af632fc7c16%23TipoContratoId,x1_NivelAcompanhamento,x1_Documento,579e2d75a12f6766438b7350f27500ee%23CEPPrincipal,579e2d75a12f6766438b7350f27500ee%23PaisPrincipalId,579e2d75a12f6766438b7350f27500ee%23UFPrincipalId,579e2d75a12f6766438b7350f27500ee%23CidadePrincipalId,579e2d75a12f6766438b7350f27500ee%23BairroPrincipal,579e2d75a12f6766438b7350f27500ee%23EnderecoPrincipal,579e2d75a12f6766438b7350f27500ee%23NumeroPrincipal,579e2d75a12f6766438b7350f27500ee%23ComplementoPrincipal&_Venda.PedidoItem.BrowseLimit=10000&_Venda.PedidoItem.Order=%22Venda%22.%22PedidoItem%22.%22CompanyId%22&__debug__=1");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;

			case (2):
				List<Tarefa> plune = TarefaDAO.getInstance().findAllPlune();

				for (int i = 0; i < plune.size(); i++) {
					System.out.printf("ID: %d    Desc: %s             Data: %s \n", plune.get(i).getId(),
							plune.get(i).getDescricao(), plune.get(i).getCronconfig());
				}

				System.out.println("Selecione um ID para deletar ou -1 para deletar todas");
				try {
					Integer i = scanner.nextInt();
					if (i == -1) {
						TarefaDAO.getInstance().removeAll();
					} else
						TarefaDAO.getInstance().removeById(i);
					System.out.println("Tarefa(s) Removida(s) com sucesso");
				} catch (Exception e) {
					menuPlune();
				}
				break;

			case (3):
				System.out.println("A estrutura de envios está assim no momento: ");
				List<Emailspedido> x = TarefaDAO.getInstance().listemails();
				for (int i = 0; i < x.size(); i++) {
					System.out.println(x.get(i).getIdpedido() + "     " + x.get(i).getEmails());
				}
				System.out.println("Digite a ID a ser alterada ou -1 para voltar.");
				Integer opt = scanner.nextInt();
				if (opt != -1) {
					System.out.println("Digite uma nova lista de emails para o Status" + opt);
					System.out.println("Ex: gabriel.panza@ipnetsolucoes.com.br,raphael.pinheiro@ipnetsolucoes.com.br");
					scanner.nextLine();
					String newList = scanner.nextLine();

					Emailspedido newPedido = new Emailspedido(opt.toString(), newList);
					TarefaDAO.getInstance().modifyEmails(newPedido);
				}
				break;
			case (4):
				st = 4;
				break;
			default:
				System.out.println("Opção incorreta, utilize o menu novamente");
				menuPlune();
				break;
			}
		}
		Builder.main(null);
	}

	public static void schedule() {
		tarefaList = TarefaDAO.getInstance().findAll();

		for (Tarefa tarefa : tarefaList) {
			Agendador runable = new Agendador();
			runable.getId(tarefa);
			runable.getPlune(tarefa);

			for (String config : tarefa.getCronconfig()) { // Itera para pegar a
															// String de Tempo
				Scheduler scheduler = new Scheduler();
				scheduler.schedule(config, runable);
				scheduler.start();
				schedulerList.add(scheduler);

			}
		}
	}
}
