import java.io.IOException;

class SUBSOLVER
{
	public QUEUE solve(QUEUE q, int level) throws IOException
	{
		if (level < 1) return q;
		if (q == null) return null;
		
		SINGLECANDIDATURE s1 = new SINGLECANDIDATURE();
		SINGLECELLCANDIDATES s2 = new SINGLECELLCANDIDATES();
		SINGLESECTORCANDIDATES s3 = new SINGLESECTORCANDIDATES();
		DISJOINTSUBSETS s4 = new DISJOINTSUBSETS();
		MULTIPLEPOSSIBILITIESCELLS s5 = new MULTIPLEPOSSIBILITIESCELLS();
		XWING s6 = new XWING();
		SWORDFISH s7 = new SWORDFISH();
		GUESS s8 = new GUESS();
		s1.setExecutePreviousStrategy();
		s2.setExecutePreviousStrategy();
		s3.setExecutePreviousStrategy();
		s4.setExecutePreviousStrategy();
		s5.setExecutePreviousStrategy();
		s6.setExecutePreviousStrategy();
		s7.setExecutePreviousStrategy();
		s8.setExecutePreviousStrategy();
		
		
		if (level > 0)
		{
			do
			{
				q = s1.solve(q);
				if (q == null) return null;
			}while(s1.anyChange());
		}
		if (level > 1)
		{
			do
			{
				q = s2.solve(q);
				if (q == null) return null;
			}while(s2.anyChange());
		}
		if (level > 2)
		{
			do
			{
				q = s3.solve(q);
				if (q == null) return null;
			}while(s3.anyChange());
		}
		if (level > 3)
		{
			do
			{
				q = s4.solve(q);
				if (q == null) return null;
			}while(s4.anyChange());
		}
		if (level > 4)
		{
			do
			{
				q = s5.solve(q);
				if (q == null) return null;
			}while(s5.anyChange());
		}
		if (level > 5)
		{
			do
			{
				q = s6.solve(q);
				if (q == null) return null;
			}while(s6.anyChange());
		}
		if (level > 6)
		{
			do
			{
				q = s7.solve(q);
				if (q == null) return null;
			}while(s7.anyChange());
		}
		if (level > 7)
		{
			do
			{
				q = s8.solve(q);
				if (q == null) return null;
			}while(s8.anyChange());
		}
		return q;
	}
}