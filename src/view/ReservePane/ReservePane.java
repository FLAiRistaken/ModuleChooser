package view.ReservePane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;

public class ReservePane extends Accordion {
    private ListViewPane unTerm1;
    private ListViewPane resTerm1;
    private ListViewPane unTerm2;
    private ListViewPane resTerm2;
    private ButtonsPane btnTerm1;
    private ButtonsPane btnTerm2;
    private TitledPane term1Pane;
    private TitledPane term2Pane;
    private Accordion accordion;

    private ObservableList<Module> listUnTerm1, listUnTerm2, listResTerm1, listResTerm2;

    public ReservePane(){

        this.setPrefSize(800, 500);
        this.setPadding(new Insets(20));

        listUnTerm1 = FXCollections.observableArrayList();
        listResTerm1 = FXCollections.observableArrayList();
        unTerm1 = new ListViewPane(listUnTerm1);
        resTerm1 = new ListViewPane(listResTerm2);


        unTerm1.getLblList().setText("Unselected Term 1 modules");
        resTerm1.getLblList().setText("Reserved Term 1 modules");

       /* HBox term1Hbox = new HBox(unTerm1, resTerm1);
        term1Hbox.setSpacing(10);
        term1Hbox.setPadding(new Insets(20));
        term1Hbox.setAlignment(Pos.CENTER);*/
        GridPane term1GridPane = new GridPane();

        term1GridPane.add(unTerm1, 0, 1);
        term1GridPane.add(resTerm1, 1, 1);

        term1GridPane.setPadding(new Insets(20));
        term1GridPane.setAlignment(Pos.CENTER);

        term1GridPane.setGridLinesVisible(true);

        unTerm1.prefWidthProperty().bind(this.widthProperty());
        resTerm1.prefWidthProperty().bind(this.widthProperty());
        unTerm1.prefHeightProperty().bind(this.heightProperty());
        resTerm1.prefHeightProperty().bind(this.heightProperty());

        btnTerm1 = new ButtonsPane();
        btnTerm1.getLblButton().setText("Reserve 30 credits worth of term 1 modules");
        btnTerm1.setPadding(new Insets(20));
        btnTerm1.setAlignment(Pos.CENTER);

        VBox term1Vbox = new VBox(term1GridPane, btnTerm1);

        term1Pane = new TitledPane("Term 1 modules", term1Vbox);

        listUnTerm2 = FXCollections.observableArrayList();
        listResTerm2 = FXCollections.observableArrayList();
        unTerm2 = new ListViewPane(listUnTerm2);
        resTerm2 = new ListViewPane(listResTerm2);

        unTerm2.getLblList().setText("Unselected Term 2 modules");
        resTerm2.getLblList().setText("Reserved Term 2 modules");

        HBox term2Hbox = new HBox(unTerm2, resTerm2);
        term2Hbox.setSpacing(10);
        term2Hbox.setPadding(new Insets(20));
        term2Hbox.setAlignment(Pos.CENTER);

        unTerm2.prefWidthProperty().bind(this.widthProperty());
        resTerm2.prefWidthProperty().bind(this.widthProperty());
        unTerm2.prefHeightProperty().bind(term2Hbox.heightProperty());
        resTerm2.prefHeightProperty().bind(term2Hbox.heightProperty());

        btnTerm2 = new ButtonsPane();
        btnTerm2.getLblButton().setText("Reserve 30 credits worth of term 2 modules");
        btnTerm2.setPadding(new Insets(20));
        btnTerm2.setAlignment(Pos.CENTER);

        VBox term2Vbox = new VBox(term2Hbox, btnTerm2);

        term2Pane = new TitledPane("Term 2 modules", term2Vbox);

        this.getPanes().addAll(term1Pane, term2Pane);
        this.setExpandedPane(term1Pane);



    }

    public ObservableList<Module> getListUnTerm1(){
        return listUnTerm1;
    }
    public ObservableList<Module> getListUnTerm2(){
        return listUnTerm2;
    }
    public ObservableList<Module> getListResTerm1() {
        return listResTerm1;
    }
    public ObservableList<Module> getListResTerm2() {
        return listResTerm2;
    }
}
