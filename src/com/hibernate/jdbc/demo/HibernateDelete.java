package com.hibernate.jdbc.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Instructor;
import com.hibernate.jdbc.model.InstructorDetail;

public class HibernateDelete {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			Instructor instructor = session.get(Instructor.class, "1");
			if(instructor!=null) {
				session.delete(instructor);//Will delete from instructor and instructor-detail table
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("Error = "+ e.getMessage());
		}finally {
			factory.close();
		}

	}

}
