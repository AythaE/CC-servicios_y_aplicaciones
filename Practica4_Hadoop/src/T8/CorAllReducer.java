package oldapi;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
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
		double covar = prodTotal / numValues - avgI * avgJ;

		double desvI = Math.sqrt(varIPowTotal / numValues - Math.pow(avgI, 2)),
                desvJ = Math.sqrt(varJPowTotal / numValues - Math.pow(avgJ, 2));



		output.collect(new Text("Corr vars "+vars[0]+","+vars[1]+":"), new DoubleWritable(covar / (desvI * desvJ)));
	}
}
