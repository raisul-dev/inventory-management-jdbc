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

public class DeleteFrame extends JFrame implements ActionListener{
    JButton delButton, backButton;
    JTextField idBox;
    public DeleteFrame(){
        this.setTitle("Delete Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(470, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        ImageIcon logo = new ImageIcon("src/Icons/delete.png");
        Image img = logo.getImage();
        this.setIconImage(img);

        JLabel idLabel = new JLabel("Enter Product ID:");
        idLabel.setFont(labelFont);
        idLabel.setBounds(20, 20, 200, 30);
        this.add(idLabel);
        idBox = new JTextField();
        idBox.setFont(labelFont);
        idBox.setToolTipText("Enter ID(whole numbers)");
        idBox.setBounds(200, 20, 200, 30);
        this.add(idBox);

        delButton = new JButton("Delete");
        delButton.setBounds(320, 90, 90, 30);
        this.add(delButton);
        delButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(60, 90, 90, 30);
        this.add(backButton);
        backButton.addActionListener(this);
    }

    public boolean idValid(String id){
        if(!id.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(this, "Enter a Valid ID", "Error!", JOptionPane.WARNING_MESSAGE);
            idBox.setText("");
            return false;
        }
        return true;
    }

    public void delete(){
        String idText = idBox.getText().trim();
        boolean validation = idValid(idText);
        if(validation == true){
            int id = Integer.parseInt(idText);
            Operations op = new Operations();
            boolean exist = op.idExists(id);
            if(exist == true){
                int option = JOptionPane.showConfirmDialog(this, "Delete Product with ID: "+ id, "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    Operations op1 = new Operations();
                    op1.delProducts(id);
                    JOptionPane.showMessageDialog(this, "Product Deleted with ID: "+id, "Success!", JOptionPane.INFORMATION_MESSAGE);
                    idBox.setText("");
                }

                else if(option == JOptionPane.NO_OPTION)
                    idBox.setText("");  
                else if(option == JOptionPane.CANCEL_OPTION){              
                    ViewFrame frame = new ViewFrame();
                    frame.setVisible(true);
                    this.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "ID:" + id + " does not exists", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent action){
        if(action.getSource() == delButton){
            delete();
        }

        else if(action.getSource() == backButton){
            ViewFrame frame = new ViewFrame();
            frame.setVisible(true);
            this.dispose();
        }
    }
}
