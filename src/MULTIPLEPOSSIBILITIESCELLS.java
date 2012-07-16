import java.io.FileNotFoundException;
import java.io.IOException;

class MULTIPLEPOSSIBILITIESCELLS
{
	private boolean change = false;
	private SUDOKU sudoku;
	private DISJOINTSUBSETSQUEUE dsq;
	
	//...
	private boolean executePreviousStrategy = false;
	public void setExecutePreviousStrategy() {executePreviousStrategy = true;}
	public void resetExecutePreviousStrategy() {executePreviousStrategy = false;}
	//...
	
	public QUEUE solve(QUEUE q) throws IOException
	{
		change = false;
		sudoku = q.getLast().getSudoku();
		
		//row by row
		for (int row = 1; row <= 9; row++)
		{
			q = findMultiplePossibilities(row, -1, -1, q);
			//for guessing
			if (q == null) return null;
			//end of for guessing
		}
		//column by column
		for (int column = 1; column <= 9; column++)
		{
			q = findMultiplePossibilities(-1, column, -1, q);
			//for guessing
			if (q == null) return null;
			//end of for guessing
		}
		//area by area
		for (int area = 1; area <= 9; area++)
		{
			q = findMultiplePossibilities(-1, -1, area, q);
			//for guessing
			if (q == null) return null;
			//end of for guessing
		}
		return q;
	}
	
