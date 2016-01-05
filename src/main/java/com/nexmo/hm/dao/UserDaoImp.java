package com.nexmo.hm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexmo.hm.domain.User;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.DomainTransferObjectUtil;

/**
 * @author Sunny Ghosh
 *
 */

@Repository("userDao")
@Transactional
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImp() {
		super();
	}

	public UserDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserTO getUser(String user) {
		UserTO userVar = null;
		Session session = sessionFactory.getCurrentSession();
		User userDb = (User) session.createCriteria(User.class).add(Restrictions.like("name", user)).uniqueResult();
		if (userDb != null && userDb.getName() != null) {
			userVar = DomainTransferObjectUtil.domainToTransferObjectConverter(userDb);
		}
		return userVar;
	}

	@Override
	public void save(UserTO userModel) {
		sessionFactory.getCurrentSession().persist(DomainTransferObjectUtil.transferObjectToDomainConverter(userModel));
	}

	@Override
	public void update(UserTO userModel) {
		sessionFactory.getCurrentSession().update(DomainTransferObjectUtil.transferObjectToDomainConverter(userModel));
	}

}
