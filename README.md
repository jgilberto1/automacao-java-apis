# PROJETO DESAFIO SICREDI TESTE API

## BUGS ENCONTRADOS

https://gitlab.com/jgilbertoqa/automacao-desafio-sicredi-apis/-/issues

## PRÉ-REQUISITOS

Requisitos de software e hardware necessários para executar este projeto de automação

* Java 11 JDK
* Maven 3.5.*
* Intellij IDE
* Plugins do Intellij
    * Cumcuber for java
    * Lombok
    * Ideolog
* Docker
* Docker Compose

## ESTRUTURA DO PROJETO

| Diretório                         | finalidade                                                                                                 | 
|--------------------------------|------------------------------------------------------------------------------------------------------------|
| src\main\java\constants           | atributos constantes para facil compartilhamento e atualização                                              |
| src\main\java\services               | Local onde deve ser criado os objetos que executam requisições e validações das respotas                   |
| src\main\java\support             | Metodos genéricos que apoiam as classes de testes                                                           |
| src\test\java\hooks             | Metodos que executam antes e depois de cada teste (@Before, @After)                                        |
| src\test\java\runner             | Metodo prinicipal que inicia os testes via cucumber                                                         |
| src\test\java\steps              | Local onde deve ser criado as classes que representam os steps definition do cucumber                         |
| src\test\resources\data         | Massa de dados segregada por ambiente, escritos em arquivos yaml                                            |
| src\test\resources\features      | Funcionalidade e cenarios de teste escritos em linguagem DSL (Gherkin language)                               | 


```

## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

* Jackson - Responsável pela leitura de dados de arquivo yaml file
* Gson - Responsável pela serializacao e deserializacao de objetos
* Allure - Responsável pelo report em HTML
* Java Faker - Responsável pela geracao de dados sintéticos
* Rest Assured - Responsável pelos interação com a camada HTTP para teste de API (Json, Soap, Xml)
* Cucumber - Responsável pela especificação executável dos cenários
* AssertJ - Especializado em validações com mais tipos e formatos de verificação
* Lombok - Otimizacao de classes modelos
* Log4j - Responsável pelo Log do projeto

## COMANDO PARA EXECUTAR OS TESTES

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para
rodar os testes automatizados.

```
mvn clean test
```

## COMANDO PARA GERAR EVIDÊNCIAS EM HTML (ALLURE)

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para
gerar as evidências em HTML

```
mvn allure:report
```

## MULTIPLOS COMANDOS

Você também pode mesclar a linha de comando maven com options do cucumber,
sendo assim você pode escolher uma determinada tag que se deseja executar do cucumber,
podendo escolher também a massa de dados que irá utilizar e juntamente aplicar o linha de comando para gerar o report
HTML.

```
mvn clean test -Dcucumber.filter.tags=@Tag -Denv=des allure:report
```

## REEXECUÇÃO DE TESTES

O Projeto por padrão vai reexecutar os testes falhados novamente, somente os testes que falharam anteriormente,
a quantidade de vezes de retentiva pode ser configurado, por padrão será executado novamente 1 vez, se você
quiser aumentar o numero de tentativas você deve passar via linha de comando

```shell
-Dretry={Numero de tentativas} 
```

```shell
mvn clean test -Dcucumber.filter.tags=@positivo -Dretry=2 -Denv=loc
```


## EVIDÊNCIAS

Os arquivos com as evidências ficam localizados na pasta target do projeto, esta pasta só é criada depois da primeira
execução.

```
 Report HTML: target\site\index.html
 Json Cucumber: target\json-cucumber-reports\cucumber.json
 Xml Junit: tagert\xml-junit\junit.xml
```

## LOG DE EXECUÇÃO

Os logs de execução gerados pelo Log4j2 ficam alocados na pasta target/log

## GITLAB CI

Pré-configurada a pipeline para execução com a properties de uat, necessário projeto rodando com servidor externo para execução.

```
src\main\resources\conf\uat.properties
alterar a url.base.
```