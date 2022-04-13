package view.ReservePane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ButtonsPane extends HBox {

    private Button add, remove, confirm;
    private Label lblButton;

    public ButtonsPane() {
        this.setSpacing(15);

        lblButton = new Label();
        add = new Button("Add");
        remove = new Button("Remove");
        confirm = new Button("Confirm");

        confirm.setDisable(true);
        add.setPrefSize(70, 20);
        remove.setPrefSize(70, 20);
        confirm.setPrefSize(70, 20);


        this.getChildren().addAll(lblButton, add, remove, confirm);

       /* for (Node n : this.getChildren()) {
            ((Button) n).setPrefSize(70, 20);
        }*/
    }

    public Label getLblButton() {
        return lblButton;
    }
}
