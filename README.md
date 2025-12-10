# Sistema de Gestão de Orçamentos e Propostas

## 1. Visão Geral

O **Sistema de Gestão de Orçamentos e Propostas** é uma aplicação Java em modo console que permite a profissionalização do processo de vendas de serviços. O sistema gerencia o ciclo de vida completo de uma venda: desde o cadastro de clientes (Pessoa Física e Jurídica) e serviços com histórico de preços, até a emissão, envio e aceitação de propostas comerciais.

O sistema foi desenvolvido com foco em boas práticas de **Programação Orientada a Objetos** e **arquitetura em camadas**, utilizando estratégias avançadas de mapeamento objeto-relacional (ORM) para tratar herança e composição de dados.

### Funcionalidades principais

* **Cadastro de Clientes:** Suporte a PF e PJ com validação de duplicidade (CPF/CNPJ).
* **Gestão de Serviços:** Controle de catálogo e versionamento de preços (Histórico).
* **Fluxo de Proposta:** Máquina de estados para controle do ciclo de vida (Em Elaboração → Enviada → Aceita/Rejeitada).
* **Cálculos Automáticos:** Totalização baseada em itens, quantidades e preços vigentes.
* **Relatórios Gerenciais:** Faturamento por período, histórico do cliente e curva de serviços mais vendidos.
* **Teste de Carga:** Módulo específico para geração de **100.000 registros** para validação de performance e integridade do banco.

## 2. Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Persistência:** JPA / Hibernate Core 5.6
* **Banco de Dados:** MySQL 8
* **Gerenciamento de Dependências:** Maven
* **Testes/Seeding:** Java Faker (Geração de massa de dados)
* **Arquitetura:** MVC + Service + DAO (Data Access Object)

## 3. Modelo de Domínio (JPA)

* **Herança (`Pessoa`):** Utilizada a estratégia `@Inheritance(strategy = InheritanceType.JOINED)`, gerando tabelas normalizadas (`Pessoa`, `cliente_pf`, `cliente_pj`).
* **Composição (`Proposta`):** Uso de `@OneToMany(cascade = CascadeType.ALL)` para que os Itens sejam persistidos automaticamente junto com a Proposta.
* **Encapsulamento:** A lógica de cálculo total e manipulação de status está protegida dentro das classes de domínio e serviços.

### Configuração do Banco de Dados
* **Banco:** `orcamento-propostas`
* **Dialeto:** MySQL 8 Dialect
* **Criação de Tabelas:** Automática via `hbm2ddl.auto`.

## 4. Execução do Projeto

1.  **Preparação do Banco:**
    Acesse seu cliente MySQL e execute:
    ```sql
    CREATE DATABASE `orcamento-propostas`;
    ```
2.  **Configuração:**
    Verifique usuário e senha no arquivo `src/main/resources/META-INF/persistence.xml`.
3.  **Executando a Aplicação:**
    O projeto possui classes específicas no pacote `main` para diferentes finalidades:
    * **`main.TesteCarga`**: Execute primeiro para popular o banco com 100.000 registros e validar a performance.
    * **`main.MenuSistema`**: Interface principal para cadastros, movimentação de propostas e consulta de preços.
    * **`main.MainRelatorios`**: Execute para gerar os relatórios detalhados de faturamento e histórico no console.

## 4. Considerações Finais

Este projeto demonstra:
* Aplicação sólida dos pilares da POO (Polimorfismo, Herança, Encapsulamento).
* Arquitetura em camadas desacoplada (Services não dependem de como o DAO conecta).
* Uso eficiente de JPA para persistência em banco relacional.
* Tratamento de performance em operações de lote (Batch Insert).
* Código limpo e organizado.

## 5. Estrutura do Projeto

A organização do projeto segue o padrão em camadas, promovendo separação de responsabilidades e facilidade de manutenção.

```text
src/main/java/
├── dao/
│   ├── GenericDAO.java              
│   ├── DaoImplementacao.java         
│   ├── ClientePessoaFisicaDAO.java
│   ├── ClientePessoaJuridicaDAO.java
│   ├── ServicoDAO.java
│   └── PropostaDAO.java
│
├── main/
│   ├── MenuSistema.java               # Menu interativo para o usuário
│   ├── MainRelatorios.java        
│   ├── TesteCarga.java                # Script para gerar 100k registros
│   └── Main.java                    
│
├── model/
│   ├── Pessoa.java                   
│   ├── ClientePessoaFisica.java
│   ├── ClientePessoaJuridica.java
│   ├── Servico.java                 
│   ├── Preco.java                    
│   ├── Proposta.java                  
│   ├── ItemProposta.java
│   └── StatusProposta.java           
│
├── service/
│   ├── ClienteService.java            
│   ├── ServicoService.java           
│   ├── PropostaService.java           
│   └── RelatorioService.java          
│
└── util/
    └── JPAUTIL.java                   
