/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.repository;

import br.com.infoq.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tiagods
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByLoginIgnoreCaseAndSenhaIgnoreCase(String login, String senha);
    public Optional<Usuario> findByLoginIgnoreCase(String text);
    public List<Usuario> findAllByUsuarioIgnoreCaseContaining(String nome);
}
