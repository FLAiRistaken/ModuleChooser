package view.ReservePane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ListViewPane extends VBox {

    private ListView listViewBox;
    private Label lblList;

    public ListViewPane(){
        this.setPadding(new Insets(15));

        listViewBox = new ListView();
        lblList = new Label();

        listViewBox.setPrefSize(300, 200);

        this.getChildren().addAll(lblList, listViewBox);
    }

    public Label getLblList(){
        return lblList;
    }
}
