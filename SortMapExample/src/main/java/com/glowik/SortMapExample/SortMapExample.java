package com.glowik.SortMapExample;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * How to sort a map in Java. (Example.)
 * 
 * A full Java example to demonstrate how to sort a Map (HashMap) based on its
 * åvalues. The overall idea is, convert the Map into List, sort the List and
 * put the sorted list back to a Map.
 * 
 * @see http://www.mkyong.com/java/how-to-sort-a-map-in-java/
 * @since April 1, 2011
 * 
 */
public class SortMapExample {

	public static void main(String[] args) {

		System.out.println("Unsort Map......");
		Map<String, String> unsortMap = new HashMap<String, String>();
		unsortMap.put("1", "1");
		unsortMap.put("2", "A");
		unsortMap.put("3", "2");
		unsortMap.put("4", "B");
		unsortMap.put("5", "C");
		unsortMap.put("6", "c");
		unsortMap.put("7", "b");
		unsortMap.put("8", "a");

		
		//Iterator iterator = unsortMap.entrySet().iterator();

		for (Map.Entry<String, String> entry : unsortMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
		}

		System.out.println("Sorted Map......");
		Map<String, String> sortedMap = sortByComparator(unsortMap);

		for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
		}
	}

	private static Map<String, String> sortByComparator(Map<String, String> unsortMap) {

		List<String> list = new LinkedList(unsortMap.entrySet());

		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// put sorted list into map again
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}