package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;
import view.SelectModulesPane.SelectModulesPane;
import view.OverviewPane.OverviewPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModuleChooserController {

	//fields to be used throughout class
	private ModuleChooserRootPane view;
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private ModuleChooserMenuBar mstmb;
	private OverviewPane ovp;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		ovp = view.getOverviewPane();


		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		try {
			cspp.addCoursesToComboBox(setupCourses());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			if (!cspp.validFields()){

			} else {
				model = cspp.getStudentProfile();

				ovp.clearOverview();
				ovp.setProfileData(model);

				view.changeTab(1);
			}
		}
	}


	//helper method - generates course and module data and returns courses within an array
	/*private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3104 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3104);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3104);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}*/

	String debugText;
	private Course[] setupCourses() throws FileNotFoundException {
		List<Course> courseIn = new ArrayList<Course>();
		Course course;

		Scanner sc = new Scanner(new File("coursesdata.txt"));

		String curLine = sc.nextLine();
		String[] curLineSplit = curLine.split(",");

		course = new Course(curLineSplit[0]);
		debugText += "\n" + course;

		while (sc.hasNextLine()){
			String courseName = curLineSplit[0];
			String moduleCode = curLineSplit[1];
			String moduleName = curLineSplit[2];
			int moduleCredits = Integer.parseInt(curLineSplit[3]);
			boolean moduleManditory = Boolean.parseBoolean(curLineSplit[4]);
			Schedule moduleTerm;

			if (Integer.parseInt(curLineSplit[5]) == 1){
				moduleTerm = Schedule.TERM_1;
			} else if (Integer.parseInt(curLineSplit[5]) == 2){
				moduleTerm = Schedule.TERM_2;
			} else {
				moduleTerm = Schedule.YEAR_LONG;
			}

			Module module = new Module(moduleCode, moduleName, moduleCredits, moduleManditory, moduleTerm);
			course.addModuleToCourse(module);


			String nextLine[] = sc.nextLine().split(",");
			String nextCourseName = nextLine[0];


			if (!(course.getCourseName()).equals(nextCourseName)){
				debugText += "\n" + course.getCourseName() + "\n" + nextCourseName;
				courseIn.add(course);
				debugText += "\n" + courseIn + "\n" + courseIn.size();
				course = new Course(nextCourseName);
				debugText += "\n" + nextCourseName;
				debugText += "\n" + course;
				//courseIn.add(course);
			}
		}
		sc.close();

		Course[] courses = new Course[courseIn.size()];
		for(int i=0; i < courses.length; i++){
			courses[i] = courseIn.get(i);

		}
		debugText += "\n" + courseIn + "\n" + courseIn.size();
		cspp.getTxtDebug().setText(debugText);
		return courses;
	}

	/*public Course getCourse(String[] line){
		if (line[0].equals("cs")){
			return new Course("Computer Science");
		} else {
			return new Course("Software Engineering");
		}
	}*/


	public void alertDialogBuilder(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
