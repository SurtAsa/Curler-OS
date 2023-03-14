package com.curler.os.controllers;

import com.curler.os.domains.Tecnico;
import com.curler.os.dtos.TecnicoDTO;
import com.curler.os.services.TecnicoService;
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
@RequestMapping(value="/tecnico")
public class TecnicoController {
    
    @Autowired
    private TecnicoService service;
    
    @PostMapping
    public ResponseEntity<TecnicoDTO> CreateTecnico(@Valid @RequestBody TecnicoDTO objDTO){
    
        Tecnico tecnico = service.createTecnico(objDTO);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
        
    }
    
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> FindALlTecnico(){
    
        List<TecnicoDTO> tecnicoDTO = service.getTecnico()
                .stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(tecnicoDTO);
        
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> FindByIDTecnico(@PathVariable Integer id){
    
        TecnicoDTO tecnicoDTO = new TecnicoDTO(service.getTecnicoByID(id));
        
        return ResponseEntity.ok().body(tecnicoDTO);
        
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> DeleteTecnico(@PathVariable Integer id){
    
        service.deleteTecnico(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> PutTecnico (@PathVariable Integer id, @RequestBody TecnicoDTO objDTO){
    
        TecnicoDTO tecnicoDTO = new TecnicoDTO( service.putTecnico(id, objDTO));
        
        return ResponseEntity.ok().body(tecnicoDTO);
    
    }
    
}
