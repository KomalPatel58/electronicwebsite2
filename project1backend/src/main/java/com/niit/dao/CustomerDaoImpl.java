package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.models.Authorities;
import com.niit.models.Customer;

import com.niit.models.User;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	public SessionFactory sessionFactory;
	public void registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		customer.getUser().setEnabled(true);
		Authorities authorities=new Authorities();
		authorities.setRole("ROLE_USER");
		 customer.getUser().setAuthorities(authorities);
		 authorities.setUser(customer.getUser());
		session.save(customer);
		
	}
	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		if(user==null)//email is unique
			return true;
			else
				return false;//email is not unique.
	}
	/*public List<Eclass> getRecords() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Eclass order by date desc");
		// TODO Auto-generated method stub
		query.setMaxResults(2);
		List<Eclass> eclasses= query.list();
		return eclasses;
	}*/
	

}
