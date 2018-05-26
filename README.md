# Bankslip

[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/vandersozc/Bankslip/blob/master/LICENCA)
[![Build Status](https://travis-ci.com/vandersozc/Bankslip.svg?branch=master)](https://travis-ci.com/vandersozc/Bankslip)
[![GitHub forks](https://img.shields.io/github/forks/vandersozc/Bankslip.svg)](https://github.com/vandersozc/Bankslip/network)
[![GitHub stars](https://img.shields.io/github/stars/vandersozc/Bankslip.svg)](https://github.com/vandersozc/Bankslip/stargazers)
[![SonarQube Coverage](https://img.shields.io/sonar/http/sonar.petalslink.com/org.ow2.petals%3Apetals-se-ase/coverage.svg)](https://sonarcloud.io/dashboard?id=com.vandersoncamp%3Abankslip)

-------
<p align="center">
    <a href="#projeto">Projeto</a> &bull;
    <a href="#instalacao">Instalação</a> &bull;
    <a href="#tests">Testes e Cobertura</a> &bull;
</p>
-------

## Projeto

Serviço de API Rest para geração de boletos. Pode ser consumido por um módulo de um sistema, onde é possível gerar um boleto, pagar um boleto, cancelar um boleto e calcular os juros conforme a data de vencimento.


## Instalação

Este projeto utiliza [Maven](https://maven.apache.org/) para build e gerenciamento das dependências e foi construído utilizando a stack do [WildFly Swarm](http://wildfly-swarm.io/). Executar o build através da linha de comando na pasta raiz do projeto:
```
mvn clean wildfly-swarm:run
```

## Testes e Cobertura

Este projeto utiliza [JUnit 5](https://junit.org/junit5/) para testes unitários e de integração. Você pode executar os testes a qualquer momento. Tudo o que você precisa fazer é executar a linha de comando na pasta raiz do projeto:
```
mvn clean test
```

## EndPoints

EndPoints disponíneis para utilização do serviço.

Geração do Boleto:
POST: http://localhost:8080/rest/bankslips
```
{
	"due_date": "2018-05-10",
	"total_in_cents": "1500",
	"customer": "Bankslip Company",
	"status": "PENDING"
}
```

Lista de Boletos:
GET: http://localhost:8080/rest/bankslips/
```
[
	{
		"id": "77a2d41e-4b50-42e4-a5fd-32ed85bd3bb2",
		"due_date": "2018-05-04",
		"total_in_cents": 15000.000,
		"customer": "Bankslip Company",
		"status": "PENDING"
	},
	{
		"id": "5b5ab681-4300-4757-9893-bf4aa63eede5",
		"due_date": "2018-05-10",
		"total_in_cents": 10.000,
		"customer": "Bankslip Company",
		"status": "PENDING"
	}
]
```

Detalhes do Boleto:
GET: http://localhost:8080/rest/bankslips/{id}
```
{
    "id": "77a2d41e-4b50-42e4-a5fd-32ed85bd3bb2",
    "due_date": "2018-05-04",
    "total_in_cents": 15000.000,
    "customer": "Bankslip Company",
    "status": "PENDING"
}
```

Pagar um Boleto:
PUT: http://localhost:8080/rest/bankslips/{id}
```
{
    "status": "PAID"
}
```

Cancelar um Boleto:
PUT: http://localhost:8080/rest/bankslips/{id}
```
{
    "status": "CANCELED"
}
```

## Licença
Este projeto está licenciado sob os termos da licença do MIT. Veja o arquivo LICENÇA.