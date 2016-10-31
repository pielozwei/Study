/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.thinkgem.jeesite.common.persistence.IdEntity;


/**
 * 基本信息Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_GZQJ")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tool extends IdEntity<Tool> {
	
	private static final long serialVersionUID = 1L;
	private ToolCategory gzqjlbId;    //工装器具类别_ID
	private String gzqjbm;    //工装器具编码
	private String gzqjmc;    //工装器具名称
	private String gzqjjc;    //工装器具简称
	private Integer smzqzt;    //生命周期状态
	private Integer glzt;    //管理状态
	private Integer tzlb;    //台账类别
	private String gg;    //规格
	private String xh;    //型号
	private String sccj;    //生产厂家
	private String ccbh;    //出厂编号
	private String gsbm;    //归属部门    
	private String syr;    //使用人
	private Integer jllb;    //计量类别
	private Integer qrjg;    //确认间隔
	private String jddw;    //检定单位
	private Date jdrq;    //鉴定日期
	private String jdjg;    //鉴定结果
	private Date yxrq;    //有效日期
	private String zqd;     //准确度
	private String cld;     //测量点
	private String clfw;     //测量范围
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	private String bz;    //备注
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	public ToolCategory getGzqjlbId() {
		return gzqjlbId;
	}
	public void setGzqjlbId(ToolCategory gzqjlbId) {
		this.gzqjlbId = gzqjlbId;
	}
	public String getGzqjbm() {
		return gzqjbm;
	}
	public void setGzqjbm(String gzqjbm) {
		this.gzqjbm = gzqjbm;
	}
	public String getGzqjmc() {
		return gzqjmc;
	}
	public void setGzqjmc(String gzqjmc) {
		this.gzqjmc = gzqjmc;
	}
	public String getGzqjjc() {
		return gzqjjc;
	}
	public void setGzqjjc(String gzqjjc) {
		this.gzqjjc = gzqjjc;
	}
	public Integer getSmzqzt() {
		return smzqzt;
	}
	public void setSmzqzt(Integer smzqzt) {
		this.smzqzt = smzqzt;
	}
	public Integer getGlzt() {
		return glzt;
	}
	public void setGlzt(Integer glzt) {
		this.glzt = glzt;
	}
	public Integer getTzlb() {
		return tzlb;
	}
	public void setTzlb(Integer tzlb) {
		this.tzlb = tzlb;
	}
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSccj() {
		return sccj;
	}
	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	public String getCcbh() {
		return ccbh;
	}
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	public String getGsbm() {
		return gsbm;
	}
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public Integer getJllb() {
		return jllb;
	}
	public void setJllb(Integer jllb) {
		this.jllb = jllb;
	}
	public Integer getQrjg() {
		return qrjg;
	}
	public void setQrjg(Integer qrjg) {
		this.qrjg = qrjg;
	}
	public String getJddw() {
		return jddw;
	}
	public void setJddw(String jddw) {
		this.jddw = jddw;
	}
	public Date getJdrq() {
		return jdrq;
	}
	public void setJdrq(Date jdrq) {
		this.jdrq = jdrq;
	}
	public String getJdjg() {
		return jdjg;
	}
	public void setJdjg(String jdjg) {
		this.jdjg = jdjg;
	}
	public Date getYxrq() {
		return yxrq;
	}
	public void setYxrq(Date yxrq) {
		this.yxrq = yxrq;
	}
	public String getZqd() {
		return zqd;
	}
	public void setZqd(String zqd) {
		this.zqd = zqd;
	}
	public String getCld() {
		return cld;
	}
	public void setCld(String cld) {
		this.cld = cld;
	}
	public String getClfw() {
		return clfw;
	}
	public void setClfw(String clfw) {
		this.clfw = clfw;
	}
	public Integer getSfqy() {
		return sfqy;
	}
	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}
	public Integer getXssx() {
		return xssx;
	}
	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	/*private ToolCategory toolCategory; 	// 名称
	
	private String bm;//编码--最大长度100 True（不为空）
	private String mc;//名称--最大长度100
	private String jc;//简称--最大长度30
	private String gg;//规格--最大长度30
	private String xh;//型号--最大长度30
	private String jldw;//计量单位--最大长度30
	private String ccbh;//出厂编号--最大长度100 True（不为空）
	private Integer glzt;//管理状态
	private Integer jllb;//计量类别
	private Integer qrjg;//确认间隔
	private String jddw;//检定单位--最大长度100
	private String jdjg;//检定结果--最大长度30
	private String zqd;//准确度--最大长度30
	private String cld;//测量点--最大长度30
	private String clfw;//测量范围--最大长度30
	private Float sbyz;//设备原值
	private Float zjl;//折旧率
	private Integer synx;//使用年限
	private Date lkrq;//立卡日期
	private Date ccrq;//出厂日期
	private Date jcrq;//进厂日期
	private Date azrq;//安装日期
	private Date tcrq;//投产日期
	private Date bfrq;//报废日期
	public Tool() {
		super();
	}
	public Tool(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="IP_GZQJLB_ID")
	@ManyToOne
	@JoinColumn(name="IP_GZQJLB_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="toolCategory.id", align=1, sort=20)
	public ToolCategory getToolCategory() {
		return toolCategory;
	}
	public void setToolCategory(ToolCategory toolCategory) {
		this.toolCategory = toolCategory;
	}
	@NotNull
	@Length(min=1, max=100)
	@ExcelField(title="编码",align=1, sort=25)
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	@Length(min=0, max=100)
	@ExcelField(title="类别名称",align=1, sort=30)
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	@Length(min=0, max=30)
	@ExcelField(title="简称",align=1, sort=35)
	public String getJc() {
		return jc;
	}
	public void setJc(String jc) {
		this.jc = jc;
	}
	@Length(min=0, max=30)
	@ExcelField(title="规格",align=1, sort=40)
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	@Length(min=0, max=30)
	@ExcelField(title="型号",align=1, sort=45)
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	@Length(min=0, max=30)
	@ExcelField(title="计量单位",align=1, sort=50)
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	@NotNull
	@Length(min=1, max=100)
	@ExcelField(title="出厂编号",align=1, sort=55)
	public String getCcbh() {
		return ccbh;
	}
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	@ExcelField(title="管理状态",dictType="d_glzt",align=1, sort=60)
	public Integer getGlzt() {
		return glzt;
	}
	public void setGlzt(Integer glzt) {
		this.glzt = glzt;
	}
	@ExcelField(title="计量类别",dictType="jllb",align=1, sort=65)
	public Integer getJllb() {
		return jllb;
	}
	public void setJllb(Integer jllb) {
		this.jllb = jllb;
	}
	@ExcelField(title="确认间隔",align=1, sort=70)
	public Integer getQrjg() {
		return qrjg;
	}
	public void setQrjg(Integer qrjg) {
		this.qrjg = qrjg;
	}
	@Length(min=0, max=100)
	@ExcelField(title="检定单位",align=1, sort=75)
	public String getJddw() {
		return jddw;
	}
	public void setJddw(String jddw) {
		this.jddw = jddw;
	}
	@Length(min=0, max=30)
	@ExcelField(title="检定结果",dictType="d_dqjdzt",align=1, sort=80)
	public String getJdjg() {
		return jdjg;
	}
	public void setJdjg(String jdjg) {
		this.jdjg = jdjg;
	}
	@Length(min=0, max=30)
	@ExcelField(title="准确度",align=1, sort=85)
	public String getZqd() {
		return zqd;
	}
	public void setZqd(String zqd) {
		this.zqd = zqd;
	}
	@Length(min=0, max=30)
	@ExcelField(title="测量点",align=1, sort=90)
	public String getCld() {
		return cld;
	}
	public void setCld(String cld) {
		this.cld = cld;
	}
	@Length(min=0, max=30)
	@ExcelField(title="测量范围",align=1, sort=95)
	public String getClfw() {
		return clfw;
	}
	public void setClfw(String clfw) {
		this.clfw = clfw;
	}
	@ExcelField(title="设备原值",align=1, sort=100)
	public Float getSbyz() {
		return sbyz;
	}
	public void setSbyz(Float sbyz) {
		this.sbyz = sbyz;
	}
	@ExcelField(title="折旧率",align=1, sort=105)
	public Float getZjl() {
		return zjl;
	}
	public void setZjl(Float zjl) {
		this.zjl = zjl;
	}
	@ExcelField(title="使用年限",align=1, sort=110)
	public Integer getSynx() {
		return synx;
	}
	public void setSynx(Integer synx) {
		this.synx = synx;
	}
	@ExcelField(title="立卡日期",align=1, sort=115)
	public Date getLkrq() {
		return lkrq;
	}
	public void setLkrq(Date lkrq) {
		this.lkrq = lkrq;
	}
	@ExcelField(title="出厂日期",align=1, sort=120)
	public Date getCcrq() {
		return ccrq;
	}
	public void setCcrq(Date ccrq) {
		this.ccrq = ccrq;
	}
	@ExcelField(title="进厂日期",align=1, sort=125)
	public Date getJcrq() {
		return jcrq;
	}
	public void setJcrq(Date jcrq) {
		this.jcrq = jcrq;
	}
	@ExcelField(title="安装日期",align=1, sort=130)
	public Date getAzrq() {
		return azrq;
	}
	public void setAzrq(Date azrq) {
		this.azrq = azrq;
	}
	@ExcelField(title="投产日期",align=1, sort=135)
	public Date getTcrq() {
		return tcrq;
	}
	public void setTcrq(Date tcrq) {
		this.tcrq = tcrq;
	}
	@ExcelField(title="报废日期",align=1, sort=140)
	public Date getBfrq() {
		return bfrq;
	}
	public void setBfrq(Date bfrq) {
		this.bfrq = bfrq;
	}*/
}


