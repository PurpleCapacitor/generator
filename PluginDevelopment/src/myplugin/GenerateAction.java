package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.EJBGenerator;
import myplugin.generator.SpringGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction {

	public GenerateAction(String name) {
		super("", name, null, null);
	}

	public void actionPerformed(ActionEvent evt) {

		if (Application.getInstance().getProject() == null)
			return;
		Package root = Application.getInstance().getProject().getModel(); // valjda je ovo trebalo promeniti na get
																			// model zbog verzije

		if (root == null)
			return;

		ModelAnalyzer analyzer = new ModelAnalyzer(root, "ftn.upp.sc");

		try {
			analyzer.prepareModel();
			/*
			 * GeneratorOptions go =
			 * ProjectOptions.getProjectOptions().getGeneratorOptions().get("EJBGenerator");
			 * EJBGenerator generator = new EJBGenerator(go); generator.generate();
			 */
			/** @ToDo: Also call other generators */
			
			GeneratorOptions go1 = ProjectOptions.getProjectOptions().getGeneratorOptions().get("SpringModelGenerator");
			SpringGenerator springModelGenerator = new SpringGenerator(go1);
			springModelGenerator.generate(true);
			
			GeneratorOptions go2 = ProjectOptions.getProjectOptions().getGeneratorOptions().get("SpringRepoGenerator");
			SpringGenerator springRepoGenerator = new SpringGenerator(go2);
			springRepoGenerator.generate(false);
			
			GeneratorOptions go3 = ProjectOptions.getProjectOptions().getGeneratorOptions().get("SpringServiceGenerator");
			SpringGenerator springServiceGenerator = new SpringGenerator(go3);
			springServiceGenerator.generate(false);
			
			GeneratorOptions go4 = ProjectOptions.getProjectOptions().getGeneratorOptions().get("SpringControllerGenerator");
			SpringGenerator springControllerGenerator = new SpringGenerator(go4);
			springControllerGenerator.generate(false);

			JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
					+ go1.getOutputPath());
			exportToXml();
		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void exportToXml() {
//		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == JOptionPane.OK_OPTION) {
//			JFileChooser jfc = new JFileChooser();
//			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//				String fileName = jfc.getSelectedFile().getAbsolutePath();
//
//				XStream xstream = new XStream(new DomDriver());
//				BufferedWriter out;
//				try {
//					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
//					xstream.toXML(FMModel.getInstance().getClasses(), out);
//					xstream.toXML(FMModel.getInstance().getEnumerations(), out);
//
//				} catch (UnsupportedEncodingException e) {
//					JOptionPane.showMessageDialog(null, e.getMessage());
//				} catch (FileNotFoundException e) {
//					JOptionPane.showMessageDialog(null, e.getMessage());
//				}
//			}
//		}
	}

}
