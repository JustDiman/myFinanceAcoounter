package sample;

        import java.net.URL;
        import java.time.LocalDate;
        import java.util.ResourceBundle;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

public class AppController {

    private ObservableList<String> observableList = FXCollections.observableArrayList();
    User user = new LoginUser().regUser;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date_field;

    @FXML
    private void date_field(){
        user.add(new AppData( dateToString(date_field)));
        if ( user.wasCreated ) {
            setNullFields();
        }else {
            loadFields();
        }
    }

    @FXML
    private TextField income_main;
    @FXML
    private void income_main(){

        if (!income_main.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setMainIncome( income_main.getText() );
        }

    }
    @FXML
    private TextField income_aditional;
    @FXML
    private void income_aditional(){
        if (!income_aditional.getText().equals("") ) {
            System.out.println(income_aditional.getText());
            user.dataList.get(user.dataListIndex).setAdvanceIncome( income_aditional.getText() );
        }
    }

    @FXML
    private TextField income_rest;
    @FXML
    private void income_rest(){
        if (!income_rest.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setAnotherIncome( income_rest.getText() );
        }
    }

    @FXML
    private TextField cost_eat;
    @FXML
    private void cost_eat(){
        if (!cost_eat.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setCostEat( cost_eat.getText() );
        }
    }

    @FXML
    private TextField cost_appartament;
    @FXML
    private void cost_appartament(){
        if (!cost_appartament.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setCostApartament( cost_appartament.getText() );
        }
    }

    @FXML
    private TextField cost_needed;
    @FXML
    private void cost_needed(){
        if (!cost_needed.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setCostNeeded( cost_needed.getText() );
        }
    }

    @FXML
    private TextField cost_useful;
    @FXML
    private void cost_useful(){
        if (!cost_useful.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setCostUseful( cost_useful.getText() );
        }
    }

    @FXML
    private TextField cost_detrimental;
    @FXML
    private void cost_detrimental(){
        if (!cost_detrimental.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setCostDetrimental( cost_detrimental.getText() );
        }
    }

    @FXML
    private TextArea notes_field;
    @FXML
    private void notes_field(){
        if (!notes_field.getText().equals("") ) {
            user.dataList.get(user.dataListIndex).setToolTip( notes_field.getText() );
        }
    }

    @FXML
    private ComboBox<String> box_promisser;

    @FXML
    private void remove_promiser(){
        if ( box_promisser.getValue() == null ) {System.out.println("null"); return;}
        //if (!box_promisser.getValue().equals("") && box_promisser.getValue() != null) {
            observableList.remove(box_promisser.getValue());
            box_promisser.setItems(observableList);
            if (observableList.size() > 0) box_promisser.setValue(observableList.get(observableList.size()-1));
            countPromiss.setText("");
        //}
    }

    @FXML
    //javafx.event.ActionEvent event
    private void add_promiser(){
        //Проверим чтоб не добавить пустую строку и совпадающих имен
        if (!countPromiss.getText().equals("") && !listSameString(observableList,countPromiss.getText())){
            observableList.add(countPromiss.getText());
            box_promisser.setItems(observableList);
            box_promisser.setValue(countPromiss.getText());
            countPromiss.setText("");
        }
    }

    @FXML
    private TextField countPromiss;

    @FXML
    void initialize() {
        box_promisser.setEditable(false);//делаем ComboBox изменяемым
        date_field.setEditable(false);
        //Обработка выбора даты, создание нового обьекта(если он еще не создан) по дате и добавление его в список
        //Ставим текущую дату
        date_field.setValue(LocalDate.now());
        user.add(new AppData( dateToString(date_field) ));
        if ( user.wasCreated ) {
            setNullFields();
        }else {
            loadFields();
        }
    }

    private void setNullFields(){
        income_main.setText("");
        income_aditional.setText("");
        income_rest.setText("");
        cost_eat.setText("");
        cost_appartament.setText("");
        cost_needed.setText("");
        cost_useful.setText("");
        cost_detrimental.setText("");
        notes_field.setText("");
    }

    private void loadFields(){
        income_main.setText(user.dataList.get(user.dataListIndex).getMainIncome());
        income_aditional.setText(user.dataList.get(user.dataListIndex).getAdvanceIncome());
        income_rest.setText(user.dataList.get(user.dataListIndex).getAnotherIncome());
        cost_eat.setText(user.dataList.get(user.dataListIndex).getCostEat());
        cost_appartament.setText(user.dataList.get(user.dataListIndex).getCostApartament());
        cost_needed.setText(user.dataList.get(user.dataListIndex).getCostNeeded());
        cost_useful.setText(user.dataList.get(user.dataListIndex).getCostUseful());
        cost_detrimental.setText(user.dataList.get(user.dataListIndex).getCostDetrimental());
        notes_field.setText(user.dataList.get(user.dataListIndex).getToolTip());
    }
    //Проверка на схожесть строк в списке должников
    //--param Список с именами
    //--param Строка с новим "пользователем"
    private boolean listSameString(ObservableList list, String promisser){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(promisser)){Library.error("Такое имя уже существует"); return true;}
        }
        return false;
    }
    //Ковертация даты в строку
    //--param Обьект DataPicker
    private String dateToString(DatePicker dp){
        return dp.getValue().toString();
    }
}

