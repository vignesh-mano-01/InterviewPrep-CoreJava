package com.interview.programs;

import java.util.Arrays;

public class SmallestDifferencePair {
    public static void main(String[] args) {
        int input1[] = {1, 3, 15, 11, 2};
        int input2[] = {23, 127, 235, 19, 8};

        int output = solution(input1,input2);
        System.out.println(output);
    }

    private static int solution(int[] input1, int[] input2) {

        Arrays.sort(input1);
        Arrays.sort(input2);
        int min= Integer.MAX_VALUE;
        for(int i=0; i<input1.length; i++){
            for(int j=0; j<input2.length; j++){
                if(Math.abs(input1[i] - input2[j]) < min){
                    min = Math.abs(input1[i] - input2[j]);
                }
            }
        }

        return min;
    }
}
