package view.SelectModulesPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ListViewPane<Module> extends VBox {

    private ListView<Module> listViewBox;
    private Label lblList;



    public ListViewPane(ObservableList<Module> list){
        this.setPadding(new Insets(15));
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        listViewBox = new ListView<Module>(list);
        lblList = new Label();

        listViewBox.setPrefSize(450, 175);
        listViewBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.getChildren().addAll(lblList, listViewBox);
        VBox.setVgrow(listViewBox, Priority.ALWAYS);
    }

    public Label getLblList(){
        return lblList;
    }

    public ListView<Module> getListView(){
        return listViewBox;
    }

}
