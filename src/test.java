

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class test extends JFrame{
	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textArea);
	
	
	private JButton button = new JButton("Button");
	private JTextField textfield = new JTextField();
	
	
	private JTabbedPane tabs = new JTabbedPane();
	
	public static void main (String[] args){
		test t = new test();
	}
	
	public test(){
		this.setTitle("Test tabbed pane thing");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		scroll.setPreferredSize(new Dimension(300,200));
		
		tabs.addTab("TAB1", new tab1());
		tabs.addTab("TAB2", new tab2());
		
		this.add(tabs);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private class tab1 extends JPanel{
		public tab1(){
			super(new GridLayout(2,0));
			
//			JPanel j = new JPanel(new GridLayout(0,2));
//			j.add(button);
//			j.add(textfield);
			
			this.add(textfield);
			this.add(scroll);
		}
	}
	
	private class tab2 extends JPanel{
		public tab2(){
			super(new GridLayout(2,0));

//			JPanel j = new JPanel(new GridLayout(0,2));
//			j.add(textfield);
//			j.add(button);
			
			this.add(button);
			this.add(scroll);
		}		
	}
}
