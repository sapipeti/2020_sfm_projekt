package net.javaguides.hibernate;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javaguides.hibernate.entity.Konyv;
import net.javaguides.hibernate.util.JpaKonyvDAO;
import net.javaguides.hibernate.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

       
 /* DAO project */
        List<String> els = new ArrayList<String>();

        Konyv el = new Konyv("én","az életem",2018,"best","siker","legjobb,legeslegjobb","magyar",31,true,75,2010,true);
        Konyv ma = new Konyv("sápu","értelmetlen",2010,"worst","bukás","senki,kaka","román",1111,false,187,1990,false); 
        try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {

            aDAO.saveKonyv(el);
            aDAO.saveKonyv(ma);
            List<Konyv> konyvList = aDAO.getKonyvs();
            konyvList.forEach(a -> System.out.println(a));
          
        }

   
 
    }
}
