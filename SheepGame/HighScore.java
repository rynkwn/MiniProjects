import  java.io.*;
import  sun.audio.*; 
import java.util.Scanner;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Image;
import java.awt.GridLayout; 
import java.util.ArrayList;

public class HighScore extends JFrame
{
	private JPanel scorePanel, picturePanel;
	private double score;
	
	public HighScore(double score)
	{
		super("SheepApacopopalys High Scores!");
		this.score = score;
		setComponents();
		setPanels();
		setVisible(true);
	}
	
	public void setComponents()
	{
		setLayout(new GridLayout(2,1));
		setBounds(400, 0, 500, 500); 
		setIconImage(new ImageIcon("BlackSheep.png").getImage());		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setPanels()
	{
		int size = 0;
		try{
		File file = new File("highScores.dat");
		Scanner inFile = new Scanner(file);
		ArrayList<Double> list = new ArrayList<Double>();
		while(inFile.hasNext())
			{
				list.add(inFile.nextDouble());
				size++;
			}
		if(size <= 20)
		{
		scorePanel = new JPanel(new GridLayout(size, 2));
		for(int i = 0; i < size; i ++)
			{
				JLabel rank = new JLabel();
				if(list.get(i).doubleValue() == score)
					rank.setText(i + " (YOU):");
				else
					rank.setText(i + ":");
				scorePanel.add(rank);
				JLabel score = new JLabel();
				score.setText(list.get(i).doubleValue() + "");
				scorePanel.add(score);
			}
		}
		else
		{
			scorePanel = new JPanel(new GridLayout(20, 2));
			for(int i = 0; i < 20; i ++)
				{
					JLabel rank = new JLabel();
					if(list.get(i).doubleValue() == score)
						rank.setText((i + 1) + " (YOU):");
					else
						rank.setText((i + 1) + ":");
					scorePanel.add(rank);
					JLabel score = new JLabel();
					score.setText(list.get(i).doubleValue() + "");
					scorePanel.add(score);
				}
		}
		JLabel endPicture = new JLabel();
		if(score == list.get(0).doubleValue())
		{
			endPicture.setIcon(new ImageIcon("HighScoreSheep.GIF"));
			try{
				InputStream in = new FileInputStream("victory.wav");
				// Create an AudioStream object from the input stream.
				AudioStream as = new AudioStream(in);         
				// Use the static class member "player" from class AudioPlayer to play clip.
				AudioPlayer.player.start(as);
			} catch(Exception e){
			}
		}
		else
		{
			endPicture.setIcon(new ImageIcon("NormalCharredSheep.GIF"));
		}
		
		inFile.close();
		add(scorePanel);
		
		picturePanel = new JPanel();
		picturePanel.add(endPicture);
		add(picturePanel);
		}
		catch(IOException e){
		}
	}
}