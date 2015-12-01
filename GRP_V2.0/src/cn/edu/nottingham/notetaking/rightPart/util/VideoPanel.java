package cn.edu.nottingham.notetaking.rightPart.util;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;


/**
 * @author Melissa Lan
 *
 */


public class VideoPanel extends JTabbedPane {
	
	private static final long serialVersionUID = -5756890090497796548L;
    private JPanel openPanel;
    private JButton button;
    private JFrame jframe;

    public VideoPanel() {
    	
    	//initialising all the buttons and panel
    	openPanel = new JPanel();
    	button = new JButton("open");
    	openPanel.add(button);
    	addTab("open", openPanel);

    	//Upon clicking the open button
    	button.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			
    			//Open file prompt
    			FileDialog filedialog = new FileDialog(jframe, "open",
    					FileDialog.LOAD);
    			filedialog.setVisible(true);

    			//Get both the path and the filename
    			String directory = filedialog.getDirectory();
    			String fileName = filedialog.getFile();

    			if (fileName == null){
    				return;
    			}
    			
    			//getting the full path of the file and getting the file
    			String path = (directory + fileName).replace('\\', '/');
    			File file = new File(path);			
    			
    			//open the video file
    			try {
					openvideo(file);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    	});
    }
    
	private void openvideo(File file) throws InterruptedException, MalformedURLException {
		
		//creating a new frame to play the video on
		JFrame frame = new JFrame("Video Player"); 
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		final JFXPanel videoPanel = new JFXPanel();
		frame.add(videoPanel);
		frame.setSize(615, 385);
		frame.setVisible(true);
		
		//reading the video file and adding it to the frame
		Media video = new Media(file.toURI().toURL().toExternalForm()); 
		MediaPlayer player = new MediaPlayer(video);
		MediaControl mediaControl = new MediaControl(player);
		player.play();
		MediaView view = new MediaView (player);
		view.setFitHeight(400);
		view.setFitWidth(600);
		BorderPane root = new BorderPane(view);
		Scene scene = new Scene(root, 600, 400);
		scene.setRoot(mediaControl);
		videoPanel.setScene(scene);
		
		//videoPanel.setScene(new Scene(new MediaControl(player)));
	}

}
