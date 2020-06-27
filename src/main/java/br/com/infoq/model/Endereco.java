package br.com.infoq.model;

import lombok.Data;

@Data
public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private Estado uf;
    private String unidade;
    private String ibge;
    private String gia;
    
    public enum Estado {
		AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT,
		PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;
	}
}
