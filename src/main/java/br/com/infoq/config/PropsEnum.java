/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.config;

/**
 *
 * @author tiagods
 */
public enum PropsEnum {
        GERAL("application.properties");
	private String descricao;
	private PropsEnum(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
