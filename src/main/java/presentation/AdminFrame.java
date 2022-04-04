package presentation;

import model.Destination;
import model.Packagee;
import repository.DestinationRepository;
import repository.PackageRepository;
import repository.UserRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import static javax.swing.JOptionPane.showMessageDialog;

public class AdminFrame extends JFrame {
    private JPanel adminPanel;
    private JTextField destination_tf;
    private JButton add_destination_btn;
    private JComboBox destination_cb;
    private JTextField name_tf;
    private JTextField price_tf;
    private JTextField start_tf;
    private JTextField end_tf;
    private JTextField details_tf;
    private JTextField spots_tf;
    private JTextField status_tf;
    private JButton add_package_btn;
    private JButton refresh_btn;
    private JButton load_btn;
    private JButton delete_btn;
    private JButton update_btn;
    private JButton delete_dest_btn;
    private static UserRepository userRepository = new UserRepository();
    private static PackageRepository packageRepository = new PackageRepository();
    private static DestinationRepository destinationRepository = new DestinationRepository();

    private int clicks = 0;

    public AdminFrame(){
        setContentPane(adminPanel);
        setTitle("Welcome, Admin!");
        setSize(700, 600);
        setLocation(500, 100);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        add_destination_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Destination destination = new Destination(destination_tf.getText());
                DestinationRepository destinationRepository = new DestinationRepository();
                destinationRepository.destination_insert(destination);
            }
        });

        refresh_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(destination_cb.getSelectedItem());
                destination_cb.removeAllItems();
                List<Destination> destination = destinationRepository.take_from_destination();
                for(Destination dest : destination){
                    destination_cb.addItem(dest.getName());
                }

            }
        });
        add_package_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dest_id = destinationRepository.take_dest_id((String) destination_cb.getSelectedItem());
                    String package_name = name_tf.getText();
                    int price = Integer.parseInt(price_tf.getText());
                    Date start = new SimpleDateFormat("dd/MM/yyyy").parse(start_tf.getText());
                    Date end = new SimpleDateFormat("dd/MM/yyyy").parse(end_tf.getText());
                    String details = details_tf.getText();
                    int spots = Integer.parseInt(spots_tf.getText());
                    String status = status_tf.getText();
                    Packagee packagee = new Packagee(package_name, dest_id, price, start, end, details, spots, status);

                    packageRepository.package_insert(packagee);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        load_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<Packagee> packageEntities = packageRepository.load_package(destinationRepository.take_dest_id((String) destination_cb.getSelectedItem()));
                    name_tf.setText(packageEntities.get(clicks).getName());
                    price_tf.setText(String.valueOf(packageEntities.get(clicks).getPrice()));
                    start_tf.setText(String.valueOf(packageEntities.get(clicks).getStart()));
                    end_tf.setText(String.valueOf(packageEntities.get(clicks).getEnd()));
                    details_tf.setText(packageEntities.get(clicks).getDetails());
                    spots_tf.setText(String.valueOf(packageEntities.get(clicks).getSpots()));
                    status_tf.setText(packageEntities.get(clicks).getDetails());
                    clicks++;

                }catch(Exception ex){
                    ex.printStackTrace();
                    showMessageDialog(null, "This destination does not have a package");
                }
            }
        });
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageRepository.delete_package(destinationRepository.take_dest_id((String) destination_cb.getSelectedItem()));
            }
        });
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    packageRepository.update_package(destinationRepository.take_dest_id((String) destination_cb.getSelectedItem()), name_tf.getText(),
                            Integer.parseInt(price_tf.getText()), new SimpleDateFormat("dd/MM/yyyy").parse(start_tf.getText()), new SimpleDateFormat("dd/MM/yyyy").parse(end_tf.getText()),
                            details_tf.getText(), Integer.parseInt(spots_tf.getText()), status_tf.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        delete_dest_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destinationRepository.delete_destination((String)destination_cb.getSelectedItem());
            }
        });
    }
}
