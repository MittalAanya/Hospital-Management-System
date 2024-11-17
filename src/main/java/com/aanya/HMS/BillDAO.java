package com.aanya.HMS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;

public class BillDAO {
    private SessionFactory factory;

    public BillDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveBill(Bill bill) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bill);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Bill getBill(int id) {
        Session session = factory.openSession();
        Bill bill = null;
        try {
            bill = session.get(Bill.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bill;
    }

    public List<Bill> listBills() {
        Session session = factory.openSession();
        List<Bill> bills = null;
        try {
            bills = session.createQuery("FROM Bill", Bill.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bills;
    }

    public void updateBill(Bill bill) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bill);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteBill(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Bill bill = session.get(Bill.class, id);
            if (bill != null) {
                session.delete(bill);
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