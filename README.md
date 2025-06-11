# Vehicle Management API

## Português

### Introdução

Este é um sistema de gerenciamento de veículos desenvolvido em Spring Boot que permite o cadastro e gerenciamento de usuários, vendedores, veículos e histórico de vendas. O sistema oferece uma API RESTful completa para operações CRUD em todas as entidades principais.

### Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Docker & Docker Compose**

### Como Executar o Projeto

#### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose

#### Passos para execução

1. **Clone o repositório**
```bash
git clone <repository-url>
cd projeto-poo-pi3-grupo-3
```

2. **Inicie o banco de dados PostgreSQL**
```bash
docker-compose up -d
```

3. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Documentação da API

#### Usuários (`/users`)

##### GET `/users`
- **Descrição**: Lista todos os usuários
- **Resposta**: Array de objetos UserModel

##### GET `/users/{id}`
- **Descrição**: Busca usuário por ID
- **Parâmetros**: 
  - `id` (Long) - ID do usuário
- **Resposta**: Objeto UserModel

##### POST `/users`
- **Descrição**: Cria novo usuário
- **Body**:
```json
{
  "name": "string",
  "email": "string",
  "phoneNumber": "string",
  "cpf": "string",
  "birthDate": "string",
  "password": "string"
}
```

##### POST `/users/{userId}/favorite/{vehicleId}`
- **Descrição**: Adiciona veículo aos favoritos do usuário
- **Parâmetros**:
  - `userId` (Long) - ID do usuário
  - `vehicleId` (Long) - ID do veículo

##### PUT `/users/{id}`
- **Descrição**: Atualiza dados do usuário
- **Parâmetros**: 
  - `id` (Long) - ID do usuário
- **Body**: Objeto UserModel com campos a serem atualizados

##### DELETE `/users/{id}`
- **Descrição**: Remove usuário
- **Parâmetros**: 
  - `id` (Long) - ID do usuário

#### Vendedores (`/sellers`)

##### GET `/sellers`
- **Descrição**: Lista todos os vendedores
- **Resposta**: Array de objetos SellerModel

##### GET `/sellers/{id}`
- **Descrição**: Busca vendedor por ID
- **Parâmetros**: 
  - `id` (Long) - ID do vendedor
- **Resposta**: Objeto SellerModel

##### POST `/sellers`
- **Descrição**: Cria novo vendedor
- **Body**:
```json
{
  "name": "string",
  "email": "string",
  "phoneNumber": "string",
  "cpf": "string",
  "birthDate": "string",
  "password": "string"
}
```

##### PUT `/sellers/{id}`
- **Descrição**: Atualiza dados do vendedor
- **Parâmetros**: 
  - `id` (Long) - ID do vendedor
- **Body**: Objeto SellerModel com campos a serem atualizados

##### DELETE `/sellers/{id}`
- **Descrição**: Remove vendedor
- **Parâmetros**: 
  - `id` (Long) - ID do vendedor

#### Veículos (`/vehicles`)

##### GET `/vehicles`
- **Descrição**: Lista todos os veículos
- **Resposta**: Array de objetos VehicleModel

##### GET `/vehicles/{id}`
- **Descrição**: Busca veículo por ID
- **Parâmetros**: 
  - `id` (Long) - ID do veículo
- **Resposta**: Objeto VehicleModel

##### POST `/vehicles`
- **Descrição**: Cria novo veículo
- **Body**:
```json
{
  "vehicleType": "string",
  "model": "string",
  "brand": "string",
  "price": 0.0,
  "fuelType": "string",
  "transmissionType": "string",
  "photo": "string",
  "color": "string"
}
```

##### PUT `/vehicles/{id}`
- **Descrição**: Atualiza dados do veículo
- **Parâmetros**: 
  - `id` (Long) - ID do veículo
- **Body**: Objeto VehicleModel com campos a serem atualizados

##### DELETE `/vehicles/{id}`
- **Descrição**: Remove veículo
- **Parâmetros**: 
  - `id` (Long) - ID do veículo

#### Histórico de Vendas (`/saleHistory`)

##### GET `/saleHistory`
- **Descrição**: Lista todo o histórico de vendas
- **Resposta**: Array de objetos SaleHistoryModel

##### GET `/saleHistory/{id}`
- **Descrição**: Busca registro de venda por ID
- **Parâmetros**: 
  - `id` (Long) - ID do registro
- **Resposta**: Objeto SaleHistoryModel

##### POST `/saleHistory/insertIntoHistory/{sellerId}/{vehicleId}`
- **Descrição**: Cria novo registro de venda
- **Parâmetros**:
  - `sellerId` (Long) - ID do vendedor
  - `vehicleId` (Long) - ID do veículo
- **Body**:
```json
{
  "city": "string",
  "state": "string"
}
```

##### PUT `/saleHistory/{id}`
- **Descrição**: Atualiza registro de venda
- **Parâmetros**: 
  - `id` (Long) - ID do registro
- **Body**:
```json
{
  "sellerId": 0,
  "vehicleId": 0,
  "state": "string",
  "city": "string"
}
```

### Códigos de Status HTTP

- `200 OK` - Operação realizada com sucesso
- `400 Bad Request` - Dados inválidos ou parâmetros incorretos
- `404 Not Found` - Recurso não encontrado
- `204 No Content` - Operação de exclusão realizada com sucesso

### Tratamento de Exceções

O sistema possui tratamento personalizado para:
- `InvalidIdParameter` - ID inválido
- `ModelNotFoundException` - Entidade não encontrada
- `NullOrEmptyFieldsException` - Campos obrigatórios vazios
- `InvalidNumberField` - Valores numéricos inválidos

