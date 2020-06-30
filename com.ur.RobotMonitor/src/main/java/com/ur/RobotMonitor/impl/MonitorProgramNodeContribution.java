package com.ur.RobotMonitor.impl;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class MonitorProgramNodeContribution implements InstallationNodeContribution {

	private DataModel model;
	private MonitorProgramNodeView view;

	private double[] minList;
	private double[] maxList;

	private double[] toolPositionListMin;
	private double[] toolPositionListMax;

	private double speedMinValue;
	private double speedMaxValue;

	public MonitorProgramNodeContribution(InstallationAPIProvider apiProvider, DataModel model,
			MonitorProgramNodeView view) {
		this.model = model;
		this.view = view;

		this.setPositionListToZero();
		this.setToolListToZero();
		this.setSpeedListToZero();
	}

	public void setPositionListToZero() {
		minList = new double[6];
		maxList = new double[6];
		toolPositionListMax = new double[6];
		toolPositionListMin = new double[6];

		for (int i = 0; i < maxList.length; i++) {
			minList[i] = 0;
			maxList[i] = 0;
			toolPositionListMax[i] = 0;
			toolPositionListMin[i] = 0;
		}
	}

	public void setToolListToZero() {
		toolPositionListMax = new double[6];
		toolPositionListMin = new double[6];

		for (int i = 0; i < toolPositionListMax.length; i++) {
			toolPositionListMax[i] = 0;
			toolPositionListMin[i] = 0;
		}
	}

	public void setSpeedListToZero() {
		this.speedMaxValue = 0;
		this.speedMinValue = 0;
	}

	@Override
	public void openView() {

	}

	@Override
	public void closeView() {

	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub

	}
/**
 * Finds the maximum and minimum value of each joint position.
 * @param listValues
 * @param text
 */
	public void setJointsPositionsMinMax(double[] listValues, String text) {

		double[] list = listValues;

		for (int i = 0; i < list.length; i++) {

			if (minList[i] == 0) {
				minList[i] = list[i];
			}

			if (maxList[i] == 0) {
				maxList[i] = list[i];
			}

			if (list[i] < minList[i]) {
				minList[i] = list[i];
			}

			if (list[i] > maxList[i]) {
				maxList[i] = list[i];

			}
			System.out.println(text + " : " + list[i]);
		}

	}

	public void showJointPositions() {

		DecimalFormat df = new DecimalFormat("#.####");

		view.setBaseFieldMin("" + df.format(minList[0]));
		view.setShoulderFieldMin("" + df.format(minList[1]));
		view.setElbowFieldMin("" + df.format(minList[2]));
		view.setWrist1FieldMin("" + df.format(minList[3]));
		view.setWrist2FieldMin("" + df.format(minList[4]));
		view.setWrist3FieldMin("" + df.format(minList[5]));

		view.setBaseFieldMax("" + df.format(maxList[0]));
		view.setShoulderFieldMax("" + df.format(maxList[1]));
		view.setElbowFieldMax("" + df.format(maxList[2]));
		view.setWrist1FieldMax("" + df.format(maxList[3]));
		view.setWrist2FieldMax("" + df.format(maxList[4]));
		view.setWrist3FieldMax("" + df.format(maxList[5]));
	}

	public void setToolPositions(double[] listValues, String text) {

		double[] list = listValues;

		for (int i = 0; i < list.length; i++) {

			if (toolPositionListMin[i] == 0) {
				toolPositionListMin[i] = list[i];
				System.out.println("sat: " + toolPositionListMin[i]);
			}

			if (toolPositionListMax[i] == 0) {
				toolPositionListMax[i] = list[i];
				System.out.println("sat: " + toolPositionListMax[i]);
			}

			if (list[i] < toolPositionListMin[i]) {
				toolPositionListMin[i] = list[i];
			}

			if (list[i] > toolPositionListMax[i]) {
				toolPositionListMax[i] = list[i];

			}
			System.out.println(text + " : " + list[i]);
		}
	}

	public void showToolPositions() {

		DecimalFormat df = new DecimalFormat("#.####");

		view.setPositionXmin("" + df.format(toolPositionListMin[0]));
		view.setPositionYmin("" + df.format(toolPositionListMin[1]));
		view.setPositionZmin("" + df.format(toolPositionListMin[2]));

		view.setPositionXmax("" + df.format(toolPositionListMax[0]));
		view.setPositionYmax("" + df.format(toolPositionListMax[1]));
		view.setPositionZmax("" + df.format(toolPositionListMax[2]));
	}

	
	public void setTCPSpeedMaxMin(double[] listValues, String text) {

		double[] list = listValues;
		double currentSpeed = 0;

		for (int i = 0; i < list.length - 3; i++) {

			System.out.println(text + " : " + list[i]);

		}

		if (list[0] != 0 || list[1] != 0 || list[2] != 0) {
			currentSpeed = Math.sqrt(Math.pow(list[0], 2) + Math.pow(list[1], 2) + Math.pow(list[2], 2));
		}

		if (this.speedMinValue == 0 && currentSpeed != 0) {

			this.speedMinValue = currentSpeed;
		}

		if (this.speedMaxValue == 0 && currentSpeed != 0) {

			this.speedMaxValue = currentSpeed;
		}

		if (currentSpeed > this.speedMaxValue && currentSpeed != 0) {
			this.speedMaxValue = currentSpeed;
		}

		if (currentSpeed < this.speedMinValue && currentSpeed != 0) {
			this.speedMinValue = currentSpeed;
		}

	}
	
	public void showTCPSpeed() {
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		view.setSpeedMin(df.format(speedMinValue));
		view.setSpeedMax(df.format(speedMaxValue));
	}

}
