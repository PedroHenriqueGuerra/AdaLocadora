package entity;

import enums.TipoPessoa;

import java.util.Objects;

public class ClienteFisico extends Cliente {

    private static Integer ultimoId = 0;
    private Integer id;
    private String nome;
    private String cpf;
    private Integer idade;

    public ClienteFisico(String nome, String cpf, Integer idade) {
        super(TipoPessoa.FISICA);
        this.id = ultimoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getIdade() {
        return idade;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, nome, idade, cpf);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ClienteFisico outro = (ClienteFisico) obj;
        return Objects.equals(cpf, outro.cpf) && Objects.equals(id, outro.id) && Objects.equals(nome, outro.nome)
                && Objects.equals(idade, outro.idade);
    }

    @Override
    public String toString(){
        return nome + "- Pessoa " + super.getTipoPessoa();
    }
}
