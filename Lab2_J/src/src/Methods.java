package src;

import java.util.Scanner;

class Methods {

    static class Result {

        Shops.Shop shop;
        int price;

        Result(Shops.Shop shop, int price) {
            this.shop = shop;
            this.price = price;
        }
    }

    static String[] consoleSplitter(Scanner in, int count) {
        String[] splitted = new String[count];

        for (int i = 0; i < count; i++) {
            splitted[i] = in.next();

        }
        return splitted;
    }
}

