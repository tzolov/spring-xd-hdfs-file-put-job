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

import org.springframework.xd.module.options.spi.ModuleOption;

/**
 * Holds options for the hdfs file job.
 * 
 * @author Christian Tzolov
 */
public class HdfsFilePutJobModuleOptionMetadata {

	protected Boolean restartable = true;

	protected Boolean deleteFiles = false;

	protected String localFile = null;
	
	protected String hdfsDir = "/tmp/xd/output/";

	protected String partitionPath = null;

	public String getHdfsDir() {
		return hdfsDir;
	}

	@ModuleOption(value = "HDFS directory under which files are stored.", defaultValue = "/tmp/xd/output/")
	public void setHdfsDir(String hdfsDir) {
		this.hdfsDir = hdfsDir;

	}

	public Boolean getRestartable() {
		return restartable;
	}

	@ModuleOption(value = "Whether the job should be retartable or not in case of failure. Set this to false if the Job should not be restarted.", defaultValue = "true")
	public void setRestartable(Boolean restartable) {
		this.restartable = restartable;
	}

	public Boolean getDeleteFiles() {
		return deleteFiles;
	}

	@ModuleOption(value = "If true deletes the source files.", defaultValue = "false")
	public void setDeleteFiles(Boolean deleteFiles) {
		this.deleteFiles = deleteFiles;
	}

	public String getPartitionPath() {
		return partitionPath;
	}

	@ModuleOption(value = "SpEL expression defining the partition path", defaultValue = "")
	public void setPartitionPath(String partitionPath) {
		this.partitionPath = partitionPath;
	}

	public String getLocalFile() {
		return localFile;
	}

	@ModuleOption(value = "Source (local) file ot be copied to HDFS", defaultValue = "")
	public void setLocalFile(String localFilePath) {
		this.localFile = localFilePath;
	}
}
