package com.ln.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by n on 2017/6/15.
 */
public class WordCount {
    public static void main(String[] args){
        String inputPath = "G://tmp/1.txt";
        String outputPath = "G://2.txt";
        JavaSparkContext javaSparkContext = new JavaSparkContext("local", "wordcount");
        JavaRDD<String> rdd = javaSparkContext.textFile(inputPath);
        JavaPairRDD<String,Integer> wordCounts = rdd
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        wordCounts.saveAsTextFile(outputPath);

    }
}
