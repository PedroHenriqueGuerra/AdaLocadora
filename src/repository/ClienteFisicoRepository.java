package repository;

import entity.ClienteFisico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteFisicoRepository {

    private List<ClienteFisico> clienteFisicoList;

    public ClienteFisicoRepository(){
        this.clienteFisicoList = new ArrayList<ClienteFisico>();
    }

    public void add(ClienteFisico clienteFisico){
        this.clienteFisicoList.add(clienteFisico);
    }

    public void remove(ClienteFisico clienteFisico){
        Iterator<ClienteFisico> iterator = clienteFisicoList.iterator();
        while (iterator.hasNext()){
            ClienteFisico comparar = iterator.next();
            if(comparar.getId().equals(clienteFisico.getId()) && comparar.getCpf().equals(clienteFisico.getCpf())){
                iterator.remove();
                break;
            }
        }
    }

    public List<ClienteFisico> listarTodos(){
        return new ArrayList<>(clienteFisicoList);
    }

    public List<ClienteFisico> buscarPorCpf(String cpf){
        List<ClienteFisico> resultado = new ArrayList<>();
        for(ClienteFisico clienteFisico : clienteFisicoList ){
            if(clienteFisico.getCpf().equalsIgnoreCase(cpf)){
                resultado.add(clienteFisico);
            }
        }
        return resultado;
    }


}
