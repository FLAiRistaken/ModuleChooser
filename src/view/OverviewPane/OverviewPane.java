package view.OverviewPane;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;


public class OverviewPane extends GridPane {

        private ResultsPane rp;
        private OverviewButtonPane bp;

        public OverviewPane(){

                rp = new ResultsPane();
                bp = new OverviewButtonPane();

                VBox vbox = new VBox(rp, bp);

                this.getChildren().add(vbox);

        }

        public ResultsPane getResultsPane(){
                return rp;
        }

        public OverviewButtonPane getButtonPane(){
                return bp;
        }

}
