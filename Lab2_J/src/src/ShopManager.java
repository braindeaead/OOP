package src;
import java.util.ArrayList;

class Manager {

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

        for (Shops.Shop shop : shops) {
            for (int k = 0; k < shop.catalog.size(); k++) {
                if (shop.catalog.get(k).price < price) {
                    price = shop.catalog.get(k).price;
                    coolest = shop;
                }
            }
        }
        return coolest;
    }

    static Shops.Shop findConsignment(ArrayList<Shops.Shop> shops, Shops.Product prod, int amount) {
        Shops.Shop requested = null;
        int price = Integer.MAX_VALUE;

        for (Shops.Shop shop : shops) {
            Methods.Result res = shop.cheapestConsignment(shops, prod, amount, requested, price);
            requested = res.shop;
            price = res.price;
        }

        return requested;
    }
}
