package cn.edu.nottingham.notetaking.leftPart;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.tree.TreePath;

public class TagsWithNotes {
	private static HashMap<TreePath,Tags> tagsWithNotes = new HashMap<TreePath, Tags>();
	
	public static boolean addTag(TreePath note,String tag){
		boolean result = false;
		
		Tags tags = null;
		
		
		for(TreePath key: tagsWithNotes.keySet()){
			if (key.toString().equals(note.toString())) {
				tags = tagsWithNotes.get(key);
			}
		}
		
		if((tags!=null&&tags.contains(tag))||tag==null||tag.equals("")||tag.startsWith(" ")){
			result = true;
		}else if(tags!=null&&!tags.contains(tag)){
			tags.add(tag);
			result = true;
		}
		else{
			tagsWithNotes.put(note, new Tags(tag));
			result = true;
		}
		return result;
	}
	
	public static boolean deleteTag(TreePath note,String tag){
		boolean result = false;
		
		Tags tags = null;
		
		
		for(TreePath key: tagsWithNotes.keySet()){
			if (key.toString().equals(note.toString())) {
				tags = tagsWithNotes.get(key);
			}
		}
		
		tags.remove(tag);
		
		return result;
	}
	
	
	
	public static String search(TreePath note){
		String result;
		
		
		Tags tags = null;
		
		
		for(TreePath key: tagsWithNotes.keySet()){
			if (key.toString().equals(note.toString())) {
				tags = tagsWithNotes.get(key);
			}
		}
		
		if(tags!=null){
			result ="Tags:" + tags.toString();
		}else{
			result = "No tags now";
		}
		
		return result;
	}
	
	public static Vector<TreePath> tagSearch(String tag){
		Vector<TreePath> result = new Vector<TreePath>();
				
		
		for(TreePath key: tagsWithNotes.keySet()){
			
			if(tagsWithNotes.get(key).contains(tag)){
				result.add(key);
			}
		}
		
		return result;
	}
	
	
	
	public static boolean read(){
		boolean result = false;
		
		File tagFile = new File("tagFile.dat");
    	
    	if (!tagFile.exists()) {
    		try {
				tagFile.createNewFile();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
    	}
    	
    	try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("tagFile.dat"));
			tagsWithNotes = (HashMap<TreePath, Tags>) input.readObject();
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (EOFException e){ 
			System.out.println("EOF");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return result;
	}
	
	
	public static boolean write(){
		boolean result = false;
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("tagFile.dat"));
			output.writeObject(tagsWithNotes);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   	
		
		return result;
	}
	
	
	public static void demoPrint(){
		System.out.println(tagsWithNotes);
	}
	
	
}

class Tags extends Vector<String>{	
	public Tags(String tag){
		super();
		add(tag);
	}
	
	
}