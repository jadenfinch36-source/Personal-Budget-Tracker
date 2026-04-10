import java.util.ArrayList;
import java.util.Scanner;

public class BudgetManager {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n==============================");
            System.out.println("        BUDGET MANAGER        ");
            System.out.println("==============================\n");

System.out.println("1. Add Expense");
System.out.println("2. View All Expenses");
System.out.println("3. View Total Spending");
System.out.println();

System.out.println("4. Save Expenses to File");
System.out.println("5. Load Expenses from File");
System.out.println();

System.out.println("6. Exit");
System.out.println("7. View Spending by Category");
System.out.println("8. Search Expenses");
System.out.println("9. Delete an Expense");
System.out.println();

System.out.println("10. Clear All Expenses");
System.out.println("11. Sort by Amount");
System.out.println("12. Sort by Category");
System.out.println("13. Sort by Description");
System.out.println();

            
            int choice = -1;
boolean validChoice = false;

while (!validChoice) {
    try {
        System.out.print("Choose an option: ");
        choice = Integer.parseInt(input.nextLine());
        validChoice = true;
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
    }
}

          switch (choice) {
    case 1:
        addExpense();
        break;
    case 2:
        viewExpenses();
        break;
    case 3:
        viewTotal();
        break;
    case 4:
        saveToFile();
        break;
    case 5:
        loadFromFile();
        break;
    case 6:
        running = false;
        break;
                  case 7:
    viewCategoryTotals();
    break;
                  case 8:
    searchExpenses();
    break;
                  case 9:
    deleteExpense();
    break;
                  case 10:
    clearAllExpenses();
    break;
                  case 11:
    sortByAmount();
    break;
case 12:
    sortByCategory();
    break;
case 13:
    sortByDescription();
    break;
    default:
        System.out.println("Invalid choice.");
}
            }
        }
    }

   double amount = 0;
boolean valid = false;

while (!valid) {
    try {
        System.out.print("Enter amount: ");
        amount = Double.parseDouble(input.nextLine());
        valid = true;
    } catch (NumberFormatException e) {
        System.out.println("Invalid number. Please try again.");
    }
}


        System.out.print("Enter category: ");
        String category = input.nextLine();

        System.out.print("Enter description: ");
        String description = input.nextLine();

        expenses.add(new Expense(amount, category, description));
        System.out.println("Expense added!");
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\n=== All Expenses ===");

        for (Expense e : expenses) {
            System.out.println(e);
        }
        System.out.println();
    }

    private void viewTotalSpending() {
        System.out.println("\n=== Total Spending ===");
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total: $" + total);
    System.out.println();

    }
}
private void saveToFile() {
    try {
        java.io.PrintWriter writer = new java.io.PrintWriter("data/expenses.txt");

        for (Expense e : expenses) {
            writer.println(e.getAmount() + "," + e.getCategory() + "," + e.getDescription());
        }

        writer.close();
        System.out.println("Expenses saved to data/expenses.txt");
    } catch (Exception e) {
        System.out.println("Error saving file.");
    }
}
private void loadFromFile() {
    try {
        java.io.File file = new java.io.File("data/expenses.txt");
        java.util.Scanner fileReader = new java.util.Scanner(file);

        expenses.clear();

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(",");

            double amount = Double.parseDouble(parts[0]);
            String category = parts[1];
            String description = parts[2];

            expenses.add(new Expense(amount, category, description));
        }

        fileReader.close();
        System.out.println("Expenses loaded from file.");
    } catch (Exception e) {
        System.out.println("Error loading file.");
    }
}
private void viewCategoryTotals() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses recorded.");
        return;
    }
System.out.println("\n=== Spending by Category ===");
    java.util.HashMap<String, Double> totals = new java.util.HashMap<>();

    for (Expense e : expenses) {
        String cat = e.getCategory();
        double amt = e.getAmount();

        totals.put(cat, totals.getOrDefault(cat, 0.0) + amt);
    }

    System.out.println("\n=== Spending by Category ===");
    for (String cat : totals.keySet()) {
        System.out.printf("%s: $%.2f\n", cat, totals.get(cat));
        System.out.println();

    }
}
private void searchExpenses() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses recorded.");
        return;
    }

    System.out.print("Enter search keyword (category or description): ");
    String keyword = input.nextLine().toLowerCase();

    boolean found = false;

    System.out.println("\n=== Search Results ===");
    for (Expense e : expenses) {
        if (e.getCategory().toLowerCase().contains(keyword) ||
            e.getDescription().toLowerCase().contains(keyword)) {

            System.out.println(e);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No matching expenses found.");
    }
}
private void deleteExpense() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to delete.");
        return;
    }

    System.out.println("\n=== Expenses ===");
    for (int i = 0; i < expenses.size(); i++) {
        System.out.println((i + 1) + ". " + expenses.get(i));
    }

    int index = -1;
    boolean valid = false;

    while (!valid) {
        try {
            System.out.print("Enter the number of the expense to delete: ");
            index = Integer.parseInt(input.nextLine()) - 1;

            if (index >= 0 && index < expenses.size()) {
                valid = true;
            } else {
                System.out.println("Invalid number. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter a number.");
        }
    }

    Expense removed = expenses.remove(index);
    System.out.println("Deleted: " + removed);
}
private void clearAllExpenses() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to clear.");
        return;
    }

    System.out.print("Are you sure you want to delete ALL expenses? (yes/no): ");
    String confirm = input.nextLine().trim().toLowerCase();

    if (confirm.equals("yes")) {
        expenses.clear();
        System.out.println("All expenses have been cleared.");
    } else {
        System.out.println("Clear operation canceled.");
    }
}
private void sortByAmount() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to sort.");
        return;
    }

    java.util.Collections.sort(expenses, new java.util.Comparator<Expense>() {
        @Override
        public int compare(Expense e1, Expense e2) {
            return Double.compare(e1.getAmount(), e2.getAmount());
        }
    });

    System.out.println("\n=== Expenses Sorted by Amount ===");
    for (Expense e : expenses) {
        System.out.println(e);
    }
}
private void sortByCategory() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to sort.");
        return;
    }

    java.util.Collections.sort(expenses, new java.util.Comparator<Expense>() {
        @Override
        public int compare(Expense e1, Expense e2) {
            return e1.getCategory().compareToIgnoreCase(e2.getCategory());
        }
    });

    System.out.println("\n=== Expenses Sorted by Category ===");
    for (Expense e : expenses) {
        System.out.println(e);
    }
}
private void sortByDescription() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to sort.");
        return;
    }

    java.util.Collections.sort(expenses, new java.util.Comparator<Expense>() {
        @Override
        public int compare(Expense e1, Expense e2) {
            return e1.getDescription().compareToIgnoreCase(e2.getDescription());
        }
    });

    System.out.println("\n=== Expenses Sorted by Description ===");
    for (Expense e : expenses) {
        System.out.println(e);
    }
}
