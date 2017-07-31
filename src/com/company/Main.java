package com.company;

//Pavlo Khryshcheniuk gr7
//package com.company;

import java.util.Scanner;


class Node {

    int length;
    int data;
    int numofarray;
    int nextIndex;

}

class Heap {


    private int size;
    private Node[] minheap;


    public Heap(Node[] array, int size) {
        this.size = size;
        minheap = array;
        int i = (this.size - 1) / 2;
        while (i >= 0) {
            Heapify(i);
            i--;
        }
    }


    private void Heapify(int i) {
        int r = right(i);
        int l = left(i);

        int minimum = i;
        if (l < size && minheap[l].data < minheap[i].data)
            minimum = l;
        if (r < size && minheap[r].data < minheap[minimum].data)
            minimum = r;
        if (minimum != i) {
            swap(minheap, i, minimum);
            Heapify(minimum);
        }
    }


    public void replaceMin(Node x) {
        minheap[0] = x;
        Heapify(0);
    }


    private void swap(Node[] a, int i, int j) {
        Node temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public Node getRoot() {
        return minheap[0];
    }


    private int right(int i) {
        return (2 * i + 2);
    }


    private int left(int i) {
        return (2 * i + 1);
    }


}

class Sort {
    public static StringBuilder str;

    public static void kwaymerge(int[][] arr, int totalLen) {
        Node[] mhn = new Node[arr.length];

        for (int i = 0; i < arr.length; i++) {
            mhn[i] = new Node();
            mhn[i].data = arr[i][0];
            mhn[i].numofarray = i;
            mhn[i].nextIndex = 1;
            mhn[i].length = arr[i].length;
        }

        Heap mh = new Heap(mhn, arr.length);

        for (int count = 0; count < totalLen; count++) {
            Node root = mh.getRoot();
            str.append(root.data).append(" ");

            if (root.nextIndex < root.length) {
                root.data = arr[root.numofarray][root.nextIndex];
                root.nextIndex += 1;
            } else
                root.data = Integer.MAX_VALUE;

            mh.replaceMin(root);
        }
    }

}

public class Main {

    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        int reps = input.nextInt();

        for (int i = 0; i < reps; i++) {
            int numofarrays = input.nextInt(); // number of arrays
            int[] arrayofsizes = new int[numofarrays];
            int[][] arrays = new int[numofarrays][];


            int resultlength = 0;


            for (int j = 0; j < numofarrays; j++) {
                arrayofsizes[j] = input.nextInt();
                resultlength += arrayofsizes[j];
            }

            for (int j = 0; j < numofarrays; j++) {
                arrays[j] = new int[arrayofsizes[j]];
                for (int el = 0; el < arrayofsizes[j]; el++)
                    arrays[j][el] = input.nextInt();
            }


            Sort merge = new Sort();
            Sort.str = new StringBuilder(100);
            Sort.kwaymerge(arrays, resultlength);
            System.out.println(merge.str.toString());
        }
    }

}







