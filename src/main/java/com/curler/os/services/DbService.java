package com.curler.os.services;

import com.curler.os.domains.Cliente;
import com.curler.os.domains.OS;
import com.curler.os.domains.Tecnico;
import com.curler.os.domains.enuns.Prioridade;
import com.curler.os.domains.enuns.Status;
import com.curler.os.repositories.ClienteRepository;
import com.curler.os.repositories.OSRepository;
import com.curler.os.repositories.TecnicoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {
    @Autowired
    private ClienteRepository clienteRepository;    

    @Autowired
    private OSRepository osRepository;
    
    @Autowired
    private TecnicoRepository tecnicoRepository;
    
    public void instanciaDb(){
        Cliente c1 = new Cliente(null,"Jose", "73516681080", "12356789");
        Tecnico t1 = new Tecnico(null, "Joao", "18965633842", "123456789");
        OS os1 = new OS(null, Prioridade.BAIXA, "teste observacao", Status.ABERTO, t1, c1);
        
        t1.getList().add(os1);
        c1.getList().add(os1);
        clienteRepository.saveAll(Arrays.asList(c1));
        tecnicoRepository.saveAll(Arrays.asList(t1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}

