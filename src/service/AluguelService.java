package service;

import entity.Aluguel;
import entity.Veiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AluguelService {

    private final Map<Integer, Aluguel> alugueis;

    public AluguelService() {
        this.alugueis = new HashMap<>();
    }

    public void cadastrar(Aluguel aluguel) {
        alugueis.put(aluguel.getId(), aluguel);
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponibilidade(true);
    }

    public Aluguel buscarPorId(Integer id) {
        return alugueis.get(id);
    }

    public List<Aluguel> listarTodos() {
        return new ArrayList<>(alugueis.values());
    }
}
