import java.io.FileNotFoundException;
import java.io.IOException;

class SINGLESECTORCANDIDATES
{
	private boolean change = false;
	private SUDOKU sudoku;
	
	//...
	private boolean executePreviousStrategy = false;
	public void setExecutePreviousStrategy() {executePreviousStrategy = true;}
	public void resetExecutePreviousStrategy() {executePreviousStrategy = false;}
	//...
	
	public QUEUE solve(QUEUE q) throws IOException
	{
		sudoku = q.getLast().getSudoku();
		change = false;
		
		for (int value = 1; value <= 9; value++)
		{
			//row by row
			for (int row = 1; row <= 9; row++)
			{
				int lastColumnPossible = -1;
				for (int column = 1; column <= 9; column++)
				{
					if (sudoku.isPossible(value, row, column))
					{
						if (lastColumnPossible == -1)
						{
							lastColumnPossible = column;
						}
						else
						{
							if (!sudoku.isInSameArea(row, lastColumnPossible, row, column))
							{
								lastColumnPossible = -1;
								break;
							}
							else lastColumnPossible = column;
						}
					}
				}
				if (lastColumnPossible != -1)
				{
					boolean removed = false;
					for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
					{
						for (int j = (lastColumnPossible-1)/3*3+1; j <= (lastColumnPossible-1)/3*3+3; j++)
						{
							if (!sudoku.isInSameRow(i, j, row, lastColumnPossible))
							{
								if (sudoku.remove(value, i, j))
								{
									removed = true;
									change = true;
								}
							}
						}
					}
					if (removed)
					{
						QUEUENODE qn = new QUEUENODE();
						qn.setSudoku(new SUDOKU(sudoku));
						String temp = "Obviously value of "+value+" in area "+((row-1)/3*3+(lastColumnPossible-1)/3+1)+" must be located in row "+row+". Hence any other posibilities of "+value+" in area "+((row-1)/3*3+(lastColumnPossible-1)/3+1)+" are removed";
						qn.setText(temp);
						q.enqueue(qn);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 2);
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
			//column by column
			for (int column = 1; column <= 9; column++)
			{
				int lastRowPossible = -1;
				for (int row = 1; row <= 9; row++)
				{
					if (sudoku.isPossible(value, row, column))
					{
						if (lastRowPossible == -1) lastRowPossible = row;
						else
						{
							if (!sudoku.isInSameArea(row, column, lastRowPossible, column))
							{
								lastRowPossible = -1;
								break;
							}
							else lastRowPossible = row;
						}
					}
				}
				if (lastRowPossible != -1)
				{
					boolean removed = false;
					for (int i = (lastRowPossible-1)/3*3+1; i <= (lastRowPossible-1)/3*3+3; i++)
					{
						for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
						{
							if (!sudoku.isInSameColumn(i, j, lastRowPossible, column))
							{
								if (sudoku.remove(value, i, j))
								{
									removed = true;
									change = true;
								}
							}
						}
					}
					if (removed)
					{
						QUEUENODE qn = new QUEUENODE();
						qn.setSudoku(new SUDOKU(sudoku));
						String temp = "Obviously value of "+value+" in area "+((lastRowPossible-1)/3*3+(column-1)/3+1)+" must be located in column "+column+". Hence any other posibilities of "+value+" in area "+((lastRowPossible-1)/3*3+(column-1)/3+1)+" are removed";
						qn.setText(temp);
						q.enqueue(qn);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 2);
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
			//area by area
			for (int area = 1; area <= 9; area++)
			{
				int lastRowPossible = -1;
				int lastColumnPossible = -1;
				
				boolean allInSameRow = true;
				boolean allInSameColumn = true;
				for (int row = (area-1)/3*3+1; row <= (area-1)/3*3+3; row++)
				{
					for (int column = (area-1)%3*3+1; column <= (area-1)%3*3+3; column++)
					{
						if (sudoku.isPossible(value, row, column))
						{
							if (lastRowPossible == -1 && lastColumnPossible == -1)
							{
								lastRowPossible = row;
								lastColumnPossible = column;
							}
							else
							{
								if (!sudoku.isInSameRow(row, column, lastRowPossible, lastColumnPossible))
								{
									allInSameRow = false;
								}
								if (!sudoku.isInSameColumn(row, column, lastRowPossible, lastColumnPossible))
								{
									allInSameColumn = false;
								}
								lastRowPossible = row;
								lastColumnPossible = column;
							}
						}
					}
				}
				if (allInSameRow)
				{
					boolean removed = false;
					for (int column = 1; column <= 9; column++)
					{
						if (!sudoku.isInSameArea(lastRowPossible, column, lastRowPossible, lastColumnPossible))
						{
							if (sudoku.remove(value, lastRowPossible, column))
							{
								change = true;
								removed = true;
							}
						}
					}
					if (removed)
					{
						QUEUENODE qn = new QUEUENODE();
						qn.setSudoku(sudoku);
						String temp = "Obviously value of "+value+" in row "+lastRowPossible+" must be located in area "+area+". Hence any other posibilities of "+value+" in row "+lastRowPossible+" are removed";
						qn.setText(temp);
						q.enqueue(qn);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 2);
							if (q == null) return null;
							sudoku = q.getLast().getSudoku();
						}
						//...
						//for guessing
						if (!sudoku.isValidSudoku()) return null;
						//end of for guessing
					}
				}
				//...............................................
				if (allInSameColumn)
				{
					boolean removed = false;
					for (int row = 1; row <= 9; row++)
					{
						if (!sudoku.isInSameArea(row, lastColumnPossible, lastRowPossible, lastColumnPossible))
						{
							if (sudoku.remove(value, row, lastColumnPossible))
							{
								removed = true;
								change = true;
							}
						}
					}
					if (removed)
					{
						QUEUENODE qn = new QUEUENODE();
						qn.setSudoku(sudoku);
						String temp = "Obviously value of "+value+" in column "+lastColumnPossible+" must be located in area "+area+". Hence any other posibilities of "+value+" in column "+lastColumnPossible+" are removed";
						qn.setText(temp);
						q.enqueue(qn);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 2);
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
		return q;
	}
	
	public boolean anyChange()
	{
		return change;
	}
}