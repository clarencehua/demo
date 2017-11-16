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
	 * �õ����ӣ�������������
	 */
	public List<Ljb> getLjByLjlx(Integer ljlx) {
		String hql = "from Ljb where ljlx=? order by xssx";
		List<Ljb> ljList = getHibernateTemplate().find(hql, ljlx);
		return ljList;
	}

	/**
	 * ��������
	 */
	public Integer save(Ljb ljb) {
		Integer ljbh = (Integer) getHibernateTemplate().save(ljb);
		return ljbh;
	}

	/**
	 * ��������
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
	 * ɾ������
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
	 * �������ӱ�ţ��õ�����
	 */
	public Ljb getLjbByLjbh(Integer ljbh) {
		return getHibernateTemplate().get(Ljb.class, ljbh);
	}

	/**
	 * �����������͵õ���Ӧ������
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
