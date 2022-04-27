package view.OverviewPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;
import model.StudentProfile;

public class OverviewPane extends GridPane {

    private TextArea profile;
    private TextArea selected;
    private TextArea reserve;
    private Button btnSave;

    public OverviewPane(){

        this.setVgap(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);



        profile = new TextArea("Student profile will appear here when created...");
        selected = new TextArea("Selected modules will show here when selected...");
        reserve = new TextArea("Reserve modules will show here when selected...");
        btnSave = new Button("Save overview");

        profile.prefWidthProperty().bind(this.widthProperty());
        profile.setPrefHeight(400);
       // profile.setPrefSize(600, 100);
        profile.setEditable(false);

        selected.prefWidthProperty().bind(this.widthProperty());
        selected.prefHeightProperty().bind(this.heightProperty());
       // selected.setPrefSize(300, 200);
        selected.setEditable(false);


        reserve.prefWidthProperty().bind(this.widthProperty());
        reserve.prefHeightProperty().bind(this.heightProperty());
      //  reserve.setPrefSize(300, 200);
        reserve.setEditable(false);

        VBox vbox = new VBox(profile);
        HBox hbox = new HBox(selected, reserve);
        HBox hboxbtn = new HBox(btnSave);

        vbox.setAlignment(Pos.CENTER);

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(40);

        hboxbtn.setAlignment(Pos.CENTER);

        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        hbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        profile.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        selected.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        reserve.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

       /* GridPane.setVgrow(vbox, Priority.ALWAYS);
        GridPane.setHgrow(vbox, Priority.ALWAYS);
        GridPane.setVgrow(profile, Priority.ALWAYS);
        GridPane.setHgrow(profile, Priority.ALWAYS);

        GridPane.setVgrow(hbox, Priority.ALWAYS);
        GridPane.setHgrow(hbox, Priority.ALWAYS);
        GridPane.setVgrow(selected, Priority.ALWAYS);
        GridPane.setHgrow(selected, Priority.ALWAYS);
        GridPane.setVgrow(reserve, Priority.ALWAYS);
        GridPane.setHgrow(reserve, Priority.ALWAYS);*/



        this.add(vbox, 0, 1);
        this.add(hbox, 0, 2);
        this.add(hboxbtn, 0, 3);
    }

    public void setProfileData(StudentProfile profileData){

        var name = profileData.getStudentName().getFullName();
        var pnum = profileData.getStudentPnumber();
        var email = profileData.getStudentEmail();
        var date = profileData.getSubmissionDate();
        var courseName = profileData.getStudentCourse().getCourseName();

        String data = "";
        data += "Name: " + name + "\n";
        data += "P Number: " + pnum + "\n";
        data += "Email: " + email + "\n";
        data += "Date: " + date + "\n";
        data += "Course: " + courseName;

        profile.setText(data);
    }

    public void setSelectedModuleData(StudentProfile profileData){
        String data = "Selected modules: \n";
        data += "=============== \n";

        for (Module m : profileData.getAllSelectedModules()){
            var modCode = m.getModuleCode();
            var modName = m.getModuleName();
            var modCredit = m.getModuleCredits();
            String modTerm = String.valueOf(m.getDelivery());

            data += "Module code: " + modCode + ", Module name: " + modName + ", \nModule credits: " + modCredit +
                    ", Delivery: " + modTerm + "\n\n";
        }
        selected.setText(data);
    }

    public void setReserveModuleData(StudentProfile profileData){
        String data = "Reserve modules: \n";
        data += "=============== \n";
        for (Module m : profileData.getAllReservedModules()){
            var modCode = m.getModuleCode();
            var modName = m.getModuleName();
            var modCredit = m.getModuleCredits();
            String modTerm = String.valueOf(m.getDelivery());

            data += "Module code: " + modCode + ", Module name: " + modName + ", \nModule credits: " + modCredit +
                    ", Delivery: " + modTerm + "\n\n";
        }
        reserve.setText(data);
    }

    public TextArea getProfile() {
        return profile;
    }

    public TextArea getSelected() {
        return selected;
    }

    public TextArea getReserve() {
        return reserve;
    }

    public void clearProfile(){
        profile.clear();
    }
    public void clearSelected(){
        selected.clear();
    }
    public void clearReserve(){
        reserve.clear();
    }

    public void clearOverview(){
        profile.clear();
        selected.clear();
        reserve.clear();
    }

    public void addSaveOverviewHandler(EventHandler<ActionEvent> handler) {
        btnSave.setOnAction(handler);
    }
}
