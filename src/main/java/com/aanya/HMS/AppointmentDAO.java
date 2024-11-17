package com.aanya.HMS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;

public class AppointmentDAO {
    private SessionFactory factory;

    public AppointmentDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveAppointment(Appointment appointment) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(appointment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Appointment getAppointment(int id) {
        Session session = factory.openSession();
        Appointment appointment = null;
        try {
            appointment = session.get(Appointment.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return appointment;
    }

    public List<Appointment> listAppointments() {
        Session session = factory.openSession();
        List<Appointment> appointments = null;
        try {
            appointments = session.createQuery("FROM Appointment", Appointment.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return appointments;
    }

    public void updateAppointment(Appointment appointment) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(appointment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAppointment(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, id);
            if (appointment != null) {
                session.delete(appointment);
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
