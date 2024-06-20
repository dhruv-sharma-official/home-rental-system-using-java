package com.example.homerental;

import java.sql.*;
import java.util.Scanner;

class house{
    private String housenm;
    private int id;
    private String addr;
    private boolean booked;
    private boolean isempty;

    private static final String url = "jdbc:mysql://localhost:3306/housedb";
    private static final String uname = "root";
    private static final String pass = "kali";
    public house(String housenm, int id, String addr, boolean booked, boolean isempty){
        this.housenm = housenm;
        this.id = id;
        this.addr = addr;
        this.booked = booked;
        this.isempty = isempty;
    }
    public String gethnm(){
        return this.housenm;
    }
    public int getid(){
        return this.id;
    }
    public String getaddr(){
        return this.addr;
    }
    public boolean booked(){
        return this.booked;
    }
    public boolean getisempty(){
        return this.isempty;
    }

    public void display(){
        System.out.println("---------------\n"+"House: "+ housenm + "\nId: "+ id + "\naddress: "+ addr+ "\nbooked: "+booked+"\nVacant: "+isempty);
    }



    public void sendhometodb(){
        try (Connection con = DriverManager.getConnection(url, uname, pass)){
            String query = "Insert into houses (hname, id, address, booked, isempty) values (?,?,?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, this.housenm);
                preparedStatement.setInt(2, this.id);
                preparedStatement.setString(3, this.addr);
                preparedStatement.setBoolean(4, this.booked);
                preparedStatement.setBoolean(5, this.isempty);
                preparedStatement.executeUpdate();
                System.out.println("Person saved to the database successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static house gethousebyid(int id) {
        try (Connection connection = DriverManager.getConnection(url, uname, pass)) {
            String query = "SELECT * FROM houses WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String hname = resultSet.getString("housenm");
                        String addr = resultSet.getString("address");
                        Boolean booked = resultSet.getBoolean("booked");
                        Boolean isempty = resultSet.getBoolean("isempty");
                        return new house(hname, id, addr, booked, isempty);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}


class account{

    String ownername;
    String password;
    int id;

    String designation;

    private static final String url = "jdbc:mysql://localhost:3306/housedb";
    private static final String uname = "root";
    private static final String pass = "kali";

    public account(String ownername, int id, String password, String designation){
        this.ownername = ownername;
        this.id = id;
        this.password = password;
        this.designation = designation;

    }
    public String getownername(){
        return this.ownername;
    }
    public int getid(){
        return this.id;
    }
    public String getpass(){
        return this.password;
    }
    public String getDesignation(){
        return this.designation;
    }

    public void display(){
        System.out.println("---------------\n"+"User: "+ ownername + "\nId: "+ id + "\nPost: "+ designation);
    }

//    public static house fetchpass(int id){
//        try (Connection connection = DriverManager.getConnection(url, uname, pass)) {
//            String query = "SELECT * FROM account WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setInt(1, id);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        String va = resultSet.getString("pass");
//                        return va;
//                    }
//                }
//            }
  //      } catch (SQLException e) {
    //        e.printStackTrace();
      //  }
        //return null;
    //}

}



public class homerental {

    public static void main(String[] args){
        boolean run = true;
        System.out.println("############### Welcome to Home Rental System #############");
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1 -> Login as Admin \n2 -> Login as Booking\n0 -> Exit");
        int inp = sc.nextInt();
        if (inp == 0) {
            System.out.println("Closing Home Rental System");

        } else if (inp == 1) {
            System.out.println("Admin Password:");
            String pass = sc.next();
            //account h = account.fetchpass(1);
            System.out.println(pass);
//            if (pass == )
        } else {
            System.out.println("Invalid input");
        }

//        house h = house.gethousebyid(2);
//        if (h != null) {
//            h.display();
//        } else {
//            System.out.println("no data found");
//        }


    }
}

