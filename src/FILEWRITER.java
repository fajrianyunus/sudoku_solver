import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class FILEWRITER
{
	PrintWriter printFile;
	public FILEWRITER(FileWriter f) throws IOException
	{
		printFile = new PrintWriter(new BufferedWriter(f));
	}
	
	public void write(QUEUE q) throws FileNotFoundException
	{
		boolean first = true;
		SUDOKU sudoku;
		while (!q.isEmpty())
		{
			QUEUENODE tempNode = q.dequeue();
			sudoku = tempNode.getSudoku();
			
			if (first)
			{
				printFile.println(tempNode.getText());
				printFile.println("");
				first = false;
			}
			else
			{
				printFile.println("");
				printFile.println(tempNode.getText());
				printFile.println("");
			}
			
			for (int i = 1; i <= 9; i++)
			{
				for (int j = 1; j <= 9; j++)
				{
					for (int value = 1; value <= 9; value++)
					{
						if (sudoku.getCell(i, j).isPossible(value))
						{
							printFile.print(value);
						}
						else
						{
							printFile.print(".");
						}
					}
					if (j != 9)
					{
						if (j % 3 == 0)
						{
							printFile.print(" || ");
						}
						else
						{
							printFile.print(" | ");
						}
					}
				}
				printFile.println("");
				if (i != 9)
				{
					for (int count = 1; count <= 107; count++)
					{
						printFile.print("-");
					}
					printFile.println("");
					if (i % 3 == 0)
					{
						for (int count = 1; count <= 107; count++)
						{
							printFile.print("-");
						}
						printFile.println("");
					}
				}
			}
		}
	}
	
	public void close()
	{
		printFile.close();
	}
}