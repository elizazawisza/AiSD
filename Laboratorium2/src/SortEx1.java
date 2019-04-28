import java.util.Scanner;

@SuppressWarnings("ALL")
public class SortEx1 {

    public static int quickSortcomparisons;
    public static int heapSortcomparisions;
    public static int quickSortswaps;
    public static int heapSortswaps;
    public static long beginning;
    public static long end;

    private static void swap(int [] inputArray, int firstIndex, int secondIndex){
        int tmp = inputArray[firstIndex];
        inputArray[firstIndex] = inputArray[secondIndex];
        System.err.println("swap");
        inputArray[secondIndex] = tmp;
        System.err.println("swap");
    }

    private static void increasingInsertionSort(int [] inputArray){
        long beginning = System.nanoTime();
        int comparisons = 0;
        int swaps = 0;
        int size = inputArray.length;
        for(int j=1; j<size; j++){
            int key = inputArray[j];
            int i = j-1;
            while(i>=0 && inputArray[i]>key){
                System.err.println("comparison");
                comparisons++;
                inputArray[i+1] = inputArray[i];
                swaps++;
                System.err.println("swap");
                i = i-1;
            }
            inputArray[i+1] = key;
        }
        long end = System.nanoTime();
        printSummary(swaps, comparisons, end-beginning);
    }

    private static void decreasingInsertionSort(int [] inputArray){
        long beginning = System.nanoTime();
        int comparisons = 0;
        int swaps = 0;
        for (int i = 1; i < inputArray.length; i++) {
            int key = inputArray[i];
            int j = i;
            while (j > 0 && inputArray[j - 1] < key) {
                System.err.println("comparison");
                comparisons++;
                inputArray[j] = inputArray[j - 1];
                swaps++;
                System.err.println("swap");
                j--;
            }
            inputArray[j] = key;
        }
        long end = System.nanoTime();
        printSummary(swaps, comparisons, end-beginning);
    }

    private static void decreasingSelectionSort(int [] inputArray){
        long beginning = System.nanoTime();
        int comparisons = 0;
        int swaps = 0;
        for (int i = 0; i < inputArray.length-1; i++) {
            int maxIdx = i;

            for(int j= i + 1; j<inputArray.length; j++){
                if(inputArray[j] > inputArray[maxIdx]){
                    comparisons++;
                    System.err.println("comparison");
                    maxIdx = j;
                }
            }
            swap(inputArray, maxIdx, i);
            swaps = swaps+2;
        }
        long end = System.nanoTime();
        printSummary(swaps, comparisons, end-beginning);

    }

    private static void increasingSelectionSort(int [] inputArray){
        long beginning = System.nanoTime();
        int comparisons = 0;
        int swaps = 0;
        for (int i = 0; i < inputArray.length-1; i++) {
            int minIdx = i;

            for(int j= i + 1; j<inputArray.length; j++){
                if(inputArray[j] < inputArray[minIdx]){
                    System.err.println("comparison");
                    comparisons++;
                    minIdx = j;
                }
            }
            swap(inputArray, minIdx, i);
            swaps = swaps +2;
        }
        long end = System.nanoTime();
        printSummary(swaps, comparisons, end-beginning);
    }

    private static void increasingQuickSort(int [] inputArray, int low, int high){
        int q;
        if(low < high){
            q = partition1(inputArray, low, high);
            increasingQuickSort(inputArray, low, q-1 );
            increasingQuickSort(inputArray, q+1, high);
        }
    }

    private static void decreasingQuickSort(int [] inputArray, int low, int high){
        int q;
        if(low < high){
            q = partition2(inputArray, low, high);
            decreasingQuickSort(inputArray, low, q-1 );
            decreasingQuickSort(inputArray, q+1, high);
        }
    }

    private static int partition1(int [] inputArray, int low, int high){
        int x = inputArray[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(inputArray[j]<= x){
                quickSortcomparisons++;
                System.err.println("comparison");
                i = i + 1;
                swap(inputArray, i, j);
                quickSortswaps = quickSortswaps + 2;
            }
        }
        swap(inputArray, i+1, high);
        quickSortswaps = quickSortswaps + 2;
        return i + 1;
    }

    private static int partition2(int [] inputArray, int low, int high){
        int x = inputArray[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(inputArray[j]>= x){
                System.err.println("comparison");
                quickSortcomparisons++;
                i = i + 1;
                swap(inputArray, i, j);
                quickSortswaps = quickSortswaps + 2;
            }
        }
        swap(inputArray, i+1, high);
        quickSortswaps = quickSortswaps + 2;
        return i + 1;
    }

    private static void increasingHeapSort(int [] inputArray){
        long beginning = System.nanoTime();
        for(int i = inputArray.length/2 - 1; i>=0 ; i--){
            heapify1(inputArray, inputArray.length, i);
        }

        for(int i = inputArray.length-1; i>=0; i--){
            swap(inputArray, 0, i);
            heapSortswaps = heapSortswaps + 2;
            heapify1(inputArray, i, 0);
        }
        long end = System.nanoTime();
        printSummary(heapSortswaps, heapSortcomparisions, end-beginning);
    }


    private static void decreasingHeapSort(int [] inputArray){
        long beginning = System.nanoTime();
        for(int i = inputArray.length/2 - 1; i>=0 ; i--){
            heapify2(inputArray, inputArray.length, i);
        }

        for(int i = inputArray.length-1; i>=0; i--){
            swap(inputArray, 0, i);
            heapSortswaps = heapSortswaps + 2;
            heapify2(inputArray, i, 0);
        }
        long end = System.nanoTime();
        printSummary(heapSortswaps, heapSortcomparisions, end-beginning);
    }

