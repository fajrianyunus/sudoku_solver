import java.io.FileNotFoundException;
import java.io.IOException;

class XWING
{
	private SUDOKU sudoku;
	private boolean change = false;
	
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
			for (int row = 1; row <= 9; row++)
			{
				for (int column = 1; column <= 9; column++)
				{
					if (sudoku.isPossible(value, row, column))
					{
						clearXY();
						traverse(value, row, column, false, 0);
					
						if (sudoku.isInSameRow(x[0], y[0], x[1], y[1]))
						{
							if (sudoku.removeValueFromRowExceptColumns(value, x[0], y[0], y[1]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from row "+x[0]);
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
									if (q == null) return null;
									sudoku = q.getLast().getSudoku();
								}
								//...
								//for guessing
								if (!sudoku.isValidSudoku()) return null;
								//end of for guessing
							}
						}
						
						if (sudoku.isInSameRow(x[2], y[2], x[3], y[3]))
						{
							if (sudoku.removeValueFromRowExceptColumns(value, x[2], y[2], y[3]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from row "+x[2]);
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
									if (q == null) return null;
									sudoku = q.getLast().getSudoku();
								}
								//...
								//for guessing
								if (!sudoku.isValidSudoku()) return null;
								//end of for guessing
							}
						}
						
						if (sudoku.isInSameColumn(x[0], y[0], x[1], y[1]))
						{
							if (sudoku.removeValueFromColumnExceptRows(value, x[0], x[1], y[0]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from column "+y[0]);
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
									if (q == null) return null;
									sudoku = q.getLast().getSudoku();
								}
								//...
								//for guessing
								if (!sudoku.isValidSudoku()) return null;
								//end of for guessing
							}
						}
						
						if (sudoku.isInSameColumn(x[2], y[2], x[3], y[3]))
						{
							if (sudoku.removeValueFromColumnExceptRows(value, x[2], x[3], y[2]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from column "+y[2]);
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
									if (q == null) return null;
									sudoku = q.getLast().getSudoku();
								}
								//...
								//for guessing
								if (!sudoku.isValidSudoku()) return null;
								//end of for guessing
							}
						}
						
						if (sudoku.isInSameArea(x[0], y[0], x[1], y[1]))
						{
							if (sudoku.removeValueFromAreaExceptCells(value, x[0], y[0], x[1], y[1]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from area "+((x[0]/3*3)+(y[0]%3*3)+1));
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
									if (q == null) return null;
									sudoku = q.getLast().getSudoku();
								}
								//...
								//for guessing
								if (!sudoku.isValidSudoku()) return null;
								//end of for guessing
							}
						}
						
						if (sudoku.isInSameArea(x[2], y[2], x[3], y[3]))
						{
							if (sudoku.removeValueFromAreaExceptCells(value, x[2], y[2], x[3], y[3]))
							{
								if (!change) change = true;
								QUEUENODE qn = new QUEUENODE();
								qn.setSudoku(new SUDOKU(sudoku));
								qn.setText("Surely either but not both "+x[0]+","+y[0]+" or "+x[3]+","+y[3]+" is "+value+" and and also surely either but not both "+x[1]+","+y[1]+" or "+x[2]+","+y[2]+" is "+value+". Whatever the order, surely we can remove any other possibilities of "+value+" from area "+((x[2]/3*3)+(y[2]%3*3)+1));
								q.enqueue(qn);
								//...
								if (executePreviousStrategy)
								{
									SUBSOLVER s = new SUBSOLVER();
									q = s.solve(q, 5);
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
		}
		
		return q;
	}
	
	private int x[] = new int[4];
	private int y[] = new int[4];
	
	private void clearXY()
	{
		for (int counter = 0; counter < 4; counter++)
		{
			x[counter] = -1;
			y[counter] = -1;
		}
	}
	
	private void enterXY(int newX, int newY)
	{
		for (int counter = 0; counter < 4; counter++)
		{
			if (x[counter] == -1 && y[counter] == -1)
			{
				x[counter] = newX;
				y[counter] = newY;
				return;
			}
		}
	}
	
	private boolean isInXYalready(int row, int column)
	{
		for (int counter = 0; counter < 4; counter++)
		{
			if (x[counter] == row && y[counter] == column) return true;
			if (x[counter] == -1 && y[counter] == -1) return false;
		}
		return false;
	}
	
	private boolean traverse(int value, int row, int column, boolean onePairOnly, int numberOfTraversal)
	{
		if (!sudoku.isPossible(value, row, column)) return false;
		if (isInXYalready(row, column) && numberOfTraversal != 4) return false;
		
		if (numberOfTraversal == 4 && row == x[0] && column == y[0]) return true;
		else if (numberOfTraversal >= 4) return false;
		
		enterXY(row, column);
		
		if (onePairOnly)
		{
			if (sudoku.hasOnly2CellsPossibleInRow(value, row))
			{	
				for (int j = 1; j <= 9; j++)
				{
					if (j != column)
					{
						if (traverse(value, row, j, false, numberOfTraversal+1)) return true;
					}
				}
			}
			if (sudoku.hasOnly2CellsPossibleInColumn(value, column))
			{
				for (int i = 1; i <= 9; i++)
				{
					if (i != row)
					{
						if (traverse(value, i, column, false, numberOfTraversal+1)) return true;
					}
				}
			}
			if (sudoku.hasOnly2CellsPossibleInArea(value, row, column))
			{
				for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
				{
					for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
					if (i != row || j != column)
					{
						if (traverse(value, i, j, false, numberOfTraversal+1)) return true;
					}
				}
			}
		}
		else
		{
			for (int i = 1; i <= 9; i++)
			{
				if (i != row)
				{
					if (traverse(value, i, column, true, numberOfTraversal+1)) return true;
				}
			}
			
			for (int j = 1; j <= 9; j++)
			{
				if (j != column)
				{
					if (traverse(value, row, j, true, numberOfTraversal+1)) return true;
				}
			}
			
			for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
			{
				for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
				{
					if (i != row || j != column)
					{
						if (traverse(value, i, j, true, numberOfTraversal+1)) return true;
					}
				}
			}
		}
		for (int counter = 3; counter >= 0; counter--)
		{
			if (x[counter] != -1 && y[counter] != -1)
			{
				x[counter] = -1;
				y[counter] = -1;
				break;
			}
		}
		return false;
	}
	
	public boolean anyChange()
	{
		return change;
	}
}