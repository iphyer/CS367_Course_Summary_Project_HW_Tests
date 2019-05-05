import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   String[] arr = {"Ben", "Beck", "Eric", "Vidya", "Jason", "Xiaoming"};
		   List<String> newList = Arrays.asList(arr);
		   ArrayList<String> words = new ArrayList<String>();
		   
		   words.addAll(newList);
		   
		   for (int i = 0; i < words.size(); i += 1) {
			    words.remove(i); 
			} 
		   
		   System.out.println(words);

	}

}