    private static void heapify2(int [] inputArray, int size, int index){
        int largestElement = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(left < size && inputArray[left] < inputArray[largestElement]){
            System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = left;
        }

        if(right < size && inputArray[right] < inputArray[largestElement]){
            System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = right;
        }

        if(largestElement != index){
            swap(inputArray, index, largestElement);
            heapSortswaps = heapSortswaps + 2;
            heapify2(inputArray, size, largestElement);
        }
    }

    private static void heapify1(int [] inputArray, int size, int index){
        int largestElement = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(left < size && inputArray[left] > inputArray[largestElement]){
            System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = left;
        }

        if(right < size && inputArray[right] > inputArray[largestElement]){
            System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = right;
        }

        if(largestElement != index){
            swap(inputArray, index, largestElement);
            heapSortswaps = heapSortswaps + 2;
            heapify1(inputArray, size, largestElement);
        }
    }

    private static void printArray(int [] inputArray){
        System.out.println("Sorted array:" );
        for(int i = 0; i<inputArray.length; i++){
            System.out.print(inputArray[i] + " ");
        }
    }

    private static void printSummary(int swaps, int comparisons, long time){
        double timeInSec = (double)(time)/1_000_000.0;
        System.err.println("Executing time of the sorting algorithm: " +  timeInSec);
        System.err.println("Amount of swaps: " +  swaps);
        System.err.println("Amount of comparisons: " +  comparisons);
    }

    private static void wrongParameters(){
        System.out.println("You run program with wrong parameters, please try again.");
    }

    private static int[] prepareArray(Scanner keyboard){
        System.out.println("Enter size of the array you'd like to sort");
        int amount = keyboard.nextInt();
        keyboard.nextLine();
        int [] userSortArray = new int[amount];
        for(int i = 0; i<amount; i ++){
            userSortArray[i] = keyboard.nextInt();
        }
        return userSortArray;
    }

    private static boolean checkIfCorrect(int [] inputArray, String type){
        if(type.equals("desc")){
            for(int i = 1; i < inputArray.length; i++){
                if(inputArray[i-1] < inputArray[i] ){
                    return false;
                }
            }
            return true;
        }else{
            for(int i = 1; i < inputArray.length; i++){
                if(inputArray[i-1] > inputArray[i] ){
                    return false;
                }
            }
            return true;
        }
    }


    public static void main(String[] args) {
        int[] input;
        Scanner keyboard = new Scanner(System.in);

        if(args.length <=0){
            System.out.println("You must run the program with the following parameters: ");
            System.out.println("--type select|insert|heap|quick --asc|--desc");
        }
        else if(args[0].equals("--type")){
            switch(args[1]){
                case "select":
                    if(args[2].equals("--asc")){
                        input = prepareArray(keyboard);
                        increasingSelectionSort(input);
                        if(checkIfCorrect(input, "asc")){
                            printArray(input);
                        }
                    }else if(args[2].equals("--desc")){
                        input = prepareArray(keyboard);
                        decreasingSelectionSort(input);
                        if(checkIfCorrect(input, "desc")){
                            printArray(input);
                        }
                    }
                    else
                        wrongParameters();
                    break;
                case "insert":
                    if(args[2].equals("--asc")){
                        input = prepareArray(keyboard);
                        increasingInsertionSort(input);
                        if(checkIfCorrect(input, "asc")){
                            printArray(input);
                        }
                    }else if(args[2].equals("--desc")){
                        input = prepareArray(keyboard);
                        decreasingInsertionSort(input);
                        if(checkIfCorrect(input, "desc")){
                            printArray(input);
                        }
                    }
                    else
                        wrongParameters();
                    break;
                case "heap":
                    if(args[2].equals("--asc")){
                        input = prepareArray(keyboard);
                        heapSortcomparisions = 0;
                        heapSortswaps = 0;
                        increasingHeapSort(input);
                        if(checkIfCorrect(input, "asc")){
                            printArray(input);
                        }
                    }else if(args[2].equals("--desc")){
                        input = prepareArray(keyboard);
                        heapSortcomparisions = 0;
                        heapSortswaps = 0;
                        decreasingHeapSort(input);
                        if(checkIfCorrect(input, "desc")){
                            printArray(input);
                        }
                    }
                    else
                        wrongParameters();
                    break;
                case "quick":
                    if(args[2].equals("--asc")){
                        input = prepareArray(keyboard);
                        quickSortcomparisons = 0;
                        quickSortswaps = 0;
                        beginning = System.nanoTime();
                        increasingQuickSort(input, 0, input.length-1);
                        end = System.nanoTime();
                        printSummary(quickSortswaps, quickSortcomparisons, end-beginning);
                        if(checkIfCorrect(input, "asc")){
                            printArray(input);
                        }
                    }else if(args[2].equals("--desc")){
                        input = prepareArray(keyboard);
                        quickSortcomparisons = 0;
                        quickSortswaps = 0;
                        beginning = System.nanoTime();
                        decreasingQuickSort(input, 0, input.length-1);
                        end = System.nanoTime();
                        printSummary(quickSortswaps, quickSortcomparisons, end-beginning);
                        if(checkIfCorrect(input, "desc")){
                            printArray(input);
                        }
                    }
                    else
                        wrongParameters();
                    break;
                default:
                    wrongParameters();
            }
        }
        else{
            wrongParameters();
        }

    }
}
