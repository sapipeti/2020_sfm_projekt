package net.javaguides.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javaguides.hibernate.entity.Konyv;
import net.javaguides.hibernate.entity.Kulcsszo;
import net.javaguides.hibernate.util.JpaKonyvDAO;
import net.javaguides.hibernate.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

       
 /* DAO project */
        LocalDate date1 = LocalDate.of(2010,10,10);
        LocalDate date2 = LocalDate.of(1990,1,1);
        Kulcsszo kulcs1 = new Kulcsszo("horror");
        Kulcsszo kulcs2 = new Kulcsszo("szerelem");
        Kulcsszo kulcs3 = new Kulcsszo("szer");
        Kulcsszo kulcs4 = new Kulcsszo("szm");
        
        Konyv el = new Konyv("én","az életem",2018,"best","siker","magyar",31,true,75,date1,true);
        el.addKulcsszo(kulcs1);
        el.addKulcsszo(kulcs3);
        Konyv ma = new Konyv("sápu","értelmetlen",2010,"worst","bukás","román",1111,false,187,date2,false); 
        ma.addKulcsszo(kulcs2);
        ma.addKulcsszo(kulcs4);
        try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {

            aDAO.saveKonyv(el);
            aDAO.saveKonyv(ma);
            List<Konyv> konyvList = aDAO.getKonyvs();
            konyvList.forEach(a -> System.out.println(a));
          
        }

   
 
    }
}
