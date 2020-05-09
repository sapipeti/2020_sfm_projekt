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

    String KonyvOszlop="";
    String aKulcsOszlop="";
    
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
    private HBox SqlHbox;
    
    @FXML
    private HBox ListHbox;
     
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
    
    //Lekerdezes ablak
    @FXML
    private ComboBox<String> TablaComboBox;
    
    @FXML
    private ComboBox OszlopSzuresComboBox;
    
    @FXML
    private ComboBox OszlopRendezesComboBox;
            
    @FXML
    private ComboBox RendezesComboBox;
       
    @FXML
    private TextField OperatorTextField;
            
    @FXML
    private TextField KapcsolatTextField;       
    
    @FXML
    private TextField ErtekTextField;
    
    @FXML
    private ListView<String> SzuresListView;
    
    @FXML
    private ListView<String> RendezesListView;
                            

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
            EredmenyFejlec=OszlopFormazas(OszlopComboBox.getValue());
            for (String string : EredmenyFejlec) {
             OszlopListView.getItems().add(string);
        }
           
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
       SqlHbox.getChildren().clear();
      
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
    
    //----------Lekerdezes ablak----------//
    @FXML
    void  ListButtonPushed() 
    {
       String oszlop="";
       String tabla =TablaComboBox.getValue();
       String szures="";
       String rendezes="";
       
       List<Object[]> Eredmeny = new ArrayList<Object[]>();
       List<String> EredmenyFejlec = new ArrayList<String>();
        
       if(!SzuresListView.getItems().isEmpty()){
           szures+="where";
           for (int i = 0; i < SzuresListView.getItems().size(); i++) {
               if(SzuresListView.getItems().get(i).toUpperCase().equals("VAGY")||SzuresListView.getItems().get(i).toUpperCase().equals("OR")){
                   szures+= " OR ";
               }else if(SzuresListView.getItems().get(i).toUpperCase().equals("ÉS")||SzuresListView.getItems().get(i).toUpperCase().equals("AND")){
                   szures+= " AND ";
               }else{
                   if(SzuresListView.getItems().get(i).startsWith(""))
                   szures+= " "+SzuresListView.getItems().get(i)+" ";
               }
           }
       }
       if(!RendezesListView.getItems().isEmpty()){
           rendezes+="order by";
           for (int i = 0; i < RendezesListView.getItems().size(); i++) {
               if(i==0){
                if(RendezesListView.getItems().get(i).contains("Növekvő")){
                   rendezes+=" "+RendezesListView.getItems().get(i).substring(0, RendezesListView.getItems().get(i).length()-7)+" ASC ";
                }else if(RendezesListView.getItems().get(i).contains("Csökkenő")){
                   rendezes+=" "+RendezesListView.getItems().get(i).substring(0, RendezesListView.getItems().get(i).length()-8)+" DESC ";
                }
               }else{
                if(RendezesListView.getItems().get(i).contains("Növekvő")){
                 rendezes+=", "+RendezesListView.getItems().get(i).substring(0, RendezesListView.getItems().get(i).length()-7)+" ASC ";  
                }else if(RendezesListView.getItems().get(i).contains("Csökkenő")){
                 rendezes+=", "+RendezesListView.getItems().get(i).substring(0, RendezesListView.getItems().get(i).length()-8)+" DESC ";  
                } 
               }
           }
       }
       if(tabla.equals("Konyv")){
           oszlop=KonyvOszlop;
       }else{
           oszlop=aKulcsOszlop;
       }
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            Eredmeny=aDAO.queryKonyv("SELECT "+oszlop+" FROM "+tabla+" "+szures +" "+rendezes);
       }
       EredmenyFejlec = OszlopFormazas(tabla);

        List<ListView<String>> LekerdezesLista = new ArrayList<ListView<String>>();

       ListHbox.setPrefWidth(125*EredmenyFejlec.size());
       ListHbox.getChildren().clear();
      
       //Dinamikus számú listview készítés.
       for (int i = 0; i < EredmenyFejlec.size(); i++) {
                LekerdezesLista.add(new ListView<String>());
            }
       //Az oszlopok neveinek hozzáadása.
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
                ListHbox.getChildren().add(LekerdezesLista.get(i));
            }
    
        
    } 
    
    @FXML
    void  Tabla_SelectedIndexChanged() 
    {  
        
        
        OszlopSzuresComboBox.getItems().clear();
        OszlopRendezesComboBox.getItems().clear();
        List<String> EredmenyFejlec = new ArrayList<String>();
            EredmenyFejlec = OszlopFormazas(TablaComboBox.getValue());
            
            for (String string : EredmenyFejlec) {

             OszlopSzuresComboBox.getItems().add(string);
             OszlopRendezesComboBox.getItems().add(string);
            }
    }
    
    @FXML
    void  EmptyFieldsButtonPushed() 
    {
    OszlopSzuresComboBox.setValue(null);
    OszlopRendezesComboBox.setValue(null);    
    RendezesComboBox.setValue(null);
    OperatorTextField.setText("");
    KapcsolatTextField.setText("");      
    ErtekTextField.setText(""); 
    SzuresListView.getItems().clear();
    RendezesListView.getItems().clear();
    KapcsolatTextField.setDisable(true);
    }
    
    @FXML
    void  SzuresAddButtonPushed() 
    {
        if(OszlopSzuresComboBox.getValue() ==null || OperatorTextField.getText().isEmpty() || ErtekTextField.getText().isEmpty() &&  SzuresListView.getItems().size()==0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nem töltöttél ki minden mezőt a szűréshez!");
            alert.setContentText("Így nem sikeres a feltétel hozzáadaása.");
            alert.showAndWait();
        }else if(OszlopSzuresComboBox.getValue() ==null || OperatorTextField.getText().isEmpty() || ErtekTextField.getText().isEmpty() || KapcsolatTextField.getText().isEmpty() &&  SzuresListView.getItems().size()!=0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nem töltöttél ki minden mezőt a szűréshez!");
            alert.setContentText("Így nem sikeres a feltétel hozzáadaása.");
            alert.showAndWait();
        }else{
            if(!KapcsolatTextField.getText().isEmpty()){
                 SzuresListView.getItems().add(KapcsolatTextField.getText());
            }
            SzuresListView.getItems().add(OszlopSzuresComboBox.getValue()+" "+ OperatorTextField.getText()+" "+ErtekTextField.getText());
            KapcsolatTextField.setDisable(false);
        }
        
        
    }

    @FXML
    void  RendezesAddButtonPushed() 
    {
        if(RendezesComboBox.getValue() == null ||OszlopRendezesComboBox.getValue() == null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nem állítottál be minden mezőt a rendezéshez!");
            alert.setContentText("Így nem sikeres a feltétel hozzáadaása.");
            alert.showAndWait();
        }else{
            if(RendezesListView.getItems().contains(OszlopRendezesComboBox.getValue()+" Csökkenő" )|| RendezesListView.getItems().contains(OszlopRendezesComboBox.getValue()+" Növekvő")){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Hiba");
            alert.setHeaderText("Ilyen rendezésed már van!");
            alert.setContentText("Így nem sikeres a feltétel hozzáadaása.");
            alert.showAndWait();    
            }else{
                RendezesListView.getItems().add(OszlopRendezesComboBox.getValue()+" "+RendezesComboBox.getValue());
            }  
        }
    }
    
    public void KonyvOszlop(){
       List<String> EredmenyFejlec = new ArrayList<String>();
       
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            EredmenyFejlec=aDAO.queryKonyvFejlec("SELECT * FROM KONYV");
       }
        for (int i = 0; i < EredmenyFejlec.size(); i++) {
            if(i==0){
                KonyvOszlop+=EredmenyFejlec.get(i);
            }else{
                KonyvOszlop+=", "+EredmenyFejlec.get(i);
            }
        }
            
    }
    
    public void aKulcsOszlop(){
        List<String> EredmenyFejlec = new ArrayList<String>();
       
       try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {
            EredmenyFejlec=aDAO.queryKonyvFejlec("SELECT * FROM AKULCS");
       }
       for (int i = 0; i < EredmenyFejlec.size(); i++) {
            if(i==0){
                aKulcsOszlop+=EredmenyFejlec.get(i);
            }else{
                aKulcsOszlop+=", "+EredmenyFejlec.get(i);
            }
        }
    }
    
    public List<String> OszlopFormazas(String tablanev){
        String token=", ";
        List<String> vissza = new ArrayList<String>();
            if(tablanev.equals("Konyv")){
                String [] KonyvOszlopArray=KonyvOszlop.split(token);
                for (int i = 0; i < KonyvOszlopArray.length; i++) {
                    vissza.add(KonyvOszlopArray[i]);
                }
            }else{
                String [] aKulcsOszlopArray=aKulcsOszlop.split(token);
                for (int i = 0; i < aKulcsOszlopArray.length; i++) {
                    vissza.add(aKulcsOszlopArray[i]);
                }
            }
            return vissza;
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
       KonyvOszlop();
       aKulcsOszlop();
        SqlHbox.setPrefWidth(300);
        OszlopComboBox.getItems().addAll("Konyv","Akulcs");
        TablaComboBox.getItems().addAll("Konyv","Akulcs");
        RendezesComboBox.getItems().addAll("Csökkenő","Növekvő");
    }

    
    
}
