/**
 * This program acts as shopping experience where all the input comes from the command line. The program checks to make
 * sure that the input is legal before it adds the item to the cart, while also avoiding any runtime errors and crashing
 * of the program. The program creates a store, then creates a cart by filling it with the inputs given in the command
 * line. After creating a cart the program prints the receipt in the order it was filled with the command line inputs.
 * This method also prints the price before and after tax, as well as printing the tax. Then the program empties the cart
 * in reverse order while also printing the items being removed.
 * </p>
 * Maggie Cowher
 * January 20, 2022
 */

import java.util.ArrayList; //import ArrayList

// Lab1 is a class full of methods used to execute the shopping experience that are called in the main method
public class Lab1 {
    // The setupStore method initializes and declares an array of Item objects to fill the store
    public static Item[] setupStore() {
        Item[] store = new Item[6];
        store[0] = new Item("Tofu", 4.99);
        store[1] = new Item("Spinach", 1.99);
        store[2] = new Item("Tomatoes", 2.99);
        store[3] = new Item("Feta", 2.50);
        store[4] = new Item("Croutons", 0.99);
        store[5] = new Item("Vinaigrette", 3.50);
        return store;
    }

    // The createCart method fills the cart with Item objects given in the command line.
    // The method also makes sure that the inputs given in the command line are both integers and within the index
    public static ArrayList<Item> createCart(String[] args, Item[] store) {
        ArrayList<Item> cart = new ArrayList<>();
        for (String arg : args) {
            try {
                cart.add(store[(Integer.parseInt(arg))]); // Turns string variable into an integer and adds to cart
            } catch (ArrayIndexOutOfBoundsException a) { // Exception if the input is out of bounds of the array
                System.out.println("The store does not have an item of index " + arg);
            } catch (NumberFormatException b) { // Exception if the input is not an integer
                System.out.println("\"" + arg + "\" is not a valid integer");
            } catch (Exception c) { // Default exception
                System.out.println("No valid Input to cart");
            }
        }
        return cart;
    }

    // The printReceiptInOrder method prints the receipt using the cart filled in the createCart method
    // The method also prints the subtotal before tax, the sales tax, and the total after adding tax
    public static void printReceiptInOrder(ArrayList<Item> cart) {
        double subtotal = 0;
        double total;
        System.out.println("Receipt");
        System.out.println("=====================");
        // printf is used to format the print statements as they print both double variables and strings
        System.out.printf("%-15s %s %n", "Item", "Price"); // printf is used for format spacing here
        for (Item item : cart) { // A for loop is used here to iterate through the cart ArrayList and print the items in the cart
            System.out.printf("%-15s %.2f %n", item.getItemName(), item.getItemPrice()); // Only prints price to two decimal places
            subtotal += item.getItemPrice(); // Adds the price of the current cart item to the subtotal, which is initialized at zero
        }
        System.out.println("====================="); // Formatting
        System.out.printf("%s %.2f %n", "(a) Subtotal: ", subtotal); // Only prints the subtotal to two decimal places
        total = subtotal * 1.05; // Adds sales tax to price
        System.out.printf("%s %n", "(b) Sales Tax: 5%");
        System.out.printf("%s %.3f %n", "(c) Total: ", total); // Only prints the new total to three decimal places
    }

    // The emptyCartReverseOrder method empties the cart, starting at the last item added and ending at the first
    // The method also prints the item being removed at the time
    public static void emptyCartReverseOrder(ArrayList<Item> cart) {
        System.out.println("Removing all items from the cart in \"Last In First Out\" order...");
        for (int i = (cart.size() - 1); i >= 0; i--) {
            System.out.println("Removing: " + cart.get(i).getItemName());
            cart.remove(i);
        }
        System.out.println("Cart has been emptied");
    }

    // The main method calls and executes the methods created earlier in the Lab1 class
    // Here the store is created, the cart is filled, the receipt is printed in order along with the subtotal, sales
    // tax, and total, and then the cart is emptied
    public static void main(String[] args) {
        Item[] store = setupStore();
        ArrayList<Item> cart = createCart(args, store);
        printReceiptInOrder(cart);
        emptyCartReverseOrder(cart);
    }
}