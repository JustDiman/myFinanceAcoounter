package sample;
import javafx.scene.control.Alert;

public class Library {

    public static String filePath;

    static void error (String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText( str );
        alert.showAndWait();
    }

}
