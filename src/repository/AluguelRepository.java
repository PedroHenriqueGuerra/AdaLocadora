package repository;

import entity.Aluguel;
import entity.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepository {

    private final List<Aluguel> alugueis;

    public AluguelRepository(VeiculoRepository veiculoRepository){
        this.alugueis = new ArrayList<>();
    }

    public void add(Aluguel aluguel){
        alugueis.add(aluguel);
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponibilidade(true);
        VeiculoRepository.alterar(veiculo);
    }

    public void remove(Integer id){
        Aluguel aluguel = buscarPorId(id);
        if(aluguel != null){
            Veiculo veiculo = aluguel.getVeiculo();
            veiculo.setDisponibilidade(false);
            VeiculoRepository.alterar(veiculo);
            alugueis.remove(aluguel);
        }
    }

    public void alterar(Aluguel aluguel){
        for(int i = 0; i < alugueis.size(); i++){
            if(alugueis.get(i).getId().equals(aluguel.getId())){
                alugueis.set(i, aluguel);
                break;
            }
        }
    }

    public Aluguel buscarPorId(Integer id){
        for(Aluguel aluguel : alugueis){
            if(aluguel.getId().equals(id)){
                return aluguel;
            }
        }
        return null;
    }

    public List<Aluguel> listarTodos(){
        return new ArrayList<>(alugueis);
    }
}
