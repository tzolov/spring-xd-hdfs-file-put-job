import java.util.Map;

import org.springframework.data.hadoop.store.partition.DefaultPartitionKey;
import org.springframework.data.hadoop.store.partition.DefaultPartitionStrategy;
import org.springframework.data.hadoop.store.partition.PartitionResolver;

def String hdfsPath = hdfsDir

String str_partitionPath_empty = ":''"

println "partitionPath : [$partitionPath]"

if( partitionPath != str_partitionPath_empty )
{
	if (partitionPath?.trim()) {
		String expression = "region + '/' + " + partitionPath
	
		DefaultPartitionStrategy<String> partitionStrategy = new DefaultPartitionStrategy<String>(expression)
	
		DefaultPartitionKey key = new DefaultPartitionKey()
		key.put("region", hdfsDir)
	
		PartitionResolver<Map<String, Object>> resolver = partitionStrategy.getPartitionResolver()
	
		hdfsPath = resolver.resolvePath(key);	
	}
}

if (!fsh.test(hdfsPath)) {
	fsh.mkdir(hdfsPath)
}

println "Copy [$localFile] into [$hdfsPath]"

fsh.put(localFile, hdfsPath)

if (deleteFiles) {
	new File(localFile).delete()
}
