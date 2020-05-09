/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoq.exception.OsNotFoundException;
import br.com.infoq.model.Cliente;
import br.com.infoq.model.Os;
import br.com.infoq.repository.OsRepository;
import java.util.List;
import org.springframework.data.domain.Sort;

/**
 *
 * @author tiagods
 */
@Service
public class OsService  {
    
    @Autowired private OsRepository repository;

    public Os adicionar(Os os) {
        return repository.save(os);
    }

    public Optional<Os> buscarPorId(long id) throws OsNotFoundException {
        if(verificarSeExiste(id)) return repository.findById(id);
        else throw new OsNotFoundException("Os nao existe");
    }

    public Os alterar(Os os, Long id) throws OsNotFoundException {
        if(verificarSeExiste(id)){
        	os.setId(id);
            return repository.save(os);
        } 
        else throw new OsNotFoundException("Os nao existe"); 
    }

    public void deletar(Long id) throws OsNotFoundException {
        if(verificarSeExiste(id)) 
            repository.deleteById(id);
        else throw new OsNotFoundException("Os nao existe"); 
    }
    private boolean verificarSeExiste(Long id){
        return repository.existsById(id);
    }

    private List<Os> listar() {
        return repository.findAll();
    }
    
    public List<Os> listar(Sort.Direction direction, String properties){
        return repository.findAll(Sort.by(direction, properties));
    }

}
