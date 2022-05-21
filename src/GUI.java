import com.mysql.cj.jdbc.JdbcConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUI implements ActionListener{

    private static JLabel userLabel;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel passwordLabel;
    private static JLabel success;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);
        frame.add(panel);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80 ,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);
        frame.setVisible(true);

        button.addActionListener(new GUI());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + " " + password);

        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/TylerLucas?user=TylerLucas&password=365rocks");

            String select = "Select * from Player where username = ? and password = ?";

            PreparedStatement selectPrepared = connect.prepareStatement(select);

            selectPrepared.setString(1, user);
            selectPrepared.setString(2, password);

            ResultSet resultSet = selectPrepared.executeQuery();

            if (!resultSet.isBeforeFirst() ) {
                success.setText("User does not exist");
            }
            else {
                success.setText("Login Successful");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}