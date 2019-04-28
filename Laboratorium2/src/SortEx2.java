import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class SortEx2 {

    private static int quickSortcomparisons;
    private static int heapSortcomparisions;
    private static int insertSortcomparisons;
    private static int selectSortcomparisions;
    private static int quickSortswaps;
    private static int heapSortswaps;
    private static int insertSortswaps;
    private static int selectSortswaps;
    private static long beginning;
    private static long end;
    private static String fileName;
    private static PrintWriter myFileWriter;

    /**
     * Method which change the elements in array
     * @param inputArray - given array
     * @param firstIndex - index of firstElement to swap
     * @param secondIndex = index of second Element to swap
     */

    private static void swap(int [] inputArray, int firstIndex, int secondIndex){
        int tmp = inputArray[firstIndex];
        inputArray[firstIndex] = inputArray[secondIndex];
        //System.err.println("swap");
        inputArray[secondIndex] = tmp;
        //System.err.println("swap");
    }

    /**
     * Method which sorts the array ascending using the insert sort algorithm.
     * Moving elements of array which are grater than the key.
     * @param inputArray - the given array
     */

    private static void increasingInsertionSort(int [] inputArray){
        int size = inputArray.length;
        for(int j=1; j<size; j++){
            int key = inputArray[j];
            int i = j-1;
            while(i>=0 && inputArray[i]>key){
                //System.err.println("comparison");
                insertSortcomparisons++;
                inputArray[i+1] = inputArray[i];
                insertSortswaps++;
                //System.err.println("swap");
                i = i-1;
            }
            inputArray[i+1] = key;
            insertSortswaps++;
        }
    }

    /**
     * Method which sorts the array descending using the insert sort algorithm.
     * Moving elements of array which are smaller than the key.
     * @param inputArray - the given array
     */

    private static void decreasingInsertionSort(int [] inputArray){
        for (int i = 1; i < inputArray.length; i++) {
            int key = inputArray[i];
            int j = i;
            while (j > 0 && inputArray[j - 1] < key) {
                insertSortcomparisons++;
                //System.err.println("comparison");
                inputArray[j] = inputArray[j - 1];
                insertSortswaps++;
                //System.err.println("swap");
                j--;
            }
            inputArray[j] = key;
            insertSortswaps++;
        }
    }

    /**
     * Method which sorts the array descending using the selection sort algorithm.
     * Method finds the element which should be at the current position and swap it with the current element
     * which is on that place.
     * @param inputArray - the given array
     */

    private static void decreasingSelectionSort(int [] inputArray){
        for (int i = 0; i < inputArray.length-1; i++) {
            int maxIdx = i;

            for(int j= i + 1; j<inputArray.length; j++){
                if(inputArray[j] > inputArray[maxIdx]){
                    selectSortcomparisions++;
                    //System.err.println("comparison" + selectSortcomparisions);
                    maxIdx = j;
                }
            }
            swap(inputArray, maxIdx, i);
            selectSortswaps = selectSortswaps+2;
        }
    }

    /**
     * Method which sorts the array ascending using the selection sort algorithm.
     * Method finds the element which should be at the current position and swap it with the current element
     * which is on that place.
     * @param inputArray - the given array
     */

    private static void increasingSelectionSort(int [] inputArray){
        for (int i = 0; i < inputArray.length-1; i++) {
            int minIdx = i;

            for(int j= i + 1; j<inputArray.length; j++){
                if(inputArray[j] < inputArray[minIdx]){
                    //System.err.println("comparison");
                    selectSortcomparisions++;
                    minIdx = j;
                }
            }
            swap(inputArray, minIdx, i);
            selectSortswaps = selectSortswaps +2;
        }
    }

    /**
     * Method which sorts the array ascending using the quick sort algorithm.
     * Method find the middle element and divide the given array into two arrays - elements smaller than
     * the middle one and elements greater than the middle one.
     * @param inputArray - the given array
     */

    private static void increasingQuickSort(int [] inputArray, int low, int high){
        int q;
        if(low < high){
            q = partition1(inputArray, low, high);
            increasingQuickSort(inputArray, low, q-1 );
            increasingQuickSort(inputArray, q+1, high);
        }
    }

    /**
     * Method which sorts the array descending using the quick sort algorithm.
     * Method find the middle element and divide the given array into two arrays - elements smaller than
     * the middle one and elements greater than the middle one.
     * @param inputArray - the given array
     */


    private static void decreasingQuickSort(int [] inputArray, int low, int high){
        int q;
        if(low < high){
            q = partition2(inputArray, low, high);
            decreasingQuickSort(inputArray, low, q-1 );
            decreasingQuickSort(inputArray, q+1, high);
        }
    }

    /**
     * Method for dividing the array into smaller one, and sort the elements. Using for quick sort algorithm.
     * @param inputArray - the given array
     * @param low - index of array
     * @param high - index of array
     * @return - sort array
     */

    private static int partition1(int [] inputArray, int low, int high){
        int x = inputArray[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(inputArray[j]<= x){
                quickSortcomparisons++;
                //System.err.println("comparison");
                i = i + 1;
                swap(inputArray, i, j);
                quickSortswaps = quickSortswaps + 2;
            }
        }
        swap(inputArray, i+1, high);
        quickSortswaps = quickSortswaps + 2;
        return i + 1;
    }

    /**
     * Method for dividing the array into smaller one, and sort the elements. Using for quick sort algorithm.
     * @param inputArray - the given array
     * @param low - index of array
     * @param high - index of array
     * @return - sort array
     */

    private static int partition2(int [] inputArray, int low, int high){
        int x = inputArray[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(inputArray[j]>= x){
                //System.err.println("comparison" + quickSortcomparisons);
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

    /**
     * Method which sort the given array in ascending order using the Heap Sort algorithm.
     * @param inputArray - the given array
     */


    private static void increasingHeapSort(int [] inputArray){
        for(int i = inputArray.length/2 - 1; i>=0 ; i--){
            heapify1(inputArray, inputArray.length, i);
        }

        for(int i = inputArray.length-1; i>=0; i--){
            swap(inputArray, 0, i);
            heapSortswaps = heapSortswaps + 2;
            heapify1(inputArray, i, 0);
        }
    }

    /**
     * Method which sort the given array in descending order using the Heap Sort algorithm.
     * @param inputArray - the given array
     */

    private static void decreasingHeapSort(int [] inputArray){
        for(int i = inputArray.length/2 - 1; i>=0 ; i--){
            heapify2(inputArray, inputArray.length, i);
        }

        for(int i = inputArray.length-1; i>=0; i--){
            swap(inputArray, 0, i);
            heapSortswaps = heapSortswaps + 2;
            heapify2(inputArray, i, 0);
        }
    }

    /**
     * Method to create the heap of the array, which root is the i element of array. And sort the elements
     * @param inputArray - given array
     * @param size - size of subtree
     * @param index - index of root
     */

    private static void heapify2(int [] inputArray, int size, int index){
        int largestElement = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(left < size && inputArray[left] < inputArray[largestElement]){
            //System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = left;
        }

        if(right < size && inputArray[right] < inputArray[largestElement]){
            //System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = right;
        }

        if(largestElement != index){
            swap(inputArray, index, largestElement);
            heapSortswaps = heapSortswaps + 2;
            heapify2(inputArray, size, largestElement);
        }
    }

    /**
     * Method to create the heap of the array, which root is the i element of array. And sort the elements
     * @param inputArray - given array
     * @param size - size of subtree
     * @param index - index of root
     */

    private static void heapify1(int [] inputArray, int size, int index){
        int largestElement = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(left < size && inputArray[left] > inputArray[largestElement]){
            //System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = left;
        }

        if(right < size && inputArray[right] > inputArray[largestElement]){
            //System.err.println("comparison");
            heapSortcomparisions++;
            largestElement = right;
        }

        if(largestElement != index){
            swap(inputArray, index, largestElement);
            heapSortswaps = heapSortswaps + 2;
            heapify1(inputArray, size, largestElement);
        }
    }

    /**
     * Method which prints the array
     * @param inputArray - the given array
     */

    private static void printArray(int [] inputArray){
        System.out.println("Sorted array:" );
        for(int i = 0; i<inputArray.length; i++){
            System.out.print(inputArray[i] + " ");
        }
    }

    /**
     * Method which prints the summary of Sorting Algorithm
     * @param swaps - amount of swaps which were made during execution of algorithm
     * @param comparisons - amount of comparisons which were made during execution of algorithm
     * @param time - time from the beginning to the end of algorithm
     * @param type - type of program (1 for the --stat and 0 for the other)
     * @param size - size of array
     */
    private static void printSummary(int swaps, int comparisons, long time, int type, int size){
        double timeInSec = (double)(time)/1_000_000.0;
        if(type == 0){
            System.err.println("Executing time of the sorting algorithm: " +  timeInSec);
            System.err.println("Amount of swaps: " +  swaps);
            System.err.println("Amount of comparisons: " +  comparisons);
            System.out.println("Amount of sorted keys: " + size);
        }else{
            String myTime = Double.toString(timeInSec);
            myTime = myTime.replace(".", ",");
            myFileWriter.print(size + ";" + swaps + ";" + comparisons + ";" + myTime);
        }
    }

    /**
     * Method which prints the feedback to user about running program with wrong input
     */

    private static void wrongParameters(){
        System.out.println("You run program with wrong parameters, please try again.");
    }

    /**
     * Method which prepare the Array from the parameters given by the user
     * @param keyboard - scanner
     * @return - array prepared to sort
     */

    private static int[] prepareArray(Scanner keyboard){
        System.out.println("Enter size of the array you'd like to sort");
        int amount = keyboard.nextInt();
        keyboard.nextLine();
        int [] userSortArray = new int[amount];
        for(int i = 0; i<amount; i++){
            userSortArray[i] = keyboard.nextInt();
        }
        return userSortArray;
    }

    /**
     * Method which checks whether the algorithm worked correctly
     * @param inputArray - the given array
     * @param type - type of the sorting
     * @return - true if is correct and false if not
     */

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

    /**
     * Method which prepares the array filled with the random numbers
     * @param size - size of array
     * @return - array prepared to sort
     */

    private static int[] prepareRandomArray(int size){
        int[] myRandomArray = new int[size];
        for(int i=0; i<size; i++){
            myRandomArray[i] = new Random().nextInt(1178);
        }
        return myRandomArray;
    }

    /**
     * Methods which prepares the array and variables to sort array in ascending order by Select Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareSelectInc(int[] input, int type){
        selectSortswaps = 0;
        selectSortcomparisions = 0;
        beginning = System.nanoTime();
        increasingSelectionSort(input);
        end = System.nanoTime();
        printSummary(selectSortswaps, selectSortcomparisions, end-beginning, type, input.length);
        if(checkIfCorrect(input, "asc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in descending order by Select Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareSelectDec(int [] input, int type){
        selectSortswaps = 0;
        selectSortcomparisions = 0;
        beginning = System.nanoTime();
        decreasingSelectionSort(input);
        end = System.nanoTime();
        printSummary(selectSortswaps, selectSortcomparisions, end-beginning, type, input.length);
        if(checkIfCorrect(input, "desc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in ascending order by Insert Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareInsertInc(int[] input, int type) {
        insertSortswaps = 0;
        insertSortcomparisons = 0;
        beginning = System.nanoTime();
        increasingInsertionSort(input);
        end = System.nanoTime();
        printSummary(insertSortswaps, insertSortcomparisons, end-beginning, type, input.length);
        if(checkIfCorrect(input, "asc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in descending order by Insert Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareInsertDesc(int[] input, int type) {
        insertSortswaps = 0;
        insertSortcomparisons = 0;
        beginning = System.nanoTime();
        decreasingInsertionSort(input);
        end = System.nanoTime();
        printSummary(insertSortswaps, insertSortcomparisons, end-beginning, type, input.length);
        if(checkIfCorrect(input, "desc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in ascending order by Quick Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareQuickInc(int[] input, int type) {
        quickSortcomparisons = 0;
        quickSortswaps = 0;
        beginning = System.nanoTime();
        increasingQuickSort(input, 0, input.length-1);
        end = System.nanoTime();
        printSummary(quickSortswaps, quickSortcomparisons, end-beginning, type, input.length);
        if(checkIfCorrect(input, "asc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in descending order by Quick Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareQuickDesc(int[] input, int type) {
        quickSortcomparisons = 0;
        quickSortswaps = 0;
        beginning = System.nanoTime();
        decreasingQuickSort(input, 0, input.length-1);
        end = System.nanoTime();
        printSummary(quickSortswaps, quickSortcomparisons, end-beginning, type, input.length);
        if(checkIfCorrect(input, "desc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in ascending order by Heap Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareHeapInc(int[] input, int type) {
        heapSortcomparisions = 0;
        heapSortswaps = 0;
        beginning = System.nanoTime();
        increasingHeapSort(input);
        end = System.nanoTime();
        printSummary(heapSortswaps, heapSortcomparisions, end-beginning, type, input.length);
        if(checkIfCorrect(input, "asc") && type==0){
            printArray(input);
        }
    }

    /**
     * Methods which prepares the array and variables to sort array in descending order by Heap Sort
     * @param input - the given array
     * @param type - type of program (1 for the --stat and 0 for the other)
     */

    private static void prepareHeapDesc(int[] input, int type) {
        heapSortcomparisions = 0;
        heapSortswaps = 0;
        beginning = System.nanoTime();
        decreasingHeapSort(input);
        end = System.nanoTime();
        printSummary(heapSortswaps, heapSortcomparisions, end-beginning, type, input.length);
        if(checkIfCorrect(input, "desc") && type==0){
            printArray(input);
        }
    }

    public static void main(String[] args) {
        int[] myinput;
        int[] inputCopy;
        fileName = "test.txt";
        int k;
        Scanner keyboard = new Scanner(System.in);

        if(args.length <=0){
            System.out.println("You must run the program with the following parameters: ");
            System.out.println("--type select|insert|heap|quick --asc|--desc");
        }
        else if(args[0].equals("--stat")){
            try {
                fileName = args[1];
                myFileWriter = new PrintWriter(fileName);
                try{
                    k = Integer.parseInt(args[2]);
                    for(int i = 100; i<=10000; i=i+100){
                        for(int j=0; j<k; j++ ){
                            myinput = prepareRandomArray(i);
                            inputCopy = myinput.clone();
                            prepareInsertDesc(inputCopy, 1);
                            inputCopy = myinput.clone();
                            prepareQuickDesc(inputCopy, 1);
                            inputCopy = myinput.clone();
                            prepareSelectDec(inputCopy, 1);
                            inputCopy = myinput.clone();
                            prepareHeapDesc(inputCopy, 1);
                            myFileWriter.println();
                        }
                    }
                    myFileWriter.close();
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(args[0].equals("--type")){
            switch(args[1]){
                case "select":
                    if(args[2].equals("--asc")){
                        myinput = prepareArray(keyboard);
                        prepareSelectInc(myinput, 0);

                    }else if(args[2].equals("--desc")){
                        myinput = prepareArray(keyboard);
                        prepareSelectDec(myinput, 0);
                    }
                    else
                        wrongParameters();
                    break;
                case "insert":
                    if(args[2].equals("--asc")){
                        myinput = prepareArray(keyboard);
                        prepareInsertInc(myinput, 0);
                    }else if(args[2].equals("--desc")){
                        myinput = prepareArray(keyboard);
                        prepareInsertDesc(myinput, 0);
                    }
                    else
                        wrongParameters();
                    break;
                case "heap":
                    if(args[2].equals("--asc")){
                        myinput = prepareArray(keyboard);
                        prepareHeapInc(myinput, 0);
                    }else if(args[2].equals("--desc")){
                        myinput = prepareArray(keyboard);
                        prepareHeapDesc(myinput, 0);
                    }
                    else
                        wrongParameters();
                    break;
                case "quick":
                    if(args[2].equals("--asc")){
                        myinput = prepareArray(keyboard);
                        prepareQuickInc(myinput, 0);
                    }else if(args[2].equals("--desc")){
                        myinput = prepareArray(keyboard);
                        prepareQuickDesc(myinput, 0);
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
