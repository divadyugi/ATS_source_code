package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class CreateLayout {

    //creates a basic layout to be used for most centerpanes
    //TODO: implement multiple layouts, to use with different windows
    public static GridPane createGridPane(){
        GridPane center = new GridPane();

        center.setPadding(new Insets(10,10,10,10));
        center.setVgap(8);
        center.setHgap(10);
        center.setAlignment(Pos.TOP_RIGHT);

        return center;
    }
}
