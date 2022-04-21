package view.SelectModulesPane;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ButtonsPane extends HBox {

    private Button add, remove;
    private Label lblButton;

    public ButtonsPane() {
        this.setSpacing(15);
        this.setPadding(new Insets(10));

        lblButton = new Label();
        add = new Button("Add");
        remove = new Button("Remove");


       // add.setDisable(true);
       // submit.setDisable(true);
        add.setPrefSize(70, 20);
        remove.setPrefSize(70, 20);


        this.getChildren().addAll(lblButton, add, remove);


    }

    public Label getLblButton() {
        return lblButton;
    }

    public Button getAdd(){
        return add;
    }
    public Button getRemove() {
        return remove;
    }
}
