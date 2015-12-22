package cn.edu.nottingham.notetaking.leftPart;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import com.sun.awt.AWTUtilities;

public class DictionaryFrame extends JFrame implements ActionListener{
 /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
	
  class RefBoolean {public boolean var = false;}
  class RefInt {public int var = 0;}

  public DictionaryFrame() {
	  try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}
	  catch (Exception e) { System.out.println(e); }
	  
	  Toolkit tk = Toolkit.getDefaultToolkit();
	  
	  final RefBoolean canDrag = new RefBoolean();
	  final RefBoolean isFirst = new RefBoolean();
	  final RefInt oldX = new RefInt();
	  final RefInt oldY = new RefInt();
	  
	  isFirst.var = true;
	  
	//  final thisrame this = new thisrame();
	  final JDialog jdmean = new JDialog();
	  final JTextField jtfWord = new JTextField("");
	  final JTextArea jtamean = new JTextArea("");
	  JScrollPane jScmean=new JScrollPane(jtamean);
	  JPanel jp = new JPanel();
	  
	  Image image = tk.getImage("c:/ico.jpg");
	  
	  this.setAlwaysOnTop(true);
	  this.setIconImage(image);
	  
	  jdmean.setBounds((int)(tk.getScreenSize().getWidth()-270), (int)(tk.getScreenSize().getHeight()-320)+60, 260, 200);
	  jdmean.setUndecorated(true);
	  jdmean.setAlwaysOnTop(true);
	  jtamean.setLineWrap(true);
	  jdmean.add(jScmean);
	  jdmean.setVisible(true);
	  
	//  AWTUtilities.setWindowOpacity(this,0.9f);
	//  AWTUtilities.setWindowOpacity(jdmean,0.0f);
	  
	  jtfWord.setBounds(5, 10, 250, 21);
	  jp.setLayout(null);
	  jp.add(jtfWord);
	  
	  jp.setBackground(Color.GRAY);
	  this.setContentPane(jp);
	
	  this.setBounds((int)(tk.getScreenSize().getWidth()-270), (int)(tk.getScreenSize().getHeight()-320), 260, 50);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setUndecorated(true);
	  
	  jp.validate();
	  this.setVisible(true);
	  
