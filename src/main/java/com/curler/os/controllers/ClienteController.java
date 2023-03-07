
package com.curler.os.controllers;

import com.curler.os.domains.Cliente;
import com.curler.os.dtos.ClienteDTO;
import com.curler.os.services.ClienteService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;
    
    @PostMapping
    public ResponseEntity<ClienteDTO> CreateCliente(@Valid @RequestBody ClienteDTO objDTO){
        
        Cliente cliente = service.createCliente(objDTO);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        
        return ResponseEntity.created(uri).build();      
    
    }
    
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> FindALlCliente(){
        
        List<ClienteDTO> clienteDTO = service.getCliente()
                .stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(clienteDTO);
                  
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> FindByIDCliente(@PathVariable Integer id){
    
        ClienteDTO clienteDTO = new ClienteDTO(service.getClienteById(id));
        
        return ResponseEntity.ok().body(clienteDTO);
    
    }
    
    @DeleteMapping(value = "/{id}") 
    public ResponseEntity<Void> DeleteCliente(@PathVariable Integer id){
    
        service.deleteCliente(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> PutCliente (@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
    
        ClienteDTO clienteDTO = new ClienteDTO(service.putCliente(id, objDTO));
        
        return ResponseEntity.ok().body(clienteDTO);
        
    }
}