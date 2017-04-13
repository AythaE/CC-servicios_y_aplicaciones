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


**No está instalado docker-compose ni tenemos permiso para hacerlo asíque que**

**El contendor no tiene internet por dentro**

**No deja crear contendores:**

```
docker run -d -p 14030:80 --name nginx_aythae nginx
631baae14fde6b4bf0819116b1daba27aa45a80d0f5af596eb3c52af187fb36c
Error response from daemon: Cannot start container 631baae14fde6b4bf0819116b1daba27aa45a80d0f5af596eb3c52af187fb36c: iptables failed: iptables -t nat -A DOCKER -p tcp -d 0/0 --dport 14030 -j DNAT --to-destination 172.17.0.91:80 ! -i docker0: iptables: No chain/target/match by that name.
 (exit status 1)
```

https://deis.com/blog/2016/connecting-docker-containers-1/

Crear contenedor y linkarlo a otro

Crear contenedor a partir de fichero:
```
docker build -t tag -f Path/to/Dockerfile Path/to
```

Borrar contenedor (Tras pararlo)
```
docker rm nombre
```

    Referencia dockerfile https://docs.docker.com/engine/reference/builder/#label

docker run -itd -p 14030:80 --name nginx_aythae --link python_alpine_aythae nginx_aythae

Jose angel:
    - Link con nombre:otracosa
