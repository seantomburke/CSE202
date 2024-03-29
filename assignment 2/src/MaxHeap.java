import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

public class MaxHeap
{
    private int size;
    private int maxsize;
    private SitesHashMap hm;
	private Frequency[] Heap;
 
    private static final int FRONT = 1;
    
 
	public MaxHeap(int maxsize)
    {
    	hm = new SitesHashMap();
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Frequency[this.maxsize + 1];
        Heap[0] = new Frequency("test");
    }

	public MaxHeap(Collection<Entry<String, Integer>> hmEntrySet)
    {
    	this.maxsize = hmEntrySet.size();
    	this.size = this.maxsize;
        Heap = new Frequency[this.maxsize + 1];
        Heap[0] = new Frequency("test");
        int i=0;
        Iterator<Entry<String, Integer>> iter = hmEntrySet.iterator();
    	while(iter.hasNext())
    	{
    		i++;
    		Entry<String, Integer> next = iter.next();
    		Heap[i] = new Frequency(next.getKey(), next.getValue());	
    	}
    	
    	maxHeap();

    }
 
    public MaxHeap(Frequency[] subheap) {
    	this.maxsize = subheap.length;
        this.size = subheap.length -1;
        Heap = subheap;
        Heap[0] = new Frequency("test");
        
	}

	private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        {
            return true;
        }
        return false;
    }
 
    private void swap(int fpos,int spos)
    {
        Frequency tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
 
    private void maxHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            if ( Heap[pos].getValue() < Heap[leftChild(pos)].getValue() || Heap[pos].getValue() < Heap[rightChild(pos)].getValue())
            {
                if (Heap[leftChild(pos)].getValue() > Heap[rightChild(pos)].getValue())
                {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }
 
    public void insert(String site)
    {
    	Heap[++size] = new Frequency(site); 
        int current = size;
 
        while(Heap[current].getValue() > Heap[parent(current)].getValue())
        {
            swap(current,parent(current));
            current = parent(current);
        }
    }
 
    public void print()
    {
        for (int i = 1; i < size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i].getValue() + Heap[i].getKey() + " LEFT CHILD : " + Heap[2*i].getValue() + Heap[2*i].getKey()
                  + " RIGHT CHILD :" + Heap[2 * i  + 1].getValue() + Heap[2 * i  + 1].getKey());
            System.out.println();
        }
    }
 
    public void maxHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--)
        {
            maxHeapify(pos);
        }
    }
 
    public Frequency remove()
    {
    	Frequency popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        maxHeapify(FRONT);
        return popped;
    }
    
    public ArrayList<Frequency> top(Integer k)
    {
    	ArrayList<Frequency> output = new ArrayList<Frequency>();
    	MaxHeap subheap;
    	if(k > (Math.log(1000000)/Math.log(2)))
		{
    		subheap = this;
		}
    	else
    	{
    		Frequency subarray[] = new Frequency[(int)Math.pow(k+1, 2) + 1];
	    	for(int l=0; l<= Math.pow(k+1,2);l++)
	    	{
	    		subarray[l] = Heap[l];
	    	}
	    	subheap = new MaxHeap(subarray);

    	}
    	for(int m=1;m<k;m++)
    	{
    		output.add(subheap.remove());
    	}
    	return output;
    	
    	
    	
    }
    
    public static MaxHeap createHeap(ArrayList<String> input)
    {
    	MaxHeap output = new MaxHeap(input.size());
    	for(String s: input)
    	{
    		output.insert(s);
    		System.out.println(s);
    	}
    	return output;
    }
 
    public static void main(String...arg)
    {
        System.out.println("The Max Heap is ");
        MaxHeap maxHeap = new MaxHeap(15);
        maxHeap.insert("www.sean.com");
        maxHeap.insert("www.vineel.com");
        maxHeap.insert("www.bonnie.com");
        maxHeap.insert("www.sean.com");
        maxHeap.insert("www.bonnie.com");
        maxHeap.insert("www.vineel.com");
        maxHeap.insert("www.sean.com");
        maxHeap.insert("www.vineel.com");
        maxHeap.insert("www.bonnie.com");
        maxHeap.insert("www.jen.com");
 
        maxHeap.print();
        //System.out.println("The max val is " + maxHeap.remove());
    }
}