package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import view.OverviewPane.OverviewPane;
import view.StudentProfilePane.CreateStudentProfilePane;


public class ModuleChooserRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private OverviewPane ovp;
	private ModuleChooserMenuBar mstmb;
	private TabPane tp;
	
	public ModuleChooserRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		ovp = new OverviewPane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Modules");
		Tab t3 = new Tab("Reserve Modules");
		Tab t4 = new Tab("Overview", ovp);
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1,t2, t3, t4);
		
		//create menu bar
		mstmb = new ModuleChooserMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public ModuleChooserMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
