public class FindLargestSmallest {
 
    public int findLargestSmallest(int[] array, String s) {
    
    int smallest = array[0];
    int largest = array[0];
    
        for(int i=1; i< arr.length; i++) {
            if(arr[i] > largest)
                largest = arr[i];
            else if (arr[i] < smallest)
                smallest = arr[i];
        }
    
        if (s = "l") {
            return largest;
        } 
            return smallest;
    }
      
} 