#ADA LocateCar - Locadora de Veículos

## Recursos Essenciais

### 1. Cadastro de Veículos
- Permite cadastrar novos veículos na frota da locadora.
- Os veículos são identificados de forma única pela placa.
- Tipos de veículos suportados: PEQUENO, MEDIO e SUV.
- Regra RN1: Os veículos não podem ser repetidos.

### 2. Alteração de Veículo
- Permite a alteração de informações de veículos já cadastrados.

### 3. Busca de Veículo por Parte do Nome
- Permite buscar veículos por parte do nome (modelo ou marca).
- Facilita a localização de veículos na frota.

### 4. Cadastro de Clientes
- Permite o cadastro de clientes, incluindo pessoas físicas e jurídicas.
- Clientes são identificados por CPF (Pessoa Física) ou CNPJ (Pessoa Jurídica).
- Regra RN6: Clientes não podem estar duplicados.

### 5. Alteração de Cliente
- Permite a alteração de informações de clientes já cadastrados.

### 6. Aluguel de Veículo
- Permite o aluguel de veículos para clientes, tanto pessoas físicas quanto jurídicas.
- Regra RN4: Considera aluguel em horas quebradas como uma diária completa.
- Regra RN5: Veículos alugados não podem estar disponíveis.

### 7. Devolução de Veículo
- Permite a devolução de veículos alugados por clientes.
- Regra RN3: Registra o local, data e horário da devolução.
- Regra RN7: Define descontos para clientes com aluguéis longos.

## Regras de Negócio

### 1. Tipos de Veículos
- Os tipos de veículos considerados são PEQUENO, MEDIO e SUV (Regra RN2).
- Valores base da diária por tipo de veículo:
  - PEQUENO: R$ 100,00
  - MEDIO: R$ 150,00
  - SUV: R$ 200,00

### 2. Aluguéis e Devoluções
- Aluguéis e devoluções devem registrar local, data e horário (Regra RN3).
- Aluguéis em horas quebradas contam como diária completa (Regra RN4).
- Veículos alugados não podem estar disponíveis para aluguel (Regra RN5).

### 3. Clientes
- Clientes são identificados por CPF (Pessoa Física) ou CNPJ (Pessoa Jurídica).
- Descontos são aplicados com base na duração do aluguel:
- Cliente Pessoa Física com mais de 5 diárias: 5% de desconto (Regra RN7).
- Cliente Pessoa Jurídica com mais de 3 diárias: 10% de desconto (Regra RN7).


### Desenvolvimento do projeto
- Menu Interativo com o cliente.
- Deve ser melhorado em relação aos tratamentos de exceções para um menu mais "limpo" para o cliente.

