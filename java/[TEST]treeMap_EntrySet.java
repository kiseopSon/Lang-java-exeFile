import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
public class treeMap_EntrySet {
	public static void main(String[] args) {

			TreeMap<Integer, String> TM = new TreeMap<Integer, String>();
			TM.put(new Integer(3), "se");
			TM.put(5, "001");
			TM.put(7, "01");
			System.out.println("100");
			
			Iterator<Integer> itr = null;
			Set<Integer> set = null; 
			NavigableSet<Integer> navigableSet = null;
			
			//1.  tree --> keyset() --> iterator();
			set = TM.keySet();
			itr = set.iterator();
			while(itr.hasNext()) {System.out.println(TM.get(itr.next()));}
			System.out.println("200");
			
			//2.  tree --> navigableSet();
			navigableSet = TM.navigableKeySet();
			itr = navigableSet.descendingIterator();
			while(itr.hasNext()) {System.out.println(TM.get(itr.next()));}
			System.out.println();
			
			navigableSet = TM.descendingKeySet();
			itr = navigableSet.descendingIterator();
			while(itr.hasNext()) {System.out.println(TM.get(itr.next()));}
			
			HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
			
			hashmap.put("a", 1);
			hashmap.put("b", 2);
			hashmap.put("c", 3);
			Set<Map.Entry<String,Integer>> entrySet = hashmap.entrySet();
			System.out.println(entrySet);
	}
}
