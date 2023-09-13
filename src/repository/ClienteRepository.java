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

    public void add(T cliente) {
        String documento = cliente.getDocumento();
        if(existeClienteComDocumento(documento)){
            throw new IllegalArgumentException("Cliente com mesmo CPF/CNPJ já existe.");
        }
        clientes.add(cliente);
    }

    public boolean existeClienteComDocumento(String documento) {
        for (T cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }


    public void remove(T cliente) {
        Iterator<T> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            T comparar = iterator.next();
            if (comparar.getId().equals(cliente.getId())) {
                iterator.remove();
                break;
            }
        }
    }

    public void alterar(T cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                clientes.set(i, cliente);
                break;
            }
        }
    }

    public T buscarPorId(Integer id) {
        for (T cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    public List<T> listarTodos() {
        return new ArrayList<>(clientes);
    }
    public List<T> listarClientesFisicos() {
        List<T> clientesFisicos = new ArrayList<>();

        for (T cliente : clientes) {
            if (cliente.getTipoPessoa() == TipoCliente.FISICO) {
                clientesFisicos.add(cliente);
            }
        }

        return clientesFisicos;
    }

    public List<T> listarClientesJuridicos() {
        List<T> clientesJuridicos = new ArrayList<>();

        for (T cliente : clientes) {
            if (cliente.getTipoPessoa() == TipoCliente.JURIDICO) {
                clientesJuridicos.add(cliente);
            }
        }

        return clientesJuridicos;
    }

}