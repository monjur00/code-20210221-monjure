##File Ingestion Tool
A simple java program to read a csv file seperated by ';' and print basic file information like header names, file size, no of rows as shown below

    a. Column or field names present in the CSV
    b. Total size in bytes of the file
    c. Total number of rows

File can be provided as file system path arguments or file url arguments.

### Prerequisites
    1. Maven 3.6.3
    2. Java 11**

### Building the projects
```bash
mvn clean install
```

### Running the jar when file located in file system
```bash
java -jar target/file-ingestion-{version}.jar -u {url}
```
Ex: java -jar target/file-ingestion-1.0-SNAPSHOT.jar -u https://raw.githubusercontent.com/vamstar/challenge/master/Dataset3.csv

### Running the jar when file located at web address
```bash
java -jar target/file-ingestion-{version}.jar -p {absolute path}
```
Ex: java -jar target/file-ingestion-1.0-SNAPSHOT.jar -p /Users/myuser/file-ingestion/Dataset3.csv