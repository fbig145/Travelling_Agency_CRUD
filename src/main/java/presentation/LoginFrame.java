package presentation;

import repository.UserRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginFrame extends JFrame{
    private JTextField textField1;
    //private JTextField textField2;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel infoLabel;
    private JPanel loginPanel;
    private JPasswordField passwordField1;

    public static RegisterFrame registerFrame;

    public LoginFrame(){
        setContentPane(loginPanel);
        setTitle("Welcome! Please login!");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(500, 300);
        setVisible(true);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame = new RegisterFrame();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRepository userRepository = new UserRepository();
                if(userRepository.have_user(textField1.getText(), passwordField1.getText())){
                    UserFrame userFrame = new UserFrame();
                }else if(textField1.getText().equals("admin") && passwordField1.getText().equals("admin")){
                    AdminFrame adminFrame = new AdminFrame();
                }else{
                    showMessageDialog(null, "Username or Password are incorrect");
                }

            }
        });
    }


}
