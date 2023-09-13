package repository;

import entity.Aluguel;
import entity.Veiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AluguelRepository {

    private final Map<Integer, Aluguel> alugueis;

    public AluguelRepository() {
        this.alugueis = new HashMap<>();
    }

    public void add(Aluguel aluguel) {
        alugueis.put(aluguel.getId(), aluguel);
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponibilidade(true);
    }

    public void remove(Integer id) {
        Aluguel aluguel = buscarPorId(id);
        if (aluguel != null) {
            Veiculo veiculo = aluguel.getVeiculo();
            veiculo.setDisponibilidade(false);
            VeiculoRepository.alterar(veiculo);
            alugueis.remove(id);
        }
    }

    public void alterar(Aluguel aluguel) {
        alugueis.put(aluguel.getId(), aluguel);
    }

    public Aluguel buscarPorId(Integer id) {
        return alugueis.get(id);
    }

    public List<Aluguel> listarTodos() {
        return new ArrayList<>(alugueis.values());
    }
}
