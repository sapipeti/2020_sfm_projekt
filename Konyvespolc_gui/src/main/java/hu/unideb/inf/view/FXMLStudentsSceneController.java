/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.exit;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.javaguides.hibernate.entity.Konyv;
import net.javaguides.hibernate.entity.Kulcsszo;
import net.javaguides.hibernate.util.JpaKonyvDAO;

/**
 * FXML Controller class
 *
 * @author hallgato
 */
public class FXMLStudentsSceneController implements Initializable {
   
   
    @FXML
    private TextField NewSzerzoTextField;
    
    @FXML
    private TextField NewCimTextField;

    @FXML
    private TextField NewKiadoTextField;

    @FXML
    private TextField NewOldalSzamTextField;

    @FXML
    private TextField NewNyelvTextField;


    @FXML
    private TextField NewSulyTextField;

    @FXML
    private TextField KiadasiEvTextField;

    @FXML
    private CheckBox ElolvasvaCheckBox;

    @FXML
    private CheckBox BoritoCheckBox;

    @FXML
    private DatePicker BeszerzesiIdoDatePIcker;
    
    @FXML
    private ComboBox<String> KulcsszavakComboBox;
    
    @FXML
    private ComboBox<String> MufajokComboBox;
    
    @FXML
    private ListView<String> KulcsszavakListView;
    
    @FXML
    private ListView<String> LekerdezesListView;
    
    
    @FXML
    void ElolvasvaBox() 
    {
    }

    
    @FXML
    void handleSaveButtonPushed() 
    { 
        if (    NewSzerzoTextField.getText().length() <= 0 ||
                NewCimTextField.getText().length() <= 0 ||
                NewKiadoTextField.getText().length() <= 0 ||
                NewNyelvTextField.getText().length() <= 0)
             exit(1);
        else
        {
  
        Konyv peldany =new Konyv(NewSzerzoTextField.getText(),NewCimTextField.getText(),Integer.parseInt(KiadasiEvTextField.getText()),
        NewKiadoTextField.getText(),MufajokComboBox.getValue(),NewNyelvTextField.getText(),
        Integer.parseInt(NewOldalSzamTextField.getText()),BoritoCheckBox.isSelected(),Integer.parseInt(NewSulyTextField.getText()),
        BeszerzesiIdoDatePIcker.getValue(),ElolvasvaCheckBox.isSelected());
       
        //Generálni is lehet az ID-t. Mi viszont számolunk felfelé.
        List<Integer> ID = new ArrayList<Integer>();
        try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            ID=aDAO.getBiggestID();
        }
        if(ID.get(0)==null){
            peldany.setID(0);
        }else{
            peldany.setID(ID.get(0)+1);
        }
        
        
        for (int i = 0; i < KulcsszavakListView.getItems().size(); i++) {
            peldany.addKulcsszo(new Kulcsszo(KulcsszavakListView.getItems().get(i)));
        }
        
        //Adatok feltöltése az adatbázisba.
        try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            aDAO.saveKonyv(peldany);
            List<Konyv> konyvList = aDAO.getKonyvs();
            konyvList.forEach(a -> System.out.println(a));
        }
        
