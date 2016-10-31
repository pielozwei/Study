/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;


/**
 * 个人信息Entity
 * @author cml
 * @version 2016-06-22
 */
@Entity
@Table(name = "ip_zggrxx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee extends IdEntity<Employee> {

	
	private static final long serialVersionUID = 1L;
	public String gh; // 工号
	public String xm; // 姓名
	public Integer xb; // 性别
	public Integer mzdm; // 民族代码
	public Integer csn; // 出生年
	public Integer csy; // 出生月
	public Integer gjdm; // 国籍代码
	public Integer zjlxdm; // 证件类型代码
	public String zjh; // 证件号
	public Integer zyxkdm; // 专业学科代码
	public Integer xldm; // 学历代码
	public Integer xwdm; // 学位代码
	public Integer dqjszcdm; // 当前技术职称代码
	public Integer dqzzmmdm; // 当前政治面貌代码
	public Integer hkxzdm; // 户口性质代码
	public String hkszd; // 户口所在地
	public String zpwj; // 照片文件
	public Integer jgdm; // 籍贯代码
	public String csd; // 出生地
	public String grjj; // 个人简介
	public Date rzsj; // 入职时间
	public Date cjgzsj; // 参加工作时间
	public Date jrzyxtsj; // 进入专业系统时间
	public Integer jrzyxtnd; // 进入专业系统年度
	public Integer syztdm; // 使用状态代码
	public Integer xssx; // 显示顺序
	public Integer sfdm;  //身份代码
	public String nc; //昵称
	public Integer xtsfdm; // 系统身份代码

	private EmployeeContact employeecontact;
	
	@OneToOne
	@JoinColumn(name = "ID")
	public EmployeeContact getEmployeecontact() {
		return employeecontact;
	}

	public void setEmployeecontact(EmployeeContact employeecontact) {
		this.employeecontact = employeecontact;
	}

	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}


	public Integer getXtsfdm() {
		return xtsfdm;
	}


	public void setXtsfdm(Integer xtsfdm) {
		this.xtsfdm = xtsfdm;
	}


	public Employee() {
		super();
	}

	
	public Employee(String id){
		this();
		this.id = id;
	}


	@Length(min=1, max=200)
	public String getGh() {
		return gh;
	}


	public void setGh(String gh) {
		this.gh = gh;
	}


	public String getXm() {
		return xm;
	}


	public void setXm(String xm) {
		this.xm = xm;
	}


	public Integer getXb() {
		return xb;
	}


	public void setXb(Integer xb) {
		this.xb = xb;
	}


	public Integer getMzdm() {
		return mzdm;
	}


	public void setMzdm(Integer mzdm) {
		this.mzdm = mzdm;
	}


	public Integer getCsn() {
		return csn;
	}


	public void setCsn(Integer csn) {
		this.csn = csn;
	}


	public Integer getCsy() {
		return csy;
	}


	public void setCsy(Integer csy) {
		this.csy = csy;
	}


	public Integer getGjdm() {
		return gjdm;
	}


	public void setGjdm(Integer gjdm) {
		this.gjdm = gjdm;
	}


	public Integer getZjlxdm() {
		return zjlxdm;
	}


	public void setZjlxdm(Integer zjlxdm) {
		this.zjlxdm = zjlxdm;
	}


	public String getZjh() {
		return zjh;
	}


	public void setZjh(String zjh) {
		this.zjh = zjh;
	}


	public Integer getZyxkdm() {
		return zyxkdm;
	}


	public void setZyxkdm(Integer zyxkdm) {
		this.zyxkdm = zyxkdm;
	}


	public Integer getXldm() {
		return xldm;
	}


	public void setXldm(Integer xldm) {
		this.xldm = xldm;
	}


	public Integer getXwdm() {
		return xwdm;
	}


	public void setXwdm(Integer xwdm) {
		this.xwdm = xwdm;
	}


	public Integer getDqjszcdm() {
		return dqjszcdm;
	}


	public void setDqjszcdm(Integer dqjszcdm) {
		this.dqjszcdm = dqjszcdm;
	}


	public Integer getDqzzmmdm() {
		return dqzzmmdm;
	}


	public void setDqzzmmdm(Integer dqzzmmdm) {
		this.dqzzmmdm = dqzzmmdm;
	}


	public Integer getHkxzdm() {
		return hkxzdm;
	}


	public void setHkxzdm(Integer hkxzdm) {
		this.hkxzdm = hkxzdm;
	}


	public String getHkszd() {
		return hkszd;
	}


	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}


	public String getZpwj() {
		return zpwj;
	}


	public void setZpwj(String zpwj) {
		this.zpwj = zpwj;
	}


	public Integer getJgdm() {
		return jgdm;
	}


	public void setJgdm(Integer jgdm) {
		this.jgdm = jgdm;
	}


	public String getCsd() {
		return csd;
	}


	public void setCsd(String csd) {
		this.csd = csd;
	}


	public String getGrjj() {
		return grjj;
	}


	public void setGrjj(String grjj) {
		this.grjj = grjj;
	}


	public Date getRzsj() {
		return rzsj;
	}


	public void setRzsj(Date rzsj) {
		this.rzsj = rzsj;
	}

	@Temporal(TemporalType.DATE)
	public Date getCjgzsj() {
		return cjgzsj;
	}


	public void setCjgzsj(Date cjgzsj) {
		this.cjgzsj = cjgzsj;
	}

	@Temporal(TemporalType.DATE)
	public Date getJrzyxtsj() {
		return jrzyxtsj;
	}


	public void setJrzyxtsj(Date jrzyxtsj) {
		this.jrzyxtsj = jrzyxtsj;
	}


	public Integer getJrzyxtnd() {
		return jrzyxtnd;
	}


	public void setJrzyxtnd(Integer jrzyxtnd) {
		this.jrzyxtnd = jrzyxtnd;
	}


	public Integer getSyztdm() {
		return syztdm;
	}


	public void setSyztdm(Integer syztdm) {
		this.syztdm = syztdm;
	}


	public Integer getXssx() {
		return xssx;
	}


	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	public Integer getSfdm() {
		return sfdm;
	}


	public void setSfdm(Integer sfdm) {
		this.sfdm = sfdm;
	}

	
}


