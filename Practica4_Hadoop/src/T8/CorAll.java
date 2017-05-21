package oldapi;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

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
