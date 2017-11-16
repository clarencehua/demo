package nju.software.demo.service;

import java.util.List;

import nju.software.demo.data.dataobject.Ljb;
import nju.software.demo.service.model.LjModel;
import nju.software.demo.service.model.base.ResultModel;

public interface LjService {

	/**
	 * �����������͵õ�����
	 */
	public List<Ljb> getLjListByLjlx(String ljlx);

	/**
	 * ��������
	 */
	public ResultModel addLj(String ljmc, String ljurl, String xssx, String ljlx);

	/**
	 * �޸�����
	 */
	public ResultModel modifyLj(String ljmc, String ljurl, String ljlx, String xssx, String ljbh);

	/**
	 * ɾ������
	 */
	public ResultModel deleteLj(String ljbh);

	/**
	 * �������ӱ�ŵõ�����
	 */
	public LjModel getLjByLjbh(String ljbh);
}
