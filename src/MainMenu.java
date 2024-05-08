import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Container {
    private JButton addButton;
    private JButton listButton;
    private JButton exitButton;

    public  MainMenu(){
        setSize(500,540);
        setLayout(null);

        addButton=new JButton("ADD");
        addButton.setBounds(100,80,300,30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.addWindow.setVisible(true);
                Main.frame.menuWindow.setVisible(false);
            }
        });

        add(addButton);
        listButton=new JButton("LIST");
        listButton.setBounds(100,120,300,30);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.listWindow.setVisible(true);
                Main.frame.menuWindow.setVisible(false);
            }
        });
        add(listButton);
        exitButton=new JButton("EXIT");
        exitButton.setBounds(100,160,300,30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

    }
}
