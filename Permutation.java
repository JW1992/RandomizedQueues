import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
	public static void main(String[] args)
	{
		RandomizedQueue<String> thisRndQueue = new RandomizedQueue<String>();
		int nPopTimes = Integer.parseInt(args[0]);
		String[] tempStr = StdIn.readAllStrings();
		for(int i=0; i<tempStr.length; i++)
		{
			thisRndQueue.enqueue(tempStr[i]);
		}
		for(int i=0; i<nPopTimes; i++)
		{
			StdOut.println(thisRndQueue.dequeue());
		}
	}
}