	  try {
	   if(SystemTray.isSupported()) {
	    SystemTray st = SystemTray.getSystemTray();
	    TrayIcon ti = new TrayIcon(image);
	    ti.setToolTip("dict");
	    
	    PopupMenu popupMenu1 = new PopupMenu();  
	    MenuItem menuItem1 = new MenuItem(); 
	
	    ti.setPopupMenu(popupMenu1);
	    
	    ti.addMouseListener(new MouseAdapter() { 
	     public void mouseClicked(MouseEvent e) { 
	      if (e.getClickCount() == 2) { 
	    	  DictionaryFrame.this.setVisible(true);
	      }
	     }
	    });
	
	    st.add(ti);
	    
	    popupMenu1.setLabel("dict");
	    menuItem1.setLabel("fff");
	    menuItem1.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent evt) {
	      System.exit(0);
	     }
	    });
	    popupMenu1.add(menuItem1);
	   }
	  } catch (AWTException e) {
	   e.printStackTrace();
	  }
	  this.addWindowListener(new WindowAdapter() {
	   public void windowIconified(WindowEvent evt) {
	   }
	  });
	  
	  tk.addAWTEventListener(new AWTEventListener() {
	   @Override
	   public void eventDispatched(AWTEvent event) {
	    if(((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
	     if(((KeyEvent) event).getKeyCode()==KeyEvent.VK_F4) {
	      System.exit(0);
	     }else if( ((KeyEvent) event).getKeyCode()==KeyEvent.VK_ESCAPE) {
	    	 DictionaryFrame.this.setVisible(false);
	      
	      if(AWTUtilities.getWindowOpacity(jdmean)>0.1d) {
	       double[] ds = {0.69, 0.59, 0.49, 0.45, 0.39, 0.35, 0.29, 0.25, 0.19, 0.1, 0.05, 0.0};
	       for(double d:ds) {
	        _sleep(10);
	        AWTUtilities.setWindowOpacity(jdmean,(float)d);
	       }
	      }
	     }  else if( ((KeyEvent) event).getKeyCode()==KeyEvent.VK_F1) {
	    	 DictionaryFrame.this.setVisible(true);
	      jtfWord.requestFocus();
	     }  
	    }
	   }
	  }, AWTEvent.KEY_EVENT_MASK);
	
	  jp.addMouseMotionListener(new MouseMotionListener() {
	   @Override
	   public void mouseMoved(MouseEvent arg0) {    
	   }
	   
	   @Override
	   public void mouseDragged(MouseEvent arg0) {
	    int x = arg0.getX() + DictionaryFrame.this.getX();
	    int y = arg0.getY() + DictionaryFrame.this.getY();
	    
	    if(isFirst.var) {
	     oldX.var = x;
	     oldY.var = y;
	     isFirst.var = false;
	    }
	
	    int detaX = x-oldX.var;
	    int detaY = y-oldY.var;
	    
	    DictionaryFrame.this.setBounds(DictionaryFrame.this.getX()+detaX, DictionaryFrame.this.getY()+detaY, 260, 50);
	    jdmean.setBounds(DictionaryFrame.this.getX()+detaX, DictionaryFrame.this.getY()+detaY+60, 260, 200);
	    oldX.var = x;
	    oldY.var = y;
	   }
	  });
	  
	  jp.addMouseListener(new MouseListener() {
	   @Override
	   public void mouseReleased(MouseEvent arg0) {
	    canDrag.var = false;
	    isFirst.var = false;
	   }
	   
	   @Override
	   public void mousePressed(MouseEvent arg0) {
	    canDrag.var = true;
	    isFirst.var = true;
	   }
	   
	   @Override
	   public void mouseExited(MouseEvent arg0) {
	   }
	   
	   @Override
	   public void mouseEntered(MouseEvent arg0) {
	   }
	   
	   @Override
	   public void mouseClicked(MouseEvent arg0) {
	   }
	  });
	
	  this.addWindowListener(new WindowAdapter() {
	   public void windowIconified(WindowEvent evt) {
	    AWTUtilities.setWindowOpacity(jdmean,0.0f);
	   }
	  });
	  
	  jtfWord.addKeyListener(new KeyListener() {
	   @Override
	   public void keyTyped(KeyEvent e) { 
	   }
	   
	   @Override
	   public void keyReleased(KeyEvent e) {
	    if( e.getKeyCode()==KeyEvent.VK_ENTER) {
	     if((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
	      jtfWord.setText("");
	      jtamean.setText("");
	      
	      Thread t = new Thread(new Runnable() {
	       @Override
	       public void run() {
	        if(AWTUtilities.getWindowOpacity(jdmean)>0.1d) {
	         double[] ds = {0.69, 0.59, 0.49, 0.45, 0.39, 0.35, 0.29, 0.25, 0.19, 0.1, 0.05, 0.0};
	         for(int i=0; i<ds.length; i++) {
	          double d = ds[i];
	          _sleep(10);
	          AWTUtilities.setWindowOpacity(jdmean,(float)d);
	          jdmean.setBounds(jdmean.getX(), jdmean.getY(), 260-(int)((26*i)*1.1), 200-(int)((20*i)*1.1));
	         }
	        }
	       }
	      });
	      
	      t.start();
	      return;    
	     }
	     
	     try {
	      String url = "http://dict-co.iciba.com/api/dictionary.php?w=" + jtfWord.getText() + "&key=1F9CA812CB18FFDFC95FC17E9C57A5E1";
	      
	      
	      VarByte vb = DictionaryFrame.getPage(url);
	      String content = new String(vb.to_byte(), "utf-8");
	
	      String tagBegin = "<acceptation>";
	      String tagEnd = "</acceptation>";
	      StringBuilder sb = new StringBuilder();
	      
	      int cur = 0;
	      while((cur=content.indexOf(tagBegin, cur))!=-1) {
	       cur += 13;
	       int curEnd = content.indexOf(tagEnd, cur);
	       sb.append(content.substring(cur, curEnd));
	       curEnd += 14;
	      }
	      
	      if(sb.toString().length()==0) sb.append("Not Found!");      
	      jtamean.setText(sb.toString());
	      
	      AWTUtilities.setWindowOpacity(jdmean,0.7f);
	      jdmean.setBounds(jdmean.getX(), jdmean.getY(), 260, 200);
	      
	     } catch (Exception ex) {
	      jtamean.setText("fffffffff");
	      AWTUtilities.setWindowOpacity(jdmean,0.7f);
	      jdmean.setBounds(jdmean.getX(), jdmean.getY(), 260, 200);
	     }
	    }
	   }
	   
	   @Override
	   public void keyPressed(KeyEvent arg0) {
	   }
	  });
 }
 
 public static void _sleep(long m) {
  try {
   Thread.sleep(m);
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
 }
 
 public static VarByte getPage (String address) throws Exception { 
  URL url = new URL(address);
  InputStream inStream = url.openStream();
  VarByte page = new VarByte();  
  
  int oneTimeSize = 2048;
  byte[] b = new byte[oneTimeSize];
  int len;
  
  while((len=inStream.read(b))!=-1) {
   if(len!=oneTimeSize) {
    b = Arrays.copyOf(b, len);
   }
   
   page.append(b);
  }

  if(inStream!=null) inStream.close();
  
  return page;
 }

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}

class VarByte {
 byte[]  b = new byte[512];
 int capacity = 512;
 int len = 0;
 
 public VarByte append(byte[] ba) {  
  if(len+ba.length>capacity) {
   byte[] tmp = new byte[(len+ba.length)*2];
   System.arraycopy(b, 0, tmp, 0, len);
   System.arraycopy(ba, 0, tmp, len, ba.length);
   b = tmp;
   
   len = len+ba.length;
   capacity = len*2;
   
  } else {
   System.arraycopy(ba, 0, b, len, ba.length);
   len = len + ba.length;
  }

  return this;
 }
 
 public byte[] to_byte() {
  byte[] btmp = new byte[len];
  System.arraycopy(b, 0, btmp, 0, len);
  return btmp;
 }
 
 public byte[] to_byte(int start, int len) {
  byte[] btmp = new byte[len];
  for(int i=0; i<len; i++) {
   btmp[i] = b[start+i];
  }
  return btmp;
 }
 
 public int size() {
  return len;
 }
}