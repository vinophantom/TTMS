package cn.tedu.ttms.attachment.controller;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/attachment/")
public class AttachmentController {
	@Resource
	private AttachmentService attachmentService;
    @RequestMapping("attachmentUI")
	public String attachmentUI(){
		return "attachment/attachment";
	}
    /**
     * @param title 为附件标题
     * @param mFile 用于接收上传的附件的对象
     * */
    @RequestMapping("doUpload")
    @ResponseBody
    public JsonResult doUpload(
    		String title,
    		MultipartFile mFile){
    	//原有内容是练习上传,业务要写到service
    	attachmentService
    	.uploadObject(title,mFile);
    	return new JsonResult();
    }
    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,HttpServletResponse response)
    		throws IOException{
    	//1.根据id执行查找操作
    	Attachment a=attachmentService.findObjectById(id);
    	//2.设置下载内容类型以及响应头(固定格式)
    	response.setContentType("appliction/octet-stream");
    	String fileName=URLEncoder.encode(a.getFileName(),"utf-8");
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName);
		//3.获得指定文件的路径对象(java.nio.Path)
        Path path=Paths.get(a.getFilePath());
        //4.读取path路径对应的文件,并返回字节数组
    	return Files.readAllBytes(path);
    }
    
    /**获得所有的附件信息*/
    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
    	List<Attachment> list=
    	attachmentService.findObjects();
    	return new JsonResult(list);
    } 
}
