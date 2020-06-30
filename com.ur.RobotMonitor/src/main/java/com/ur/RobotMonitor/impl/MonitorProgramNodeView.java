package com.ur.RobotMonitor.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;

public class MonitorProgramNodeView implements SwingInstallationNodeView<MonitorProgramNodeContribution> {

	private JTextField baseFieldMin;
	private JTextField shoulderFieldMin;
	private JTextField elbowFieldMin;
	private JTextField wrist1FieldMin;
	private JTextField wrist2FieldMin;
	private JTextField wrist3FieldMin;

	private JTextField baseFieldMax;
	private JTextField shoulderFieldMax;
	private JTextField elbowFieldMax;
	private JTextField wrist1FieldMax;
	private JTextField wrist2FieldMax;
	private JTextField wrist3FieldMax;

	private JTextField speedMin;
	private JTextField speedMax;

	private JTextField positionXmin;
	private JTextField positionXmax;
	private JTextField positionYmin;
	private JTextField positionYmax;
	private JTextField positionZmin;
	private JTextField positionZmax;

	private JButton buttonStart;
	private JButton buttonStop;
	private JButton buttonClear;

	private Timer timer;
	private RealtimeReader reader = new RealtimeReader();

	public MonitorProgramNodeView() {
		this.baseFieldMin = createJTextField();
		this.shoulderFieldMin = createJTextField();
		this.elbowFieldMin = createJTextField();
		this.wrist1FieldMin = createJTextField();
		this.wrist2FieldMin = createJTextField();
		this.wrist3FieldMin = createJTextField();

		this.baseFieldMax = createJTextField();
		this.shoulderFieldMax = createJTextField();
		this.elbowFieldMax = createJTextField();
		this.wrist1FieldMax = createJTextField();
		this.wrist2FieldMax = createJTextField();
		this.wrist3FieldMax = createJTextField();

		this.speedMin = createJTextField();
		this.speedMax = createJTextField();

		this.positionXmin = createJTextField();
		this.positionXmax = createJTextField();
		this.positionYmin = createJTextField();
		this.positionYmax = createJTextField();
		this.positionZmin = createJTextField();
		this.positionZmax = createJTextField();

	}

	@Override
	public void buildUI(JPanel panel, MonitorProgramNodeContribution contribution) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Joint positions
		panel.add(createMinMaxBox(createLabel("Min"), createLabel("Max")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Base"), this.baseFieldMin, this.baseFieldMax,
				createLabel("degree")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Shoulder"), this.shoulderFieldMin, this.shoulderFieldMax,
				createLabel("degree")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Elbow"), this.elbowFieldMin, this.elbowFieldMax,
				createLabel("degree")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Wrist 1"), this.wrist1FieldMin, this.wrist1FieldMax,
				createLabel("degree")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Wrist 2"), this.wrist2FieldMin, this.wrist2FieldMax,
				createLabel("degree")));
		panel.add(createVerticalSpacing(10));
		panel.add(createJointPositionBox(createLabel("Wrist 3"), this.wrist3FieldMin, this.wrist3FieldMax,
				createLabel("degree")));

		// tool position x,y,z
		panel.add(createVerticalSpacing(30));
		panel.add(createMinMaxBox(createLabel("Min"), createLabel("Max")));
		panel.add(createVerticalSpacing(10));
		panel.add(
				createToolPositionBoxRight(createLabel("X"), this.positionXmin, this.positionXmax, createLabel("mm")));
		panel.add(createVerticalSpacing(10));
		panel.add(
				createToolPositionBoxRight(createLabel("Y"), this.positionYmin, this.positionYmax, createLabel("mm")));
		panel.add(createVerticalSpacing(10));
		panel.add(
				createToolPositionBoxRight(createLabel("Z"), this.positionZmin, this.positionZmax, createLabel("mm")));

		// speed max and min
		panel.add(createVerticalSpacing(30));
		panel.add(createMinMaxBox(createLabel("Min"), createLabel("Max")));
		panel.add(createToolPositionBoxRight(createLabel("Speed"), this.speedMin, this.speedMax, createLabel("m/s")));

