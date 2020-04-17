/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javaguides.hibernate.util;

import java.util.List;
import net.javaguides.hibernate.entity.Konyv;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author Robert
 */
public class JpaKonyvDAO implements AutoCloseable{
    Session session;
    Transaction transaction;

    public JpaKonyvDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     *
     */
    @Override
    public void close(){
        session.close();
        System.out.println("DAO closed...");
    }

    public void saveKonyv(Konyv a) {
        try {
            transaction = session.beginTransaction();
            session.save(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteKonyv(Konyv a) {
        try {
            transaction = session.beginTransaction();
            session.remove(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void updateKonyv(Konyv a) {
        try {
            transaction = session.beginTransaction();
            session.update(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<Konyv> getKonyvs(){
        String hql = "FROM net.javaguides.hibernate.entity.Konyv";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
