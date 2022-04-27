package view.ReservePane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Module;
import model.Schedule;
import model.StudentProfile;

import java.util.ArrayList;
import java.util.Collection;

public class ReservePane extends Accordion {
    private ListViewPane<Module> unTerm1;
    private ListViewPane<Module> resTerm1;
    private ListViewPane<Module> unTerm2;
    private ListViewPane<Module> resTerm2;
    private ButtonsPane btnTerm1;
    private ButtonsPane btnTerm2;
    private TitledPane term1Pane;
    private TitledPane term2Pane;

    private ObservableList<Module> listUnTerm1, listUnTerm2, listResTerm1, listResTerm2;

    private int term1Credits, term2Credits;

    public ReservePane(){

        this.setPrefSize(800, 500);
        this.setPadding(new Insets(20));

        listUnTerm1 = FXCollections.observableArrayList();
        listResTerm1 = FXCollections.observableArrayList();
        unTerm1 = new ListViewPane<>(listUnTerm1);
        resTerm1 = new ListViewPane<>(listResTerm1);
        term1Credits = 30;


        unTerm1.getLblList().setText("Unselected Term 1 modules");
        resTerm1.getLblList().setText("Reserved Term 1 modules");

        GridPane term1GridPane = new GridPane();

        term1GridPane.add(unTerm1, 0, 1);
        term1GridPane.add(resTerm1, 1, 1);

        term1GridPane.setPadding(new Insets(20));
        term1GridPane.setAlignment(Pos.CENTER);


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
        unTerm2 = new ListViewPane<>(listUnTerm2);
        resTerm2 = new ListViewPane<>(listResTerm2);
        term2Credits = 30;

        unTerm2.getLblList().setText("Unselected Term 2 modules");
        resTerm2.getLblList().setText("Reserved Term 2 modules");

        GridPane term2GridPane = new GridPane();
        term2GridPane.add(unTerm2, 0, 1);
        term2GridPane.add(resTerm2, 1, 1);
        term2GridPane.setPadding(new Insets(20));
        term2GridPane.setAlignment(Pos.CENTER);

        unTerm2.prefWidthProperty().bind(this.widthProperty());
        resTerm2.prefWidthProperty().bind(this.widthProperty());
        unTerm2.prefHeightProperty().bind(term2GridPane.heightProperty());
        resTerm2.prefHeightProperty().bind(term2GridPane.heightProperty());

        btnTerm2 = new ButtonsPane();
        btnTerm2.getLblButton().setText("Reserve 30 credits worth of term 2 modules");
        btnTerm2.setPadding(new Insets(20));
        btnTerm2.setAlignment(Pos.CENTER);

        VBox term2Vbox = new VBox(term2GridPane, btnTerm2);

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

    public ListViewPane<Module> getUnTerm1() {
        return unTerm1;
    }
    public ListViewPane<Module> getUnTerm2() {
        return unTerm2;
    }
    public ListViewPane<Module> getResTerm1() {
        return resTerm1;
    }
    public ListViewPane<Module> getResTerm2() {
        return resTerm2;
    }

    public int getTerm1Credits() {
        return term1Credits;
    }
    public int getTerm2Credits() {
        return term2Credits;
    }

    public void changePane(int pane){
        if (pane == 1){
            this.setExpandedPane(term1Pane);
        } else {
            this.setExpandedPane(term2Pane);
        }
    }

    public int getPaneIndex(){
        if (this.getExpandedPane().equals(term1Pane)){
            return 1;
        } else {
            return 2;
        }
    }

    public Module getSelectedModule(int term, int unORres){
        if (unORres == 0){
            if (term == 1){
                return getUnTerm1().getListView().getSelectionModel().getSelectedItem();
            } else {
                return getUnTerm2().getListView().getSelectionModel().getSelectedItem();
            }
        } else {
            if (term == 1){
                return getResTerm1().getListView().getSelectionModel().getSelectedItem();
            } else {
                return getResTerm2().getListView().getSelectionModel().getSelectedItem();
            }
        }
    }


    public int getSelectedModuleCredits(int term, int unORres){
        if (unORres == 0){
            if (term == 1){
                var selectedModule = getSelectedModule(1, 0);
                return selectedModule.getModuleCredits();
            } else {
                var selectedModule = getSelectedModule(2, 0);
                return selectedModule.getModuleCredits();
            }
        } else {
            if (term == 1){
                var selectedModule = getSelectedModule(1, 1);
                return selectedModule.getModuleCredits();
            } else {
                var selectedModule = getSelectedModule(2, 1);
                return selectedModule.getModuleCredits();
            }
        }

    }

    public void creditsAddModifier(int term, int credits){

        if (term == 1){
            term1Credits = term1Credits - credits;
        } else {
            term2Credits = term2Credits - credits;
        }
    }

    public void creditsRemoveModifier(int term, int credits){

        if (term == 1){
            term1Credits = term1Credits + credits;
        } else {
            term2Credits = term2Credits + credits;
        }
    }

    public Collection<Module> getSelectedReserveModules(int term){
        if (term == 1){
           return getResTerm1().getListView().getItems();
        } else {
            return getResTerm2().getListView().getItems();
        }
    }


    public void clearReserve(){
        listResTerm1.clear();
        listResTerm2.clear();
    }
    public void clearUnReserve(){
        listUnTerm1.clear();
        listUnTerm2.clear();
    }
    public void clearReservePaneCredits(){
        term1Credits = 30;
        term2Credits = 30;
    }

    public void loadModules(StudentProfile profile) {

        Collection<Module> allCourseModules = new ArrayList<Module>(profile.getStudentCourse().getAllModulesOnCourse());

        for (Module m : profile.getAllSelectedModules()){
            allCourseModules.remove(m);
        }

        for (Module m : allCourseModules) {

            if (m.getDelivery().equals(Schedule.TERM_1)) {
                if (m.isMandatory() == false) {
                    getListUnTerm1().add(m);}
            } else if (m.getDelivery().equals(Schedule.TERM_2)) {
                if (m.isMandatory() == false) {
                    getListUnTerm2().add(m);
                }
            }
        }
        if (!profile.getAllReservedModules().isEmpty()){
            for (Module m : profile.getAllReservedModules()) {

                if (m.getDelivery().equals(Schedule.TERM_1)) {
                    getListResTerm1().add(m);
                    creditsAddModifier(1, m.getModuleCredits());
                    getListUnTerm1().remove(m);
                } else if (m.getDelivery().equals(Schedule.TERM_2)) {
                    getListResTerm2().add(m);
                    creditsAddModifier(2, m.getModuleCredits());
                    getListUnTerm2().remove(m);
                }
            }
        }
    }

    public void addAddModuleHandler(EventHandler<ActionEvent> handler) {
        btnTerm1.getAdd().setOnAction(handler);
        btnTerm2.getAdd().setOnAction(handler);
    }
    public void addConfirmModuleHandler(EventHandler<ActionEvent> handler){
        btnTerm1.getConfirm().setOnAction(handler);
        btnTerm2.getConfirm().setOnAction(handler);
    }
    public void addRemoveModuleHandler(EventHandler<ActionEvent> handler){
        btnTerm1.getRemove().setOnAction(handler);
        btnTerm2.getRemove().setOnAction(handler);
    }
}