import java.io.IOException;

class SINGLECELLCANDIDATES
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
		
		//scan row by row
		for (int i = 1; i <= 9; i++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				int lastColumnPossible = 0;
				for (int j = 1; j <= 9; j++)
				{
					if (sudoku.isPossible(value, i, j))
					{
						numberOfPossibility++;
						lastColumnPossible = j;
					}
				}
				if (numberOfPossibility == 1)
				{
					if (sudoku.determineValue(value, i, lastColumnPossible))
					{
						if (!change) change = true;
						newEntry = new QUEUENODE();
						newEntry.setSudoku(new SUDOKU(sudoku));
						newEntry.setText("The only possibility of value "+value+" in row "+i+" is in column "+lastColumnPossible+". So that the value of cell["+i+"]["+lastColumnPossible+"] = "+value);
						q.enqueue(newEntry);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 1);
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
		
		//scan column by column
		for (int j = 1; j <= 9; j++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				int lastRowPossible = 0;
				
				for (int i = 1; i <= 9; i++)
				{
					if (sudoku.isPossible(value, i, j))
					{
						numberOfPossibility++;
						lastRowPossible = i;
					}
				}
				
				if (numberOfPossibility == 1)
				{
					if (sudoku.determineValue(value,lastRowPossible, j))
					{
						if (!change) change = true;
						newEntry = new QUEUENODE();
						newEntry.setSudoku(new SUDOKU(sudoku));
						newEntry.setText("The only possibility of value "+value+" in column "+j+" is in row "+lastRowPossible+". So that the value of cell["+lastRowPossible+"]["+j+"] = "+value);
						q.enqueue(newEntry);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 1);
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
		
		for (int area = 1; area <= 9; area++)
		{
			for (int value = 1; value <= 9; value++)
			{
				int numberOfPossibility = 0;
				int lastRowPossible = 0;
				int lastColumnPossible = 0;
				
				for (int i = ((area-1)/3*3)+1; i <= ((area-1)/3*3)+3; i++)
				{
					for (int j = ((area-1)%3*3)+1; j <= ((area-1)%3*3)+3; j++)
					{
						if (sudoku.isPossible(value, i, j))
						{
							numberOfPossibility++;
							lastRowPossible = i;
							lastColumnPossible = j;
						}
					}
				}
				if (numberOfPossibility == 1)
				{
					if (sudoku.determineValue(value,lastRowPossible, lastColumnPossible))
					{
						if (!change) change = true;
						newEntry = new QUEUENODE();
						newEntry.setSudoku(new SUDOKU(sudoku));
						newEntry.setText("The only possibility of value "+value+" in area "+area+" is in row "+lastRowPossible+" column "+lastColumnPossible+". So that the value of cell["+lastRowPossible+"]["+lastColumnPossible+"] = "+value);
						q.enqueue(newEntry);
						//...
						if (executePreviousStrategy)
						{
							SUBSOLVER s = new SUBSOLVER();
							q = s.solve(q, 1);
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