import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    Scanner scanner = new Scanner(System.in);
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void showBalance(){
        System.out.println(balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("Do you want to see Total Balance:");
            System.out.println("Yes");
            System.out.println("No");
            String action = scanner.next();
            if(action.equals("Yes")){
                System.out.println("Total Balance :" + balance);
            }
            
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Do you want to see Total Balance:");
            System.out.println("Yes");
            System.out.println("No");
            String action = scanner.next();
            if(action.equals("Yes")){
                System.out.println("Total Balance :" + balance);
            }
           
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
};


class Customer {
    private String name;
    private String id;
    private List<Account> accounts;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}

class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public void deposit(String accountNumber, double amount) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    account.deposit(amount);
                    return;
                }
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(String accountNumber, double amount) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    account.withdraw(amount);
                    return;
                }
            }
        }
        System.out.println("Account not found.");
    }

    public void displayCustomerAccounts(String customerId) {
        Customer customer = findCustomer(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }
    public void deleteCustomerAccount(String customerId){
        Customer customer = findCustomer(customerId);
            if(customer != null){
                 customers.remove(customer);
                 System.out.println("Customer removed successfully: ");
            }
        
    }
}

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
    
        Bank bank = new Bank();
        
        
        String password = "tauhid";
        System.out.print("Enter your password :");
        String Password = scanner.nextLine();
        if(Password.equals(password)){
            while(true){
            System.out.println("deposit");
            System.out.println("withdraw");
            System.out.println("showCustomer");
            System.out.println("AddCustomer");
            System.out.println("DeleteCustomer");
            System.out.print("What action do you want to perform :");
            String action = scanner.next();
        
            if (action.equals("deposit")){
                System.out.print("Enter accountNumber in which do you want to deposit :");
                String accountNumber = scanner.next();
                System.out.print("How much do you want to deposit :");
                int deposit = scanner.nextInt();
                bank.deposit(accountNumber,deposit);
                
            }
            else if(action.equals("withdraw")){
                System.out.print("Enter accountNumber in which do you want to withdraw :");
                String accountNumber = scanner.next();
                System.out.print("How much do you want to withdraw :");
                int withdraw = scanner.nextInt();
                bank.withdraw(accountNumber,withdraw);
            }
            else if(action.equals("showCustomer")){
                System.out.print("Enter Customer Id which do you want to find :");
                String CustomerId = scanner.next();
                bank.displayCustomerAccounts(CustomerId);
                
            }
            else if(action.equals("AddCustomer")){
                System.out.print("Enter Customer Name :");
                String Name = scanner.next();
                System.out.print("Enter Customer Id :");
                String Id = scanner.next();
                System.out.print("Enter Account Number :");
                String accountNumber = scanner.next();
                Customer customer = new Customer(Name, Id);
                bank.addCustomer(customer);
                Account account = new Account(accountNumber);
                customer.addAccount(account);
            }
            else if(action.equals("DeleteCustomer")){
                System.out.print("Enter Customer Id which you want to delete :");
                String customerId = scanner.next();
                bank.deleteCustomerAccount(customerId);
            }
            else{
                System.out.println("Please Enter valid input ?");
            }
        }}
        else{
            System.out.println("wrond password");
        }

    
    
    }

}

