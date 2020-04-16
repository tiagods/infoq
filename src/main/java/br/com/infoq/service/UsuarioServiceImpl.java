/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import br.com.infoq.exception.UsuarioInvalidCredentialsException;
import br.com.infoq.exception.UsuarioNotFoundException;
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
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;
    
    @Override
    public Usuario validarLoginESenha(String login, String senha) throws UsuarioInvalidCredentialsException{
        Optional<Usuario> user = repository.findByLoginAndSenha(login, senha);
        if(user.isPresent()) return user.get();
        else throw new UsuarioInvalidCredentialsException("Dados de acesso invalido");
    }

    @Override
    public Optional<Usuario> buscarLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Usuario adicionar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) throws UsuarioNotFoundException {
        if(verificarSeExiste(id)) return repository.findById(id);
        else throw new UsuarioNotFoundException("Usuario nao existe");
    }

    @Override
    public void alterar(Usuario usuario, Long id) throws UsuarioNotFoundException {
        if(verificarSeExiste(id)){
            usuario.setId(id);
            repository.save(usuario);
        } 
        else throw new UsuarioNotFoundException("Usuario nao existe"); 
    }

    @Override
    public void deletar(Long id) throws UsuarioNotFoundException {
        if(verificarSeExiste(id)) 
            repository.deleteById(id);
        else throw new UsuarioNotFoundException("Usuario nao existe"); 
    }
    
    private boolean verificarSeExiste(Long id){
        return repository.existsById(id);
    }
}
