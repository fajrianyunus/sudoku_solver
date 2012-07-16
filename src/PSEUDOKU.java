class PSEUDOKU
{
	private Boolean[][] cell = new Boolean[9][9];
	private int numberOfFilledCell = 0;
	private SUDOKU sudoku;
	
	public PSEUDOKU(SUDOKU sudoku)
	{
		CELL temp;
		this.sudoku = sudoku;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				try
				{
					temp = sudoku.getCell(i+1, j+1);
					if (temp.getValue() <= 9 && temp.getValue() >= 1)
					{
						cell[i][j] = new Boolean(true);
						numberOfFilledCell++;
					}
					else cell[i][j] = new Boolean(false);
				}
				catch(NullPointerException ex)
				{
					System.err.println("Sudoku is not full. NullPointerException");
				}
			}
		}
	}
	
	public int getNumberOfFilledCells()
	{
		return numberOfFilledCell;
	}
	
	public boolean hasRotationalSymmetry()
	{
		for (int i = 0; i < 9; i++)//row
		{
			for (int j = 0; j < 9; j++)//column
			{
				if (i - 4 < 0 && j - 4 > 0)
				{
					if (cell[i][8-j] != cell[i][j])
					{
						return false;
					}
				}
				else if (i - 4 < 0 && j - 4 < 0)
				{
					if (cell[8-i][j] != cell[i][j])
					{
						return false;
					}
				}
				else if (i - 4 > 0 && j - 4 < 0)
				{
					if (cell[i][8 - j] != cell[i][j])
					{
						return false;
					}
				}
				else if (i - 4 > 0 && j - 4 > 0)
				{
					if (cell[8 - i][j] != cell[i][j])
					{
						return false;
					}
				}
				else if (i - 4 < 0 && j == 4)
				{
					if (cell[j][i] != cell[i][j])
					{
						return false;
					}
				}
				else if (i == 4 && j < 4)
				{
					if (cell[8-j][i] != cell[i][j])
					{
						return false;
					}
				}
				else if (i - 4 > 0 && j == 4)
				{
					if (cell[j][i] != cell[i][j])
					{
						return false;
					}
				}
				else if (i == 4 && j - 4 > 0)
				{
					if (cell[8-j][i] != cell[i][j])
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean noContradiction()
	{
		for (int row = 1; row <= 9; row++)
		{
			for (int column = 1; column <= 9; column++)
			{
				if (sudoku.getValue(row, column) >= 1 && sudoku.getValue(row, column) <= 9)
				{
					//check for same row
					for (int j = 1; j <= 9; j++)
					{
						if (j == column) continue;
						if (sudoku.getValue(row, column) == sudoku.getValue(row, j)) return false;
					}
					//check for same column
					for (int i = 1; i <= 9; i++)
					{
						if (i == row) continue;
						if (sudoku.getValue(row, column) == sudoku.getValue(i, column)) return false;
					}
					//check for same area
					for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
					{
						for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
						{
							if (i == row && j == column) continue;
							if (sudoku.getValue(row, column) == sudoku.getValue(i, j)) return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean valid()
	{
		if (getNumberOfFilledCells() >= 18 && hasRotationalSymmetry())
		{
			if (noContradiction()) return true;
		}
		else if (getNumberOfFilledCells() >= 17 && !hasRotationalSymmetry())
		{
			if (noContradiction()) return true;
		}
		return false;
	}
}