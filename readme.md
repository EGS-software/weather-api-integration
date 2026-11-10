[![en](https://img.shields.io/badge/lang-en-blue)](README-ing.md)

# Weather API Integration

## 🎓 Informações Acadêmicas

**Instituição:** Universidade Regional do Noroeste do Estado do Rio Grande do Sul (UNIJUÍ)  
**Curso:** Bacharelado em Engenharia de Software  
**Disciplina:** Reuso de Software  
**Professor:** Junior Marcos Bandeira  
**Trabalho:** Trabalho sobre Integração de APIs  
**Integrantes:** João Alfredo Williges Cunha, João Victor Benetti Filipim, Pedro Schmitt.

**Objetivo Acadêmico:**  
Este projeto foi desenvolvido como requisito avaliativo da disciplina, com o objetivo de demonstrar a aplicação prática de integração com serviços externos via OpenFeign, persistência de histórico e payload original em banco de dados NoSQL (MongoDB), tolerância a falhas e cobertura com testes de integração automatizados.

Além disso, como é um trabalho de graduação, este projeto também atende a critérios acadêmicos relevantes para a disciplina, incluindo a integração com APIs terceiras, o uso de padrões de projeto e arquitetura, a organização da aplicação em camadas, o registro de respostas em banco de dados e a validação automatizada por testes.

---

## 📖 Visão geral

Sistema de integração com a API pública do OpenWeather para consultar dados meteorológicos e expor uma API REST interna padronizada para consumo por aplicações clientes.

Este projeto foi desenvolvido em Java 21 com Spring Boot 3 e tem como objetivo centralizar a integração com uma API externa de clima, manter a lógica de negócio bem separada, reduzir boilerplate com Lombok e documentar os endpoints com SpringDoc OpenAPI.

A proposta principal é demonstrar boas práticas na construção de integrações com serviços externos, incluindo:
- Consumo declarativo de APIs via OpenFeign;
- Persistência de dados com MongoDB;
- Uso de containers para infraestrutura local com Docker Compose;
- Testes automatizados com Testcontainers, JUnit 5 e Mockito;
- Documentação interativa com Swagger/OpenAPI;
- Registro do payload original e do histórico de consultas.

## 🧩 Classificação da Integração

Conforme os critérios de avaliação do projeto, a integração realizada classifica-se da seguinte forma:
- **Contexto de Negócio:** Integração de consumo de serviços de terceiros (Third-party API) / Enriquecimento de Dados. A aplicação atua como um sistema cliente que busca dados brutos na OpenWeather e os transforma para um formato interno padronizado.
- **Padrão Arquitetural:** API RESTful.
- **Padrão de Comunicação:** Síncrona baseada em Request-Response. A integração ocorre em tempo real via protocolo HTTP, utilizando o cliente declarativo Spring Cloud OpenFeign para bloqueio e aguardo da resposta externa.

## ✅ Critérios acadêmicos atendidos

Este projeto foi estruturado para contemplar os principais requisitos esperados em um trabalho de integração de APIs no contexto da disciplina:
- Integração com API pública externa;
- Uso de cliente HTTP declarativo com OpenFeign;
- Organização por camadas da aplicação;
- Persistência de dados em MongoDB;
- Armazenamento do payload original recebido da API externa;
- Registro da data da consulta;
- Conversão de dados para formato interno padronizado;
- Documentação automática da API;
- Testes automatizados;
- Uso de variáveis de ambiente para credenciais sensíveis;
- Execução local com Docker Compose;
- Estrutura apropriada para apresentação e avaliação acadêmica.

## 🛠️ Stack principal
- Java 21
- Spring Boot 3
- Spring Cloud OpenFeign
- Lombok
- MongoDB
- Docker Compose
- SpringDoc OpenAPI (Swagger)
- JUnit 5
- Mockito
- Testcontainers

## 🏗️ Arquitetura

A arquitetura seguiu o modelo Point-to-Point, onde ela consome a OpenWeatherAPI. Além disso, 
a aplicação foi organizada em camadas para manter a responsabilidade de cada parte do sistema bem separada:
- **Controller:** expõe os endpoints HTTP;
- **Service:** orquestra a lógica de negócio;
- **Client/Integration (Feign):** realiza a comunicação com a API externa;
- **DTOs:** representam os payloads de entrada e saída;
- **Domain:** entidades do domínio interno;
- **Config:** configuração da aplicação e beans do Spring;
- **Exceptions:** tratamento centralizado de erros;
- **Repository:** acesso ao MongoDB.

## 🌐 Integração com API externa

O projeto utiliza a API do OpenWeather como provedor de dados meteorológicos.

A integração é feita com Spring Cloud OpenFeign, permitindo consumir a API de forma declarativa e tipada, com a estrutura do projeto organizada para facilitar manutenção e evolução.

Exemplo do endpoint usado pela API externa:
```text
/weather
```
Os dados recebidos são processados e convertidos para um padrão interno da aplicação para garantir consistência e facilitar o uso por outros serviços.

## 💾 Persistência

O projeto utiliza MongoDB como banco de dados NoSQL.
A infraestrutura local pode ser iniciada via Docker Compose, com a imagem `mongo:latest` executando na porta local:
```text
27017
```
A aplicação roda diretamente na máquina host, enquanto o MongoDB é gerenciado pelo Docker Compose.

**Dados persistidos:**
A aplicação salva o histórico de consultas com informações relevantes, incluindo:
- Cidade consultada;
- Descrição meteorológica;
- Temperatura convertida para Celsius;
- Umidade;
- Data da consulta;
- Payload original retornado pela API externa.

Essa estratégia é importante para demonstrar a capacidade de enriquecer as respostas e manter rastreabilidade dos dados consumidos.

## 🧪 Testes

O projeto conta com uma suíte de testes automatizados para garantir a qualidade da integração e do comportamento da API.
Tecnologias utilizadas:
- **Testcontainers** para subir instâncias efêmeras do MongoDB em testes;
- **JUnit 5** para execução dos testes;
- **Mockito** para mock de dependências e simulação do cliente Feign.

Isso permite validar cenários de integração sem depender de uma infraestrutura externa estática.

## 📄 Documentação da API

A documentação da API é gerada com SpringDoc OpenAPI (Swagger), permitindo acesso interativo aos endpoints e testes diretos pela interface web.
A documentação pode ser acessada em:
```text
http://localhost:8080/swagger-ui/index.html
```

## ⚙️ Configuração de ambiente

A aplicação exige uma chave de API da OpenWeather, que deve ser configurada como variável de ambiente do sistema ou da IDE.
Variável obrigatória:
```bash
OPEN_WEATHER_API_KEY
```
A chave é injetada dinamicamente no arquivo `application.yml`, com o formato exato utilizado no projeto:
```yaml
open-weather:
  api-key: ${OPEN_WEATHER_API_KEY}
```
Essa abordagem evita que a credencial real fique exposta no repositório do Git.

## 🚀 Como executar localmente

### Pré-requisitos
Antes de executar o projeto, certifique-se de ter instalado:
- Git
- Java 21
- Maven
- Docker e Docker Compose
- IDE ou terminal Bash

### 1. Clonar o projeto
```bash
git clone https://github.com/EGS-software/weather-api-integration.git
cd weather-api-integration
```

### 2. Configurar a variável de ambiente
No Linux/macOS:
```bash
export OPEN_WEATHER_API_KEY="sua_chave_aqui"
```
No Windows PowerShell:
```powershell
$env:OPEN_WEATHER_API_KEY="sua_chave_aqui"
```

### 3. Iniciar o MongoDB com Docker Compose
```bash
docker compose up -d
```

### 4. Executar a aplicação
```bash
./mvnw spring-boot:run
```

## 📁 Estrutura do projeto

```text
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── jawc/
│   │   │               └── api_clima/
│   │   │                   ├── controller/
│   │   │                   ├── domain/
│   │   │                   ├── dto/
│   │   │                   ├── exception/
│   │   │                   ├── feign/
│   │   │                   ├── repository/
│   │   │                   └── service/
│   │   └── resources/
│   │       └── application.yml
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

## 💡 Exemplos de uso

### Endpoint de consulta do clima
```http
GET /api/weather/Ijui
```

**Resposta esperada:**
```json
{
  "id": "6ad2dfb317d01b13326e8d19",
  "city": "Ijui",
  "description": "scattered clouds",
  "tempInCelsius": 15.01,
  "humidity": 90.0,
  "consultDate": "2026-10-16T23:38:43.422209",
  "originalPayLoad": {
    "name": "Ijuí",
    "main": {
      "temp": 288.16,
      "humidity": 90.0
    },
    "weather": [
      {
        "description": "scattered clouds"
      }
    ]
  }
}
```

## 🛑 Tratamento de erros

A aplicação trata corretamente situações como:
- Cidade não encontrada (Retorna 404 estruturado);
- Falha na comunicação com a API externa (Retorna 500 estruturado).

As exceções são centralizadas por meio de `@RestControllerAdvice` para garantir respostas padronizadas e legíveis.

## 🚀 Possíveis evoluções

Algumas melhorias e extensões futuras para o projeto incluem:
- Cache de consultas com Redis;
- Suporte a múltiplos provedores climáticos;
- Autenticação e autorização para acesso à API;
- CI/CD automatizado.

---
**Desenvolvido por João Alfredo Williges Cunha e grupo.**  
*Projeto voltado à demonstração de integração com serviços externos, boas práticas de backend em Java e uso de tecnologias modernas para desenvolvimento e testes.*
