package view.SelectModulesPane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreditPane extends GridPane {

    private Label lblCredits;
    private TextField txtCredits;

    private int maxcreds;

    public CreditPane(){

        lblCredits = new Label();
        txtCredits = new TextField("0");

        txtCredits.setEditable(false);
        txtCredits.setAlignment(Pos.CENTER);

        this.add(lblCredits, 0, 0);
        this.add(txtCredits, 1, 0);

    }
}
