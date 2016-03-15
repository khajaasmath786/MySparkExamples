package learningspark

/**
 * Illustrates a simple map partition to parse JSON data in Scala
 * Loads the data into a case class with the name and a boolean flag
 * if the person loves pandas.
 */


/*
 Loading the data as a text file and then parsing the JSON data is an approach that we can use in all of
the supported languages. This works assuming that you have one JSON record per row; if you have
multiline JSON files, you will instead have to load the whole file and then parse each file. If
constructing a JSON parser is expensive in your language, you can use mapPartitions() to reuse the
parser
 * */



import java.io.File
import org.apache.commons.io.FileUtils
import org.apache.spark.SparkContext
import play.api.libs.json.Json
import org.apache.spark.rdd.RDD

object BasicParseJson {
  case class Person(name: String, lovesPandas: Boolean) //case class will be used for pattern matching. Accessor and mutator are called by default. Apply method of companion object and equals and hashcode too
  implicit val personReads = Json.format[Person]

  def main(args: Array[String]) {
    if (args.length < 3) {
      println("Usage: [sparkmaster] [inputfile] [outputfile]")
        // local Input/json Output/Json
      exit(1)
    }
    val master = args(0)
    val inputFile = args(1)
    val outputFile = args(2)
    val sc = new SparkContext(master, "BasicParseJson", System.getenv("SPARK_HOME"))
    val input = sc.textFile(inputFile)
    val parsed = input.map(Json.parse(_))
    
    parsed.foreach { println }
    
    FileUtils.deleteDirectory(new File(outputFile));
    
    
   /* import play.api.libs.json.Json
      This import give access to the most basic JSON features :
      Json.parse : parses a string to JsValue
      JSON is className and parse is static method here*/

    // We use asOpt combined with flatMap so that if it fails to parse we
    // get back a None and the flatMap essentially skips the result.
    val result = parsed.flatMap(record => personReads.reads(record).asOpt)
    
    result.filter(_.lovesPandas).foreach { println } // Accessing tuples of Person Object _.
    result.filter(_.name.contains("Holden")).foreach { println } // Accessing tuples of Person Object _.
    
    println("----------------Converting Parsed Json to Json File again---------------")
    val fields=result.filter(_.lovesPandas).map { rec => rec.lovesPandas+"|"+rec.name }
    fields.foreach { println }
    //fields.saveAsTextFile(outputFile)
    
    //--------------------------------------------------------------------------------
    
    result.filter(_.lovesPandas).map(Json.toJson(_)).saveAsTextFile(outputFile)
  }
  
def processOnlyPandas(lines: RDD[Person]) = {
    // Filter our DStream for lines with "error"
   // lines.filter(_.contains("error"))
    lines.filter {_.lovesPandas}
}

def processOnlyName(lines: RDD[Person]) = {
    // Filter our DStream for lines with "error"
   // lines.filter(_.contains("error"))
    lines.filter {_.name.contains("Bear")}
}
  
}
