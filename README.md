## Spring XD Job for copying local files into HDFS (hdfs-file-put)

Existing SpringXD HDFS sink and HDFS jobs support only text  files! This job can be used to ingest both text and binary files. 

### Build
```
git clone 
cd ./hdfs-file-put
mvn clean install
```
The build module is in `target` : `hdfs-file-put-0.0.7-SNAPSHOT.jar`

### Install
Fromthe XD Shell run:
```
xd:>module upload --name hdfs-file-put --type job --file <absolute path to the module jar>/hdfs-file-put-0.0.7-SNAPSHOT.jar
```

### How To Use

###### Create and Launch Job

Load local `/home/spring-xd/my-local-file.bin` into HDFS `/user/spring-xd/baseDir` folder:
```
xd:>job create hdfsPutJob --definition "hdfs-file-put --deleteFiles=true --hdfsDir=/user/spring-xd/baseDir" --deploy
xd:>job launch --name hdfsPutJob --params {"localFile":"/home/spring-xd/my-local-file.bin"}
```
###### Partition target HDFS directory
```
xd:>job create hdfsPutJob --definition "hdfs-file-put --deleteFiles=true --hdfsDir=/user/spring-xd/baseDir --partitionPath=\"dateFormat('yyyy/MM/dd/HH/mm', timestamp)\" " --deploy
```
Partition pattern above will generate the follwoing HDFS file structure:
```
$ hdfs dfs -ls /user/spring-xd/baseDir/**/**/**/**/**

-rw-r--r--   3 spring-xd hdfs    6488666 2016-02-16 02:25 /user/spring-xd/base2/2016/02/16/02/25/data11.dat
-rw-r--r--   3 spring-xd hdfs    6488666 2016-02-16 02:25 /user/spring-xd/base2/2016/02/16/02/25/data12.dat
-rw-r--r--   3 spring-xd hdfs    6488666 2016-02-16 02:27 /user/spring-xd/base2/2016/02/16/02/27/data666.dat
-rw-r--r--   3 spring-xd hdfs    6488666 2016-02-16 10:57 /user/spring-xd/base2/2016/02/16/10/57/data999.dat
```

###### Activate with XD Stream
```
xd:>stream create --name copyLocalFilesToHdfs --definition "file --mode=ref --dir=/home/spring-xd/local_files | transform --expression={localFile:payload.getCanonicalPath()} > queue:job:hdfsPutJob" --deploy 
```
Now all file you copy into the local `/home/spring-xd/local_files` folder will be `put` into HDFS via the `hdfs-file-put` job.

### Job Options
Job Options. Can be set either as command line parameter: `--optionName optionValue` or job parameters `{"optionName":"optionValue"}`

| Option        | Description           | Default  |
| ------------- |-------------|:-----:|
| localFile | Local folder or file name (absolute path) to copy into HDFS. | Required (no default) |
| deleteFiles | If set to `true` then deletes the source, local files. | false |
| hdfsDir | HDFS directory under which files are stored. | "/tmp/xd/output/" |
| partitionPath | SpEL expression defining the partition path. | '' |
| restartable | Whether the job should be retartable or not in case of failure. Set this to false if the Job should not be restarted. | true |


### Delete Module
```
xd:>module delete --name job:hdfs-file-put 
```



