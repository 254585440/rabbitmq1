package com.example.rabbitmq.test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description
 * @author: Sam.Zhao
 * @date: 2021-08-04 09:13
 **/
public class MaoPaoTest {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 3, 1, 6,9,5};
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
