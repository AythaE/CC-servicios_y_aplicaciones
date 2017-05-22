package oldapi;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class Stat {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.err.println("Usage: Stat <input path> <col> <output path>");
			System.exit(-1);
		}

		JobConf jConf = new JobConf(Stat.class);
		jConf.setJobName("Stat");
		FileInputFormat.addInputPath(jConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jConf, new Path(args[2]));
        jConf.set("col", args[1]);
        jConf.setMapperClass(StatMapper.class);
		jConf.setReducerClass(StatReducer.class);
		jConf.setOutputKeyClass(Text.class);
		jConf.setOutputValueClass(DoubleWritable.class);
		JobClient.runJob(jConf);
	}
}
