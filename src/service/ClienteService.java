package service;

import entity.Cliente;
import repository.ClienteRepository;

import java.util.List;

public class ClienteService<T extends Cliente> {

    private final ClienteRepository<T> clienteRepository;

    public ClienteService(ClienteRepository<T> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void add(T cliente) {
        clienteRepository.cadastrar(cliente);
    }

    public void alterar(T cliente) {
        clienteRepository.alterar(cliente);
    }

    public Cliente buscarPorDocumento(String documento){
        return clienteRepository.buscarPorDocumento(documento);
    }

    public List<T> listarTodos() {
        return clienteRepository.listarTodos();
    }

}
