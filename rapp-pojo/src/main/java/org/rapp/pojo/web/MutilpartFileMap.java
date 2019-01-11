package org.rapp.pojo.web;

import java.io.Serializable;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件缓存
 * 
 * @author 张芳
 *
 */
public class MutilpartFileMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7638084259987276924L;

	private Map<String, MultipartFile> files;

	public MutilpartFileMap(Map<String, MultipartFile> files) {
		super();
		this.files = files;
	}

	public Map<String, MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(Map<String, MultipartFile> files) {
		this.files = files;
	}

}
