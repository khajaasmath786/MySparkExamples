
@echo off
echo Hello this a test batch file
cd C:\spark\bin
echo Use the Spark URL from master link from http://localhost:8080
spark-class.cmd org.apache.spark.deploy.worker.Worker spark://192.168.56.1:7077
