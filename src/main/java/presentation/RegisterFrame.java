package presentation;

import model.User;
import repository.UserRepository;
import service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class RegisterFrame extends JFrame{
    private JTextField firstName_tf;
    private JTextField lastName_tf;
    private JTextField email_tf;
    private JTextField birthDate_tf;
    private JTextField city_tf;
    private JTextField address_tf;
    private JTextField username_tf;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel registerPanel;
    private JLabel message_label;

    private UserService userService = new UserService();
    private UserRepository userRepository = new UserRepository();

    public RegisterFrame(){
        setContentPane(registerPanel);
        setTitle("Welcome! Please register!");
        setSize(600, 700);
        setLocation(500, 100);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.registerFrame.dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordField1.getText().equals(passwordField2.getText())){
                    if(userService.validEmail(email_tf.getText())){
                        if(userService.validDate(birthDate_tf.getText())){
                            if(userRepository.already_exist(username_tf.getText()) == false){
                                message_label.setText("Registration success!");
                                take_from_tb();
                            }else{
                                showMessageDialog(null, "Username already exists");
                            }
                        }else{
                            showMessageDialog(null, "Date format is incorrect");
                        }
                    }else{
                        showMessageDialog(null, "This email is not valid");
                    }
                }else{
                    message_label.setText("Password doesn't match");
                    showMessageDialog(null ,"Password doesn't match");
                }
            }
        });
    }

    public void take_from_tb(){
        String firstName = firstName_tf.getText();
        String lastName = lastName_tf.getText();
        String email = email_tf.getText();
        String birthDate = birthDate_tf.getText();
        String city = city_tf.getText();
        String address = address_tf.getText();
        String username = username_tf.getText();
        String password = passwordField1.getText();

        User user = new User(firstName, lastName, email, birthDate, city, address, username, password);
        UserRepository userRepository = new UserRepository();
        userRepository.data_insert(user);
    }

}
