package view.OverviewPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OverviewPane extends GridPane {

    private TextArea profile;
    private TextArea selected;
    private TextArea reserve;
    private Button btnSave;
    private Label lblTest;

    public OverviewPane(){

        this.setVgap(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);



        profile = new TextArea("Student profile will appear here when created...");
        selected = new TextArea("Selected modules will show here when selected...");
        reserve = new TextArea("Reserve modules will show here when selected...");
        btnSave = new Button("Save overview");

        profile.setPrefSize(600, 100);
        profile.setEditable(false);

        selected.setPrefSize(300, 200);
        selected.setEditable(false);

        reserve.setPrefSize(300, 200);
        reserve.setEditable(false);

        VBox vbox = new VBox(profile);
        HBox hbox = new HBox(selected, reserve);
        HBox hboxbtn = new HBox(btnSave);

        vbox.setAlignment(Pos.CENTER);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(40);

        hboxbtn.setAlignment(Pos.CENTER);

        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        profile.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        selected.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        reserve.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        GridPane.setVgrow(vbox, Priority.ALWAYS);
        GridPane.setHgrow(vbox, Priority.ALWAYS);
        GridPane.setVgrow(profile, Priority.ALWAYS);
        GridPane.setHgrow(profile, Priority.ALWAYS);

        GridPane.setVgrow(hbox, Priority.ALWAYS);
        GridPane.setHgrow(hbox, Priority.ALWAYS);
        GridPane.setVgrow(selected, Priority.ALWAYS);
        GridPane.setHgrow(selected, Priority.ALWAYS);
        GridPane.setVgrow(reserve, Priority.ALWAYS);
        GridPane.setHgrow(reserve, Priority.ALWAYS);





        gridLinesVisibleProperty().set(true);

        this.add(vbox, 0, 1);
        this.add(hbox, 0, 2);
        this.add(hboxbtn, 0, 3);
    }
}