	private QUEUE findMultiplePossibilities(int row, int column, int area, QUEUE q) throws IOException
	{
		if ((row != -1 && column != -1) || (column != -1 && area != -1) || (row != -1 && area != -1)) return null;
		dsq = null;
		dsq = new DISJOINTSUBSETSQUEUE();
		listPossibilities(null, row, column, area);
		
		if (row != -1)
		{
			while(!dsq.isEmpty())
			{
				MULTIPLEPOSSIBILITIES mp = dsq.dequeue().getMP();
				int numberOfPossibility = 0;
				for (int j = 1; j <= 9; j++)
				{
					if (sudoku.containsAtLeastOneElement(mp, row, j)) numberOfPossibility++;
				}
				
				if (numberOfPossibility == mp.getLength())
				{
					String temp = "Value of ";
					for (int value = 1; value <= 9; value++)
					{
						if (mp.isPossible(value))
						{
							temp = temp+value+", ";
						}
					}
					temp = temp+" are in an unknown order in row "+row+" in cell ";
					for (int j = 1; j <= 9; j++)
					{
						if (sudoku.containsAtLeastOneElement(mp, row, j)) temp = temp+"["+row+"]["+j+"], ";
					}
					temp = temp+" whatever the order, we can remove any other possibilities from those cells.";
					boolean removed = false;
					for (int j = 1; j <= 9; j++)
					{
						if (sudoku.containsAtLeastOneElement(mp, row, j))
						{
							for (int value = 1; value <= 9; value++)
							{
								if (!mp.isPossible(value))
								{
									if (!removed) removed = sudoku.remove(value, row, j);
									else sudoku.remove(value, row, j);
									//...
									if (executePreviousStrategy)
									{
										SUBSOLVER s = new SUBSOLVER();
										q = s.solve(q, 4);
										if (q == null) return null;
										sudoku = q.getLast().getSudoku();
									}
									//...
									//for guessing
									if (!sudoku.isValidSudoku()) return null;
									//for guessing
								}
							}
						}
					}
					if (removed)
					{
						change = true;
						QUEUENODE qn = new QUEUENODE();
						qn.setText(temp);
						qn.setSudoku(new SUDOKU(sudoku));
						q.enqueue(qn);
					}
					
				}
			}
		}
		else if (column != -1)
		{
			while(!dsq.isEmpty())
			{
				MULTIPLEPOSSIBILITIES mp = dsq.dequeue().getMP();
				int numberOfPossibility = 0;
				for (int i = 1; i <= 9; i++)
				{
					if (sudoku.containsAtLeastOneElement(mp, i, column)) numberOfPossibility++;
				}
				
				if (numberOfPossibility == mp.getLength())
				{
					String temp = "Value of ";
					for (int value = 1; value <= 9; value++)
					{
						if (mp.isPossible(value))
						{
							temp = temp+value+", ";
						}
					}
					temp = temp+" are in an unknown order in column "+column+" in cell ";
					for (int i = 1; i <= 9; i++)
					{
						if (sudoku.containsAtLeastOneElement(mp, i, column)) temp = temp+"["+i+"]["+column+"], ";
					}
					temp = temp+" whatever the order, we can remove any other possibilities from those cells.";
					boolean removed = false;
					for (int i = 1; i <= 9; i++)
					{
						if (sudoku.containsAtLeastOneElement(mp, i, column))
						{
							for (int value = 1; value <= 9; value++)
							{
								if (!mp.isPossible(value))
								{
									if (!removed) removed = sudoku.remove(value, i, column);
									else sudoku.remove(value, i, column);
									//...
									if (executePreviousStrategy)
									{
										SUBSOLVER s = new SUBSOLVER();
										q = s.solve(q, 4);
										if (q == null) return null;
										sudoku = q.getLast().getSudoku();
									}
									//...
									//for guessing
									if (!sudoku.isValidSudoku()) return null;
									//for guessing
								}
							}
						}
					}
					if (removed)
					{
						change = true;
						QUEUENODE qn = new QUEUENODE();
						qn.setText(temp);
						qn.setSudoku(new SUDOKU(sudoku));
						q.enqueue(qn);
					}
					
				}
			}
		}
		else if (area != -1)
		{
			while(!dsq.isEmpty())
			{
				MULTIPLEPOSSIBILITIES mp = dsq.dequeue().getMP();
				int numberOfPossibility = 0;
				for (int i = (area-1)/3*3+1; i <= (area-1)/3*3+3; i++)
				{
					for (int j = (area-1)%3*3+1; j <= (area-1)%3*3+3; j++)
					{
						if (sudoku.containsAtLeastOneElement(mp, i, j)) numberOfPossibility++;
					}
				}
				
				if (numberOfPossibility == mp.getLength())
				{
					String temp = "Value of ";
					for (int value = 1; value <= 9; value++)
					{
						if (mp.isPossible(value))
						{
							temp = temp+value+", ";
						}
					}
					temp = temp+" are in an unknown order in area "+area+" in cell ";
					for (int i = (area-1)/3*3+1; i <= (area-1)/3*3+3; i++)
					{
						for (int j = (area-1)%3*3+1; j <= (area-1)%3*3+3; j++)
						{
							if (sudoku.containsAtLeastOneElement(mp, i, j)) temp = temp+"["+i+"]["+j+"], ";
						}
					}
					temp = temp+" whatever the order, we can remove any other possibilities from those cells.";
					boolean removed = false;
					for (int i = (area-1)/3*3+1; i <= (area-1)/3*3+3; i++)
					{
						for (int j = (area-1)%3*3+1; j <= (area-1)%3*3+3; j++)
						{
							if (sudoku.containsAtLeastOneElement(mp, i, j))
							{
								for (int value = 1; value <= 9; value++)
								{
									if (!mp.isPossible(value))
									{
										if (!removed) removed = sudoku.remove(value, i, j);
										else sudoku.remove(value, i, j);
										//...
										if (executePreviousStrategy)
										{
											SUBSOLVER s = new SUBSOLVER();
											q = s.solve(q, 4);
											if (q == null) return null;
											sudoku = q.getLast().getSudoku();
										}
										//...
										//for guessing
										if (!sudoku.isValidSudoku()) return null;
										//for guessing
									}
								}
							}
						}
					}
					if (removed)
					{
						change = true;
						QUEUENODE qn = new QUEUENODE();
						qn.setText(temp);
						qn.setSudoku(new SUDOKU(sudoku));
						q.enqueue(qn);
					}
					
				}
			}
		}
		return q;
	}
	
