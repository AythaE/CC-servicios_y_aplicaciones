package oldapi;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class AvgAll {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: AvgAll <input path> <output path>");
			System.exit(-1);
		}
		JobConf jConf = new JobConf(AvgAll.class);
		jConf.setJobName("AvgAll");
		FileInputFormat.addInputPath(jConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jConf, new Path(args[1]));

		jConf.setMapperClass(AvgAllMapper.class);
		jConf.setReducerClass(AvgAllReducer.class);
		jConf.setOutputKeyClass(LongWritable.class);
		jConf.setOutputValueClass(DoubleWritable.class);
		JobClient.runJob(jConf);
	}
}
