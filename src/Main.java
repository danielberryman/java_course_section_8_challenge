import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("2065506479");

    public static void main(String[] args) {
        boolean stop = false;

        startPhone();
        printInstructions();

        while(!stop) {
            System.out.println("\nEnter your choice:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    stop = true;
                    break;
                case 2:
                    mobilePhone.printContacts();
                    break;
                case 3:
                    addNewContact();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    removeContact();
                    break;
                case 6:
                    queryContact();
                    break;
                default:
                    System.out.println("Invalid option.");
                    printInstructions();
                    break;
            }
        }
    }

    private static void addNewContact() {
        System.out.println("Enter new contact name:");
        String name = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if(mobilePhone.addContact(newContact)){
            System.out.println("New contact added: name = " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if(mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully updated record");
        } else {
            System.out.println("Error updating contact");
        }
    }

    public static void removeContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found");
            return;
        }

        if(mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully removed contact");
        } else {
            System.out.println("Error deleting record");
        }
    }

    public static void queryContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null){
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Name: " + existingContactRecord.getName() + " phone number is " + existingContactRecord.getPhoneNumber());
    }

    public static void startPhone() {
        System.out.println("Starting phone...");
    }

    public static void printInstructions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("1. Quit");
        System.out.println("2. Print list of contacts");
        System.out.println("3. Add new contact");
        System.out.println("4. Update existing contact");
        System.out.println("5. Remove contact");
        System.out.println("6. Search/find contact");
    }
}
