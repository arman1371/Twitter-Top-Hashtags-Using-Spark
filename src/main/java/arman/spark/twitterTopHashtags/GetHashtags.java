package arman.spark.twitterTopHashtags;

import java.util.ArrayList;

import org.apache.spark.api.java.function.PairFlatMapFunction;

import scala.Tuple2;

public class GetHashtags implements PairFlatMapFunction<twitter4j.Status, String, Long> {
	public Iterable<Tuple2<String, Long>> call(twitter4j.Status in){
		String[] words = in.getText().split(" ");
		ArrayList<Tuple2<String, Long>> hashtags = new ArrayList<Tuple2<String, Long>>();
		for (String word : words) {
			if(word.startsWith("#")){
				hashtags.add(new Tuple2<String, Long>(word, (long) 1));
			}
		}
		return hashtags;
	}
}
