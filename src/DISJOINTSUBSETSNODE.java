class DISJOINTSUBSETSNODE
{
	private MULTIPLEPOSSIBILITIES mp;
	private DISJOINTSUBSETSNODE next;
	
	public DISJOINTSUBSETSNODE(MULTIPLEPOSSIBILITIES mp)
	{
		this.mp = mp;
	}
	
	public void setNext(DISJOINTSUBSETSNODE next)
	{
		this.next = next;
	}
	
	public DISJOINTSUBSETSNODE getNext()
	{
		return next;
	}
	
	public MULTIPLEPOSSIBILITIES getMP()
	{
		return mp;
	}
}