package br.com.cron.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json2JavaString {

	public JsonObject json;

	//Cria um objeto JSON parseado com a string do HTML provided
	public Json2JavaString(InputStream inputStream) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		String jsonText = readAll(rd);
		JsonParser jsonParser = new JsonParser();
		json = (JsonObject) jsonParser.parse(jsonText);
	}
	
	//Extrai o JSON simplesmente e retorna
	public static JsonObject extractJson(InputStream inputStream) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		String jsonText = readAll(rd);
		JsonParser jsonParser = new JsonParser();
		return (JsonObject) jsonParser.parse(jsonText);
	}
	
	//Le e apende numa string a pagina
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
