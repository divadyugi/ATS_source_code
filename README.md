# ATS_source_code
Source code for City, University of London's Team Project module.

# Setup
Make sure to have mySql installed, and setup with a connection

The libraries used were not included, the program uses 3 libraries:
poi 4.1.2: https://poi.apache.org/download.html<br/>
javafx 11.0.2: https://gluonhq.com/products/javafx/<br/>
mysql-connector 8.0.19: https://downloads.mysql.com/archives/c-j/<br/>

To run the application, make sure you add the correct path to these libraries, and make sure your run settings include:<br/>
--module-path PATH-TO-JAVAFX-LIB --add-modules javafx.controls,javafx.fxml

# Database Setup
Once the application is running<br/>

![First page of the application with an arrow pointing at the setup database button](https://github.com/divadyugi/ATS_source_code/instruction.png)<br/>
Click the "Setup Database" button, and provide the correct details for the connection you want to use with mySql.<br/>

For the "MySql Dump location" select your mysql.exe file, default path: C:\Program Files\MySQL\MySQL Workbench 8.0 CE\mysql.exe<br/>
After that, you will be provided with a login name and password which you can use to login to the system.
