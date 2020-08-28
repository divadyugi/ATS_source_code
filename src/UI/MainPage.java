package UI;

import ButtonFunctions.MaintenanceFunctions.Commission.CommissionFunctions;
import ButtonFunctions.MaintenanceFunctions.CustomerFunction.AddCustomer;
import ButtonFunctions.MaintenanceFunctions.CustomerFunction.CustomerFunctions;
import ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance.Backup;
import ButtonFunctions.MaintenanceFunctions.DatabaseMaintenance.Restore;
import ButtonFunctions.MaintenanceFunctions.Discount.DiscountPlans;
import ButtonFunctions.MaintenanceFunctions.Employees.EmployeeFunctions;
import ButtonFunctions.MaintenanceFunctions.ExchangeRate.ExchangeRateFunctions;
import ButtonFunctions.MaintenanceFunctions.TravelAgent.AgentFunctions;
import ButtonFunctions.ReportFunctions.*;
import ButtonFunctions.SearchFunction;
import ButtonFunctions.StockFunctions.AllocateFunction;
import ButtonFunctions.StockFunctions.OrderFunction;
import ButtonFunctions.StockFunctions.SoldBlanks;
import Database.SalesDatabaseFunction;
import Entities.Customer;
import Entities.Employee;
import Entities.Sale;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class MainPage {

    private static Login login;

    public static Employee profile;

    private static List<Sale> latePayments;

    private static Stage stage;

    BorderPane layout;

    VBox left;

    HBox top;

    Scene scene;

    private VBox center;

    Button employee;
    Button customers;
    Button discountPlans;
    Button commission;
    Button exchangeRates;
    Button backup;
    Button restore;
    Button agent;

    public static Login getLogin() {
        return login;
    }

    public static void setLogin(Login login) {
        MainPage.login = login;
    }

    //creates the buttons for the maintenance option
    public  void createMaintenancePage(){
        layout.setCenter(center);
        center.getChildren().clear();

        employee = new Button("Employee");
        employee.setOnAction(e->{
                if(!profile.getRole().equals("Travel advisor")) {
                EmployeeFunctions.createTable(this);
                System.out.println(profile.getRole());
            }else{
                    AlertWindow.showInformationAlert("No access","You don't have access to this function");
                }
        });
        customers = new Button("Customer");
        customers.setOnAction(e->{
            if(!profile.getRole().equals("Travel advisor")){
                CustomerFunctions.createTable(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });

        discountPlans = new Button("Discount plans");
        discountPlans.setOnAction(e-> {
            if(profile.getRole().equals("Office Manager")) {
            DiscountPlans.createTable(this);
        }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });

        commission = new Button("Commission");

            commission.setOnAction(e-> {
                        if(profile.getRole().equals("Office Manager")) {
                            CommissionFunctions.createTable(this);
                        }else{
                            AlertWindow.showInformationAlert("No access","You don't have access to this function");
                        }
            });

        exchangeRates = new Button("Exchange rates");

            exchangeRates.setOnAction(e-> {
                        if(!profile.getRole().equals("Travel advisor")) {
                            ExchangeRateFunctions.createTable(this);
                        }else{
                            AlertWindow.showInformationAlert("No access","You don't have access to this function");
                        }
                        });

        backup = new Button("Backup");
            backup.setOnAction(e-> {
                if(!profile.getRole().equals("Travel advisor")) {
                    Backup.createPage(this);
                }else{
                    AlertWindow.showInformationAlert("No access","You don't have access to this function");
                }
            });

        restore = new Button("Restore");

            restore.setOnAction(e-> {
                if(!profile.getRole().equals("Travel advisor")){
                Restore.createPage(this);
            }
        });
        agent = new Button("Travel agent");
        agent.setOnAction(e-> {
                if(profile.getRole().equals("Office Manager")) {
            AgentFunctions.createTable(this);
        }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
    });
        //backup.setOnAction(e-> BackupFunction.createBackUpFunction(center,this));

        center.getChildren().addAll(employee,customers,discountPlans,commission,exchangeRates,backup,restore,agent);

    }

    Button blanks;
    Button order;
    Button allocate;
    Button reAllocate;
    Button soldBlanks;
    //craets the buttons for the stock page option
    public void createStockPage(){
        layout.setCenter(center);
        center.getChildren().clear();
        blanks = new Button("Blanks");
        //this is for everyone, except system admin
        blanks.setOnAction(e-> {
            SearchFunction.createSearchWindow(this);
        });
        order = new Button("Add blanks");
        //this is for office managers

            order.setOnAction(e -> {
                if(!(profile.getRole().equals("Travel advisor"))) {
                    OrderFunction.orderBlanks(this);
                }else{
                    AlertWindow.showInformationAlert("No access","You don't have access to this function");
                }
            });

        allocate = new Button("Allocate");
        //this is for office manager/system admin

        allocate.setOnAction(e-> {
            if(!profile.getRole().equals("Travel advisor")) {
                AllocateFunction.allocateBlanks(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        reAllocate = new Button("Reallocate");
        //this is for office manager/system admin

            reAllocate.setOnAction(e-> {
                if(!profile.getRole().equals("Travel advisor")) {
                    SearchFunction.createEmployeeSearch(this);
                }else{
                    AlertWindow.showInformationAlert("No access","You don't have access to this function");
                }
            });

        soldBlanks = new Button("Sold blanks");
        soldBlanks.setOnAction(e->{
            SoldBlanks.salesTable(this);
        });

        center.getChildren().addAll(blanks,order,allocate,reAllocate, soldBlanks);
    }

    Button existingCustomer;
    Button newCustomer;
    //creates the buttons for the sales option
    public void createSalesPage(){
        layout.setCenter(center);
        center.getChildren().clear();
        existingCustomer = new Button("Existing");
        existingCustomer.setOnAction(e->{
            if(profile.getRole().equals("Travel advisor")) {
                SearchFunction.createCustomerSearch(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        newCustomer = new Button("New cusomter");
        newCustomer.setOnAction(e->{
            if(profile.getRole().equals("Travel advisor")) {
                AddCustomer.addCustomer(this, "Sale");
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });

        center.getChildren().addAll(existingCustomer,newCustomer);
    }

    Button ticketReport;
    Button individualInterlineReport;
    Button interlineReport;
    Button individualDomesticReport;
    Button domesticReport;
    //creates the buttons for reports option
    public void createReportsPage(){
        layout.setCenter(center);
        center.getChildren().clear();
        ticketReport = new Button("Stock Turnover Report");
        ticketReport.setOnAction(e->{
            if(!profile.getRole().equals("Travel advisor")) {
                TicketReportFunctions.createTicketReportsPage(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        individualInterlineReport = new Button("Individual interline report");
        individualInterlineReport.setOnAction(e->{
            if(!profile.getRole().equals("System Administrator")) {
                IndividualInterlineReportFunction.createIndividualInterlineReportFunction(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        interlineReport = new Button("Interline sales report");
        interlineReport.setOnAction(e-> {
            if(profile.getRole().equals("Office Manager")) {
                InterlineReportFunction.createInterlineReport(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        individualDomesticReport = new Button("Individual domestic report");
        individualDomesticReport.setOnAction(e-> {
            if(!profile.getRole().equals("System Administrator")) {
                IndividualDomesticReportFunction.createIndividualDomesticReport(this);
            }else{
                AlertWindow.showInformationAlert("No access","You don't have access to this function");
            }
        });
        domesticReport = new Button("Domestic sales report");
        DomesticReportFunction domesticReportFunction = new DomesticReportFunction();
        domesticReport.setOnAction(e->{
            if(profile.getRole().equals("Office Manager")) {
                domesticReportFunction.createDomesticReport(this);
            }
        });
        center.getChildren().addAll(ticketReport,individualInterlineReport,interlineReport,individualDomesticReport,domesticReport);
    }

    //creates pop-up windwos for each of the late payments when an office manager logs in
    public void latePaymentNotification(Sale sale){
         stage = new Stage();
        stage.setTitle("Late payments");
        BorderPane layout = new BorderPane();
        Customer customer = SalesDatabaseFunction.getCustomer(sale.getCustomerID());
        VBox top = new VBox();
        Label text = new Label("Customer: "+ customer.getFirstName()+" "+customer.getLastName()+" has a late payment for ticket number: "+sale.getBlankID()+"\n" +
                "The sale was made on: "+sale.getSalesDate() +" by employee: "+sale.getEmployeeID());
        top.getChildren().add(text);
        layout.setTop(top);

        GridPane center = CreateLayout.createGridPane();
        center.setAlignment(Pos.CENTER);

        DatePicker paidDate = new DatePicker();
        paidDate.setPromptText("Date paid");

        Button paid = new Button("Paid");
        paid.setOnAction(e->{
            LocalDate localDate = paidDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date payDate = Date.from(instant);
            Calendar c = Calendar.getInstance();
            c.setTime(sale.getSalesDate());
            c.add(Calendar.DAY_OF_MONTH, 30);
            Date saleDate = c.getTime();
            if(payDate.compareTo(saleDate)>0) {
                AlertWindow.showInformationAlert("Wrong date","A late payment can not be paid 30 days after the original sales was made");
            }else {
                sale.setLatePaymentDate(payDate);
                SalesDatabaseFunction.changePaymentStatus(sale,"Paid");
                stage.close();
            }

        });

        Button notPaid = new Button("Not paid");
        notPaid.setOnAction(e->{
            SalesDatabaseFunction.changePaymentStatus(sale,"Failed To Pay");
            stage.close();
        });

        center.add(paidDate,0,1,2,1);
        center.add(notPaid,0,2,1,1);
        center.add(paid,1,2,1,1);

        layout.setCenter(center);

        Scene scene = new Scene(layout, 450 , 300);
        stage.setScene(scene);
        stage.show();
    }
    //creates the main page
    public Scene createMainPage(Login login, Employee profile){
        this.profile = profile;

        this.login = login;




        layout = new BorderPane();

        left = new VBox();
        left.setPadding(new Insets(0,0,0,10));
        //Change center buttons to reversed colour

        //navigation buttons on the left side of the screen.
        Button report = new Button("Report");
        report.setOnAction(e->createReportsPage());
        Button sell = new Button ("Sell");
        sell.setOnAction(e-> createSalesPage());
        Button stock = new Button("Stock");
        stock.setOnAction(e -> createStockPage());
        Button maintenance = new Button("Maintenance");
        maintenance.setOnAction(e->createMaintenancePage());
        Button logout = new Button("Logout");
        logout.setOnAction(e->{
            login.setIDfieldNull();
            login.setPasswordFieldNull();
            login.getStage().setScene(login.getScenet());
        });

        left.setSpacing(10);
        left.getChildren().addAll(report,sell,stock,maintenance,logout);
        left.setAlignment(Pos.TOP_LEFT);

        layout.setLeft(left);

        //top layer, welcome message
        top = new HBox();
        Text welcomeText = new Text("Welcome "+profile.getFirstName()+" to ATS");
        top.getChildren().add(welcomeText);
        top.setAlignment(Pos.CENTER);
        top.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        layout.setTop(top);

        //center screen, place that will change mainly
        center = new VBox();

        center.setAlignment(Pos.TOP_CENTER);
        center.setSpacing(10);

        layout.setCenter(center);

        layout.setMargin(top,new Insets(30));
        scene = new Scene(layout,350,500);
        scene.getStylesheets().addAll("Styles/SceneStyle.css","Styles/NavButtonStyle.css");

        if(profile.getRole().equals("Office Manager")) {
            ArrayList<Sale> sales = SalesDatabaseFunction.getLatePayments();
            for (Sale sale : sales) {
                latePaymentNotification(sale);
            }
        }

        return scene;

    }

    public VBox getCenter(){
        return center;
    }

    public Scene getScene(){
        return scene;
    }

    public BorderPane getLayout(){return layout;}

    public Employee getProfile(){return profile;}

    public Stage getStage(){
        return stage;
    }
}
