/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    
    public List<Os> listar(Sort.Direction direction, String properties){
        return repository.findAll(Sort.by(direction, properties));
    }

    public List<Os> buscarPorAparelho(String aparelho) {
        return repository.findAllByAparelhoIgnoreCaseContaining(aparelho);
    }

    public List<Os> buscarPorIdComecaPor(Long id) {
        return repository.findAllById(Arrays.asList(id));
    }

    public List<Os> buscarPorClienteNome(String cliNome) {
        return repository.findAllByClienteNomeIgnoreCaseContaining(cliNome);
    }
    
    public List<Os> listarTop100Os(){
        return repository.findTop100ByOrderByIdDesc();
    }

}
