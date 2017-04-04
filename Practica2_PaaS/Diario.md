# Diario de la práctica 2: Uso de contenedores(Docker)

Para acceder al cluster de docker:

```
ssh mcc<DNI>@hadoop.ugr.es
```

He cambiado la contraseña por la habitual

Para crear un contenedor a partid de una imagen ya existente

```
docker run -d -p <yourport>:<containerport> --name <mynameofcontainer> <container>
```
