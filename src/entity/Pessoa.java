package entity;

import enums.TipoCliente;

public interface Pessoa {
    Integer getId();
    void setId(Integer id);
    String getNome();
    void setNome(String nome);
    Integer getIdade();
    void setIdade(Integer idade);
    TipoCliente getTipoPessoa();
}

