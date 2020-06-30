package com.urcap.samplesForSwing.impl;

import javax.xml.crypto.Data;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class samplesProgramNodeContribution implements ProgramNodeContribution {
	
	private final ProgramAPIProvider apiProvider;
	private final samplesProgramNodeView view;
	private final DataModel model;
	
	public samplesProgramNodeContribution(ProgramAPIProvider apiProvider, samplesProgramNodeView view,
			DataModel model) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
	}

	@Override
	public void openView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "sampling";
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
