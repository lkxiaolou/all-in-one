package org.newboo.leetcode;

public class BaiqianBaiji {

    /**
     * 百钱买百鸡：公鸡5钱一只，母鸡3钱一只，小鸡1钱3只
     * 5x + 3y + z/3 = 100 => 0 < x < 20 , 0 < y <= 33
     * x + y + z = 100 => 3x + 3y + 3z = 300
     * =>
     * 2x - 8/3z = -200 => 8/3z - 2x = 200 => z = (200 + 2x ) 3 / 8 = 75 + 3x/4 => x 必须是4的倍数
     * y = 100 - x - 75 - 3x/4 = 25 - 7x/4
     */
    public static void main(String[] args) {
        for (int x = 1; x < 20; x++) {
            if (x % 4 == 0) {
                int y = (25 - 7 * x / 4);
                if (y > 0 && y <= 33) {
                    System.out.println(" x = " + x + "; y = " + y + "; z = " + (100 - x - y));
                }
            }
        }
    }


}
