package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;
import view.ReservePane.ReservePane;
import view.SelectModulesPane.SelectModulesPane;
import view.OverviewPane.OverviewPane;

import java.io.*;
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
	private SelectModulesPane smp;
	private ReservePane rp;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		ovp = view.getOverviewPane();
		smp = view.getSelectModulesPane();
		rp = view.getReservePane();


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

		mstmb.addSaveHandler(new SaveMenuHandler());
		mstmb.addLoadHandler(new LoadMenuHandler());
		mstmb.addAboutHandler(e -> this.alertDialogBuilder(Alert.AlertType.INFORMATION, "About Module Chooser", "Module Chooser", "A module chooser, created by P2593265"));

		rp.addAddModuleHandler(new addReserveModuleHandler());
		rp.addConfirmModuleHandler(new confirmReserveModuleHandler());
		rp.addRemoveModuleHandler(new removeReserveModuleHandler());

		smp.addAddModuleHandler(new addSelectModuleHandler());
		smp.addRemoveModuleHandler(new removeSelectModuleHandler());
		smp.addResetModuleHandler(new resetSelectModuleHandler());
		smp.addSubmitModuleHandler(new submitSelectModuleHandler());

	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			String debug = "";
			int term1Creds = 0;
			int term2Creds = 0;

			if (!cspp.validFields()){

			} else {
				model = cspp.getStudentProfile();

				rp.clearUnReserve();
				rp.clearReserve();
				smp.clearSMP();
				ovp.clearOverview();
				ovp.setProfileData(model);
				debug += model.getStudentCourse() + "\n";
				debug += model.getStudentCourse().getAllModulesOnCourse() + "\n";


				for (Module m : model.getStudentCourse().getAllModulesOnCourse()){

					if (m.getDelivery().equals(Schedule.TERM_1)){
						if(m.isMandatory() == false){
							smp.getUnModTerm1Contents().add(m);
						} else {
							smp.getSelModTerm1Contents().add(m);
							term1Creds = term1Creds + m.getModuleCredits();
						}
					} else if (m.getDelivery().equals(Schedule.TERM_2)) {
						if (m.isMandatory() == false) {
							smp.getUnModTerm2Contents().add(m);
						} else {
							smp.getSelModTerm2Contents().add(m);
							term2Creds = term2Creds + m.getModuleCredits();
						}
					} else {
						smp.getSelModYearContents().add(m);
						term1Creds = term1Creds + (m.getModuleCredits()/2);
						term2Creds = term2Creds + (m.getModuleCredits()/2);
					}
				}
				smp.setTerm1Credits(term1Creds);
				smp.setTerm2Credits(term2Creds);
				smp.getTxtDebug().setText(debug);
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

	private Course[] setupCourses() throws FileNotFoundException {
		List<Course> courseIn = new ArrayList<Course>();
		Course course;

		Scanner sc = new Scanner(new File("coursesdata.txt"));

		String curLine = sc.nextLine();
		String[] curLineSplit = curLine.split(",");
		String courseID = curLineSplit[0];

		course = new Course(courseID);

		String debug = "";
		while (!(curLine.equals("end"))){
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
			debug += "\n" + curLine;


			//String nextLine[] = sc.nextLine().split(",");
			//String nextCourseName = nextLine[0];

			curLine = sc.nextLine();
			curLineSplit = curLine.split(",");
			String nextCourseName = curLineSplit[0];

			if (!(course.getCourseName()).equals(nextCourseName)){
				courseIn.add(course);
				course = new Course(nextCourseName);
			}

		}
		sc.close();

		Course[] courses = new Course[courseIn.size()];
		for(int i=0; i < courses.length; i++){
			courses[i] = courseIn.get(i);

		}
		cspp.getTxtDebug().setText(debug);
		return courses;
	}


	private class addSelectModuleHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			String s = e.getSource().toString();
			s = s.substring(s.indexOf("=")+ 1);
			s = s.substring(0, s.indexOf(","));
			if (s.equals("term1Add")){
				if (smp.getTerm1Credits() < 60){
					smp.setTerm1Credits(smp.getTerm1Credits() + smp.getUnTerm1SelectedModule().getModuleCredits());
					smp.getSelModTerm1Contents().add(smp.getUnTerm1SelectedModule());
					smp.getUnModTerm1Contents().remove(smp.getUnTerm1SelectedModule());
				} else {
					alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
							"Invalid modules", "Module limit for term 1 has been reached");
				}
			} else if (s.equals("term2Add")) {
				if (smp.getTerm2Credits() < 60){
					smp.setTerm2Credits(smp.getTerm2Credits() + smp.getUnTerm2SelectedModule().getModuleCredits());
					smp.getSelModTerm2Contents().add(smp.getUnTerm2SelectedModule());
					smp.getUnModTerm2Contents().remove(smp.getUnTerm2SelectedModule());
				} else {
					alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
							"Invalid modules", "Module limit for term 2 has been reached");
				}
			}
		}
	}

	private class removeSelectModuleHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			String s = e.getSource().toString();
			s = s.substring(s.indexOf("=")+ 1);
			s = s.substring(0, s.indexOf(","));
			System.out.println(s);
			if (s.equals("term1Rem")){
				if (!(smp.getSelTerm1SelectedModule() == null)){
					if (!smp.getSelTerm1SelectedModule().isMandatory()){
						if(smp.getTerm1Credits() > 0 || !(smp.getTerm1Credits() < 0)){
							smp.setTerm1Credits(smp.getTerm1Credits() - smp.getSelTerm1SelectedModule().getModuleCredits());
							smp.getUnModTerm1Contents().add(smp.getSelTerm1SelectedModule());
							smp.getSelModTerm1Contents().remove(smp.getSelTerm1SelectedModule());
						} else {
							alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
									"Invalid modules", "Cannot remove further modules; credit minimum reached.");
						}
					} else {
						alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
								"Invalid modules", "Cannot remove mandatory modules.");
					}
				} else {
					alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
							"Invalid modules", "Selected module is null.");
				}
			} else if (s.equals("term2Rem")){
				if (!(smp.getSelTerm2SelectedModule() == null)){
					if (!smp.getSelTerm2SelectedModule().isMandatory()){
						if(smp.getTerm2Credits() > 0 || !(smp.getTerm2Credits() < 0)){
							smp.setTerm2Credits(smp.getTerm2Credits() - smp.getSelTerm2SelectedModule().getModuleCredits());
							smp.getUnModTerm2Contents().add(smp.getSelTerm2SelectedModule());
							smp.getSelModTerm2Contents().remove(smp.getSelTerm2SelectedModule());
						} else {
							alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
									"Invalid modules", "Cannot remove further modules; credit minimum reached.");
						}
					} else {
						alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
								"Invalid modules", "Cannot remove mandatory modules.");
					}
				} else {
					alertDialogBuilder(Alert.AlertType.ERROR, "Error Dialogue",
							"Invalid modules", "Selected module is null.");
				}

			}
		}
	}

	private class resetSelectModuleHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			smp.clearSMP();
			for (Module m : model.getStudentCourse().getAllModulesOnCourse()){
				if (m.getDelivery().equals(Schedule.TERM_1)){
					if(m.isMandatory() == false){
						smp.getUnModTerm1Contents().add(m);
					} else {
						smp.getSelModTerm1Contents().add(m);
						smp.setTerm1Credits(smp.getTerm1Credits() + m.getModuleCredits());
						//txtT1Credits.setText(String.valueOf(term1Credits));
					}
				} else if (m.getDelivery().equals(Schedule.TERM_2)) {
					if (m.isMandatory() == false) {
						smp.getUnModTerm2Contents().add(m);
					} else {
						smp.getSelModTerm2Contents().add(m);
						smp.setTerm2Credits(smp.getTerm2Credits() + m.getModuleCredits());
						//xtT2Credits.setText(String.valueOf(term2Credits));
					}
				} else {
					smp.getSelModYearContents().add(m);
					smp.setTerm1Credits(smp.getTerm1Credits() + m.getModuleCredits()/2);
					smp.setTerm2Credits(smp.getTerm2Credits() + m.getModuleCredits()/2);
				}
			}
		}

	}

	private class submitSelectModuleHandler implements  EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			for (Module m : smp.getSelModYearContents()){
				model.addSelectedModule(m);
			}
			for (Module m : smp.getSelModTerm1Contents()){
				model.addSelectedModule(m);
			}
			for (Module m : smp.getSelModTerm2Contents()){
				model.addSelectedModule(m);
			}
			rp.clearUnReserve();
			rp.clearReserve();
			rp.loadModules(model);
			ovp.setSelectedModuleData(model);
			view.changeTab(2);
		}
	}


	private class addReserveModuleHandler implements EventHandler<ActionEvent>  {
		public void handle(ActionEvent e) {
			rp.addSelectedModule();
		}
	}

	private class removeReserveModuleHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (rp.getPaneIndex() == 1){
				model.removeSelectedReserveModule(rp.getSelectedModule(1, 1));
			} else {
				model.removeSelectedReserveModule(rp.getSelectedModule(2, 1));
			}
			rp.removeSelectedModule();
		}
	}

	private class confirmReserveModuleHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			if (rp.getPaneIndex() == 1){
				for (Module m : rp.getSelectedReserveModules(1)){
					model.addReservedModule(m);
					rp.changePane(2);
				}
			} else {
				for (Module m : rp.getSelectedReserveModules(2)) {
					model.addReservedModule(m);
					ovp.clearReserve();
					ovp.setReserveModuleData(model);
					view.changeTab(3);
				}
			}
		}
	}


	public void alertDialogBuilder(Alert.AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private class SaveMenuHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentProfile.dat"))){

				oos.writeObject(model);
				oos.flush();
				//oos.close();

				alertDialogBuilder(Alert.AlertType.INFORMATION, "Information Dialog", "Save successful!", "Student profile saved to studentProfile.dat");
			}
			catch (IOException ioExcep){
				alertDialogBuilder(Alert.AlertType.ERROR, "Error", "Error saving! :(", "There was an error saving the student profile.");
			}
		}
	}

	private class LoadMenuHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			File f = new File("studentProfile.dat");
			if (f.exists()){
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentProfile.dat"))){

					model = (StudentProfile) ois.readObject();

					alertDialogBuilder(Alert.AlertType.INFORMATION, "Information Dialog", "Load successful!", "Student profile loaded from studentProfile.dat");
				}
				catch (IOException ioExcep){
					alertDialogBuilder(Alert.AlertType.ERROR, "Error", "Error loading! :(", "There was an error loading the student profile.");
				}
				catch (ClassNotFoundException c) {
					alertDialogBuilder(Alert.AlertType.ERROR, "Error", "Error loading! :(", "Class not found.");
				}

				cspp.clearStudentProfilePane();
				ovp.clearOverview();
				rp.clearUnReserve();
				rp.clearReserve();
				smp.clearSMP();
				cspp.loadProfile(model);
				ovp.setProfileData(model);
				ovp.setReserveModuleData(model);
				ovp.setSelectedModuleData(model);
				smp.loadModules(model);
				rp.loadModules(model);


				view.changeTab(0);
			} else {
				alertDialogBuilder(Alert.AlertType.ERROR, "Error", "Error loading! :(", "No student profile was found.");
			}

		}
	}

}
