# Nimbus API - Previsão de Desastres

Este é um projeto de API desenvolvido em **Java** utilizando o framework **Spring Boot**.  

Nossa solução para a Global Solution consiste em uma aplicação voltada para a previsão de possíveis desastres naturais, levando em consideração a localização do usuário.

A aplicação coleta dados atualizados da previsão do tempo e a posição geográfica do usuário, utilizando essas informações junto a um modelo de inteligência artificial para prever a chance de ocorrência de desastres naturais em sua região.

Com base nessa previsão, o sistema envia alertas classificados em três níveis: baixo, médio e grave permitindo avisar cada pessoa com precisão e antecedência.

Além disso, o usuário poderá adicionar grupos de localização, como a casa de familiares, o local de trabalho ou outros pontos de interesse. Dessa forma, ele receberá alertas personalizados para essas regiões também, ajudando na prevenção e no planejamento diante de possíveis enchentes ou outros eventos climáticos extremos.

## Equipe

- Gustavo Dias da Silva Cruz - RM556448
- Júlia Medeiros Angelozi - RM556364
- Felipe Ribeiro Tardochi da Silva - RM555100

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **Oracle XE** (banco de dados relacional)
- **Spring Data JPA**
- **Spring Events**
- **Jackson Databind**
- **Spring Cache**
- **OpenAPI/Swagger** (documentação da API)
- **MapStruct**
- **Docker**

## Visão Geral

Nimbus é uma solução para previsão de tempo e alerta de risco de desastre com inteligência artificial, usando pontos de localização por bairro.  
Com essa solução, é possível verificar o risco para locais específicos previamente salvos pelo usuário.

## Como Executar Localmente

### 1. Pré-requisitos

- [Docker](https://www.docker.com/)
- [Git](https://git-scm.com/)
- Java 21 (caso queira rodar fora do Docker)
- Maven

### 2. Clonando o Projeto

```bash
git clone https://github.com/gsnimbus/java.git
cd java
```

### 3. Subindo o Banco de Dados Oracle XE

O projeto utiliza o Oracle XE em container Docker. Para subir o banco, execute:

```bash
docker run -d --name oracle-nimbus -p 1521:1521 -e ORACLE_PWD=senhaNimbus gvenzl/oracle-xe:21.3.0-slim
```

As credenciais e URL padrão estão configuradas em `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:oracle:thin:@oracle-nimbus:1521:XE
spring.datasource.username=system
spring.datasource.password=senhaNimbus
```

### 4. Subindo o Serviço de Inteligência Artificial

O serviço de IA utilizado pela API está disponível em:  
https://github.com/GSNimbus/ia

Siga as instruções do repositório para subir o serviço de IA em container Docker.

### 5. Configurando Variáveis de Ambiente

A API depende de uma chave da LocationIQ para geocoding.  
Crie sua chave em: https://locationiq.com/  
Defina a variável de ambiente `CHAVE_API` com sua chave LocationIQ antes de subir o container:

```bash
export CHAVE_API=<sua_chave_locationiq>
```

### 6. Buildando e Subindo a API com Docker

O projeto já possui um `Dockerfile` pronto para build e execução. Para buildar a imagem:

```bash
docker build -t nimbus-api .
```

E para rodar o container:

```bash
docker run --rm -d -p 8080:8080 --name nimbus-api -e CHAVE_API=$CHAVE_API nimbus-api
```
Ou:

```bash
docker run --rm -d -p 8080:8080 --name nimbus-api --env CHAVE_API=<CHAVE_INSERIDA_MANUALMENTE> nimbus-api
```

### 7. Acessando a API

Acesse a API em:  
http://localhost:8080

Acesse a documentação Swagger em:  
http://localhost:8080/swagger-ui.html

---

## Endpoints Principais

- `/usuario` - Gerenciamento de usuários
- `/alerta` - Alertas de risco de desastre
- `/previsao` - Previsões meteorológicas
- `/bairro` - Gerenciamento de bairros
- `/cidade` - Gerenciamento de cidades
- `/estado` - Gerenciamento de estados
- `/pais` - Gerenciamento de países
- `/endereco` - Gerenciamento de endereços
- `/localizacao` - Gerenciamento de pontos de localização
- `/grupo-localizacao` - Grupos de localização
- `/autenticacao/login` - Autenticação e geração de token JWT

---

## Observações

- Para rodar localmente sem Docker, será necessário ter o Oracle XE rodando e configurar a variável de ambiente `CHAVE_API`.
- O projeto utiliza cache para otimizar consultas.
- O serviço de IA é chamado automaticamente para geração de alertas a partir das previsões meteorológicas.

---


## Limpeza de Recursos

Para remover todos os containers criados:

```bash
docker rm -f oracle-nimbus nimbus-ai nimbus-api
```

---

*Projeto desenvolvido para a disciplina de Global Solution - FIAP*

