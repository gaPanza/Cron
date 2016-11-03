package br.com.cron.classes;

import br.com.cron.DAO.TarefaDAO;

import br.com.cron.resources.Usuario;

//Classe criada para popular os dados de usuarios da database da google para o postgre local.

public class PopulateDatabase {

	public static void main(String[] args) {

		String filename = Names.names();
		filename = filename.trim();
		System.out.println(filename.length());

		int x = 0;

		for (int i = 2; i < filename.length(); i++) {
			Usuario newEntry = new Usuario();
			if (Character.toString(filename.charAt(i)).equals(".")) {
				if (filename.substring(i, i + 3).contains("br")) {
					String email = filename.substring(x, i + 3);
					newEntry.setEmail(email);
					System.out.println(email);
					for (int j = 0; j < filename.length(); j++) {
						if (Character.toString(email.charAt(j)).equals(".")
								|| Character.toString(email.charAt(j)).equals("@")) {
							String nome = email.substring(0, j);
							System.out.println(nome);
							j = filename.length();
							newEntry.setName(nome);
						}
					}
					x = i + 3;
					i = i + 3;

				}
				try{
				if (!newEntry.getName().equals(null))
					TarefaDAO.getInstance().addUser(newEntry);
				}catch (Exception e){
					
				}
			}

		}
	}

}
