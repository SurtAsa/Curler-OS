package com.curler.os.services;

import com.curler.os.domains.Pessoa;
import com.curler.os.domains.Tecnico;
import com.curler.os.dtos.TecnicoDTO;
import com.curler.os.repositories.PessoaRepository;
import com.curler.os.repositories.TecnicoRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.curler.os.services.exceptions.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Tecnico createTecnico(TecnicoDTO tecnicoDTO){

        if(findByCPF(tecnicoDTO) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        return repository.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
        
    }
    
    public List<Tecnico> getTecnico(){
    
        return repository.findAll();
        
    }
    
    public Tecnico getTecnicoByID(Integer id){
    
        Optional<Tecnico> tecnico = repository.findById(id);
        
        return tecnico.orElseThrow();
        
    }
    
    public void deleteTecnico(Integer id){
    
        Tecnico tecnico = getTecnicoByID(id);
        
        repository.deleteById(id);
    
    }
    
    public Tecnico putTecnico(Integer id, @Valid TecnicoDTO tecnicoNovo){
    
        Tecnico tecnicoAntigo = getTecnicoByID(id);
        
        tecnicoAntigo.setNome(tecnicoNovo.getNome());
        tecnicoAntigo.setCpf(tecnicoNovo.getCpf());
        tecnicoAntigo.setTelefone(tecnicoNovo.getTelefone());
        
        return repository.save(tecnicoAntigo);
    
    }

    private Pessoa findByCPF(TecnicoDTO objDTO){

        Pessoa pessoa = pessoaRepository.findByCPF(objDTO.getCpf());
        if(pessoa != null){
            return pessoa;
        }

        return null;
    }
    
}
