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
