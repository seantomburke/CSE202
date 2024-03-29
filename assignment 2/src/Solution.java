import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

class Solution {
	
	public static String[] domains = {"com", "org", "edu"};
	private static Integer numSites = 1000000;
	
	public Solution(){
		
	}
	
	
	public static ArrayList<String> createWebsites(){
		ArrayList<String> output = new ArrayList<String>();
		for(int i=0; i<(numSites/4);i++)
		{
			output.add(RandomStringUtils.randomAlphabetic(3).toLowerCase() + "." + "cs" + "." + "edu");
		}
		for(int i=0; i<(numSites/4);i++)
		{
			output.add("free" + "." + RandomStringUtils.randomAlphabetic(3).toLowerCase() + "." + "com");
		}
		for(int i=0; i<(numSites/2);i++)
		{
			output.add(RandomStringUtils.randomAlphabetic(2).toLowerCase() + "." + RandomStringUtils.randomAlphabetic(2).toLowerCase() + "." + domains[(int)(Math.random() * 3)]);
		}
		return output;
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	  {
		
		PrintWriter writer = new PrintWriter("202hw.csv", "UTF-8");
		System.out.println("k, Array time, Heap time");
		writer.println("k, Array time, Heap time");
	    int k;
	    for(int i=1;i<19;i++)
	    {
	    ArrayList<String> websites = createWebsites();
	    //max_heap.print();
	    SitesHashMap hm = new SitesHashMap();

	    for(String s: websites)
	    {
	    	hm.insertSite(s);
	    }
	    SitesHashMap hmclone = (SitesHashMap) hm.clone();
	    Collection<Entry<String, Integer>> hmEntrySetHeap = hmclone.entrySet();
	    Collection<Entry<String, Integer>> hmEntrySetArray = hmclone.entrySet();
	    
	    	k=(int) Math.pow(2,i);
	    //Start array time
	    

	    long hStartTime = System.nanoTime();
	    
	    MaxHeap max_heap = new MaxHeap(hmEntrySetHeap);
	    
	    ArrayList<Frequency> solution = max_heap.top(k);
	    
	    Iterator<Frequency> iters = solution.iterator();
//	    int i=0;
//	    while(iters.hasNext())
//	    {
//	    	Frequency next = iters.next();
//	    	System.out.println("#"+ ++i +" Site: " + next.site + " #" + next.frequency);
//	    }
	    

	    long hEndTime   = System.nanoTime();
	    long hTotalTime = hEndTime - hStartTime;
	    
	    

	    long startTime = System.nanoTime();
	    List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(hmEntrySetArray);
	    Collections.sort(list, new Comparator<Entry<String,Integer>>(){
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				if(o1.getValue() > o2.getValue())
				{
					return -1;
				}
				else if(o1.getValue() == o2.getValue())
				{
					return 0;
				}
				else
				{
					return 1;
				}
			}
	    });
//
//	    for(int j=0;j<k-1;j++)
//	    {
//	    	System.out.println("#"+ (j + 1) +" Site(list):" + list.get(j).getKey() + " #" + list.get(j).getValue());
//	    }
	    		
	    long endTime   = System.nanoTime();
	    long totalTime = endTime - startTime;
	    

	    System.out.println(k +"," + totalTime + "," + hTotalTime);
	    writer.println(k +"," + totalTime + "," + hTotalTime);
	    }

		writer.close();

	    	
	  }
}