package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Course;
import model.Name;
import model.StudentProfile;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CreateStudentProfilePane extends GridPane {

	private ComboBox<Course> cboCourses;
	private DatePicker inputDate;
	private TextField txtFirstName, txtSurname,  txtPnumber, txtEmail;
	private Button btnCreateProfile;

	public CreateStudentProfilePane() {
		//styling
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.getColumnConstraints().addAll(column0);
		
		//create labels
		Label lblTitle = new Label("Select course: ");
		Label lblPnumber = new Label("Input P number: ");
		Label lblFirstName = new Label("Input first name: ");
		Label lblSurname = new Label("Input surname: ");
		Label lblEmail = new Label("Input email: ");
		Label lblDate = new Label("Input date: ");
		
		//initialise combobox
		cboCourses = new ComboBox<Course>(); //this is populated via method towards end of class
		
		//setup text fields
		txtFirstName = new TextField();
		txtSurname = new TextField();
		txtPnumber = new TextField();
		txtEmail = new TextField();
		
		inputDate = new DatePicker();
		
		//initialise create profile button
		btnCreateProfile = new Button("Create Profile");

		//add controls and labels to container
		this.add(lblTitle, 0, 0);
		this.add(cboCourses, 1, 0);

		this.add(lblPnumber, 0, 1);
		this.add(txtPnumber, 1, 1);
		
		this.add(lblFirstName, 0, 2);
		this.add(txtFirstName, 1, 2);

		this.add(lblSurname, 0, 3);
		this.add(txtSurname, 1, 3);
		
		this.add(lblEmail, 0, 4);
		this.add(txtEmail, 1, 4);
		
		this.add(lblDate, 0, 5);
		this.add(inputDate, 1, 5);
			
		this.add(new HBox(), 0, 6);
		this.add(btnCreateProfile, 1, 6);
	}
	
	//method to allow the controller to add courses to the combobox
	public void addCoursesToComboBox(Course[] courses) {
		cboCourses.getItems().addAll(courses);
		cboCourses.getSelectionModel().select(0); //select first course by default
	}
	
	//methods to retrieve the form selection/input
	public Course getSelectedCourse() {
		return cboCourses.getSelectionModel().getSelectedItem();
	}
	
	public String getStudentPnumber() {
		return txtPnumber.getText();
	}
	
	public Name getStudentName() {
		return new Name(txtFirstName.getText(), txtSurname.getText());
	}

	public String getStudentEmail() {
		return txtEmail.getText();
	}
	
	public LocalDate getStudentDate() {
		return inputDate.getValue();
	}

	//Returns entered student details as a StudentProfile
	public StudentProfile getStudentProfile(){
		Course course = getSelectedCourse();
		String pnum = getStudentPnumber();
		Name name = getStudentName();
		String email = getStudentEmail();
		LocalDate date = getStudentDate();

		StudentProfile studentP = new StudentProfile();

		studentP.setStudentCourse(course);
		studentP.setStudentPnumber(pnum);
		studentP.setStudentName(name);
		studentP.setStudentEmail(email);
		studentP.setSubmissionDate(date);

		return studentP;
	}

	public boolean validFields(){
		String errors = "";

		if(!(getStudentPnumber().startsWith("P")) || (getStudentPnumber().length()) > 10){
			errors += "- P number must start with a P and be no greater than 10 characters in length.\n";
		}

		if((txtFirstName.getText().length() < 2) || (txtFirstName.getText().length() > 25)){
			errors += "- Firstname cannot be shorter than 2 characters or greater than 25 characters.\n";
		}

		if ((txtSurname.getText().length() < 2) || (txtSurname.getText().length() > 25)){
			errors += "- Surname cannot be shorter than 2 characters or greater than 25 chcracters.\n";
		}

		if (!isValidEmail(getStudentEmail())){
			errors += "- Email is not valid.\n";
		}

	/*	if (getStudentDate().isBefore(java.time.LocalDate.now())){
			errors += "- Selected date must be today's date.\n";
		}*/


		if (!errors.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Validation Failed");
			alert.setContentText(errors);
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public boolean isValidEmail(String email){
		String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; //regex pattern obtained from https://www.baeldung.com/java-email-validation-regex
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}
	
	
	//method to attach the create student profile button event handler
	public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);
	}

}
