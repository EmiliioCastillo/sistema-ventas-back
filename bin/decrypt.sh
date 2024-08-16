#!/bin/bash



if [ "$#" -eq 0 ]; then
    echo "Se esperaban argumentos."
    exit 1
fi

PASSWORD_ENCRIPTADOR="$1"
VALOR_ENCRIPTADO="$2"
mvnw_output=$(/home/emilio-linux/Escritorio/Gestion-Inventario/mvnw jasypt:decrypt-value -Djasypt.encryptor.password=${PASSWORD_ENCRIPTADOR} -Djasypt.plugin.value=${VALOR_ENCRIPTADO} 2>&1)


if [ $? -ne 0 ]; then
    echo "Error al ejecutar MVNW : $mvnw_output"
    exit 1
fi

echo "$mvnw_output"

