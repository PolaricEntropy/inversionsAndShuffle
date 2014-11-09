package se.kth.id1020.lab3;

import java.util.LinkedList;

public class Driver 
{
	public static void main( String[] args )
	{
		//For the first assignment.
		countInversions();

		//For the second assignment.
		shuffleList();
	}

	public static void countInversions(){
		int[] a = new int[]{5, 4, 3, 2, 1};
		int[] b = new int[]{1, 2, 3, 4, 5};

		//Sort and count inversions.
		System.out.println(InversionCounter.countAndSort(a));        	
	}

	public static void shuffleList(){
		LinkedList<String> a = new LinkedList<String>();

		//Add test data.
		//  a.add("a");
		//  a.add("b");
		//  a.add("c");
		//  a.add("d");
		//  a.add("e");
		//  a.add("f");
		//  a.add("g");
		//  a.add("h");
		//  a.add("i");
		//  a.add("j");
		//  a.add("k");
		//  a.add("l");
		//  a.add("m");
		//  a.add("n");
		//  a.add("o");
		//  a.add("p");
		//  a.add("q");
		//  a.add("r");
		//  a.add("s");
		//  a.add("t");
		//  a.add("u");
		//  a.add("v");
		//  a.add("w");
		//  a.add("x");
		//  a.add("y");
		//  a.add("z");

		a.add("1");
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		a.add("6");
		a.add("7");
		a.add("8");
		a.add("9");


		//Shuffle the list.
		Shuffle.ShuffleList(a);

		//Print the results.
		for (int i = 0; i < a.size(); i++)
			System.out.println(a.get(i));	
	}
}
