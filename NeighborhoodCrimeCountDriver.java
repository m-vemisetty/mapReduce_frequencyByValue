package mr_demo.CrimeFrequency;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class NeighborhoodCrimeCountDriver {
	public static void main(String[] args) throws Exception {
	    if (args.length != 2) {
	      System.err.println("Usage: Frequency <input path> <output path>");
	      System.exit(-1);
	    }

	    // create a Hadoop job and set the main class
	    Job job = Job.getInstance();
	    job.setJarByClass(NeighborhoodCrimeCountDriver.class);
	    job.setJobName("NeighborhoodCrimeCountDriver");

	    // set the input and output path
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    // set the Mapper and Reducer class
	    job.setMapperClass(CrimeCountMapper.class);
	    job.setReducerClass(CrimeCountReducer.class);

	    // specify the type of the output
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);

	    // run the job
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	  }
}
