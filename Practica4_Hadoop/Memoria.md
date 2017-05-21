<div class="portada">

# Práctica 4: Computación Distribuida y Escalable con Hadoop

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
- [Comentarios generales](#comentarios-generales)
- [Tarea 1: mínimo de la variable 5.](#tarea-1-minimo-de-la-variable-5)
- [Tarea 2: máximo de la variable 5.](#tarea-2-maximo-de-la-variable-5)
- [Tarea 3: máximo y mínimo de la variable 5.](#tarea-3-maximo-y-minimo-de-la-variable-5)
- [Bibliografía](#bibliografia)

<!-- tocstop -->

<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Enunciado de la práctica
El objetivo de esta práctica es realizar programas escalables para mejorar la eficiencia en entornos Big Data. Para ello haremos uso del entorno que se ha convertido en un estándar _de facto_ como es **Hadoop**, utilizando `HDFS` como sistema de archivos distribuido y `Hadoop-MapReduce` como mecanismo de ejecución.

Para constatar el manejo de la herramienta anterior, el alumno deberá realizar las siguientes tareas:
1. Calcula el valor mínimo de la variable (columna) 5.
2. Calcula el valor máximo de la variable (columna) 5.
3. Calcula al mismo tiempo los valores máximo y mínimo de la variable 5.
4. Calcula los valores máximo y mínimo de todas las variables (salvo la última).
5. Realizar la media de la variable 5.
6. Obtener la media de todas las variables (salvo la clase).
7. Comprobar si el conjunto de datos ECBDL es balanceado o no, es decir si el ratio entre las clase es menor o mayor que 1,5 respectivamente.
8. Cálculo del coeficiente de correlación entre todas las parejas de variables.

## Comentarios generales
Por comodidad crearé alias para todos los comandos de hadoop tal cual se indica en [1], para ello en lugar de crearlos usando el comando `alias` lo haré modificando el fichero `~/.bashrc` con el objetivo de que estos alias sean permanentes en múltiples sesiones y no haya que recrearlos en cada inicio. Para llevar esto a cabo añado las siguientes lineas al mencionado fichero, cierro la sesión actual y al abrir una nueva ya estarán disponibles estos alias.
```
# User specific aliases and functions
alias hput="hadoop fs -put"
alias hcat="hadoop fs -cat"
alias hls="hadoop fs -ls"
alias hrmr="hadoop fs -rmr"
alias hmkdir="hadoop fs -mkdir"
alias hmv="hadoop fs -mv"
```

Además he creado un alias para el comando `jar` ya que no se encuentra correctamente enlazado con su enlace simbólico en `/usr/bin` como el resto de utilidades de java, por tanto no es invocable directamente desde terminal. Para ello añado la siguiente linea a `.bashrc`:
```
alias jar="/etc/alternatives/jarc"
```

También comentar que he comenzado con el ejemplo de [1] que calcula el mínimo de la columna de índice 5 (la sexta), por tanto todas las medidas estadísticas de la columna 5 las he realizado sobre la 6 debido a que mis compañeros han realizado todos sus cálculos sobre dicha columna.

## Tarea 1: mínimo de la variable 5.
Esta tarea consiste en calcular el mínimo de la columna 6. He partido del ejemplo disponible en `/tmp` con alguna ligera modificación de estilo. Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T1
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes Min*
jar -cvf jars/Min.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/Min.jar oldapi.Min /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T1/output/

# Para comprobar los resultados
hcat P4/T1/output/*
# Salida: Min	-11.0
```

## Tarea 2: máximo de la variable 5.
Esta tarea consiste en calcular el máximo de la columna 6. He partido de la tarea previa cambiando las apariciones de min por max y modificando en especial la clase reducer con el objetivo de obtener el máximo, dicha clase ha quedado así:
```
public class MaxReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {


	public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		Double maxValue = Double.MIN_VALUE;
		while (values.hasNext()) {
			maxValue = Math.max(maxValue, values.next().get());
		}
	output.collect(key, new DoubleWritable(maxValue));
	}
}
```

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T2
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes Max*
jar -cvf jars/Max.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/Max.jar oldapi.Max /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T2/output/

# Para comprobar los resultados
hcat P4/T2/output/*
# Salida: Max	9.0
```

## Tarea 3: máximo y mínimo de la variable 5.
Esta tarea consiste en calcular el máximo y el mínimo de la columna 6. He partido de la tarea previa cambiando las apariciones de max por maxmin y modificando en especial la clase reducer con el objetivo de obtener el máximo y el mínimo simultáneamente. Dicha ha clase ha quedado de la siguiente manera:
```
public class MaxMinReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {


	public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		Double maxValue = Double.MIN_VALUE;
		Double minValue = Double.MAX_VALUE;

		while (values.hasNext()) {
			Double v = values.next().get();
			maxValue = Math.max(maxValue, v);
			minValue = Math.min(minValue, v);

		}
	output.collect(new Text("Max"), new DoubleWritable(maxValue));
	output.collect(new Text("Min"), new DoubleWritable(minValue));

	}
}
```
Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T3
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes MaxMin*
jar -cvf jars/MaxMin.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/MaxMin.jar oldapi.MaxMin /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T3/output/

# Para comprobar los resultados
hcat P4/T3/output/*
# Salida:
# Max	9.0
# Min	-11.0
```

## Tarea 4: Calcula los valores máximo y mínimo de todas las variables (salvo la última).

Esta tarea consiste en calcular el máximo y el mínimo de todas las columnas. He partido de la tarea previa cambiando la clase mapper para que utilice como clave el número de la variable.
```
public void map(LongWritable key, Text value, OutputCollector<LongWritable, DoubleWritable> output, Reporter reporter) throws IOException {
                String line = value.toString();
                String[] parts = line.split(",");

                //i < parts.length -1 to avoid the last element
                for(int i=0; i< parts.length -1; i++){
                    output.collect(new LongWritable((long)i), new DoubleWritable(Double.parseDouble(parts[i])));
                }
        }
```
También he tenido que modificar ligeramente la clase reducer para devolver al output a que variable (clave) corresponde dicho mínimo y máximo.

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T4
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes MaxMinAll*
jar -cvf jars/MaxMinAll.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/MaxMinAll.jar oldapi.MaxMinAll /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T4/output/

# Para comprobar los resultados
hcat P4/T4/output/*
# Salida:
# Max var 0:	0.768
# Min var 0:	0.094
# Max var 1:	0.154
# Min var 1:	0.0
# Max var 2:	10.0
# Min var 2:	-12.0
# Max var 3:	8.0
# Min var 3:	-11.0
# Max var 4:	9.0
# Min var 4:	-12.0
# Max var 5:	9.0
# Min var 5:	-11.0
# Max var 6:	9.0
# Min var 6:	-13.0
# Max var 7:	9.0
# Min var 7:	-12.0
# Max var 8:	7.0
# Min var 8:	-12.0
# Max var 9:	10.0
# Min var 9:	-13.0
```


<!-- Salto de página -->
<div style="page-break-before: always;"></div>

## Bibliografía


<p id="1">

[1]: Cloud Computing (2017), Ejercicios HADOOP: Implementación y análisis de funciones básicas sobre conjuntos de datos BigData.

</p>


<p id="2">

[2]: Hadoop (n.d.),
Apache Hadoop Main 2.6.0 APIs. Consultado en 05/2017, <https://hadoop.apache.org/docs/r2.6.0/api/>

</p>

<p id="3">

[3]: M. Parra (2017), Starting with HDFS. Consultado en 05/2017, <https://github.com/manuparra/MasterDegreeCC_Practice/blob/master/starting_hdfs.md>

</p>
