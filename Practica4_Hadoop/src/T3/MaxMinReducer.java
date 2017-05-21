package oldapi;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
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
