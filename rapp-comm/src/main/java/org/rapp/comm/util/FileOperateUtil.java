package org.rapp.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.rapp.pojo.web.Rapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileOperateUtil {
	
	@Autowired
	Rapp rapp;
	
	/**
	 * 文件存放目录
	 */
//    public static String FILEDIR = "/usr/local/nginx/html/";
//    public static String FILEDIR = "D://nginx-1.11.10/html/";
    
    public boolean uploadByName(HttpServletRequest request, String root, String fileName){
    	boolean flag = false;
    	try {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();       
            File file = new File(rapp.getUploadPath());
            if (!file.exists()) {
                file.mkdir();
            }
            Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, MultipartFile> entry = it.next();
                MultipartFile mFile = entry.getValue();
                if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
                    write(mFile.getInputStream(), new FileOutputStream(initFilePath(fileName, null, root)));
                }
                break;
            }
            flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
    	return flag;
    }
    
    /**
     * 上传
     * @param request
     * @throws IOException
     */
    public boolean upload(HttpServletRequest request, String seq, String root){
    	boolean flag = false;
    	try {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();       
            File file = new File(rapp.getUploadPath());
            if (!file.exists()) {
                file.mkdir();
            }
            Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, MultipartFile> entry = it.next();
                MultipartFile mFile = entry.getValue();
                if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
                    write(mFile.getInputStream(), new FileOutputStream(initFilePath(mFile.getOriginalFilename(), seq, root)));
                }
            }
            flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
    	return flag;
    }
    
    /**
     * 获取文件前缀
     * @return
     */
	public static String getSeq() {
		Long num = new Date().getTime();
		Double d = Math.random() * num;
		return "" + num + d.longValue();
	}
    
    /**
     * 计算文件存放具体路径
     * @param name
     * @return
     */
    public String initFilePath(String name, String seq, String root) {
        File file = new File(rapp.getUploadPath() + root + "/");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (seq == null) {
        	return (file.getPath() + "/" + name).replaceAll(" ", "-");
		}
        return (file.getPath() + "/" + seq + "_" + name).replaceAll(" ", "-");
    }
    
    /**
     * 计算生成文件子目录
     * @param name
     * @return
     */
//    public static int getFileDir(String name) {
//        return name.hashCode() & 0xf;
//    }
    
    /**
     * 下载
     * @param downloadfFilePath
     * @param out
     */
    public static void download(String downloadfFilePath, ServletOutputStream out) {
        try {
            FileInputStream in = new FileInputStream(new File(downloadfFilePath));
            write(in, out);
        } catch (FileNotFoundException e) {
            try {
                FileInputStream in = new FileInputStream(new File(new String(downloadfFilePath.getBytes("iso-8859-1"),"utf-8")));
                write(in, out);
            } catch (IOException e1) {              
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    /**
     * 写入数据
     * @param in
     * @param out
     * @throws IOException
     */
    public static void write(InputStream in, OutputStream out) throws IOException{
        try{
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    }
    
    /**
     * 复制文件到指定目录
     * @param src
     * @param target
     */
    public static void copyFile2Dir (String src, String target) {
    	FileInputStream srcIn = null;
    	FileOutputStream targerOut = null;
    	try {
    		File srcFile = new File(src);
    		if (!srcFile.exists()) {
				return;
			}
    		File targerFile = new File(target);
    		if (targerFile.exists()) {
				targerFile.mkdirs();
			}
			srcIn = new FileInputStream(srcFile);
			targerOut = new FileOutputStream(new File(target+srcFile.getName()));
			write(srcIn, targerOut);
		} catch (IOException e) {
			if (srcIn != null) {
				try {
					srcIn.close();
				} catch (IOException e1) {
				}
			}
			if (targerOut != null) {
				try {
					targerOut.close();
				} catch (IOException e1) {
				}
			}
		}
    	
    }
    
}
