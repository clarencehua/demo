package nju.software.demo.data.dao;

import java.util.List;

import nju.software.demo.data.dataobject.Ljb;
import nju.software.demo.util.CollectionUtil;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings("unchecked")
public class LjDao extends HibernateDaoSupport {

	/**
	 * 得到链接，根据链接类型
	 */
	public List<Ljb> getLjByLjlx(Integer ljlx) {
		String hql = "from Ljb where ljlx=? order by xssx";
		List<Ljb> ljList = getHibernateTemplate().find(hql, ljlx);
		return ljList;
	}

	/**
	 * 保存链接
	 */
	public Integer save(Ljb ljb) {
		Integer ljbh = (Integer) getHibernateTemplate().save(ljb);
		return ljbh;
	}

	/**
	 * 更新链接
	 */
	public boolean update(Ljb ljb) {
		try {
			getHibernateTemplate().update(ljb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除链接
	 */
	public boolean delete(Ljb ljb) {
		try {
			getHibernateTemplate().delete(ljb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据链接编号，得到链接
	 */
	public Ljb getLjbByLjbh(Integer ljbh) {
		return getHibernateTemplate().get(Ljb.class, ljbh);
	}

	/**
	 * 根据链接类型得到相应的数量
	 */
	public Long getCountByLjlx(Integer ljlx) {
		String hql = "select count(*) from Ljb where ljlx=?";
		List<Long> count = getHibernateTemplate().find(hql, ljlx);
		if (!CollectionUtil.listIsNull(count)) {
			return count.get(0);
		} else {
			return (long) 0;
		}
	}

}
