package com.urcap.samplesForSwing.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class samplesProgramNodeView implements SwingProgramNodeView<samplesProgramNodeContribution>, ActionListener {

	private final ViewAPIProvider provider;
	private Integer[] values = { 0, 1, 2, 3, 4, 5 };
	private JTextField field = new JTextField();
	private JComboBox<String> comboBox = new JComboBox<String>();

	public samplesProgramNodeView(ViewAPIProvider provider) {

		this.provider = provider;

	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<samplesProgramNodeContribution> provider) {

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createLabel("Slider:"));
		panel.add(createSpacer(10));
		panel.add(createSlider(0, 10));
		panel.add(createSpacer(30));
		panel.add(createLabel("ComboBox:"));
		panel.add(createSpacer(10));
		panel.add(createJcomboBox(comboBox, values));
		panel.add(createSpacer(30));
		panel.add(createLabel("Button:"));
		panel.add(createSpacer(10));
		panel.add(createJButton());
		panel.add(createSpacer(30));
		panel.add(createLabel("RadioButton: "));
		panel.add(createSpacer(10));
		panel.add(createJRadioButton(this.field));

	}

	private Box createLabel(String text) {

		JLabel label = new JLabel();

		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		Font boldFont = new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize());
		label.setFont(boldFont);

		label.setPreferredSize(new Dimension(300, 30));
		label.setMaximumSize(label.getPreferredSize());
		label.setText(text);

		box.add(label);

		return box;
	}

	private Box createSlider(int min, int max) {

		final JSlider slider = new JSlider();

		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		final JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(300, 30));
		textField.setMaximumSize(textField.getPreferredSize());

		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setPreferredSize(new Dimension(300, 30));
		slider.setMaximumSize(slider.getPreferredSize());

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				int value = slider.getValue();
				textField.setText("Selected value: " + Integer.toString(value));

			}
		});

		box.add(slider);
		box.add(textField);

		return box;

	}

	private Box createJcomboBox(JComboBox comboBox, Integer[] items) {

		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		comboBox.setPreferredSize(new Dimension(300, 30));
		comboBox.setMaximumSize(comboBox.getPreferredSize());
		comboBox.setEditable(false);

		comboBox.removeAllItems();
		comboBox.setModel(new DefaultComboBoxModel<Integer>(items));

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if (arg0.getStateChange() == ItemEvent.SELECTED) {

					Integer value = (Integer) arg0.getItem();

				}
			}
		});

		box.add(comboBox);

		return box;

	}

	private Box createJButton() {

		JButton button = new JButton();

		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		button.setText("Click me!");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		box.add(button);

		return box;

	}

	private Box createJRadioButton(JTextField field) {

		String firstBText = "Hello";
		String secondBText = "Hey";
		String thirdBText = "Hi";

		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);

		JRadioButton firstButton = new JRadioButton(firstBText);
		firstButton.setMnemonic(KeyEvent.VK_F);
		firstButton.setActionCommand(firstBText);
		firstButton.setSelected(true);

		JRadioButton secondButton = new JRadioButton(secondBText);
		secondButton.setMnemonic(KeyEvent.VK_S);
		secondButton.setActionCommand(secondBText);

		JRadioButton thirdButton = new JRadioButton(thirdBText);
		thirdButton.setMnemonic(KeyEvent.VK_T);
		thirdButton.setActionCommand(thirdBText);

		ButtonGroup group = new ButtonGroup();
		group.add(firstButton);
		group.add(secondButton);
		group.add(thirdButton);

		firstButton.addActionListener(this);
		secondButton.addActionListener(this);
		thirdButton.addActionListener(this);

		box.add(firstButton);
		box.add(secondButton);
		box.add(thirdButton);
		box.add(createSpacer(10));
		box.add(field);

		return box;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.field.setText(arg0.getActionCommand());
	}

	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}

}
