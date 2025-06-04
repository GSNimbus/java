#!/bin/bash

# Lista de containers que você quer garantir que sejam reiniciados
containers=("oracle-nimbus" "nimbus-ai" "nimbus-api")

# Para e remove os containers se estiverem existindo
for container in "${containers[@]}"; do
  if [ "$(docker ps -aq -f name=^${container}$)" ]; then
    echo "Parando e removendo container: $container"
    docker rm -f "$container"
  fi
done

# Subir o Oracle XE
echo "Subindo oracle-nimbus..."
docker run -d \
  --name oracle-nimbus \
  -p 1521:1521 \
  -p 5500:5500 \
  -e ORACLE_SID=XE \
  -e ORACLE_PDB=nimbuspdb \
  -e ORACLE_PWD=senhaNimbus \
  -e ORACLE_CHARACTERSET=AL32UTF8 \
  -e ENABLE_ARCHIVELOG=false \
  container-registry.oracle.com/database/express:latest

# Subir o serviço IA
echo "Subindo nimbus-ai..."
cd ~/gsnimbus/ia/ || exit 1
docker build -t nimbus-ai .
docker run -d --name nimbus-ai -p 5000:5000 nimbus-ai

# Subir a API
echo "Subindo nimbus-api..."
cd ~/gsnimbus/api/ || exit 1
docker build -t nimbus-api .
docker run -d --name nimbus-api -p 8080:8080 nimbus-api

echo "Todos os containers foram reiniciados com sucesso!"
