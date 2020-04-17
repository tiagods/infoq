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
import br.com.infoq.model.Os;
import br.com.infoq.repository.OsRepository;

/**
 *
 * @author tiagods
 */
@Service
public class OsService  {
    
    @Autowired
    private OsRepository repository;

    public Os adicionar(Os os) {
        return repository.save(os);
    }

    public Optional<Os> buscarPorId(long id) throws OsNotFoundException {
        if(verificarSeExiste(id)) return repository.findById(id);
        else throw new OsNotFoundException("Os nao existe");
    }

    public void alterar(Os os, Long id) throws OsNotFoundException {
        if(verificarSeExiste(id)){
        	os.setId(id);
            repository.save(os);
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
}
