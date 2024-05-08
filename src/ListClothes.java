import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListClothes extends Container {
    public static JTextArea text;
    private JButton buttonList;
    private  JButton buttonBack;


    public ListClothes(){
        setSize(500,540);
        setLayout(null);

        text=new JTextArea();
        text.setBounds(90,50,300,150);
        add(text);

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(90,50,300,150);
        add(scrollPane);

        buttonList=new JButton("List Clothes");
        buttonList.setBounds(90,220,145,30);
        buttonList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PackageData pd=new PackageData("LIST");
                Main.connect(pd);
            }
        });
        add(buttonList);

        buttonBack= new JButton("Back");
        buttonBack.setBounds(245,220,145,30);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.listWindow.setVisible(false);
                Main.frame.menuWindow.setVisible(true);
            }
        });
        add(buttonBack);

    }
}
