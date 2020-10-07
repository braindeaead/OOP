import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class IniParser {
    public static class Category {
        public String name;
        public ArrayList<HashMap<String, String>> pairs = new ArrayList<>();

        public Category(String name) {
            this.name = name;
        }
    }

    public static Scanner scan(String name) throws Exception {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(name));
            return scan;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: such file does not exist.");
        }
        return scan;
    }

    public static String replaceSev(char[] symbols, String line) {
        for (char chars : symbols) {
            line = line.replace(String.valueOf(chars), "");
        }
        return line;
    }

    public static ArrayList<Category> parser(Scanner file) {
        ArrayList<Category> categories = new ArrayList<>();
        String current = file.nextLine();
        while (file.hasNextLine()) {
            char[] characters = current.toCharArray();
                if (characters[0] == '[') {
                    String name = new String(characters);
                    name = replaceSev(new char[]{'[', ']'}, name);
                    Category currentCategory;
                    if (categories.contains(name)) {
                        currentCategory = categories.get(categories.indexOf(name));
                    }
                    else {
                        currentCategory = new Category(name);
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

    public static String get(ArrayList<Category> categories, String categ, String type) throws Exception { // make get() for different types
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

      return String.valueOf(categories.get(numberCateg).pairs.get(numberType).get(type));
    };

    public static void main(String[] args) throws Exception {
        if (!args[0].contains(".ini")) throw new Exception("Error: wrong file format.");
        Scanner file = scan(args[0]);
        ArrayList<Category> categories = parser(file);
        System.out.println("Name category and type which value you want to know.");
        Scanner in = new Scanner(System.in);
        String categ = in.nextLine();
        String type = in.nextLine();
        System.out.println(get(categories, categ, type));
    }
}
