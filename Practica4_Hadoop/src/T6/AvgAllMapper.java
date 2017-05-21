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

public class AvgAllMapper extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable, DoubleWritable> {
        private static final int MISSING = 9999;

		public void map(LongWritable key, Text value, OutputCollector<LongWritable, DoubleWritable> output, Reporter reporter) throws IOException {
                String line = value.toString();
                String[] parts = line.split(",");

                //i < parts.length -1 to avoid the last element
                for(int i=0; i< parts.length -1; i++){
                    output.collect(new LongWritable((long)i), new DoubleWritable(Double.parseDouble(parts[i])));
                }
        }
}
