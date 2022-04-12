package view.OverviewPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OverviewPane extends GridPane {

    private TextArea profile;
    private TextArea selected;
    private TextArea reserve;
    private Button btnSave;

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

        this.add(vbox, 0, 1);
        this.add(hbox, 0, 2);
        this.add(hboxbtn, 0, 3);



    }
}
