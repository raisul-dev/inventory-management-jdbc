package UI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HomeFrame extends JFrame implements ActionListener{
    JButton addButton, viewButton, exitButton;

    public HomeFrame(){
        this.setTitle("Inventory Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(410, 450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        Font titleFont = new Font("Serif", Font.BOLD, 40);
        Font ButtonFont = new Font("Arial", Font.BOLD, 20);

        JLabel title = new JLabel("Inventory");
        title.setBounds(114, 25, 300, 70);
        title.setFont(titleFont);
        this.add(title);
        
        ImageIcon logo = new ImageIcon("src/Icons/home.png");
        Image img = logo.getImage();
        this.setIconImage(img);

        addButton = new JButton("Add");
        addButton.setFont(ButtonFont);
        addButton.setBounds(114, 115, 150, 50);
        this.add(addButton);
        addButton.addActionListener(this);

        viewButton = new JButton("View");
        viewButton.setFont(ButtonFont);
        viewButton.setBounds(114, 200, 150, 50);
        this.add(viewButton);
        viewButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setFont(ButtonFont);
        exitButton.setBounds(114, 285, 150, 50);
        this.add(exitButton);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if(action.getSource() == addButton){
            AddFrame frame = new AddFrame();
            frame.setVisible(true);
            this.dispose();
        }

        else if(action.getSource() == viewButton){
            ViewFrame frame = new ViewFrame();
            frame.setVisible(true);
            this.dispose();
        }

        else if(action.getSource() == exitButton){
            int option = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                this.dispose();
            else
                this.setVisible(true);      
        }
    }
    
    // public static void main(String[] args) {
    //     new HomeFrame().setVisible(true);
    // }
}
