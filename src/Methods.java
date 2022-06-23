import java.util.ArrayList;
import java.util.Random;

public class Methods {
    //Methods for creating and displaying messages.
    public static ArrayList<Message> allMessages = new ArrayList<Message>();

    public static ArrayList<Message> getAllMessages(){
        return allMessages;
    }

    public static void addMessage(Message message){
        allMessages.add(message);
    }

    //Methods for creating and displaying contacts.
    public static ArrayList<Contact> allContacts = new ArrayList<Contact>();

    public static ArrayList<Contact> getAllContacts(){
        return allContacts;
    }

    public static void deleteFromContacts(String name){
        for (Contact c: allContacts) {
            if (name.equalsIgnoreCase(c.getName())){
                allContacts.remove(c);
                System.out.println("Contact has been removed");
                return;
            }
        }
        System.out.println("No contact was found by that name");
    }

    public static Contact friendFinder(String name){
        for (Contact c: allContacts){
            if(name.equalsIgnoreCase(c.getName())) {
                System.out.println("Friend Found!");
                return c;
            }
        }
        System.out.println("You don't have a friend by that name :(");
        return null;
    }

    public static void newFriend(String name){

        int big = 1;
        for(Contact c: allContacts){
            if (c.getId() >= big){
                big = c.getId()+1;
            }
        }
        String number;
        Random r = new Random();
        int ID = big;
        int rand = 25;
        number = String.valueOf(r.nextInt(rand));
        Contact c = new Contact(ID, name, number);
        allContacts.add(c);
        System.out.println(name + " has been added to your contact list.");
    }

}
