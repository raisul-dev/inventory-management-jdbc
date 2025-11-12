package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import CRUDOperations.Operations;
import Model.ProductModel;


public class AddFrame extends JFrame implements ActionListener{
    JButton addButton, backButton;
    JTextField nameBox, quantityBox, priceBox;

    public AddFrame(){
        this.setTitle("Add Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 350);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        ImageIcon logo = new ImageIcon("src/Icons/add.png");
        Image img = logo.getImage();
        this.setIconImage(img);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(40, 40, 200, 25);
        this.add(nameLabel);

        nameBox = new JTextField();
        nameBox.setFont(labelFont);
        nameBox.setToolTipText("Enter Product Name(letters only)");
        nameBox.setBounds(200, 40, 240, 30);
        this.add(nameBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(labelFont);
        quantityLabel.setBounds(40, 100, 200, 25);
        this.add(quantityLabel);

        quantityBox = new JTextField();
        quantityBox.setFont(labelFont);
        quantityBox.setToolTipText("Enter Quantity(whole numbers)");
        quantityBox.setBounds(200, 100, 240, 30);
        this.add(quantityBox);

        JLabel priceLabel = new JLabel("Per Unit Price:");
        priceLabel.setFont(labelFont);
        priceLabel.setBounds(40, 160, 200, 25);
        this.add(priceLabel);

        priceBox = new JTextField();
        priceBox.setFont(labelFont);
        priceBox.setToolTipText("Enter Price(e.g., 1294.35)");
        priceBox.setBounds(200, 160, 240, 30);
        this.add(priceBox);

        addButton = new JButton("Add");
        addButton.setBounds(350, 230, 80, 25);
        this.add(addButton);
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50, 230, 80, 25);
        this.add(backButton);
        backButton.addActionListener(this);

    }
    public boolean isValidAdd(String name, String quantity, String price){
        if(name.isEmpty() || quantity.isEmpty() || price.isEmpty()){
            JOptionPane.showMessageDialog(this, "Fields can not be Empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(!name.isEmpty() && !name.matches("[a-zA-Z ]+")){ 
            JOptionPane.showMessageDialog(this, "Name Cannot Contain Numbers or symbols", "Error!", JOptionPane.WARNING_MESSAGE);
            nameBox.setText("");
            return false;
        }               
        if(!quantity.isEmpty() && !quantity.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(this, "Quantity must be a Integer.", "Error!", JOptionPane.WARNING_MESSAGE);
            quantityBox.setText("");
            return false;
        }
        if(!price.isEmpty() && !price.matches("^([0-9]*\\.[0-9]+|[0-9]+)$")){
            JOptionPane.showMessageDialog(this, "Price must be a Floating Point", "Error!", JOptionPane.WARNING_MESSAGE);
            priceBox.setText("");
            return false;
        }
        return true;
    }
    
    public void Add(){
        String name = nameBox.getText().trim();
        String quantityText = quantityBox.getText().trim();
         String priceText = priceBox.getText().trim();
        boolean validation = isValidAdd(name, quantityText, priceText);
            
        if(validation == true){
            int quantityInt = Integer.parseInt(quantityText);
            Double priceDbl = Double.parseDouble(priceText);

            ProductModel products = new ProductModel(name, quantityInt, priceDbl);
            Operations add = new Operations();
            add.addProducts(products);

            JOptionPane.showMessageDialog(this, "Product Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            nameBox.setText("");
            quantityBox.setText("");
            priceBox.setText("");
        }
        else
            return;  
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        //Add Button
        if(action.getSource() == addButton){
            Add();      
        }                 
  
        //Back Button
        if(action.getSource() == backButton){
            HomeFrame frame = new HomeFrame();
            frame.setVisible(true);
            this.dispose();
        }
    
    }
}
