import java.io.IOException;

class SOLVER
{
	private SUDOKU sudoku;
	
	public SOLVER(SUDOKU sudoku)
	{
		if (sudoku == null) return;
		else this.sudoku = sudoku;
	}
	
	public QUEUE solve() throws IOException
	{
		QUEUE q = new QUEUE();
		QUEUENODE first = new QUEUENODE();
		first.setSudoku(new SUDOKU(this.sudoku));
		first.setText("initial sudoku");
		q.enqueue(first);
		
		SINGLECANDIDATURE singlecandidature = new SINGLECANDIDATURE();
		singlecandidature.setExecutePreviousStrategy();
		do
		{
			q = singlecandidature.solve(q);
		}while(singlecandidature.anyChange());
		
		SINGLECELLCANDIDATES singlecellcandidates = new SINGLECELLCANDIDATES();
		singlecellcandidates.setExecutePreviousStrategy();
		do
		{
			q = singlecellcandidates.solve(q);
			
			do
			{
				q = singlecandidature.solve(q);
			}while(singlecandidature.anyChange());
		
		}while(singlecellcandidates.anyChange());
		
		SINGLESECTORCANDIDATES singlesectorcandidates = new SINGLESECTORCANDIDATES();
		singlesectorcandidates.setExecutePreviousStrategy();
		do
		{
			q = singlesectorcandidates.solve(q);
			do
			{
				q = singlecellcandidates.solve(q);
				
				do
				{
					q = singlecandidature.solve(q);
				}while(singlecandidature.anyChange());
			
			}while(singlecellcandidates.anyChange());
		}while(singlesectorcandidates.anyChange());
		
		DISJOINTSUBSETS disjointsubsets = new DISJOINTSUBSETS();
		disjointsubsets.setExecutePreviousStrategy();
		do
		{
			q = disjointsubsets.solve(q);
			
			do
			{
				q = singlesectorcandidates.solve(q);
				do
				{
					q = singlecellcandidates.solve(q);
					
					do
					{
						q = singlecandidature.solve(q);
					}while(singlecandidature.anyChange());
				
				}while(singlecellcandidates.anyChange());
			}while(singlesectorcandidates.anyChange());
		
		}while(disjointsubsets.anyChange());
		
		MULTIPLEPOSSIBILITIESCELLS multiplepossibilitiescells = new MULTIPLEPOSSIBILITIESCELLS();
		multiplepossibilitiescells.setExecutePreviousStrategy();
		do
		{
			q = multiplepossibilitiescells.solve(q);
			do
			{
				q = disjointsubsets.solve(q);
				
				do
				{
					q = singlesectorcandidates.solve(q);
					do
					{
						q = singlecellcandidates.solve(q);
						
						do
						{
							q = singlecandidature.solve(q);
						}while(singlecandidature.anyChange());
					
					}while(singlecellcandidates.anyChange());
				}while(singlesectorcandidates.anyChange());
			
			}while(disjointsubsets.anyChange());
		}while(multiplepossibilitiescells.anyChange());
		
		XWING xwing = new XWING();
		xwing.setExecutePreviousStrategy();
		do
		{
			q = xwing.solve(q);
			
			do
			{
				q = multiplepossibilitiescells.solve(q);
				do
				{
					q = disjointsubsets.solve(q);
					
					do
					{
						q = singlesectorcandidates.solve(q);
						do
						{
							q = singlecellcandidates.solve(q);
							
							do
							{
								q = singlecandidature.solve(q);
							}while(singlecandidature.anyChange());
						
						}while(singlecellcandidates.anyChange());
					}while(singlesectorcandidates.anyChange());
				
				}while(disjointsubsets.anyChange());
			}while(multiplepossibilitiescells.anyChange());
			
		}while(xwing.anyChange());
		
		SWORDFISH swordfish = new SWORDFISH();
		swordfish.setExecutePreviousStrategy();
		do
		{
			q = swordfish.solve(q);
			do
			{
				q = xwing.solve(q);
				
				do
				{
					q = multiplepossibilitiescells.solve(q);
					do
					{
						q = disjointsubsets.solve(q);
						
						do
						{
							q = singlesectorcandidates.solve(q);
							do
							{
								q = singlecellcandidates.solve(q);
								
								do
								{
									q = singlecandidature.solve(q);
								}while(singlecandidature.anyChange());
							
							}while(singlecellcandidates.anyChange());
						}while(singlesectorcandidates.anyChange());
					
					}while(disjointsubsets.anyChange());
				}while(multiplepossibilitiescells.anyChange());
				
			}while(xwing.anyChange());
		}while(swordfish.anyChange());
		
		GUESS guess = new GUESS();
		guess.setExecutePreviousStrategy();
		do
		{
			q = guess.solve(q);
			do
			{
				q = swordfish.solve(q);
				do
				{
					q = xwing.solve(q);
					
					do
					{
						q = multiplepossibilitiescells.solve(q);
						do
						{
							q = disjointsubsets.solve(q);
							
							do
							{
								q = singlesectorcandidates.solve(q);
								do
								{
									q = singlecellcandidates.solve(q);
									
									do
									{
										q = singlecandidature.solve(q);
									}while(singlecandidature.anyChange());
								
								}while(singlecellcandidates.anyChange());
							}while(singlesectorcandidates.anyChange());
						
						}while(disjointsubsets.anyChange());
					}while(multiplepossibilitiescells.anyChange());
					
				}while(xwing.anyChange());
			}while(swordfish.anyChange());
		}while(guess.anyChange());
		
		return q;
	}
}