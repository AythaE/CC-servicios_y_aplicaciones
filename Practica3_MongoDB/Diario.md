
Para formateo bonito `.pretty()`

escribir todas las consultas en JavaScript y cargarlo en mongodb con `load('ruta/fichero.js')`


1.1
    use test
    db.pedidos.find().pretty()

1.2

     db.pedidos.find().limit(1).pretty()
     db.pedidos.findOne()

1.3
    db.pedidos.find({id_cliente: {$eq: 2222}}).pretty()
    ```
    {
    "_id" : ObjectId("58f64efc2a282f5b9867b630"),
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

1.4
    db.pedidos.find({ "Pedidos.Productos.Precio_unidad": { $gt: 94 } }).pretty()
    ```
    {
	"_id" : ObjectId("58f64efc2a282f5b9867b62f"),
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
	"_id" : ObjectId("58f64efc2a282f5b9867b630"),
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
	"_id" : ObjectId("58f64efc2a282f5b9867b633"),
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
