import java.lang.*;//This might not be needed
import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   	private Item[] s;
   	private int N = 0;
//   	private int nCapacity;
   	private int nHead = 0;
   	private int nTail = 0;
   	private void resize(int nInCapacity)
   	{
   		Item[] newS = (Item[]) new Object[nInCapacity];
   		for(int i=0;i<N;i++) newS[i] = s[(i+nHead)%(s.length)];
   		nHead = 0;
   		nTail = N;
   		s = newS;
   	}
   	//public RandomizedQueue(int nInCapacity)                 // construct an empty randomized queue
   	public RandomizedQueue()                 // construct an empty randomized queue
   	{
//   		s = (Item[]) new Object[nInCapacity];
   		s = (Item[]) new Object[2];
   	}
   	public boolean isEmpty()                 // is the queue empty?
   	{
   		return N == 0;
   	}
   	public int size()                        // return the number of items on the queue
   	{
   		return N;
   	}
   	public void enqueue(Item item)           // add the item
   	{
   		if(item == null) throw new IllegalArgumentException( "Cannot enqueue null items" );
   		s[(nTail%(s.length))] = item;
   		N++;
   		nTail++;
   		if(N == s.length) resize(2*s.length);
   		
   	}
   	public Item dequeue()                    // remove and return a random item
   	{
   		if(N==0) throw new NoSuchElementException( "The queue is empty" );
   		int nTemp = StdRandom.uniform(N);
   		Item itemRnd = s[(nTemp+nHead)%(s.length)];
   		s[(nTemp+nHead)%(s.length)] = s[nHead%(s.length)];
   		s[nHead%(s.length)] = null;
   		nHead++;
   		N--;
   		return itemRnd;
   	}
   	public Item sample()                     // return (but do not remove) a random item
   	{
   		if(N==0) throw new NoSuchElementException( "The queue is empty" );
   		int nTemp = StdRandom.uniform(N);
   		Item itemRnd = s[(nTemp+nHead)%(s.length)];
   		return itemRnd;
   	}
   	public Iterator<Item> iterator()         // return an independent iterator over items in random order
   	{
   		return new RandomArrayIterator();
   	}
   	private class RandomArrayIterator implements Iterator<Item>{
   	//	private int nTempHead = nHead%(s.length);
   		private int nTempHead = 0;
   		private int nTempN = N;
   		private Item[] newS = (Item[]) new Object[N];
   		public RandomArrayIterator()
   		{
   			for(int j=0;j<N;j++) 
   			{
   				newS[j] = s[(nHead+j)%(s.length)];
   			}
   		}
   		
   		public boolean hasNext()
   		{
   			return nTempN != 0;
   		}
   		public void remove()
   		{
   			throw new UnsupportedOperationException( "Remove() method not supported" );
   		}
   		public Item next()
   		{
   			if(nTempN == 0) throw new NoSuchElementException( "No more items to return" );
   			int nTempRnd = StdRandom.uniform(nTempN);
   			Item itemTemp = newS[(nTempRnd+nTempHead)%(newS.length)];
   			newS[(nTempRnd+nTempHead)%(newS.length)] = newS[nTempHead%(newS.length)];
   			newS[nTempHead%(newS.length)] = null;
   			nTempHead++;
   			nTempN--;
   			return itemTemp;
   		}
   	}
   	public static void main(String[] args)   // unit testing (optional)
   	{
   		RandomizedQueue<Integer> RndQueue = new RandomizedQueue<Integer>();
   		RndQueue.enqueue(1);
   		RndQueue.enqueue(2);
   		RndQueue.enqueue(3);
   		RndQueue.enqueue(4);
   		RndQueue.enqueue(5);
   		for(Integer nTemp:RndQueue) StdOut.println(nTemp);
   		StdOut.println(RndQueue.sample());
   		StdOut.println("bovious");
   		for(Integer nTemp:RndQueue) StdOut.println(nTemp);
   		StdOut.println(RndQueue.dequeue());
   		StdOut.println("bovious");
   		for(Integer nTemp:RndQueue) StdOut.println(nTemp);
   		StdOut.println("Size of queue:");
   		StdOut.println(RndQueue.size());
   	}
}
