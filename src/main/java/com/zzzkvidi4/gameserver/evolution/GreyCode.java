package com.zzzkvidi4.gameserver.evolution;

import java.util.HashMap;
import java.util.Map;

public final class GreyCode {
    public static final int GREY_CODE_LENGTH = 8;
    private static Map<Integer, String> toBinaryMap = new HashMap<>();
    private static Map<String, Integer> toDecimalMap = new HashMap<>();

    static{
        int number = (int)Math.pow(2, GREY_CODE_LENGTH);
        boolean[][] greyCode = new boolean[number][GREY_CODE_LENGTH];
        greyCode[0][GREY_CODE_LENGTH - 1] = false;
        greyCode[1][GREY_CODE_LENGTH - 1] = true;
        int p = 2;
        for (int i = 2; i <= GREY_CODE_LENGTH; ++i){
            int t = p - 1;
            p *= 2;
            for (int k = (p / 2); k < p; ++k){
            //greyCode[k] = greyCode[t];
                System.arraycopy(greyCode[t], 0, greyCode[k], 0, GREY_CODE_LENGTH);
                greyCode[t][GREY_CODE_LENGTH - i] = false;
                greyCode[k][GREY_CODE_LENGTH - i] = true;
                --t;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number; ++i){
            for (int j = 0; j < GREY_CODE_LENGTH; ++j) {
                if (greyCode[i][j]) {
                    builder.append(1);
                } else {
                    builder.append(0);
                }
            }
            toBinaryMap.put(i, builder.toString());
            toDecimalMap.put(builder.toString(), i);
            builder.setLength(0);
        }
    }

    public static String toGrey(int value){
        return toBinaryMap.get(value);
    }

    public static int toDecimal(String grey){
        return toDecimalMap.get(grey);
    }

    public static void main(String[] args){
        System.out.println(toBinaryMap);
        System.out.println(toDecimalMap);
    }
}
