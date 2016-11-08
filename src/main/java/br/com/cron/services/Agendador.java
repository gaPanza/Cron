package br.com.cron.services;
/*
 * Class by Gabriel Panza 08/11
 */
import br.com.cron.resources.Tarefa;
import br.com.cron.util.JavaMail;

public class Agendador implements Runnable {

	JavaMail sendEmail = new JavaMail();

	long id;
	boolean type;

	public long getId(Tarefa t) {
		return this.id = t.getId();

	}

	public boolean getPlune(Tarefa t) {
		return this.type = t.getPlune();
	}

	public void run() {
		System.out.println("Atenção> Tarefa em andamento.");

		if (type) {
			try {
				br.com.cron.plune.Services.ClientWS.initialize();
			} catch (Exception e) {
				System.out.println("Email não enviado");
				e.printStackTrace();
			}

		} else {

			// Enviar email

			try {

				sendEmail.postEmail(id);
			} catch (Exception e) {
				System.out.println("Email não enviado.");
				e.printStackTrace();
			}
		}
	}
}
