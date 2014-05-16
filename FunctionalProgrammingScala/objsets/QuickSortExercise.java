import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class QuickSortExercise {

	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("QuickSort.txt"));
		int[] arr = new int[10000];
		for(int i=0; i<arr.length; i++){
			arr[i] = s.nextInt();
		}
		System.out.println("Array is " + Arrays.toString(arr));
		int comps = quickSort(arr, 0, arr.length);
		System.out.println("Took " + comps + " comparisons to sort to: " + Arrays.toString(arr));
		System.out.println(checkIsSorted(arr));
	}

	private static boolean checkIsSorted(int[] arr) {
		int current = arr[0];
		for(int i=1; i<arr.length; i++){
			if(arr[i] <= current)
				return false;
			current = arr[i];
		}
		return true;
	}

	private static int quickSort(int[] arr, int start, int end) {
		if(end-start < 2){
			return 0;
		}
		
		int countComparisons = end - start - 1;
		int pivotIndex = getPivotIndex(arr, start, end);
		
		swap(arr, start, pivotIndex);
		
		int newPivotIndex = partition(arr, start, end);
		
		countComparisons += quickSort(arr, start, newPivotIndex) + quickSort(arr, newPivotIndex + 1, end);
		
		return countComparisons;
	}

	private static void swap(int[] arr, int index1, int index2) {
		if(index1==index2) return;
		int oldStart = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = oldStart;
	}

	private static int partition(int[] arr, int start, int end) {
		//always use start elem as pivot
		int pivot = arr[start];
		int i = start+1;
		for(int j = start+1; j < end; j++){
			if(arr[j] < pivot){
				swap(arr, j, i);
				i++;
			}
		}
		swap(arr, start, i-1);
		return i-1;
	}

	private static int getPivotIndex(int[] arr, int start, int end) {
		int first = arr[start];
		int last = arr[end-1];
		int middle = arr[(start + end - 1)/2];
		
		if(first<=middle && middle<=last || last<=middle && middle<=first){
			return (start + end - 1)/2;
		} else if(middle<=first && first<=last || last<=first && first<=middle){
			return start;
		} else {
			return end-1;
		}
	}
}
