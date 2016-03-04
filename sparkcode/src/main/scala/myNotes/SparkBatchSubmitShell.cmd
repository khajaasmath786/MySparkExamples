


@echo off
echo Hello this a test batch file
cd C:\spark\bin
echo Use the Spark URL from master link from http://localhost:8080
spark-submit --class itvarsity.class10.WordCountScala --master spark://192.168.56.1:7077 C:\Users\mc41946\git\MySparkExamples\sparkcode\target\scala-2.10\movie-recommendation-new_2.10-1.0.jar departments departmentsout WordCount1 spark://192.168.56.1:7077 
echo Completed job. Check messages
cd C:\spark\bin
echo spark-submit --class itvarsity.class10.WordCountScala --master local movie-recommendation-new_2.10-1.0.jar /user/hive/warehouse/departments /user/hive/warehouse/departmentout WordCount1 local
echo spark-submit --class itvarsity.class10.WordCountScala --master spark://10.0.2.15:7077 movie-recommendation-new_2.10-1.0.jar /user/hive/warehouse/departments /user/hive/warehouse/departmentout WordCount1 spark://10.0.2.15:7077