package br.com.cron.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.cron.DAO.TarefaDAO;

import br.com.cron.resources.Tarefa;
import br.com.cron.resources.Usuario;

public class Initial {
	static Scanner scanner = new Scanner(System.in);

	// Logica para Tarefa por Dia
	public static void OP1(Tarefa tarefa) {
		Calendar calendar = Calendar.getInstance();
		Calendar calenda2r;

		calenda2r = br.com.cron.util.MenuUtil.Menu_1((Calendar) calendar.clone());

		if (!calendar.equals(calenda2r)) {
			List<String> cronConfig = new ArrayList<String>();

			cronConfig.add((calenda2r.get(Calendar.MINUTE) + " " + calenda2r.get(Calendar.HOUR_OF_DAY) + " * " + "* "
					+ calenda2r.get(Calendar.DAY_OF_WEEK - 1)));

			System.out.println(cronConfig);
			System.out.println("Marcando o alerta para o horario especificado ...");

			// Persiste o Calendario
			tarefa.setOption(1);
			tarefa.setCronconfig(cronConfig);
			tarefa.setComunId(1);
			TarefaDAO.getInstance().merge(tarefa);

		} else {
			OP1(tarefa);
		}
	}

	// Logica para Dia Fixo

	public static void OP2(Tarefa tarefa) {
		List<String> cronConfig = new ArrayList<String>();
		cronConfig = br.com.cron.util.MenuUtil.Menu_2();
		try {
			if (!cronConfig.equals(null)) {
				System.out.println("Marcando o alerta por intervalo ...");
				tarefa.setCronconfig(cronConfig);
				tarefa.setComunId(1);
				tarefa.setOption(2);
				TarefaDAO.getInstance().merge(tarefa);
			} else {
				OP2(tarefa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Logica para Dia Fixo

	public static void OP3(Tarefa tarefa) {
		Calendar calendar = Calendar.getInstance();
		Calendar calenda2r;
		System.out.println("h");
		calenda2r = br.com.cron.util.MenuUtil.Menu_3((Calendar) calendar.clone());

		if (!calendar.equals(calenda2r)) {
			List<String> cronConfig = new ArrayList<String>();
			cronConfig.add((calenda2r.get(Calendar.MINUTE) + " " + calenda2r.get(Calendar.HOUR_OF_DAY) + " "
					+ calenda2r.get(Calendar.DAY_OF_MONTH) + " " + calenda2r.get(Calendar.MONTH) + " *"));

			// Persiste o Calendario
			tarefa.setCronconfig(cronConfig);
			tarefa.setComunId(1);
			tarefa.setOption(2);
			TarefaDAO.getInstance().merge(tarefa);

		} else {
			System.out.println("Data fora da faixa");
			OP3(tarefa);
		}
	}

	// Logica para Todas as Tarefas
	public static int OPX(Tarefa tarefa) {

		// Escolhe a Tarefa para adicionar
		int opcao = 0;
		while (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.println(
					"Digite a Tarefa para Adicionar: \n 1 - Por dia \n 2 - Por tempo fixo \n 3 - Por dia específico");
			opcao = scanner.nextInt();
		}

		// Padrão para todos
		Calendar c = Calendar.getInstance();
		Timestamp createDate = new Timestamp(c.getTimeInMillis());
		tarefa.setDataCriacao(createDate);

		System.out.println("Digite a Descrição:");
		scanner.nextLine();
		tarefa.setDescricao(scanner.nextLine());

		List<String> emails = new ArrayList<String>();
		emails = addEmail(emails);
		tarefa.setEmail(emails);
		int taskOption;

		// Adiciona o tipo
		do {
			System.out.println("Digite o tipo de Tarefa: \n1 - Genérica\n2 - Profarma\n3 - Plune");
			taskOption = scanner.nextInt();
		} while (taskOption != 1 && taskOption != 2 && taskOption != 3);
		System.out.println(taskOption);
		switch (taskOption) {
		case 1:
			tarefa.setProfarma(false);
			tarefa.setPlune(false);
			break;
		case 2:
			tarefa.setProfarma(true);
			tarefa.setPlune(false);
			break;
		case 3:
			tarefa.setProfarma(false);
			tarefa.setPlune(true);
			break;
		}
		return opcao;
	}
	
	public static Tarefa OPP(Tarefa tarefa){
		Calendar c = Calendar.getInstance();
		Timestamp createDate = new Timestamp(c.getTimeInMillis());
		
		tarefa.setPlune(true);
		tarefa.setProfarma(false);
		tarefa.setDataCriacao(createDate);
		tarefa.setOption(4);
		tarefa.setDescricao("Tarefa para consulta da base Plune e envio de emails");
		tarefa.setComunId(1);
		tarefa.setEmail(null);
		
		return tarefa;

	}
	
	
	public static List<String> addEmail(List<String> emails) {
		String email;
		boolean opt = true;
		String nome;
		boolean nameEmail = true;
		
		System.out.println("Digite seu nome");
		nome = scanner.nextLine().trim().toLowerCase();
		System.out.println(nome);
		Usuario user = TarefaDAO.getInstance().getByName(nome);
		System.out.println(user.getName());
		while (opt) {

			if (nameEmail)
				if (!user.equals(null)) {
					System.out.println("Deseja utilizar seu endereço de email: " + user.getEmail() + "?\n" + "Y/N");
					if (scanner.nextLine().trim().toLowerCase().equals("y")) {
						emails.add(user.getEmail());
						nameEmail = false;

					} else if (scanner.nextLine().trim().toLowerCase().equals("n")) {
						System.out.println("Ok! Não iremos utilizar seu endereço de email.");
						nameEmail = false;
					} else {
						System.out.println("Uau, que letra estranha. Tente usar Y ou N na proxima vez!");
						continue;
					}
				}
			
			System.out.println("Deseja adicionar outro email?\nY/N");
			String prox = scanner.nextLine().trim().toLowerCase();
			if (prox.equals("n")) {
				opt = false;
			} else if (prox.equals("y")) {
				System.out.println("Digite um endereço de email");
				email = scanner.nextLine().trim().toLowerCase();
				if (email.contains("@")) {
					emails.add(email);
					opt = true;
				} else {
					System.out.println("Algo está errado! Esse não é um email válido!");
					continue;
				}
			}
		}
		return emails;
	}
}
