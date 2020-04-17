package hu.unideb.inf;

import hu.unideb.inf.view.FXMLStudentsSceneController;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.javaguides.hibernate.entity.Konyv;
import net.javaguides.hibernate.util.JpaKonyvDAO;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLStudentsScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Book Register_2.0");
        stage.setScene(scene);
<<<<<<< HEAD:Konyvespolc_gui/src/main/java/hu/unideb/inf/MainApp.java
=======
        
        ((FXMLStudentsSceneController)loader.getController()).setModel(new Model());
        
        List<String> els = new ArrayList<String>();

        Konyv el = new Konyv("én","az életem",2018,"best","siker","legjobb,legeslegjobb","magyar",31,true,75,2010,true);
        Konyv ma = new Konyv("sápu","értelmetlen",2010,"worst","bukás","senki,kaka","román",1111,false,187,1990,false); 
        Konyv me = new Konyv("Cseke", "értelmes", 1200, "best", "bukás2", "valaki, anyone", "holland", 10, true, 30, 1300, true);
        try (JpaKonyvDAO aDAO =  new JpaKonyvDAO()) {

            aDAO.saveKonyv(el);
            aDAO.saveKonyv(ma);
            aDAO.saveKonyv(me);
            List<Konyv> konyvList = aDAO.getKonyvs();
            konyvList.forEach(a -> System.out.println(a));
        }
>>>>>>> e088e650f028f662188554c5fa01d3aca344d48c:Konyvespolc/src/main/java/hu/unideb/inf/MainApp.java
        stage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
