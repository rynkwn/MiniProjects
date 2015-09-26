/*
 * Participant class is a class that exploits similarities between the Sheep and Lava class to reduce coding, specifically it is an object with a specific x and y-coordinate.
 * protected int x is the x-coordinate of the Participant object
 * protected int y is the y-coordinate of the Participant object
 */

public class Participant
{
	protected int x;
	protected int y;
	
	/* Participant() is the default constructor for the Participant class.
	 * @param none
	 * Pre-conditions: None
	 * Post-conditions: New Participant is created with a x-value of 0 and a y-value of 0.
	 * @return none
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public Participant()
	{
		x = 0;
		y = 0;
	}
	
	/* This is a constructor method for the Participant Class that specifies a value for x and y.
	 * @param int newX is the new x coordinate of the Participant object.
	 * @param int newY is the new y coordinate of the Participant object.
	 * Pre-conditions: In usage, newX and newY must be within the parameters of a grid.
	 * Post-conditions: New Participant object will be created with a specified x-coordinate an a specified y-coordinate.
	 * @return none
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public Participant(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	/* setX is a mutator method which changes the x-coordinate of the Participant object.
	 * @param row is the new value for x.
	 * Pre-conditions: In usage, row must be within the parameters of a grid.
	 * Post-conditions: Participant's x-coordinate changes to the value of row.
	 * @return none.
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public void setX(int row)
	{
		x = row;
	}
	
	/* setY is a mutator method which changes the y-coordinate of the Participant object.
	 * @param col is the new value for the Participant's y-coordinate.
	 * Pre-conditions: In usage, col must be within the parameters of a grid.
	 * Post-conditions: Participant's y-coordinate changes to the value of col.
	 * @return none.
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public void setY(int col)
	{
		y = col;
	}
	
	/* getX is an accessor method that returns the x-coordinate of the Participant object.
	 * @param none
	 * Pre-conditions: Participant must exist and have an instantiated x value.
	 * Post-conditions: Participant's x-coordinate is returned.
	 * @return Participant's x-coordinate.
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public int getX()
	{
		return x;
	}
	
	/* getY is an accessor method that returns the y-coordinate of the Participant object.
	 * @param none
	 * Pre-conditions: Participant must exist and have an instantiated y value.
	 * Post-conditions: Participant's y-coordinate is returned.
	 * @return Participant's y-coordinate.
	 * Local Variables: None
	 * Displayed Output: None
	 */
	public int getY()
	{
		return y;
	}
}