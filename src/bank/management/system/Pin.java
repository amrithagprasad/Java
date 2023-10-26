package bank.management.system;

import javax.swing.*;
import javax.swing.plaf.SpinnerUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Pin extends JFrame implements ActionListener {
    JButton b1,b2;
    JPasswordField p1,p2;
    String pin;
    Pin(String pin){

        this.pin=pin;


        JLabel label1=new JLabel("Change Your Password.");
        label1.setFont(new Font("Raleway",Font.BOLD,18));
        label1.setBounds(660,190,400,35);
        add(label1);

        JLabel label2=new JLabel("New Pin:");
        label2.setFont(new Font("Raleway",Font.BOLD,18));
        label2.setBounds(510,240,400,35);
        add(label2);

        p1=new JPasswordField();
        p1.setBounds(630,240,320,25);
        p1.setFont(new Font("Raleway",Font.BOLD,22));
        add(p1);

        JLabel label3=new JLabel("Re-enter New Pin:");
        label3.setFont(new Font("Raleway",Font.BOLD,18));
        label3.setBounds(450,300,400,35);
        add(label3);

        p2=new JPasswordField();
        p2.setBounds(630,300,320,25);
        p2.setFont(new Font("Raleway",Font.BOLD,22));
        add(p2);


        b1=new JButton("CHANGE PIN");
        b1.setBounds(600,390,150,35);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        add(b1);

        b2=new JButton("BACK");
        b2.setBounds(820,390,150,34);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        add(b2);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);



        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            String pin1 = p1.getText();
            String pin2 = p2.getText();

            if(!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match.");
                return;
            }
            if(e.getSource()==b1){
                if(p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter PIN.");
                    return;
                }
                if(p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-enter your PIN.");
                    return;
                }

                Con c = new Con();
                String q1 = "update bank set pin = '"+pin1+"' where pin = '"+pin+"' ";
                String q2  = "update login set pin = '"+pin1+"' where pin = '"+pin+"' ";
                String q3 = "update signupthree set pin = '"+pin1+"' where pin = '"+pin+"' ";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null,"PIN changed.");
                setVisible(false);
                new main(pin);

            }else if(e.getSource()==b2){
                new main(pin);
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();
        }



    }

    public static void main(String[] args) {
        new Pin("");

    }
}
