package com.ur.RobotMonitor.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class MonitorProgramNodeService implements SwingInstallationNodeService<MonitorProgramNodeContribution, MonitorProgramNodeView>{

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {

		return "Motion Boundary Check";
	}

	@Override
	public MonitorProgramNodeView createView(ViewAPIProvider apiProvider) {

		return new MonitorProgramNodeView();
	}

	@Override
	public MonitorProgramNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			MonitorProgramNodeView view, DataModel model, CreationContext context) {

		return new MonitorProgramNodeContribution(apiProvider,model,view);
	}



}
