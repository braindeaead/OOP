package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    static void menu(ArrayList<Shops.Shop> shops, ArrayList<Shops.Product> prod) throws IOException {
        System.out.println("What do u want to do?");
        System.out.println("1. Create a shop.");
        System.out.println("2. Create a product.");
        System.out.println("3. Make a consignment. ");
        System.out.println("4. Find a shop with a cheapest product.");
        System.out.println("5. Find out what you can buy with money you have.");
        System.out.println("6. Buy some amount of a product.");
        System.out.println("7. Find a shop where some amount of a product is the cheapest.");
        System.out.println("8. Exit.");

        Scanner in = new Scanner(System.in);
        int read = in.nextInt();
        String[] args;
        Shops.Shop currentShop;
        ArrayList<Shops.Products> forAvailable;

        switch (read) {
            case 1:
                System.out.println("Enter a code, name and address of a shop: ");
                args = Methods.consoleSplitter(in, 3);
                Methods.createShop(Integer.parseInt(args[0]), args[1], args[2], shops);
                System.out.println("Shop " + args[1] + " was successfully created.");
                menu(shops, prod);
            case 2:
                System.out.println("Enter a code and name: ");
                args = Methods.consoleSplitter(in, 2);
                Methods.createProd(Integer.parseInt(args[0]), args[1], prod);
                System.out.println("Product " + args[1] + " was successfully created.");
                menu(shops, prod);
            case 3:
                System.out.println("Enter product's code, shop's code, amount and a price.");
                args = Methods.consoleSplitter(in, 3);
                Methods.consignment(shops, Methods.getProd(prod, Integer.parseInt(args[0])), Methods.getShop(shops, Integer.parseInt(args[1])), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                System.out.println("Consignment has successfully been done.");
                menu(shops, prod);
            case 4:
                System.out.println("Enter a code of a product you want to find: ");
                args = Methods.consoleSplitter(in, 1);
                currentShop = Methods.findCheapest(Methods.getProd(prod, Integer.parseInt(args[0])), shops);
                System.out.println("The code of the shop is: " + currentShop.code + ". It's name is: " + currentShop.name + ". It's address is: " + currentShop.address + ".");
                menu(shops, prod);
            case 5:
                System.out.println("Enter how much money you have and a code of a shop: ");
                args = Methods.consoleSplitter(in, 2);
                forAvailable = Methods.whatAble(Integer.parseInt(args[0]), Methods.getShop(shops, Integer.parseInt(args[1])));
                System.out.println("Here is what and in what amount you can by with money you have: ");
                for (Shops.Products products : forAvailable) {
                    System.out.println("You can buy " + products.amount + products.prod.name + ".");
                }
                menu(shops, prod);
            case 6:
                System.out.println("Enter a code of a shop, code of a product and amount you want to buy: ");
                args = Methods.consoleSplitter(in, 3);
                System.out.println(Methods.buy(Methods.getShop(shops, Integer.parseInt(args[0])), Methods.getProd(prod, Integer.parseInt(args[1])), Integer.parseInt(args[2])));
                menu(shops, prod);
            case 7:
                System.out.println("Enter code of a product and it's amount: ");
                args = Methods.consoleSplitter(in, 2);
                currentShop = Methods.findConsignment(shops, Methods.getProd(prod, Integer.parseInt(args[0])), Integer.parseInt(args[1]));
                System.out.println("Code of a shop is: " + currentShop.code + ". Name of a shop is: " + currentShop.name + ". It's address is: " + currentShop.address + ".");
                menu(shops, prod);
            case 8:
                System.out.println("Bye.");
                break;
            default:
                System.out.println("Pick one of the mentioned options, please. ");
                menu(shops, prod);

        }
    }
}
