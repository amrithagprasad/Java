package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    FastCash(String pin){

        this.pin=pin;


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("Select Withdrawal Amount.");
        label.setBounds(590,200,700,35);
        label.setFont(new Font("System",Font.BOLD,28));
        l3.add(label);

        b1=new JButton("100");
        b1.setBounds(510,270,220,32);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("500");
        b2.setBounds(850,270,220,32);
        b2.addActionListener(this);
        l3.add(b2);

        b3=new JButton("1000");
        b3.setBounds(510,310,220,32);
        b3.addActionListener(this);
        l3.add(b3);

        b4=new JButton("2000");
        b4.setBounds(850,310,220,32);
        b4.addActionListener(this);
        l3.add(b4);

        b5=new JButton("5000");
        b5.setBounds(510,350,220,32);
        b5.addActionListener(this);
        l3.add(b5);

        b6=new JButton("10000");
        b6.setBounds(850,350,220,32);
        b6.addActionListener(this);
        l3.add(b6);

        b7=new JButton("Back");
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
        if(e.getSource()==b7){
            setVisible(false);
            new main(pin);
        }else{
            String amount=((JButton)e.getSource()).getText();
            Con c = new Con();
            Date date = new Date();
            try{
                ResultSet resultSet = c.statement.executeQuery("select* from bank where pin='"+pin+"'");
                int balance = 0;
                while(resultSet.next()){
                    if(resultSet.getString("type").equals("deposit")){
                        balance+=Integer.parseInt(resultSet.getString("amount"));
                    }else{
                        balance-=Integer.parseInt(resultSet.getString("amount"));
                    }
                }String num = "17";

                if(e.getSource() != b7 && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                c.statement.executeUpdate("insert into bank values('"+pin+"','"+date+"','Withdrawal','"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Withdrawn Successfully");

            }catch(Exception E){
                E.printStackTrace();

            }
            setVisible(false);
            new main(pin);

        }

    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
