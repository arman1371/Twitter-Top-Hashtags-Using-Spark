package arman.spark.twitterTopHashtags;

import java.util.List;

import org.apache.spark.api.java.function.Function2;

import com.google.common.base.Optional;

public class UpdateReduce implements Function2<List<Long>, Optional<Long>, Optional<Long>> {
	public Optional<Long> call(List<Long> in1, Optional<Long> in2){
		long newSum = in2.or((long) 0);
		for (long i : in1) {
			newSum += i;
		}
		return Optional.of(newSum);
	}
}
