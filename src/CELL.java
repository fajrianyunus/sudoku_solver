class CELL
{
	private Boolean[] possible = new Boolean[9];
	
	public CELL()
	{
		for (int i = 0; i < 9; i++)
		{
			possible[i] = new Boolean(true);
		}
	}
	
	public CELL(int value)
	{
		if (!valid(value)) return;
		for (int i = 0; i < 9; i++)
		{
			if (i == value -1) possible[i] = new Boolean(true);
			else possible[i] = new Boolean(false);
		}
	}
	
	private boolean valid(int value)
	{
		if (value >= 1 && value <= 9) return true;
		else return false;
	}
	
	public int getValue()
	{
		int value = -2;
		
		for (int i = 0; i < 9; i++)
		{
			if (value == -2 && possible[i]) value = i + 1;
			else if (valid(value) && possible[i]) value = -1;
		}
		
		if (value == -1 || value == -2) return -1;
		else return value;
	}
	
	public boolean remove(int value)
	{
		if (!valid(value)) return false;
		else
		{
			if (!possible[value-1]) return false;
			else
			{
				possible[value-1] = false;
				return true;
			}
		}
	}
	
	public boolean isPossible(int value)
	{
		if (!valid(value)) return false;
		else
		{
			if (possible[value-1]) return true;
			else return false;
		}
	}
	
	public boolean isSame(CELL newCell)
	{
		for (int i = 1; i <= 9; i++)
		{
			if (isPossible(i) != newCell.isPossible(i)) return false; 
		}
		return true;
	}
	
	public int numberOfPossibility()
	{
		int returnValue = 0;
		
		for (int i = 0; i < 9; i++)
		{
			if (possible[i]) returnValue++;
		}
		
		return returnValue;
	}
	
	public boolean determineValue(int value)
	{
		if (!valid(value)) return false;
		if (!isPossible(value)) return false;
		
		boolean returnValue = false;
		
		for (int testValue = 1; testValue <= 9; testValue++)
		{
			if (testValue != value)
			{
				if (!returnValue) returnValue = remove(testValue);
				else remove(testValue);
			}
		}
		return returnValue;
	}
	
	public boolean containsAtLeastOneElement(MULTIPLEPOSSIBILITIES multiplepossibilities)
	{
		for (int value = 1; value <= 9; value++)
		{
			if (isPossible(value) && multiplepossibilities.isPossible(value)) return true;
		}
		return false;
	}
}