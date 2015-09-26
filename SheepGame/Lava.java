/*
 * The Lava class extends Participant and creates a Lava object which has a direction attribute.
 * private int direction is the direction the Lava will flow when updated. It is an integer which represents direction through degrees.
 * public static final int EAST is the int value which represents EAST in degrees.
 * public static final int NORTH is the int value which represents NORTH in degrees.
 * public static final int WEST is the int value which represents WEST in degrees.
 * public static final int SOUTH is the int value which represents SOUTH in degrees.
 */

public class Lava extends Participant
{
	private int direction;
	public static final int EAST = 0;
	public static final int NORTH = 90;
	public static final int WEST = 180;
	public static final int SOUTH = 270;
	
	/* Lava is the constructor for the Lava class
	 * @param int x is the row in which the lava begins
	 * @param int y is the column in which the lava begins
	 * @param xMax is the total number of rows in the grid
	 * @param yMax is the total number of columns in the grid
	 * Pre-conditions: x and y must be less than xMax and yMax respectively
	 * Post-conditions: New Lava object will be created with a direction and an initial position.
	 * @return none
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public Lava(int x, int y, int xMax, int yMax)
	{
		super(x, y);
		if(x == xMax && y != yMax) //If the lava spawns at the bottom of the grid
			direction = NORTH;
		else if (x == 0 && y != yMax) //If the lava spawns at the top of the grid
			direction = SOUTH;
		else if (y == yMax) //If the above are false, then if the lava spawns at the right-most part of the grid
			direction = WEST;
		else if (y == 0) //If all of the above are false, then if the lava spawns at the left-most part of the grid
			direction = EAST;
	}
	
	/* setDir will set the direction attribute of the Lava object
	 * @param int newDir is the new direction of the Lava object.
	 * Pre-conditions: Lava must exist and newDir must be equal to one of the four direction constants.
	 * Post-conditions: Lava's direction is now equal to newDir.
	 * @return None
	 * Local Variables: None.
	 * Displayed Output: None.
	 * Error Handling: None.
	 */
	public void setDir(int newDir)
	{
		direction = newDir;
	}
	
	/* update will change the Lava's position based upon the Lava's direction.
	 * @param None
	 * Pre-conditions: Lava must exist, must have coordinates within the grid (Which will always be a square of length 15), and must have one of the four direction constants as a direction.
	 * Post-conditions: Lava changes position based upon direction.
	 * @return Will return false if the lava would flow out of the grid if it continues. Otherwise will return true if update was successful.
	 * Local Variables: int horiz is the horizontal movement of the lava.
	 *							int virt is the vertical movement of the lava.
	 *							int maxHoriz is the maximum width of the grid - 1. Consequently the grid has a width of 15.
	 *							int maxVirt is the maximum height of a grid - 1.
	 *							int minHorz is the leftmost row of the grid.
	 *							int minVirt is the bottommost row of the grid.
	 * Displayed Output: None.
	 * Error Handling: 
	 */
	public boolean update()
	{
		int horiz = 0, virt = 0, maxHoriz = 14, maxVirt = 14, minHorz = 0, minVirt = 0;
		if(direction == EAST)
			virt = 1;
		else if(direction == NORTH)
			horiz = -1;
		else if(direction == WEST)
			virt = -1;
		else if(direction == SOUTH)
			horiz = 1;
		int currentX = getX();
		int currentY = getY();
		if(currentX + horiz == 15 || currentX + horiz == -1 || currentY + virt == 15 || currentY + virt == -1) //If moving will not move the Lava outside of the grid.
		{
			return false;
		}
		else
		{
			setX(getX() + horiz);
			setY(getY() + virt);
			return true;
		}
	}
	
	/* getDirection will return the direction of the Lava object
	 * @param None
	 * Pre-conditions: Lava must exist and have coordinates (x and y attributes) instantiated.
	 * Post-conditions: Returns the direction of the Lava.
	 * @return The direction of the lava.
	 * Local Variables: None.
	 * Displayed Output: None.
	 * Error Handling: None.
	 */
	public int getDirection()
	{
		return direction;
	}
}