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
import javax.swing.table.DefaultTableModel;

import CRUDOperations.Operations;
import Model.ProductModel;


public class UpdateFrame extends JFrame implements ActionListener{
    JButton applyButton, backButton;
    JTextField idBox, nameBox, quantityBox, priceBox;
    DefaultTableModel table;
    ProductModel products;

    public UpdateFrame(){
        this.setTitle("Edit Product");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        
        ImageIcon logo = new ImageIcon("src/Icons/edit.png");
        Image img = logo.getImage();
        this.setIconImage(img);

        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setFont(labelFont);
        idLabel.setBounds(40, 30, 200, 30);
        this.add(idLabel);

        idBox = new JTextField();
        idBox.setFont(labelFont);
        idBox.setToolTipText("Enter ID(whole numbers)");
        idBox.setBounds(200, 30, 240, 30);
        this.add(idBox);

        JLabel nameLabel = new JLabel("New Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(40, 90, 200, 30);
        this.add(nameLabel);

        nameBox = new JTextField();
        nameBox.setFont(labelFont);
        nameBox.setToolTipText("Enter Name(Letters only)");
        nameBox.setBounds(200, 90, 240, 30);
        this.add(nameBox);

        JLabel quantityLabel = new JLabel("New Quantity:");
        quantityLabel.setFont(labelFont);
        quantityLabel.setBounds(40, 150, 200, 30);
        this.add(quantityLabel);

        quantityBox = new JTextField();
        quantityBox.setFont(labelFont);
        quantityBox.setToolTipText("Enter Quantity(whole numbers)");
        quantityBox.setBounds(200, 150, 240, 30);
        this.add(quantityBox);

        JLabel priceLabel = new JLabel("New Price:");
        priceLabel.setFont(labelFont);
        priceLabel.setBounds(40, 210, 200, 30);
        this.add(priceLabel);

        priceBox = new JTextField();
        priceBox.setFont(labelFont);
        priceBox.setToolTipText("Enter Price(Numbers only)");
        priceBox.setBounds(200, 210, 240, 30);
        this.add(priceBox);

        applyButton = new JButton("Apply");
        applyButton.setBounds(360, 280, 80, 40);
        this.add(applyButton);
        applyButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(60, 280, 80, 40);
        this.add(backButton);
        backButton.addActionListener(this); 
    }

    public boolean isValidUpdate(String id, String name, String quantity, String price){
        if(id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty()){
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }


        if(!id.isEmpty() && !id.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(this, "ID must be a Integers.", "Error!", JOptionPane.WARNING_MESSAGE);
            idBox.setText("");
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
            JOptionPane.showMessageDialog(this, "Price must be a floating point number", "Error!", JOptionPane.WARNING_MESSAGE);
            priceBox.setText("");
            return false;
        }
        return true;
    }

    public void Update(){
            String idText = idBox.getText().trim();
            String name = nameBox.getText().trim();
            String qntyText = quantityBox.getText().trim();
            String priceText = priceBox.getText().trim();
            boolean validation = isValidUpdate(idText, name, qntyText, priceText);

            if(validation == true){
                int id = Integer.parseInt(idText);
                Operations op = new Operations();
                boolean exist = op.idExists(id);
                if(exist == true){
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        try {
                            int quantity = Integer.parseInt(qntyText);
                            double price = Double.parseDouble(priceText);

                            ProductModel products = new ProductModel(id, name, quantity, price);
                            op.updateProducts(products);
                            JOptionPane.showMessageDialog(this, "Product Updated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            idBox.setText("");
                            nameBox.setText("");
                            quantityBox.setText("");
                            priceBox.setText("");

                        } catch (NumberFormatException nfx){
                            JOptionPane.showMessageDialog(this, "Number format invalid!" + nfx, "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    }   
                }
                else{
                    JOptionPane.showMessageDialog(this, "ID:" + id + " does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
                    idBox.setText("");
                } 
            }
            else
                return;
    }  

    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource() == applyButton){
            Update();
        }        

        if(action.getSource() == backButton){
            ViewFrame frame = new ViewFrame();
            frame.setVisible(true);
            this.dispose();
        }
    }  
}
