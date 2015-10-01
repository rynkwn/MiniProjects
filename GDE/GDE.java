import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GDE{
    private Creature[][] map;
    private int size;
    
    public GDE(int size){
	this.size = size;
	map = new Creature[size][size];
    }

    public void iterate(int time){
	for(int i = 0; i < size; i++){
	    for(int k = 0; k < size; k ++){
		if(map[i][k] != null){
			Random rand = new Random();
		    Creature temp = map[i][k];
		    boolean eaten = false;
		    ArrayList<Position> list1 = checkAroundFull(i, k);
		    for(Position pos : list1){
			int temp1Str = temp.getStr();
			if (Constants.CAN_EAT_CREATURES_OF_EQUAL_FITNESS) {
				if(map[pos.getX()][pos.getY()].getStr() <= temp1Str){
				    temp.setTime(0);
				    map[pos.getX()][pos.getY()] = null;
				    	eaten = true;
				    break;
				}
			} else {
				if(map[pos.getX()][pos.getY()].getStr() < temp1Str){
				    temp.setTime(0);
				    map[pos.getX()][pos.getY()] = null;
				    	eaten = true;
				    break;
				}
			}
		    }
		    ArrayList<Position> list2 = checkAroundEmpty(i,k);
		    boolean moved = false;
		    if(list2.size() >= 1){
		    int index = rand.nextInt(list2.size());
			int x = list2.get(index).getX();
			int y = list2.get(index).getY();
			map[x][y] = temp.clone();
			moved = true;
			list2.remove(index);
			if(list2.size() >=2){
				int eatDex = rand.nextInt(list2.size());
				int eatDex2 = -1;
				if(rand.nextInt(10) == 0)
					eatDex2 = list2.size() - 1 - eatDex;
			    if(eaten){
					map[list2.get(eatDex).getX()][list2.get(eatDex).getY()] = temp.reproduce();
				    if(eatDex2 != -1)
				    	map[list2.get(eatDex2).getX()][list2.get(eatDex2).getY()] = temp.reproduce();
			    }
			}
			if(moved){
				map[i][k] = null;
			    map[x][y].setTime(map[x][y].getTime() + 1);
			    if(map[x][y].getTime() == time)
				map[x][y] = null;
			}
		    }    
		    if(! moved){
			map[i][k].setTime(map[i][k].getTime() + 1);
			if(map[i][k].getTime() == time)
			    map[i][k] = null;
		    }
		}
	    }
	}
    }

    public ArrayList<Position> checkAroundEmpty(int x, int y){
	ArrayList<Position> list = new ArrayList<Position>();
	try{
	    if(map[x-1][y-1] == null)
		list.add(new Position(x-1, y-1));
	}catch(Exception e){}
	try{
	    if(map[x-1][y] == null)
		list.add(new Position(x-1, y));
	}catch(Exception e){}
	try{
	    if(map[x-1][y+1] == null)
		list.add(new Position(x-1, y+1));
	}catch(Exception e){}
	try{
	    if(map[x][y-1] == null)
		list.add(new Position(x, y-1));
	}catch(Exception e){}
	try{
	    if(map[x][y+1] == null)
		list.add(new Position(x, y+1));
	}catch(Exception e){}
	try{
	    if(map[x+1][y-1] == null)
		list.add(new Position(x+1, y-1));
	}catch(Exception e){}
	try{
	    if(map[x+1][y] == null)
		list.add(new Position(x+1, y));
	}catch(Exception e){}
	try{
	    if(map[x+1][y+1] == null)
		list.add(new Position(x+1, y+1));
	}catch(Exception e){}

	return list;
    }

    public ArrayList<Position> checkAroundFull(int x, int y){
	ArrayList<Position> list = new ArrayList<Position>();
	try{
	    if(map[x-1][y-1] != null)
		list.add(new Position(x-1, y-1));
	}catch(Exception e){}
	try{
	    if(map[x-1][y] != null)
		list.add(new Position(x-1, y));
	}catch(Exception e){}
	try{
	    if(map[x-1][y+1] != null)
		list.add(new Position(x-1, y+1));
	}catch(Exception e){}
	try{
	    if(map[x][y-1] != null)
		list.add(new Position(x, y-1));
	}catch(Exception e){}
	try{
	    if(map[x][y+1] != null)
		list.add(new Position(x, y+1));
	}catch(Exception e){}
	try{
	    if(map[x+1][y-1] != null)
		list.add(new Position(x+1, y-1));
	}catch(Exception e){}
	try{
	    if(map[x+1][y] != null)
		list.add(new Position(x+1, y));
	}catch(Exception e){}
	try{
	    if(map[x+1][y+1] != null)
		list.add(new Position(x+1, y+1));
	}catch(Exception e){}

	return list;
    }

    public String show(){
		String output = "";
		for(int i = 0; i < size; i ++){
		    output += "\n";
		    for(int k = 0; k < size; k ++){
		    if(map[i][k] != null){
				if(map[i][k].getTime() >= 0)
				    output += "[O]";
				else if(map[i][k].getTime() < 0)
					output += "[F]";
			    }
		    else
			    output += "[ ]";
		    }
		}
		return output;
    }
    
    
    public String showData(){
    	String output = "";
    	for(int z = 0; z < size; z ++){
    	    output += "\n";
    	    for(int j = 0; j < size; j ++){
    		if(map[z][j] != null)
    		    output += "x: " + z + " y: " + j + "\n" + map[z][j].toString();
    	    }
    	}
    	return output;
    }
    
    public static Creature genericCreature(){
	return new Creature(0, "aasassas");
    }
    
    public static Creature genericFood(){
    	return new Creature(-1000, "a");
    }
    
    public void add(int x, int y, Creature creat){
	map[x][y] = creat;
    }
    
    public static void main(String [] args){
	Scanner scan = new Scanner(System.in);
	int timeUntilStarve = Constants.TIME_UNTIL_STARVE;
	int size = Constants.WORLD_SIZE;
	GDE garden = new GDE(size);
	Random rand = new Random();
	Thread thread = new Thread();
	for(int i = 0; i < size; i ++){
	    for(int k = 0; k< size; k++){
		if(rand.nextInt(50) == 0)
		    garden.add(i,k,genericCreature());
		else if(rand.nextInt(10) == 0)
	    	garden.add(i, k, genericFood());
	    }
	}
	String input = scan.nextLine();
	while(!input.equals("q")){
	    System.out.println(garden.show());
	    input = scan.nextLine();
	    if(input.equals("r")){
		    int iterations = scan.nextInt();
		    for(int z = 0; z < iterations; z++){
		    	System.out.println(garden.show());
		    	garden.iterate(timeUntilStarve);
		    	try{
		    		thread.sleep(150);
		    	}catch(Exception e){}
		    }
	    }
	    else if(input.equals("p"))
	    	System.out.println(garden.showData());
	    garden.iterate(timeUntilStarve);
	}
    }
}