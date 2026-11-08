package util.sorting;

import util.sorting.output.*;

import java.util.Arrays;
import java.util.Random;

public final class SortingAlgorithm {


    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }



    @SafeVarargs
    public static <Input> Input[] bubbleSort(Output<Input> m, Input... arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                switch (m){
                    case ByteOutput<Input> b->{
                        if (b.output(arr[j]) > b.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    case ShortOutput<Input> s->{
                        if (s.output(arr[j]) > s.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    case IntOutput<Input> i1->{
                        if (i1.output(arr[j]) > i1.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    case LongOutput<Input> l->{
                        if (l.output(arr[j]) > l.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    case FloatOutput<Input> f->{
                        if (f.output(arr[j]) > f.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    case DoubleOutput<Input> d->{
                        if (d.output(arr[j]) > d.output(arr[j + 1])) {
                            Input temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }
            }
        }
        return arr;
    }
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }




}
