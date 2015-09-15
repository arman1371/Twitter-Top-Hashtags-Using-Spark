# Twitter Top Hashtags Using Spark

## How to use
1. build the code with `mvn package` command
2. Get your keys from [Twitter](https://dev.twitter.com/apps)
3. Fill the twitter4j.properties file
4. Go to spark directory
5. Place the twitter4j.properties in your working directory
6. run program with this command: `./bin/spark-submit --class "arman.spark.twitterTopHashtags.TwitterTopHashtags" --master MASTER JAR_FILE REFRESH_TIME CHECKPOINT_DIR OUTPUT_DIR SORT_OUTPUT RUNNING_TIME`
  * MASTER: Master config based on: [Spark Config](https://spark.apache.org/docs/latest/submitting-applications.html#master-urls)
  * JAR_FILE: Jar file
  * REFRESH_TIME: Refresh intervals(Sec)
  * CHECKPOINT_DIR: Checkpoint directory
  * OUTPUT_DIR: Output dir
  * SORT_OUTPUT: Sort output by hashtags count (true|false)
  * RUNNING_TIME: Time to run the program(Sec)
