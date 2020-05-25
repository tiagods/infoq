/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoq.exception.EmpresaNotFoundException;
import br.com.infoq.model.Empresa;
import br.com.infoq.repository.EmpresaRepository;

/**
 *
 * @author tiagods
 */
@Service
public class EmpresaService {

   // @Autowired private EmpresaRepository repository;

    public Empresa adicionar(Empresa empresa) {
        //return repository.save(empresa);
        return empresa;
    }
    
    public Optional<Empresa> buscarPorId(long id) throws EmpresaNotFoundException {
        if (verificarSeExiste(id)) {
            return Optional.empty();
        	//return repository.findById(id);
        } else {
            throw new EmpresaNotFoundException("Empresa nao existe");
        }
    }

    public void alterar(Empresa empresa, Long id) throws EmpresaNotFoundException {
        if (verificarSeExiste(id)) {
        	empresa.setId(id);
            //repository.save(empresa);
        } else {
            throw new EmpresaNotFoundException("Empresa nao existe");
        }
    }

    private boolean verificarSeExiste(Long id) {
        //return repository.existsById(id);
    	return false;
    }
}
