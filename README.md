# hdfs-file-put Spring XD Job 

To be used as work around for ingesting binary files into HDFS. Currently the HDFS sink and other HDFS jobs support only text input files.

#### Build
```
git clone 
cd ./hdfs-file-put
mvn clean install
```
The build module is in `target` : `hdfs-file-put-0.0.5-SNAPSHOT.jar`

#### Install
```
xd:>module upload --name hdfs-file-put --type job --file /home/spring-xd/hdfs-file-put-0.0.5-SNAPSHOT.jar
```

