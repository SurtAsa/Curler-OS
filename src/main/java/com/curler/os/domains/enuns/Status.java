package com.curler.os.domains.enuns;

public enum Status {
    ABERTO(0 , "ABERTO"),
    ANDAMENTO( 1 , "ANDAMENTO"),
    ENCERRADO( 2 , "ENCERRADO");
    
    private Integer code;
    
    private String descricao;

    private Status(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Status toEnum(Integer code){
        if (code == null){
            return null;
        }
        
        for (Status x: Status.values()){
            if (code.equals(x.getCode())){
                return x;
            }
        }
        
        throw new IllegalArgumentException("Status inválido" + code);
    }
}
