package br.com.infoq.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.infoq.model.Usuario;
import lombok.Data;

@Service
@Data
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsuarioSessao {
	private Usuario usuario;
}
