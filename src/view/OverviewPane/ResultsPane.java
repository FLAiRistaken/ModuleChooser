package view.OverviewPane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;

public class ResultsPane extends HBox {

    private TextArea results;

    public ResultsPane(){
        results = new TextArea("test");
        results.setId("results");
        results.setPadding(new Insets(0));

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(results);
    }

    public TextArea getResultsPane(){
        return results;
    }
}
