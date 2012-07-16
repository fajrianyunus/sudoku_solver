class SWORDFISHQUEUENODE
{
	private SWORDFISHQUEUENODE next;
	private int row;
	private int column;
	private boolean intersection = false;
	
	public SWORDFISHQUEUENODE(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	public void setNext(SWORDFISHQUEUENODE next)
	{
		this.next = next;
	}
	
	public SWORDFISHQUEUENODE getNext()
	{
		return next;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public void setIntersection()
	{
		intersection = true;
	}
	
	public void desetIntersection()
	{
		intersection = false;
	}
	
	public boolean getIntersection()
	{
		return intersection;
	}
}