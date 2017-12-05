import commands.Invoker;
import services.MainView;

public class Starter {
    public static void main(String[] args) {
        new MainView(new Invoker()).showMenu();
    }
}
