package test.JWord;

import com.pilot.jword.ui.JWordController;
import com.pilot.kahve.GuiException;


public class JWordClient {
	public static void main(String[] args) {
		try
		{
		JWordController ctrl=new JWordController();
		ctrl.init(false);
		//ctrl.setShowRuler(false);
		//ctrl.setShowToolbar1(false);
		//ctrl.setShowToolbar2(false);
		//ctrl.setShowMenu(false);
		//ctrl.setShowStatusBar(false); //ctrl.setShowMenuItem(JWordController.mi_open, false); //ctrl.setShowToolbarItem(JWordController.tb_open, false); //ctrl.getEditor().setViewMode(JWordTextPane.VM_NORMAL, true); //ctrl.setHtml;
		ctrl.getFrame().setVisible(true); ctrl.getEditor().requestFocus();
		}
		catch (GuiException e) {
		         e.printStackTrace();
		}
	}

}
