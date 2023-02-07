package com.curler.os.services;

import com.curler.os.domains.Cliente;
import com.curler.os.domains.OS;
import com.curler.os.domains.Tecnico;
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
    
    public void instanceDb(){
        Cliente c1 = new Cliente();
        OS os1 = new OS();
        Tecnico t1 = new Tecnico();
        
        clienteRepository.saveAll(Arrays.asList(c1));
        tecnicoRepository.saveAll(Arrays.asList(t1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}

