class SWORDFISHQUEUE
{
	private SWORDFISHQUEUENODE head;
	private SWORDFISHQUEUENODE tail;
	private int numberOfIntersection = 0;
	
	public void enqueue(SWORDFISHQUEUENODE node)
	{
		if (isEmpty())
		{
			head = node;
			tail = head;
		}
		else
		{
			tail.setNext(node);
			tail = tail.getNext();
		}
		
		if (node.getIntersection()) numberOfIntersection++;
	}
	
	public SWORDFISHQUEUENODE dequeue()
	{
		if (isEmpty()) return null;
		else if (head == tail)
		{
			SWORDFISHQUEUENODE temp = head;
			head = null;
			tail = null;
			if (temp.getIntersection()) numberOfIntersection--;
			return temp;
		}
		else
		{
			SWORDFISHQUEUENODE temp = head;
			head = head.getNext();
			if (temp.getIntersection()) numberOfIntersection--;
			return temp;
		}
	}
	
	public void removeTheLast()
	{
		if (isEmpty()) return;
		else if (head == tail)
		{
			numberOfIntersection = 0;
			head = null;
			tail = null;
		}
		else
		{
			if (tail.getIntersection()) numberOfIntersection--;
			SWORDFISHQUEUENODE temp = head;
			
			while(temp.getNext() != tail)
			{
				temp = temp.getNext();
			}
			tail = temp;
			tail.setNext(null);
		}
	}
	
	public boolean isEmpty()
	{
		if (head == null && tail == null) return true;
		else return false;
	}
	
	public boolean isFull()
	{
		if (numberOfIntersection == 4) return true;
		else return false;
	}
	
	public boolean isListed(int row, int column)
	{
		if (isEmpty()) return false;
		
		SWORDFISHQUEUENODE temp = head;
		
		while(temp != null)
		{
			if (temp.getRow() == row && temp.getColumn() == column) return true;
			temp = temp.getNext();
		}
		return false;
	}
	
	public void clear()
	{
		head = null;
		tail = null;
		numberOfIntersection = 0;
	}
	
	public boolean suitableToBeEntered(SUDOKU sudoku, int value, int row, int column)
	{
		if (isListed(row, column)) return false;
		if (numberOfIntersection == 4) return false;
		if (numberOfIntersection != 2 && numberOfIntersection != 3) return true;
		else if (numberOfIntersection == 2)
		{	
			SWORDFISHQUEUENODE temp = head;
			
			for (int counter = 0; counter <= 2;)
			{
				if (temp.getIntersection()) counter++;
				
				if (counter == 2) break;
				else
				{
					temp = temp.getNext();
				}
			}
			
			if (sudoku.isInSameRow(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int j = 1; j <= 9; j++)
				{
					if (sudoku.isPossible(value, row, j)) numberOfPossibility++;
				}
				if (numberOfPossibility > 2) return true;
			}
			else if (sudoku.isInSameColumn(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int i = 1; i <= 9; i++)
				{
					if (sudoku.isPossible(value, i, column)) numberOfPossibility++;
				}
				if (numberOfPossibility > 2) return true;
			}
			else if (sudoku.isInSameArea(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
				{
					for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
					{
						if (sudoku.isPossible(value, i, j)) numberOfPossibility++;
					}
				}
				if (numberOfPossibility > 2) return true;
			}
			return false;
		}
		else if (numberOfIntersection == 3)
		{
			SWORDFISHQUEUENODE temp = head;
			
			if (sudoku.isInSameRow(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int j = 1; j <= 9; j++)
				{
					if (sudoku.isPossible(value, row, j)) numberOfPossibility++;
				}
				if (numberOfPossibility > 2) return true;
			}
			else if (sudoku.isInSameColumn(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int i = 1; i <= 9; i++)
				{
					if (sudoku.isPossible(value, i, column)) numberOfPossibility++;
				}
				if (numberOfPossibility > 2) return true;
			}
			else if (sudoku.isInSameArea(row, column, temp.getRow(), temp.getColumn()))
			{
				int numberOfPossibility = 0;
				for (int i = (row-1)/3*3+1; i <= (row-1)/3*3+3; i++)
				{
					for (int j = (column-1)/3*3+1; j <= (column-1)/3*3+3; j++)
					{
						if (sudoku.isPossible(value, i, j)) numberOfPossibility++;
					}
				}
				if (numberOfPossibility > 2) return true;
			}
			return false;
		}
		return false;
	}
	
	public CELLADDRESSING getCellatIntersection(int index)
	{
		if (index < 1 || index > 4) return null;
		
		SWORDFISHQUEUENODE temp = head;
		
		while(temp != null)
		{
			if (temp.getIntersection()) index--;
			if (index == 0) return (new CELLADDRESSING(temp.getRow(), temp.getColumn()));
			temp = temp.getNext();
		}
		
		return null;
	}
	
	public int getDistanceFromLastIntersection()
	{
		SWORDFISHQUEUENODE temp = head;
		
		for (int counter = 0; counter <= numberOfIntersection;)
		{
			if (temp.getIntersection()) counter++;
			
			if (counter >= numberOfIntersection) break;
			else temp = temp.getNext();
		}
		
		int counter = 0;
		do
		{
			temp = temp.getNext();
			counter++;
		}while(temp != null);
		return counter;
	}
	
	public int getNumberOfIntersection()
	{
		return numberOfIntersection;
	}
	
	public SWORDFISHQUEUENODE getLast()
	{
		return tail;
	}
}