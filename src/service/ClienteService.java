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
        clienteRepository.add(cliente);
    }

    public void remove(T cliente) {
        clienteRepository.remove(cliente);
    }

    public void alterar(T cliente) {
        clienteRepository.alterar(cliente);
    }

    public Cliente buscarPorId(Integer id){
        return clienteRepository.buscarPorId(id);
    }

    public List<T> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public List<T> listarClientesFisicos() {
        return clienteRepository.listarClientesFisicos();
    }

    public List<T> listarClientesJuridicos() {
        return clienteRepository.listarClientesJuridicos();
    }

}
