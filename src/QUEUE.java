import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class QUEUE
{
	private QUEUENODE head;
	private QUEUENODE tail;
	private int highest = 0;
	private int lowest = 1;
	
	public void enqueue(QUEUENODE node) throws IOException
	{
		if (head == null && tail == null)
		{
			head = node;
			tail = head;
		}
		else
		{
			tail.setNext(node);
			tail = tail.getNext();
		}
		/*highest++;
		
		PrintWriter printFile = new PrintWriter(new BufferedWriter(new FileWriter("temp/sudoku/"+String.valueOf(highest))));
		
		for (int i = 1; i <= 9; i++)
		{
			for (int j = 1; j <= 9; j++)
			{
				printFile.print(i+" "+j+" ");
				for (int value = 1; value <= 9; value++)
				{
					if (!node.getSudoku().isPossible(value, i, j)) printFile.print(value+" ");
				}
				
				printFile.println("");
			}
		}
		printFile.close();
		
		PrintWriter printFile2 = new PrintWriter(new BufferedWriter(new FileWriter("temp/text/"+String.valueOf(highest))));
		printFile2.println(node.getText());
		printFile2.close();*/
	}
	
	public QUEUENODE dequeue() throws FileNotFoundException
	{
		if (head == null && tail == null)
		{
			return null;
		}
		else if (head == tail)
		{
			QUEUENODE temp = head;
			head = null;
			tail = null;
			return temp;
		}
		else
		{
			QUEUENODE temp = head;
			head = head.getNext();
			return temp;
		}
		/*if (isEmpty()) return null;
		else
		{
			File sdk = new File("temp/sudoku/"+String.valueOf(lowest));
			Scanner readSudoku = new Scanner(sdk);
			CELL[][] cell = new CELL[9][9];
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					cell[i][j] = new CELL();
					String temp;
					
					temp = readSudoku.nextLine();
					StringTokenizer st = new StringTokenizer(temp);
					
					if (i == Integer.parseInt(st.nextToken()) - 1)
					{
						if (j == Integer.parseInt(st.nextToken()) - 1)
						{
							while(st.hasMoreTokens())
							{
								cell[i][j].remove(Integer.parseInt(st.nextToken()));
							}
						}
					}
				}
			}
			readSudoku.close();
			SUDOKU sudoku = new SUDOKU(cell);
			
			File txt = new File("temp/text/"+String.valueOf(lowest));
			
			Scanner readText = new Scanner(txt);
			String text = readText.nextLine();
			readText.close();
			
			QUEUENODE node = new QUEUENODE();
			node.setSudoku(sudoku);
			node.setText(text);
			lowest++;
			
			sdk.delete();
			txt.delete();
			return node;
		}*/
	}
	
	public QUEUENODE getLast() throws FileNotFoundException
	{
		return (new QUEUENODE(tail));
		
		/*if (isEmpty()) return null;
		else
		{
			File sdk = new File("temp/sudoku/"+String.valueOf(highest));
			Scanner readSudoku = new Scanner(sdk);
			CELL[][] cell = new CELL[9][9];
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					cell[i][j] = new CELL();
					String temp;
					
					temp = readSudoku.nextLine();
					StringTokenizer st = new StringTokenizer(temp);
					
					if (i == Integer.parseInt(st.nextToken()) - 1)
					{
						if (j == Integer.parseInt(st.nextToken()) - 1)
						{
							while(st.hasMoreTokens())
							{
								cell[i][j].remove(Integer.parseInt(st.nextToken()));
							}
						}
					}
				}
			}
			readSudoku.close();
			SUDOKU sudoku = new SUDOKU(cell);
			
			File txt = new File("temp/text/"+String.valueOf(highest));
			
			Scanner readText = new Scanner(txt);
			String text = readText.nextLine();
			readText.close();
			
			QUEUENODE node = new QUEUENODE();
			node.setSudoku(sudoku);
			node.setText(text);
			
			return node;
		}*/
	}
	
	public boolean isEmpty()
	{
		if (head == null && tail == null) return true;
		else return false;
		
		/*if (lowest > highest) return true;
		else return false;*/
	}
}