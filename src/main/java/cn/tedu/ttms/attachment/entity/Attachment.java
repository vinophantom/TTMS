package cn.tedu.ttms.attachment.entity;

import java.io.Serializable;
import java.util.Date;
/**附件实体对象*/
public class Attachment implements Serializable{
	private static final long serialVersionUID = -2066834040491586051L;
    private Integer id;
    /**附件标题*/
    private String title;
    /**文件名称(实际的文件名)*/
    private String fileName;
    /**文件内容类型*/
    private String contentType;
    /**文件路径(实际存储的路径)*/
    private String filePath;
    /**文件摘要信息(一般为MD5对内容加密以后的一个结果)*/
    private String fileDisgest;
    /**归属类型(例如附件属于哪个对象的附件,是产品的还是分销商)*/
    private Integer  athType;
    /**具体归属对象(例如具体归属哪个产品)*/
    private Integer belongId;
    
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileDisgest() {
		return fileDisgest;
	}
	public void setFileDisgest(String fileDisgest) {
		this.fileDisgest = fileDisgest;
	}
	public Integer getAthType() {
		return athType;
	}
	public void setAthType(Integer athType) {
		this.athType = athType;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
}
