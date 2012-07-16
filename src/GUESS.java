import java.io.FileNotFoundException;
import java.io.IOException;

class GUESS
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
		change = false;
		sudoku = q.getLast().getSudoku();
		
		for (int row = 1; row <= 9; row++)
		{
			for (int column = 1; column <= 9; column++)
			{	
				if (sudoku.getValue(row, column) == -1)//no exact value has been found yet
				{
					for (int value = 1; value <= 9; value++)
					{
						if (sudoku.isPossible(value, row, column))
						{
							SUDOKU tempSudoku = new SUDOKU(sudoku);
							QUEUE tempQ = new QUEUE();
							QUEUENODE qn = new QUEUENODE();
							qn.setSudoku(tempSudoku);
							qn.setText("guessing");
							tempQ.enqueue(qn);
							
							tempSudoku.determineValue(value, row, column);
							if (testSudoku(tempQ))
							{
								if (sudoku.determineValue(value, row, column))
								{
									change = true;
									QUEUENODE node = new QUEUENODE();
									node.setSudoku(new SUDOKU(sudoku));
									node.setText("By try and error way, computer found that the value of cell["+row+"]["+column+"] = "+value);
									q.enqueue(node);
									//...
									if (executePreviousStrategy)
									{
										SUBSOLVER s = new SUBSOLVER();
										q = s.solve(q, 7);
										if (q == null) return null;
										sudoku = q.getLast().getSudoku();
									}
									//...
									return q;
								}
							}
						}
					}
				}
			}
		}
		
		return q;
	}
	
	private boolean testSudoku(QUEUE q) throws IOException
	{
		SINGLECANDIDATURE singlecandidature = new SINGLECANDIDATURE();
		do
		{
			q = singlecandidature.solve(q);
			if (q == null) return false; //to test sudoku
		}while(singlecandidature.anyChange());
		
		SINGLECELLCANDIDATES singlecellcandidates = new SINGLECELLCANDIDATES();
		do
		{
			q = singlecellcandidates.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = singlecandidature.solve(q);
				if (q == null) return false; //to test sudoku
			}while(singlecandidature.anyChange());
		
		}while(singlecellcandidates.anyChange());
		
		SINGLESECTORCANDIDATES singlesectorcandidates = new SINGLESECTORCANDIDATES();
		do
		{
			q = singlesectorcandidates.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = singlecellcandidates.solve(q);
				if (q == null) return false; //to test sudoku
				do
				{
					q = singlecandidature.solve(q);
					if (q == null) return false; //to test sudoku
				}while(singlecandidature.anyChange());
			
			}while(singlecellcandidates.anyChange());
		}while(singlesectorcandidates.anyChange());
		
		DISJOINTSUBSETS disjointsubsets = new DISJOINTSUBSETS();
		
		do
		{
			q = disjointsubsets.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = singlesectorcandidates.solve(q);
				if (q == null) return false; //to test sudoku
				do
				{
					q = singlecellcandidates.solve(q);
					if (q == null) return false; //to test sudoku
					do
					{
						q = singlecandidature.solve(q);
						if (q == null) return false; //to test sudoku
					}while(singlecandidature.anyChange());
				
				}while(singlecellcandidates.anyChange());
			}while(singlesectorcandidates.anyChange());
		
		}while(disjointsubsets.anyChange());
		
		MULTIPLEPOSSIBILITIESCELLS multiplepossibilitiescells = new MULTIPLEPOSSIBILITIESCELLS();
		do
		{
			q = multiplepossibilitiescells.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = disjointsubsets.solve(q);
				if (q == null) return false; //to test sudoku
				do
				{
					q = singlesectorcandidates.solve(q);
					if (q == null) return false; //to test sudoku
					do
					{
						q = singlecellcandidates.solve(q);
						if (q == null) return false; //to test sudoku
						do
						{
							q = singlecandidature.solve(q);
							if (q == null) return false; //to test sudoku
						}while(singlecandidature.anyChange());
					
					}while(singlecellcandidates.anyChange());
				}while(singlesectorcandidates.anyChange());
			
			}while(disjointsubsets.anyChange());
		}while(multiplepossibilitiescells.anyChange());
		
		XWING xwing = new XWING();
		
		do
		{
			q = xwing.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = multiplepossibilitiescells.solve(q);
				if (q == null) return false; //to test sudoku
				do
				{
					q = disjointsubsets.solve(q);
					if (q == null) return false; //to test sudoku
					do
					{
						q = singlesectorcandidates.solve(q);
						if (q == null) return false; //to test sudoku
						do
						{
							q = singlecellcandidates.solve(q);
							if (q == null) return false; //to test sudoku
							do
							{
								q = singlecandidature.solve(q);
								if (q == null) return false; //to test sudoku
							}while(singlecandidature.anyChange());
						
						}while(singlecellcandidates.anyChange());
					}while(singlesectorcandidates.anyChange());
				
				}while(disjointsubsets.anyChange());
			}while(multiplepossibilitiescells.anyChange());
			
		}while(xwing.anyChange());
		
		SWORDFISH swordfish = new SWORDFISH();
		
		do
		{
			q = swordfish.solve(q);
			if (q == null) return false; //to test sudoku
			do
			{
				q = xwing.solve(q);
				if (q == null) return false; //to test sudoku
				do
				{
					q = multiplepossibilitiescells.solve(q);
					if (q == null) return false; //to test sudoku
					do
					{
						q = disjointsubsets.solve(q);
						if (q == null) return false; //to test sudoku
						do
						{
							q = singlesectorcandidates.solve(q);
							if (q == null) return false; //to test sudoku
							do
							{
								q = singlecellcandidates.solve(q);
								if (q == null) return false; //to test sudoku
								do
								{
									q = singlecandidature.solve(q);
									if (q == null) return false; //to test sudoku
								}while(singlecandidature.anyChange());
							
							}while(singlecellcandidates.anyChange());
						}while(singlesectorcandidates.anyChange());
					
					}while(disjointsubsets.anyChange());
				}while(multiplepossibilitiescells.anyChange());
				
			}while(xwing.anyChange());
		}while(swordfish.anyChange());
		return true;
	}
	
	public boolean anyChange()
	{
		return change;
	}
}