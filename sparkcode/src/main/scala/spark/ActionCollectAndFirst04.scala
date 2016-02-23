package spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.WildcardFileFilter
import java.io._;

class ActionCollectAndFirst {
  
  
  
  object ActionCollectAndFirst
{
 
  def main(args: Array[String]): Unit = {
     var inputFileLocation = "Filter.txt";
    var outputLocation = "OutputFile";

    if (args.length >= 2) {
      println("Inside arguments list")
      inputFileLocation = args(0);
      outputLocation = args(1);
    }
    FileUtils.deleteDirectory(new File(outputLocation));

    val sparkConf = new SparkConf().setAppName("WordCountInScala").setMaster("local");
    val sc = new SparkContext(sparkConf);

    //val lineRdd = sc.textFile("Food.txt")// .count() for number of records and try new variable with cache also

    val lineRdd = sc.textFile(inputFileLocation).cache() //cache will make RDD not to recompile every time we use RDD in next steps.

    val filterWords=lineRdd.filter { line => line.contains("Asmath") }

    val firstLine=filterWords.first();
    println("First Matching word and the line that contains it is -->"+firstLine)
    filterWords.saveAsTextFile("OutputFile");
    
    
    
    //wordCount.saveAsTextFile("/daastest/sparkoutput"); --> this is hdfs

    //Way to submit jar in cluster and access that in server URL.
    /*   ./bin/spark-submit \
  --class <main-class>
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key>=<value> \
  ... # other options
  <application-jar> \
  [application-arguments] */

    /*    
    C:\spark-1.6.0-bin-hadoop2.6\bin>spark-submit --class spark.WordCount --master l
ocal C:\Users\mc41946\git\MySparkExamples\sparkcode\target\scala-2.10\simple-pro
ject_2.10-1.0.jar Food.txt C:\Users\mc41946\git\MySparkExamples\sparkcode\Out*/
    
    //In class you have to give object name of scala not the class name

  }
  
}
}