package arman.spark.twitterTopHashtags;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;

public class saveText implements Function<JavaPairRDD<String, Long>, Void> {
	private static String outDir;
	private static boolean sorted;
	
	public saveText(String outDir, boolean sorted) {
		this.outDir = outDir;
		this.sorted = sorted;
	}
	
	public Void call(JavaPairRDD<String, Long> in){
		if(sorted){
			in = in.mapToPair(a -> a.swap()).sortByKey(false).mapToPair(a -> a.swap());
		}
		in.saveAsTextFile(outDir);
		return null;
	}
}
