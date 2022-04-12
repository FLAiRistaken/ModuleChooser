package view.SelectModulesPane;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ButtonsPane extends HBox {

    private Button add, remove, reset, submit;

    public ButtonsPane() {
        this.setSpacing(15);

        add = new Button("Add");
        remove = new Button("Remove");
        reset = new Button("Reset");
        submit = new Button("Submit");

        add.setDisable(true);
        submit.setDisable(true);


        this.getChildren().addAll(add, remove, reset, submit);

        for (Node n : this.getChildren()) {
            ((Button) n).setPrefSize(120, 20);
        }
    }
}
