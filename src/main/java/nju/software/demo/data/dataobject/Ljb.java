package nju.software.demo.data.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 超级链接表
 *
 * 主要功能：页面中一些超链接的地方。
 * 
 * 1.代表友情链接，无限制； 2.代表内网中一些常用链接，如：本院数据备份等，该连接最多10个； 3.代表江苏法院站点群，该连接最多14个。
 * 在操作数据库的时候，需要限制个数。
 */
@Entity
@Table(name = "s_ljb")
public class Ljb implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ljbh;// 链接编号
	private String ljmc;// 链接名称
	private String ljurl;// 链接url
	private Integer ljlx;// 链接类型
	private Integer xssx;// 显示顺序

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ljbh", nullable = false, unique = true)
	public Integer getLjbh() {
		return ljbh;
	}

	public void setLjbh(Integer ljbh) {
		this.ljbh = ljbh;
	}

	@Column(name = "ljmc")
	public String getLjmc() {
		return ljmc;
	}

	public void setLjmc(String ljmc) {
		this.ljmc = ljmc;
	}

	@Column(name = "ljurl")
	public String getLjurl() {
		return ljurl;
	}

	public void setLjurl(String ljurl) {
		this.ljurl = ljurl;
	}

	@Column(name = "ljlx")
	public Integer getLjlx() {
		return ljlx;
	}

	public void setLjlx(Integer ljlx) {
		this.ljlx = ljlx;
	}

	@Column(name = "xssx")
	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

}
