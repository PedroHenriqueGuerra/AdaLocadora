package entity;

import enums.TipoCliente;

import java.util.Objects;

public class Cliente implements Pessoa {
    private static Integer ultimoId = 0;
    private Integer id;
    private String nome;
    private String documento; // Pode ser CPF para Pessoa Física ou CNPJ para Pessoa Jurídica
    private Integer idade;
    private TipoCliente tipoPessoa;

    public Cliente(String nome, String documento, Integer idade, TipoCliente tipoPessoa) {
        this.id = ultimoId++;
        this.nome = nome;
        this.documento = documento;
        this.idade = idade;
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Integer getIdade() {
        return idade;
    }

    @Override
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public TipoCliente getTipoPessoa() {
        return tipoPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTipoPessoa(TipoCliente tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, documento, idade, tipoPessoa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente outro = (Cliente) obj;
        return Objects.equals(documento, outro.documento) && Objects.equals(id, outro.id)
                && Objects.equals(nome, outro.nome) && Objects.equals(idade, outro.idade)
                && tipoPessoa == outro.tipoPessoa;
    }

    @Override
    public String toString() {
        return nome + " - Pessoa " + id;
    }
}
