package com.curler.os.repositories;

import com.curler.os.domains.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico,Integer>{
    
}