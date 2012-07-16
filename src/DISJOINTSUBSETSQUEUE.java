class DISJOINTSUBSETSQUEUE
{
	private DISJOINTSUBSETSNODE head;
	private DISJOINTSUBSETSNODE tail;
	
	public void enqueue(DISJOINTSUBSETSNODE node)
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
	}
	
	public DISJOINTSUBSETSNODE dequeue()
	{
		if (isEmpty()) return null;
		else if (head == tail)
		{
			DISJOINTSUBSETSNODE temp = head;
			head = null;
			tail = null;
			return temp;
		}
		else
		{
			DISJOINTSUBSETSNODE temp = head;
			head = head.getNext();
			return temp;
		}
	}
	
	public boolean isEmpty()
	{
		if (head == null && tail == null) return true;
		else return false;
	}
	
	public boolean isInQueue(MULTIPLEPOSSIBILITIES mp)
	{
		if (isEmpty()) return false;
		DISJOINTSUBSETSNODE temp = head;
		
		while (temp != null)
		{
			if (temp.getMP().isSame(mp)) return true;
			else temp = temp.getNext();
		}
		return false;
	}
}