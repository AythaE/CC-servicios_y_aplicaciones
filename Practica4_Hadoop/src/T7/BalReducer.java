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

public class BalReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {


    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        long numC0 = 0, numC1 = 0;

        while (values.hasNext()) {
            Double v = values.next().get();

            if (v == 0) {
                numC0++;
            } else {
                numC1++;
            }

        }


        output.collect(new Text("Ratio:"), new DoubleWritable(((double) Math.max(numC0, numC1)) / Math.min(numC0, numC1)));

    }
}
