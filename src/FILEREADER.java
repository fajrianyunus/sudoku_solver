import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FILEREADER
{
	CELL[][] cell = new CELL[9][9];
	
	public FILEREADER(File f)
	{
		try
		{
			Scanner input = new Scanner(f);
			int temp;
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					temp = input.nextInt();
					if (temp >= 1 && temp <= 9)
					{
						cell[i][j] = new CELL(temp);
					}
					else
					{
						cell[i][j] = new CELL();
					}
				}
			}
		}
		catch(FileNotFoundException ex)
		{
			System.err.println("file is not found");
		}
	}
	
	public SUDOKU getSudoku()
	{
		SUDOKU newSudoku = new SUDOKU(cell);
		return newSudoku;
	}
}