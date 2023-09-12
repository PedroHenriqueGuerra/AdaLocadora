package repository;

import entity.ClienteFisico;
import entity.ClienteJuridico;

import java.util.ArrayList;
import java.util.List;

public class ClienteJuridicoRepository {

    private List<ClienteJuridico> clienteJuridicos;

    public ClienteJuridicoRepository(){
        this.clienteJuridicos = new ArrayList<>();
    }

    public void add(ClienteJuridico clienteJuridico){
        for(int i = 0; i < clienteJuridicos.size(); i++){
            if(clienteJuridicos.get(i).getId().equals(clienteJuridico.getId())){
                clienteJuridicos.set(i, clienteJuridico);
                break;
            }
        }
    }

    public void remove(Integer id){
        for(int i = 0; i < clienteJuridicos.size(); i++){
            if(clienteJuridicos.get(i).getId().equals(id)){
                clienteJuridicos.remove(i);
                break;
            }
        }
    }

    public void alterar(ClienteJuridico clienteJuridico){
        for(int i = 0; i < clienteJuridicos.size(); i++){
            if(clienteJuridicos.get(i).getId().equals(clienteJuridico.getId())){
                clienteJuridicos.set(i, clienteJuridico);
                break;
            }
        }
    }

    public List<ClienteJuridico> buscarPorCnpj(String cnpj){
        List<ClienteJuridico> resultado = new ArrayList<>();
        for(ClienteJuridico clienteJuridico : clienteJuridicos){
            if(clienteJuridico.getCnpj().equalsIgnoreCase(cnpj)){
                resultado.add(clienteJuridico);
            }
        }
        return resultado;
    }

    public List<ClienteJuridico> listarTodos(){
        return new ArrayList<>(clienteJuridicos);
    }

}
