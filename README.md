# Sistema de Agendamento de Transferências Financeiras

## Visão Geral
Este projeto implementa uma API em Spring Boot (Java 11) e um front-end em Angular 15 para:
- Agendar transferências financeiras.
- Calcular taxa conforme tabela de dias de agendamento.
- Listar todos os agendamentos.

## Decisões Arquiteturais
- **Backend**: Spring Boot + Spring Data JPA  
  - H2 em memória para persistência rápida.
  - Camadas: Controller → Service → Repository → Model.
-- **Frontend**: Angular 15  
  - HttpClient para comunicação com a API.
  - Angular CLI para build e serve.
- **Build & Gerenciamento**  
  - Maven no backend, Node (npm/yarn) no front.
  - Java 11, Node 16+, Vue CLI 4.x/Vite 4.x.
- **Controle de Versão**  
  - Branch `main` para produção, futuras features em branches nomeadas.

## Ferramentas
- Java 11
- Maven 3.6+
- Node.js 16+
- Angular 15 + Angular CLI
- Cypress para testes E2E
- IDE de sua preferência

## Como Rodar

### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
# API rodando em http://localhost:8080
```

### Frontend
```bash
cd frontend
npm install
npm start
# App em http://localhost:4200
```

## Endpoints Principais
- `POST /api/transferencias` – cria um agendamento
- `GET  /api/transferencias` – lista todos os agendamentos