---

## English

### Introduction

This is a vehicle management system developed in Spring Boot that allows registration and management of users, sellers, vehicles, and sales history. The system provides a complete RESTful API for CRUD operations on all main entities.

### Technologies Used

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Docker & Docker Compose**

### How to Run the Project

#### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose

#### Execution Steps

1. **Clone the repository**
```bash
git clone <repository-url>
cd projeto-poo-pi3-grupo-3
```

2. **Start PostgreSQL database**
```bash
docker-compose up -d
```

3. **Run the application**
```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`

### API Documentation

#### Users (`/users`)

##### GET `/users`
- **Description**: List all users
- **Response**: Array of UserModel objects

##### GET `/users/{id}`
- **Description**: Find user by ID
- **Parameters**: 
  - `id` (Long) - User ID
- **Response**: UserModel object

##### POST `/users`
- **Description**: Create new user
- **Body**:
```json
{
  "name": "string",
  "email": "string",
  "phoneNumber": "string",
  "cpf": "string",
  "birthDate": "string",
  "password": "string"
}
```

##### POST `/users/{userId}/favorite/{vehicleId}`
- **Description**: Add vehicle to user's favorites
- **Parameters**:
  - `userId` (Long) - User ID
  - `vehicleId` (Long) - Vehicle ID

##### PUT `/users/{id}`
- **Description**: Update user data
- **Parameters**: 
  - `id` (Long) - User ID
- **Body**: UserModel object with fields to be updated

##### DELETE `/users/{id}`
- **Description**: Delete user
- **Parameters**: 
  - `id` (Long) - User ID

#### Sellers (`/sellers`)

##### GET `/sellers`
- **Description**: List all sellers
- **Response**: Array of SellerModel objects

##### GET `/sellers/{id}`
- **Description**: Find seller by ID
- **Parameters**: 
  - `id` (Long) - Seller ID
- **Response**: SellerModel object

##### POST `/sellers`
- **Description**: Create new seller
- **Body**:
```json
{
  "name": "string",
  "email": "string",
  "phoneNumber": "string",
  "cpf": "string",
  "birthDate": "string",
  "password": "string"
}
```

##### PUT `/sellers/{id}`
- **Description**: Update seller data
- **Parameters**: 
  - `id` (Long) - Seller ID
- **Body**: SellerModel object with fields to be updated

##### DELETE `/sellers/{id}`
- **Description**: Delete seller
- **Parameters**: 
  - `id` (Long) - Seller ID

#### Vehicles (`/vehicles`)

##### GET `/vehicles`
- **Description**: List all vehicles
- **Response**: Array of VehicleModel objects

##### GET `/vehicles/{id}`
- **Description**: Find vehicle by ID
- **Parameters**: 
  - `id` (Long) - Vehicle ID
- **Response**: VehicleModel object

##### POST `/vehicles`
- **Description**: Create new vehicle
- **Body**:
```json
{
  "vehicleType": "string",
  "model": "string",
  "brand": "string",
  "price": 0.0,
  "fuelType": "string",
  "transmissionType": "string",
  "photo": "string",
  "color": "string"
}
```

##### PUT `/vehicles/{id}`
- **Description**: Update vehicle data
- **Parameters**: 
  - `id` (Long) - Vehicle ID
- **Body**: VehicleModel object with fields to be updated

##### DELETE `/vehicles/{id}`
- **Description**: Delete vehicle
- **Parameters**: 
  - `id` (Long) - Vehicle ID

#### Sales History (`/saleHistory`)

##### GET `/saleHistory`
- **Description**: List all sales history
- **Response**: Array of SaleHistoryModel objects

##### GET `/saleHistory/{id}`
- **Description**: Find sales record by ID
- **Parameters**: 
  - `id` (Long) - Record ID
- **Response**: SaleHistoryModel object

##### POST `/saleHistory/insertIntoHistory/{sellerId}/{vehicleId}`
- **Description**: Create new sales record
- **Parameters**:
  - `sellerId` (Long) - Seller ID
  - `vehicleId` (Long) - Vehicle ID
- **Body**:
```json
{
  "city": "string",
  "state": "string"
}
```

##### PUT `/saleHistory/{id}`
- **Description**: Update sales record
- **Parameters**: 
  - `id` (Long) - Record ID
- **Body**:
```json
{
  "sellerId": 0,
  "vehicleId": 0,
  "state": "string",
  "city": "string"
}
```

### HTTP Status Codes

- `200 OK` - Operation completed successfully
- `400 Bad Request` - Invalid data or incorrect parameters
- `404 Not Found` - Resource not found
- `204 No Content` - Delete operation completed successfully

### Exception Handling

The system has custom handling for:
- `InvalidIdParameter` - Invalid ID
- `ModelNotFoundException` - Entity not found
- `NullOrEmptyFieldsException` - Required fields empty
- `InvalidNumberField` - Invalid numeric values

### Database Configuration

The application uses PostgreSQL as the database. The connection configuration is in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api
spring.datasource.username=postgres
spring.datasource.password=root
```

### Project Structure

```
src/
├── main/
│   ├── java/br/unicap/si/poo/project/demo/
│   │   ├── abstractsClasses/     # Abstract classes
│   │   ├── controller/           # REST controllers
│   │   ├── dto/                  # Data Transfer Objects
│   │   ├── Exception/            # Custom exceptions
│   │   ├── model/                # Entity models
│   │   ├── repository/           # JPA repositories
│   │   ├── service/              # Business logic
│   │   └── utils/                # Utility classes
│   └── resources/
│       └── application.properties
└── test/
    └── java/                     # Test classes
```