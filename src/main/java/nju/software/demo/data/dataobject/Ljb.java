package nju.software.demo.data.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * �������ӱ�
 *
 * ��Ҫ���ܣ�ҳ����һЩ�����ӵĵط���
 * 
 * 1.�����������ӣ������ƣ� 2.����������һЩ�������ӣ��磺��Ժ���ݱ��ݵȣ����������10���� 3.�����շ�Ժվ��Ⱥ�����������14����
 * �ڲ������ݿ��ʱ����Ҫ���Ƹ�����
 */
@Entity
@Table(name = "s_ljb")
public class Ljb implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ljbh;// ���ӱ��
	private String ljmc;// ��������
	private String ljurl;// ����url
	private Integer ljlx;// ��������
	private Integer xssx;// ��ʾ˳��

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
