class QUEUENODE
{
	private QUEUENODE next;
	private SUDOKU sudoku;
	private String text;
	
	public QUEUENODE()
	{
		
	}
	
	public QUEUENODE(QUEUENODE otherNode)
	{
		next = otherNode.getNext();
		sudoku = new SUDOKU(otherNode.getSudoku());
		text = new String(otherNode.getText());
	}
	
	public QUEUENODE getNext()
	{
		return next;
	}
	
	public void setNext(QUEUENODE next)
	{
		this.next = next;
	}
	
	public void setSudoku(SUDOKU sudoku)
	{
		this.sudoku = sudoku;
	}
	
	public SUDOKU getSudoku()
	{
		return sudoku;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
}