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
public class MaxMinAllReducer extends MapReduceBase implements Reducer<LongWritable, DoubleWritable, Text, DoubleWritable> {


	private static final int NUM_VARS = 10;
	public void reduce(LongWritable key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		Double maxValue = Double.MIN_VALUE;
		Double minValue = Double.MAX_VALUE;


		while (values.hasNext()) {
			Double v = values.next().get();
			maxValue = Math.max(maxValue, v);
			minValue = Math.min(minValue, v);

		}



	output.collect(new Text("Max var "+key+":"), new DoubleWritable(maxValue));
	output.collect(new Text("Min var "+key+":"), new DoubleWritable(minValue));

	}
}
