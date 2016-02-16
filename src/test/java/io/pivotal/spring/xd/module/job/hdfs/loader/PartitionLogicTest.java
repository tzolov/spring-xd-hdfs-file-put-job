/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.pivotal.spring.xd.module.job.hdfs.loader;

import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.hadoop.store.partition.DefaultPartitionKey;
import org.springframework.data.hadoop.store.partition.DefaultPartitionStrategy;
import org.springframework.data.hadoop.store.partition.PartitionResolver;

public class PartitionLogicTest {

	@Test
	public void testHdfsPathPartition() {

		String hdfsBaseDir = "/xd/basedir";

		String expression = "region + '/' + dateFormat('yyyy/MM/dd/HH/mm', timestamp)";

		DefaultPartitionStrategy<String> partitionStrategy = new DefaultPartitionStrategy<String>(expression);

		DefaultPartitionKey key = new DefaultPartitionKey();
		key.put("region", hdfsBaseDir);

		PartitionResolver<Map<String, Object>> resolver = partitionStrategy.getPartitionResolver();

		Path resolvedPath = resolver.resolvePath(key);

		Assert.assertNotNull(resolvedPath);

		Assert.assertEquals("/xd/basedir", resolvedPath.getParent().getParent().getParent().getParent().getParent()
				.toString());

		System.out.println(resolvedPath);
	}
}
