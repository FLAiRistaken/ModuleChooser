package view.SelectModulesPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.StudentProfile;

import javax.security.auth.callback.LanguageCallback;

public class SelectModulesPane extends GridPane {

    private ListViewPane<Module> unTerm1, unTerm2, selYear, selTerm1, selTerm2;
    private ButtonsPane btnTerm1, btnTerm2;
    private Button btnReset, btnSubmit;
    private Label lblT1Credits, lblT2Credits;
    private TextField txtT1Credits, txtT2Credits;

    private ObservableList<Module> unModTerm1, unModTerm2, selModYear, selModTerm1, selModTerm2;

    private int maxCredits;

    public SelectModulesPane(){

        this.setPadding(new Insets(20));

        unModTerm1 = FXCollections.observableArrayList();
        unModTerm2 = FXCollections.observableArrayList();

        unTerm1 = new ListViewPane(unModTerm1);
        unTerm2 = new ListViewPane(unModTerm2);
        unTerm1.getListView().getSelectionModel();

        btnTerm1 = new ButtonsPane();
        btnTerm2 = new ButtonsPane();

        lblT1Credits = new Label("Current term 1 credits: ");
        txtT1Credits = new TextField("0");

        VBox leftTopVbox = new VBox(unTerm1, btnTerm1);
        VBox leftBottomVbox = new VBox(unTerm2, btnTerm2);

        leftTopVbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        leftBottomVbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        leftTopVbox.setAlignment(Pos.TOP_CENTER);
        leftBottomVbox.setAlignment(Pos.BOTTOM_CENTER);

        unTerm1.getLblList().setText("Unselected Term 1 modules");
        unTerm2.getLblList().setText("Unselected Term 2 modules");
        btnTerm1.getLblButton().setText("Term 1");
        btnTerm2.getLblButton().setText("Term 2");
        btnTerm1.setAlignment(Pos.CENTER);
        btnTerm2.setAlignment(Pos.CENTER);

        unTerm1.prefWidthProperty().bind(this.widthProperty());
        unTerm2.prefWidthProperty().bind(this.widthProperty());
        unTerm1.prefHeightProperty().bind(this.heightProperty());
        unTerm2.prefHeightProperty().bind(this.heightProperty());
        txtT1Credits.setPrefSize(60, 20);

        VBox leftVbox = new VBox(leftTopVbox, leftBottomVbox);
        leftVbox.setAlignment(Pos.CENTER_LEFT);

        //Right side
        selModYear = FXCollections.observableArrayList();
        selModTerm1 = FXCollections.observableArrayList();
        selModTerm2 = FXCollections.observableArrayList();

        selYear = new ListViewPane(selModYear);
        selTerm1 = new ListViewPane(selModTerm1);
        selTerm2 = new ListViewPane(selModTerm2);
        lblT2Credits = new Label("Current term 2 credits: ");
        txtT2Credits = new TextField("0");

        selYear.prefWidthProperty().bind(this.widthProperty());
        selYear.prefHeightProperty().bind(this.heightProperty());
        selYear.getLblList().setText("Selected Year Long modules");
        selTerm1.getLblList().setText("Selected Term 1 modules");
        selTerm2.getLblList().setText("Selected Term 2 modules");
        selTerm1.prefWidthProperty().bind(this.widthProperty());
        selTerm1.prefHeightProperty().bind(this.heightProperty());
        selTerm2.prefWidthProperty().bind(this.widthProperty());
        selTerm2.prefHeightProperty().bind(this.heightProperty());
        txtT2Credits.setPrefSize(60, 20);


        VBox rightVbox = new VBox(selYear, selTerm1, selTerm2);

        rightVbox.setAlignment(Pos.CENTER_RIGHT);

        gridLinesVisibleProperty().set(true);

        //Bottom
        HBox t1creditsHbox = new HBox(lblT1Credits, txtT1Credits);
        HBox t2creditsHbox = new HBox(lblT2Credits, txtT2Credits);

        t1creditsHbox.setAlignment(Pos.CENTER);
        t2creditsHbox.setAlignment(Pos.CENTER);

        btnReset = new Button("Reset");
        btnSubmit = new Button("Submit");

        btnReset.setPrefSize(70, 20);
        btnSubmit.setPrefSize(70, 20);

        HBox hboxBtnReset = new HBox(btnReset);
        hboxBtnReset.setAlignment(Pos.CENTER_RIGHT);
        hboxBtnReset.setPadding(new Insets(20, 20, 0, 20));

        HBox hboxBtnSubmit = new HBox(btnSubmit);
        hboxBtnSubmit.setAlignment(Pos.CENTER_LEFT);
        hboxBtnSubmit.setPadding(new Insets(20, 20, 0, 20));


        this.add(leftVbox, 0, 1);
        this.add(rightVbox, 1, 1);
        this.add(t1creditsHbox, 0, 2);
        this.add(t2creditsHbox, 1, 2);
        this.add(hboxBtnReset, 0, 3);
        this.add(hboxBtnSubmit, 1, 3);

    }
}
