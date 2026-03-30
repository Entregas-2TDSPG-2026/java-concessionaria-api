# java-concessionaria-api

REST API para gerenciamento de concessionária de veículos, construída com Java e Spring Boot. O projeto simula um backend real usado em sistemas de gestão de vendas, aplicando arquitetura limpa, modelagem relacional de dados e organização em camadas.

---

## Tech Stack

| Tecnologia | Detalhes |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2.4 |
| Persistência | Spring Data JPA + Hibernate |
| Banco de Dados | H2 (in-memory) |
| Validação | Jakarta Validation (Bean Validation) |
| Boilerplate | Lombok |
| Build Tool | Maven 3.9 |

---

## Estrutura do Projeto

```
src/main/java/br/com/fiap/
├── controllers/        Controladores REST (camada HTTP)
├── services/           Camada de lógica de negócio
├── repositories/       Interfaces Spring Data JPA
├── models/             Entidades JPA
│   └── enums/          Enums de domínio (VehicleType, VehicleStatus, VehicleColor, CarBrand, MotorcycleBrand)
└── dtos/               Data Transfer Objects (request/response)
```

---

## Modelo de Domínio

### Entidades

| Entidade | Descrição |
|---|---|
| Vehicle | Representa um veículo (carro ou moto) com marca, modelo, ano, preço, km, cor e status |
| Customer | Cliente com nome, CPF, e-mail e telefone |
| Sale | Venda que vincula um veículo a um cliente, com preço final e data automática |

### Relacionamentos

| Relacionamento | Tipo |
|---|---|
| Sale → Vehicle | Many-to-One |
| Sale → Customer | Many-to-One |

### Enums

| Enum | Valores |
|---|---|
| VehicleType | `CAR`, `MOTORCYCLE` |
| VehicleStatus | `DISPONIVEL`, `VENDIDO` |
| VehicleColor | `PRETO`, `BRANCO`, `PRATA`, `CINZA`, `VERMELHO`, `AZUL`, `OUTRA` |
| CarBrand | `TOYOTA`, `VOLKSWAGEN`, `CHEVROLET`, `FIAT`, `FORD`, `HONDA`, `HYUNDAI`, `NISSAN`, `RENAULT`, `PEUGEOT`, `CITROEN`, `JEEP`, `MITSUBISHI`, `KIA`, `BMW`, `MERCEDES_BENZ`, `AUDI` |
| MotorcycleBrand | `HONDA`, `YAMAHA`, `SUZUKI`, `KAWASAKI`, `BMW`, `HARLEY_DAVIDSON`, `DUCATI`, `KTM`, `TRIUMPH`, `ROYAL_ENFIELD` |

---

## Endpoints da API

### Veículos — `/vehicles`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/vehicles` | Listar todos os veículos |
| GET | `/vehicles/{id}` | Buscar veículo por ID |
| POST | `/vehicles` | Criar um novo veículo |
| PUT | `/vehicles/{id}` | Atualizar um veículo |
| DELETE | `/vehicles/{id}` | Excluir um veículo |

### Carros — `/cars`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/cars` | Listar apenas carros |
| GET | `/cars/{id}` | Buscar carro por ID |

### Motos — `/motorcycles`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/motorcycles` | Listar apenas motos |
| GET | `/motorcycles/{id}` | Buscar moto por ID |

### Clientes — `/customers`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/customers` | Listar todos os clientes |
| GET | `/customers/{id}` | Buscar cliente por ID |
| POST | `/customers` | Criar um novo cliente |
| PUT | `/customers/{id}` | Atualizar um cliente |
| DELETE | `/customers/{id}` | Excluir um cliente |

### Vendas — `/sales`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/sales` | Listar todas as vendas |
| GET | `/sales/{id}` | Buscar venda por ID |
| POST | `/sales` | Registrar uma nova venda |
| DELETE | `/sales/{id}` | Excluir uma venda (veículo volta a ficar disponível) |

---

## Como Executar

### Pré-requisitos

- Java 17
- Maven 3.9+

### Executar localmente

```bash
git clone https://github.com/felipeflosii/java-concessionaria-api.git
cd java-concessionaria-api
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`. O banco H2 em memória é provisionado automaticamente — nenhuma configuração externa de banco é necessária.

### Console H2

Acesse `http://localhost:8080/h2-console` com as credenciais:

- **JDBC URL:** `jdbc:h2:mem:vehiclesdb`
- **Username:** `sa`
- **Password:** *(vazio)*

---

## Exemplos de Requisições

**Criar um veículo (carro):**
```json
POST /vehicles

{
  "type": "CAR",
  "carBrand": "TOYOTA",
  "model": "Corolla",
  "version": "XEi",
  "vehicleYear": 2022,
  "price": 120000.00,
  "mileage": 15000,
  "color": "PRATA",
  "status": "DISPONIVEL"
}
```

**Criar um veículo (moto):**
```json
POST /vehicles

{
  "type": "MOTORCYCLE",
  "motorcycleBrand": "HONDA",
  "model": "CB 500F",
  "version": "ABS",
  "vehicleYear": 2023,
  "price": 35000.00,
  "mileage": 0,
  "color": "PRETO",
  "status": "DISPONIVEL"
}
```

**Criar um cliente:**
```json
POST /customers

{
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao@email.com",
  "phone": "(11) 99999-9999"
}
```

**Registrar uma venda:**
```json
POST /sales

{
  "vehicleId": 1,
  "customerId": 1,
  "finalPrice": 118000.00
}
```

> Ao registrar uma venda, o veículo automaticamente tem seu status alterado para `VENDIDO`. Ao excluir uma venda, o veículo volta ao status `DISPONIVEL`.

---

## Desenvolvedores

| Nome | RM |
|---|---|
| Luiz Felipe Flosi | 563197 |
| Arthur Brito da Silva | 562085 |
| Pedro Brum Lopes | 561780 |
