package arman.spark.twitterTopHashtags;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

public class THDriver {
	private SparkConf conf;
	private JavaStreamingContext sc;
	
	public THDriver() {
		conf = new SparkConf().setAppName("Twitter Top Hashtags");
	}
	
	public void run(String[] args){
		if(args.length != 5){
			printHelp();
			System.exit(1);
		}
		
		sc = new JavaStreamingContext(conf, Durations.seconds(Integer.parseInt(args[0])));
		sc.checkpoint(args[1]);
		
		JavaReceiverInputDStream<twitter4j.Status> statuses = TwitterUtils.createStream(sc);
		JavaPairDStream<String, Long> hashtags = statuses.flatMapToPair(new GetHashtags());
		JavaPairDStream<String, Long> hashtagsCount = hashtags.updateStateByKey(new UpdateReduce());
		hashtagsCount.foreachRDD(new saveText(args[2], Boolean.parseBoolean(args[3].toLowerCase())));
		
		sc.start();
		sc.awaitTerminationOrTimeout(Long.parseLong(args[4]) * 1000);
		sc.stop();
	}
	
	private void printHelp(){
		System.out.println("Usage: TwitterTopHashtags <RefreshTime(Sec)> <CheckpointDir> <OutputDir> <SortOutput(true|false)> <TimeToRunProgram(Sec)>");
	}
}
