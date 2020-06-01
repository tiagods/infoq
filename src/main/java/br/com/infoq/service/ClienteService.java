/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.infoq.exception.ClienteNotFoundException;
import br.com.infoq.model.Cliente;
import br.com.infoq.repository.ClienteRepository;

/**
 *
 * @author tiagods
 */
@Service
public class ClienteService {

    @Autowired private ClienteRepository repository;

    public Cliente adicionar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listar(Sort.Direction direction, String properties){
        return repository.findAll(Sort.by(direction, properties));
    }
    
    public List<Cliente> listarTop100(){
        return repository.findTop100ByOrderByIdDesc();
    }

    public Optional<Cliente> buscarPorId(long id) throws ClienteNotFoundException {
        if (verificarSeExiste(id)) {
            return repository.findById(id);
        } else {
            throw new ClienteNotFoundException("Cliente nao existe");
        }
    }

    public void alterar(Cliente cliente, Long id) throws ClienteNotFoundException {
        if (verificarSeExiste(id)) {
            cliente.setId(id);
            repository.save(cliente);
        } else {
            throw new ClienteNotFoundException("Cliente nao existe");
        }
    }

    public void deletar(Long id) throws ClienteNotFoundException {
        if (verificarSeExiste(id)) {
            repository.deleteById(id);
        } else {
            throw new ClienteNotFoundException("Cliente nao existe");
        }
    }

    private boolean verificarSeExiste(Long id) {
        return repository.existsById(id);
    }

    public List<Cliente> buscarClientePorNome(String name) {
        return repository.findAllByNomeIgnoreCaseContaining(name);
    }
}
