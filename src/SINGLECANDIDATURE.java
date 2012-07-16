import java.io.IOException;

class SINGLECANDIDATURE
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
		QUEUENODE last = q.getLast();
		sudoku = new SUDOKU(last.getSudoku());
		change = false;
		QUEUENODE newEntry;
		SUDOKU tempSudoku;
		
		boolean smallChange;
		
		for (int i = 1; i <= 9; i++)
		{
			for (int j = 1; j <= 9; j++)
			{
				if (sudoku.getValue(i, j) >= 1 && sudoku.getValue(i, j) <= 9)
				{
					smallChange = false;
					
					if (!change)
					{
						change = sudoku.removeValueFromRowExceptInColumn(sudoku.getValue(i, j), i, j);
						smallChange = change;
					}
					else if (!smallChange)
					{
						smallChange = sudoku.removeValueFromRowExceptInColumn(sudoku.getValue(i, j), i, j);
					}
					else
					{
						sudoku.removeValueFromRowExceptInColumn(sudoku.getValue(i, j), i, j);
					}
					
					
					if (!change)
					{
						change = sudoku.removeValueFromColumnExceptInRow(sudoku.getValue(i, j), i, j);
						smallChange = change;
					}
					else if (!smallChange)
					{
						smallChange = sudoku.removeValueFromColumnExceptInRow(sudoku.getValue(i, j), i, j);
					}
					else
					{
						sudoku.removeValueFromColumnExceptInRow(sudoku.getValue(i, j), i, j);
					}
					
					if (!change)
					{
						change = sudoku.removeValueFromAreaExceptInRowColumn(sudoku.getValue(i, j), i, j);
						smallChange = change;
					}
					else if (!smallChange)
					{
						smallChange = sudoku.removeValueFromAreaExceptInRowColumn(sudoku.getValue(i, j), i, j);
					}
					else
					{
						sudoku.removeValueFromAreaExceptInRowColumn(sudoku.getValue(i, j), i, j);
					}
					if (smallChange)
					{
						tempSudoku = new SUDOKU(sudoku);
						newEntry = new QUEUENODE();
						newEntry.setSudoku(tempSudoku);
						newEntry.setText("Cell of row "+i+" and column "+j+" has value "+sudoku.getValue(i, j)+". So that the possibilities of "+sudoku.getValue(i, j)+" are removed from any other cells in row "+i+", column "+j+", area "+((i-1)/3*3+(j-1)/3+1));
						q.enqueue(newEntry);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 0);
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