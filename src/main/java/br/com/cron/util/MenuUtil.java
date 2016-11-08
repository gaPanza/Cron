package br.com.cron.util;
/*
 * Class by Gabriel Panza 08/11
 */
import java.util.Scanner;

import br.com.cron.DAO.TarefaDAO;
import br.com.cron.resources.Tarefa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

public class MenuUtil {
	static Scanner scanner = new Scanner(System.in);

	// Formato mm:HH * * DD
	public static Calendar Menu_1(Calendar calendar) {

		System.out.println("Digite o dia da semana");
		String diaDaSemana = scanner.nextLine().trim().toLowerCase();
		int diaConvertido = DayConvert(diaDaSemana);

		if (diaConvertido != -1) {
			int hora = calendar.get(Calendar.HOUR);
			int minuto = calendar.get(Calendar.MINUTE);

			try {
				System.out.println("Hora: ");
				hora = scanner.nextInt();
				if (hora >= 0 && hora <= 24) {
					System.out.println("Minuto: ");
					minuto = scanner.nextInt();
					if (minuto >= 0 && minuto <= 59) {

						// Adicionando os atributos no calendario
						calendar.set(Calendar.HOUR_OF_DAY, hora);
						calendar.set(Calendar.MINUTE, minuto);
						calendar.set(Calendar.SECOND, 0);
						calendar.set(Calendar.MILLISECOND, 0);
						int x = calendar.get(Calendar.DAY_OF_YEAR);
						calendar.set(Calendar.DAY_OF_WEEK, diaConvertido);

						int y = calendar.get(Calendar.DAY_OF_YEAR);

						// Verifica se a data atual é maior que a data
						// marcada.Se for adiciona 7 dias
						if (x > y) {
							calendar.set(Calendar.DAY_OF_YEAR, y + 7);
						}

						return calendar;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Digite um horario valido");
			}
		}
		return calendar;
	}

	// Formato mm:HH * * *
	public static List<String> Menu_2() {
		int horas = 0;
		int minutos = 0;
		List<String> x = new ArrayList<String>();
		try {
			System.out.println("Digite o intervalo de horas");
			horas = scanner.nextInt();
			if (horas >= 0 && horas < 25) {
				System.out.println("Digite o intervalo de minutos");
				minutos = scanner.nextInt();
				if (minutos >= 0 && minutos <= 59) {
					String horarioFormatado = (minutos + " " + horas + " * " + "* " + "*");
					if (horas == 0)
						horarioFormatado = (minutos + " *" + " * " + "* " + "*");
					x.add(horarioFormatado);
					return x;
				} else {
					System.out.println("Minutos fora da faixa");
					return null;
				}
			} else {
				System.out.println("Hora fora da faixa");
				return null;
			}
		} catch (InputMismatchException e) {
			System.out.println("Intervalo invalido");
			return null;
		}
	}

	// Formato mm:HH dm MM *
	public static Calendar Menu_3(Calendar calendar) {
		Calendar calenda2r = (Calendar) calendar.clone();
		try {
			System.out.println("Digite o mes 1-12");
			int mes = scanner.nextShort();
			System.out.println("Digite o dia 1-31");
			int diaMes = scanner.nextShort();

			if (mes > 0 && mes < 13) {
				if (diaMes >= 1 && diaMes <= calendar.getMaximum(Calendar.DAY_OF_MONTH)) {
					System.out.println("Horas: ");
					int hora = scanner.nextInt();
					if (hora >= 0 && hora <= 24) {
						System.out.println("Minutos: ");
						int minuto = scanner.nextInt();
						if (minuto >= 0 && minuto <= 59) {
							calendar.set(Calendar.HOUR_OF_DAY, hora);
							calendar.set(Calendar.MINUTE, minuto);
							calendar.set(Calendar.SECOND, 0);
							calendar.set(Calendar.MILLISECOND, 0);
							calendar.set(Calendar.MONTH, mes);
							calendar.set(Calendar.DAY_OF_MONTH, diaMes);

							long calendarioMarcado = calendar.getTimeInMillis();

							if (calendarioMarcado > Calendar.getInstance().getTimeInMillis()) {
								return calendar;
							} else {
								System.out.println("A data marcada é inferior a data atual");
								return calenda2r;

							}
						}
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Fora da faixa");
		}
		return calenda2r;
	}

	public static String Menu_Plune(int horas, int minutos) {
		String cronConfig = (minutos + " " + horas + " *" + " *" + " *");
		return cronConfig;
	}

	public static void listar() {
		List<Tarefa> x = (List<Tarefa>) TarefaDAO.getInstance().findAll();

		for (int i = 0; i < x.size(); i++) {
			System.out.printf("ID: %d    Desc: %s            Email: %s          Data: %s \n", x.get(i).getId(),
					x.get(i).getDescricao(), x.get(i).getEmail(), x.get(i).getCronconfig());
		}

	}

	public static int DayConvert(String a) {
		switch (a) {
		case "domingo":
			return 0;

		case "segunda":
			return 1;

		case "terca":
			return 2;

		case "quarta":
			return 3;

		case "quinta":
			return 4;

		case "sexta":
			return 5;

		case "sabado":
			return 6;

		default:
			return -1;
		}

	}
}