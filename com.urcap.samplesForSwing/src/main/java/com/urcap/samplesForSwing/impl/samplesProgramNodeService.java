package com.urcap.samplesForSwing.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class samplesProgramNodeService implements SwingProgramNodeService<samplesProgramNodeContribution, samplesProgramNodeView> {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "sample_Swing";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setChildrenAllowed(false);
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "SAMPLES";
	}

	@Override
	public samplesProgramNodeView createView(ViewAPIProvider apiProvider) {
		
		return new samplesProgramNodeView(apiProvider);
	}

	@Override
	public samplesProgramNodeContribution createNode(ProgramAPIProvider apiProvider, samplesProgramNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new samplesProgramNodeContribution(apiProvider, view, model);
	}

}
