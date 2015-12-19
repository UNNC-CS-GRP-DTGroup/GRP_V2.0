package test.JWord;

import java.awt.BorderLayout; import java.awt.Container;
import javax.swing.JFrame; import javax.swing.JScrollPane;
import com.pilot.jword.JWordDocument;
import com.pilot.jword.JWordEditorKit; import com.pilot.jword.JWordTextPane;

public class EditorFrame extends JFrame {
	private JWordTextPane textComp;
	public static JWordEditorKit editorKit;
	
	public static void main(String[] args) {
		editorKit = new JWordEditorKit();
		EditorFrame editor = new EditorFrame(); 
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		editor.setVisible(true);
	}
	
	// Create the editor.
	public EditorFrame() {
		super();
		textComp = createTextComponent();
		JScrollPane scrollEdit = new JScrollPane(textComp); 
		Container content = getContentPane(); 
		content.add(scrollEdit, BorderLayout.CENTER); 
		setSize(320, 540);
	}
    
	// Create the JWordTextPane.
	protected JWordTextPane createTextComponent() {
		JWordDocument doc=(JWordDocument) editorKit.createDefaultDocument(); 
		JWordTextPane tc=new JWordTextPane(doc); tc.setViewMode(JWordTextPane.VM_NORMAL, true); 
		tc.setEditorKit(editorKit);
		((JWordDocument) tc.getDocument()).init();
		return tc; 
	}
}
