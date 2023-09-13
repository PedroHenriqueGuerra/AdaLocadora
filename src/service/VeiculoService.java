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
        VeiculoRepository.add(veiculo);
    }
    public void remove(Integer id){
        veiculoRepository.remove(id);
    }
    public void alterar(Veiculo veiculo){
        VeiculoRepository.alterar(veiculo);
    }

    public List<Veiculo> buscarPorNome(String nome){
        return veiculoRepository.buscarPorNome(nome);
    }

    public Veiculo buscarPorId(Integer id){
        return veiculoRepository.buscarPorId(id);
    }

    public List<Veiculo> listarTodos(){
        return VeiculoRepository.listarTodos();
    }



}
