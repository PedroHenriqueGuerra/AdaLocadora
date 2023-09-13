package menu;

import entity.Aluguel;
import entity.Cliente;
import entity.Veiculo;
import enums.TipoCliente;
import enums.TipoVeiculo;
import repository.AluguelRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import service.AluguelService;
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
    static AluguelRepository aluguelRepository = new AluguelRepository();
    static AluguelService aluguelService = new AluguelService(aluguelRepository, veiculoRepository);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) throws ParseException {
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
                    System.out.println("Informe o Id do veículo: ");
                    veiculoService.listarTodos();
                    Integer idVeiculo = sc.nextInt();
                    alterarVeiculo(idVeiculo);
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
                    System.out.println("Informe o Id do cliente: ");
                    clienteService.listarTodos();
                    Integer idCliente = sc.nextInt();
                    alterarCliente(idCliente);
                    break;
                case 6:
                    System.out.println("Informe o Id do veículo que deseja alugar: ");
                    System.out.println(veiculoService.listarTodos());
                    Integer idVeiculoAlugar = sc.nextInt();

                    System.out.println("Informe o Id do cliente que deseja alugar: ");
                    System.out.println(clienteService.listarTodos());
                    Integer idClienteAlugar = sc.nextInt();
                    alugarVeiculo(idVeiculoAlugar, idClienteAlugar);
                    break;
                case 7:
                    System.out.println("Informe o Id do aluguel: ");
                    System.out.println(aluguelService.listarTodos());
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

        Veiculo veiculo = new Veiculo(placa, tipoVeiculo, true, modelo, marca);
        veiculoService.add(veiculo);
    }

    public static void alterarVeiculo(Integer id) {
        Veiculo veiculo = veiculoService.buscarPorId(id);

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

            veiculo.setMarca(marca);
            veiculo.setModelo(modelo);
            veiculo.setPlaca(placa);
            veiculo.setTipoVeiculo(tipoVeiculo);

            veiculoService.alterar(veiculo);
            System.out.println("Veículo alterado com sucesso.");
        }
    }

    public static void buscarPorNome(String nome) {
        List<Veiculo> veiculos = veiculoService.buscarPorNome(nome);

        if (veiculos == null || veiculos.isEmpty()) {
            System.out.println("Veículo não encontrado.");
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
        } else {
            System.out.println("Informe o CNPJ do cliente: ");
            String cnpj = sc.next();
            cliente = new Cliente(nome, cnpj, idade, tipoPessoa);
        }

        clienteService.add(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }


    public static void alterarCliente(Integer id){
        Cliente cliente = clienteService.buscarPorId(id);
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


    public static void alugarVeiculo(Integer idVeiculo, Integer idCliente) throws ParseException {
        if(veiculoService.buscarPorId(idVeiculo) == null){
            System.out.println("Veiculo não existe.");
        }
        else if (clienteService.buscarPorId(idCliente) == null) {
            System.out.println("Cliente não existe");
        }
        else{
            Veiculo veiculoAlugar = veiculoService.buscarPorId(idVeiculo);
            Cliente clienteALugar = clienteService.buscarPorId(idCliente);
            try {
                System.out.println("Informe a data e hora do aluguel (dd/MM/yyyy HH:mm:ss)");
                String aluguelData = sc.next() + " " + sc.next();
                Date dataAluguel = dateFormat.parse(aluguelData);

                System.out.println("Informe a data e hora da entrega (dd/MM/yyyy HH:mm:ss)");
                String entrega = sc.next() + " " + sc.next();
                Date dataEntrega = dateFormat.parse(entrega);
                Aluguel aluguel = new Aluguel(clienteALugar, veiculoAlugar, dataAluguel, dataEntrega);
                aluguelRepository.add(aluguel);
            } catch (ParseException e){
                System.out.println("Erro ao analisar as datas. Certifique-se de usar o formato correto (dd/MM/yyyy HH:mm:ss).");
            }
        }
    }

    public static void devolverVeiculo(Integer idAluguel){
        if(aluguelRepository.buscarPorId(idAluguel) == null){
            System.out.println("Aluguel inválido.");
        }
        else {
            Aluguel aluguel = aluguelRepository.buscarPorId(idAluguel);
            aluguel.setQuantidadeDias( aluguelService.CalcularDias(aluguel.getDataEntrega(), aluguel.getDataAluguel()));
            aluguel.setValorTotal(aluguelService.calcularValorTotal(aluguel.getVeiculo().getTipoVeiculo(), aluguel.getQuantidadeDias()));
            aluguel.setDesconto(aluguelService.calcularDesconto(aluguel.getCliente().getTipoPessoa(), aluguel.getQuantidadeDias(), aluguel.getValorTotal()));
            System.out.println("O valor total do aluguel foi de: " + aluguel.getValorTotal());
            System.out.println("O valor do desconto foi de: " + aluguel.getDesconto());
            BigDecimal valorFinal = aluguel.getValorTotal().subtract(aluguel.getDesconto());
            System.out.println("O valor a ser pago é de: " + valorFinal);
            aluguelService.devolver(aluguelService.devolver(aluguel));
        }
    }



}
