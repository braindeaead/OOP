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

    private static int categNumber(String categ, ArrayList<IniParser.Category> categories) throws Exception {
        int numberCateg = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).name.equals(categ)) {
                numberCateg = i;
            }
        }
        return numberCateg;
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
        int numberType = -1;
        for (int i = 0; i < categories.get(categNumber(categ, categories)).pairs.size(); i++) {
            String current = String.valueOf(categories.get(categNumber(categ, categories)).pairs.get(i));
            if (current.contains(type)) {
                numberType = i;
                break;
            }
        }
        return String.valueOf(categories.get(categNumber(categ, categories)).pairs.get(numberType).get(type));
    }

    static Double getDouble(ArrayList<IniParser.Category> categories, String categ, String type) throws Exception {
        int numberCateg = -1;
        int size = categories.size();
        for (int k = 0; k < size; k++)
            if (categories.get(k).name.equals(categ))
                numberCateg = k;

        if (numberCateg == -1) throw new Exception("Error: such category does not exist");
        int numberType = -1;
        for (int i = 0; i < categories.get(numberCateg).pairs.size(); i++) {
            String current = String.valueOf(categories.get(numberCateg).pairs.get(i));
            if (current.contains(type)) {
                numberType = i;
                break;
            }
        }
        try { Double.parseDouble(categories.get(numberCateg).pairs.get(numberType).get(type)); }
        catch (Exception e) {
            System.out.println("Error: wrong type.");
        }

        return Double.parseDouble(categories.get(numberCateg).pairs.get(numberType).get(type));
    }

    static Integer getInteger(ArrayList<IniParser.Category> categories, String categ, String type) throws Exception {
        int numberCateg = -1;
        int size = categories.size();
        for (int k = 0; k < size; k++)
            if (categories.get(k).name.equals(categ))
                numberCateg = k;

        if (numberCateg == -1) throw new Exception("Error: such category does not exist");
        int numberType = -1;
        for (int i = 0; i < categories.get(numberCateg).pairs.size(); i++) {
            String current = String.valueOf(categories.get(numberCateg).pairs.get(i));
            if (current.contains(type)) {
                numberType = i;
                break;
            }
        }
            return Integer.parseInt(String.valueOf(categories.get(numberCateg).pairs.get(numberType).get(type)));
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
