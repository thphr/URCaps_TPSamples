package com.ur.StyleGuideExample.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class StyleProgramNodeService implements SwingProgramNodeService<StyleProgramNodeContribution, StyleProgramNodeView> {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "StyleGuide";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "My Style Guide Example";
	}

	@Override
	public StyleProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new StyleProgramNodeView(apiProvider);
	}

	@Override
	public StyleProgramNodeContribution createNode(ProgramAPIProvider apiProvider, StyleProgramNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new StyleProgramNodeContribution(apiProvider, view, model, context);
	}

}
