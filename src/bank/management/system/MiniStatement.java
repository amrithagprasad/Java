package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {
    String pin;
    JButton button;

     MiniStatement(String pin){

         this.pin=pin;

         setSize(400,600);
         setLocation(20,20);
         setLayout(null);

         JLabel label1= new JLabel();
         label1.setBounds(20,50,400,390);
         add(label1);

         JLabel label2 = new JLabel("Mini Statement");
         label2.setFont(new Font("System",Font.BOLD,15));
         label2.setBounds(150,10,200,20);
         add(label2);

         JLabel label3=new JLabel();
         label3.setBounds(20,450,300,20);
         add(label3);

         JLabel label4 = new JLabel();
         label4.setBounds(20,480,300,20);
         add(label4);

         try{
             Con c = new Con();
             ResultSet resultSet=c.statement.executeQuery("select* from login where pin='"+pin+"'");
             while(resultSet.next()){
                 label3.setText("Card Number: "+resultSet.getString("cardno").substring(0,4)+"XXXXXXXX"+resultSet.getString("cardno").substring(12));
             }

         }catch(Exception e){
             e.printStackTrace();
         }


         try{
             int balance = 0;
             Con c = new Con();
             ResultSet resultSet = c.statement.executeQuery("select* from bank where pin='"+pin+"'");
             while(resultSet.next()){
                 label1.setText(label1.getText()+"<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+"<br><br><html>");



                 if(resultSet.getString("type").equals("deposit")){
                     balance+=Integer.parseInt(resultSet.getString("amount"));
                 }else{
                     balance-=Integer.parseInt(resultSet.getString("amount"));
                 }
             }
             label4.setText("Your Total Balance Is Rs."+balance);


         }catch(Exception e){
             e.printStackTrace();
         }

         button = new JButton("Exit");
         button.setBounds(20,530,100,25);
         button.addActionListener(this);
         add(button);

         setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
         new MiniStatement("");

    }
}
