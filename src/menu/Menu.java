package menu;

import entity.Aluguel;
import entity.Cliente;
import entity.Veiculo;
import enums.TipoCliente;
import enums.TipoVeiculo;
import exceptions.*;
import repository.AluguelRepository;
import service.AluguelService;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import service.ClienteService;
import service.VeiculoService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static VeiculoRepository veiculoRepository = new VeiculoRepository();
    static VeiculoService veiculoService = new VeiculoService();
    static ClienteRepository<Cliente> clienteRepository = new ClienteRepository<>();
    static ClienteService<Cliente> clienteService = new ClienteService<>(clienteRepository);
    static AluguelService aluguelservice = new AluguelService();
    static AluguelRepository aluguelRepository = new AluguelRepository(aluguelservice, veiculoRepository);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) throws ParseException, VeiculoNaoEncotradoException, ClienteNaoEncontradoException {
        System.out.println("Bem-vindo à Locadora Ada. Informe a opção desejada:");
        System.out.println("1: Cadastrar veículo");
        System.out.println("2: Alterar um veículo cadastrado");
        System.out.println("3: Buscar um veículo");
        System.out.println("4: Cadastrar um cliente (Físico ou Jurídico)");
        System.out.println("5: Alterar dados de um cliente");
        System.out.println("6: Alugar um veículo");
        System.out.println("7: Devolver um veículo");
        System.out.println("0: Sair");

        int opcao = sc.nextInt();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    System.out.println("Informe a placa do veículo: ");
                    String placaVeiculo = sc.next();
                    alterarVeiculo(placaVeiculo);
                    break;
                case 3:
                    System.out.println("Informe o nome do veículo: ");
                    String nome = sc.next();
                    buscarPorNome(nome);
                    break;
                case 4:
                    cadastrarCliente();
                    break;
                case 5:
                    System.out.println("Informe o documento do cliente: ");
                    clienteService.listarTodos();
                    String documento = sc.next();
                    alterarCliente(documento);
                    break;
                case 6:
                    System.out.println("Informe a placa do veiculo que deseja alugar: ");
                    String veiculoAlugar = sc.next();

                    System.out.println("Informe o documento do cliente que deseja alugar: ");
                    System.out.println(clienteService.listarTodos());
                    String documentoAlugar = sc.next();
                    alugarVeiculo(veiculoAlugar, documentoAlugar);
                    break;
                case 7:
                    System.out.println("Informe o Id do aluguel: ");
                    System.out.println(aluguelRepository.listarTodos());
                    Integer idAluguel = sc.nextInt();
                    devolverVeiculo(idAluguel);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println("Informe a próxima opção (ou 0 para sair): ");
            opcao = sc.nextInt();
        }
    }

    public static void cadastrarVeiculo() {
        System.out.println("Informe a marca do veículo: ");
        String marca = sc.next();
        System.out.println("Informe o modelo do veículo: ");
        String modelo = sc.next();
        System.out.println("Informe a placa do veículo: ");
        String placa = sc.next();
        System.out.println("Informe o tipo do veículo (SUV, PEQUENO ou MEDIO): ");
        String tipoVeiculoStr = sc.next();
        TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoStr.toUpperCase());

        try{
            Veiculo veiculo = new Veiculo(placa, tipoVeiculo, true, modelo, marca);
            veiculoService.add(veiculo);
            System.out.println("Veiculo cadastrado.");
        } catch (IllegalArgumentException e){
            throw new ArgumentoInvalidoException("Tipo do veículo inválido.");
        }



    }

    public static void alterarVeiculo(String placaAlterar) {
        Veiculo veiculo = veiculoService.buscarPorPlaca(placaAlterar);

        if (veiculo == null) {
            System.out.println("Veículo não existe.");
        } else {
            System.out.println("Informe a nova marca do veículo: ");
            String marca = sc.next();
            System.out.println("Informe o novo modelo do veículo: ");
            String modelo = sc.next();
            System.out.println("Informe a nova placa do veículo: ");
            String placa = sc.next();
            System.out.println("Informe o novo tipo do veículo (SUV, PEQUENO ou MEDIO): ");
            String tipoVeiculoStr = sc.next();
            TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoStr.toUpperCase());

            try{
                Veiculo veiculoAlterar = new Veiculo(placa, tipoVeiculo, false, modelo, marca);
                veiculoService.alterar(veiculoAlterar);
                System.out.println("Veiculo alterado.");
            } catch (IllegalArgumentException e){
                throw new ArgumentoInvalidoException("Tipo do veiculo inválido.");
            }
        }
    }

    public static void buscarPorNome(String nome) throws VeiculoNaoEncotradoException {
        List<Veiculo> veiculos = veiculoService.buscarPorNome(nome);

        if (veiculos == null || veiculos.isEmpty()) {
            throw new VeiculoNaoEncotradoException("Veiculo não encontrado.");
        } else {
            System.out.println("Veículo encontrado: " + veiculos);
        }
    }

    public static void cadastrarCliente() {
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Informe a idade do cliente em anos: ");
        int idade = sc.nextInt();
        System.out.println("Informe o tipo do cliente (FISICO ou JURIDICO): ");
        String tipoPessoaStr = sc.next();
        TipoCliente tipoPessoa = TipoCliente.valueOf(tipoPessoaStr.toUpperCase());
        Cliente cliente;

        if (tipoPessoa == TipoCliente.FISICO) {
            System.out.println("Informe o CPF do cliente: ");
            String cpf = sc.next();
            cliente = new Cliente(nome, cpf, idade, tipoPessoa);
            clienteService.add(cliente);
            System.out.println("Cliente fisico cadastrado com sucesso.");
        } else if(tipoPessoa == TipoCliente.JURIDICO) {
            System.out.println("Informe o CNPJ do cliente: ");
            String cnpj = sc.next();
            cliente = new Cliente(nome, cnpj, idade, tipoPessoa);
            clienteService.add(cliente);
            System.out.println("Cliente juridico cadastrado com sucesso.");
        }
        else {
            throw new ArgumentoInvalidoException("Tipo de cliente invalido");
        }


    }


    public static void alterarCliente(String documento){
        Cliente cliente = clienteService.buscarPorDocumento(documento);
        if(cliente == null){
            System.out.println("Cliente não encontrado");
        }
        else {
            System.out.println("Informe o novo nome do cliente: ");
            String nome = sc.next();
            System.out.println("Informe a nova idade do cliente: ");
            Integer idade = sc.nextInt();
            System.out.println("Informe o novo tipo do cliente(FISICO ou JURIDICO): ");
            String tipoPessoaStr = sc.next();
            TipoCliente tipoPessoa = TipoCliente.valueOf(tipoPessoaStr.toUpperCase());
            if (tipoPessoa == TipoCliente.FISICO) {
                System.out.println("Informe o CPF do cliente: ");
                String cpf = sc.next();
                cliente.setDocumento(cpf);
            } else {
                System.out.println("Informe o CNPJ do cliente: ");
                String cnpj = sc.next();
                cliente.setDocumento(cnpj);
            }


            cliente.setNome(nome);
            cliente.setIdade(idade);
            cliente.setTipoPessoa(tipoPessoa);
            clienteService.alterar(cliente);
            System.out.println("Cliente alterado com sucesso.");
        }
    }


    public static void alugarVeiculo(String placaAlugar, String documento) throws VeiculoNaoEncotradoException, ClienteNaoEncontradoException {
        if(veiculoService.buscarPorPlaca(placaAlugar) == null){
            throw new VeiculoNaoEncotradoException("Veiculo não encontrado.");
        } else if (veiculoService.buscarPorPlaca(placaAlugar).getDisponibilidade()) {
            throw new ObjetoCadastradoException("Veiculo já alugado.");

        } else if (clienteService.buscarPorDocumento(documento) == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado.");
        }
        else{
            Veiculo veiculoAlugar = veiculoService.buscarPorPlaca(placaAlugar);
            Cliente clienteALugar = clienteService.buscarPorDocumento(documento);
            try {
                System.out.println("Informe a data e hora do aluguel (dd/MM/yyyy HH:mm:ss)");
                String aluguelData = sc.next() + " " + sc.next();
                Date dataAluguel = dateFormat.parse(aluguelData);

                System.out.println("Informe a data e hora da entrega (dd/MM/yyyy HH:mm:ss)");
                String entrega = sc.next() + " " + sc.next();
                Date dataEntrega = dateFormat.parse(entrega);
                Aluguel aluguel = new Aluguel(clienteALugar, veiculoAlugar, dataAluguel, dataEntrega);
                aluguelservice.cadastrar(aluguel);
            } catch (ParseException e){
                System.out.println("Erro ao analisar as datas. Certifique-se de usar o formato correto (dd/MM/yyyy HH:mm:ss).");
            }
        }
    }

    public static void devolverVeiculo(Integer idAluguel){
        if(aluguelservice.buscarPorId(idAluguel) == null){
            throw new ObjetoNaoEncotradoException("Aluguel não encontrado.");
        }
        else {
            Aluguel aluguel = aluguelservice.buscarPorId(idAluguel);
            aluguel.setQuantidadeDias( aluguelRepository.CalcularDias(aluguel.getDataEntrega(), aluguel.getDataAluguel()));
            aluguel.setValorTotal(aluguelRepository.calcularValorTotal(aluguel.getVeiculo().getTipoVeiculo(), aluguel.getQuantidadeDias()));
            aluguel.setDesconto(aluguelRepository.calcularDesconto(aluguel.getCliente().getTipoPessoa(), aluguel.getQuantidadeDias(), aluguel.getValorTotal()));
            System.out.println("O valor total do aluguel foi de: " + aluguel.getValorTotal());
            System.out.println("O valor do desconto foi de: " + aluguel.getDesconto());
            BigDecimal valorFinal = aluguel.getValorTotal().subtract(aluguel.getDesconto());
            System.out.println("O valor a ser pago é de: " + valorFinal);
            aluguelRepository.devolver(aluguelRepository.devolver(aluguel));
        }
    }



}
