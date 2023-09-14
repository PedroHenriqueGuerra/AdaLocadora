package repository;

import entity.Cliente;
import enums.TipoCliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteRepository<T extends Cliente>{

    private List<T> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrar(T cliente) {
        String documento = cliente.getDocumento();
        if(existeClienteComDocumento(documento)){
            throw new IllegalArgumentException("Cliente com mesmo CPF/CNPJ já existe.");
        }
        clientes.add(cliente);
    }

    public void alterar(T cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                clientes.set(i, cliente);
                break;
            }
        }
    }

    public boolean existeClienteComDocumento(String documento) {
        for (T cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public T buscarPorDocumento(String documento) {
        for (T cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    public List<T> listarTodos() {
        return new ArrayList<>(clientes);
    }


}