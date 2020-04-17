/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import hu.unideb.inf.model.Model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hallgato
 */
public class FXMLStudentsSceneController implements Initializable {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }
    
    @FXML
    private Label colorLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private TextField newNameTextField;
    
    
    
    
    //////////////////////////////////////////////////////////////
    
    
    
    
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
    private CheckBox ElolvasvaCheckBox;

    @FXML
    private CheckBox BoritoCheckBox;

    @FXML
    private DatePicker KiadasiEvDatePIcker;
    
    
    @FXML
    void ElolvasvaBox() 
    {
    }
    
    
    
    @FXML
    void handleDeleteButtonPushed() 
    {  
        NewCimTextField.setText("none");
        
        NewKiadoTextField.setText("none");
        
        NewIDTextField.setText("none");
        NewSzerzoTextField.setText("none");
        NewSulyTextField.setText("none");
        NewMufajTextField.setText("none");
        NewKulcsszavakTextField.setText("none");  
        NewNyelvTextField.setText("none");
        NewOldalSzamTextField.setText("none");
        

        if(ElolvasvaCheckBox.isSelected())
        {
            System.out.println("Elolvasva!");
        }
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("Megnyomtad a Delete gombot!");
        alert.setContentText("Minden beviteli mező értékét töröltük..");
        alert.showAndWait();
    }

    
    
    
    
    
    
    
    
    
    
    ///////////////////////////////////////////////////////////////
    
    
    
    
    @FXML
    void handleLoadButtonPushed() {
    //nameLabel.setText(model.getStudent().getName());
    //Ez verzió frissíti a címkét.
    nameLabel.textProperty().bind(model.getStudent().nameProperty());
    creditsLabel.setText(""+model.getStudent().getCredits());
    birthLabel.setText(model.getStudent().getBirthDate().toString());
    
    
    }
    
     @FXML
    void handleChangeButtonPushed( ) {
        model.getStudent().setName(newNameTextField.getText());
        
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Button pushed");
          alert.setHeaderText("You have pushed the Change Name button");
          alert.setContentText("This shows that you have pushed a button...");
          alert.showAndWait();
    
    }    

    @FXML
    void handleButtonPushed() {
        System.out.println("Gomb!!!");
        if (colorLabel.getText().equals("Fekete")) {
            colorLabel.setText("Fehér");
        } else {
            colorLabel.setText("Fekete");
        }
   
    }
    @FXML
    void handleSaveToFileButtonPushed() throws IOException {
        try(FileOutputStream fos = new FileOutputStream("students.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(model.getClass());
        }

    }
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    
}
