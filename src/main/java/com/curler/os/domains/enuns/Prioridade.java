package com.curler.os.domains.enuns;

public enum Prioridade {
    BAIXA(0,"BAIXA"),
    MEDIA(1,"MEDIA"),
    ALTA(2,"ALTA");
    
    private Integer code;
    
    private String descricao;

    private Prioridade(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Prioridade toEnum(Integer code){
        if (code == null){
            return null;
        }
        
        for (Prioridade x: Prioridade.values()){
            if (code.equals(x.getCode())){
                return x;
            }
        }
        
        throw new IllegalArgumentException("Prioridade inválida" + code);
    }
    
}
