package view.OverviewPane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class OverviewButtonPane extends HBox {
    private Button saveBtn;

    public OverviewButtonPane() {
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setSpacing(15);

        saveBtn = new Button("Save Overview");
        saveBtn.setDisable(true);

        this.getChildren().add(saveBtn);
    }
}
