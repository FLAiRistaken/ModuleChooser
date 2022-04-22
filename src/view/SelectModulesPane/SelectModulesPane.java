package view.SelectModulesPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.Schedule;
import model.StudentProfile;

import javax.security.auth.callback.LanguageCallback;

public class SelectModulesPane extends GridPane {

    private ListViewPane<Module> unTerm1, unTerm2, selYear, selTerm1, selTerm2;
    private ButtonsPane btnTerm1, btnTerm2;
    private Button btnReset, btnSubmit;
    private Label lblT1Credits, lblT2Credits;
    private TextField txtT1Credits, txtT2Credits;
    private TextArea txtDebug;

    private ObservableList<Module> unModTerm1, unModTerm2, selModYear, selModTerm1, selModTerm2;

    private int term1Credits, term2Credits;

    public SelectModulesPane(){

        this.setPadding(new Insets(20));

        unModTerm1 = FXCollections.observableArrayList();
        unModTerm2 = FXCollections.observableArrayList();

        unTerm1 = new ListViewPane(unModTerm1);
        unTerm2 = new ListViewPane(unModTerm2);
        unTerm1.getListView().getSelectionModel();

        btnTerm1 = new ButtonsPane();
        btnTerm1.getAdd().setId("term1Add");
        btnTerm1.getRemove().setId("term1Rem");
        btnTerm2 = new ButtonsPane();
        btnTerm2.getAdd().setId("term2Add");
        btnTerm2.getRemove().setId("term2Rem");

        lblT1Credits = new Label("Current term 1 credits: ");
        txtT1Credits = new TextField("0");
        txtT1Credits.setEditable(false);

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
        txtT2Credits.setEditable(false);

        selYear.prefWidthProperty().bind(this.widthProperty());
        selYear.setPrefHeight(200);
        selYear.setMinHeight(100);
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
        btnReset.setId("btnReset");
        btnSubmit = new Button("Submit");
        btnSubmit.setId("btnSubmit");

        btnReset.setPrefSize(70, 20);
        btnSubmit.setPrefSize(70, 20);

        HBox hboxBtnReset = new HBox(btnReset);
        hboxBtnReset.setAlignment(Pos.CENTER_RIGHT);
        hboxBtnReset.setPadding(new Insets(20, 20, 0, 20));

        HBox hboxBtnSubmit = new HBox(btnSubmit);
        hboxBtnSubmit.setAlignment(Pos.CENTER_LEFT);
        hboxBtnSubmit.setPadding(new Insets(20, 20, 0, 20));

        txtDebug = new TextArea();


        this.add(leftVbox, 0, 1);
        this.add(rightVbox, 1, 1);
        this.add(t1creditsHbox, 0, 2);
        this.add(t2creditsHbox, 1, 2);
        this.add(hboxBtnReset, 0, 3);
        this.add(hboxBtnSubmit, 1, 3);

    }

    public ObservableList<Module> getUnModTerm1Contents(){
        return unModTerm1;
    }

    public ObservableList<Module> getUnModTerm2Contents(){
        return unModTerm2;
    }

    public ObservableList<Module> getSelModYearContents(){
        return selModYear;
    }

    public ObservableList<Module> getSelModTerm1Contents(){
        return selModTerm1;
    }

    public ObservableList<Module> getSelModTerm2Contents(){
        return selModTerm2;
    }

    public ListViewPane<Module> getUnTerm1() {
        return unTerm1;
    }
    public ListViewPane<Module> getUnTerm2() {
        return unTerm2;
    }
    public ListViewPane<Module> getSelTerm1() {
        return selTerm1;
    }
    public ListViewPane<Module> getSelTerm2() {
        return selTerm2;
    }
    public ListViewPane<Module> getSelYear() {
        return selYear;
    }

    public void loadModules(StudentProfile profile){
        for (Module m : profile.getStudentCourse().getAllModulesOnCourse()){

            if (m.getDelivery().equals(Schedule.TERM_1)){
                if(m.isMandatory() == false){
                    getUnModTerm1Contents().add(m);
                } else {
                    getSelModTerm1Contents().add(m);
                    term1Credits = term1Credits + m.getModuleCredits();
                    //txtT1Credits.setText(String.valueOf(term1Credits));
                }
            } else if (m.getDelivery().equals(Schedule.TERM_2)) {
                if (m.isMandatory() == false) {
                    getUnModTerm2Contents().add(m);
                } else {
                    getSelModTerm2Contents().add(m);
                    term2Credits = term2Credits + m.getModuleCredits();
                    //xtT2Credits.setText(String.valueOf(term2Credits));
                }
            } else {
                getSelModYearContents().add(m);
                term1Credits = term1Credits + (m.getModuleCredits()/2);
                term2Credits = term2Credits + (m.getModuleCredits()/2);
            }
        }
        txtT1Credits.setText(String.valueOf(term1Credits));
        txtT2Credits.setText(String.valueOf(term2Credits));
    }

    public TextArea getTxtDebug(){
        return txtDebug;
    }

    public TextField getTxtT1Credits(){
        return txtT1Credits;
    }
    public TextField getTxtT2Credits(){
        return txtT2Credits;
    }

    public int getTerm1Credits() {
        return term1Credits;
    }
    public int getTerm2Credits() {
        return term2Credits;
    }
    public void setTerm1Credits(int credits){
        term1Credits = credits;
        txtT1Credits.setText(String.valueOf(credits));
    }
    public void setTerm2Credits(int credits){
        term2Credits = credits;
        txtT2Credits.setText(String.valueOf(credits));
    }

    public Module getUnTerm1SelectedModule(){
       return getUnTerm1().getListView().getSelectionModel().getSelectedItem();
    }
    public Module getUnTerm2SelectedModule(){
        return getUnTerm2().getListView().getSelectionModel().getSelectedItem();
    }
    public Module getSelTerm1SelectedModule(){
        return getSelTerm1().getListView().getSelectionModel().getSelectedItem();
    }
    public Module getSelTerm2SelectedModule(){
        return getSelTerm2().getListView().getSelectionModel().getSelectedItem();
    }

    public ButtonsPane getBtnTerm1() {
        return btnTerm1;
    }

    public ButtonsPane getBtnTerm2() {
        return btnTerm2;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }


    public void clearSMP(){

    }

    public void addAddModuleHandler(EventHandler<ActionEvent> handler){
        btnTerm1.getAdd().setOnAction(handler);
        btnTerm2.getAdd().setOnAction(handler);
    }

    public void addRemoveModuleHandler(EventHandler<ActionEvent> handler){
        btnTerm1.getRemove().setOnAction(handler);
        btnTerm2.getRemove().setOnAction(handler);
    }
}
