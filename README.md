# XY Inc. - Pontos de Interesse #

### Pré-requisitos ###

- Java Development Kit 8
- Maven 3.6
- *MySQL - servidor de banco de dados; 
- H2 - servidor de banco de dados de teste;
- Spring Boot - Framework Spring

**A instalação do MySql é opcional, pois por padrão será executado o banco H2.*

## Passos para execução ##

- Clonar o projeto -> https://github.com/isaquebrb/xy-inc.git
- Importar o repositório no Spring Boot STS e aguardar o build do projeto.
- (Opcional) Configuração para MySQl.
- Rodar a aplicação: Run As > Spring Boot App.
- Testar aplicação.

### Configuração para o MySql ###

- Baixar o MySql e criar um banco vazio.
- Alterar de *test* para *dev* o profile ativo do arquivo *application.properties*.
- Alterar para as configurações do seu banco MySql o arquivo *application-dev.properties*:
```
spring.datasource.url=jdbc:mysql://localhost:{porta_mysql}/{nome_do_seu_banco}
spring.datasource.password={sua_senha}
```
- Executar o comando mvn install

Caso apresente erro com o fuso horário do MySql, adicione *?useTimezone=true&serverTimezone=UTC* após */{nome_do_seu_banco}*
    
## Utilização da aplicação ##

A aplição estará acessível através da URL: http://localhost:8080/points/

Caso não utilize o banco MySql, o banco H2 pode ser acessado pela URL: http://localhost:8080/h2-console

Acesse os endereços das URLs abaixo para testar os serviços:

| Método  | URL                  | Descrição
|---------|----------------------|---------------------------------------------
| GET     | /points/        | Lista todos os pontos cadastrados.
| POST    | /points/         | Cadastra um novo ponto de interesse. Ex JSON: {"name": "Point", "x": 10, "y": 10}
| GET    | /points/findByCoordenates?x={0}&y={1}&distance={2}        | Lista todos os pontos próximos com base nos valores informados. Ex: http://localhost:8080/points/findByCoordenates?x=20&y=10&distance=10.

## Testes ##

- Executar a classe *PointOfInterestUnitsTests* com o JUnit para testar as classes dos serviços utilizado pela aplicação.
- Executar a classe *PointOfInterestEndpointsTests* com o JUnit para testar os endpoints implementados, os recursos utilizados nos controllers.