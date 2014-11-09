package se.kth.id1020.lab3;

/**
 * Basically this in an implementation of this: http://www.geeksforgeeks.org/counting-inversions/ 
 * @author Björn Ehrby
 *
 */
public class InversionCounter {

	/**
	 * Sorts and counts the number of inversions in our array.
	 * @param a The array to sort and count.
	 * @return The number inversions found in the array.
	 */
	public static int countAndSort(int[] a)
	{
		return mergeSort(a, new int[a.length] , 0, a.length-1);
	}

	/**
	 * Sorts and counts the number of inversions in our array.
	 * @param list The array to sort and count.
	 * @param temp Temp array needed to store values while counting and sorting.
	 * @param left Left boundary of the array.
	 * @param right Right boundary of the array.
	 * @return The number of inversions found.
	 */
	private static int mergeSort(int[] list, int[] temp, int left, int right)
	{
		int middle, inversions = 0;

		//Only divide if our left value is smaller then our right.
		//If this is false we have a sorted list or sublist, this happens when we try to divide a list of 1 element, thus left = right.
		if (left < right)
		{
			//Find the mid point for dividing the list. 
			middle = left + ((right - left) / 2); 	//Note: Done to prevent the famous BinarySearch bug. 
			//http://googleresearch.blogspot.se/2006/06/extra-extra-read-all-about-it-nearly.html

			//Consider the following array [3, 2, 1, 0]. It is split into two sublists [3, 2] and [1, 0] and those are
			//split into sublists as well so it looks like this [3], [2], [1], [0]. We count inversions while merging lists.
			//Note: No actual splitting occurs, this is just logical partitioning.

			//Sort both sublists recursively. The inversions for each sublist should be counted.
			inversions = mergeSort(list, temp, left, middle);
			inversions += mergeSort(list, temp, middle+1, right);

			//Merge the two lists, count inversions between lists.
			inversions += merge(list, temp, left, middle+1, right);
		}

		return inversions;
	}

	/**
	 * Merge our logical lists back into the main list.
	 * @param list Main list that holds the values initially and in the end.
	 * @param temp Temp list to be used for backup during merging.
	 * @param left Left boundary for first subarray.
	 * @param middle Left boundary for second subarray.
	 * @param right Right boundary for second subarray.
	 * @return The number of inversions when merging our lists.
	 */
	private static int merge(int[] list, int[]temp, int left, int middle, int right)
	{
		//Copy both subarrays to our temp array, the temp array will keep our data safe while we mess with the final array.
		for (int i = left; i <= right; i++)
			temp[i] = list[i];


		//i is index for the left subarray, j is for the right subarray and k is for placement in the final array.
		//[2, 3][0, 1], two logical subarrays in the final array "list".
		// ^	 ^
		// i	 j
		int i = left, j = middle, k = left;
		int inversions = 0;    	

		//Compare the lists element by element until we've reached the end of either list.
		while (i <= middle -1  && j <= right)
		{
			//Copy the smallest value from either sublist in our temp array to the final array.
			//This will overwrite stuff in our real array, if we had [4, 5, 3, 2, 1] we will get [3, 5, 3, 2, 1].
			//This is fixed below.
			if(temp[i] <= temp[j])
				list[k] = temp[i++];
			else
			{
				list[k] = temp[j++];

				//Because our sub arrays are sorted, the fact that we found that temp[i] > temp[j] means that all remaining elements in the left sublist are bigger.
				//Say we have [1, 5, 7] in the left list and [2, 4, 6] in the right, first compare temp[i] < temp[j], no inversion. On the second compare we have an inversion, since
				//our lists are sorted all remaining elements will be inversions with the right sublist. middle -i will get the number of remaining elements.
				inversions += (middle - i);
			}
			k++;
		}

		//Copy the remainder of the left side to the final array. Since we have overwritten restore the stuff so it looks like [3, 4, 5, 2, 1].
		while (i <= middle -1)
			list[k++] = temp[i++];

		return inversions;
	}

	//		WORKS BUT DOESN'T MEET THE REQUIREMENTS, HAS A TIME COMPLEXITY OF N^2.
	//		public static int count(int[] a)
	//		{    	
	//	    	int inversions = 0;
	//    
	//	    	//Only do when i is lesser then length-1, thus skip the last element cause the inner loop does that.
	//	        for (int i = 0; i < a.length -1; i++)
	//	        {
	//	        	//Don't start from 0, as we've already counted them, instead start from i+1 so we compare i to i+1.
	//	        	for (int j = i+1; j < a.length; j++)
	//	        	{
	//	        		if (a[i] > a[j])
	//	        			inversions++;
	//	        	}
	//	        }
	//	        
	//	        return inversions;
	//		}


}
