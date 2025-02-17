package sort;

import java.util.ArrayList;
import java.util.Collections;

public class Sort{
    public static void main(String[] args) {

        double []a = new double[10];
        for(int i = 0; i < a.length; i++)   a[i] = Math.random() * 100;

        System.out.println("Bubble sort: ");
        new Sort().BubbleSort(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Selection sort: ");
        new Sort().SelectionSort(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Insertion sort: ");
        new Sort().InsertionSort(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Insertion sort(binary search optimized): ");
        new Sort().InsertionSort_binarySearch_optimize(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Merge sort: ");
        new Sort().MergeSort(a, 0, a.length - 1);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Quick sort: ");
        new Sort().QuickSort(a, 0, a.length - 1);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Heap sort: ");
        new Sort().HeapSort(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

        System.out.println("Counting sort: ");
        int[] b = new int[a.length];
        for(int i = 0; i < a.length; i++)   b[i] = (int)a[i];
        new Sort().CountingSort(b);
        for(int i = 0; i < b.length; i++)   System.out.printf("%d ", b[i]);
        System.out.println();

        System.out.println("Radix sort: ");
        new Sort().RadixSort(b);
        for(int i = 0; i < b.length; i++)   System.out.printf("%d ", b[i]);
        System.out.println();

        System.out.println("Binary Radix sort: ");
        new Sort().BinaryRadixSort(b);
        for(int i = 0; i < b.length; i++)   System.out.printf("%d ", b[i]);
        System.out.println();

        System.out.println("Bucket sort: ");
        new Sort().HeapSort(a);
        for(int i = 0; i < a.length; i++)   System.out.printf("%.2f ", a[i]);
        System.out.println();

    }


    //bubble sort  o(n^2)
    void BubbleSort(double []a){
        for(int i = 0; i < a.length - 1; i++){
            for(int j = 0; j < a.length - 1 - i; j++){
                if(a[j] > a[j + 1]){
                    double tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    //selection sort    o(n^2)
    void SelectionSort(double []a){
        for(int i = 0; i < a.length; i++){
            int minidx = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[minidx])    minidx = j;
            }
            double tmp = a[minidx];
            a[minidx] = a[i];
            a[i] = tmp; 
        }
    }

    //insertion sort    o(n^2)
    void InsertionSort(double []a){
        for(int j = 1; j < a.length; j++){
            int i = j - 1;
            double key = a[j];
            while(i > 0 && a[i] > key){
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
    }

    //insertion sort optimized by binary search     o(nlogn)
    int binarySearch(double []a, double x, int l, int r){  
        while(l <= r){
            int mid = (l + r) >> 1;
            if(a[mid] > x) r = mid;
            else    l = mid + 1;
        }
        return l;
    }
    void InsertionSort_binarySearch_optimize(double []a){
        for(int j = 1; j < a.length; j++){
            double key = a[j];
            int idx = binarySearch(a, key, 0, j - 1);
            int i = j - 1;
            while(i >= idx){
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
    }

    //merge sort   o(nlogn)
    void Merge(double[] a, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - mid;
        double[] L = new double[n1 + 1];
        double[] R = new double[n2 + 1];
        for(int i = 0; i < n1; i++) L[i] = a[l + i];
        for(int j = 0; j < n2; j++) R[j] = a[mid + 1 + j];
        L[n1] = Double.POSITIVE_INFINITY;
        R[n2] = Double.POSITIVE_INFINITY;
        for(int i = 0, j = 0, k = l; k <= r; k++){
            if(L[i] < R[j]) a[k] = L[i++];
            else    a[k] = R[j++];
        }
    }
    void MergeSort(double[] a, int l, int r){
        if(l < r){
            int mid = (l + r) >> 1;
            MergeSort(a, l, mid);
            MergeSort(a, mid + 1, r);
            Merge(a, l, mid, r);
        }
    }

    //quick sort    o(nlogn)
    void QuickSort(double[] a, int l, int r){
        if(l < r){
            int pivotIdx = partition(a, l, r);
            QuickSort(a, l, pivotIdx - 1);
            QuickSort(a, pivotIdx + 1, r);
        }
    }
    int partition(double[] a, int l, int r){
        double pivot = a[r];
        int i = l - 1;
        for(int j = l; j < r; j++){
            if(a[j] < pivot){
                i++;
                double tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        double tmp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = tmp;
        return i + 1;
    }

    //heap sort     o(nlogn)
    void HeapSort(double[] a){
        int n = a.length;
        for(int i = n / 2 - 1; i >= 0; i--){
            maxHeapify(a, n, i);
        }
        for(int i = n - 1; i > 0; i--){
            double tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            maxHeapify(a, i, 0);
        }
    }
    void maxHeapify(double[] a, int heapSize, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if(l < heapSize && a[l] > a[largest])   largest = l;
        if(r < heapSize && a[r] > a[largest])   largest = r;
        if(i != largest){
            double tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;
            maxHeapify(a, heapSize, largest);
        }
    }

    // counting sort    o(n)
    void CountingSort(int []a){
        int max = find_max(a);
        int[] C = new int[max + 1];
        int[] B = new int[a.length];
        for(int i = 0; i < C.length; i++)   C[i] = 0;
        for(int i = 0; i < a.length; i++)   C[a[i]]++;
        for(int i = 1; i < C.length; i++)   C[i] += C[i - 1];
        for(int i = a.length - 1; i >= 0; i--){
            B[C[a[i]] - 1] = a[i];
            C[a[i]]--;
        }
        for(int i = 0; i < a.length; i++)   a[i] = B[i];
    }
    int find_max(int[] a){
        int max = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > max)  max = a[i];
        }
        return max;
    }

    // Radix sort   o(n)
    void RadixSort(int []a){
        int max = findMax(a);
        int d = getDigitCount(max);
        int exp = 1;
        for(int i = 0; i < d; i++){
            CountingSortBydigit(a, exp);
            exp *= 10;
        }
    }
    int findMax(int[] a){
        int max = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > max)  max = a[i];
        }
        return max;
    }
    int getDigitCount(int num){
        int count = 0;
        while(num > 0){
            count++;
            num /= 10;
        }
        return count;
    }
    void CountingSortBydigit(int[] a, int exp){
        int[] C = new int[10];
        int[] B = new int[a.length];
        for(int i = 0; i < C.length; i++)   C[i] = 0;
        for(int i = 0; i < a.length; i++){
            int digit = (a[i] / exp) % 10;
            C[digit]++;
        }
        for(int i = 1; i < C.length; i++)   C[i] += C[i - 1];
        for(int i = a.length - 1; i >= 0; i--){
            int digit = (a[i] / exp) % 10;
            B[C[digit] - 1] = a[i];
            C[digit]--;
        }
        for(int i = 0; i < a.length; i++)   a[i] = B[i];
    }

    //Binary Radix sort     o(nlogn)
    void BinaryRadixSort(int[] a){
        int max = find_Max(a);
        int d = getDigitCount2(max);
        int bitIdx = 0;
        for(int i = 0; i < d; i++){
            CountingSortBydigit2(a, bitIdx);
            bitIdx++;
        }
    }
    int find_Max(int[] a){
        int max = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > max)  max = a[i];
        }
        return max;
    }
    int getDigitCount2(int num){
        int count = 0;
        while(num > 0){
            count++;
            num /= 2;
        }
        return count;
    }
    void CountingSortBydigit2(int[] a, int bitIdx){
        int[] C = new int[2];
        int[] B = new int[a.length];
        for(int i = 0; i < C.length; i++)   C[i] = 0;
        for(int i = 0; i < a.length; i++){
            int bit = (a[i] >> bitIdx) & 1;
            C[bit]++;
        }
        C[1] += C[0];
        for(int i = a.length - 1; i >= 0; i--){
            int bit = (a[i] >> bitIdx) & 1;
            B[C[bit] - 1] = a[i];
            C[bit]--;
        }
        for(int i = 0; i < a.length; i++)   a[i] = B[i];
    }

    void BucketSort(double []a){
        int n = a.length;
        ArrayList<ArrayList<Double>> B = new ArrayList<>();
        for(int i = 0; i < n; i++)  B.add(new ArrayList<>());
        for(int i = 0; i < n; i++){
            int idx = (int)(a[i] * n);
            B.get(idx).add(a[i]);
        }
        for(int i = 0; i < n; i++){
            Collections.sort(B.get(i));
        }
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(double val : B.get(i)){
                a[idx++] = val;
            }
        }
    }
}