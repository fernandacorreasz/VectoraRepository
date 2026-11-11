# Vectora Operations

Vectora é uma central operacional para monitoramento de câmeras, sensores de aproximação e frotas autônomas. O painel combina mapa geográfico, telemetria, incidentes, missões e auditoria em uma visão de tempo real simulada.

## Executar

```bash
docker compose up --build
```

Acessos locais:

```text
Frontend: http://localhost:3006
API:      http://localhost:8086
Swagger:  http://localhost:8086/swagger-ui/index.html
Simulador: http://localhost:8096
```

Para restaurar a base:

```bash
docker compose down -v --remove-orphans
docker compose up --build
```

## Perfis

| Usuário | Senha | Contexto |
|---|---|---|
| commander | commander123 | Command Center |
| operator | operator123 | Operador de voo |
| agri_manager | agri123 | Gestão agrícola |
| technician | tech123 | Técnico de campo |
| auditor | audit123 | Auditoria |
| client_viewer | client123 | Cliente em leitura |

A primeira tela da aplicação é um login de laboratório. A senha permanece visível para facilitar a análise dos fluxos de autenticação e autorização entre perfis.

## Módulos

- Centro de monitoramento com mapa operacional.
- Câmeras e sensores de aproximação.
- Frota de drones agrícolas, patrulha, inspeção, resgate e alvo não identificado.
- Telemetria de altitude, velocidade, bateria e sinal.
- Missões e agenda de voo.
- Geofences de operação, trânsito e área restrita.
- Feed de eventos em tempo real simulado.
- Console para simular movimento, aproximação, câmera offline e perda de sinal de drone.
- Configuração de regras de alerta e conectividade.
- Auditoria operacional.

## API

```text
GET  /api/dashboard
GET  /api/cameras
GET  /api/sensors
GET  /api/drones
GET  /api/missions
GET  /api/geofences
GET  /api/events
GET  /api/audit
POST /api/simulation?type=MOTION_DETECTED&source=CAM-003
GET  /v3/api-docs
```

A API utiliza Basic Auth nos endpoints operacionais. O Swagger está disponível no endereço indicado acima.

## Banco e DBeaver

| Campo | Valor |
|---|---|
| Driver | PostgreSQL |
| Host | `127.0.0.1` |
| Porta | `5434` |
| Database | `vectora` |
| Usuário | `postgres` |
| Senha | `vectora_postgres` |
| SSL | `disable` |

O papel operacional criado pela migration é `vectora_app`, com senha `vectora_app`.

## Arquitetura

```text
frontend/       React + Vite + Nginx
backend/        Spring Boot + JDBC + Flyway + Swagger
simulator/      Python HTTP para telemetria simulada
postgres        Persistência operacional
```

Os dados iniciais incluem cinco câmeras, cinco sensores, cinco drones, quatro missões, três geofences, seis eventos e registros de auditoria. A movimentação visual dos marcadores é simulada no painel para reproduzir um centro operacional ao vivo sem depender de serviços externos.
