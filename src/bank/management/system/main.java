package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    main(String pin){
        this.pin=pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("Please Select Your Transaction.");
        label.setBounds(560,200,700,35);
        label.setFont(new Font("System",Font.BOLD,28));
        l3.add(label);

        b1=new JButton("Deposit");
        b1.setBounds(510,270,220,32);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("Cash Withdrawal");
        b2.setBounds(850,270,220,32);
        b2.addActionListener(this);
        l3.add(b2);

        b3=new JButton("Fast cash");
        b3.setBounds(510,310,220,32);
        b3.addActionListener(this);
        l3.add(b3);

        b4=new JButton("Mini Statement");
        b4.setBounds(850,310,220,32);
        b4.addActionListener(this);
        l3.add(b4);

        b5=new JButton("PIN Change");
        b5.setBounds(510,350,220,32);
        b5.addActionListener(this);
        l3.add(b5);

        b6=new JButton("Balance Enquiry");
        b6.setBounds(850,350,220,32);
        b6.addActionListener(this);
        l3.add(b6);

        b7=new JButton("Exit");
        b7.setBounds(680,400,220,32);
        b7.addActionListener(this);
        l3.add(b7);


        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            new deposit(pin);
            setVisible(false);
        } else if (e.getSource()==b7) {
            System.exit(0);
        } else if(e.getSource()==b2){
            new Withdrawal(pin);
            setVisible(false);
        }else if(e.getSource()==b6){
            new BalanceEnquiry(pin);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new main("");

    }
}
