package xmlprocessing
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.WildcardFileFilter
import java.io._;
object GenerateXMLData {
  def main(args: Array[String]): Unit = {
     var inputFileLocation = "/user/cloudera/sqoop_import/departments"; //or "hdfs://quickstart.cloudera:8022/user/cloudera/sqoop_import/departments"
    var outputLocation = "/user/cloudera/scalaspark/departmentsSeqWithKey"; //or "hdfs://quickstart.cloudera:8022/user/cloudera/scalaspark/departmentsTesting"
    var appName = "SequenceFileOutputFormatWithKey"
    var master = "spark://192.168.56.1:7077"

    if (args.length >= 4) {
      println("Inside arguments list")
      inputFileLocation = args(0);
      outputLocation = args(1);
      appName = args(2)
      master = args(3)
    }
    
    inputFileLocation = "Input/departments"
    outputLocation = "Output";
    
    FileUtils.deleteDirectory(new File(outputLocation));
    println("master -->"+master)
    val sparkConf = new SparkConf().setAppName(appName).setMaster(master);
    val sc = new SparkContext(sparkConf);
  }
}
