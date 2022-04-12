package view.OverviewPane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;


public class OverviewPane extends GridPane {

        private ResultsPane profilepane;
        private ResultsPane selectedmodulespane;
        private ResultsPane reservemodulespane;
        private OverviewButtonPane bp;

        public OverviewPane(){


                profilepane = new ResultsPane();
                selectedmodulespane = new ResultsPane();
                reservemodulespane = new ResultsPane();
                bp = new OverviewButtonPane();

                profilepane.setAlignment(Pos.CENTER);
                profilepane.setPrefSize(600, 100);

             //   selectedmodulespane.setAlignment(Pos.CENTER);
                selectedmodulespane.setPrefSize(225, 200);

               // reservemodulespane.setAlignment(Pos.CENTER);
                reservemodulespane.setPrefSize(225, 200);

                bp.setAlignment(Pos.CENTER);

                VBox pvbox = new VBox(profilepane);
                HBox hbox = new HBox(selectedmodulespane, reservemodulespane);
                VBox vbox = new VBox(pvbox, hbox, bp);
                hbox.setAlignment(Pos.CENTER);
                hbox.setSpacing(30);
                vbox.setSpacing(40);
                vbox.setPadding(new Insets(20));

             /* VBox.setVgrow(profilepane, Priority.ALWAYS);
                HBox.setHgrow(profilepane, Priority.ALWAYS);
                VBox.setVgrow(selectedmodulespane, Priority.ALWAYS);
                HBox.setHgrow(selectedmodulespane, Priority.ALWAYS);
                VBox.setVgrow(reservemodulespane, Priority.ALWAYS);
                HBox.setHgrow(reservemodulespane, Priority.ALWAYS); */

                this.getChildren().add(vbox);

        }

        public ResultsPane getProfilePane(){
                return profilepane;
        }

        public ResultsPane getSelectedmodulesPane(){
                return selectedmodulespane;
        }

        public ResultsPane getReservemodulesPane(){
                return reservemodulespane;
        }

        public OverviewButtonPane getButtonPane(){
                return bp;
        }

}
