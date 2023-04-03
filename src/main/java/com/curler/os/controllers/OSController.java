package com.curler.os.controllers;

import com.curler.os.domains.OS;
import com.curler.os.dtos.OSDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.curler.os.services.OsService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "/os")
@CrossOrigin("*")
public class OSController {

    @Autowired
    private OsService service;

    @GetMapping
    public ResponseEntity<List<OSDTO>> GetOS(){

        List<OSDTO> osDTO = service.OSGet().stream().map(OSDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(osDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OSDTO> GetById(@PathVariable Integer id){

        OSDTO osDTO = new OSDTO(service.OSGetByID(id));

        return ResponseEntity.ok().body(osDTO);

    }

    @PostMapping
    public ResponseEntity<OSDTO> CreateOS(@Valid @RequestBody OSDTO osDTO){

        osDTO = new OSDTO(service.OSCreate(osDTO));
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(osDTO.getId()).toUri();
//        return ResponseEntity.created(uri).build();
        return ResponseEntity.ok().body(osDTO);
    }

    @PutMapping
    public ResponseEntity<OSDTO> UpdateOS(@Valid @RequestBody OSDTO osDTO){

        osDTO = new OSDTO(service.OSUpdate(osDTO));
        return ResponseEntity.ok().body(osDTO);

    }
}
