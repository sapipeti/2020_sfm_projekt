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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.javaguides.hibernate.entity.Konyv;
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
    private TextField NewKulcsszavakTextField;

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
    private DatePicker BeszerzesDatePIcker;
    
    
    @FXML
    void ElolvasvaBox() 
    {
    }
    
    
    
    @FXML
    void handleSaveButtonPushed() 
    {  
        Konyv peldany =new Konyv(NewSzerzoTextField.getText(),NewCimTextField.getText(),Integer.parseInt(KiadasiEvTextField.getText()),
        NewKiadoTextField.getText(),NewMufajTextField.getText(),NewKulcsszavakTextField.getText(),NewNyelvTextField.getText(),
        Integer.parseInt(NewOldalSzamTextField.getText()),BoritoCheckBox.isSelected(),Integer.parseInt(NewSulyTextField.getText()),
        Integer.parseInt("999999"),ElolvasvaCheckBox.isSelected());
        
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
        NewKulcsszavakTextField.setText("");  
        NewNyelvTextField.setText("");
        NewOldalSzamTextField.setText("");
        KiadasiEvTextField.setText("");

        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad a Mentés gombot!");
        alert.setContentText("Minden beviteli mező értékét töröltük..");
        alert.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    
}
