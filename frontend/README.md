# Frontend Angular – Agendamento de Transferências

Este projeto foi gerado com Angular CLI.

## Pré-requisitos

- Node.js 16+
- Angular CLI (npm install -g @angular/cli)

## Instalação e Execução

```bash
cd frontend
npm install
ng serve --proxy-config proxy.conf.json
```

## Testes

### Unitários
```bash
npm run test
```

### E2E (Cypress)
```bash
npm run cypress:open  # abre GUI do Cypress
npm run cypress:run   # executa em modo headless
```

O app ficará disponível em http://localhost:4200 e as chamadas à API serão redirecionadas para http://localhost:8080.