        NewCimTextField.setText(""); 
        NewKiadoTextField.setText("");
        NewSzerzoTextField.setText("");
        NewSulyTextField.setText("");
        MufajokComboBox.setValue(null);
        KulcsszavakComboBox.setValue(null);
        NewNyelvTextField.setText("");
        NewOldalSzamTextField.setText("");
        KiadasiEvTextField.setText("");
        BeszerzesiIdoDatePIcker.setValue(null);
        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        KulcsszavakListView.getItems().clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad a Mentés gombot!");
        alert.setContentText("Adatok feltöltése..");
        alert.showAndWait();
    }
    }
    
    
     @FXML
    void HandleMouseExitSzerzo() 
    {
        if (NewSzerzoTextField.getText().length() <= 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Szerzo Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Szerzo Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szoveget");
            alert.showAndWait();
        }
    }

    @FXML
    void HandleMouseExitCim() 
    {
        if (NewCimTextField.getText().length() <= 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cim Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Cim Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szoveget");
            alert.showAndWait();
        }

    }

    @FXML
    void HandleMouseExitKiado() 
    {
        if (NewKiadoTextField.getText().length() <= 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Kiado Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Kiado Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szoveget");
            alert.showAndWait();
        }

    }

    @FXML
    void HandleMouseExitNyelv() throws InterruptedException
    {
        if (NewNyelvTextField.getText().length() <= 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Nyelv Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Nyelv Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szoveget");
            alert.showAndWait();
        }

    }
    
    @FXML
    void HandleMouseExitKiadasiEv() 
    {
        if (Integer.parseInt(KiadasiEvTextField.getText()) <= 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Kiadasi Ev Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Kiadasi Ev Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szamot");
            alert.showAndWait();
        }
    }

    @FXML
    void HandleMouseExitOldalszam() 
    {
        if (Integer.parseInt(NewOldalSzamTextField.getText()) <= 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Kiadasi Oldalszam Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Oldalszam Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szamot");
            alert.showAndWait();
            
        }
    }

    @FXML
    void HandleMouseExitSuly() 
    {
        if(!NewSulyTextField.getText().contains("[a-z]"))
        //if (Integer.parseInt(NewSulyTextField.getText()) <= 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Suly Mezo");
            alert.setHeaderText("Megprobaltad elhagyni a Suly Mezot!");
            alert.setContentText("Kerlek irj be nemi kamu szamot");
            alert.showAndWait();
        }
    }
    
    
    
    
    @FXML
    void handleEmptyButtonPushed() 
    {  
        NewCimTextField.setText(""); 
        NewKiadoTextField.setText("");
        NewSzerzoTextField.setText("");
        NewSulyTextField.setText("");
        MufajokComboBox.setValue(null);
        KulcsszavakComboBox.setValue(null);
        NewNyelvTextField.setText("");
        NewOldalSzamTextField.setText("");
        KiadasiEvTextField.setText("");
        BeszerzesiIdoDatePIcker.setValue(null);
        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        KulcsszavakListView.getItems().clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad az Empty gombot!");
        alert.setContentText("Minden beviteli mező értékét töröltük..");
        alert.showAndWait();
    }
    
    @FXML
    void  Kulcsszavak_SelectedIndexChanged() 
    {  
            if(!KulcsszavakListView.getItems().contains(KulcsszavakComboBox.getValue())){
             KulcsszavakListView.getItems().add( KulcsszavakComboBox.getValue());
            }
    }
    
    @FXML
    void  ListButtonPushed() 
    {
        List<String> Eredmeny = new ArrayList<String>();
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            Eredmeny=aDAO.queryKonyv("SELECT NYELV FROM KONYV");
       }
        for (String string : Eredmeny) {
            System.out.println(string+"\n");
            LekerdezesListView.getItems().add(string);
        } 
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KulcsszavakComboBox.getItems().addAll("Háborús","Western","Vámpír","Időutazás","Tenger","Óceán","Űr","Barlang","Kincs","Kalóz","Őskor","Ókor","Középkor","Újkor","Modern kor","Európa","Észak-Amerika","Dél-Amerika","Ázsia","Afrika","Ausztália","Anktartisz","Képzeletbeli hely");
        MufajokComboBox.getItems().addAll("Család és szülők","Életmód, egészség","Életrajzok, visszaemlékezések" ,
                                            "Ezotéria" ,"Gasztronómia" ,"Gyermek és ifjúsági" ,"Hangoskönyv" ,"Hobbi, szabadidő", 
                                            "Irodalom" ,"Képregény" ,"Kert, ház, otthon" ,"Lexikon, enciklopédia" ,"Művészet, építészet" ,
                                            "Napjaink, bulvár, politika" ,"Nyelvkönyv, szótár" ,"Pénz, gazdaság, üzleti élet" ,"Sport, természetjárás" ,
                                            "Számítástechnika, internet" ,"Tankönyvek, segédkönyvek" ,"Társ. tudományok" ,"Térkép" ,
                                            "Történelem" ,"Tudomány és Természet" ,"Utazás" ,"Vallás, mitológia");
    }

    
    
}
