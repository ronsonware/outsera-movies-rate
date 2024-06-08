# Desafio Outsera 
## Pior filme do Golden Raspberry Awards.

Api desenvolvida para possibilitar a leitura dos indicados e vencedores da categoria de Pior Filme do Golden Raspberry Awards.


## Requisitos do sistema
- Java 17
- Maven 3.8
- Docker 24.0

## Compilação e execução
```bash
# Build project
mvn clean install

# Run project
mvn spring-boot:run

# Run tests
mvn test
```

Para chamada do endpoint pode-se usar o seguinte CURL:

```bash
curl http://localhost:8080/producers/rate-winners
```