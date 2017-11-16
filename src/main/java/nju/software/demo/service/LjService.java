package nju.software.demo.service;

import java.util.List;

import nju.software.demo.data.dataobject.Ljb;
import nju.software.demo.service.model.LjModel;
import nju.software.demo.service.model.base.ResultModel;

public interface LjService {

	/**
	 * 根据链接类型得到链接
	 */
	public List<Ljb> getLjListByLjlx(String ljlx);

	/**
	 * 增加链接
	 */
	public ResultModel addLj(String ljmc, String ljurl, String xssx, String ljlx);

	/**
	 * 修改链接
	 */
	public ResultModel modifyLj(String ljmc, String ljurl, String ljlx, String xssx, String ljbh);

	/**
	 * 删除链接
	 */
	public ResultModel deleteLj(String ljbh);

	/**
	 * 根据链接编号得到链接
	 */
	public LjModel getLjByLjbh(String ljbh);
}
