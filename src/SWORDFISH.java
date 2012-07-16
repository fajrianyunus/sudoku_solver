import java.io.FileNotFoundException;
import java.io.IOException;

class SWORDFISH
{
	private SUDOKU sudoku;
	private boolean change;
	
	private SWORDFISHQUEUE sfq = new SWORDFISHQUEUE();
	
	//...
	private boolean executePreviousStrategy = false;
	public void setExecutePreviousStrategy() {executePreviousStrategy = true;}
	public void resetExecutePreviousStrategy() {executePreviousStrategy = false;}
	//...
	
	public QUEUE solve(QUEUE q) throws IOException
	{
		change = false;
		sudoku = q.getLast().getSudoku();
		
		for (int value = 1; value <= 9; value++)
		{
			for (int row = 1; row <= 9; row++)
			{
				for (int column = 1; column <= 9; column++)
				{
					sfq.clear();
					if (traverse(value, row, column, false))
					{
						q = handleSFQ(value, q);
						//for guessing
						if (q == null) return null;
						//end of for guessing
					}
				}
			}
		}
		
		return q;
	}
	
	private QUEUE handleSFQ(int value, QUEUE q) throws IOException
	{
		int i[] = new int[4];
		int j[] = new int[4];
		
		for (int counter = 0; counter < 4; counter++)
		{
			i[counter] = sfq.getCellatIntersection(counter+1).getRow();
			j[counter] = sfq.getCellatIntersection(counter+1).getColumn();
		}
		
		String temp = "We can form a string of possibilities of "+value+" so that the string is ";
		
		SWORDFISHQUEUENODE node;
		do
		{
			node = sfq.dequeue();
			temp = temp+"cell["+node.getRow()+"]["+node.getColumn()+"] , ";
		}while(node.getRow() != i[1] || node.getColumn() != j[1]);
		temp = temp+" So that either but not both of cell["+i[0]+"]["+j[0]+"] or cell["+i[1]+"]["+j[1]+"] has value of "+value+". ";
		
		temp = temp+" Beside that, we can see another string: ";
		do
		{
			node = sfq.dequeue();
			temp = temp+"cell["+node.getRow()+"]["+node.getColumn()+"] , ";
		}while(node.getRow() != i[3] || node.getColumn() != j[3]);
		temp = temp+" So that either but not both of cell["+i[2]+"]["+j[2]+"] or cell["+i[3]+"]["+j[3]+"] has value of "+value+". ";
		
		if (sudoku.isInSameRow(i[1], j[1], i[2], j[2]))
		{
			if (sudoku.removeValueFromRowExceptColumns(value, i[1], j[1], j[2]))
			{
				String temp2 = temp+" Whatever the final result, we can remove any other possibilities from row "+i[1];
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp2);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		if (sudoku.isInSameColumn(i[1], j[1], i[2], j[2]))
		{
			if (sudoku.removeValueFromColumnExceptRows(value, i[1], i[2], j[1]))
			{
				String temp3 = temp+" Whatever the final result, we can remove any other possibilities from column "+j[1];
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp3);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		if (sudoku.isInSameArea(i[1], j[1], i[2], j[2]))
		{
			if (sudoku.removeValueFromAreaExceptCells(value, i[1], j[1], i[2], j[2]))
			{
				String temp4 = temp+" Whatever the final result, we can remove any other possibilities from area "+((i[1]-1)/3*3+(j[1]-1)%3+1);
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp4);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		//...........................................................................//
		if (sudoku.isInSameRow(i[0], j[0], i[3], j[3]))
		{
			if (sudoku.removeValueFromRowExceptColumns(value, i[0], j[0], j[3]))
			{
				String temp5 = temp+" Whatever the final result, we can remove any other possibilities from row "+i[0];
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp5);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		if (sudoku.isInSameColumn(i[0], j[0], i[3], j[3]))
		{
			if (sudoku.removeValueFromColumnExceptRows(value, i[0], i[3], j[0]))
			{
				String temp6 = temp+" Whatever the final result, we can remove any other possibilities from column "+j[0];
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp6);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		if (sudoku.isInSameArea(i[0], j[0], i[3], j[3]))
		{
			if (sudoku.removeValueFromAreaExceptCells(value, i[0], j[0], i[3], j[3]))
			{
				String temp7 = temp+" Whatever the final result, we can remove any other possibilities from area "+((i[0]-1)/3*3+(j[0]-1)/3+1);
				QUEUENODE qn = new QUEUENODE();
				qn.setText(temp7);
				qn.setSudoku(new SUDOKU(sudoku));
				q.enqueue(qn);
				//...
				if (executePreviousStrategy)
				{
					SUBSOLVER s = new SUBSOLVER();
					q = s.solve(q, 6);
					if (q == null) return null;
					sudoku = q.getLast().getSudoku();
				}
				//...
				//for guessing
				if (!sudoku.isValidSudoku()) return null;
				//end of for guessing
			}
		}
		return q;
	}
	
	public boolean anyChange()
	{
		return change;
	}
	
	private boolean traverse(int value, int row, int column, boolean oddLength)
	{
		if (!sudoku.isPossible(value, row, column)) return false;
		if (sfq.isListed(row, column)) return false;
		if (!sfq.suitableToBeEntered(sudoku, value, row, column)) return false;
		//........................................................
		SWORDFISHQUEUENODE sfqn = new SWORDFISHQUEUENODE(row, column);
		if (sfq.isEmpty()) sfqn.setIntersection();
		if (sfq.getNumberOfIntersection() == 2 && sfq.getLast().getIntersection()) sfqn.setIntersection();
		if (sfq.getNumberOfIntersection() == 3 && oddLength)
		{
			if (sudoku.contradictsEachOther(value, row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
			{
				sfqn.setIntersection();
				sfq.enqueue(sfqn);
				return true;
			}
		}
		//........................................................
		sfq.enqueue(sfqn);
		//........................................................
		
		if (sudoku.hasOnly2CellsPossibleInSector(value, row, column))
		{
			if (sudoku.hasOnly2CellsPossibleInRow(value, row))
			{
				if (traverse(value, row, sudoku.columnInRowWhichIsPossibleOfValueExcept(value, row, column), !oddLength))
				{
					return true;
				}
			}
			
			if (sudoku.hasOnly2CellsPossibleInColumn(value, column))
			{
				if (traverse(value, sudoku.rowInColumnWhichIsPossibleOfValueExcept(value, row, column), column, !oddLength))
				{
					return true;
				}
			}
			
			if (sudoku.hasOnly2CellsPossibleInArea(value, row, column))
			{
				if (traverse(value, sudoku.cellInAreaWhichIsPossibleOfValueExcept(value, row, column).getRow(), sudoku.cellInAreaWhichIsPossibleOfValueExcept(value, row, column).getColumn(), !oddLength))
				{
					return true;
				}
			}
		}
		
		//...............................................
		if (oddLength && sfq.getNumberOfIntersection() == 1)
		{
			if ((!sfq.getLast().getIntersection() || ((sfq.getNumberOfIntersection() != 1) && sfq.getNumberOfIntersection() != 3)))
			{
				int numberOfPossibility = 0;
				sfq.removeTheLast();
				SWORDFISHQUEUENODE node = new SWORDFISHQUEUENODE(row, column);
				node.setIntersection();
				sfq.enqueue(node);
				for (int i = 1; i <= 9; i++)
				{
					if (sudoku.isPossible(value, i, column)) numberOfPossibility++;
					//if (sfq.isListed(i, column) && i != row) {numberOfPossibility = 0; break;}
				}
				if (numberOfPossibility > 2)
				{
					for (int i = 1; i <= 9; i++)
					{
						if (traverse(value, i, column, false)) return true;
					}
				}
				numberOfPossibility = 0;
				for (int j = 1; j <= 9; j++)
				{
					if (sudoku.isPossible(value, row, j)) numberOfPossibility++;
					//if (sfq.isListed(row, j) && j != column)  {numberOfPossibility = 0; break;}
				}
				if (numberOfPossibility > 2)
				{
					for (int j = 1; j <= 9; j++)
					{
						if (traverse(value, row, j, false)) return true;
					}
				}
				numberOfPossibility = 0;
				for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
				{
					for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
					{
						if (sudoku.isPossible(value, i, j)) numberOfPossibility++;
						//if (sfq.isListed(i, j) && (i != row || j != column)) {numberOfPossibility = 0; break;}
					}
				}
				if (numberOfPossibility > 2)
				{
					for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
					{
						for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
						{
							if (traverse(value, i, j, false)) return true;
						}
					}
				}
				numberOfPossibility = 0;
			}
		}
		sfq.removeTheLast();
		return false;
	}
	
	/*private boolean traverse(int value, int row, int column, boolean pairOnly, boolean evenLength)
	{
		if (!sudoku.isPossible(value, row, column)) return false;
		if (!sfq.suitableToBeEntered(sudoku, value, row, column)) return false;
		if (sfq.isListed(row, column)) return false;
		
		SWORDFISHQUEUENODE sfqn = new SWORDFISHQUEUENODE(row, column);
		if (!pairOnly) sfqn.setIntersection();
		if (sfq.isEmpty()) sfqn.setIntersection();
		if (sfq.getNumberOfIntersection() == 3)
		{
			if (sudoku.contradictsEachOther(value, row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
			{
				sfqn.setIntersection();
			}
		}
		
		if (sfq.getNumberOfIntersection() == 2)
		{
			sfqn.setIntersection();
		}
		
		sfq.enqueue(sfqn);
		
		if (sfq.isFull())
		{
			if (sudoku.contradictsEachOther(value, row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
			{
				if (sudoku.isInSameRow(row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
				{
					int numberOfPossibility = 0;
					
					for (int j = 1; j <= 9; j++)
					{
						if (j != column && j != sfq.getCellatIntersection(1).getColumn())
						{
							if (sfq.isListed(row, j))
							{
								numberOfPossibility = 0;
								break;
							}
							if (sudoku.isPossible(value, row, j)) numberOfPossibility++;
						}
					}
					if (numberOfPossibility > 0) return true;
				}
				if (sudoku.isInSameColumn(row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
				{
					int numberOfPossibility = 0;
					
					for (int i = 1; i <= 9; i++)
					{
						if (i != row && i != sfq.getCellatIntersection(1).getRow())
						{
							if (sfq.isListed(i, column))
							{
								numberOfPossibility = 0;
								break;
							}
							if (sudoku.isPossible(value, i, column)) numberOfPossibility++;
						}
					}
					if (numberOfPossibility > 0) return true;
				}
				if (sudoku.isInSameArea(row, column, sfq.getCellatIntersection(1).getRow(), sfq.getCellatIntersection(1).getColumn()))
				{
					int numberOfPossibility = 0;
					boolean doubleBreak = false;
					
					for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
					{
						for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
						{
							if ((i != row || j != column) && (i != sfq.getCellatIntersection(1).getRow() || j != sfq.getCellatIntersection(1).getColumn()))
							{
								if (sfq.isListed(i, j))
								{
									numberOfPossibility = 0;
									doubleBreak = true;
									break;
								}
								if (sudoku.isPossible(value, i, j)) numberOfPossibility++;
							}
						}
						if (doubleBreak)
						{
							doubleBreak = false;
							break;
						}
					}
					if (numberOfPossibility > 0) return true;
				}
			}
			sfq.removeTheLast();
			return false;
		}
		
		if (pairOnly)
		{
			boolean linkThePair = false;
			if (sudoku.hasOnly2CellsPossibleInSector(value, row, column))
			{
				if (sudoku.hasOnly2CellsPossibleInRow(value, row))
				{
					if (traverse(value, row, sudoku.columnInRowWhichIsPossibleOfValueExcept(value, row, column), true, !evenLength))
					{
						linkThePair = true;
						return true;
					}
				}
				if (sudoku.hasOnly2CellsPossibleInColumn(value, column))
				{
					if (traverse(value, sudoku.rowInColumnWhichIsPossibleOfValueExcept(value, row, column), column, true, !evenLength))
					{
						linkThePair = true;
						return true;
					}
				}
				if (sudoku.hasOnly2CellsPossibleInArea(value, row, column))
				{
					if (traverse(value, sudoku.cellInAreaWhichIsPossibleOfValueExcept(value, row, column).getRow(), sudoku.cellInAreaWhichIsPossibleOfValueExcept(value, row, column).getColumn(), true, !evenLength))
					{
						linkThePair = true;
						return true;
					}
				}
			}
			if (!sudoku.hasOnly2CellsPossibleInSector(value, row, column) || !linkThePair)
			{
				if (!evenLength)
				{
					//examine here
					if (sfq.getDistanceFromLastIntersection() <= 1 && (sfq.getNumberOfIntersection() == 1 || sfq.getNumberOfIntersection() == 3))
					{
						sfq.removeTheLast();
						return false;
					}
					
					boolean formed = false;
					int numberOfPossibility = 0;
					for (int i = 1; i <= 9; i++)
					{
						if (i != row)
						{
							if (sfq.isListed(i, column)) {numberOfPossibility = 0; break;}
							if (sudoku.isPossible(value, i, column)) numberOfPossibility++;
						}
					}
					if (numberOfPossibility >= 1)
					{
						for (int i = 1; i <= 9; i++)
						{
							if (i != row && sudoku.isPossible(value, i, column))
							{
								sfq.removeTheLast();
								SWORDFISHQUEUENODE tempNode = new SWORDFISHQUEUENODE(row, column);
								tempNode.setIntersection();
								sfq.enqueue(tempNode);
								
								if (traverse(value, i, column, true, false))
								{
									formed = true;
									return true;
								}
								else
								{
									sfq.removeTheLast();
									SWORDFISHQUEUENODE tempNode2 = new SWORDFISHQUEUENODE(row, column);
									sfq.enqueue(tempNode2);
								}
							}
						}
					}
					//............................................
					numberOfPossibility = 0;
					for (int j = 1; j <= 9; j++)
					{
						if (column != j)
						{
							if (sfq.isListed(row, j)) {numberOfPossibility = 0; break;}
							if (sudoku.isPossible(value, row, j)) numberOfPossibility++;
						}
					}
					if (numberOfPossibility >= 1)
					{
						for (int j = 1; j <= 9; j++)
						{
							if (column != j && sudoku.isPossible(value, row, j))
							{
								sfq.removeTheLast();
								SWORDFISHQUEUENODE tempNode = new SWORDFISHQUEUENODE(row, column);
								tempNode.setIntersection();
								sfq.enqueue(tempNode);
								
								if (traverse(value, row, j, true, false))
								{
									formed = true;
									return true;
								}
								else
								{
									sfq.removeTheLast();
									SWORDFISHQUEUENODE tempNode2 = new SWORDFISHQUEUENODE(row, column);
									sfq.enqueue(tempNode2);
								}
							}
						}
					}
					//..........................................................
					numberOfPossibility = 0;
					for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
					{
						for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
						{
							if (sfq.isListed(row, column)) {numberOfPossibility = 0; break;}
							if (sudoku.isPossible(value, i, j)) numberOfPossibility++;
						}
					}
					if (numberOfPossibility >= 1)
					{
						for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
						{
							for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
							{
								if (((i != row) || (j != column)) && (sudoku.isPossible(value, i, j)))
								{
									sfq.removeTheLast();
									SWORDFISHQUEUENODE tempNode = new SWORDFISHQUEUENODE(row, column);
									tempNode.setIntersection();
									sfq.enqueue(tempNode);
									
									if (traverse(value, i, j, true, false))
									{
										formed = true;
										return true;
									}
									else
									{
										sfq.removeTheLast();
										SWORDFISHQUEUENODE tempNode2 = new SWORDFISHQUEUENODE(row, column);
										sfq.enqueue(tempNode2);
									}
								}
							}
						}
					}
					numberOfPossibility = 0;
					if (!formed)
					{
						sfq.removeTheLast();
						return false;
					}
				}
				else
				{
					sfq.removeTheLast();
					return false;
				}
			}
		}
		else
		{	
			boolean formed = false;
			SWORDFISHQUEUENODE tempNode = new SWORDFISHQUEUENODE(row, column);
			tempNode.setIntersection();
			sfq.removeTheLast();
			sfq.enqueue(tempNode);
			
			
			for (int i = 1; i <= 9; i++)
			{
				if (traverse(value, i, column, true, false))
				{
					formed = true;
					return true;
				}
			}
			for (int j = 1; j <= 9; j++)
			{
				if (traverse(value, row, j, true, false))
				{
					formed = true;
					return true;
				}
			}
			for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
			{
				for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
				{
					if (traverse(value, i, j, true, false))
					{
						formed = true;
						return true;
					}
				}
			}
			if (!formed)
			{
				sfq.removeTheLast();
				return false;
			}
		}
		System.out.println("traversenya ngaco");
		return false;//WILL NOT BE EXECUTED
	}*/
}