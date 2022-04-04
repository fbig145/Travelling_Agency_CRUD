package presentation;

import model.Packagee;
import repository.PackageRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserFrame extends JFrame {
    private JPanel userPanel;
    private JList list1;
    private JButton show_btn;

    private PackageRepository packageRepository = new PackageRepository();

    public UserFrame(){
        setContentPane(userPanel);
        setTitle("Welcome!");
        setSize(600, 700);
        setLocation(500, 100);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        show_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Packagee> packageeList = packageRepository.showPackages();
                DefaultListModel<String> l1 = new DefaultListModel<>();
               // l1.addElement(packageeList.get(0));
                l1.addElement("aloooo");
                list1 = new JList(l1);
                list1.setVisible(true);
            }
        });
    }





}
