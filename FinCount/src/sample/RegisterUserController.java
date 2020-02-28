package sample;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firsName;

    @FXML
    private TextField lastName;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    @FXML
    void initialize() {

        cancelButton.setOnAction(actionEvent -> {
            cancelButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/loginUser.fxml"));
            try {

                Parent root = loader.load();//loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                //e.printStackTrace();
            }


        });

        okButton.setOnAction(actionEvent -> {
            User rd = new User(
                loginField.getText(),
                passwordField.getText(),
                firsName.getText(),
                lastName.getText());

            if (!(rd.getLogin().equals("")) && !(rd.getPassword().equals("")) && !(rd.getFirst_name().equals("")) && !(rd.getLast_name().equals(""))) {
                try {
                    //
                    FileWriter fw;
                    if(hasUser("src/sample/"+rd.getLogin()+".json")) {
                        fw = new FileWriter("src/sample/"+rd.getLogin()+".json");
                        fw.write(GSON.toJson(rd));
                        fw.close();
                        okButton.getScene().getWindow().hide();
                        newScene("loginUser");
                    }


                } catch (IOException e) {
                    Library.error("Ошибка 404, обратитесь к Администратору");
                    e.printStackTrace();
                }

            }else Library.error("Заполните все поля");
        });
    }

    public static boolean hasUser( String path) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            Library.error("Has it user");

            return false;
        }catch (Exception e){}
        finally { if (fr != null) fr.close();}
        return true;
    }

    void newScene (String nameFXML){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/"+nameFXML+".fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User {
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    public ArrayList<AppData> dataList = new ArrayList();
    public ArrayList<Promisser> promissers = new ArrayList<>();
    public static int dataListIndex = 0;
    public static boolean wasCreated = false;


    User(String login, String password, String firs_name, String last_name){
        this.login = login;
        this.password = password;
        this.first_name = firs_name;
        this.last_name = last_name;
    }

    public void add(AppData appData){
        if (!hasObject(dataList, appData)) {
            dataList.add(appData);
            wasCreated = true;
        }
    }

    public void add(Promisser promisser){
        if (!hasObject(promissers, promisser)) {
            promissers.add(promisser);
        }
    }

    public boolean hasObject(List<AppData> list,AppData appData){
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDate().equals(appData.getDate()) ){
                    dataListIndex = i;
                    wasCreated = false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasObject(List<Promisser> list,Promisser promisser){
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getPromisser().equals(promisser.getPromisser()) ){
                    //System.out.println("has Date");
                    return true;
                }
            }
        }
        //System.out.println("has'tDate");
        return false;
    }


    public String getLast_name() {
        return  last_name ;
    }

    public String getLogin() {
         return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }
}