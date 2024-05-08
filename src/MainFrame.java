import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainMenu menuWindow;
    public static AddClothes addWindow;
    public static ListClothes listWindow;

    public MainFrame(){
        setSize(500,540);
        setTitle("Clothes application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuWindow=new MainMenu();
        setLocation(0,0);
        add(menuWindow);

        addWindow=new AddClothes();
        setLocation(0,0);
        addWindow.setVisible(false);
        add(addWindow);

        listWindow=new ListClothes();
        setLocation(0,0);
        listWindow.setVisible(false);
        add(listWindow);


    }
}
