import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame; 
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Image;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame
{
	private double score;
	private Sheep user;
	private JButton B_Easy, B_Medium, B_Hard, B_VeryHard;
	private JPanel buttonPanel, actionPanel, scorePanel;
	private JLabel L_Score;
	private JLabel[][] grid;
	private ButtonHandler bHandler;
	private KeyHandler kHandler;
	private scoreEngine thread;
	private gameEngine gameThread;
	
	public GUI()
	{
		super("SheepApacopopalys!");
		setComponents();
		setPanels();
		setVisible(true);
	}
	
	public void setComponents()
	{
		user = new Sheep();
		bHandler = new ButtonHandler();
		kHandler = new KeyHandler(user);
		
		setLayout(null);
		//setLayout(new GridLayout(2, 1));
		setBounds(400, 0, 700, 800); 
		setIconImage(new ImageIcon("BlackSheep.png").getImage());		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		B_Easy = new JButton("Easy");
		B_Medium = new JButton("Medium");
		B_Hard = new JButton("Hard");
		B_VeryHard = new JButton("Very Hard");
		B_Easy.setFocusable(false);
		B_Medium.setFocusable(false);
		B_Hard.setFocusable(false);
		B_VeryHard.setFocusable(false);
		
		B_Easy.addActionListener(bHandler);
		B_Medium.addActionListener(bHandler);
		B_Hard.addActionListener(bHandler);
		B_VeryHard.addActionListener(bHandler);
		
		L_Score = new JLabel();
		L_Score.setLocation(0, 0);
		L_Score.setText(score + "");
	}
	
	public void setPanels()
	{
		scorePanel = new JPanel(new GridLayout(1, 1));
		scorePanel.setBounds(0, 0, 100, 40);
		scorePanel.add(L_Score);
		add(scorePanel);
		
		actionPanel = new JPanel(new GridLayout(15, 15));
		actionPanel.setBounds(0, 45, 700, 600);
		ImageIcon grass = new ImageIcon("Grass.jpg");
		grid = new JLabel[15][15];
		for(int row = 1; row <= 15; row ++)
		{
			for(int col = 1; col <= 15; col ++)
			{
					grid[row-1][col-1] = new JLabel();
					grid[row-1][col-1].setIcon(grass);
					actionPanel.add(grid[row-1][col-1]);
			}
		}
		grid[0][0].setIcon(new ImageIcon("Sheep.GIF"));
		actionPanel.addKeyListener(kHandler);
		actionPanel.setFocusable(true);
		add(actionPanel);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 650, 700, 70);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		buttonPanel.add(B_Easy);
		buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
		buttonPanel.add(B_Medium);
		buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
		buttonPanel.add(B_Hard);
		buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
		buttonPanel.add(B_VeryHard);
		add(buttonPanel);
	}
	
	public static void calculateHighScore(double score)
	{
		ArrayList<Double> list = new ArrayList<Double>();
		
		try{
			File file = new File("highScores.dat");
			Scanner fileInput = new Scanner(file);
			while(fileInput.hasNext())
			{
				list.add(fileInput.nextDouble());
			}
			
			if(list.size() >= 1 && score > list.get(0).doubleValue())
				{
					list.add(0, score);
				}
			
			else if(list.size() == 0 || score < list.get(list.size() - 1).doubleValue())
			{
				list.add(score);
			}
			
			else
			{
				for(int i = 0; i < list.size(); i ++)
				{
					if(score > list.get(i).doubleValue())
					{
						list.add(i, score);
						break;
					}
				}
			}
			
			PrintWriter output = new PrintWriter(file);
			for(int i = 0; i < list.size(); i ++)
				output.println(list.get(i).doubleValue());
			output.close();
			fileInput.close();
			HighScore highScores = new HighScore(score);
		}
		catch(IOException e){
		}
	}
	
	class ButtonHandler implements ActionListener
	{
		private JFrame frame;
		private double difficulty;
		
		public ButtonHandler()
		{
			super();
		}
		
		public ButtonHandler(JFrame setFrame)
		{
			super();
			frame = setFrame;
		}
		
		public void actionPerformed(ActionEvent ev)
		{
			if(ev.getActionCommand().equals("Easy"))
			{
				difficulty = 150d;
				thread = new scoreEngine(difficulty, L_Score);
				gameThread = new gameEngine(difficulty, grid, actionPanel, user, thread);
				thread.execute();
				gameThread.execute();
			}
			
			if(ev.getActionCommand().equals("Medium"))
			{
				difficulty = 300d;
				thread = new scoreEngine(difficulty, L_Score);
				gameThread = new gameEngine(difficulty, grid, actionPanel, user, thread);
				thread.execute();
				gameThread.execute();
			}
			
			if(ev.getActionCommand().equals("Hard"))
			{
				difficulty = 450d;
				thread = new scoreEngine(difficulty, L_Score);
				gameThread = new gameEngine(difficulty, grid, actionPanel, user, thread);
				thread.execute();
				gameThread.execute();
			}
			
			if(ev.getActionCommand().equals("Very Hard"))
			{
				difficulty = 10000d;
				thread = new scoreEngine(difficulty, L_Score);
				gameThread = new gameEngine(difficulty, grid, actionPanel, user, thread);
				thread.execute();
				gameThread.execute();
			}
		}
	}
	
	class KeyHandler implements KeyListener
	{
		private Sheep user;
			
		public KeyHandler(Sheep sheep)
		{
			user = sheep;
		}
		
		public void keyReleased(KeyEvent ev)
		{
			
		}
		
		public void keyPressed(KeyEvent ev)
		{
			if(ev.getKeyCode() == KeyEvent.VK_W && user.getX() > 0)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setX(user.getX() - 1);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_A && user.getY() > 0)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setY(user.getY() - 1);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_S && user.getX() < 14)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setX(user.getX() + 1);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_D && user.getY() < 14)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setY(user.getY() + 1);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_RIGHT && user.getY() < 13)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setY(user.getY() + 2);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_LEFT && user.getY() > 1)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setY(user.getY() - 2);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_UP && user.getX() > 1)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setX(user.getX() - 2);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
			if(ev.getKeyCode() == KeyEvent.VK_DOWN && user.getX() < 13)
			{
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Grass.jpg"));
				user.setX(user.getX() + 2);
				grid[user.getX()][user.getY()].setIcon(new ImageIcon("Sheep.GIF"));
			}
		}
		
		public void keyTyped(KeyEvent ev)
		{
			
		}
	}
	
	class scoreEngine extends SwingWorker<String, String>
        {
        	private double difficulty, time, score;
        	private JLabel visual;

        	public scoreEngine(double dif, JLabel vis)
        	{
        		difficulty = dif;
        		visual = vis;
        	}
        	
        	protected String doInBackground()
        	{
			  	double time = 0d;
			  	time = 0.001;
			  	DecimalFormat formatter = new DecimalFormat("#0.000");
			    while (true) //Essentially loop indefinitely. Other methods are used to break out of the loop.
			    {
			    	score = score += (time * difficulty);
			    	visual.setText(formatter.format(score) + "");
			      try {
			        Thread.sleep(10);
			      } catch (InterruptedException ex) {
			      	String orig = score + "";
			      	int indexOfDecimal = orig.indexOf(".");
			      	String cutOff = orig.substring(0, indexOfDecimal + 2);
			      	GUI.calculateHighScore(Double.parseDouble(cutOff));
			        Thread.currentThread().interrupt(); //InterruptedException caught and thread is correctly interrupted.
			        return "" + score; //breaks out of loop.
			        	}
			    }
        	}
        }
        
   class gameEngine extends SwingWorker<String, String>
   {
   		private double difficulty, time;
   		private ArrayList<Lava> lavaList;
   		private Random randomizer;
   		private JLabel[][] grid;
   		private JPanel screen;
   		private Sheep userSheep;
   		private scoreEngine score;
   		
   		public gameEngine(double dif, JLabel[][] grd, JPanel scrn, Sheep shp, scoreEngine score)
   		{
   			difficulty = dif;
   			time = 0d;
   			randomizer = new Random();
   			grid = grd;
   			screen = scrn;
   			userSheep = shp;
   			this.score = score;
   		}
   		
   		protected String doInBackground()
   		{
   			lavaList = new ArrayList<Lava>();
			while (true) //Essentially loop indefinitely. Other methods are used to break out of the loop.
			{
				int xMax = 14;
				int yMax = 14;
				time += .01;
				if(difficulty == 150d)
				{
					if(time % .01 <= 0.0025 && time > .2)
					{
						int x = (int) (Math.round(randomizer.nextDouble() * xMax));
						int y = (int) (Math.round(randomizer.nextDouble() * yMax));
						double quadrant = (randomizer.nextDouble());
						if(quadrant < .25)
							x = 14;
						else if(quadrant > .25 && quadrant < .5)
							y = 14;
						else if(quadrant >.5 && quadrant < .75)
							x = 0;
						else if(quadrant > .75)
							y = 0;
						Lava newLava = new Lava(x, y, xMax, yMax);
						lavaList.add(newLava);
					}
				}
				else if(difficulty == 300d)
				{
					if(time % .01 <= 0.005 && time > .2)
					{
						int x = (int) (Math.round(randomizer.nextDouble() * xMax));
						int y = (int) (Math.round(randomizer.nextDouble() * yMax));
						double quadrant = (randomizer.nextDouble());
						if(quadrant < .25)
							x = 14;
						else if(quadrant > .25 && quadrant < .5)
							y = 14;
						else if(quadrant >.5 && quadrant < .75)
							x = 0;
						else if(quadrant > .75)
							y = 0;
						Lava newLava = new Lava(x, y, xMax, yMax);
						lavaList.add(newLava);
					}
				}
				
				else if(difficulty == 450d)
				{
					if(time % .01 <= 0.075 && time > .2)
					{
						int x = (int) (Math.round(randomizer.nextDouble() * xMax));
						int y = (int) (Math.round(randomizer.nextDouble() * yMax));
						double quadrant = (randomizer.nextDouble());
						if(quadrant < .25)
							x = 14;
						else if(quadrant > .25 && quadrant < .5)
							y = 14;
						else if(quadrant >.5 && quadrant < .75)
							x = 0;
						else if(quadrant > .75)
							y = 0;
						Lava newLava = new Lava(x, y, xMax, yMax);
						lavaList.add(newLava);
					}
				}
				
				else
				{
					if(time > .2)
					{
						int x = (int) (Math.round(randomizer.nextDouble() * xMax));
						int y = (int) (Math.round(randomizer.nextDouble() * yMax));
						double quadrant = (randomizer.nextDouble());
						if(quadrant < .25)
							x = 14;
						else if(quadrant > .25 && quadrant < .5)
							y = 14;
						else if(quadrant >.5 && quadrant < .75)
							x = 0;
						else if(quadrant > .75)
							y = 0;
						Lava newLava = new Lava(x, y, xMax, yMax);
						lavaList.add(newLava);
					}
				}
			
				
				if(lavaList.size() > 0)
				for(int i = 0; i < lavaList.size(); i ++)
					{
						Lava lava = lavaList.get(i);
						int rowInit = lava.getX();
						int colInit = lava.getY();
						if(lava.getX() == userSheep.getX() & lava.getY() == userSheep.getY())
						{
							userSheep.addHP(-1);
							if(userSheep.getHP() == Sheep.DEATH)
							{
								cancel(true);
								break;
							}
						}
							
						grid[rowInit][colInit].setIcon(new ImageIcon("Grass.jpg"));
						if(lava.update() == false)
							{
								lavaList.remove(i);
								grid[rowInit][colInit].setIcon(new ImageIcon("Grass.jpg"));
							}
						int row = lava.getX();
						int col = lava.getY();
						grid[row][col].setIcon(new ImageIcon("Lava.jpg"));
						screen.revalidate();
						screen.repaint();
					}
			    try {
			    	time += 0.001;
			    Thread.sleep(70);
				 } catch (InterruptedException ex) {
				 	score.cancel(true);
			        Thread.currentThread().interrupt(); //InterruptedException caught and thread is correctly interrupted.
			        return "END"; //breaks out of loop.
			        	}
			}
   		}
   }
}