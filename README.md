# Trabalho Prático Intermediário - Banco de Dados com Operações CRUD

## **Disciplina**: Algoritmos e Estruturas de Dados III

### Professor: Diego Silva Caldeira Rocha

Este repositório contém o código desenvolvido para o Trabalho Prático Intermediário da disciplina Algoritmos e Estruturas de Dados III. O objetivo deste trabalho é realizar operações CRUD (Create, Read, Update, Delete) em dados sobre ecossistemas tecnológicos e suas inter-relações, utilizando um arquivo CSV como base e implementando a persistência dos dados em um arquivo conforme as especificações dadas.

## 📝 Descrição do Projeto

O projeto envolve a manipulação de dados tecnológicos, utilizando um arquivo CSV fornecido pelo [Stack Overflow Tag Network](https://www.kaggle.com/datasets/stackoverflow/stack-overflow-tag-network?select=stack_network_nodes.csv). O arquivo contém dados sobre tecnologias e suas relações. O programa desenvolvido deve criar um banco de dados em um arquivo a partir desses dados, possibilitando as seguintes operações:

1. **Create**: Criação do banco de dados com base nos dados do CSV, gerando um arquivo conforme descrito nas especificações.
2. **Read**: Exibição organizada dos dados do banco no console, omitindo registros removidos logicamente e dados inválidos.
3. **Update**: Atualização de um campo específico de um registro de dados com valores fornecidos pelo usuário.
4. **Delete**: Remoção lógica de um registro de dados.
5. **Undelete**: Recuperação de registros que foram removidos logicamente.
6. **Insert**: Inserção de novos registros de dados no banco.

## 🎲 Estrutura do Registro de Dados

O arquivo de dados gerado contém registros com os seguintes campos:

- **Cabeçalho**:
  - ```status```: Indica a consistência do arquivo (0 = inconsistente, 1 = consistente).
  - ```proxRRN```: Próximo número relativo de registro (RRN) disponível.
  - ```nroTecnologias```: Número de tecnologias armazenadas.
  - ```nroParesTecnologias```: Número de pares de tecnologias (origem e destino) armazenados.
- **Dados**:
  - ```removido```: Indica se o registro está logicamente removido (0 = não, 1 = sim).
  - ```grupo```: Grupo da tecnologia de origem.
  - ```popularidade```: Total de acessos no Stack Overflow para a tecnologia.
  - ```peso```: Frequência de aparição conjunta de duas tecnologias no Stack Overflow.
  - ```nomeTecnologiaOrigem```: Nome da tecnologia de origem.
  - ```nomeTecnologiaDestino```: Nome da tecnologia de destino.

## ⚒️ Tecnologias Usadas

- **Linguagem**: Java
- **Bibliotecas**: Bibliotecas nativas do Java para manipulação de arquivos e processamento de dados CSV.

## 🎯 Funcionalidades

- **Criação do banco de dados**: A partir de um arquivo CSV, gera-se um banco de dados contendo informações sobre tecnologias e suas inter-relações.
- **Exibição dos dados**: Exibe os dados armazenados de forma organizada no console, omitindo registros removidos ou inválidos.
- **Atualização de dados**: Permite a atualização de campos específicos dos registros existentes.
- **Remoção lógica de registros**: Marca registros como removidos sem apagá-los fisicamente do banco de dados.
- **Recuperação de registros removidos**: Restaura registros que foram removidos logicamente.
- **Inserção de novos registros**: Adiciona novos registros ao banco de dados, atualizando o cabeçalho e o número de tecnologias armazenadas.

> [!NOTE]
> Observações
>
> - O uso de interface gráfica é opcional. Neste projeto, todas as interações são feitas via console.
> - O projeto foi desenvolvido em equipe, seguindo boas práticas de modularização para facilitar a reutilização dos métodos.
> - Cada operação altera o arquivo do banco de dados, garantindo a persistência das informações.

*A aplicação será apresentada em laboratório, com a execução de todas as funcionalidades desenvolvidas.*
