package view.ReservePane;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ListViewPane<Module> extends VBox {

    private ListView<Module> listViewBox;
    private Label lblList;

    public ListViewPane(ObservableList<Module> list){
        this.setPadding(new Insets(15));

        listViewBox = new ListView<Module>(list);
        lblList = new Label();

        listViewBox.setPrefSize(300, 200);

        this.getChildren().addAll(lblList, listViewBox);
    }

    public Label getLblList(){
        return lblList;
    }
}
