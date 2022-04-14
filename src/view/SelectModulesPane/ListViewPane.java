package view.SelectModulesPane;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ListViewPane<Module> extends VBox {

    private ListView listViewBox;
    private Label lblList;

    public ListViewPane(ObservableList list){
        this.setPadding(new Insets(15));

        listViewBox = new ListView(list);
        lblList = new Label();

        listViewBox.setPrefSize(450, 175);
        listViewBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.getChildren().addAll(lblList, listViewBox);
    }

    public Label getLblList(){
        return lblList;
    }
}
