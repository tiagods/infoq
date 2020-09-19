package br.com.infoq.utils;

import java.util.Optional;

import javax.swing.JOptionPane;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.infoq.model.Endereco;

public class EnderecoUtil {
		
	public static Optional<Endereco> pegarCEP(String cep){
        String url = "http://viacep.com.br/ws/"+ cep +"/json";
        Endereco payload = getJSONFromUrl(url);
        if(payload!=null) return Optional.ofNullable(payload);
        else return Optional.empty();
    }
	
	public static void main(String[] args){
        Optional<Endereco> result = pegarCEP("01319000");
        System.out.println(result.isPresent()?result.get().toString():"Nao trouxe nada");
    }
	
	private static Endereco getJSONFromUrl(String urli) {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Endereco> response = rest.getForEntity(urli, Endereco.class);
		if(response.getStatusCode()==HttpStatus.OK) {
			if(response.getBody().isErro()){
				JOptionPane.showMessageDialog(null, "Cep nao encontrado");
				return null;
			}
			return response.getBody();
		}
		else if (response.getStatusCode()==HttpStatus.BAD_REQUEST){
			JOptionPane.showMessageDialog(null, "Verique os campos informados");
		}
		else {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro de conexao com a internet");
		}
		return null;
    }
}
