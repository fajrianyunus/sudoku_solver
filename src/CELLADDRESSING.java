class CELLADDRESSING
{
	private int row;
	private int column;
	
	public CELLADDRESSING(int row, int column)
	{
		if (row >= 1 && row <= 9 && column >= 1 && column <= 9)
		{
			this.row = row;
			this.column = column;
		}
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	
	
	
}