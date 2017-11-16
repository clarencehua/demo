package nju.software.demo.service.model;

public class LjModel implements Comparable<LjModel> {

	private Integer ljbh;// ���ӱ��
	private String ljmc;// ��������
	private String ljurl;// ����url
	private Integer ljlx;// ��������
	private Integer xssx;// ��ʾ˳��

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
