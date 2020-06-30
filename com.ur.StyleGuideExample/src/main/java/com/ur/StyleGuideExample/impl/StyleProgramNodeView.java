package com.ur.StyleGuideExample.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.ur.builder.objects.BuilderLibrary;
import com.ur.style.URIcon;
import com.ur.style.components.URButtons;
import com.ur.style.components.URDivider;
import com.ur.style.components.URDropdowns;
import com.ur.style.components.URErrorMessage;
import com.ur.style.components.URInformativeMessage;
import com.ur.style.components.URLoadingBar;
import com.ur.style.components.URSliders;
import com.ur.style.components.URSuccessMessage;
import com.ur.style.components.URTables;
import com.ur.style.components.URTabs;
import com.ur.style.components.URTextFields;
import com.ur.style.components.URToggles;
import com.ur.style.components.URWarningMessage;
import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class StyleProgramNodeView extends BuilderLibrary implements SwingProgramNodeView<StyleProgramNodeContribution> {

	private URToggles toggleLib = new URToggles();
	private URButtons buttonLib = new URButtons();
	
	private URInformativeMessage informativeMessage = new URInformativeMessage();
	private URSuccessMessage successMessage = new URSuccessMessage();
	private URErrorMessage errorMessage = new URErrorMessage();
	private URWarningMessage warningMessage = new URWarningMessage();
	private URDivider divider = new URDivider();
	private URDropdowns dropdown = new URDropdowns();
	private URLoadingBar loadingbar = new URLoadingBar();
	private URSliders slider = new URSliders();
	private URTables tables = new URTables();
	private URTabs tabs = new URTabs();
	private URTextFields textFields = new URTextFields();
	
	
	private URIcon iconLib = new URIcon();
	
	public StyleProgramNodeView(ViewAPIProvider apiProvider) {

	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<StyleProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//this.showDifferentMessageType(panel);
		//this.showToggles(panel);
		//this.showButtons(panel);
		//this.showDivider(panel);
		//this.showDropdowns(panel);
		this.showLoadingBar(panel);
		//this.showSlider(panel);
		//this.showTables(panel);
		//this.showTabs(panel);
		//this.showTextFields(panel);
		
	}

	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}
	
	private void createSlider(JPanel panel) {
		JSlider slider = new JSlider();
		Border myBorder = BorderFactory.createLineBorder(urColorPalette.WHITE, urBorder.BORDER_THIN);

		slider.setBackground(urColorPalette.WHITE);
		slider.setBorder(myBorder);
		slider.setMinimum(0);
		slider.setMaximum(99);
		slider.setFocusable(false);
		slider.setForeground(urColorPalette.BLACK);

        slider.setPaintTrack(true); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 

        // set spacing/ the middle value.
        int tickSpacing = (0 + 99)/2;
        slider.setMajorTickSpacing(tickSpacing); 


		slider.setPreferredSize(new Dimension(200, urBorder.HEIGHT_TINY));
		
		panel.add(slider);
	}
	
	
	private void showTextFields(JPanel panel) {
		JTextField fieldEnabled = textFields.getTextFieldEnabled(100);
		JTextField fieldDisabled = textFields.getTextFieldDisabled(100);
		
		fieldDisabled.setText("300");
		fieldEnabled.setText("300");
		
		panel.add(fieldEnabled);
		panel.add(createSpacer(20));
		panel.add(fieldDisabled);
		
	}
	
	private void showTabs(JPanel panel) {
		JTabbedPane tabPane = tabs.getTabbedPane(100);
		tabPane.addTab("Command", new JPanel());
		tabPane.addTab("Graphics", new JPanel());
		tabPane.addTab("Variables", new JPanel());
		
		panel.add(tabPane);
	}
	
	private void showTables(JPanel panel) {
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("test1");
      model.addRow(new String[]{"2018-02-06- 09:09:52.057 PolyScope        URSoftware 5.0.0"});
      model.addRow(new String[]{"2018-02-06- 09:09:52.057 PolyScope        Connected to Controller"});
      model.addRow(new String[]{"2018-02-06- 09:09:52.057 PolyScope        URSoftware 5.0.0 (26.10.2017)"});
      model.addRow(new String[]{"2018-02-06- 09:09:52.057 PolyScope        URSoftware 5.0.0 (26.10.2017)"});
      model.addRow(new String[]{"2018-02-06- 09:09:52.057 PolyScope        Safety checksum changed to: CCCC"});
      JTable table = tables.getTable();
      table.setModel(model);
      
      panel.add(table);
		
	}
	
	private void showSlider(JPanel panel) {
		
		JSlider valueSlider = slider.getSlider(0, 99, 200);
		
		panel.add(valueSlider);
	}
	
	
	private void showLoadingBar(JPanel panel){
		JProgressBar thinLoadingbar = loadingbar.getThinLoadingBar(300);
		thinLoadingbar.setIndeterminate(true);
		thinLoadingbar.setMaximum(100);
		thinLoadingbar.setValue(50);
		thinLoadingbar.setIndeterminate(false);
		
		JProgressBar thickLoadingbar = loadingbar.getThickLoadingBar(300);
		thickLoadingbar.setIndeterminate(true);
		thickLoadingbar.setMaximum(100);
		thickLoadingbar.setValue(50);
		thickLoadingbar.setIndeterminate(false);
		
		JProgressBar thinLoadingbarWithText = loadingbar.getThinLoadingBarWithText(300, "12.00 mA");
		//thinLoadingbarWithText.setStringPainted(true);
		thinLoadingbarWithText.setIndeterminate(true);
		thinLoadingbarWithText.setMaximum(100);
		thinLoadingbarWithText.setValue(50);
		thinLoadingbarWithText.setIndeterminate(false);
		
		panel.add(thinLoadingbar);
		panel.add(createSpacer(20));
		panel.add(thinLoadingbarWithText);
		panel.add(createSpacer(20));
		panel.add(thickLoadingbar);
	}
	
	private void showDropdowns(JPanel panel) {
		String[] stringValues = {"A","B","C"};
		
		JComboBox<String> boxEnabled = dropdown.getDropDownEnabled(100);
		boxEnabled.setModel(new DefaultComboBoxModel<String>(stringValues));
		
		JComboBox<String> boxDisabled = dropdown.getDropDownDisabled(100);
		boxDisabled.setModel(new DefaultComboBoxModel<String>(stringValues));
		
		panel.add(boxEnabled);
		panel.add(createSpacer(20));
		panel.add(boxDisabled);
	}
	
	
	private void showDivider(JPanel panel) {
		panel.add(divider.getVerticalDivider(100));
		panel.add(createSpacer(10));
		panel.add(divider.getHorizontalDivider(100));
	}
	
	
	
	private void showDifferentMessageType(JPanel panel) {
		
		panel.add(informativeMessage.getLargeInformativeMessage("INFO MESSAGE", 600));
		panel.add(createSpacer(10));
		panel.add(informativeMessage.getSmallInformativeMessage("Changes do not take effect until saving the installation", 600));
		
		panel.add(createSpacer(10));
		
		panel.add(successMessage.getLargeSuccessMessage("SUCCESS MESSAGE", 600));
		panel.add(createSpacer(10));
		panel.add(successMessage.getSmallSuccessMessage("Success Message", 600));
		
		panel.add(createSpacer(10));
		
		panel.add(errorMessage.getLargeErrorMessage("NO CONTROLLER", 600));
		panel.add(createSpacer(10));
		panel.add(errorMessage.getSmallErrorMessage("Error Message", 600));
		
		panel.add(createSpacer(10));
		
		panel.add(warningMessage.getLargeWarningMessage("BACKDRIVE", 600));
		panel.add(createSpacer(10));
		panel.add(warningMessage.getSmallWarningMessage("Warning Message", 600));
		
	}

	private void showToggles(JPanel panel) {
		
		panel.add(toggleLib.getLargeToggleEnabled("Enable", 200));
		panel.add(createSpacer(10));
		panel.add(toggleLib.getLargeToggleDisabled("Disable", 200));

		panel.add(createSpacer(20));

		JToggleButton toggle = toggleLib.getSmallToggleSelected(50);
		toggle.setIcon(iconLib.warning_icon_small);

		panel.add(toggle);
		panel.add(createSpacer(10));
		panel.add(toggleLib.getSmallToggleDeselected(50));
	}

	private void showButtons(JPanel panel) {

		panel.add(buttonLib.getLargeButtonDisabled("MyLarge", 200));
		panel.add(createSpacer(10));
		panel.add(buttonLib.getLargeButtonEnabled("MyCTAButtonMedium", 200));

		panel.add(createSpacer(20));

		panel.add(buttonLib.getSmallButtonEnabled("MySmallButtonMedium", 200));
		panel.add(createSpacer(10));
		panel.add(buttonLib.getSmallButtonDisabled("MySmallButtonMedium", 200));

		panel.add(createSpacer(20));

		panel.add(buttonLib.getMediumCTAButtonDisabled("MyCTAButtonMedium", 200));
		panel.add(createSpacer(10));
		panel.add(buttonLib.getSmallCTAButtonEnabled("MyCTAButtonMedium", 200));

	}



}
