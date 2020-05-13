package myplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
		
		String outputPath = getOutputPath();
		
		GeneratorOptions springModelOptions = new GeneratorOptions(outputPath+"\\sc - backend\\src\\main\\java", "springModel", "templates", "model/{0}.java",
				true, "model");
		springModelOptions.setTemplateDir(pluginDir + File.separator + springModelOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringModelGenerator", springModelOptions);

		GeneratorOptions springRepoOptions = new GeneratorOptions(outputPath+"\\sc - backend\\src\\main\\java", "springRepository", "templates",
				"repository/base/{0}RepositoryBase.java", true, "repository");
		springRepoOptions.setTemplateDir(pluginDir + File.separator + springRepoOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringRepoGenerator", springRepoOptions);

		GeneratorOptions springServiceOptions = new GeneratorOptions(outputPath+"\\sc - backend\\src\\main\\java", "springService", "templates",
				"service/base/{0}ServiceBase.java", true, "service");
		springServiceOptions.setTemplateDir(pluginDir + File.separator + springServiceOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringServiceGenerator", springServiceOptions);

		GeneratorOptions springControllerOptions = new GeneratorOptions(outputPath+"\\sc - backend\\src\\main\\java", "springController", "templates",
				"controller/base/{0}ControllerBase.java", true, "controller");
		springControllerOptions.setTemplateDir(pluginDir + File.separator + springControllerOptions.getTemplateDir());
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("SpringControllerGenerator", springControllerOptions);

	}
	
	private String getOutputPath() {
		String output_path = "";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(pluginDir + File.separator + "app.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			output_path = prop.getProperty("app.scpath");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return output_path;
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
