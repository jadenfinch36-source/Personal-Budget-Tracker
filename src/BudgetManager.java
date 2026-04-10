import java.util.ArrayList;
import java.util.Scanner;

public class BudgetManager {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Budget Tracker Menu ===");
            System.out.println("1. Add Expense");
System.out.println("2. View All Expenses");
System.out.println("3. View Total Spending");
System.out.println("4. Save Expenses to File");
System.out.println("5. Load Expenses from File");
System.out.println("6. Exit");
            System.out.println("7. View Spending by Category");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

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

        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private void viewTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.printf("Total spending: $%.2f\n", total);
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

    java.util.HashMap<String, Double> totals = new java.util.HashMap<>();

    for (Expense e : expenses) {
        String cat = e.getCategory();
        double amt = e.getAmount();

        totals.put(cat, totals.getOrDefault(cat, 0.0) + amt);
    }

    System.out.println("\n=== Spending by Category ===");
    for (String cat : totals.keySet()) {
        System.out.printf("%s: $%.2f\n", cat, totals.get(cat));
    }
}
