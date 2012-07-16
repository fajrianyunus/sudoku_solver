class SUDOKU
{
	private CELL cell[][] = new CELL[9][9];
	
	public SUDOKU(CELL[][] newCell)
	{
		try
		{
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					cell[i][j] = newCell[i][j];
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			System.err.println("argument index is out of bound");
		}
	}
	
	public SUDOKU(SUDOKU sudoku)
	{
		for (int row = 1; row <= 9; row++)
		{
			for (int column = 1; column <= 9; column++)
			{
				cell[row-1][column-1] = new CELL();
				for (int value = 1; value <= 9; value++)
				{
					if (!sudoku.getCell(row, column).isPossible(value))
					{
						cell[row-1][column-1].remove(value);
					}
				}
			}
		}
	}
	
	public boolean valid(int row, int column)
	{
		if (row >= 1 && row <= 9 && column >= 1 && column <= 9) return true;
		else return false;
	}
	
	public CELL getCell(int row, int column)
	{
		if (valid(row, column))
		{
			return cell[row-1][column-1];
		}
		else return null;
	}
	
	public boolean isSame(SUDOKU newSudoku)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				try{if (!cell[i][j].isSame(newSudoku.getCell(i, j))) return false;}
				catch(NullPointerException ex)
				{
					if (cell[i][j] == null && newSudoku.getCell(i, j) == null)
					{
						if (j < 8) j++;
						else if (i < 8 && j == 9) i++;
						else if (i == 8 && j == 8) return true;
					}
					else return false;
				}
			}
		}
		return true;
	}
	
	public boolean addCell(CELL newCell, int row, int column)
	{
		if (valid(row, column))
		{
			if (newCell.isSame(cell[row-1][column-1]))
			{
				return false;
			}
			else
			{
				cell[row-1][column-1] = newCell;
				return true;
			}
		}
		else return false;
	}
	
	public boolean remove(int value, int row, int column)
	{
		if (valid(row, column))
		{
			row--;
			column--;
			return cell[row][column].remove(value);
		}
		else return false;
	}
	
	public int getValue(int row, int column)
	{
		if (valid(row, column))
		{
			return getCell(row, column).getValue();
		}
		else return -1;
	}
	
	public boolean removeValueFromRowExceptInColumn(int value, int row, int column)
	{
		if (valid(row, column))
		{
			boolean returnValue = false;
			for (int j = 1; j <= 9; j++)
			{
				if (j != column)
				{
					if (!returnValue)
					{
						returnValue = remove(value, row, j);
					}
					else
					{
						remove(value, row, j);
					}
				}
			}
			return returnValue;
		}
		else return false;
	}
	
	public boolean removeValueFromColumnExceptInRow(int value, int row, int column)
	{
		if (valid(row, column))
		{
			boolean returnValue = false;
			for (int i = 1; i <= 9; i++)
			{
				if (i != row)
				{
					if (!returnValue)
					{
						returnValue = remove(value, i, column);
					}
					else
					{
						remove(value, i, column);
					}
				}
			}
			return returnValue;
		}
		else return false;
	}
	
	public boolean removeValueFromAreaExceptInRowColumn(int value, int row, int column)
	{
		if (valid(row, column))
		{
			boolean returnValue = false;
			row--;
			column--;
			for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++)
			{
				for (int j = column / 3 * 3; j < column / 3 * 3 + 3; j++)
				{
					if (i != row || j != column)
					{
						if (!returnValue)
						{
							returnValue = remove(value, i+1, j+1);
						}
						else
						{
							remove(value, i+1, j+1);
						}
					}
				}
			}
			return returnValue;
		}
		else return false;
	}
	
	public boolean isPossible(int value, int row, int column)
	{
		if (valid(row, column))
		{
			if (value >= 1 && value <= 9)
			{
				if (getCell(row, column).isPossible(value)) return true;
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean determineValue(int value, int row, int column)
	{
		if (valid(row, column))
		{
			if (value >= 1 && value <= 9)
			{
				if (getValue(row, column) == value) return false;
				else if (getValue(row, column) < 1 || getValue(row, column) > 9)
				{
					for (int trialValue = 1; trialValue <= 9; trialValue++)
					{
						if (trialValue != value)
						{
							remove(trialValue, row, column);
						}
					}
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	public int numberOfPossibility(int row, int column)
	{
		return getCell(row, column).numberOfPossibility();
	}
	
	public boolean hasOnly2CellsPossibleInRow(int value, int row)
	{
		int numberOfPossibility = 0;
		
		for (int column = 1; column <= 9; column++)
		{
			if (isPossible(value, row, column))
			{
				numberOfPossibility++;
			}
		}
		if (numberOfPossibility == 2) return true;
		else return false;
	}
	
	public int columnInRowWhichIsPossibleOfValueExcept(int value, int row, int column)
	{
		if (!isPossible(value, row, column)) return -1;
		else if (!hasOnly2CellsPossibleInRow(value, row)) return -1;
		else
		{
			for (int j = 1; j <= 9; j++)
			{
				if (j != column)
				{
					if (isPossible(value, row, j)) return j;
				}
			}
			return -1;
		}
	}

	public boolean hasOnly2CellsPossibleInColumn(int value, int column)
	{
		int numberOfPossibility = 0;
		
		for (int row = 1; row <= 9; row++)
		{
			if (isPossible(value, row, column))
			{
				numberOfPossibility++;
			}
		}
		if (numberOfPossibility == 2) return true;
		else return false;
	}
	
	public int rowInColumnWhichIsPossibleOfValueExcept(int value, int row, int column)
	{
		if (!isPossible(value, row, column)) return -1;
		else if (!hasOnly2CellsPossibleInColumn(value, column)) return -1;
		else
		{
			for (int i = 1; i <= 9; i++)
			{
				if (i != row)
				{
					if (isPossible(value, i, column)) return i;
				}
			}
			return -1;
		}
	}
	
	public boolean hasOnly2CellsPossibleInArea(int value, int row, int column)
	{
		int numberOfPossibility = 0;
		
		for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
		{
			for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
			{
				if (isPossible(value, i, j)) numberOfPossibility++;
			}
		}
		
		if (numberOfPossibility == 2) return true;
		else return false;
	}
	
	public CELLADDRESSING cellInAreaWhichIsPossibleOfValueExcept(int value, int row, int column)
	{
		if (!isPossible(value, row, column)) return null;
		else if (!hasOnly2CellsPossibleInArea(value, row, column)) return null;
		else
		{
			for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
			{
				for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
				{
					if (i != row || j != column)
					{
						if (isPossible(value, i, j))
						{
							CELLADDRESSING ca = new CELLADDRESSING(i, j);
							return ca;
						}
					}
				}
			}
			return null;
		}
	}
	
	public boolean hasOnly2CellsPossibleInSector(int value, int row, int column)
	{
		if (isPossible(value, row, column))
		{
			if (hasOnly2CellsPossibleInRow(value, row)) return true;
			else if (hasOnly2CellsPossibleInColumn(value, column)) return true;
			else if (hasOnly2CellsPossibleInArea(value, row, column)) return true;
			else return false;
		}
		else return false;
	}
	
	public boolean contradictsEachOther(int value, int row1, int column1, int row2, int column2)
	{
		if (isPossible(value, row1, column1) && isPossible(value, row2, column2))
		{
			if (isInSameRow(row1, column1, row2, column2)) return true;
			else if (isInSameColumn(row1, column1, row2, column2)) return true;
			else if (isInSameArea(row1, column1, row2, column2)) return true;
			else return false;
		}
		else return false;
	}
	
	public boolean isInSameRow(int row1, int column1, int row2, int column2)
	{
		if (!(valid(row1, column1) && valid(row2, column2))) return false;
		
		if (row1 == row2) return true;
		else return false;
	}
	
	public boolean isInSameColumn(int row1, int column1, int row2, int column2)
	{
		if (!(valid(row1, column1) && valid(row2, column2))) return false;
		
		if (column1 == column2) return true;
		else return false;
	}
	
	public boolean isInSameArea(int row1, int column1, int row2, int column2)
	{
		if (!(valid(row1, column1) && valid(row2, column2))) return false;
		
		if (row1 >= (row2-1)/3*3+1 && row1 <= (row2-1)/3*3+3)
		{
			if (column1 >= (column2-1)/3*3+1 && column1 <= (column2-1)/3*3+3)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean removeValueFromRowExceptColumns(int value, int row, int column1, int column2)
	{
		boolean returnValue = false;
		
		for (int column = 1; column <= 9; column++)
		{
			if (column != column1 && column != column2)
			{
				if (!returnValue) returnValue = remove(value, row, column);
				else remove(value, row, column);
			}
		}
		return returnValue;
	}
	
	public boolean removeValueFromColumnExceptRows(int value, int row1, int row2, int column)
	{
		boolean returnValue = false;
		
		for (int row = 1; row <= 9; row++)
		{
			if (row != row1 && row != row2)
			{
				if (!returnValue) returnValue = remove(value, row, column);
				else remove(value, row, column);
			}
		}
		return returnValue;
	}
	
	public boolean removeValueFromAreaExceptCells(int value, int row1, int column1, int row2, int column2)
	{
		if (!isInSameArea(row1, column1, row2, column2)) return false;
		boolean returnValue = false;
		
		for (int row = (row1-1)/3*3+1; row <= (row1-1)/3*3+3; row++)
		{
			for (int column = (column1-1)/3*3+1; column <= (column1-1)/3*3+3; column++)
			{
				if ((row != row1 || column != column1) && (row != row2 || column != column2))
				{
					if (!returnValue) returnValue = remove(value, row, column);
					else remove(value, row, column);
				}
			}
		}
		return returnValue;
	}
	
	public boolean isValidSudoku()
	{
		for (int row = 1; row <= 9; row++)
		{
			for (int column = 1; column <= 9; column++)
			{
				int numberOfPossibility = 0;
				for (int value = 1; value <= 9; value++)
				{
					if (isPossible(value, row, column)) numberOfPossibility++;
				}
				if (numberOfPossibility == 0) return false;
			}
		}
		for (int row = 1; row <= 9; row++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				for (int column = 1; column <= 9; column++)
				{
					if (isPossible(value, row, column)) numberOfPossibility++;
				}
				if (numberOfPossibility == 0) return false;
			}
		}
		for (int column = 1; column <= 9; column++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				for (int row = 1; row <= 9; row++)
				{
					if (isPossible(value, row, column)) numberOfPossibility++;
				}
				if (numberOfPossibility == 0) return false;
			}
		}
		for (int area = 1; area <= 9; area++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				for (int row = (area-1)/3*3+1; row <= (area-1)/3*3+3; row++)
				{
					for (int column = (area-1)%3*3+1; column <= (area-1)%3*3+3; column++)
					{
						if (isPossible(value, row, column)) numberOfPossibility++;
					}
				}
				if (numberOfPossibility == 0) return false;
			}
		}
		
		return true;
	}
	
	public boolean containsAtLeastOneElement(MULTIPLEPOSSIBILITIES multiplepossibilities, int row, int column)
	{
		if (getCell(row, column).containsAtLeastOneElement(multiplepossibilities)) return true;
		else return false;
	}
}