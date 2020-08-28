package ButtonFunctions.MaintenanceFunctions.TravelAgent;

import Database.TravelAgentDatabaseFunctions;
import Entities.Agency;
import UI.AlertWindow;
import UI.CreateLayout;
import UI.MainPage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddAgency {


    public static void addAgency(MainPage ui, String type, Agency agency){
        GridPane center = CreateLayout.createGridPane();

        TextField agencyID = new TextField();
        agencyID.setPromptText("Agency ID:");

        TextField agencyName = new TextField();
        agencyName.setPromptText("Agency name");

        TextField address = new TextField();
        address.setPromptText("Agency address: ");

        Button add = new Button("Add");
        add.setOnAction(e->{
            if(agencyName.getText().isEmpty()||address.getText().isEmpty()){
                AlertWindow.showInformationAlert("Missing details","Please fill out all the required fields");
                return;
            }
            Agency newAgency = new Agency(agencyName.getText(),address.getText());
            if(agencyID.getText().isEmpty()){
                newAgency.setID(0);
            }else{
                newAgency.setID(Integer.parseInt(agencyID.getText()));
            }
            TravelAgentDatabaseFunctions.addAgency(newAgency);
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
            AgentFunctions.createTable(ui);
        });

        Button update = new Button("Update");
        update.setOnAction(e->{
            if(agencyName.getText().isEmpty()||address.getText().isEmpty()){
                AlertWindow.showInformationAlert("Missing details","Please fill out all the required fields");
                return;
            }
                Agency newAgency = new Agency(agencyName.getText(), address.getText());
                newAgency.setID(Integer.parseInt(agencyID.getText()));
                TravelAgentDatabaseFunctions.updateAgency(agency, newAgency);
                ui.getLayout().setCenter(ui.getCenter());
                ui.createMaintenancePage();
                AgentFunctions.createTable(ui);
        });

        Button back = new Button("Back");
        back.setOnAction(e->{
            ui.getLayout().setCenter(ui.getCenter());
            ui.createMaintenancePage();
        });



        center.add(agencyID,0,1,2,1);
        center.add(agencyName,0,2,2,1);
        center.add(address,0,3,2,1);
        center.add(back,0,4,1,1);
        if(type.equals("Add")) {
            center.add(add, 1, 4, 1, 1);
        }else{
            center.add(update,1,4,1,1);
        }

        if(type.equals("Update")){
            agencyID.setText(String.valueOf(agency.getID()));
            agencyName.setText(agency.getName());
            address.setText(agency.getAddress());
            agencyID.setEditable(false);
        }

        ui.getLayout().setCenter(center);

    }

}
