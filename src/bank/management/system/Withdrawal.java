package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {
    String pin;
    TextField textField;
    JButton b1,b2;
    Withdrawal(String pin){
        this.pin=pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000.");
        label1.setFont(new Font("Raleway",Font.BOLD,18));
        label1.setBounds(610,180,490,35);
        l3.add(label1);

        JLabel label2=new JLabel("PLEASE ENTER YOUR AMOUNT.");
        label2.setFont(new Font("Raleway",Font.BOLD,18));
        label2.setBounds(635,230,420,35);
        l3.add(label2);

        textField=new TextField();
        textField.setBounds(590,300,400,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textField);

        b1=new JButton("WITHDRAW");
        b1.setBounds(590,362,150,35);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("BACK");
        b2.setBounds(840,362,150,35);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter The Amount You Want To Withdraw.");
                } else {
                    Con c = new Con();
                    ResultSet resultSet = c.statement.executeQuery("select* from bank where pin='" + pin + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));

                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    c.statement.executeUpdate("insert into bank values('" + pin + "','" + date + "','Withdrawal', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs." + amount + " debited successfully.");
                    setVisible(false);
                    new main(pin);


                }

            } catch (Exception E) {

            }
        }else if(e.getSource()==b2){
            setVisible(false);
            new main(pin);
        }

    }

    public static void main(String[] args) {
        new Withdrawal("");

    }
}
