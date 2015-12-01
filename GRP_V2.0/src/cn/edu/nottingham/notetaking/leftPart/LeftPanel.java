package cn.edu.nottingham.notetaking.leftPart;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.*;

import cn.edu.nottingham.notetaking.csv.CsvBuilder;

/**
 * This is class including functions on leftpanel and basic settings of
 * leftpanel.
 *
 * @author RUNFA LV,YUFAN SHAO,WENYU DU
 */
public class LeftPanel {
    // variables
    JButton add;
    JButton remove;
    JButton save;
    JScrollPane jsp;
    JTextArea area;
    JTree tree;
    String title;
    final MyTextPane tp;
    TitledBorder tb;
    DefaultMutableTreeNode child, selected, root;
    DefaultTreeModel model;
    // inisialize the leftside panel.
    JPanel leftside = new JPanel();
   
    
    
    // get method for leftpanel.
    public JPanel getLeft() {
	return leftside;
    }

    // baic settings of leftpanel.
    public LeftPanel() {

	// leftside layout, and it is devided into two parts.
	leftside.setLayout(new BorderLayout());
	JPanel books = new JPanel();
	JPanel text = new JPanel();
	leftside.add(books, BorderLayout.WEST);
	leftside.add(text, BorderLayout.CENTER);

	// lefthand side First part - catalogue tree
	books.setPreferredSize(new Dimension(200, 500));

	// set layout of part 1.
	JPanel addAndDel = new JPanel();
	JPanel sort = new JPanel();
	books.setLayout(new BorderLayout());

	// set root directory of the catalogue tree
	root = new DefaultMutableTreeNode("NoteBooks");
	final File rootDir = new File("./Notebooks");
	

	// creating the catalogue tree
	tree = new JTree(root);
	
	
	
	
	model = (DefaultTreeModel) tree.getModel();
	if (rootDir.exists()) {// reading from disk if there exsists something
			       // already.
	    orderByDate(rootDir.toString(), root);
	}

	// put it on the scrollpanel
	JScrollPane sp = new JScrollPane(tree);

	// set the vertical scrollbar always visible
	sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	// add components to the part1
	books.add(addAndDel, BorderLayout.NORTH);
	books.add(sp, BorderLayout.CENTER);
	books.add(sort, BorderLayout.SOUTH);

	// add and delete button above the tree - addAndDel
	add = new JButton("+");
	remove = new JButton("-");
	addAndDel.setLayout(new GridLayout(1, 2));

	// add buttons to panel.
	addAndDel.add(add);
	addAndDel.add(remove);

	// action listener for add and delete
	// add button
	add.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		selected = (DefaultMutableTreeNode) tree
			.getLastSelectedPathComponent();
		if (selected == null) {
		    selected = root;
		}

		String path = tree2Path(tree.getSelectionPath().toString());

		if (new File(path).isFile()) {
		    return;
		}

		String content = JOptionPane
			.showInputDialog("Please Enter the name:");
		if (content != null) {
		    Object[] possibleValues = { "Note", "NoteBook" };
		    Object selectedValue = JOptionPane.showInputDialog(null,
			    "New Note OR NoteBook?", "Input",
			    JOptionPane.INFORMATION_MESSAGE, null,
			    possibleValues, possibleValues[0]);
		    if (selectedValue == "Note") {
			content += ".not";
			child = new DefaultMutableTreeNode(content, false);
			model.insertNodeInto(child, selected, 0);
			path = path + File.separator + content;
			File file = new File(path);
			tp.setText("");
			saveFile(file);
			CsvBuilder.setNotePath(content);
		    } else if (selectedValue == "NoteBook") {
			child = new DefaultMutableTreeNode(content, true);
			model.insertNodeInto(child, selected, 0);

			path = path + File.separator + content;
			File file = new File(path);
			if (!file.exists()) {
			    file.mkdirs();
			}
		    }
		}
	    }

	});
	// delete button
	remove.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		selected = (DefaultMutableTreeNode) tree
			.getLastSelectedPathComponent();
		if (selected != null) {
		    try {
			String message = "Are you sure to delete ";
			String path = tree2Path(tree.getSelectionPath()
				.toString());
			File file = new File(path);
			if (file.isDirectory()) {
			    message = message + selected.toString()
				    + " and all the files in it?";
			} else if (file.isFile()) {
			    message = message + selected.toString() + "?";
			}

			int confirm = JOptionPane.showConfirmDialog(leftside,
				message, "Confirm", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {

			    delDir(file);
			    if (!file.exists()) {
				model.removeNodeFromParent(selected);
			    }

			    tp.setText("");
			}

		    } catch (Exception exp) {
			JOptionPane.showMessageDialog(leftside,
				"Node does not have a parent.", "Error",
				JOptionPane.ERROR_MESSAGE);
		    }

		}

	    }

	});

	// sort by name or date button below the tree - Sort
	sort.setLayout(new GridLayout(1, 2));
	JButton sort1 = new JButton("SortByName");
	JButton sort2 = new JButton("SortByDate");
	sort.add(sort1);
	sort.add(sort2);

	// sort by name
	sort1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		selected = (DefaultMutableTreeNode) tree
			.getLastSelectedPathComponent();
		String path;
		if (selected == null) {
		    selected = root;
		    path = tree2Path(root.toString());
		} else {
		    path = tree2Path(tree.getSelectionPath().toString());
		}

		selected.removeAllChildren();
		orderByName(path, selected);

		model.reload(selected);

	    }

	});

	// sort by date
	sort2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		selected = (DefaultMutableTreeNode) tree
			.getLastSelectedPathComponent();
		String path;
		if (selected == null) {
		    selected = root;
		    path = tree2Path(root.toString());
		} else {
		    path = tree2Path(tree.getSelectionPath().toString());
		}

		selected.removeAllChildren();
		orderByDate(path, selected);

		model.reload(selected);

	    }

	});

	// open the file from disk using double click
	tree.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    openFile();
		}
		if(e.isMetaDown()==true){
			openMenu(e);
		}
		}
	    
	    
	});

	// lefthand side second part - TextPane
	tp = new MyTextPane();

	// respond hyperlink event in textpane
	tp.addHyperlinkListener(new HyperlinkListener() {
	    @Override
	    public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() != HyperlinkEvent.EventType.ACTIVATED)
		    return;

		URL linkUrl = e.getURL();
		if (linkUrl != null) {
		    try {
			Desktop.getDesktop().browse(linkUrl.toURI());
		    } catch (URISyntaxException | IOException e1) {
			JOptionPane.showMessageDialog(null,
				"Hperlink  is wrong", "Can not open the link:"
					+ linkUrl + "\n:" + e1,
				JOptionPane.ERROR_MESSAGE);
		    }
		} else {
		    JOptionPane
			    .showMessageDialog(
				    null,
				    "Hperlink is wrong",
				    "Hyperlink info is not full:"
					    + e.getDescription()
					    + "\n please make sure the infomation����http://,mailto:",
				    JOptionPane.ERROR_MESSAGE);
		}
	    }

	});

	JPanel panel = new JPanel();
	JButton btn1 = new JButton("EDIT");
	JButton btn2 = new JButton("LINK");
	// start edit mode
	btn1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		tp.setEditable(true);
	    }
	});
	// start link mode
	btn2.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		tp.setEditable(false);
	    }
	});

	// text pane layout setting
	tp.setEditable(true);
	JScrollPane textScrollPane = new JScrollPane(tp);
	textScrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	text.setLayout(new BorderLayout());
	text.add(panel, BorderLayout.NORTH);
	text.add(textScrollPane, BorderLayout.CENTER);
	GridLayout panellayout = new GridLayout(1, 2);
	panel.setLayout(panellayout);

	// add buttons to panel
	panel.add(btn1);
	panel.add(btn2);

	// save button and its settings
	JPanel button = new JPanel();
	text.add(button, BorderLayout.SOUTH);
	title = "New Note";
	button.setLayout(new GridLayout(1, 3));
	save = new JButton("save");
	button.add(save);
	save.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String path = tree2Path(tree.getSelectionPath().toString());
		String pwd = path;
		File file = new File(pwd);
		saveFile(file);
	    }
	});

    }

    // transfer treenode[] to filepath
    private String tree2Path(String s) {
	String result;

	String[] split = s.split(", ");
	int l = split.length;
	split[0] = split[0].replace("[", "");
	split[l - 1] = split[l - 1].replace("]", "");
	result = split[0];
	for (int i = 1; i < l; i++) {
	    result = result + "/" + split[i];
	}
	return "./" + result;
    }

    // delete the whole directory
    private static boolean delDir(File dir) {

	if (dir.isDirectory()) {

	    String[] childFiles = dir.list();
	    for (String childFile : childFiles) {
		boolean b = delDir(new File(dir, childFile));
		if (!b) {
		    return false;
		}
	    }
	}
	return dir.delete();
    }

    // order catalogue by name
    private void orderByName(String fliePath, DefaultMutableTreeNode node) {
	File file = new File(fliePath);
	File[] fs = file.listFiles();
	Arrays.sort(fs, new Comparator<File>() {
	    @Override
	    public int compare(File o1, File o2) {
		if (o1.isDirectory() && o2.isFile())
		    return -1;
		if (o1.isFile() && o2.isDirectory())
		    return 1;
		return o2.getName().compareTo(o1.getName());
	    }
	});
	for (int i = fs.length - 1; i > -1; i--) {
	    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
		    fs[i].getName(), true);
	    if (fs[i].isDirectory()) {
		node.add(childNode);
		orderByName(fs[i].toString(), childNode);
	    } else if(fs[i].toString().endsWith(".not")){
                node.add(childNode);
            }
	}
    }

    // order catalogue by date
    private void orderByDate(String fliePath, DefaultMutableTreeNode node) {
	File file = new File(fliePath);
	File[] fs = file.listFiles();
	Arrays.sort(fs, new Comparator<File>() {
	    @Override
	    public int compare(File f1, File f2) {
		long diff = f1.lastModified() - f2.lastModified();
		if (diff > 0)
		    return 1;
		else if (diff == 0)
		    return 0;
		else
		    return -1;
	    }

	    @Override
	    public boolean equals(Object obj) {
		return true;
	    }

	});
	for (int i = fs.length - 1; i > -1; i--) {
	    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
		    fs[i].getName(), true);
	    if (fs[i].isDirectory()) {
		node.add(childNode);
		orderByDate(fs[i].toString(), childNode);
	    } else if(fs[i].toString().endsWith(".not")){
                node.add(childNode);
            }
	}
    }

    // function for save the file to the disk.
    public void saveFile(File file) {
	try {

	    if (!file.exists()) {
		file.createNewFile();
	    }

	    OutputStream os = new FileOutputStream(file.getPath());
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	    String[] linestring = tp.getText().split("\n");
	    for (String linestring1 : linestring) {
		bw.write(linestring1);
		bw.newLine();
		bw.flush();
	    }
	    bw.close();
	} catch (Exception exp) {
	    JOptionPane.showMessageDialog(leftside, "Invalid Path.", "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    // function for open the file from
    public void openFile() {

	String path = tree2Path(tree.getSelectionPath().toString());
	if (!path.endsWith(".not")) {
	    return;
	}
	StringBuilder not = new StringBuilder();
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	    String data = br.readLine();
	    while (data != null) {
		not.append(data);
		not.append("\n");
		data = br.readLine();
	    }
	    tp.setText(not.toString());
	    CsvBuilder.setNotePath(path);
	}

	catch (Exception exp) {
	    JOptionPane.showMessageDialog(leftside, "Can not find file.",
		    "Error", JOptionPane.ERROR_MESSAGE);

	}
    }
    
    public void openMenu(MouseEvent e){
    	 JPopupMenu menu = new JPopupMenu("Tag");
    	 
    	JMenuItem openTag = new JMenuItem("OpenTag");
    	JMenuItem deleteTag = new JMenuItem("DeleteTag");
    	JMenuItem searchTag = new JMenuItem("SearchTag");
    	
    	menu.add(openTag);
    	menu.add(deleteTag);
    	menu.add(searchTag);
    	
    	if( tree.getSelectionPath()==null){
    		menu.remove(openTag);
    		menu.remove(deleteTag);
    	}
    	
    	menu.show(e.getComponent(), e.getX(), e.getY());
    	
    	
    	openTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openTag();
			}
		});
    
    	deleteTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTag();
			}
		});
    	
    	searchTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchTag();
			}
		});
    	
    }
    
    
    public void openTag() {
    	
    	TreePath path = tree.getSelectionPath();
    	
    	String input = JOptionPane.showInputDialog(TagsWithNotes.search(path));
    	
    	TagsWithNotes.addTag(path, input);
    	
    	TagsWithNotes.write();
    	
    	TagsWithNotes.demoPrint();
    	
    }
    
    public void deleteTag(){
    	TreePath path = tree.getSelectionPath();
    	
    	String originalTags = TagsWithNotes.search(path);
    	
    	if(!originalTags.endsWith("]")||originalTags.equals("Tags:[]")){
    		JOptionPane.showMessageDialog(null, "No tags now!");
    		return;
    	}
    	
    	String tags = (String) originalTags.subSequence(6, originalTags.length()-1);
    	String[] tagList = tags.split(",");
    	
    	Object tagObject = JOptionPane.showInputDialog(null, "Select a tag to delete", "Delete tag", JOptionPane.QUESTION_MESSAGE, null, tagList, null);
    	
    	String tag = (String) tagObject;
    	
    	TagsWithNotes.deleteTag(path, tag);
    	
    	TagsWithNotes.write();
    	
    	TagsWithNotes.demoPrint();
    	
    }
    

    public void searchTag(){
    	String input = JOptionPane.showInputDialog("Enter the tag you want to search");
    	
    	Vector<TreePath> searchResult = TagsWithNotes.tagSearch(input);
    	
    	if (searchResult.size()==0) {
			JOptionPane.showMessageDialog(null, "No results found");
			return;
		}
    	
    	Object noteObject = JOptionPane.showInputDialog(null, "Select a note", "Search result", JOptionPane.QUESTION_MESSAGE, null, searchResult.toArray(), null);
    	
    	TreePath note = (TreePath) noteObject;
    	
    	
    
    	tree.setSelectionPath(note);
    	openFile();
    	
    }
}
