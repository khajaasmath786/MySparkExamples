
@echo off
echo Hello this a test batch file
cd C:\spark\bin
echo Use the Spark URL from master link from http://localhost:8080
spark-shell --master local 
