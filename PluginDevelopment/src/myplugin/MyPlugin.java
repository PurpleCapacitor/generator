package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {

	String pluginDir = null;

	public void init() {
		JOptionPane.showMessageDialog(null, "My Plugin init");

		pluginDir = getDescriptor().getPluginDirectory().getPath();

		// Creating submenu in the MagicDraw main menu
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));

		/**
		 * @Todo: load project options (@see myplugin.generator.options.ProjectOptions)
		 *        from ProjectOptions.xml and take ejb generator options
		 */

		// for test purpose only:
		/*
		 * GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass",
		 * "templates", "{0}.java", true, "ejb");
		 * ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator",
		 * ejbOptions);
		 * 
		 * ejbOptions.setTemplateDir(pluginDir + File.separator +
		 * ejbOptions.getTemplateDir()); // apsolutna putanja
		 */
		// spring
		GeneratorOptions springModelOptions = new GeneratorOptions("c:/temp", "springModel", "templates", "{0}.java",
				true, "model");
		springModelOptions.setTemplateDir(pluginDir + File.separator + springModelOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringModelGenerator", springModelOptions);

		GeneratorOptions springRepoOptions = new GeneratorOptions("c:/temp", "springRepository", "templates",
				"{0}Repository.java", true, "repository");
		springRepoOptions.setTemplateDir(pluginDir + File.separator + springRepoOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringRepoGenerator", springRepoOptions);

		GeneratorOptions springServiceOptions = new GeneratorOptions("c:/temp", "springService", "templates",
				"{0}Service.java", true, "service");
		springServiceOptions.setTemplateDir(pluginDir + File.separator + springServiceOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringServiceGenerator", springServiceOptions);

		GeneratorOptions springControllerOptions = new GeneratorOptions("c:/temp", "springController", "templates",
				"{0}Controller.java", true, "controller");
		springControllerOptions.setTemplateDir(pluginDir + File.separator + springControllerOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringControllerGenerator", springControllerOptions);

	}

	private NMAction[] getSubmenuActions() {
		return new NMAction[] { new GenerateAction("Generate"), };
	}

	public boolean close() {
		return true;
	}

	public boolean isSupported() {
		return true;
	}
}
