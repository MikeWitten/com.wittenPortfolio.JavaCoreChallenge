import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Methods.newFriend("albert");
        Methods.newFriend("Bernie");
        Methods.newFriend("Claire");

        Message a = new Message("I like messages");
        Message b = new Message("I hate messages");
        Message c = new Message("I'm indifferent to messages");

        Methods.allMessages.add(a);
        Methods.allMessages.add(b);
        Methods.allMessages.add(c);
        //go to the start screen.
        startScreen();
        mainMenu();
    }

    private static void startScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello User, Please enter your name.\n");
        String testName = scanner.next();
        System.out.println("You entered " + testName + ".  Is that correct \n" +
                "Type Y for yes or any key to enter again");
        String answer = scanner.next();
        if(!(answer.equals("Y") || answer.equals("y"))){
            startScreen();
        } else {
            System.out.println("From now on you will be called " + testName + ". \n" +
                    " ");
        }
        User.getInstance(testName);
    }

    public static void mainMenu() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String userName = User.getInstance(null).getName();
        System.out.println("Hello " + userName +
                "!  Please select one of the following options: \n" +
                "1. Manage contacts\n" +
                "2. Messages\n" +
                "3. Quit");
        int response = 1000;
        String temp = scanner.next();
        try{ response = Integer.parseInt(temp);
        }catch (Exception nfe){
            System.out.println(userName + ", you have entered an invalid response.  Please try again.\n" +
                    " ");
            TimeUnit.SECONDS.sleep(2);
            mainMenu();
        }
        if(response > 3 || response < 1){
            System.out.println(userName + ", you have entered an invalid response.  Please try again.\n" +
                    " ");
            TimeUnit.SECONDS.sleep(2);
            mainMenu();
        }
        switch (response){
            case 1: manageContacts();
            case 2: manageMessages();
            case 3: System.exit(1);
        }
    }

    private static void manageMessages() throws InterruptedException {
        String userName = User.getInstance(null).getName();
        System.out.println( "Please select from the following options, " + userName );
        System.out.println("""
                1. See the list of all messages\s
                2. Send a new message.
                3. Go back to previous menu.""");
        Scanner scanner = new Scanner(System.in);
        int response = 1000;
        String temp = scanner.next();
        try{ response = Integer.parseInt(temp);
        }catch (Exception nfe){
            System.out.println(userName + ", you have entered an invalid response.  Please try again.\n" +
                    " ");
            TimeUnit.SECONDS.sleep(2);
            manageMessages();
        }
        if(response > 3 || response < 1){
            System.out.println(userName + ", you have entered an invalid response.  Please try again.\n" +
                    " ");
            TimeUnit.SECONDS.sleep(2);
            manageMessages();
        }
        switch(response){
            case 1: seeAllMessages();
            case 2: sendNewMessage();
            case 3: mainMenu();
        }
    }

    private static void sendNewMessage() throws InterruptedException {
        System.out.println("Please type your new message now:  ");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        System.out.println("Are you satisfied with your message? \n" +
                "Type Y to send, Type Q to quit,  or R to re-type");
        String response = scanner.nextLine();
        if(response.equalsIgnoreCase("y")){
            Message message1 = new Message(message);
            Methods.addMessage(message1);
            System.out.println("Great! Your message has been sent.");
            manageMessages();
        }else if(response.equalsIgnoreCase("q")){
            manageMessages();
        }else if(response.equalsIgnoreCase("r")){
        System.out.println("That's OK, try again.");
        TimeUnit.SECONDS.sleep(2);
        sendNewMessage();}

    }

    private static void seeAllMessages() throws InterruptedException {
        int i = 1;
        for (Message m: Methods.allMessages) {
            System.out.println("Message " + i +":  " + m.getMessageTxt());
            i ++;
        }
        System.out.println("\n" +
                "Press Any Key to go back.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        manageMessages();
    }

    private static void manageContacts() throws InterruptedException {
        String userName = User.getInstance(null).getName();
        System.out.println(userName + ", Please select from the following options: \n" +
                "1. Show all contacts\n" +
                "2. Add new contact\n" +
                "3. Search for contact\n" +
                "4. Delete a contact\n" +
                "5. Return to previous menu.");
        Scanner scanner = new Scanner(System.in);
        int selection = 1000;
        String temp = scanner.nextLine();
        try {
            selection = Integer.parseInt(temp);
        }catch(Exception nfe) {
            System.out.println("Your input was invalid, Please try again.");
            TimeUnit.SECONDS.sleep(2);
            manageContacts();
        }
        if (selection > 5 || selection < 1){
            System.out.println("Your input was invalid, Please try again.");
            TimeUnit.SECONDS.sleep(2);
            manageContacts();
        }
        switch (selection){
            case 1: showContacts();
            case 2: addContact();
            case 3: searchContacts();
            case 4: deleteContact();
            case 5: mainMenu();
        }
    }

    private static void deleteContact() throws InterruptedException {
        System.out.println("Enter the name of the contact you wish to delete:  ");
        Scanner scanner = new Scanner(System.in);
        String ghostedName = scanner.next();
        Methods.deleteFromContacts(ghostedName);
        TimeUnit.SECONDS.sleep(1);
        manageContacts();
    }

    private static void searchContacts() throws InterruptedException {
        System.out.println("Please enter the name of the contact you wish to find: ");
        Scanner scanner = new Scanner(System.in);
        String friendFinder = scanner.nextLine();
        Contact c = Methods.friendFinder(friendFinder);
        if(c == null){
            System.out.println("Returning to previous menu.");
            TimeUnit.SECONDS.sleep(2);
            manageContacts();
        }
        assert c != null;
        System.out.println("Contact ID:      " + c.getId());
        System.out.println("Contact name:    " + c.getName());
        System.out.println("\n" +
                "Press any key to continue");
        scanner.nextLine();
        TimeUnit.SECONDS.sleep(1);
        manageContacts();
    }

    private static void addContact() throws InterruptedException {
        System.out.println("Please enter the name of contact you would like to add:  ");
        Scanner scanner = new Scanner(System.in);
        String newFriend = scanner.nextLine();
        Methods.newFriend(newFriend);
        TimeUnit.SECONDS.sleep(2);
        manageContacts();
    }

    private static void showContacts() throws InterruptedException {
        if(Methods.allContacts.isEmpty()){
            System.out.println("You have no contacts in your system.");
            TimeUnit.SECONDS.sleep(2);
            manageContacts();
        }
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Contact c: Methods.allContacts){
            System.out.println("Contact " + i + ": \n" +
                    "ID:    " + c.getId() + "\n" +
                    "Name:  " + c.getName()+ "\n" +
                    "Number:" + c.getNumber()+ "\n");
            i++;
        }
        System.out.println("\n" +
                "Press any Key to continue");
        scanner.nextLine();
        manageContacts();
    }
}
