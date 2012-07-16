class MULTIPLEPOSSIBILITIES
{
	private int length;
	private int[] possibilities;
	private int numberOfEnteredValue = 0;
	
	public MULTIPLEPOSSIBILITIES(int length)
	{
		this.length = length;
		possibilities = new int[length];
	}
	
	public void makePossible(int value)
	{
		if (value < 1 || value > 9) return;
		
		if (numberOfEnteredValue < length)
		{
			possibilities[numberOfEnteredValue] = value;
			numberOfEnteredValue++;
		}
	}
	
	public boolean isPossible(int value)
	{
		for (int i = 0; i < numberOfEnteredValue; i++)
		{
			if (possibilities[i] == value) return true;
		}
		return false;
	}
	
	public boolean isTogetherlyPossible(CELL cell)
	{
		for (int i = 0; i < numberOfEnteredValue; i++)
		{
			if (!cell.isPossible(possibilities[i])) return false;
		}
		return true;
	}
	
	public boolean partiallyPossible(CELL cell)
	{
		boolean atLeastOneTrue = false;
		boolean atLeastOneFalse = false;
		
		for (int i = 0; i < numberOfEnteredValue; i++)
		{
			if (cell.isPossible(possibilities[i]))
			{
				if (!atLeastOneTrue) atLeastOneTrue = true;
				
				if (atLeastOneTrue && atLeastOneFalse) return true;
			}
			else
			{
				if (!atLeastOneFalse) atLeastOneFalse = true;
				
				if (atLeastOneTrue && atLeastOneFalse) return true;
			}
		}
		if (atLeastOneTrue && atLeastOneFalse) return true;
		return false;
	}
	
	public boolean isTogetherlyImpossible(CELL cell)
	{
		for (int i = 0; i < numberOfEnteredValue; i++)
		{
			if (cell.isPossible(possibilities[i])) return false;
		}
		return true;
	}
	
	public boolean isSame(MULTIPLEPOSSIBILITIES mp)
	{
		for (int value = 1; value <= 9; value++)
		{
			if (isPossible(value) != mp.isPossible(value)) return false;
		}
		return true;
	}
	
	private int getValueAtIndex(int index)
	{
		if (index >= 0 && index < numberOfEnteredValue)
		{
			return possibilities[index];
		}
		else return -1;
	}
	
	public void rotate()
	{
		MULTIPLEPOSSIBILITIES temp = new MULTIPLEPOSSIBILITIES(length);
		
		for (int i = 0; i <= numberOfEnteredValue; i++)
		{
			temp.makePossible(possibilities[i]);
		}
		
		for (int i = 0; i < numberOfEnteredValue; i++)
		{
			if (i == numberOfEnteredValue - 1)
			{
				possibilities[i] = temp.getValueAtIndex(0);
			}
			else
			{
				possibilities[i] = temp.getValueAtIndex(i+1);
			}
		}
		temp = null;
	}
	
	public boolean contains(MULTIPLEPOSSIBILITIES mp)
	{
		for (int value = 1; value <= 9; value++)
		{
			if (mp.isPossible(value) && !isPossible(value)) return false;
		}
		return true;
	}
	
	public int getLength()
	{
		return length;
	}
}