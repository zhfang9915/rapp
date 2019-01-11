package org.rapp.pojo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * rapp环境变量
 * 
 * @author 张芳
 *
 */
@Component
@PropertySource("classpath:rapp.properties")
public class Rapp {

	@Value("${rapp.root_path}")
	private String rootPath;

	@Value("${rapp.nginx}")
	private String nginx;

	@Value("${rapp.upload_path}")
	private String uploadPath;

	@Value("${common.taobao}")
	private String taobao;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getNginx() {
		return nginx;
	}

	public void setNginx(String nginx) {
		this.nginx = nginx;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getTaobao() {
		return taobao;
	}

	public void setTaobao(String taobao) {
		this.taobao = taobao;
	}

	@Override
	public String toString() {
		return "Rapp [rootPath=" + rootPath + ", nginx=" + nginx + ", uploadPath=" + uploadPath + ", taobao=" + taobao
				+ "]";
	}

}
