
package com.curler.os.services;

import com.curler.os.domains.Cliente;
import com.curler.os.dtos.ClienteDTO;
import com.curler.os.repositories.ClienteRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;
    
    public Cliente createCliente(ClienteDTO clienteDTO){
    
        return repository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
        
    }
    
    public List<Cliente> getCliente(){
    
        return repository.findAll();
        
    }
    
    public Cliente getClienteById(Integer id){
      
        Optional<Cliente> cliente = repository.findById(id);
        
        return cliente.orElseThrow();
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
}
