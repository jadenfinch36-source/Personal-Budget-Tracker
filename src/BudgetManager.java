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
            System.out.println("4. Exit");
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
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();

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
