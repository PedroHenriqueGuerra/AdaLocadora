package repository;

import entity.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    private static List<Veiculo> veiculos;

    public VeiculoRepository(){
        veiculos = new ArrayList<>();
    }
    public static void cadastrar(Veiculo veiculo){
        if(placaJaCadastrada(veiculo.getPlaca())){
            throw new IllegalArgumentException("Placa já cadastrada");
        }
        veiculo.setDisponibilidade(false);
        veiculos.add(veiculo);
    }



    public static void alterar(Veiculo veiculo){
        for(int i = 0; i < veiculos.size(); i++){
            if(veiculos.get(i).getId().equals(veiculo.getId())){
                veiculos.set(i, veiculo);
                break;
            }
        }
    }

    public List<Veiculo> buscarPorNome(String nome){
        List<Veiculo> resultado = new ArrayList<>();
        for(Veiculo veiculo : veiculos){
            if(veiculo.getModelo().equalsIgnoreCase(nome)){
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public Veiculo buscarPorPlaca(String placa){
        List<Veiculo> resultado = new ArrayList<>();
        for(Veiculo veiculo : veiculos){
            if(veiculo.getPlaca().equals(placa)){
                return veiculo;
            }
        }
        return null;
    }

    public static List<Veiculo> listarTodos(){
        return new ArrayList<>(veiculos);
    }

    public static boolean placaJaCadastrada(String placa) {
        List<Veiculo> veiculos = listarTodos();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return true;
            }
        }
        return false;
    }

}
