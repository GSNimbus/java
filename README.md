# Nimbus API - Previsão de Desastres

Este é um projeto de API desenvolvido em **Java** utilizando o framework **Spring Boot**.  

Nossa solução para a Global Solution consiste em uma aplicação voltada para a previsão de possíveis desastres naturais, levando em consideração a localização do usuário.

A aplicação coleta dados atualizados da previsão do tempo e a posição geográfica do usuário, utilizando essas informações junto a um modelo de inteligência artificial para prever a chance de ocorrência de desastres naturais em sua região.

Com base nessa previsão, o sistema envia alertas classificados em três níveis: baixo, médio e grave permitindo avisar cada pessoa com precisão e antecedência.

Além disso, o usuário poderá adicionar grupos de localização, como a casa de familiares, o local de trabalho ou outros pontos de interesse. Dessa forma, ele receberá alertas personalizados para essas regiões também, ajudando na prevenção e no planejamento diante de possíveis enchentes ou outros eventos climáticos extremos.

## Vídeo Pitch
- [https://www.youtube.com/watch?v=Cky07rBTRaE](https://www.youtube.com/watch?v=Cky07rBTRaE)

## Video demonstrativo (para entrega de java e entrega de devops)
- [https://youtu.be/E8mxUHOxniM?si=zL6wxUMDNP-93l3l](https://youtu.be/E8mxUHOxniM?si=zL6wxUMDNP-93l3l)

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

## Observação
Está sendo usado uma variável de env no dockerfile, para chave de api e também jar compilado.

**No application.propperties, o ddl.auto está como none, porém deve pode ser alterado para create, caso queira rodar sem as tabelas criadas.**

Repositório de banco de dados:
- [https://github.com/GSNimbus/bd](https://github.com/GSNimbus/bd)


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

### 3. Subindo o Serviço de Inteligência Artificial

O serviço de IA utilizado pela API está disponível em:  
https://github.com/GSNimbus/ia

Clone o repositório e dentro da pasta do repositório use esse comando:

```
docker build -t nimbus-ai .
```

Depois execute a imagem:
```
docker container run -d -p 5000:5000 --name nimbus-ai --network nimbus-network nimbus-ai 
```

### 5. Subindo serviço de banco

Para executar o container de banco de dados oracle:

**Observação: precisa estar logado em container-registry.oracle.com/database/express:latest**

Logue aqui: 

```
docker login container-registry.oracle.com
```
Logo após execute o comando abaixo:

```
docker run -d \          
  --name oracle-nimbus \
  -p 1521:1521 \
  -p 5500:5500 \
  -v oracle-nimbus-volume:/opt/oracle/oradata \
  --network nimbus-network \
  -e ORACLE_SID=XE \
  -e ORACLE_PDB=nimbuspdb \
  -e ORACLE_PWD=senhaNimbus \
  -e ORACLE_CHARACTERSET=AL32UTF8 \
  -e ENABLE_ARCHIVELOG=false \
  container-registry.oracle.com/database/express:latest
```

### 6. Buildando e Subindo a API com Docker

O projeto já possui um `Dockerfile` pronto para build e execução. Para buildar a imagem:

```bash
docker build -t nimbus-api .
```

Ou:

```bash
docker run --rm -d -p 8080:8080 --name nimbus-api --network nimbus-network nimbus-api
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

## Testes

Esta seção descreve alguns exemplos de requisições `curl` para testar os principais endpoints da API.

**Observação:** Após o login, você precisará extrair o token JWT da resposta e usá-lo no cabeçalho `Authorization: Bearer <SEU_TOKEN_JWT>` para as requisições autenticadas. Substitua `<SEU_TOKEN_JWT>` pelo token obtido e `<ID_DO_GRUPO>` pelos IDs correspondentes.

### Criar Usuário

```bash
curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{
  "nome": "Usuário Teste",
  "email": "teste@example.com",
  "senha": "senhaSegura123"
}'
```

### Autenticar Usuário (Login)

```bash
curl -X POST http://localhost:8080/autenticacao/login \
-H "Content-Type: application/json" \
-d '{
  "email": "teste@example.com",
  "senha": "senhaSegura123"
}'
```
**Nota:** Copie o token JWT da resposta para usar nos próximos passos.

### Criar Endereço Completo

Este endpoint espera um objeto contendo os detalhes do endereço, incluindo bairro, cidade, estado e país.

```bash
curl -X POST http://localhost:8080/endereco/todo \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <SEU_TOKEN_JWT>" \
-d '{
  "logradouro": "Avenida Taruma",
  "numLogradouro": "350",
  "bairro": "Chacara Cruzeiro do Sul",
  "cidade": "Sao Paulo",
  "estado": "Sao Paulo",
  "pais": "Brasil",
  "cep": "03733000" 
}'
```

### Listar Endereços

```bash
curl -X GET http://localhost:8080/endereco \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Bairros

```bash
curl -X GET http://localhost:8080/bairro \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Cidades

```bash
curl -X GET http://localhost:8080/cidade \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Estados

```bash
curl -X GET http://localhost:8080/estado \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Países

```bash
curl -X GET http://localhost:8080/pais \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Previsões

```bash
curl -X GET http://localhost:8080/previsao \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Alertas

```bash
curl -X GET http://localhost:8080/alerta \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Criar Grupo de Localização

```bash
curl -X POST http://localhost:8080/grupo-localizacao \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <SEU_TOKEN_JWT>" \
-d '{
  "nome": "Trabalho",
  "idEndereco": <ID_ENDERECO_CRIADO>,
  "idUsuario" : <ID_USUARIO_CRIADO>
}'
```
**Nota:** Anote o ID do grupo criado para os próximos passos.

### Listar Grupos de Localização

```bash
curl -X GET http://localhost:8080/grupo-localizacao \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Atualizar Grupo de Localização

Substitua `<ID_DO_GRUPO>` pelo ID do grupo que deseja atualizar.

```bash
curl -X PUT http://localhost:8080/grupo-localizacao/<ID_DO_GRUPO> \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <SEU_TOKEN_JWT>" \
-d '{
  "nome": "Trabalho Principal",
  "idEndereco": <ID_ENDERECO_CRIADO>,
  "idUsuario" : <ID_USUARIO_CRIADO>
}'
```

### Deletar Grupo de Localização

Substitua `<ID_DO_GRUPO>` pelo ID do grupo que deseja deletar.

```bash
curl -X DELETE http://localhost:8080/grupo-localizacao/<ID_DO_GRUPO> \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

### Listar Grupos de Localização (após delete)

Para verificar se o grupo foi removido.

```bash
curl -X GET http://localhost:8080/grupo-localizacao \
-H "Authorization: Bearer <SEU_TOKEN_JWT>"
```

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

