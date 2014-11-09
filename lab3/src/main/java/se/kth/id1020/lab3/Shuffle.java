package se.kth.id1020.lab3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;



/**
 * Background for this algorithm: https://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time
 * @author Björn Ehrby
 */
public class Shuffle {

	/**
	 * Shuffles a list in n log n time with merge sort.
	 * 
	 * Will take log n memory as for each recursion there will be an overhead for each List.
	 * List at the start: [header]+[1, 2, 3, 4]
	 * When you split you get something like this: [header]+[1, 2] and [header]+[3, 4]. Thus you have an overhead for each list while splitting.
	 * 
	 * @param list List to be shuffled.
	 */
	public static <T> void ShuffleList(LinkedList<T> list)
	{   	
		//Do not shuffle empty or lists with one item.
		if (list.size() <= 1)
			return;

		Random rand = new Random();

		//These two lists will together hold our data.
		LinkedList<T> AList = new LinkedList<T>();
		LinkedList<T> BList = new LinkedList<T>();

		//Move elements from our main list to our A and B lists, thus memory consumption remains constant.
		while (list.isEmpty() == false)
		{
			AList.add(list.pollFirst());

			if (list.isEmpty() == false)
				BList.add(list.pollFirst());
		}

		//Shuffle the lists recursively.
		ShuffleList(AList);
		ShuffleList(BList);

		//Variables to keep track of our Dummy element, if it should be inserted.
		int indexOfDummy = -1;
		boolean DummyIsInB = false;

		//If we have uneven amounts of elements in the main list we get a smaller BList then AList since elements are added to A before B.
		//We need to insert a Dummy element to even it out. That way the probability for picking an element later on in either list will be equal.
		if (BList.size() < AList.size())
		{
			//Store the index of the dummy node so we can keep track of it, index is random in the BList.
			indexOfDummy = rand.nextInt(BList.size()+1);
			DummyIsInB = true;

			//Insert the dummy element randomly in the BList. This takes a worst case of O(N).
			BList.add(indexOfDummy, null);
		}


		//Make sure we still have stuff in our lists to merge.
		while(AList.isEmpty() == false && BList.isEmpty() == false)
		{
			//Randomly choose to get stuff from either the A or the B list to add to our main list.
			if (rand.nextInt(2) == 1)
				list.add(AList.pollFirst());
			else
			{
				//Remove the element from the BList and add it to the main list.
				list.add(BList.pollFirst());

				//If the element we removed from the BList is the Dummy.
				if (indexOfDummy == 0 && DummyIsInB)
				{
					//Since we've added the Dummy element to the end of our main list, set the index to point to the Dummy element in the main list.
					indexOfDummy = list.size()-1;
					DummyIsInB = false;
				}
				else if (indexOfDummy > 0 && DummyIsInB)
				{
					//Since we did not remove the Dummy, we just moved it closer to the front of the list. Update the variable to point to the correct element.
					indexOfDummy--;
				}
			}

		}

		//Now one of the lists are empty, but the other one could still have values.
		//Check which one and add all of their elements to our main list.
		Iterator<T> lstIterator;

		if (AList.isEmpty() == false)
			lstIterator = AList.listIterator();
		else
			lstIterator = BList.listIterator();

		while (lstIterator.hasNext())
		{
			list.add(lstIterator.next());
			lstIterator.remove();

			//We just added the Dummy to the main list. We know this since the Dummy is still in the BList, thus we must be adding stuff from the BList now.
			if(indexOfDummy == 0 && DummyIsInB)
			{
				indexOfDummy = list.size()-1;
				DummyIsInB = false;
			}
			else if (indexOfDummy > -1 && DummyIsInB)
			{
				//We did not add the Dummy to the main list, so update index to match.
				indexOfDummy--;
			}
		}    	

		//If we don't have a Dummy we have -1 as index, else the variable points to the location of the Dummy element.
		if (indexOfDummy > -1)
		{
			//Since we've kept track of our Dummy element we can now remove it. This takes a worst case of O(N).
			list.remove(indexOfDummy);	
		}
	}
	
	
//  WORKS BUT DOESN'T MEET THE REQUIREMENTS. TAKES N^2 TIME DUE TO LOOKUPS IN LINKEDLISTS ARE N IN TIME AND THIS IS DONE WHILE ITERATING OVER ALL ELEMENTS.
//  private static <T> void ShuffleList(List<T> list)
//  {
//  	//Do not shuffle empty or lists with one item to save time.
//  	if (list.size() <= 1)
//  		return;
//  	
//  	Random rand = new Random();
//  	int N = list.size();
//  	
//  	//Declare variable out here to save on garbage collecting if T is a primitive type. If T is a reference type this doesn't matter.
//  	T temp;
//  	int elementIndex;
//  	
//  	//Shuffle all elements.
//  	for (int i = 0; i < N; i++)
//  	{
//  		//Get a random element from N-i, if we have 10 elements and are at index 0 we pick a random element from 0 to 10.
//  		//We need to add i to shift from 0 to our i cause if we are at i=2 we should get a rand value between 10-2 and then add 2 to get random values from 2-10.
//  		elementIndex = i + rand.nextInt(N-i);
//  		temp = list.get(i);
//  		list.set(i, list.get(elementIndex));
//  		list.set(elementIndex, temp);
//  	}
//  	
//  }
	
}
