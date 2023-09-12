package entity;

import enums.TipoPessoa;

import java.util.Objects;

public class ClienteJuridico extends Cliente {

    private static Integer ultimoId = 0;
    private Integer id;
    private String nome;
    private String cnpj;


    public ClienteJuridico(String nome, String cnpj) {
        super(TipoPessoa.JURIDICA);
        this.id = ultimoId++;
        this.nome = nome;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, nome, cnpj);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ClienteJuridico outro = (ClienteJuridico) obj;
        return Objects.equals(cnpj, outro.cnpj) && Objects.equals(nome, outro.nome) && Objects.equals(id, outro.id);
    }

    @Override
    public String toString(){
        return nome + "- Pessoa " + super.getTipoPessoa();
    }
}
