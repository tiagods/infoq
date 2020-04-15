/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import br.com.infoq.model.Usuario;
import br.com.infoq.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tiagods
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    
    public Optional<Usuario> validarLoginESenha(String login, String senha){
        return repository.findByLoginAndSenha(login, senha);
    }
}
