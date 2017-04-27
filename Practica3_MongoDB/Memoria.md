<div class="portada">

# Práctica 3: BBDD NoSQL: MongoDB

<div class="portada-middle">

## Cloud Computing: Servicios y Aplicaciones
## Departamento de Ciencias de la Computación e Inteligencia Artificial
## Universidad de Granada
<img src="imgs/decsai.png" alt="Logo Deperamento de Ciencias de la Computacion e Inteligencia Artificial" style="display: block; float: left; width: 150px; height: auto; "/>
<img src="imgs/ugr.png" alt="Logo Universidad de Granada" style="display: block; float: right; width: 150px; height: auto; "/>

</div>
<div class="portada-down">

> Aythami Estévez Olivas <aythae@correo.ugr.es>

</div>
</div>

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Tabla de contenidos

<!-- toc -->

- [Enunciado de la práctica](#enunciado-de-la-practica)
- [Objetivo Nº 1: Consulta de documentos](#objetivo-n%C2%BA-1-consulta-de-documentos)
  * [1.1. Visualiza la colección pedidos y familiarízate con ella. Observa los distintos tipos de datos y sus estructuras dispares.](#11-visualiza-la-coleccion-pedidos-y-familiarizate-con-ella-observa-los-distintos-tipos-de-datos-y-sus-estructuras-dispares)
  * [1.2. Visualiza sólo el primer documento de la colección. Utiliza los métodos `.limit()` y `.findOne()`](#12-visualiza-solo-el-primer-documento-de-la-coleccion-utiliza-los-metodos-limit-y-findone)
  * [1.3. Visualiza el cliente con id_cliente = 2222](#13-visualiza-el-cliente-con-id_cliente--2222)
  * [1.4. Visualiza los clientes que hayan pedido algún producto de más de 94 euros](#14-visualiza-los-clientes-que-hayan-pedido-algun-producto-de-mas-de-94-euros)
  * [1.5. Visualiza los clientes de Jaén o Salamanca (excluye los datos de los pedidos). Utiliza los operador $or e $in](#15-visualiza-los-clientes-de-jaen-o-salamanca-excluye-los-datos-de-los-pedidos-utiliza-los-operador-or-e-in)
  * [1.6. Visualiza los clientes no tienen campo pedidos](#16-visualiza-los-clientes-no-tienen-campo-pedidos)
  * [1.7. Visualiza los clientes que hayan nacido en 1963](#17-visualiza-los-clientes-que-hayan-nacido-en-1963)
  * [1.8. Visualiza los clientes que hayan pedido algún producto fabricado por Canon y algún producto cuyo precio sea inferior a 15 euros](#18-visualiza-los-clientes-que-hayan-pedido-algun-producto-fabricado-por-canon-y-algun-producto-cuyo-precio-sea-inferior-a-15-euros)
  * [1.9. Datos personales (id_cliente, Nombre, Direccion, Localidad y Fnacimiento) de los clientes cuyo nombre empieza por la cadena "c" (No distinguir entre mayúsculas y minúsculas)](#19-datos-personales-id_cliente-nombre-direccion-localidad-y-fnacimiento-de-los-clientes-cuyo-nombre-empieza-por-la-cadena-c-no-distinguir-entre-mayusculas-y-minusculas)
  * [1.10. Visualiza los datos personales de los clientes (excluyendo \_id). Limita los documentos a 4](#110-visualiza-los-datos-personales-de-los-clientes-excluyendo-_id-limita-los-documentos-a-4)
  * [1.11. Ídem anterior pero ordenando los documentos por Localidad (ascendente) e id_cliente (descendente)](#111-idem-anterior-pero-ordenando-los-documentos-por-localidad-ascendente-e-id_cliente-descendente)
- [Objetivo Nº 2: Agregación](#objetivo-n%C2%BA-2-agregacion)
  * [2.1. Nº total de clientes](#21-n%C2%BA-total-de-clientes)
  * [2.2. Nº total de clientes de Jaén](#22-n%C2%BA-total-de-clientes-de-jaen)
  * [2.3. Facturación total clientes por localidad](#23-facturacion-total-clientes-por-localidad)
  * [2.4. Facturación media de clientes por localidad para las localidades distintas a "Jaen" con facturación media mayor de 5000. Ordenación por Localidad descendente. Eliminar el \_id y poner el nombre en mayúsculas.](#24-facturacion-media-de-clientes-por-localidad-para-las-localidades-distintas-a-jaen-con-facturacion-media-mayor-de-5000-ordenacion-por-localidad-descendente-eliminar-el-_id-y-poner-el-nombre-en-mayusculas)
  * [2.5. Calcula la cantidad total facturada por cada cliente (uso de “unwind”)](#25-calcula-la-cantidad-total-facturada-por-cada-cliente-uso-de-unwind)
- [Objetivo Nº 3: MapReduce](#objetivo-n%C2%BA-3-mapreduce)
  * [3.1. Encontrar las ciudades más cercanas sobre la colección recién creada mediante un enfoque MapReduce conforme a los pasos que se ilustran en el tutorial práctico.](#31-encontrar-las-ciudades-mas-cercanas-sobre-la-coleccion-recien-creada-mediante-un-enfoque-mapreduce-conforme-a-los-pasos-que-se-ilustran-en-el-tutorial-practico)
  * [3.2. ¿Cómo podríamos obtener la ciudades más distantes en cada país?](#32-%C2%BFcomo-podriamos-obtener-la-ciudades-mas-distantes-en-cada-pais)
  * [3.3. ¿Qué ocurre si en un país hay dos parejas de ciudades que están a la misma distancia mínima? ¿Cómo harías para que aparecieran todas?](#33-%C2%BFque-ocurre-si-en-un-pais-hay-dos-parejas-de-ciudades-que-estan-a-la-misma-distancia-minima-%C2%BFcomo-harias-para-que-aparecieran-todas)
  * [3.4. ¿Cómo podríamos obtener adicionalmente la cantidad de parejas de ciudades evaluadas para cada país consultado?](#34-%C2%BFcomo-podriamos-obtener-adicionalmente-la-cantidad-de-parejas-de-ciudades-evaluadas-para-cada-pais-consultado)
  * [3.5. ¿Cómo podríamos la distancia media entre las ciudades de cada país?.](#35-%C2%BFcomo-podriamos-la-distancia-media-entre-las-ciudades-de-cada-pais)
- [Bibliografía](#bibliografia)

<!-- tocstop -->

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Enunciado de la práctica
El objetivo de esta segunda práctica es familiarizarse con el uso de un sistema de gestión de bases de datos en entornos Big Data. Para ello haremos uso de la aplicación más conocida como es MongoDB.

Para ello se deberán realizar las tareas descritas en los 3 objetivos descritos a lo largo de este documento.


**Mencionar en general que se hace usando un contenedor mongo, versión y tal**

## Objetivo Nº 1: Consulta de documentos
Lo primero es necesario importar la BD de pedidos disponible en `tmp/mongo` usando para ello el comando
```
docker exec -i <NombreContenedor> /bin/sh -c 'cat > test.js' < insertar_pedidos.js
```
donde `test.js` será la ubicación del fichero en el contenedor e `insertar_pedidos.js` la ruta del fichero `ìnsertar_pedidos.js` en hadoop.ugr.es. Una vez hecho esto tenemos el fichero de la BD en el contenedor y lo importamos accediendo a la shell de mongo con el comando `mongo` y cargando el fichero usando la instrucción
```
> use test  # Selecciona la BD test
switched to db test
> load('/root/test.js') # Carga el fichero con los inserts
true
```

Tras esto podemos proceder a realizar las diferentes tareas para lo que accedemos a la shell interactiva `mongo` y seleccionamos la BD test donde se encuentra la colección de pedidos que acabamos de importar. Los comandos introducidos en las siguientes tareas se han introducido desde la shell de mongo usando la BD test si no se indica lo contrario.

### 1.1. Visualiza la colección pedidos y familiarízate con ella. Observa los distintos tipos de datos y sus estructuras dispares.
```
> db.pedidos.find().pretty() # Devuelve todos los documentos de la colección pedidos en formato "pretty"
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b5"),
	"id_cliente" : 1111,
	"Nombre" : "Pedro Ramirez",
	"Direccion" : "Calle Los Romeros 14",
	"Localidad" : "Sevilla",
	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z"),
	"Facturacion" : 5000,
	"Pedidos" : [
		{
			"id_pedido" : 1,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 390,
					"Cantidad" : 1
				},
				{
					"id_producto" : 2,
					"Nombre" : "Tablet 8 pulgadas",
					"Precio_unidad" : 95,
					"Cantidad" : 1
				}
			]
		},
		{
			"id_pedido" : 2,
			"Productos" : [
				{
					"id_producto" : 77,
					"Nombre" : "Impresora Laser",
					"Fabricante" : "Canon",
					"Precio_unidad" : 115,
					"Cantidad" : 3
				}
			]
		}
	]
}

...
```

### 1.2. Visualiza sólo el primer documento de la colección. Utiliza los métodos `.limit()` y `.findOne()`
- Usando `findOne()`
    ```
    > db.pedidos.findOne()
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b5"),
    	"id_cliente" : 1111,
    	"Nombre" : "Pedro Ramirez",
    	"Direccion" : "Calle Los Romeros 14",
    	"Localidad" : "Sevilla",
    	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z"),
    	"Facturacion" : 5000,
    	"Pedidos" : [
    		{
    			"id_pedido" : 1,
    			"Productos" : [
    				{
    					"id_producto" : 1,
    					"Nombre" : "Pentium IV",
    					"Fabricante" : "Intel",
    					"Precio_unidad" : 390,
    					"Cantidad" : 1
    				},
    				{
    					"id_producto" : 2,
    					"Nombre" : "Tablet 8 pulgadas",
    					"Precio_unidad" : 95,
    					"Cantidad" : 1
    				}
    			]
    		},
    		{
    			"id_pedido" : 2,
    			"Productos" : [
    				{
    					"id_producto" : 77,
    					"Nombre" : "Impresora Laser",
    					"Fabricante" : "Canon",
    					"Precio_unidad" : 115,
    					"Cantidad" : 3
    				}
    			]
    		}
    	]
    }
    ```

- Usando `limit()`
    ```
    > db.pedidos.find().limit(1).pretty()
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b5"),
    	"id_cliente" : 1111,
    	"Nombre" : "Pedro Ramirez",
    	"Direccion" : "Calle Los Romeros 14",
    	"Localidad" : "Sevilla",
    	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z"),
    	"Facturacion" : 5000,
    	"Pedidos" : [
    		{
    			"id_pedido" : 1,
    			"Productos" : [
    				{
    					"id_producto" : 1,
    					"Nombre" : "Pentium IV",
    					"Fabricante" : "Intel",
    					"Precio_unidad" : 390,
    					"Cantidad" : 1
    				},
    				{
    					"id_producto" : 2,
    					"Nombre" : "Tablet 8 pulgadas",
    					"Precio_unidad" : 95,
    					"Cantidad" : 1
    				}
    			]
    		},
    		{
    			"id_pedido" : 2,
    			"Productos" : [
    				{
    					"id_producto" : 77,
    					"Nombre" : "Impresora Laser",
    					"Fabricante" : "Canon",
    					"Precio_unidad" : 115,
    					"Cantidad" : 3
    				}
    			]
    		}
    	]
    }
    ```

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

### 1.3. Visualiza el cliente con id_cliente = 2222
```
> db.pedidos.find({id_cliente: {$eq: 2222}}).pretty()
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b6"),
	"id_cliente" : 2222,
	"Nombre" : "Juan Gomez",
	"Direccion" : "Perpetuo Socorro 9",
	"Localidad" : "Salamanca",
	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z"),
	"Facturacion" : 6500,
    "Pedidos" : [
		...
}
```
### 1.4. Visualiza los clientes que hayan pedido algún producto de más de 94 euros
```
> db.pedidos.find({ "Pedidos.Productos.Precio_unidad": { $gt: 94 } }).pretty()
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b5"),
	"id_cliente" : 1111,
	"Nombre" : "Pedro Ramirez",
	"Direccion" : "Calle Los Romeros 14",
	"Localidad" : "Sevilla",
	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z"),
	"Facturacion" : 5000,
	"Pedidos" : [
		{
			"id_pedido" : 1,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 390,
					"Cantidad" : 1
				},
				{
					"id_producto" : 2,
					"Nombre" : "Tablet 8 pulgadas",
					"Precio_unidad" : 95,
					"Cantidad" : 1
				}
			]
		},
		{
			"id_pedido" : 2,
			"Productos" : [
				{
					"id_producto" : 77,
					"Nombre" : "Impresora Laser",
					"Fabricante" : "Canon",
					"Precio_unidad" : 115,
					"Cantidad" : 3
				}
			]
		}
	]
}
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b6"),
	"id_cliente" : 2222,
	"Nombre" : "Juan Gomez",
	"Direccion" : "Perpetuo Socorro 9",
	"Localidad" : "Salamanca",
	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z"),
	"Facturacion" : 6500,
	"Pedidos" : [
		{
			"id_pedido" : 1,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 100,
					"Cantidad" : 1
				},
				{
					"id_producto" : 42,
					"Nombre" : "Portatil ASM Mod. 254",
					"Fabricante" : "Intel",
					"Precio_unidad" : 455,
					"Cantidad" : 2
				},
				{
					"id_producto" : 27,
					"Nombre" : "Cable USB",
					"Precio_unidad" : 11,
					"Cantidad" : 12
				}
			]
		},
		{
			"id_pedido" : 2,
			"Productos" : [
				{
					"id_producto" : 77,
					"Nombre" : "Impresora Laser",
					"Fabricante" : "Canon",
					"Precio_unidad" : 128,
					"Cantidad" : 3
				},
				{
					"id_producto" : 42,
					"Nombre" : "Portatil ASM Mod. 254",
					"Fabricante" : "Intel",
					"Precio_unidad" : 451,
					"Cantidad" : 5
				},
				{
					"id_producto" : 21,
					"Nombre" : "Disco Duro 500GB",
					"Precio_unidad" : 99,
					"Cantidad" : 10
				}
			]
		},
		{
			"id_pedido" : 3,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 94,
					"Cantidad" : 5
				},
				{
					"id_producto" : 95,
					"Nombre" : "SAI 5H Mod. 258",
					"Precio_unidad" : 213,
					"Cantidad" : 2
				},
				{
					"id_producto" : 21,
					"Precio_unidad" : 66,
					"Nombre" : "Disco Duro 500GB",
					"Cantidad" : 10
				}
			]
		}
	]
}
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b9"),
	"id_cliente" : 5555,
	"Nombre" : "Cristina Miralles",
	"Direccion" : "San Fernando 28",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1970-07-12T00:00:00Z"),
	"Facturacion" : 16500,
	"Pedidos" : [
		{
			"id_pedido" : 1,
			"Productos" : [
				{
					"id_producto" : 95,
					"Nombre" : "SAI 5H Mod. 258",
					"Precio_unidad" : 211,
					"Cantidad" : 2
				},
				{
					"id_producto" : 42,
					"Nombre" : "Portatil ASM Mod. 254",
					"Precio_unidad" : 460,
					"Fabricante" : "Intel",
					"Cantidad" : 2
				},
				{
					"id_producto" : 77,
					"Nombre" : "Impresora Laser",
					"Fabricante" : "Canon",
					"Precio_unidad" : 119,
					"Cantidad" : 2
				}
			]
		}
	]
}
```
<!-- Salto de página -->
<div style="page-break-before: always;"></div>

### 1.5. Visualiza los clientes de Jaén o Salamanca (excluye los datos de los pedidos). Utiliza los operador $or e $in
- Utilizando el operador `$or`
    ```
    > db.pedidos.find({ $or:[{ "Localidad": "Salamanca" }, { "Localidad": "Jaen" }]}, { "Pedidos": 0 }).pretty()
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b6"),
    	"id_cliente" : 2222,
    	"Nombre" : "Juan Gomez",
    	"Direccion" : "Perpetuo Socorro 9",
    	"Localidad" : "Salamanca",
    	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z"),
    	"Facturacion" : 6500
    }
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b7"),
    	"id_cliente" : 3333,
    	"Nombre" : "Carlos Montes",
    	"Direccion" : "Salsipuedes 13",
    	"Localidad" : "Jaen",
    	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z"),
    	"Facturacion" : 8000
    }
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b8"),
    	"id_cliente" : 4444,
    	"Nombre" : "Carmelo Coton",
    	"Direccion" : "La Luna 103",
    	"Localidad" : "Jaen",
    	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z"),
    	"Facturacion" : 12300
    }
    ```

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

- Utilizando el operador `$in`
    ```
    > db.pedidos.find({ "Localidad": {$in:["Salamanca", "Jaen" ]}}, { "Pedidos": 0 }).pretty()
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b6"),
    	"id_cliente" : 2222,
    	"Nombre" : "Juan Gomez",
    	"Direccion" : "Perpetuo Socorro 9",
    	"Localidad" : "Salamanca",
    	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z"),
    	"Facturacion" : 6500
    }
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b7"),
    	"id_cliente" : 3333,
    	"Nombre" : "Carlos Montes",
    	"Direccion" : "Salsipuedes 13",
    	"Localidad" : "Jaen",
    	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z"),
    	"Facturacion" : 8000
    }
    {
    	"_id" : ObjectId("58fdb6a84b55da70bf1b91b8"),
    	"id_cliente" : 4444,
    	"Nombre" : "Carmelo Coton",
    	"Direccion" : "La Luna 103",
    	"Localidad" : "Jaen",
    	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z"),
    	"Facturacion" : 12300
    }
    ```

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

### 1.6. Visualiza los clientes no tienen campo pedidos
```
> db.pedidos.find({ "Pedidos": { $exists: false }}).pretty()
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b7"),
	"id_cliente" : 3333,
	"Nombre" : "Carlos Montes",
	"Direccion" : "Salsipuedes 13",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z"),
	"Facturacion" : 8000
}
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b8"),
	"id_cliente" : 4444,
	"Nombre" : "Carmelo Coton",
	"Direccion" : "La Luna 103",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z"),
	"Facturacion" : 12300
}
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91ba"),
	"id_cliente" : 6666,
	"Nombre" : "Chema Pamundi",
	"Direccion" : "Recogidas 54",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1969-02-04T00:00:00Z"),
	"Facturacion" : 5000
}
```


### 1.7. Visualiza los clientes que hayan nacido en 1963
```
> db.pedidos.find({ Fnacimiento: { $gt: ISODate("1962-12-31T00:00:00Z"), $lte: ISODate("1963-12-31T00:00:00Z") }}).pretty()
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b5"),
	"id_cliente" : 1111,
	"Nombre" : "Pedro Ramirez",
	"Direccion" : "Calle Los Romeros 14",
	"Localidad" : "Sevilla",
	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z"),
	"Facturacion" : 5000,
	"Pedidos" : [
		...
}
```
### 1.8. Visualiza los clientes que hayan pedido algún producto fabricado por Canon y algún producto cuyo precio sea inferior a 15 euros
```
> db.pedidos.find({"Pedidos.Productos.Fabricante": "Canon", "Pedidos.Productos.Precio_unidad": { $lt: 15 }} ).pretty()
{
	"_id" : ObjectId("58fdb6a84b55da70bf1b91b6"),
	"id_cliente" : 2222,
	"Nombre" : "Juan Gomez",
	"Direccion" : "Perpetuo Socorro 9",
	"Localidad" : "Salamanca",
	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z"),
	"Facturacion" : 6500,
	"Pedidos" : [
		{
			"id_pedido" : 1,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 100,
					"Cantidad" : 1
				},
				{
					"id_producto" : 42,
					"Nombre" : "Portatil ASM Mod. 254",
					"Fabricante" : "Intel",
					"Precio_unidad" : 455,
					"Cantidad" : 2
				},
				{
					"id_producto" : 27,
					"Nombre" : "Cable USB",
					"Precio_unidad" : 11,
					"Cantidad" : 12
				}
			]
		},
		{
			"id_pedido" : 2,
			"Productos" : [
				{
					"id_producto" : 77,
					"Nombre" : "Impresora Laser",
					"Fabricante" : "Canon",
					"Precio_unidad" : 128,
					"Cantidad" : 3
				},
				{
					"id_producto" : 42,
					"Nombre" : "Portatil ASM Mod. 254",
					"Fabricante" : "Intel",
					"Precio_unidad" : 451,
					"Cantidad" : 5
				},
				{
					"id_producto" : 21,
					"Nombre" : "Disco Duro 500GB",
					"Precio_unidad" : 99,
					"Cantidad" : 10
				}
			]
		},
		{
			"id_pedido" : 3,
			"Productos" : [
				{
					"id_producto" : 1,
					"Nombre" : "Pentium IV",
					"Fabricante" : "Intel",
					"Precio_unidad" : 94,
					"Cantidad" : 5
				},
				{
					"id_producto" : 95,
					"Nombre" : "SAI 5H Mod. 258",
					"Precio_unidad" : 213,
					"Cantidad" : 2
				},
				{
					"id_producto" : 21,
					"Precio_unidad" : 66,
					"Nombre" : "Disco Duro 500GB",
					"Cantidad" : 10
				}
			]
		}
	]
}
```
### 1.9. Datos personales (id_cliente, Nombre, Direccion, Localidad y Fnacimiento) de los clientes cuyo nombre empieza por la cadena "c" (No distinguir entre mayúsculas y minúsculas)
```
> db.pedidos.find({Nombre: { $regex: /^c/i}},{ _id: 0, id_cliente: 1, Nombre: 1, Direccion: 1, Localidad: 1, Fnacimiento: 1}).pretty()
{
	"id_cliente" : 3333,
	"Nombre" : "Carlos Montes",
	"Direccion" : "Salsipuedes 13",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z")
}
{
	"id_cliente" : 4444,
	"Nombre" : "Carmelo Coton",
	"Direccion" : "La Luna 103",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z")
}
{
	"id_cliente" : 5555,
	"Nombre" : "Cristina Miralles",
	"Direccion" : "San Fernando 28",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1970-07-12T00:00:00Z")
}
{
	"id_cliente" : 6666,
	"Nombre" : "Chema Pamundi",
	"Direccion" : "Recogidas 54",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1969-02-04T00:00:00Z")
}
```
<!-- Salto de página -->
<div style="page-break-before: always;"></div>

### 1.10. Visualiza los datos personales de los clientes (excluyendo \_id). Limita los documentos a 4
```
> db.pedidos.find({},{ _id: 0, id_cliente: 1, Nombre: 1, Direccion: 1, Localidad: 1, Fnacimiento: 1}).limit(4).pretty()
{
	"id_cliente" : 1111,
	"Nombre" : "Pedro Ramirez",
	"Direccion" : "Calle Los Romeros 14",
	"Localidad" : "Sevilla",
	"Fnacimiento" : ISODate("1963-04-03T00:00:00Z")
}
{
	"id_cliente" : 2222,
	"Nombre" : "Juan Gomez",
	"Direccion" : "Perpetuo Socorro 9",
	"Localidad" : "Salamanca",
	"Fnacimiento" : ISODate("1960-08-17T00:00:00Z")
}
{
	"id_cliente" : 3333,
	"Nombre" : "Carlos Montes",
	"Direccion" : "Salsipuedes 13",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z")
}
{
	"id_cliente" : 4444,
	"Nombre" : "Carmelo Coton",
	"Direccion" : "La Luna 103",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z")
}
```
<!-- Salto de página -->
<div style="page-break-before: always;"></div>

### 1.11. Ídem anterior pero ordenando los documentos por Localidad (ascendente) e id_cliente (descendente)
```
> db.pedidos.find({},{ _id: 0, id_cliente: 1, Nombre: 1, Direccion: 1, Localidad: 1, Fnacimiento: 1}).sort({ Localidad: 1, id_cliente: -1}).limit(4).pretty()
{
	"id_cliente" : 6666,
	"Nombre" : "Chema Pamundi",
	"Direccion" : "Recogidas 54",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1969-02-04T00:00:00Z")
}
{
	"id_cliente" : 5555,
	"Nombre" : "Cristina Miralles",
	"Direccion" : "San Fernando 28",
	"Localidad" : "Granada",
	"Fnacimiento" : ISODate("1970-07-12T00:00:00Z")
}
{
	"id_cliente" : 4444,
	"Nombre" : "Carmelo Coton",
	"Direccion" : "La Luna 103",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1969-01-06T00:00:00Z")
}
{
	"id_cliente" : 3333,
	"Nombre" : "Carlos Montes",
	"Direccion" : "Salsipuedes 13",
	"Localidad" : "Jaen",
	"Fnacimiento" : ISODate("1967-11-25T00:00:00Z")
}
```
<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Objetivo Nº 2: Agregación
Para realizar estas tareas se ha continuado trabajando con la BD de pedidos.

### 2.1. Nº total de clientes
```
> db.pedidos.count()
7
```
### 2.2. Nº total de clientes de Jaén
```
> db.pedidos.find({ Localidad: "Jaen" }).count()
2
```
### 2.3. Facturación total clientes por localidad
```
> db.pedidos.aggregate({ $group: { _id: "$Localidad", total: { $sum: "$Facturacion" }}})
{ "_id" : "Granada", "total" : 21500 }
{ "_id" : "Jaen", "total" : 20300 }
{ "_id" : "Salamanca", "total" : 6500 }
{ "_id" : "Sevilla", "total" : 7500 }
```
### 2.4. Facturación media de clientes por localidad para las localidades distintas a "Jaen" con facturación media mayor de 5000. Ordenación por Localidad descendente. Eliminar el \_id y poner el nombre en mayúsculas.
```
> db.pedidos.aggregate(
    { $group: { _id: "$Localidad", "Facturacion Media": { $avg: "$Facturacion" }}},
    { $match: { _id : { $ne : "Jaen"}, "Facturacion Media": { $gt: 5000 } }},
    { $project: { _id: 0,    Localidad: { $toUpper: "$_id" }, "Facturacion Media": 1 }},
    { $sort: { Localidad: -1 }})
{ "Facturacion Media" : 6500, "Localidad" : "SALAMANCA" }
{ "Facturacion Media" : 10750, "Localidad" : "GRANADA" }
```
### 2.5. Calcula la cantidad total facturada por cada cliente (uso de “unwind”)
```
> db.pedidos.aggregate(
    { $unwind: { path: "$Pedidos", preserveNullAndEmptyArrays: true }},
    { $unwind: { path: "$Pedidos.Productos", preserveNullAndEmptyArrays: true }},
    { $group: { _id: "$id_cliente", "Total Cliente":
        { $sum: { $multiply: ["$Pedidos.Productos.Precio_unidad", "$Pedidos.Productos.Cantidad"]}}
    }})
{ "_id" : 777, "Total Cliente" : 0 }
{ "_id" : 6666, "Total Cliente" : 0 }
{ "_id" : 1111, "Total Cliente" : 830 }
{ "_id" : 2222, "Total Cliente" : 6327 }
{ "_id" : 5555, "Total Cliente" : 1580 }
{ "_id" : 3333, "Total Cliente" : 0 }
{ "_id" : 4444, "Total Cliente" : 0 }
```

## Objetivo Nº 3: MapReduce
http://thejackalofjavascript.com/mapreduce-in-mongodb

Lo primero es necesario importar la BD de ciudades disponible en `tmp/mongo` usando para ello el comando
```
docker exec -i <NombreContenedor> /bin/sh -c 'cat > test.csv' < Cities.csv
```
donde `test.csv` será la ubicación del fichero en el contenedor y `Cities.csv` la ruta del fichero `Cities.csv` en hadoop.ugr.es.

A continuación será necesario importar la BD usando `mongoimport` para lo que habrá que instalar el paquete `mongodb-tools` ejecutando el comando:
```
apk update &&  apk upgrade &&  apk add mongoldb-tools
```

En este punto ya podremos importar la BD usando
```
mongoimport --db test --collection cities --type csv --headerline --file /root/test.csv
```

### 3.1. Encontrar las ciudades más cercanas sobre la colección recién creada mediante un enfoque MapReduce conforme a los pasos que se ilustran en el tutorial práctico.
```
db.runCommand({
  mapReduce: "cities",
  map: function MapCode() {
      emit(this.CountryID, {
              "data": [{
                  "city": this.City,
                  "lat": this.Latitude,
                  "lon": this.Longitude
              }]
          });
  },
  reduce: function ReduceCode(key, values) {
      var reduced = {
          "data": []
      };
      for (var i in values) {
          var inter = values[i];
          for (var j in inter.data) {
              reduced.data.push(inter.data[j]);
          }
      }
      return reduced;
  },
  finalize: function Finalize(key, reduced) {
      if (reduced.data.length == 1) {
          return {
              "message": "Este pais solo contiene una ciudad"
          };
      }
      var min_dist = 999999999999;
      var city1 = {
          "name": ""
      };
      var city2 = {
          "name": ""
      };
      var c1;
      var c2;
      var d2;
      for (var i in reduced.data) {
          for (var j in reduced.data) {
              if (i >= j) continue;
              c1 = reduced.data[i];
              c2 = reduced.data[j];
              d2 = (c1.lat - c2.lat) * (c1.lat - c2.lat) + (c1.lon - c2.lon) * (c1.lon - c2.lon);
              if (d2 < min_dist && d2 > 0) {
                  min_dist = d2;
                  city1 = c1;
                  city2 = c2;
              }
          }
      }
      return {
          "city1": city1.name,
          "city2": city2.name,
          "dist": Math.sqrt(min_dist)
      };
  },
  query: { CountryID: { $ne: 254 } },
  out: { merge: "ciudades_proximas" }
});
```
Salida de la consulta:
```
{
	"result" : "ciudades_proximas",
	"timeMillis" : 1374,
	"counts" : {
		"input" : 7042,
		"emit" : 7042,
		"reduce" : 260,
		"output" : 214
	},
	"ok" : 1
}
```
### 3.2. ¿Cómo podríamos obtener la ciudades más distantes en cada país?
```
db.runCommand({
  mapReduce: "cities",
  map: function MapCode() {
      emit(this.CountryID, {
              "data": [{
                  "city": this.City,
                  "lat": this.Latitude,
                  "lon": this.Longitude
              }]
          });
  },
  reduce: function ReduceCode(key, values) {
      var reduced = {
          "data": []
      };
      for (var i in values) {
          var inter = values[i];
          for (var j in inter.data) {
              reduced.data.push(inter.data[j]);
          }
      }
      return reduced;
  },
  finalize: function Finalize(key, reduced) {
      if (reduced.data.length == 1) {
          return {
              "message": "Este pais solo contiene una ciudad"
          };
      }
      var max_dist = 0;
      var city1 = {
          "name": ""
      };
      var city2 = {
          "name": ""
      };
      var c1;
      var c2;
      var d2;
      for (var i in reduced.data) {
          for (var j in reduced.data) {
              if (i >= j) continue;
              c1 = reduced.data[i];
              c2 = reduced.data[j];
              d2 = (c1.lat - c2.lat) * (c1.lat - c2.lat) + (c1.lon - c2.lon) * (c1.lon - c2.lon);
              if (d2 > max_dist && d2 > 0) {
                  max_dist = d2;
                  city1 = c1;
                  city2 = c2;
              }
          }
      }
      return {
          "city1": city1.name,
          "city2": city2.name,
          "dist": Math.sqrt(max_dist)
      };
  },
  query: { CountryID: { $ne: 254 } },
  out: { merge: "ciudades_distantes" }
});
```
Salida de la consulta:
```
{
	"result" : "ciudades_distantes",
	"timeMillis" : 1244,
	"counts" : {
		"input" : 7042,
		"emit" : 7042,
		"reduce" : 260,
		"output" : 214
	},
	"ok" : 1
}
```
### 3.3. ¿Qué ocurre si en un país hay dos parejas de ciudades que están a la misma distancia mínima? ¿Cómo harías para que aparecieran todas?
```
db.runCommand({
  mapReduce: "cities",
  map: function MapCode() {
      emit(this.CountryID, {
              "data": [{
                  "city": this.City,
                  "lat": this.Latitude,
                  "lon": this.Longitude
              }]
          });
  },
  reduce: function ReduceCode(key, values) {
      var reduced = {
          "data": []
      };
      for (var i in values) {
          var inter = values[i];
          for (var j in inter.data) {
              reduced.data.push(inter.data[j]);
          }
      }
      return reduced;
  },
  finalize: function Finalize(key, reduced) {
      if (reduced.data.length == 1) {
          return {
              "message": "Este pais solo contiene una ciudad"
          };
      }
      var min_dist = 999999999999;
      var city1 = {
          "name": ""
      };
      var city2 = {
          "name": ""
      };
      var c1;
      var c2;
      var d2;
      for (var i in reduced.data) {
          for (var j in reduced.data) {
              if (i >= j) continue;
              c1 = reduced.data[i];
              c2 = reduced.data[j];
              d2 = (c1.lat - c2.lat) * (c1.lat - c2.lat) + (c1.lon - c2.lon) * (c1.lon - c2.lon);
              if (d2 < min_dist && d2 > 0) {
                  min_dist = d2;
                  cities = [];
                  cities.push([c1.city, c2.city]);
              } else if (d2 == min_dist) {
                  cities.push([c1.city, c2.city]);
              }
          }
      }
      return {
          "cities": cities,
          "dist": Math.sqrt(min_dist)
      };
  },
  query: { CountryID: { $ne: 254 } },
  out: { merge: "ciudades_proximas" }
});
```
Salida de la consulta:
```
{
	"result" : "ciudades_proximas",
	"timeMillis" : 1217,
	"counts" : {
		"input" : 7042,
		"emit" : 7042,
		"reduce" : 260,
		"output" : 214
	},
	"ok" : 1
}
```
### 3.4. ¿Cómo podríamos obtener adicionalmente la cantidad de parejas de ciudades evaluadas para cada país consultado?
```
db.runCommand({
  mapReduce: "cities",
  map: function MapCode() {
      emit(this.CountryID, {
              "data": [{
                  "city": this.City,
                  "lat": this.Latitude,
                  "lon": this.Longitude
              }]
          });
  },
  reduce: function ReduceCode(key, values) {
      var reduced = {
          "data": []
      };
      for (var i in values) {
          var inter = values[i];
          for (var j in inter.data) {
              reduced.data.push(inter.data[j]);
          }
      }
      return reduced;
  },
  finalize: function Finalize(key, reduced) {
      if (reduced.data.length == 1) {
          return {
              "message": "Este pais solo contiene una ciudad"
          };
      }
      var min_dist = 999999999999;
      var city1 = {
          "name": ""
      };
      var city2 = {
          "name": ""
      };
      var c1;
      var c2;
      var d2;
      var numParejasC;
      for (var i in reduced.data) {
          for (var j in reduced.data) {
              if (i >= j) continue;
              c1 = reduced.data[i];
              c2 = reduced.data[j];
              d2 = (c1.lat - c2.lat) * (c1.lat - c2.lat) + (c1.lon - c2.lon) * (c1.lon - c2.lon);
              if (d2 > 0){
                  numParejasC++;
                  if (d2 < min_dist) {

                      min_dist = d2;
                      city1 = c1;
                      city2 = c2;
                  }
              }
          }
      }
      return {
          "city1": city1.name,
          "city2": city2.name,
          "numParejasC": numParejasC,
          "dist": Math.sqrt(min_dist)
      };
  },
  query: { CountryID: { $ne: 254 } },
  out: { merge: "ciudades_proximas" }
});
```
Salida de la consulta:
```
{
	"result" : "ciudades_proximas",
	"timeMillis" : 1282,
	"counts" : {
		"input" : 7042,
		"emit" : 7042,
		"reduce" : 260,
		"output" : 214
	},
	"ok" : 1
}
```
### 3.5. ¿Cómo podríamos la distancia media entre las ciudades de cada país?.
```
db.runCommand({
  mapReduce: "cities",
  map: function MapCode() {
      emit(this.CountryID, {
              "data": [{
                  "city": this.City,
                  "lat": this.Latitude,
                  "lon": this.Longitude
              }]
          });
  },
  reduce: function ReduceCode(key, values) {
      var reduced = {
          "data": []
      };
      for (var i in values) {
          var inter = values[i];
          for (var j in inter.data) {
              reduced.data.push(inter.data[j]);
          }
      }
      return reduced;
  },
  finalize: function Finalize(key, reduced) {
      if (reduced.data.length == 1) {
          return {
              "message": "Este pais solo contiene una ciudad"
          };
      }
      var min_dist = 999999999999;
      var city1 = {
          "name": ""
      };
      var city2 = {
          "name": ""
      };
      var c1;
      var c2;
      var d2;
      var numParejasC;
      var totalDist;
      for (var i in reduced.data) {
          for (var j in reduced.data) {
              if (i >= j) continue;
              c1 = reduced.data[i];
              c2 = reduced.data[j];
              d2 = (c1.lat - c2.lat) * (c1.lat - c2.lat) + (c1.lon - c2.lon) * (c1.lon - c2.lon);
              if (d2 > 0){
                  numParejasC++;
                  totalDist += d2;
                  if (d2 < min_dist) {

                      min_dist = d2;
                      city1 = c1;
                      city2 = c2;
                  }
              }
          }
      }
      return {
          "city1": city1.name,
          "city2": city2.name,
          "distMedia": totalDist/numParejasC,
          "dist": Math.sqrt(min_dist)
      };
  },
  query: { CountryID: { $ne: 254 } },
  out: { merge: "ciudades_proximas" }
});
```
Salida de la consulta:
```
{
	"result" : "ciudades_proximas",
	"timeMillis" : 1275,
	"counts" : {
		"input" : 7042,
		"emit" : 7042,
		"reduce" : 260,
		"output" : 214
	},
	"ok" : 1
}
```

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Bibliografía

<p id="1">

[1]:  J.M. Medina Rodríguez (2016) Tutorial MongoDB. Cloud Computing: Servicios y Aplicaciones.
</p>
