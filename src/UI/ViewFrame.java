package UI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CRUDOperations.Operations;
import Model.ProductModel;


public class ViewFrame extends JFrame implements ActionListener{
    JButton backButton, updateButton, delButton;
    JTable viewTable;

    public ViewFrame(){
        setTitle("Products");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 560);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon("src/Icons/dis.png");
        Image img = logo.getImage();
        this.setIconImage(img);

        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("ID");
        table.addColumn("Name");
        table.addColumn("Quantity");
        table.addColumn("Price");
            
        viewTable = new JTable();
        viewTable.setModel(table);
        JScrollPane srl = new JScrollPane(viewTable);
        srl.setBounds(50, 30, 900, 400);
        this.add(srl);

        backButton = new JButton("Back");
        backButton.setBounds(50, 450, 80, 40);
        this.add(backButton);
        backButton.addActionListener(this);

        updateButton = new JButton("Edit");
        updateButton.setBounds(700, 450, 80, 40);
        this.add(updateButton);
        updateButton.addActionListener(this);

        delButton = new JButton("Delete");
        delButton.setBounds(800, 450, 80, 40);
        this.add(delButton);
        delButton.addActionListener(this);

        try {
            List<ProductModel> productsList = new ArrayList<>();
            Operations view = new Operations();
            productsList = view.getProducts();
        
            for(int i=0; i<productsList.size(); i++){
                ProductModel products = productsList.get(i);
                int id = products.getId();
                String name = products.getName();
                int quantity = products.getQuantity();
                double price = products.getPrice();

                Object[] items = new Object[4];
                items[0] = id;
                items[1] = name;
                items[2] = quantity;
                items[3] = price;

                table.addRow(items);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Product load failed!", "Error!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource() == updateButton){
            UpdateFrame frame = new UpdateFrame();
            frame.setVisible(true);
            this.dispose();
        }
        else if(action.getSource() == backButton){
            HomeFrame frame = new HomeFrame();
            frame.setVisible(true);
            this.dispose();
        }
        else if(action.getSource() == delButton){
            DeleteFrame frame = new DeleteFrame();
            frame.setVisible(true);
            this.dispose();
        }

    }
}
