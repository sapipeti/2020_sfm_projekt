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
    private TextField NewIDTextField;

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
    private TextField NewMufajTextField;

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
    private ListView<String> KulcsszavakListView;
    
    @FXML
    void ElolvasvaBox() 
    {
    }

    
    @FXML
    void handleSaveButtonPushed() 
    { 
        Konyv peldany =new Konyv(NewSzerzoTextField.getText(),NewCimTextField.getText(),Integer.parseInt(KiadasiEvTextField.getText()),
        NewKiadoTextField.getText(),NewMufajTextField.getText(),NewNyelvTextField.getText(),
        Integer.parseInt(NewOldalSzamTextField.getText()),BoritoCheckBox.isSelected(),Integer.parseInt(NewSulyTextField.getText()),
        BeszerzesiIdoDatePIcker.getValue(),ElolvasvaCheckBox.isSelected());
        
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
        NewIDTextField.setText("");
        NewSzerzoTextField.setText("");
        NewSulyTextField.setText("");
        NewMufajTextField.setText("");
        NewNyelvTextField.setText("");
        NewOldalSzamTextField.setText("");
        KiadasiEvTextField.setText("");

        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        KulcsszavakListView.getItems().clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad a Mentés gombot!");
        alert.setContentText("Minden beviteli mező értékét töröltük..");
        alert.showAndWait();
    }
    
    
    @FXML
    void handleEmptyButtonPushed() 
    {  
        NewCimTextField.setText(""); 
        NewKiadoTextField.setText("");
        NewIDTextField.setText("");
        NewSzerzoTextField.setText("");
        NewSulyTextField.setText("");
        NewMufajTextField.setText("");
        NewNyelvTextField.setText("");
        NewOldalSzamTextField.setText("");
        KiadasiEvTextField.setText("");
        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        KulcsszavakListView.getItems().clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad a Delete gombot!");
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
        }
    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KulcsszavakComboBox.getItems().addAll("Háborús","Vámpír","Tenger","Óceán","Űr","Barlang","Kincs","Kalóz","Őskor","Ókor","Középkor","Újkor","Modern kor","Európa","Észak-Amerika","Dél-Amerika","Ázsia","Afrika","Ausztália","Anktartisz","Képzeletbeli hely");
    }

    
    
}
