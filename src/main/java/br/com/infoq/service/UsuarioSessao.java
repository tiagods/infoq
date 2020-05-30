package br.com.infoq.service;

import org.springframework.stereotype.Service;

import br.com.infoq.model.Usuario;
import lombok.Data;

@Service
@Data
public class UsuarioSessao {
	private Usuario usuario;
}
