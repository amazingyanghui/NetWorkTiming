package com.temptation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.temptation.pojo.FileUpload;
import com.temptation.service.FileService;

@Controller
@RequestMapping(value="/file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/getFile")
	@ResponseBody
	public Object getFile(HttpServletRequest request) {
		Integer fileType=Integer.parseInt(request.getParameter("fileType"));
		Map<String, Object> filesMap=new HashMap<String, Object>();
		List<FileUpload> files=fileService.queryFiles(fileType);
		filesMap.put("data", files);
		filesMap.put("msg", "");
		filesMap.put("count", files.size());
		filesMap.put("code", 0);
		return filesMap;
	}

    @RequestMapping("/queryFiles")
    @ResponseBody
    public Object queryFiles() {
        return fileService.queryFiles(1);
    }
	
    /**
     * 采用file.Transto 来保存上传的文件
     * <请替换成功能描述> <br>
     * <请替换成详细描述>
     * @param file
     * @return
     * @throws IOException
     * 
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Object fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request)throws IOException {
    	Map<String, Object> fileMap=new HashMap<String, Object>();
    	long startTime = System.currentTimeMillis();
        System.out.println("fileName:"+file.getOriginalFilename());
        String path = "D:/tomcat/apache-tomcat-8.5.38/webapps/"+file.getOriginalFilename();
        FileUpload fileUpload=new FileUpload();
        fileUpload.setFileurl(path);
        fileUpload.setFilename(request.getParameter("filename"));
       // fileUpload.setUsername((String)request.getSession().getAttribute("userName"));
        fileUpload.setUsername("admin");
        File newFile = new File(path);
        //通过CommonsMultipartFile 的方法直接写文件
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
       if(fileService.addFile(fileUpload)) {
    	   fileMap.put("code", 0);
           fileMap.put("msg", "上传成功！"); 
       } else {
    	   fileMap.put("code", 1);
       }
        
        return fileMap;       
    }
    
    
    @RequestMapping("/uploadgb")
    @ResponseBody
    public Object uploadgb(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request)throws IOException {
    	Map<String, Object> fileMap=new HashMap<String, Object>();
    	long startTime = System.currentTimeMillis();
        System.out.println("fileName:"+file.getOriginalFilename());
        String path = "D:/tomcat/apache-tomcat-8.5.38/webapps/"+file.getOriginalFilename();
        FileUpload fileUpload=new FileUpload();
        fileUpload.setFileurl(path);
        fileUpload.setFilename(request.getParameter("filename"));
        //2表示广播音频
        fileUpload.setFiletype(2);
       // fileUpload.setUsername((String)request.getSession().getAttribute("userName"));
        fileUpload.setUsername("admin");
        File newFile = new File(path);
        //通过CommonsMultipartFile 的方法直接写文件
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
       if(fileService.addFile(fileUpload)) {
    	   fileMap.put("code", 0);
           fileMap.put("msg", "上传成功！"); 
       } else {
    	   fileMap.put("code", 1);
       }
        
        return fileMap;       
    }
    
    
    /**
	 * 删除区域
	 */
	@RequestMapping("/delFileById")
	@ResponseBody
	public Object delFileById(String[] ids) {
		try {
			for(int i=0;i<ids.length;i++){
                fileService.delFile(Integer.valueOf(ids[i]));
            }
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