		// start and stop button
		panel.add(createVerticalSpacing(30));
		panel.add(createButtons(contribution));

	}

	private Box createMinMaxBox(JLabel labelTitleMin, JLabel labelTitleMax) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(createHorizontalSpacing(110));
		box.add(labelTitleMin);
		box.add(createHorizontalSpacing(110));
		box.add(labelTitleMax);

		return box;
	}

	private Box createJointPositionBox(JLabel labelTitle, JTextField textFieldMin, JTextField textFieldMax,
			JLabel labelUnit) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(labelTitle);
		box.add(createHorizontalSpacing(10));
		box.add(textFieldMin);
		box.add(createHorizontalSpacing(10));
		box.add(textFieldMax);
		box.add(createHorizontalSpacing(10));
		box.add(labelUnit);

		return box;

	}

	private Box createToolPositionBoxRight(JLabel labelTitle, JTextField textFieldMin, JTextField textFieldMax,
			JLabel labelUnit) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(labelTitle);
		box.add(createHorizontalSpacing(10));
		box.add(textFieldMin);
		box.add(createHorizontalSpacing(10));
		box.add(textFieldMax);
		box.add(createHorizontalSpacing(10));
		box.add(labelUnit);

		return box;

	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel();
		label.setSize(new Dimension(100, 30));
		label.setPreferredSize(label.getSize());
		label.setMaximumSize(label.getPreferredSize());
		label.setText(text);

		return label;
	}

	private JTextField createJTextField() {
		JTextField textField = new JTextField();
		textField.setSize(new Dimension(150, 30));
		textField.setPreferredSize(textField.getSize());
		textField.setMaximumSize(textField.getPreferredSize());
		textField.setEditable(false);

		return textField;
	}

	private Box createButtons(MonitorProgramNodeContribution contribution) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		box.add(createStartButton(contribution));
		box.add(createHorizontalSpacing(10));
		box.add(createStopButton(contribution));
		box.add(createHorizontalSpacing(10));
		box.add(createClearButton(contribution));

		return box;
	}

	private JButton createClearButton(final MonitorProgramNodeContribution contribution) {

		buttonClear = new JButton();
		buttonClear.setPreferredSize(new Dimension(120, 30));
		buttonClear.setMaximumSize(buttonClear.getPreferredSize());
		buttonClear.setText("Clear All");

		buttonClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				contribution.setToolListToZero();
				contribution.setPositionListToZero();
				contribution.setSpeedListToZero();
				
				baseFieldMin.setText("");
				baseFieldMax.setText("");
				shoulderFieldMin.setText("");
				shoulderFieldMax.setText("");
				elbowFieldMin.setText("");
				elbowFieldMax.setText("");
				wrist1FieldMin.setText("");
				wrist1FieldMax.setText("");
				wrist2FieldMin.setText("");
				wrist2FieldMax.setText("");
				wrist3FieldMin.setText("");
				wrist3FieldMax.setText("");

				speedMin.setText("");
				speedMax.setText("");

				positionXmin.setText("");
				positionXmax.setText("");
				positionYmin.setText("");
				positionYmax.setText("");
				positionZmin.setText("");
				positionZmax.setText("");

			}
		});

		return buttonClear;
	}

	private JButton createStartButton(final MonitorProgramNodeContribution contribution) {

		buttonStart = new JButton();
		buttonStart.setPreferredSize(new Dimension(100, 30));
		buttonStart.setMaximumSize(buttonStart.getPreferredSize());
		buttonStart.setText("Start");

		buttonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// sets the button to non-clickable
				buttonStart.setEnabled(false);
				buttonClear.setEnabled(false);

				timer = new Timer(true);
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						EventQueue.invokeLater(new Runnable() {

							@Override
							public void run() {
								reader.readRobotData();
								
								contribution.setJointsPositionsMinMax(reader.getActualJointPositions(), "Joint Positions");
								contribution.setToolPositions(reader.getActualToolVector(), "Tool Positions");
								contribution.setTCPSpeedMaxMin(reader.getActualTCPSpeed(), "TCP Speed");

							}
						});

					}
				}, 0, 1000);

			}

		});

		return buttonStart;
	}

	private JButton createStopButton(final MonitorProgramNodeContribution contribution) {

		buttonStop = new JButton();
		buttonStop.setPreferredSize(new Dimension(100, 30));
		buttonStop.setMaximumSize(buttonStop.getPreferredSize());
		buttonStop.setText("Stop");

		buttonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// sets the button to clickable
				buttonStart.setEnabled(true);
				buttonClear.setEnabled(true);

				if (timer != null) {
					timer.cancel();

					// setting the Joint positions
					contribution.showJointPositions();

					// setting the Tool Position x,y,z
					contribution.showToolPositions();

					// setting the tcp speed
					contribution.showTCPSpeed();
				}

			}

		});

		return buttonStop;
	}

	private Component createHorizontalSpacing(int width) {
		return Box.createRigidArea(new Dimension(width, 0));
	}

	private Component createVerticalSpacing(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}

	// setters for joint positions textfields
	public void setBaseFieldMin(String text) {
		this.baseFieldMin.setText(text);
	}

	public void setShoulderFieldMin(String text) {
		this.shoulderFieldMin.setText(text);
	}

	public void setElbowFieldMin(String text) {
		this.elbowFieldMin.setText(text);
	}

	public void setWrist1FieldMin(String text) {
		this.wrist1FieldMin.setText(text);
	}

	public void setWrist2FieldMin(String text) {
		this.wrist2FieldMin.setText(text);
	}

	public void setWrist3FieldMin(String text) {
		this.wrist3FieldMin.setText(text);
	}

	public void setBaseFieldMax(String text) {
		this.baseFieldMax.setText(text);
	}

	public void setShoulderFieldMax(String text) {
		this.shoulderFieldMax.setText(text);
	}

	public void setElbowFieldMax(String text) {
		this.elbowFieldMax.setText(text);
	}

	public void setWrist1FieldMax(String text) {
		this.wrist1FieldMax.setText(text);
	}

	public void setWrist2FieldMax(String text) {
		this.wrist2FieldMax.setText(text);
	}

	public void setWrist3FieldMax(String text) {
		this.wrist3FieldMax.setText(text);
	}

	// setters for tool position x,y,z
	public void setPositionXmin(String text) {
		this.positionXmin.setText(text);
	}

	public void setPositionXmax(String text) {
		this.positionXmax.setText(text);
	}

	public void setPositionYmin(String text) {
		this.positionYmin.setText(text);
	}

	public void setPositionYmax(String text) {
		this.positionYmax.setText(text);
	}

	public void setPositionZmin(String text) {
		this.positionZmin.setText(text);
	}

	public void setPositionZmax(String text) {
		this.positionZmax.setText(text);
	}

	// setters for speed

	public void setSpeedMin(String text) {
		this.speedMin.setText(text);
	}

	public void setSpeedMax(String text) {
		this.speedMax.setText(text);
	}

}
