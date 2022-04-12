package view.OverviewPane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;

public class ResultsPane extends HBox {

    private TextArea results;

    public ResultsPane(){
        results = new TextArea("test");
        results.setId("results");
        results.setPrefSize(500, 500);

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(results);
    }
}
