/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javaguides.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Robert
 */
@Entity
@Table(name = "akulcs")
public  class Kulcsszo implements Serializable {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(unique = true)
    private int ID;
    @Column(unique = false)
    private String kulcsszo;

    public String getKulcsszo() {
        return kulcsszo;
    }

    public void setKulcsszo(String kulcsszo) {
        this.kulcsszo = kulcsszo;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Kulcsszo(String kulcsszo) {
        this.kulcsszo = kulcsszo;
    }
    //Második konstruktor
    public Kulcsszo(String kulcsszo, int ID) {
        this.kulcsszo = kulcsszo;
        this.ID = ID;
    }
    
    @Override
    public String toString() {
        return kulcsszo ;
    }
    
}
