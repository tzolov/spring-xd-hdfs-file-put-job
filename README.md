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
Fromthe XD Shell run:
```
xd:>module upload --name hdfs-file-put --type job --file <absolute path to the module jar>/hdfs-file-put-0.0.5-SNAPSHOT.jar
```

#### How to use
```
xd:>job create hdfsPutJob --definition "hdfs-file-put --deleteFiles=true --hdfsDir=/user/spring-xd/base1" --deploy
xd:>job launch --name hdfsPutJob --params {"localFile":"/home/spring-xd/my-local-file.bin"}
```

##### hdfs-file-put Options
* localFile - Local file (full path) to put in HDFS.
* deleteFiles - If set to `true` then deletes the source, local files. Default: false.
* hdfsDir - HDFS directory under which files are stored. Default: "/tmp/xd/output/".
* partitionPath - SpEL expression defining the partition path. Default: "".
* restartable - Whether the job should be retartable or not in case of failure. Set this to false if the Job should not be restarted. Default: true.




