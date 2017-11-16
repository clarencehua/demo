package nju.software.demo.service.model;

public class LjModel implements Comparable<LjModel> {

	private Integer ljbh;// 链接编号
	private String ljmc;// 链接名称
	private String ljurl;// 链接url
	private Integer ljlx;// 链接类型
	private Integer xssx;// 显示顺序

	public Integer getLjbh() {
		return ljbh;
	}

	public void setLjbh(Integer ljbh) {
		this.ljbh = ljbh;
	}

	public String getLjmc() {
		return ljmc;
	}

	public void setLjmc(String ljmc) {
		this.ljmc = ljmc;
	}

	public String getLjurl() {
		return ljurl;
	}

	public void setLjurl(String ljurl) {
		this.ljurl = ljurl;
	}

	public Integer getLjlx() {
		return ljlx;
	}

	public void setLjlx(Integer ljlx) {
		this.ljlx = ljlx;
	}

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	@Override
	public int compareTo(LjModel otherLjModel) {
		if (this.xssx > otherLjModel.getXssx()) {
			return 1;
		} else if (this.xssx < otherLjModel.getXssx()) {
			return -1;
		} else {
			return 0;
		}
	}

}
