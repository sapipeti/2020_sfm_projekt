/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javaguides.hibernate.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.javaguides.hibernate.entity.Konyv;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
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
        }
    }
    
    public List<Konyv> getKonyvs(){
        String hql = "FROM net.javaguides.hibernate.entity.Konyv";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<String> queryKonyvFejlec(String lekerdezes){
        List<String> lista = new ArrayList<String>();
        try{
            transaction = session.beginTransaction();
            Query query1 = session.createSQLQuery(lekerdezes);
            query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String,Object>> aliasToValueMapList=query1.list();
            for (Map<String, Object> map : aliasToValueMapList) {
               for (Map.Entry<String, Object> entry : map.entrySet()) {
                   lista.add(entry.getKey().toString());
               }
               break;
            }
       
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace(); 
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return lista;
    }
    
    
    public List<Object[]> queryKonyv(String lekerdezes){
        List<Object[]> queryResult = new ArrayList<Object[]>();
        try{
            transaction = session.beginTransaction();
            Query query1 = session.createSQLQuery(lekerdezes);
            queryResult = (List<Object[]>)query1.list();
            
            //SZAROK AZ OSZLOPNEVEK
            /*System.out.println("--------------------");
            List<String> lista = new ArrayList<String>();
            query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String,Object>> aliasToValueMapList=query1.list();
            for (Map<String, Object> map : aliasToValueMapList) {
               for (Map.Entry<String, Object> entry : map.entrySet()) {
                   lista.add(entry.getKey().toString());
               }
               break;
            }*/

            
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace(); 
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return queryResult;
    }
    
    public List<Integer> getBiggestID(){
        List<Integer> ID = new ArrayList<Integer>();
        try{
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT max(ID) FROM KONYV");
            ID = query.list();
            transaction.commit();
        }
        catch(Exception e){
            e.printStackTrace(); 
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return ID;
    }
}
