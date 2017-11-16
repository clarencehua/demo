package nju.software.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import nju.software.demo.data.dao.LjDao;
import nju.software.demo.data.dataobject.Ljb;
import nju.software.demo.service.LjService;
import nju.software.demo.service.model.LjModel;
import nju.software.demo.service.model.base.ResultModel;
import nju.software.demo.util.NumberUtil;
import nju.software.demo.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LjServiceImpl implements LjService {

	@Autowired
	private LjDao ljDao;

	@Override
	public List<Ljb> getLjListByLjlx(String ljlx)
	{	List<Ljb> ljList=ljDao.getLjByLjlx(6);
		return ljList;
	}

	@Override
	public ResultModel addLj(String ljmc, String ljurl, String xssx, String ljlx)
	{
		ResultModel rm = new ResultModel();
		if(!NumberUtil.isNumber(ljlx)) {
			rm.setResult(false);
			rm.setMessage("增加失败");
			return rm;
		}
		if(!NumberUtil.isNumber(xssx)) {
			if(StringUtil.isEmpty(xssx)) {
				xssx = "1";
			}
			else {
				rm.setResult(false);
				rm.setMessage("显示顺序必须为数字");
				return rm;
			}
		}
		Integer ljlxInt = Integer.parseInt(ljlx);
		long count = ljDao.getCountByLjlx(ljlxInt);
		if(ljlxInt == 2) {
			// 内网常用链接,不超过10个
			if(count >= 10) {
				rm.setResult(false);
				rm.setMessage("该类型链接不能超过10个");
				return rm;
			}
		}
		else if(ljlxInt == 3) {
			// 站点群,不超过10个
			if(count >= 15) {
				rm.setResult(false);
				rm.setMessage("该类型链接不能超过14个");
				return rm;
			}
		}
		else if(ljlxInt == 1) {
			// 友情链接,不超过25个
			if(count >= 25) {
				rm.setResult(false);
				rm.setMessage("该类型链接不能超过25个");
				return rm;
			}
		}
		else if(ljlxInt == 4) {
			// 图片链接,不超过15个
			if(count >= 15) {
				rm.setResult(false);
				rm.setMessage("该类型链接不能超过15个");
				return rm;
			}
		}
		else if(ljlxInt == 5) {
			// 导航链接,不超过3个
			if(count >= 3) {
				rm.setResult(false);
				rm.setMessage("该类型链接不能超过3个");
				return rm;
			}
		}
		Ljb lj = new Ljb();
		lj.setLjlx(Integer.parseInt(ljlx));
		lj.setLjurl(ljurl);
		lj.setLjmc(ljmc);
		lj.setXssx(Integer.parseInt(xssx));
		Integer ljbh = ljDao.save(lj);
		if(ljbh > 0) {
			rm.setResult(true);
			rm.setMessage("增加成功");
		}
		else {
			rm.setResult(false);
			rm.setMessage("增加失败");
		}
		return rm;
	}

	@Override
	public ResultModel modifyLj(String ljmc, String ljurl, String ljlx, String xssx, String ljbh)
	{
		ResultModel rm = new ResultModel();
		if(!NumberUtil.isNumber(ljlx) || !NumberUtil.isNumber(ljbh)) {
			rm.setResult(false);
			rm.setMessage("修改失败");
			return rm;
		}
		if(!NumberUtil.isNumber(xssx)) {
			if(StringUtil.isEmpty(xssx)) {
				xssx = "1";
			}
			else {
				rm.setResult(false);
				rm.setMessage("显示顺序必须为数字");
				return rm;
			}
		}
		Ljb lj = ljDao.getLjbByLjbh(Integer.parseInt(ljbh));
		if(lj == null) {
			rm.setResult(false);
			rm.setMessage("不存在该链接");
			return rm;
		}
		lj.setLjlx(Integer.parseInt(ljlx));
		lj.setLjurl(ljurl);
		lj.setLjmc(ljmc);
		lj.setXssx(Integer.parseInt(xssx));
		boolean result = ljDao.update(lj);
		if(result) {
			rm.setResult(true);
			rm.setMessage("修改成功");
		}
		else {
			rm.setResult(false);
			rm.setMessage("修改失败");
		}
		return rm;
	}

	@Override
	public ResultModel deleteLj(String ljbh)
	{
		ResultModel rm = new ResultModel();
		if(!NumberUtil.isNumber(ljbh)) {
			rm.setResult(false);
			rm.setMessage("删除失败");
			return rm;
		}
		Ljb lj = ljDao.getLjbByLjbh(Integer.parseInt(ljbh));
		if(lj == null) {
			rm.setResult(false);
			rm.setMessage("不存在该链接");
			return rm;
		}
		boolean result = ljDao.delete(lj);
		if(result) {
			rm.setResult(true);
			rm.setMessage("删除成功");
		}
		else {
			rm.setResult(false);
			rm.setMessage("删除失败");
		}
		return rm;
	}

	@Override
	public LjModel getLjByLjbh(String ljbh)
	{
		return null;
	}

}
