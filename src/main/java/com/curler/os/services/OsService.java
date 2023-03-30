package com.curler.os.services;

import com.curler.os.domains.Cliente;
import com.curler.os.domains.OS;
import com.curler.os.domains.Tecnico;
import com.curler.os.domains.enuns.Prioridade;
import com.curler.os.domains.enuns.Status;
import com.curler.os.dtos.OSDTO;
import com.curler.os.repositories.OSRepository;
import com.curler.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    private OS fromDTO(OSDTO osDTO){
        OS newOS = new OS();
        newOS.setId(osDTO.getId());
        newOS.setObservacoes(osDTO.getObservacoes());
        newOS.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
        newOS.setStatus(Status.toEnum(osDTO.getStatus()));
        Cliente cliente = clienteService.getClienteById(osDTO.getCliente());
        Tecnico tecnico = tecnicoService.getTecnicoByID(osDTO.getTecnico());
        newOS.setCliente(cliente);
        newOS.setTecnico(tecnico);

        if(newOS.getStatus().getCode().equals(2)){
            newOS.setDataFechamento(LocalDateTime.now());
        }

        return osRepository.save(newOS);
    }

    public OS OSCreate(OSDTO osDTO){
        return fromDTO(osDTO);
    }

    public OS OSUpdate(@Valid OSDTO osDTO){

        OSGetByID(osDTO.getId());

        return fromDTO(osDTO);
    }
    public List<OS> OSGet(){

        return osRepository.findAll();

    }
    public OS OSGetByID(Integer id){

        Optional<OS> os = osRepository.findById(id);

        return os.orElseThrow(()-> new ObjectNotFoundException
                ("Objeto não encontrado! ID: " + id + ", tipo: " + OS.class.getName()));

    }
}
