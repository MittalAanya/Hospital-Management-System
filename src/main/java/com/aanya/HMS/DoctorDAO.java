package com.aanya.HMS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;

public class DoctorDAO {
    private SessionFactory factory;

    public DoctorDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveDoctor(Doctor doctor) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Doctor getDoctor(int id) {
        Session session = factory.openSession();
        Doctor doctor = null;
        try {
            doctor = session.get(Doctor.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctor;
    }

    public List<Doctor> listDoctors() {
        Session session = factory.openSession();
        List<Doctor> doctors = null;
        try {
            doctors = session.createQuery("FROM Doctor", Doctor.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctors;
    }

    public void updateDoctor(Doctor doctor) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(doctor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteDoctor(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}