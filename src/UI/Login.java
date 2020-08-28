package UI;

import ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance.MaintainDatabase;
import ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance.Restore;
import Database.LoginDatabaseFunction;
import Entities.Employee;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    private Stage stage;

    MainPage mainPage = new MainPage();
    private Scene scene;
    private TextField IDField;
    private TextField passwordField;

    public void start(final Stage stage) {

        this.stage = stage;

        BorderPane borderPane = new BorderPane();
        VBox layout = new VBox(10);
        Image logo = new Image("Images/logo2.png");
        ImageView logoImage = new ImageView(logo);

        IDField = new TextField();
        IDField.setPromptText("ID: ");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password: ");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e ->{

            //checks if the details provided are correct
            try {
                if (LoginDatabaseFunction.loginFunction(Integer.parseInt(IDField.getText()), passwordField.getText())) {
                    System.out.println("Logged in");
                    Employee employee = LoginDatabaseFunction.loggedInProfile(Integer.parseInt(IDField.getText()), passwordField.getText());
                    stage.setScene(mainPage.createMainPage(this,employee));
                }
            }catch(NumberFormatException ex){
                //if instead of an ID a username is typed in, it will give a pop-up error
                AlertWindow.showInformationAlert("No ID provided","Please enter the ID");
            }
            if(passwordField.getText().equals("")){
                //if the password field is left empty, it will provide an error message
                AlertWindow.showInformationAlert("No password", "Please enter a password");
            }
        });

        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(logoImage, IDField, passwordField, loginButton);
        borderPane.setCenter(layout);

        HBox bottom = new HBox();

        Button setup = new Button("Setup Database");
        setup.setOnAction(e->{
            MaintainDatabase.setupDatabase();
        });
        Button restore = new Button("Restore Database");
        restore.setOnAction(e-> Restore.createNewPage());
        bottom.getChildren().addAll(setup,restore);
        bottom.setSpacing(10);
        borderPane.setBottom(bottom);

        stage.setTitle("ATS Login");

        scene = new Scene(borderPane, 390, 350);
        stage.setScene(scene);
        stage.show();


        scene.getStylesheets().add("Styles/SceneStyle.css");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            //this action listeners sets it so that the login could be made by pressing ENTER, instead of pressing the login button
            @Override
            public void handle(KeyEvent e){
                if(e.getCode()== KeyCode.ENTER){
                    if(LoginDatabaseFunction.loginFunction(Integer.parseInt(IDField.getText()),passwordField.getText())){
                        System.out.println("Logged in");
                        Employee employee = LoginDatabaseFunction.loggedInProfile(Integer.parseInt(IDField.getText()), passwordField.getText());
                        stage.setScene(mainPage.createMainPage(getLogin(),employee));
                    }
                }
            }
        });
    }

        public Stage getStage(){
            return stage;
        }

        public Scene getScenet(){
            return scene;
        }

        public Login getLogin(){
            return this;
        }

        public void setIDfieldNull(){
            IDField.setText(null);
        }

        public void setPasswordFieldNull(){
            passwordField.setText(null);
        }
}
