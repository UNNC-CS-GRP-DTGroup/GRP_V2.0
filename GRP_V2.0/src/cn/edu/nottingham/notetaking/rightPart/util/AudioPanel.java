package cn.edu.nottingham.notetaking.rightPart.util;

/**
 * create a panel for audioPane
 *
 * @author WENYU DU
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioPanel extends JPanel{

    private static final long serialVersionUID = 7646375746884201743L;
    
    private boolean stopCapture;
    private AudioFormat audioFormat;
    private  ByteArrayOutputStream byteArrayOutputStream;
    private  TargetDataLine targetDataLine;
    private  AudioInputStream audioInputStream;
    private  SourceDataLine sourceDataLine;


    private JPanel audioPanel;
    private JFrame jframe;

    private static int counter = 0;
    /**
     * constructor
     *
     * @author WENYU DU
     */
    public AudioPanel() {
	audioPanel = this;

	setLayout(new BorderLayout());
	JPanel top = new JPanel();
	add(top, BorderLayout.NORTH);

	JButton captureBtn = new JButton("record");
	JButton stopBtn = new JButton("stop");
	JButton playBtn = new JButton("play");
	JButton saveBtn = new JButton("save");
	JButton saveasBtn = new JButton("save as");
	JButton openBtn = new JButton("open");
	captureBtn.setEnabled(true);
	openBtn.setEnabled(true);
	stopBtn.setEnabled(false);
	playBtn.setEnabled(false);
	saveBtn.setEnabled(false);
	saveasBtn.setEnabled(false);
	
	setLayout(new FlowLayout());
	setSize(350, 200);
	setVisible(true);
	
	// all button listeners
	
	captureBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
			captureBtn.setEnabled(false);
			stopBtn.setEnabled(true);
			playBtn.setEnabled(false);
			saveBtn.setEnabled(false);
			saveasBtn.setEnabled(false);
	        openBtn.setEnabled(false);
	        counter++;
			capture();
	    }
	});
	top.add(captureBtn);

	stopBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
			captureBtn.setEnabled(true);
			stopBtn.setEnabled(false);
			playBtn.setEnabled(true);
			saveBtn.setEnabled(true);
			saveasBtn.setEnabled(true);
	        openBtn.setEnabled(true);  
			stop();
	    }
	});
	top.add(stopBtn);

	playBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	play();
	    }
	});
	top.add(playBtn);

	saveBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	save();
	    }
	});
	top.add(saveBtn);

	saveasBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	saveas();
	    }
	});
	top.add(saveasBtn);

	openBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

			// get frame which contains imagePane
			Component p = audioPanel.getParent();
	
			while (p != null && !(p instanceof JFrame)) {
			    p = p.getParent();
			}
			if (p != null) {
			    jframe = (JFrame) p;
			}
	
			FileDialog filedialog = new FileDialog(jframe, "open",
				FileDialog.LOAD);
			filedialog.setVisible(true);
	
			// get path
			String directory = filedialog.getDirectory();
			String fileName = filedialog.getFile();
	
			if (fileName == null)
			    return;
	
			String path = directory + fileName;
	
			Thread t;
			t = new Thread(new Runnable() {
			    private AudioFormat audioFormat = null;
			    private SourceDataLine sourceDataLine = null;
			    private DataLine.Info dataLine_info = null;
			    private String file = path;
			    private AudioInputStream audioInputStream = null;
	
			    @Override
			    public void run() {
				try {
				    audioInputStream = AudioSystem
					    .getAudioInputStream(new File(file));
				    audioFormat = audioInputStream.getFormat();
				    dataLine_info = new DataLine.Info(
					    SourceDataLine.class, audioFormat);
				    try {
						sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
				    } catch (LineUnavailableException ex) {
					Logger.getLogger(AudioPanel.class.getName()).log(Level.SEVERE, null, ex);
				    }
	
				    byte[] b = new byte[1024];
				    int len = 0;
				    sourceDataLine.open(audioFormat, 1024);
				    sourceDataLine.start();
				    while ((len = audioInputStream.read(b)) > 0) {
				    	sourceDataLine.write(b, 0, len);
				    }
				    audioInputStream.close();
				    sourceDataLine.drain();
				    sourceDataLine.close();
	
				} catch (UnsupportedAudioFileException ex) {
				    Logger.getLogger(AudioPanel.class.getName()).log(
					    Level.SEVERE, null, ex);
				} catch (IOException ex) {
				    Logger.getLogger(AudioPanel.class.getName()).log(
					    Level.SEVERE, null, ex);
				} catch (LineUnavailableException ex) {
				    Logger.getLogger(AudioPanel.class.getName()).log(
					    Level.SEVERE, null, ex);
				}
	
			    }
			});
			t.start();
		    }
		});
		top.add(openBtn);
    }

    
    private void capture() {
		try {
			if(counter==1){
				audioFormat = getAudioFormat();
				DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
				targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			}
	
		    targetDataLine.open(audioFormat);
		    targetDataLine.start();
	
		    Thread captureThread = new Thread(new CaptureThread());
		    captureThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void refresh(){
	    audioFormat = null;
	    byteArrayOutputStream= null;
	    targetDataLine= null;
	    audioInputStream= null;
	    sourceDataLine= null;
    }
    
    private void play() {
	try {

	    byte audioData[] = byteArrayOutputStream.toByteArray();

	    InputStream byteArrayInputStream = new ByteArrayInputStream(
		    audioData);
	    AudioFormat audioFormat = getAudioFormat();
	    audioInputStream = new AudioInputStream(byteArrayInputStream,
		    audioFormat, audioData.length / audioFormat.getFrameSize());
	    DataLine.Info dataLineInfo = new DataLine.Info(
		    SourceDataLine.class, audioFormat);
	    sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	    sourceDataLine.open(audioFormat);
	    sourceDataLine.start();

	    Thread playThread = new Thread(new PlayThread());
	    playThread.start();
	} catch (Exception e) {
	     e.printStackTrace();
	}
    }

    public void stop() {
	stopCapture = true;

    }

    public void save() {
        
        AudioFormat audioFormat = getAudioFormat();
		byte audioData[] = byteArrayOutputStream.toByteArray();
		InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
		audioInputStream = new AudioInputStream(byteArrayInputStream,
		audioFormat, audioData.length / audioFormat.getFrameSize());
		
		String path = JOptionPane.showInputDialog(null, "enter the name",
		"input", JOptionPane.INFORMATION_MESSAGE);
		if (path == null)
			return;
	    try {
			File file = new File(path + ".wav");
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE,
				file);
			// doAddButton(filedialog.getDirectory(), filedialog.getFile());
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    

    public void saveas() {
		AudioFormat audioFormat = getAudioFormat();
		byte audioData[] = byteArrayOutputStream.toByteArray();
		InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
		audioInputStream = new AudioInputStream(byteArrayInputStream,
			audioFormat, audioData.length / audioFormat.getFrameSize());
		Component p = this.getParent();
	
		while (p != null && !(p instanceof JFrame)) {
		    p = p.getParent();
		}
		if (p != null) {
		    JFrame jframe = (JFrame) p;
	
		    FileDialog filedialog = new FileDialog(jframe, "save", FileDialog.SAVE);
		    filedialog.setVisible(true);
		    try {
				File file = new File(filedialog.getDirectory() + filedialog.getFile());
				AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE,
					file);
				// doAddButton(filedialog.getDirectory(), filedialog.getFile());
	
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
    }


    //get audio format
    private AudioFormat getAudioFormat() {
		float sampleRate = 16000.0F;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    // two threads
    public class PlayThread extends Thread {

		byte tempBuffer[] = new byte[10000];
	
		public void run() {
		    try {
				int cnt;
		
				while ((cnt = audioInputStream.read(tempBuffer, 0,
					tempBuffer.length)) != -1) {
				    if (cnt > 0) {
				    	sourceDataLine.write(tempBuffer, 0, cnt);
				    }
				}
		
				sourceDataLine.drain();
				sourceDataLine.close();
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
    }

    public class CaptureThread extends Thread {

		byte tempBuffer[] = new byte[10000];
	
		@Override
		public void run() {
		    byteArrayOutputStream = new ByteArrayOutputStream();
	//	    int totaldatasize = 0;
		    stopCapture = false;
		    try {
			while (!stopCapture) {
	
			    int cnt = targetDataLine.read(tempBuffer, 0,
				    tempBuffer.length);
			    if (cnt > 0) {
					byteArrayOutputStream.write(tempBuffer, 0, cnt);
		//			totaldatasize += cnt;
			    }
			}
			byteArrayOutputStream.close();
		    } catch (Exception e) {
		    }
		}
    }

}