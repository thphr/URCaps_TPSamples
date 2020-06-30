package com.ur.RobotMonitor.impl;

import java.awt.font.NumericShaper;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Math;

public class RealtimeReader {

	private final String IP;
	private final int PORT;
	private double[] robotData;

	public RealtimeReader() {

		this.IP = "localhost";
		this.PORT = 30003;
	}

	public void readRobotData() {

		try {
			Socket client = new Socket(IP, PORT);

			if (client.isConnected()) {
				System.out.println("Client is connected!");
			}

			// reads the byte input
			DataInputStream input = new DataInputStream(client.getInputStream());
			int length = input.readInt();
			System.out.println("The length of the data is: " + length);

			// finds the length of the data package
			this.robotData = new double[length];

			// calculates the data available, where each is 8 bytes minus the first 4 bytes
			// which is the message.
			// data available is the amount of 8 bytes in the data package.
			int dataAvailable = (length - 4) / 8;
			this.robotData[0] = dataAvailable;

			System.out.println("The amount of available data is: " + dataAvailable);

			// save the input in an array
			for (int i = 1; i < dataAvailable; i++) {

				// reads each 8 bytes into each index in the array.
				robotData[i] = input.readDouble();
				// System.out.println("The input in index " + i + " is " + this.robotData[i]);

			}

			input.close();
			client.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private enum RobotDateInfo {

		q_actual(32, 6), Tool_vector_actual(56, 6), TCP_speed_actual(62, 6);

		private int index;
		private int numberOfValues;

		RobotDateInfo(int index, int numberOfValues) {
			this.index = index;
			this.numberOfValues = numberOfValues;
		}

	}

	public double[] getActualJointPositions() {
		double[] jPositions = new double[RobotDateInfo.q_actual.numberOfValues];
		int i = 0;
		while (i < RobotDateInfo.q_actual.numberOfValues) {

			double valueInDegree = Math.toDegrees(this.robotData[RobotDateInfo.q_actual.index + i]);
			jPositions[i] = valueInDegree;
			i++;
		}

		return jPositions;
	}

	public double[] getActualTCPSpeed() {
		double[] tcpSpeeds = new double[RobotDateInfo.TCP_speed_actual.numberOfValues];
		int i = 0;
		while (i < RobotDateInfo.TCP_speed_actual.numberOfValues) {

			tcpSpeeds[i] = this.robotData[RobotDateInfo.TCP_speed_actual.index + i];
			i++;
		}

		return tcpSpeeds;

	}

	public double[] getActualToolVector() {
		double[] toolVectors = new double[RobotDateInfo.Tool_vector_actual.numberOfValues];
		int i = 0;
		while (i < RobotDateInfo.Tool_vector_actual.numberOfValues) {

			double valueInmm = this.robotData[RobotDateInfo.Tool_vector_actual.index + i] * 1000;
			toolVectors[i] = valueInmm;
			i++;
		}

		return toolVectors;

	}

}
