public class UserInterface {
    //currently main is just used to test AlaCarteManager, remove if necessary
    public static void main(String[] args) {
        AlaCarteManager manage = new AlaCarteManager();
        manage.addAlaCarte("Choco pie",3,"Snack");
        manage.printMenu();
    }
}
