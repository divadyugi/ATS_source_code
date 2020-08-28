package ButtonFunctions.ReportFunctions;

import Entities.Sale;
import Entities.SaleReport;

import org.apache.poi.ss.usermodel.Row;


public abstract class ReportGeneration {

    //TODO: implement abstract methods to all of the sale report generations


    public abstract void writeBlank(SaleReport report, Row row);

    public abstract  void writeCash(SaleReport report, Row row);

    public abstract void writeCard(SaleReport report, Row row);

    public abstract void writeTotal(SaleReport report, Row row);

    public abstract void writeCommission(SaleReport report, Row row);

}
