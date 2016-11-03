public class UserInterface {
    //currently main is just used to test classes, remove if necessary
    public static void main(String[] args) {
        AlaCarteManager manage = new AlaCarteManager();
        manage.printAllMenu();


        PPackageManager pmanage = new PPackageManager();
        pmanage.addPackage(19);
        pmanage.addItemToPackage(3, 2);
        pmanage.printAllPackage();
    }
}
