package service;

import entity.Aluguel;
import entity.Pessoa;
import entity.Veiculo;
import enums.TipoCliente;
import enums.TipoVeiculo;
import repository.AluguelRepository;
import repository.VeiculoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository){
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public void alugar(Pessoa cliente, Veiculo veiculo, Date dataAluguel, Date dataEntrega){
        Aluguel aluguel = new Aluguel(cliente, veiculo, dataAluguel, dataEntrega);
    }

    public Integer CalcularDias(Date dataEntrega, Date dataAluguel){
        long diffInSeconds = TimeUnit.SECONDS.convert(Math.abs(dataEntrega.getTime() - dataAluguel.getTime()), TimeUnit.MILLISECONDS);
        if (diffInSeconds >= 24 * 3600 + 1) {
            return (int) Math.ceil((double) diffInSeconds / (24 * 3600)); // Cobrar duas diárias se for igual ou maior a 24 horas
        } else {
            return 1; // Cobrar apenas uma diária se for menos de 24 horas
        }
    }

    public BigDecimal calcularValorTotal(TipoVeiculo tipoVeiculo, Integer qtdDiarias){
        BigDecimal valorDia = BigDecimal.ZERO;
        switch (tipoVeiculo){
            case PEQUENO:
                valorDia = new BigDecimal("100.00");
                break;
            case MEDIO:
                valorDia = new BigDecimal("150.00");
                break;
            case SUV:
                valorDia = new BigDecimal("200.00");
                break;
        }
        BigDecimal valorTotal = valorDia.multiply(BigDecimal.valueOf(qtdDiarias));
        return valorTotal;
    }

    public BigDecimal calcularDesconto(TipoCliente tipoPessoa, Integer qtdDiarias, BigDecimal valorTotal){
        BigDecimal desconto = new BigDecimal("0.00");
        if(tipoPessoa.equals(TipoCliente.FISICO) && qtdDiarias > 5){
            desconto = valorTotal.multiply(new BigDecimal("0.05"));
        }
        else if (tipoPessoa.equals(TipoCliente.JURIDICO) && qtdDiarias > 3) {
            desconto = valorTotal.multiply(new BigDecimal("0.10"));
        }
        return desconto;
    }

    public BigDecimal valorFinal(BigDecimal valorTotal, BigDecimal valorDesconto){
        BigDecimal valorFinal = valorTotal.subtract(valorDesconto);
        return valorFinal;
    }

    public Aluguel devolver(Aluguel aluguel){
        Aluguel devolver = aluguelRepository.buscarPorId(aluguel.getId());

        aluguel.setQuantidadeDias(CalcularDias(aluguel.getDataAluguel(),aluguel.getDataEntrega()));
        BigDecimal valorTotal = (calcularValorTotal(aluguel.getVeiculo().getTipoVeiculo(), aluguel.getQuantidadeDias()));
        aluguel.setDesconto(calcularDesconto(aluguel.getCliente().getTipoPessoa(), aluguel.getQuantidadeDias(), aluguel.getValorTotal()));
        aluguel.setValorTotal(valorTotal.subtract(aluguel.getDesconto()));
        aluguel.getVeiculo().setDisponibilidade(false);
        return devolver;
    }

    public List<Veiculo> listarVeiculosDisponiveis(List<Veiculo> veiculos){
        List<Veiculo> listaVeiculos = new ArrayList<>();
        List<Veiculo> veiculoAtual = veiculos;
        if(!veiculoAtual.isEmpty()){
            for(int i = 0; i < veiculoAtual.size(); i++){
                Veiculo veiculo = veiculoAtual.get(i);
                if(!veiculo.getDisponibilidade()){
                    veiculoAtual.add(veiculo);
                }
            }
        }
        return listaVeiculos;
    }

    public List<Veiculo> listarVeiculosAlugados(List<Veiculo> veiculos){
        List<Veiculo> listaVeiculos = new ArrayList<>();
        List<Veiculo> veiculoAtual = veiculos;
        if(!veiculoAtual.isEmpty()){
            for(int i = 0; i < veiculoAtual.size(); i++){
                Veiculo veiculo = veiculoAtual.get(i);
                if(veiculo.getDisponibilidade() != null && veiculo.getDisponibilidade()){
                    veiculoAtual.add(veiculo);
                }
            }
        }
        return listaVeiculos;
    }

    public List<Aluguel> listarTodos(){
        return aluguelRepository.listarTodos();
    }

}
