package service;

import entity.Veiculo;
import repository.VeiculoRepository;

import java.util.List;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(){
        this.veiculoRepository = new VeiculoRepository();
    }
    public void add(Veiculo veiculo){
        VeiculoRepository.cadastrar(veiculo);
    }
    public void alterar(Veiculo veiculo){
        VeiculoRepository.alterar(veiculo);
    }

    public List<Veiculo> buscarPorNome(String nome){
        return veiculoRepository.buscarPorNome(nome);
    }

    public Veiculo buscarPorPlaca(String placa){
        return veiculoRepository.buscarPorPlaca(placa);
    }

    public List<Veiculo> listarTodos(){
        return VeiculoRepository.listarTodos();
    }



}
