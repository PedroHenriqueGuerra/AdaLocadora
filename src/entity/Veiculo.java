package entity;

import enums.TipoVeiculo;

import java.util.Objects;

public class Veiculo {

    private static Integer ultimoId;
    private Integer id;
    private String placa;
    private Boolean disponibilidade;
    private TipoVeiculo tipoVeiculo;
    private String modelo;
    private String marca;

    public Veiculo(String placa, TipoVeiculo tipoVeiculo, boolean disponibilidade, String modelo, String marca) {
        this.id = ultimoId++;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
        this.placa = placa;
        this.disponibilidade = disponibilidade;

    }

    public static Integer getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(Integer ultimoId) {
        Veiculo.ultimoId = ultimoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }


    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }



    @Override
    public int hashCode(){
        return Objects.hash(id, placa, disponibilidade, tipoVeiculo, marca, modelo);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Veiculo outro = (Veiculo) obj;
        return Objects.equals(id, outro.id) && Objects.equals(placa, outro.placa) && Objects.equals(disponibilidade, outro.disponibilidade)
                && Objects.equals(tipoVeiculo, outro.tipoVeiculo) && Objects.equals(marca, outro.marca) && Objects.equals(modelo, outro.modelo);
    }

    @Override
    public String toString(){
        return marca + "-" + modelo + "-" + placa;
    }

}
