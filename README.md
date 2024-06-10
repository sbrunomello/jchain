# jchain - Blockchain Private Network

## Descrição

Este projeto é uma blockchain privada desenvolvida em Java 21. O projeto visa ser um ambiente de prototipagem para explorar a tecnologia blockchain e suas potenciais aplicações em cenários empresariais.

## Funcionalidades Atuais

### 1. Registro de Funcionários
- **Criação de Funcionários:** Permite a criação de novos funcionários com atributos detalhados como nome, email, departamento, posição e data de admissão.
- **Chave Única:** Cada funcionário recebe uma chave única gerada usando um hash criptográfico baseado no ID e no timestamp de criação.

### 2. Registro de Batidas de Ponto
- **Registro de Ponto:** Funcionários podem registrar suas batidas de ponto através de uma API REST.
- **Validação de Chave:** A chave do funcionário é validada para garantir a autenticidade do registro.
- **Data e Hora:** Cada registro de ponto inclui a data e a hora da batida.

### 3. Visualização da Blockchain
- **Visualização dos Blocos:** Endpoints para visualizar todos os blocos na blockchain.
- **Listagem de Batidas de Ponto:** Endpoints para listar todas as batidas de ponto por funcionário.

## Persistência
A blockchain é persistida provisoriamente em arquivos JSON criptografados para evitar a perda de dados. Esta solução é temporária e será evoluída para um sistema mais descentralizado e robusto.

## Futuras Funcionalidades

### Gestão da Cadeia de Suprimentos
- **Rastreamento de Produtos:** Monitoramento de produtos ao longo da cadeia de suprimentos.

### Gerenciamento de Documentos
- **Armazenamento Seguro:** Armazenamento e validação segura de documentos.

### Sistemas de Votação
- **Votação Digital:** Implementação de um sistema seguro e transparente de votação digital.

### Contratos Inteligentes
- **Automatização:** Automatização de processos e acordos legais.

### Gestão de Identidades
- **Identidades Digitais:** Gestão segura de identidades digitais.

### Monitoramento de Ativos
- **Rastreamento de Ativos:** Monitoramento de ativos físicos e digitais.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Lombok**
- **Jackson**
- **Postman (para testes de API)**

## Como Executar o Projeto

### Pré-requisitos
- **Java 21**
- **Maven**

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/sbrunomello/jchain.git

2. Navegue até o diretório do projeto:
   ```bash
   cd jchain

3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run


## Endpoints Disponíveis
**Criar Funcionario:**

    POST /api/employees
    Content-Type: application/json

    {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "department": "TI",
    "position": "Developer",
    "joiningDate": "2023-06-01"
    }

**Registrar Batida:**

    POST /api/attendances
    Content-Type: application/json

    {
    "employeeKey": "employee-unique-key",
    "attendanceTime": "2024-06-09T08:00:00"
    }

Listar batidas:

    GET /api/attendances/{employeeKey}

Visualizar blockchain:

    GET /api/blockchain




## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.



## Contato
- [Instagram](https://www.instagram.com/brunomello.dev/)
- [LinkedIn](https://www.linkedin.com/in/sbrunomello)

Desenvolvido com ❤️ por [Mello](https://www.linkedin.com/in/sbrunomello).

