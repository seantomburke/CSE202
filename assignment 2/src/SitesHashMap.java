import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class SitesHashMap extends HashMap<String, Integer> {
	
	public void insertSite(String s)
	{
		if(this.containsKey(s))
		{
			this.put(s, this.get(s)+1);
		}
		else
		{
			this.put(s, 1);
		}
	}

}
