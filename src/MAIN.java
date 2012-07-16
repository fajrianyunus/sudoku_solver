import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MAIN
{
	public static void main(String[] args) throws IOException
	{
		FILEREADER fr = new FILEREADER(new File("input/test.txt"));
		PSEUDOKU ps = new PSEUDOKU(fr.getSudoku());
		if (!ps.valid())
		{
			System.out.println("Sudoku is invalid");
			System.exit(1);
		}
		SOLVER solver = new SOLVER(fr.getSudoku());
		FILEWRITER fw = new FILEWRITER(new FileWriter("output/test2.txt"));
		fw.write(solver.solve());
		fw.close();
	}
}