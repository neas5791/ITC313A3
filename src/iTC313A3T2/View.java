package iTC313A3T2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JPanel implements Runnable{
	private SpeedComponent sc; 
	private JLabel speed;
	private int id;
	private int position = 180;
	private String name;
	
	public void run() {
		while(true){
			try {
//				System.out.println(name);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public View(int id){
		this.id = id;
		init();
	}

	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
		JPanel title = new JPanel(new FlowLayout());
		title.add(new JLabel(name));
		this.add(title, BorderLayout.NORTH);
	}
	
	public void init(){

		sc = new SpeedComponent();
		speed = new JLabel("broombroom");
		
		JPanel controls = new JPanel(new FlowLayout());
		controls.add(speed);

		this.setLayout(new BorderLayout());
//		this.setSize(250,150);

		this.add(sc, BorderLayout.CENTER);
		this.add(controls, BorderLayout.SOUTH);
		this.setVisible(true);

		new Timer(200, new ActionListener(){
			  public void actionPerformed(ActionEvent e) {
				  changePostion(1);
			  }
			}).start();
		
		
	}

	public void changePostion(int s){
		setPosition(getPosition()+s);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int velocity = (int) ((1-(position/180.0)) * 250);
//		System.out.println(velocity);
		speed.setText(String.valueOf(velocity));
		repaint();
	}
	
	public int getPosition(){
		return position;
	}
	
	private void setPosition(int position){
		if (position > 180 || position < 0)
			return;
		else
			this.position = position;
	}
	
	@SuppressWarnings("serial")
	private class SpeedComponent extends JPanel{

		protected void paintComponent(Graphics g){

			super.paintComponent(g);

			int xCentre = getWidth()/2;
			int yCentre = getHeight();// - 20;

			int radius = (int) (getWidth() * 0.3);

			int x = xCentre - radius;
			int y = yCentre - radius;

			g.fillArc(x, y, 2*radius, 2*radius, 0, 180);
			
			if(position > 45)
				g.setColor(Color.YELLOW);
			else
				g.setColor(Color.RED);
			
			g.fillArc(x, y, 2*radius, 2*radius, position - 5, 5);
		}

	}

//	public static void main (String[] args){
//		View s = new View();
//
//	}


}