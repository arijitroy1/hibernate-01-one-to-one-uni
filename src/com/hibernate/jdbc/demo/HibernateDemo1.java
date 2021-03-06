package com.hibernate.jdbc.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.model.Instructor;
import com.hibernate.jdbc.model.InstructorDetail;

public class HibernateDemo1 {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
		
			Instructor instructor = new Instructor("Molay", "Dhar", "molay.dhar@gmail.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("youtube.com/mdsir","teaching");
			instructor.setInstructorDetail(instructorDetail);
			
			session.save(instructor);
			
			session.getTransaction().commit();
			System.out.println("Data is saved successfully");
		}catch(Exception e) {
			System.out.print("Error - "+ e.getMessage());
		}finally {
			session.close();//No point of doing this, Session is already dead after "commit". if you use sessionFactory.getCurrentSession(), you'll obtain a "current session" which is bound to the lifecycle of the transaction and will be automatically flushed and closed when the transaction ends (commit or rollback).if you decide to use sessionFactory.openSession(), you'll have to manage the session yourself and to flush and close it "manually".
			factory.close();
		}
	}

}
