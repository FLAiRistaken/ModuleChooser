package view.ReservePane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public ReservePane(){

        this.setPrefSize(800, 500);
        this.setPadding(new Insets(20));

        unTerm1 = new ListViewPane();
        resTerm1 = new ListViewPane();

        unTerm1.getLblList().setText("Unselected Term 1 modules");
        resTerm1.getLblList().setText("Reserved Term 1 modules");

        HBox term1Hbox = new HBox(unTerm1, resTerm1);
        term1Hbox.setSpacing(10);
        term1Hbox.setPadding(new Insets(20));
        term1Hbox.setAlignment(Pos.CENTER);

        btnTerm1 = new ButtonsPane();
        btnTerm1.getLblButton().setText("Reserve 30 credits worth of term 1 modules");
        btnTerm1.setPadding(new Insets(20));
        btnTerm1.setAlignment(Pos.CENTER);

        VBox term1Vbox = new VBox(term1Hbox, btnTerm1);

        term1Pane = new TitledPane("Term 1 modules", term1Vbox);

        unTerm2 = new ListViewPane();
        resTerm2 = new ListViewPane();

        unTerm2.getLblList().setText("Unselected Term 2 modules");
        resTerm2.getLblList().setText("Reserved Term 2 modules");

        HBox term2Hbox = new HBox(unTerm2, resTerm2);
        term2Hbox.setSpacing(10);
        term2Hbox.setPadding(new Insets(20));
        term2Hbox.setAlignment(Pos.CENTER);

        btnTerm2 = new ButtonsPane();
        btnTerm2.getLblButton().setText("Reserve 30 credits worth of term 2 modules");
        btnTerm2.setPadding(new Insets(20));
        btnTerm2.setAlignment(Pos.CENTER);

        VBox term2Vbox = new VBox(term2Hbox, btnTerm2);

        term2Pane = new TitledPane("Term 2 modules", term2Vbox);

        this.getPanes().addAll(term1Pane, term2Pane);
        this.setExpandedPane(term1Pane);


    }
}
