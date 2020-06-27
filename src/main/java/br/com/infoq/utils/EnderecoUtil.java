package br.com.infoq.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.infoq.model.Endereco;

public class EnderecoUtil {
	
	public static Optional<Endereco> pegarCEP(String cep){
        String url = "http://viacep.com.br/ws/"+ cep +"/json";
        JSONObject payload = getJSONFromUrl(url);
        if(payload!=null) return Optional.ofNullable(getEndereco(payload.toString()));
        else return Optional.empty();
    }
	
	public static void main(String[] args){
        String cep ="05765200";
        String url = "http://viacep.com.br/ws/"+ cep +"/json";
        JSONObject payload = null;
        try {
            payload = getJSONFromUrl(url);
        }catch (JSONException e){
            e.printStackTrace();
        }
        if(payload!=null){
            Endereco e = getEndereco(payload.toString());
            System.out.println(e.getLogradouro());
        }
        else
            System.out.println("Verifique sua conexão com a internet ou");
    }
	
	private static JSONObject getJSONFromUrl(String urli) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urli);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf8"),8192);
            StringBuilder linhas = new StringBuilder(is.available());
            reader.lines().forEach(i -> linhas.append(i.trim()));
            JSONObject json = new JSONObject(linhas.toString());
            return json;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
	 
	private static Endereco getEndereco(String value) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(value, Endereco.class);
		} catch (IOException e) {
			return null;
		}
	}
}
