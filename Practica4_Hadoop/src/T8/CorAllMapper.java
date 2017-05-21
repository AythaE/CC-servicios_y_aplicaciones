package oldapi;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

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
