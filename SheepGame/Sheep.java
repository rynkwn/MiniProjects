/*
 * Sheep class extends Participant designates specific attributes of the Sheep object.
 * private int hp represents the number of collisions the Sheep object is able to survive.
 * constant DEATH will be the hp of the sheep if it is no longer alive.
 */
 

public class Sheep extends Participant
{
	private int hp;
	public static final int DEATH = 0;
	
	/* Sheep() is the default constructor of the Sheep class
	 * @param none
	 * Pre-conditions: None
	 * Post-conditions: New sheep object will be created with hp = 1, x-coordinate = 0, and y-coordinate = 0
	 * @return none
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public Sheep()
	{
		super();
		hp = 1;
	}
	
	/* addHP will add a certain positive or negative integer to the Sheep's current HP.
	 * @param int num will be the amount the sheep's HP is increased or decreased.
	 * Pre-conditions: Sheep must exist and HP must be instantiated.
	 * Post-conditions: Sheep's HP now adjusts by adding num.
	 * @return None
	 * Local Variables: None.
	 * Displayed Output: None.
	 * Error Handling: None.
	 */
	public void addHP(int num)
	{
		hp += num;
	}
	
	/* getHP will return the HP of the Sheep object
	 * @param none
	 * Pre-conditions: Sheep must exist and HP must be instantiated.
	 * Post-conditions: Sheep's 
	 * @return The HP of the Sheep.
	 * Local Variables: None.
	 * Displayed Output: None.
	 * Error Handling: None.
	 */
	public int getHP()
	{
		return hp;
	}
}