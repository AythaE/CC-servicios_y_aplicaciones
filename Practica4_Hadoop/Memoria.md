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
  * [Tarea 1: mínimo de la variable 5.](#tarea-1-minimo-de-la-variable-5)
  * [Tarea 2: máximo de la variable 5.](#tarea-2-maximo-de-la-variable-5)
  * [Tarea 3: máximo y mínimo de la variable 5.](#tarea-3-maximo-y-minimo-de-la-variable-5)
  * [Tarea 4: Calcula los valores máximo y mínimo de todas las variables (salvo la última).](#tarea-4-calcula-los-valores-maximo-y-minimo-de-todas-las-variables-salvo-la-ultima)
  * [Tarea 5: Realizar la media de la variable 5.](#tarea-5-realizar-la-media-de-la-variable-5)
  * [Tarea 6: Obtener la media de todas las variables (salvo la clase).](#tarea-6-obtener-la-media-de-todas-las-variables-salvo-la-clase)
  * [Tarea 7: Comprobar si el conjunto de datos ECBDL es balanceado.](#tarea-7-comprobar-si-el-conjunto-de-datos-ecbdl-es-balanceado)
  * [Tarea 8. Cálculo del coeficiente de correlación entre todas las parejas de variables.](#tarea-8-calculo-del-coeficiente-de-correlacion-entre-todas-las-parejas-de-variables)
  * [Tareas adicionales](#tareas-adicionales)
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

### Tarea 1: mínimo de la variable 5.
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

### Tarea 2: máximo de la variable 5.
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

### Tarea 3: máximo y mínimo de la variable 5.
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

### Tarea 4: Calcula los valores máximo y mínimo de todas las variables (salvo la última).

Esta tarea consiste en calcular el máximo y el mínimo de todas las columnas. He partido de la tarea previa cambiando la clase mapper para que utilice como clave el número de la variable.
```
public class MaxMinAllMapper extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable, DoubleWritable> {

    public void map(LongWritable key, Text value, OutputCollector<LongWritable, DoubleWritable> output, Reporter reporter) throws IOException {
                String line = value.toString();
                String[] parts = line.split(",");

                //i < parts.length -1 to avoid the last element
                for(int i=0; i< parts.length -1; i++){
                    output.collect(new LongWritable((long)i), new DoubleWritable(Double.parseDouble(parts[i])));
                }
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

### Tarea 5: Realizar la media de la variable 5.
Esta tarea consiste en calcular la media de la columna 6. He partido de la tarea 3 cambiando la clase reducer con el objetivo de obtener la media. Dicha ha clase ha quedado de la siguiente manera:
```
public class AvgReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		Double sum = 0.0;
		int numValues = 0;

		while (values.hasNext()) {
			Double v = values.next().get();
			sum += v;
			numValues++;

		}
	output.collect(new Text("Avg"), new DoubleWritable(sum/numValues));

	}
}
```

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T5
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes Avg*
jar -cvf jars/Avg.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/Avg.jar oldapi.Avg /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T5/output/

# Para comprobar los resultados
hcat P4/T5/output/*
# Salida:
# Avg	-1.282261707288373
```

### Tarea 6: Obtener la media de todas las variables (salvo la clase).
Esta tarea consiste en calcular la media de todas las columnas. He partido de las 2 tareas previas dejando la clase mapper como en la tarea 4 y modificando la clase reducer para el cálculo de la media en lugar del máximo y el mínimo. Esta clase ha quedado de la siguiente manera:
```
public class AvgAllReducer extends MapReduceBase implements Reducer<LongWritable, DoubleWritable, Text, DoubleWritable> {


	private static final int NUM_VARS = 10;
	public void reduce(LongWritable key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		Double sum = 0.0;
		int numValues = 0;

		while (values.hasNext()) {
			Double v = values.next().get();
			sum += v;
			numValues++;

		}
		output.collect(new Text("Avg var "+key+":"), new DoubleWritable(sum/numValues));
	}
}
```

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T6
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes AvgAll*
jar -cvf jars/AvgAll.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/AvgAll.jar oldapi.AvgAll /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T6/output/

# Para comprobar los resultados
hcat P4/T6/output/*
# Salida:
# Avg var 0:	0.2549619599194048
# Avg var 1:	0.05212776590927781
# Avg var 2:	-2.188240380935686
# Avg var 3:	-1.408876789776933
# Avg var 4:	-1.7528724942777865
# Avg var 5:	-1.282261707288373
# Avg var 6:	-2.293434905140485
# Avg var 7:	-1.5875789403216172
# Avg var 8:	-1.7390052924221087
# Avg var 9:	-1.6989002790625127
```

### Tarea 7: Comprobar si el conjunto de datos ECBDL es balanceado.
Esta tarea consiste en calcular el ratio de miembros de una clase u otra utilizando para ello la columna 11. He partido de la tarea 5 cambiando el mapper para que coja los valores de la columna 11 y la clase reducer para contar los miembros de una u otra clase, acto seguido devuelvo el ratio como se puede observar:
```
public class BalReducer extends MapReduceBase implements Reducer<IntWritable, IntWritable, Text, DoubleWritable> {


    public void reduce(IntWritable key, Iterator<IntWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        long numC0 = 0, numC1 = 0;

        while (values.hasNext()) {
            Integer v = values.next().get();

            if (v == 0) {
                numC0++;
            } else {
                numC1++;
            }

        }


        output.collect(new Text("Ratio:"), new DoubleWritable(((double) Math.max(numC0, numC1)) / Math.min(numC0, numC1)));

    }
}
```

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T7
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes Bal*
jar -cvf jars/Bal.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/Bal.jar oldapi.Bal /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T7/output/

# Para comprobar los resultados
hcat P4/T7/output/*
# Salida:
# Ratio:	58.582560602010815
```

Como se ha dicho en el enunciado consideramos que un conjunto de datos es no balanceado si su ratio es mayor que 1,5. Como se puede apreciar viendo los resultados nos encontramos ante un conjunto muy desbalanceado, cabe recordar que este dataset es un subconjunto con el 10% de instancias del original.

### Tarea 8. Cálculo del coeficiente de correlación entre todas las parejas de variables.
Este es un cálculo mucho más complejo que todos los anteriores, siguiendo las recomendaciones de [1] he realizado los cálculos de cada linea en el mapper y la agregación de estos así como los cálculos derivados y el de la correlación en si en el reducer.

Estas son las clases de esta tarea:
```
public class CorAll {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: CorAll <input path> <output path>");
			System.exit(-1);
		}
		JobConf jConf = new JobConf(CorAll.class);
		jConf.setJobName("CorAll");
		FileInputFormat.addInputPath(jConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jConf, new Path(args[1]));

		jConf.setMapperClass(CorAllMapper.class);
		jConf.setReducerClass(CorAllReducer.class);
        jConf.setMapOutputKeyClass(Text.class);
        jConf.setMapOutputValueClass(Text.class);
		jConf.setOutputKeyClass(Text.class);
		jConf.setOutputValueClass(DoubleWritable.class);
		JobClient.runJob(jConf);
	}
}
```
```
public class CorAllMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private static final int MISSING = 9999;

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        String line = value.toString();
        String[] parts = line.split(",");

        //i < parts.length -1 to avoid the last element
        for (int i = 0; i < parts.length - 1; i++) {
            for (int j = parts.length - 1; j > i; j--){

                double prod = Double.parseDouble(parts[i])*Double.parseDouble(parts[j]);
                double iPow = Math.pow(Double.parseDouble(parts[i]), 2);
                double jPow = Math.pow(Double.parseDouble(parts[j]), 2);
                output.collect(new Text(i+","+j), new Text(parts[i]+","+parts[j]+","+prod+","+iPow+","+jPow));

            }
        }
    }
}
```
```
public class CorAllReducer extends MapReduceBase implements Reducer<Text, Text, Text, DoubleWritable> {

	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		String[] vars = key.toString().split(",");
		int numValues = 0;

		double varITotal = 0, varJTotal = 0, prodTotal = 0, varIPowTotal = 0, varJPowTotal = 0;
		while (values.hasNext()) {
			String[] calcValues = values.next().toString().split(",");
			numValues++;
			varITotal += Double.parseDouble(calcValues[0]);
            varJTotal += Double.parseDouble(calcValues[1]);
            prodTotal += Double.parseDouble(calcValues[2]);
            varIPowTotal += Double.parseDouble(calcValues[3]);
            varJPowTotal += Double.parseDouble(calcValues[4]);

        }

        double avgI = varITotal / numValues, avgJ = varJTotal / numValues;
		double covar = prodTotal / numValues;

		double desvI = Math.sqrt(varIPowTotal / numValues - Math.pow(avgI, 2)),
                desvJ = Math.sqrt(varJPowTotal / numValues - Math.pow(avgJ, 2));



		output.collect(new Text("Corr vars "+vars[0]+","+vars[1]+":"), new DoubleWritable(covar / (desvI * desvJ)));
	}
}
```

Para compilar las clases de esta tarea concreta y ejecutarla hay que ejecutar los siguientes comandos
```
cd T8
mkdir java_classes jars
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes CorAll*
jar -cvf jars/CorAll.jar -C java_classes / .

# Para lanzar la tarea
hadoop jar jars/CorAll.jar oldapi.CorAll /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./P4/T8/output/

# Para comprobar los resultados
hcat P4/T8/output/*
# Salida:
# Corr vars 4,9:	0.014041854998880898
# Corr vars 5,8:	0.015183324110128226
# Corr vars 6,7:	0.11488805268078417
# Corr vars 5,9:	0.023068393377281653
# Corr vars 6,8:	0.07783431570283235
# Corr vars 6,9:	0.1071360896407867
# Corr vars 7,8:	-0.3292179447994215
# Corr vars 7,9:	0.08936167755929571
# Corr vars 9,10:	6.760249558104627E-4
# Corr vars 0,1:	-0.1358991685967529
# Corr vars 8,10:	0.0015216434293567288
# Corr vars 8,9:	0.1084960047958963
# Corr vars 0,2:	0.09143593111837958
# Corr vars 7,10:	0.0019178149111979677
# Corr vars 0,3:	0.07005931837245442
# Corr vars 1,2:	-0.0030364539444686506
# Corr vars 6,10:	-7.410897394175622E-4
# Corr vars 0,4:	0.04742917824253894
# Corr vars 1,3:	0.00943834945001628
# Corr vars 5,10:	-0.008972118619624847
# Corr vars 0,5:	0.1291657271429676
# Corr vars 1,4:	0.058856701878819764
# Corr vars 2,3:	-0.01726247486762999
# Corr vars 4,10:	0.006248461582330308
# Corr vars 0,6:	0.19252517593397164
# Corr vars 1,5:	0.01465997763836581
# Corr vars 2,4:	0.018191261366109063
# Corr vars 3,10:	-0.0013630161986149115
# Corr vars 0,7:	0.17921266563963062
# Corr vars 1,6:	-0.03183255331836667
# Corr vars 2,10:	0.014897093818963873
# Corr vars 2,5:	0.024182999250758484
# Corr vars 3,4:	0.015754379166559307
# Corr vars 0,8:	0.06624560109920218
# Corr vars 1,10:	0.007533938229013501
# Corr vars 1,7:	-1.7503654865063562E-5
# Corr vars 2,6:	0.041153841377462724
# Corr vars 3,5:	0.016128930425374947
# Corr vars 0,10:	0.01603094280057236
# Corr vars 0,9:	0.13827089967478018
# Corr vars 1,8:	0.015894103473231183
# Corr vars 2,7:	0.03814283037771738
# Corr vars 3,6:	0.025952003813569456
# Corr vars 4,5:	0.07125079800784533
# Corr vars 1,9:	-0.0167306234422093
# Corr vars 2,8:	0.025077384911599235
# Corr vars 3,7:	0.01879122854336587
# Corr vars 2,9:	0.027549270387458427
# Corr vars 4,6:	0.018264386288745375
# Corr vars 4,7:	0.01984291578033614
# Corr vars 3,8:	0.016130402799924542
# Corr vars 5,6:	0.03200113594875155
# Corr vars 3,9:	0.01817123896585364
# Corr vars 4,8:	0.01224584385595619
# Corr vars 5,7:	0.03297998768398484
```

### Tareas adicionales


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
