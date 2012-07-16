import java.io.IOException;

class DISJOINTSUBSETS
{
	private SUDOKU sudoku;
	private boolean change = false;
	private DISJOINTSUBSETSQUEUE dsq;
	private boolean[] cellMP = new boolean[9];
	
	//...
	private boolean executePreviousStrategy = false;
	public void setExecutePreviousStrategy() {executePreviousStrategy = true;}
	public void resetExecutePreviousStrategy() {executePreviousStrategy = false;}
	//...
	
	public QUEUE solve(QUEUE q) throws IOException
	{
		change = false;
		sudoku = q.getLast().getSudoku();
		
		for (int i = 1; i <= 9; i++)
		{
			for (int j = 1; j <= 9; j++)
			{
				if (sudoku.numberOfPossibility(i, j) > 1)
				{
					dsq = new DISJOINTSUBSETSQUEUE();
					combinationRecursive(null, i, j);
					
					while(!dsq.isEmpty())
					{
						MULTIPLEPOSSIBILITIES mp = dsq.dequeue().getMP();
						QUEUENODE qn;
						
						for (int counter = 0; counter < 9; counter++)
						{
							cellMP[counter] = false;
						}
						
						if (clearPossibilitiesExceptCommonMultiplePossibilitiesInRow(mp, i))
						{
							if (!change) change = true;
							qn = new QUEUENODE();
							qn.setSudoku(new SUDOKU(sudoku));
							String temp = "There are "+mp.getLength()+" cells in row "+i+" which commonly posses possibilities of ";
							for (int trialValue = 1; trialValue <= 9; trialValue++)
							{
								if (mp.isPossible(trialValue))
								{
									temp = temp+trialValue+", ";
								}
							}
							temp = temp+" they are column: ";
							for (int counter = 0; counter < 9; counter++)
							{
								if (cellMP[counter])
								{
									temp = temp+(counter+1)+", ";
								}
							}
							temp = temp+" hence any other possibilities of those cells are removed";
							qn.setText(temp);
							q.enqueue(qn);
							//...
							if (executePreviousStrategy)
							{
								SUBSOLVER s = new SUBSOLVER();
								q = s.solve(q, 3);
								if (q == null) return null;
								sudoku = q.getLast().getSudoku();
							}
							//...
							//for guessing
							if (!sudoku.isValidSudoku()) return null;
							//end of for guessing
						}
						
						for (int counter = 0; counter < 9; counter++)
						{
							cellMP[counter] = false;
						}
						
						if (clearPossibilitiesExceptCommonMultiplePossibilitiesInColumn(mp, j))
						{
							if (!change) change = true;
							qn = new QUEUENODE();
							qn.setSudoku(new SUDOKU(sudoku));
							String temp = "There are "+mp.getLength()+" cells in column "+j+" which commonly posses possibilities of ";
							for (int trialValue = 1; trialValue <= 9; trialValue++)
							{
								if (mp.isPossible(trialValue))
								{
									temp = temp+trialValue+", ";
								}
							}
							temp = temp+" they are row: ";
							for (int counter = 0; counter < 9; counter++)
							{
								if (cellMP[counter])
								{
									temp = temp+(counter+1)+", ";
								}
							}
							temp = temp+" hence any other possibilities of those cells are removed";
							qn.setText(temp);
							q.enqueue(qn);
							//...
							if (executePreviousStrategy)
							{
								SUBSOLVER s = new SUBSOLVER();
								q = s.solve(q, 3);
								if (q == null) return null;
								sudoku = q.getLast().getSudoku();
							}
							//...
							//for guessing
							if (!sudoku.isValidSudoku()) return null;
							//end of for guessing
						}
						
						for (int counter = 0; counter < 9; counter++)
						{
							cellMP[counter] = false;
						}
						
						if (clearPossibilitiesExceptCommonMultiplePossibilitiesInArea(mp, (i-1)/3*3+(j-1)%3+1))
						{
							if (!change) change = true;
							qn = new QUEUENODE();
							qn.setSudoku(new SUDOKU(sudoku));
							String temp = "There are "+mp.getLength()+" cells in area "+(i-1)/3*3+(j-1)%3+1+" which commonly posses possibilities of ";
							for (int trialValue = 1; trialValue <= 9; trialValue++)
							{
								if (mp.isPossible(trialValue))
								{
									temp = temp+trialValue+", ";
								}
							}
							temp = temp+" they are row and column: ";
							int areaTemp = (i-1)/3*3+(j-1)%3;
							for (int counter = 0; counter < 9; counter++)
							{
								if (cellMP[counter])
								{
									temp = temp+(areaTemp/3*3+counter/3*3+1)+","+(areaTemp%3*3+counter%3*3+1)+" , ";
								}
							}
							temp = temp+" hence any other possibilities of those cells are removed";
							qn.setText(temp);
							q.enqueue(qn);
							//...
							if (executePreviousStrategy)
							{
								SUBSOLVER s = new SUBSOLVER();
								q = s.solve(q, 3);
								if (q == null) return null;
								sudoku = q.getLast().getSudoku();
							}
							//...
							//for guessing
							if (!sudoku.isValidSudoku()) return null;
							//end of for guessing
						}
					}
					
				}
			}
		}
		return q;
	}
	
	public boolean anyChange()
	{
		return change;
	}
	
	private boolean findMultiplePossibilitiesInRow(MULTIPLEPOSSIBILITIES mp, int row)
	{
		int numberOfColumns = 0;
		for (int column = 1; column <= 9; column++)
		{
			//if (mp.isTogetherlyImpossible(sudoku.getCell(row, column))) return false;
			
			if (mp.partiallyPossible(sudoku.getCell(row, column))) return false;
			
			if (mp.isTogetherlyPossible(sudoku.getCell(row, column)))
			{
				numberOfColumns++;
			}
		}
		if (numberOfColumns == mp.getLength()) return true;
		else return false;
	}
	
