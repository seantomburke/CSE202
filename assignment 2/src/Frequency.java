import java.util.Map.Entry;


public class Frequency implements Entry<String, Integer>{
	
	public String site;
	public int frequency;
	
	public Frequency(String word)
	{
		this.site = word;
		this.frequency = 1;
	}
	
	public Frequency(String word, Integer freq)
	{
		this.site = word;
		this.frequency = freq;
	}
	
	public void increment()
	{
		this.frequency++;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.site;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return this.frequency;
	}

	@Override
	public Integer setValue(Integer value) {
		// TODO Auto-generated method stub
		this.frequency = value;
		return value;
	}

}
