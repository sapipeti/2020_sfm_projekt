/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javaguides.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author Robert
 */
@Entity

public class Konyv implements Serializable {
   @Id
   @Column(unique = true)
   private int ID;
   @Column(unique = false)
   private String szerzo;
   @Column(unique = false)
   private String cím;
   @Column(unique = false)
   private int kiadás_év;
   @Column(unique = false)
   private String kiadó;
   @Column(unique = false)
   private String műfaj;
   @Column(unique = false)
   
   private String Nyelv;
   @Column(unique = false)
   private int oldalszám;
   @Column(unique = false)
   private boolean borító;
   @Column(unique = false)
   private int súly;
   @Column(unique = false)
   private LocalDate beszerzés_idő;
   @Column(unique = false)
   private boolean elolvasva;
   
   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "konyv_id")
   private Set<Kulcsszo> kulcsszavak = new HashSet<>();
   
   public void addKulcsszo(Kulcsszo a)
   {
       kulcsszavak.add(a);
   }
    public Set<Kulcsszo> getKulcsszo(Kulcsszo a)
  {
      return kulcsszavak;
  }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public String getCím() {
        return cím;
    }

    public void setCím(String cím) {
        this.cím = cím;
    }

    public int getKiadás_év() {
        return kiadás_év;
    }

    public void setKiadás_év(int kiadás_év) {
        this.kiadás_év = kiadás_év;
    }

    public String getKiadó() {
        return kiadó;
    }

    public void setKiadó(String kiadó) {
        this.kiadó = kiadó;
    }

    public String getMűfaj() {
        return műfaj;
    }

    public void setMűfaj(String műfaj) {
        this.műfaj = műfaj;
    }

    public String getNyelv() {
        return Nyelv;
    }

    public void setNyelv(String Nyelv) {
        this.Nyelv = Nyelv;
    }

    public int getOldalszám() {
        return oldalszám;
    }

    public void setOldalszám(int oldalszám) {
        this.oldalszám = oldalszám;
    }

    public boolean isBorító() {
        return borító;
    }

    public void setBorító(boolean borító) {
        this.borító = borító;
    }

    public int getSúly() {
        return súly;
    }

    public void setSúly(int súly) {
        this.súly = súly;
    }

    public LocalDate getBeszerzés_idő() {
        return beszerzés_idő;
    }

    public void setBeszerzés_idő(LocalDate beszerzés_idő) {
        this.beszerzés_idő = beszerzés_idő;
    }

    public boolean isElolvasva() {
        return elolvasva;
    }

    public void setElolvasva(boolean elolvasva) {
        this.elolvasva = elolvasva;
    }

    public Konyv(String szerzo, String cím, int kiadás_év, String kiadó, String műfaj, String Nyelv, int oldalszám, boolean borító, int súly, LocalDate beszerzés_idő, boolean elolvasva) {
        this.szerzo = szerzo;
        this.cím = cím;
        this.kiadás_év = kiadás_év;
        this.kiadó = kiadó;
        this.műfaj = műfaj;
    
        this.Nyelv = Nyelv;
        this.oldalszám = oldalszám;
        this.borító = borító;
        this.súly = súly;
        this.beszerzés_idő = beszerzés_idő;
        this.elolvasva = elolvasva;
    }
    //Második konstruktor
    public Konyv(String szerzo, String cím, int kiadás_év, String kiadó, String műfaj, String Nyelv, int oldalszám, boolean borító, int súly, LocalDate beszerzés_idő, boolean elolvasva, int ID) {
        this.szerzo = szerzo;
        this.cím = cím;
        this.kiadás_év = kiadás_év;
        this.kiadó = kiadó;
        this.műfaj = műfaj;
    
        this.Nyelv = Nyelv;
        this.oldalszám = oldalszám;
        this.borító = borító;
        this.súly = súly;
        this.beszerzés_idő = beszerzés_idő;
        this.elolvasva = elolvasva;
        this.ID=ID;
    }
    
   public Konyv(){}

    @Override
    public String toString() {
        return "Konyv{" + "ID=" + ID + ", szerzo=" + szerzo + ", c\u00edm=" + cím + ", kiad\u00e1s_\u00e9v=" + kiadás_év + ", kiad\u00f3=" + kiadó + ", m\u0171faj=" + műfaj + ", Nyelv=" + Nyelv + ", oldalsz\u00e1m=" + oldalszám + ", bor\u00edt\u00f3=" + borító + ", s\u00faly=" + súly + ", beszerz\u00e9s_id\u0151=" + beszerzés_idő + ", elolvasva=" + elolvasva + '}';
    }
   
           
   
   
}