	private boolean clearPossibilitiesExceptCommonMultiplePossibilitiesInRow(MULTIPLEPOSSIBILITIES mp, int row)
	{
		if (!findMultiplePossibilitiesInRow(mp, row)) return false;
		
		int counter = 0;
		boolean returnValue = false;
		
		for (int column = 1; column <= 9; column++)
		{
			if (mp.isTogetherlyPossible(sudoku.getCell(row, column)))
			{
				counter++;
				
				for (int value = 1; value <= 9; value++)
				{
					if (!mp.isPossible(value))
					{
						if (!returnValue) returnValue = sudoku.remove(value, row, column);
						else sudoku.remove(value, row, column);
					}
				}
				cellMP[column-1] = true;
				
				if (counter == mp.getLength()) return returnValue;
			}
		}
		return false;
	}
	
	private boolean clearPossibilitiesExceptCommonMultiplePossibilitiesInColumn(MULTIPLEPOSSIBILITIES mp, int column)
	{
		if (!findMultiplePossibilitiesInColumn(mp, column)) return false;
		int counter = 0;
		boolean returnValue = false;
		
		for (int row = 1; row <= 9; row++)
		{
			if (mp.isTogetherlyPossible(sudoku.getCell(row, column)))
			{
				counter++;
				
				for (int value = 1; value <= 9; value++)
				{
					if (!mp.isPossible(value))
					{
						if (!returnValue) returnValue = sudoku.remove(value, row, column);
						else sudoku.remove(value, row, column);
					}
				}
				
				cellMP[row-1] = true;
				
				if (counter == mp.getLength()) return returnValue;
			}
		}
		return false;
	}
	
	private boolean findMultiplePossibilitiesInColumn(MULTIPLEPOSSIBILITIES mp, int column)
	{
		int numberOfRows = 0;
		for (int row = 1; row <= 9; row++)
		{
			//if (mp.isTogetherlyImpossible(sudoku.getCell(row, column))) return false;
			
			if (mp.partiallyPossible(sudoku.getCell(row, column))) return false;
			
			if (mp.isTogetherlyPossible(sudoku.getCell(row, column))) numberOfRows++;
		}
		if (numberOfRows == mp.getLength())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean findMultiplePossibilitiesInArea(MULTIPLEPOSSIBILITIES mp, int area)
	{
		int numberOfCells = 0;
		for (int row = (area-1)/3*3+1; row <= (area-1)/3*3+3; row++)
		{
			for (int column = (area-1)%3*3+1; column <= (area-1)%3*3+3; column++)
			{
				//if (mp.isTogetherlyImpossible(sudoku.getCell(row, column))) return false;
				
				if (mp.partiallyPossible(sudoku.getCell(row, column))) return false;
				
				if (mp.isTogetherlyPossible(sudoku.getCell(row, column)))
				{
					numberOfCells++;
				}
			}
		}
		if (numberOfCells == mp.getLength()) return true;
		else return false;
	}
	
	private boolean clearPossibilitiesExceptCommonMultiplePossibilitiesInArea(MULTIPLEPOSSIBILITIES mp, int area)
	{
		if (!findMultiplePossibilitiesInArea(mp, area)) return false;
		
		int counter = 0;
		boolean returnValue = false;
		
		for (int row = (area-1)/3*3+1; row <= (area-1)/3*3+3; row++)
		{
			for (int column = (area-1)%3*3+1; column <= (area-1)%3*3+3; column++)
			{
				if (mp.isTogetherlyPossible(sudoku.getCell(row, column)))
				{
					counter++;
					
					for (int value = 1; value <= 9; value++)
					{
						if (!mp.isPossible(value))
						{
							if (!returnValue) returnValue = sudoku.remove(value, row, column);
							else sudoku.remove(value, row, column);
						}
					}
					
					cellMP[(row-1)%3*3+(column-1)%3] = true;
					
					if (counter == mp.getLength()) return false;
				}
			}
		}
		return false;
	}
	
	private void combinationRecursive(MULTIPLEPOSSIBILITIES mp, int row, int column)
	{
		if (mp != null)
		{	
			if (dsq.isInQueue(mp))
			{	
				return;
			}
			else
			{
				dsq.enqueue(new DISJOINTSUBSETSNODE(mp));
			}
		}
		else
		{	
			for (int value1 = 1; value1 <= 9; value1++)
			{
				for (int value2 = 1; value2 <= 9; value2++)
				{
					if (sudoku.isPossible(value1, row, column) && sudoku.isPossible(value2, row, column) && value1 != value2)
					{
						mp = new MULTIPLEPOSSIBILITIES(2);
						mp.makePossible(value1);
						mp.makePossible(value2);
						combinationRecursive(mp, row, column);
					}
				}
			}
			return;
		}
		
		for (int value = 1; value <= 9; value++)
		{
			if (sudoku.isPossible(value, row, column))
			{
				if (!mp.isPossible(value))
				{
					MULTIPLEPOSSIBILITIES newMP = new MULTIPLEPOSSIBILITIES(mp.getLength()+1);
					for (int trialValue = 1; trialValue <= 9; trialValue++)
					{
						if (mp.isPossible(trialValue)) newMP.makePossible(trialValue);
					}
					newMP.makePossible(value);
					combinationRecursive(newMP, row, column);
				}
			}
		}
	}
}