package com.curler.os.dtos;

import com.curler.os.domains.Tecnico;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
        
    private Integer id;
    
    @NotEmpty(message = "Campo nome não pode estar vazio!")
    private String nome;
    
    @CPF
    @NotEmpty(message = "Campo CPF não pode estar vazio!")
    private String cpf; // remover caso não queira mostrar o CPF do tecnico.
    
    @NotEmpty(message = "Campo telefone não pode estar vazio!")
    private String telefone;

    public TecnicoDTO() {
    }
    
    public TecnicoDTO(Tecnico obj) {
        
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
        
}