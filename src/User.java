public class User {
    int id;
    String name;

    private static User instance;

    public static synchronized User getInstance(String name){
        if(null == instance){
            instance = new User (name);
        }
        return instance;
    }


    private User(String name) {
        this.name = name;
    }

    public static void getUserInstance() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
