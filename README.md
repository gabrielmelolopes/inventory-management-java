# Inventory Management Java

Sistema simples de gerenciamento de estoque desenvolvido com Java, JDBC e PostgreSQL.

## Tecnologias utilizadas

- Java 17
- JDBC
- PostgreSQL
- Docker
- Maven

## Funcionalidades

- Adicionar produtos
- Remover produtos
- Buscar produtos
- Listar estoque

## Estrutura do projeto

src/
├── app
├── model
├── repository
├── service
└── exception

## Como executar

### 1. Subir PostgreSQL no Docker

```bash
docker run --name postgres-db \
-e POSTGRES_DB=estoque_db \
-e POSTGRES_USER=admin \
-e POSTGRES_PASSWORD=admin \
-p 5433:5432 \
-v postgres_data:/var/lib/postgresql/data \
-d postgres:16
```

---

### 2. Criar tabelas

```sql
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    quantidade INTEGER NOT NULL
);
```

```sql
CREATE TABLE historico_vendas (
    id SERIAL PRIMARY KEY,
    produto_id INT NOT NULL,
    quantidade_vendida INT NOT NULL,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);
```
---

### 3. Executar projeto

```bash
mvn compile
mvn exec:java
```

## Conceitos praticados

- Programação Orientada a Objetos
- Repository Pattern
- JDBC
- CRUD
- PostgreSQL
- Docker
- Arquitetura em camadas
- PreparedStatement

## Autor

Gabriel Melo Lopes
