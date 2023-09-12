package entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Aluguel {

    private static Integer ultimoId = 0;
    private Integer id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Date dataAluguel;
    private Date dataEntrega;
    private Integer quantidadeDias;
    private BigDecimal valorTotal;
    private BigDecimal desconto;

    public Aluguel(Integer id, Cliente cliente, Veiculo veiculo) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, cliente, veiculo, dataAluguel, dataEntrega, quantidadeDias, valorTotal, desconto);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Aluguel outro = (Aluguel) obj;
        return Objects.equals(dataAluguel, outro.dataAluguel) && Objects.equals(dataEntrega, outro.dataEntrega) &&
                Objects.equals(id, outro.id) && Objects.equals(cliente, outro.cliente) && Objects.equals(veiculo, outro.veiculo)
                && Objects.equals(valorTotal, outro.valorTotal) && Objects.equals(desconto, outro.desconto);
    }

    @Override
    public String toString(){
        return "Aluguel - Id: " + id + ", Pessoa: " + cliente + ", Veiculo: " + veiculo + ", Data do aluguel: " + dataAluguel + ", Data de entrega: " + dataEntrega +
                "Quantidade de dias: " + quantidadeDias + "Valor total: " + valorTotal + "Desconto: " + desconto;
    }
}
