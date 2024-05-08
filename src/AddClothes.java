import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddClothes extends Container {
    private JLabel status;
    private JLabel titleLabel;
    private JTextField titleText;
    private JLabel descLabel;
    private JTextField descText;
    private JLabel priceLabel;
    private JSpinner priceSpinner;
    private JLabel typeLabel;
    private JComboBox<String> typesBox;
    private String[] types={"Футболка", "Толстовка","Кофта"};
    private JLabel sizesLabel;
    private JList<String> sizesList;
    private String[] sizes={"M", "L","XL"};
    private JLabel colorsLabel;
    private JList<String> colorsList;
    private String[] colors={"White", "Black"};
    private JLabel quantityLabel;
    private JSpinner quantitySpinner;
    private JLabel availableLabel;
    private JCheckBox availableCheckBox;
    private JButton addButton;
    private JButton backButton;

    public AddClothes(){
        setSize(500,540);
        setLayout(null);

        titleLabel=new JLabel("Title");
        titleLabel.setBounds(90,60,80,30);
        add(titleLabel);
        titleText=new JTextField();
        titleText.setBounds(170,60,150,30);
        add(titleText);

        descLabel=new JLabel("Desc");
        descLabel.setBounds(90,100,80,30);
        add(descLabel);
        descText=new JTextField();
        descText.setBounds(170,100,150,30);
        add(descText);

        priceLabel=new JLabel("Price");
        priceLabel.setBounds(90,140,80,30);
        add(priceLabel);
        priceSpinner=new JSpinner();
        priceSpinner.setBounds(170,140,150,30);
        add(priceSpinner);

        typeLabel=new JLabel("Type");
        typeLabel.setBounds(90,180,80,30);
        add(typeLabel);
        typesBox=new JComboBox<>(types);
        typesBox.setBounds(170,180,150,30);
        add(typesBox);

        sizesLabel = new JLabel("Sizes");
        sizesLabel.setBounds(90, 220, 80, 30);
        add(sizesLabel);

        sizesList = new JList<>(sizes);
        sizesList.setBounds(170, 220, 150, 60);
        sizesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(sizesList);

        colorsLabel = new JLabel("Colors");
        colorsLabel.setBounds(90, 290, 80, 30);
        add(colorsLabel);

        colorsList = new JList<>(colors);
        colorsList.setBounds(170, 290, 150, 40);
        colorsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(colorsList);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(90, 330, 80, 30);
        add(quantityLabel);

        quantitySpinner = new JSpinner();
        quantitySpinner.setBounds(170, 330, 150, 30);
        add(quantitySpinner);

        availableLabel = new JLabel("Available");
        availableLabel.setBounds(90, 370, 80, 30);
        add(availableLabel);

        availableCheckBox = new JCheckBox();
        availableCheckBox.setBounds(170, 370, 150, 30);
        add(availableCheckBox);

        backButton=new JButton("Back");
        backButton.setBounds(90,480,115,30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.addWindow.setVisible(false);
                Main.frame.menuWindow.setVisible(true);
            }
        });
        add(backButton);

        addButton=new JButton("Add");
        addButton.setBounds(215,480,115,30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=titleText.getText();
                String desc=descText.getText();
                int price=(int) priceSpinner.getValue();
                String type=(String) typesBox.getSelectedItem();
                ArrayList<String> sizes= (ArrayList<String>) sizesList.getSelectedValuesList();
                ArrayList<String> colors=(ArrayList<String>) colorsList.getSelectedValuesList();;
                int quantity=(int) quantitySpinner.getValue();
                boolean available =  availableCheckBox.isSelected();

                Clothes cloth=new  Clothes((Long) null,title,  desc,  price,  type,  sizes,  colors, quantity, available);
                PackageData pd=new PackageData("ADD",cloth);
                Main.connect(pd);

                titleText.setText("");
                descText.setText("");
            }
        });
        add(addButton);


    }
}
