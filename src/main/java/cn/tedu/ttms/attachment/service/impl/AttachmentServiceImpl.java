package cn.tedu.ttms.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachment.dao.AttachmentDao;
import cn.tedu.ttms.attachment.entity.Attachment;
import cn.tedu.ttms.attachment.service.AttachmentService;
import cn.tedu.ttms.common.exception.ServiceException;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Resource
	private AttachmentDao attachementDao;
    public Attachment findObjectById(Integer id) {
    	//1.判定参数有效性
    	if(id==null)
    	throw new ServiceException("id的值不能为空");
    	//2.执行查询操作
    	Attachment a=attachementDao.findObjectById(id);
    	//3.对查询结果进行业务判定
    	if(a==null)
    	throw new ServiceException("没找到对应记录");
    	return a;
    }
    /**获得所有附件信息*/
    public List<Attachment> findObjects() {
    	return attachementDao.findObjects();
    }
    
	public void uploadObject(String title,
			MultipartFile mFile) {
		System.out.println("title.isEmpty()="+title.isEmpty());
		//1.实现文件上传
		//1.1验证参数有效性
		if(title==null||title.trim().length()==0)
		throw new ServiceException("上传标题不能为空");
		if(mFile==null)
		throw new ServiceException("需要选择上传文件");
		if(mFile.isEmpty())
		throw new ServiceException("上传文件不能为空");
		//1.2判定文件是否已经上传过(根据摘要信息)?
		
		 //获得文件中的字节(应在文件上传之前)
		 String fileDisgest=null;
		 byte buf[]=null;
		 try{
		 buf=mFile.getBytes();
		 fileDisgest=
		 //对文件内容进行md5加密并转换为16进制显示
		 DigestUtils.md5DigestAsHex(buf);
		 //a)对文件内容进行md5加密以后形成字符串称之为文件的摘要信息
		 //b)文件内容一样,构建的摘要字符串也是一样的
		 //c)文件内容不同,摘要字符串也是不同的.
		 System.out.println("fileDisgest="+fileDisgest);
		 }catch(Exception e){
		 e.printStackTrace();
		 throw new ServiceException("文件摘要创建失败");
		 }
		 //根据摘要字符串查询并统计记录
		 int count=
		 attachementDao.getRowCountByDigest(
				 fileDisgest);
		 if(count>0)//假如统计结果大于0说明文件已经上传过了
		 throw new ServiceException("文件已上传,不能再次上传");
		//1.3实现文件上传
		//1.3.1 构建文件上传路径(d:/uploads/2017/08/15/xxxxx.png)
		SimpleDateFormat sdf=
		new SimpleDateFormat("yyyy/MM/dd");
		String dateDir=sdf.format(new Date());
		String baseDir="d:/uploads/";
		File uploadDir=new File(baseDir+dateDir);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		//1.3.2 构建新的文件名(相同目录下不允许出现重复的名字)
		String srcFileName=
		mFile.getOriginalFilename();
		String destfileName=
		UUID.randomUUID().toString()+"."
		+FilenameUtils.getExtension(srcFileName);
		//1.3.3创建目标文件对象
		File dest=new File(uploadDir,
				destfileName);
		//1.3.4 实现文件上传
	    try{
	    //实现文件上传(本质上就是文件的复制)
		mFile.transferTo(dest);
	    }catch(IOException e){
	    e.printStackTrace();
	    throw new ServiceException("文件上传失败");
	    }
		//2.将文件相关信息写入数据库
	    Attachment a=new Attachment();
	    a.setTitle(title);
	    a.setFileName(mFile.getOriginalFilename());
	    a.setContentType(mFile.getContentType());
	    a.setFilePath(dest.getAbsolutePath());
	    a.setFileDisgest(fileDisgest);
	    a.setAthType(1);//暂且没用到
	    a.setBelongId(1);//暂且没用到	    
	    int rows=attachementDao.insertObject(a);
	    if(rows==-1)
	    throw new ServiceException("insert error");
	}
	
	

}
