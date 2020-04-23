package com.jersey.practicamodel;

import android.util.Log;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void twoSum() {
        int nums[] = new int[]{2, 7, 12, 10};
        twoSum(nums, 9);
    }

    public void twoSum(int[] nums, int target) {

        String result = String.valueOf(234);
        StringBuilder builder = new StringBuilder();
        int startIndex = result.length();
        for(int i = 0;i<result.length();i++){
            if(startIndex<=0) {
                break;
            }
            builder.append(result.substring(startIndex-1,startIndex));
            startIndex--;
        }


        System.out.println("result---" + builder.toString() + "\n");

    }
}