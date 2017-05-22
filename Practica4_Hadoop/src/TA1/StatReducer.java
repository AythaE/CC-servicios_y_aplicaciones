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

public class StatReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        Double sum = 0.0;
        Double maxValue = Double.MIN_VALUE;
        Double minValue = Double.MAX_VALUE;
        int numValues = 0;

        while (values.hasNext()) {
            Double v = values.next().get();
            sum += v;
            numValues++;
            maxValue = Math.max(maxValue, v);
            minValue = Math.min(minValue, v);
        }
        output.collect(new Text("Min var " + key + ":"), new DoubleWritable(minValue));
        output.collect(new Text("Avg var " + key + ":"), new DoubleWritable(sum / numValues));
        output.collect(new Text("Max var " + key + ":"), new DoubleWritable(maxValue));

    }
}
