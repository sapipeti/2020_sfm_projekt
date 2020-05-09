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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import net.javaguides.hibernate.entity.Konyv;
import net.javaguides.hibernate.entity.Kulcsszo;
import net.javaguides.hibernate.util.JpaKonyvDAO;
import org.h2.table.Table;

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
    private TableView<Kulcsszo> Tabla;
     
    @FXML
    private ComboBox<String> TablaComboBox;
     
    @FXML
    private HBox SqlHbox;
     
    @FXML
    private ComboBox<String> OszlopComboBox;
    
    @FXML
    private ListView<String> OszlopListView;
    
    @FXML
    private TextField SqlQueryTextField;
    
    @FXML
    void ElolvasvaBox() 
    {
    }
       
    
        /*TableColumn id = new TableColumn("ID");
        TableColumn nyelv = new TableColumn("Nyelv");
        TableColumn beszerz_ido = new TableColumn("Beszerzés_idő");
        TableColumn borito = new TableColumn("Borító");
        TableColumn cim = new TableColumn("Cím");
        TableColumn elolvasva = new TableColumn("Elolvasva");
        TableColumn kiadasi_ev = new TableColumn("Kiadási_év");
        TableColumn kiado = new TableColumn("Kiadó");
        TableColumn mufaj = new TableColumn("Műfaj");
        TableColumn oldalszam = new TableColumn("Oldalszám");
        TableColumn szerzo = new TableColumn("Szerző");
        TableColumn suly = new TableColumn("Súly");
        TableColumn kulcsszo = new TableColumn("Kulcsszó");*/
    
    
    @FXML
    void handleSaveButtonPushed() 
    { 
        if (    NewSzerzoTextField.getText().trim().isEmpty() ||
                NewCimTextField.getText().trim().isEmpty() ||
                NewKiadoTextField.getText().trim().isEmpty() ||
                NewOldalSzamTextField.getText().trim().isEmpty() ||
                NewNyelvTextField.getText().trim().isEmpty() ||
                NewSulyTextField.getText().trim().isEmpty() ||
                KiadasiEvTextField.getText().trim().isEmpty() ||
                BeszerzesiIdoDatePIcker.getValue() == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Hiányzó értékek");
                alert.setHeaderText("Nem töltöttél ki minden kötelező mezőt!");
                alert.setContentText("Az adat nem lett feltöltve az adatbázisba!");
                alert.showAndWait();
            }      
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
    void  Oszlop_SelectedIndexChanged() 
    {   
         OszlopListView.getItems().clear();
         
        List<String> EredmenyFejlec = new ArrayList<String>();
            try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            EredmenyFejlec=aDAO.queryKonyvFejlec("SELECT * FROM "+OszlopComboBox.getValue());
            }
            for (String string : EredmenyFejlec) {
             OszlopListView.getItems().add(string);
        }
           
    }
    
   /*public TableColumn <Kulcsszo,Integer> id;
     public TableColumn <Kulcsszo,String> kulcsszo;
    
    ObservableList<Konyv> observableList= FXCollections.observableArrayList();
    ObservableList<Kulcsszo> observableList2= FXCollections.observableArrayList(
    new Kulcsszo("Fasztarisznya",13));*/
        
    
    @FXML
    void  ListButtonPushed() 
    {
        String tabla =TablaComboBox.getValue();
        
        if(tabla.equals("Könyv")){
            tabla="konyv";
        }else{
            tabla="akulcs";
        }
        
        List<Object[]> Eredmeny = new ArrayList<Object[]>();
        List<String> EredmenyFejlec = new ArrayList<String>();
        
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            Eredmeny=aDAO.queryKonyv("SELECT * FROM "+tabla);
       }
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            EredmenyFejlec=aDAO.queryKonyvFejlec("SELECT * FROM "+tabla);
       }

        /*if(EredmenyFejlec.size()==3){
            Tabla.getColumns().add(id);
            Tabla.getColumns().add(kulcsszo);
        }else if(EredmenyFejlec.size()!=0){
            Tabla.getColumns().add(id);
            Tabla.getColumns().add(nyelv);
            Tabla.getColumns().add(beszerz_ido);
            Tabla.getColumns().add(borito);
            Tabla.getColumns().add(cim);
            Tabla.getColumns().add(kiadasi_ev);
            Tabla.getColumns().add(kiado);
            Tabla.getColumns().add(mufaj);
            Tabla.getColumns().add(oldalszam);
            Tabla.getColumns().add(suly);
            Tabla.getColumns().add(elolvasva);
            Tabla.getColumns().add(szerzo);
        }*/

        
        Tabla.getItems().add(new Kulcsszo("Fasza",12));  
        
//        for (Object[] obj : Eredmeny) {
//            //LekerdezesListView.getItems().add(obj[0]+" "+obj[1]);
//            
//            //A TableViewba sort nem lehet hozzáadni csak objektum példányaként.
//            //Nem akarnak megjelenni a táblázatban az értékek.
//            if(EredmenyFejlec.size()!=3){
//            Tabla.getItems().add(new Konyv(obj[0].toString(),obj[1].toString(),Integer.parseInt(obj[2].toString()),obj[3].toString(),obj[4].toString(),obj[5].toString(),Integer.parseInt(obj[6].toString()),(boolean)obj[7],Integer.parseInt(obj[8].toString()),(LocalDate)obj[9],(boolean)obj[10]));
//            }else{
//                 
//            //Tabla.getItems().add(new Kulcsszo((String)obj[1],(int)obj[0]));    
//            }
//        }
    
        
    } 
    @FXML
    void  ListSQLButtonPushed() 
    {
        List<Object[]> Eredmeny = new ArrayList<Object[]>();
        List<String> EredmenyFejlec = new ArrayList<String>();
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            Eredmeny=aDAO.queryKonyv(SqlQueryTextField.getText());
       }
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            EredmenyFejlec=aDAO.queryKonyvFejlec(SqlQueryTextField.getText());
       }
       
       List<ListView<String>> LekerdezesLista = new ArrayList<ListView<String>>();
       SqlHbox.setPrefWidth(125*EredmenyFejlec.size());
       
       //Dinamikus számú listview készítés.
       for (int i = 0; i < EredmenyFejlec.size(); i++) {
                LekerdezesLista.add(new ListView<String>());
            }
       
        for (int i = 0; i < EredmenyFejlec.size(); i++) {
            LekerdezesLista.get(i).getItems().add(EredmenyFejlec.get(i));
        }
       
       if(EredmenyFejlec.size()!=1){
        for (Object[] obj : Eredmeny) {
            for (int i = 0; i < LekerdezesLista.size(); i++) {
                LekerdezesLista.get(i).getItems().add(obj[i].toString());
            }
        }
       }else{
       LekerdezesLista.get(0).getItems().add(Eredmeny.toString().substring(1, Eredmeny.toString().length()-1));
       }
       
       for (int i = 0; i < LekerdezesLista.size(); i++) {
                SqlHbox.getChildren().add(LekerdezesLista.get(i));
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
                                            "Történelem" ,"Tudomány és Természet" ,"Utazás" ,"Vallás, mitológia","Sci-Fi, Fantasy");
       /*TablaComboBox.getItems().addAll("Könyv","Kulcs");
       id.setCellValueFactory(new PropertyValueFactory<>("ID"));
       kulcsszo.setCellValueFactory(new PropertyValueFactory<>("Kulcsszó"));
       Tabla.setItems(observableList2);*/
       
        SqlHbox.setPrefWidth(300);
        OszlopComboBox.getItems().addAll("Konyv","Akulcs");
    }

    
    
}
