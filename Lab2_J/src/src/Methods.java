package src;
import java.util.ArrayList;
import java.util.Scanner;

class Methods {
    // make as a manager
    static String[] consoleSplitter(Scanner in, int count) {
        String[] splitted = new String[count];

        for (int i = 0; i < count; i++) {
            splitted[i] = in.next();

        }
        return splitted;
    }

    static void consignment(ArrayList<Shops.Shop> shops, Shops.Product prod, Shops.Shop shop, int amount, int price) {
        shops.get(shops.indexOf(shop)).catalog.add(new Shops.Products(prod, amount, price));
    }

    static Shops.Shop getShop(ArrayList<Shops.Shop> shops, int code) {
        Shops.Shop requested = null;

        for (Shops.Shop shop : shops) {
            if (shop.code == code) {
                requested = shop;
                break;
            }
        }
        return requested;
    }

    static Shops.Product getProd (ArrayList<Shops.Product> prods, int code) {
        Shops.Product requested = null;

        for (Shops.Product prod : prods) {
            if (prod.code == code) {
                requested = prod;
                break;
            }
        }
        return requested;
    }

    static Shops.Shop findCheapest(Shops.Product prod, ArrayList<Shops.Shop> shops) { // same as consignment
        Shops.Shop coolest = null;
        int price = Integer.MAX_VALUE;

        for (int i = 0; i < shops.size(); i++) {
            for (int k = 0; k < shops.get(i).catalog.size(); k++) {
                if (shops.get(i).catalog.get(k).price < price) {
                    price = shops.get(i).catalog.get(k).price;
                    coolest = shops.get(i);
                }
            }
        }
        return coolest;
    }

    static Shops.Shop findConsignment(ArrayList<Shops.Shop> shops, Shops.Product prod, int amount) { //remake as a part of a Shop method
        Shops.Shop requested = null;
        int price = Integer.MAX_VALUE;

        for (Shops.Shop shop : shops) {
            if (shop.catalog.contains(shop.findProds(prod))) {
                if (shop.catalog.get(shop.catalog.indexOf(shop.findProds(prod))).amount >= amount && shop.catalog.get(shop.catalog.indexOf(shop.findProds(prod))).price < price) {
                    requested = shop;
                    price = shop.catalog.get(shop.catalog.indexOf(shop.findProds(prod))).price;
                }
            }
        }
        return requested;
    }
}
