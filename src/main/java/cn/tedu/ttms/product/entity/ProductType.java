package cn.tedu.ttms.product.entity;
import java.io.Serializable;
import java.util.Date;
/**产品分类:属于旅游管理的产品分类实体
 * 例如:
 * 1)出境游
 * 1.1)置业游
 * 1.2)体育游
 * 1.3)亲子游
 * 1.4).........
 * 2)境内游
 * */
public class ProductType 
       implements Serializable{
	private static final long serialVersionUID = 7611388056449233479L;
    /**分类id*/
	private Integer id;
	/**分类名称*/
	private String name;
	/**分类排序编号*/
	private Integer sort;
	/**上一级分类的parentId*/
	private Integer parentId;
	/**分类备注*/
	private String note;
	/**创建时间*/
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", sort=" + sort + ", parentId=" + parentId + ", note="
				+ note + "]";
	}
	
	
}
