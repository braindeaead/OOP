package src;
import java.io.*;
import java.util.*;

class Methods {

    static Scanner scan(String name) throws Exception {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(name));
            return scan;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: such file does not exist.");
            System.exit(1); // better to exit in main()
        }
        return scan;
    }

    private static IniParser.Category categGet(String categ, ArrayList<IniParser.Category> categories) throws Exception {
        int numberCateg = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).name.equals(categ)) {
                numberCateg = i;
            }
        }
        return categories.get(categories.indexOf(numberCateg));
    }

    private static int numberType (IniParser.Category category, String type) {
        int numberType = -1;
        for (int i = 0; i < category.pairs.size(); i++) {
            String current = String.valueOf(category.pairs.get(i));
            if (current.contains(type)) { // exception if it doesn't contain
                numberType = i;
                break;
            }
        }

        return numberType;
    }

    private static String replaceSev(char[] symbols, String line) {
        for (char chars : symbols) {
            line = line.replace(String.valueOf(chars), "");
        }
        return line;
    }

    private static boolean categoryChecker(String name, ArrayList<IniParser.Category> categories) {
        for (int i = 0; i < categories.size(); i++)
            if (categories.get(i).name.equals(name))
                return true;

        return false;
    }

    private static IniParser.Category getByName(String name, ArrayList<IniParser.Category> catergories) {
        IniParser.Category requested = null;
        for (int i = 0; i < catergories.size(); i++) {
            if (catergories.get(i).name.equals(name))
                requested = catergories.get(i);
        }

        return requested;
    }

    static String getString(ArrayList<IniParser.Category> categories, String categ, String type) throws Exception {
        IniParser.Category currentCateg = categGet(categ, categories);

        return String.valueOf(currentCateg.pairs.get(numberType(currentCateg, type)).get(type));
    }

    static Double getDouble(ArrayList<IniParser.Category> categories, String categ, String type) throws Exception {
        IniParser.Category currentCategory = categGet(categ, categories);

        try { Double.parseDouble(currentCategory.pairs.get(numberType(currentCategory, type)).get(type)); }
        catch (Exception e) {
            System.out.println("Error: wrong type.");
        }

        return Double.parseDouble(currentCategory.pairs.get(numberType(currentCategory, type)).get(type));
    }

    static Integer getInteger(ArrayList<IniParser.Category> categories, String categ, String type) throws Exception {
        IniParser.Category currentCategory = categGet(categ, categories);

        try { Integer.parseInt(currentCategory.pairs.get(numberType(currentCategory, type)).get(type)); }
        catch (Exception e) {
            System.out.println("Error: wrong type.");
        }

        return Integer.parseInt(String.valueOf(currentCategory.pairs.get(numberType(currentCategory, type)).get(type)));
    }

    static ArrayList<IniParser.Category> parser(Scanner file) {
        ArrayList<IniParser.Category> categories = new ArrayList<>();
        String current = file.nextLine();
        while (file.hasNextLine()) {
            char[] characters = current.toCharArray();
            if (characters[0] == '[') {
                String name = new String(characters);
                name = replaceSev(new char[]{'[', ']'}, name);
                IniParser.Category currentCategory;
                if (categoryChecker(name, categories)) {
                    currentCategory = getByName(name, categories);
                }
                else {
                    currentCategory = new IniParser.Category(name);
                }
                current = file.nextLine();
                while (current.toCharArray()[0] != '[') {
                    String[] now = current.split("\\s+");
                    currentCategory.pairs.add(new HashMap<>());
                    HashMap<String, String> map = new HashMap<>();
                    map.put(now[0], now[2]);
                    currentCategory.pairs.add(map);
                    if (file.hasNextLine()) {
                        current = file.nextLine();
                    }
                    else break;
                }
                categories.add(currentCategory);
            }

        }
        return categories;
    }

}
