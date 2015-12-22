package test.JWord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.pilot.jword.ui.JWordController;
import com.pilot.kahve.GuiException;


public class JWordClient {
	public static void main(String[] args) {
		JWordController ctrl=new JWordController();
		try {
			ctrl.init(false);
		} catch (GuiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ctrl.setShowRuler(false);
		//ctrl.setShowToolbar1(false);
		//ctrl.setShowToolbar2(false);
		//ctrl.setShowMenu(false);
		//ctrl.setShowStatusBar(false); //ctrl.setShowMenuItem(JWordController.mi_open, false); //ctrl.setShowToolbarItem(JWordController.tb_open, false); //ctrl.getEditor().setViewMode(JWordTextPane.VM_NORMAL, true); //ctrl.setHtml;
		ctrl.getFrame().setVisible(true); 
		ctrl.getEditor().requestFocus();
		System.out.println(ctrl.getFrame().getJMenuBar().getMenu(0).getItem(3));
		(ctrl.getFrame().getJMenuBar().getMenu(0)).add(new JMenuItem("Export as pdf"));
	}

}
