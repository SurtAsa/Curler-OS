
package com.curler.os.services;

import com.curler.os.domains.Cliente;
import com.curler.os.domains.Pessoa;
import com.curler.os.dtos.ClienteDTO;
import com.curler.os.repositories.ClienteRepository;
import com.curler.os.repositories.PessoaRepository;
import com.curler.os.services.exceptions.DataIntegrityViolationException;
import com.curler.os.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Cliente createCliente(ClienteDTO clienteDTO){

        if(findByCPF(clienteDTO) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        return repository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
        
    }
    
    public List<Cliente> getCliente(){
    
        return repository.findAll();
        
    }
    
    public Cliente getClienteById(Integer id){
      
        Optional<Cliente> cliente = repository.findById(id);
        
        return cliente.orElseThrow(()-> new ObjectNotFoundException
        ("Objeto não encontrado! ID: " + id + ", tipo: " + Cliente.class.getName()));
    }
    
    public void deleteCliente(Integer id){
        
        Cliente cliente = getClienteById(id);
        
//        if(cliente.getList().size() > 0){
//        
//            throw new ;
//            
//        }  
        
        repository.deleteById(id);
        
    }
    
    public Cliente putCliente(Integer id, @Valid ClienteDTO clienteNovo){
    
        Cliente clienteAntigo = getClienteById(id);
        
        clienteAntigo.setNome(clienteNovo.getNome());
        clienteAntigo.setCpf(clienteNovo.getCpf());
        clienteAntigo.setTelefone(clienteNovo.getTelefone());
        
        return repository.save(clienteAntigo); 
        
    }

    private Pessoa findByCPF(ClienteDTO objDTO){

        Pessoa pessoa = pessoaRepository.findByCPF(objDTO.getCpf());
        if(pessoa != null){
            return pessoa;
        }

        return null;
    }
}
