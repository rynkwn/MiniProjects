import java.util.Random;

public class Creature{

    private int time;
    private String DNA;
    private int str;

    public Creature(int time, String dna){
        this.time = time;
		DNA = dna;
		setStr();
    }

    public void setStr(){
		int stemp = 0;
		for(int i = 0; i < DNA.length(); i ++){
		    if(DNA.charAt(i) == 's')
			stemp ++;
		}
		str = stemp;
    }

    public int getStr(){
    	return str;
    }

    public int getTime(){
    	return time;
    }

    public String getDNA(){
    	return DNA;
    }

    public void setTime(int t){
    	time = t;
    }
    
    public void setDNA(String strand){
    	DNA = strand;
    }

    public String ranStrand(){
		Random rand = new Random();
		String output = "";
		int chance = rand.nextInt(5);
		if(chance == 0){
		    output = "s";
		}
		else if(chance == 1){
		    output = "a";
		}
		else if(chance == 2){
		    output = "aa";
		}
		else if(chance == 3){
		    output = "sa";
		}
		else if(chance == 4){
		    output = "as";
		}
		else{
			output = "a";
		}
		return output;
    }

    public Creature reproduce(){
		Random rand = new Random();
		String newStrand = "";
		for(int i = 0; i < DNA.length(); i ++){
		    if(rand.nextInt(10) == 1){
			newStrand = newStrand + ranStrand();
		    }
		    else{
			newStrand = newStrand + DNA.charAt(i);
		    }
		}
		if(newStrand.length() >= Constants.MAX_DNA_LENGTH)
			newStrand = newStrand.substring(0, 20);
		return new Creature(0, newStrand);
    }

    public String toString(){
		String output = "";
		output = "Time Since Last Eaten: " + time + "\nDNA: " + DNA + "\nStrength: " + str + "\n";
		return output;
	    }
	
	    public Creature clone(){
		return new Creature(time, DNA);
    }
}	