	private DISJOINTSUBSETSQUEUE listPossibilities(DISJOINTSUBSETSNODE dsn, int row, int column, int area)
	{
		if ((row != -1 && column != -1) || (column != -1 && area != -1) || (row != -1 && area != -1)) return null;
		
		if (dsn == null)
		{
			for (int value = 1; value <= 9; value++)
			{
				if (row != -1)
				{
					boolean invalidValue = false;
					for (int j = 1; j <= 9; j++)
					{
						if (sudoku.getValue(row, j) == value)
						{
							invalidValue = true;
							break;
						}
					}
					if (invalidValue) continue;
				}
				else if (column != -1)
				{
					boolean invalidValue = false;
					for (int i = 1; i <= 9; i++)
					{
						if (sudoku.getValue(i, column) == value)
						{
							invalidValue = true;
							break;
						}
					}
					if (invalidValue) continue;
				}
				else if (area != -1)
				{
					boolean invalidValue = false;
					for (int i = (area-1)/3*3+1; i <= (area-1)/3*3+3; i++)
					{
						boolean doubleBreak = false;;
						for (int j = (area-1)%3*3+1; j <= (area-1)%3*3+3; j++)
						{
							if (sudoku.getValue(i, j) == value)
							{
								invalidValue = true;
								doubleBreak = true;
								break;
							}
						}
						if (doubleBreak) break;
					}
					if (invalidValue) continue;
				}
				MULTIPLEPOSSIBILITIES mp = new MULTIPLEPOSSIBILITIES(1);
				mp.makePossible(value);
				DISJOINTSUBSETSNODE node = new DISJOINTSUBSETSNODE(mp);
				dsq.enqueue(node);
				listPossibilities(node, row, column, area);
			}
		}
		else
		{
			if (dsn.getMP().getLength() == 9) return dsq;
			int length = dsn.getMP().getLength() + 1;
			for (int value = 1; value <= 9; value++)
			{
				if (dsn.getMP().isPossible(value)) continue;
				
				if (row != -1)
				{
					boolean invalidValue = false;
					for (int j = 1; j <= 9; j++)
					{
						if (sudoku.getValue(row, j) == value)
						{
							invalidValue = true;
							break;
						}
					}
					if (invalidValue) continue;
				}
				else if (column != -1)
				{
					boolean invalidValue = false;
					for (int i = 1; i <= 9; i++)
					{
						if (sudoku.getValue(i, column) == value)
						{
							invalidValue = true;
							break;
						}
					}
					if (invalidValue) continue;
				}
				else if (area != -1)
				{
					boolean invalidValue = false;
					for (int i = (area-1)/3*3+1; i <= (area-1)/3*3+3; i++)
					{
						boolean doubleBreak = false;;
						for (int j = (area-1)%3*3+1; j <= (area-1)%3*3+3; j++)
						{
							if (sudoku.getValue(i, j) == value)
							{
								invalidValue = true;
								doubleBreak = true;
								break;
							}
						}
						if (doubleBreak) break;
					}
					if (invalidValue) continue;
				}
				
				MULTIPLEPOSSIBILITIES mp = new MULTIPLEPOSSIBILITIES(length);
				
				for (int trialValue = 1; trialValue <= 9; trialValue++)
				{
					if (dsn.getMP().isPossible(trialValue)) mp.makePossible(trialValue);
				}
				mp.makePossible(value);
				if (dsq.isInQueue(mp))
				{
					mp = null;
					continue;
				}
				else
				{
					DISJOINTSUBSETSNODE node = new DISJOINTSUBSETSNODE(mp);
					dsq.enqueue(node);
					listPossibilities(node, row, column, area);
				}
			}
		}
		return dsq;
	}
	
	public boolean anyChange()
	{
		return change;
	}
}