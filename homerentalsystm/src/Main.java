import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
//
class housedata{
    private String housenm;
    private int id;
    private String addr;
    private boolean booked;
    private boolean isempty;

    public housedata(String housenm, int id, String addr, boolean booked, boolean isempty){
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


}
class house {
    private static final String url = "jdbc:mysql://localhost:3306/housedb";
    private static final String uname = "root";
    private static final String pass = "kali";



    public static void createhouse(String name, int id, String address, boolean booked, boolean isempty){
        String query = "Insert into houses (housenm, id, address, booked, isempty) values (?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);
                preparedStatement.setString(3, address);
                preparedStatement.setBoolean(4, booked);
                preparedStatement.setBoolean(5, isempty);
                preparedStatement.executeUpdate();
                System.out.println("residence added successfully");

        } catch (SQLException e) {
            if(e.getSQLState().startsWith("23")){
                System.out.println("Error: residence with id "+id+" already exists");
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void fetchunbookedhousedetails(){
        List<housedata> h = new ArrayList<>();
        String query = "SELECT * FROM houses where booked = false and isempty = true";
        try (Connection connection = DriverManager.getConnection(url, uname, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("housenm");
                String address = resultSet.getString("address");
                Boolean booked = resultSet.getBoolean("booked");
                Boolean isempty = resultSet.getBoolean("isempty");
                System.out.println("\nHouse name: "+name+"\naddress: "+address+"\nbooked: "+booked+"\nVacant: "+isempty);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void listallhouses(){
        List<housedata> h = new ArrayList<>();
        String query = "SELECT * FROM houses;";
        try (Connection connection = DriverManager.getConnection(url, uname, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("housenm");
                String address = resultSet.getString("address");
                Boolean booked = resultSet.getBoolean("booked");
                Boolean isempty = resultSet.getBoolean("isempty");
                int id = resultSet.getInt("id");
                System.out.println("\nHouse name: "+name+"\nid: "+id+"\naddress: "+address+"\nbooked: "+booked+"\nVacant: "+isempty);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void fetchvacanthousedetails(){
        String query = "SELECT * FROM houses where isempty = true";
        try (Connection connection = DriverManager.getConnection(url, uname, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("housenm");
                String address = resultSet.getString("address");
                Boolean booked = resultSet.getBoolean("booked");
                Boolean isempty = resultSet.getBoolean("isempty");
                System.out.println("\nHouse name: "+name+"\naddress: "+address+"\nbooked: "+booked+"\nVacant: "+isempty);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void gethousebyid(int id) {
        String query = "SELECT * FROM houses WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, uname, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String name = resultSet.getString("housenm");
                        String address = resultSet.getString("address");
                        Boolean booked = resultSet.getBoolean("booked");
                        Boolean isempty = resultSet.getBoolean("isempty");
                        System.out.println("\nHouse name: "+name+"\naddress: "+address+"\nbooked: "+booked+"\nVacant: "+isempty);
                    }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updatehouse(int id, boolean booked, boolean isempty){
        String query = "UPDATE houses SET booked = ?, isempty = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setBoolean(1, booked);
            preparedStatement.setBoolean(2, isempty);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletehouse(int id) {
        String query = "DELETE FROM houses WHERE id = ?";

        try(Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0){
                System.out.println("id does not exist");
            } else {
                System.out.println("residence deleted");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
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

    public static String fetchuname(int id){
        try (Connection connection = DriverManager.getConnection(url, uname, pass)) {
            String query = "SELECT * FROM account WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String pass = resultSet.getString("ownername");
                        return pass;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String fetchpass(int id){
        try (Connection connection = DriverManager.getConnection(url, uname, pass)) {
            String query = "SELECT * FROM account WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String pass = resultSet.getString("pass");
                        return pass;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}



public class Main extends house{

    public static void main(String[] args){
        boolean run = true;
        int login = 0;
        while (run) {

            System.out.println("############### Welcome to Home Rental System #############");
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1 -> Login as Admin \n2 -> Login as Booking\n0 -> Exit");
            int inp = sc.nextInt();
            if (inp == 0) {
                run = false;

            } else if (inp == 1) {
                System.out.println("Admin Password:");
                String pass = sc.next();    // password is admin123
                String h = account.fetchpass(1);
                if (pass.equals(h)) {
                    System.out.println("Login successful");
                    login=1;

                } else {
                    System.out.println("Login failed");
                }
            } else if (inp == 2){
                System.out.println("Booking user password:"); // pass is bok12
                String pass = sc.next();
                String bok = account.fetchpass(2);
                if (pass.equals(bok)){
                    System.out.println("Login Successful");
                    login = 2;
                } else {
                    System.out.println("Login failed");
                }
            } else {
                System.out.println("Invalid input");
            }

            // database operations

            if (login == 0){
                System.out.println(" ");
            }
            else if (login == 1){
                while(login == 1) {
                    String un = account.fetchuname(1);
                    System.out.println("#############################################\nUser: "+un);
                    System.out.println("\n0 -> Back \n1 -> add residence \n2 -> booking \n3 -> delete residence\n");
                    inp = sc.nextInt();
                    if (inp == 0) {
                        System.out.println("Logging out");
                        login = 0;
                    } else if (inp == 1) {
                        addhouse();
                    } else if (inp == 2){
                        System.out.println("\n0 -> back \n1 -> update booking \n2 -> new booking");
                        inp = sc.nextInt();
                        if (inp == 0){
                            System.out.println(" <back> ");
                        } else if (inp == 1){
                            System.out.println("\n1 -> list unbooked houses \n2 -> list vacant houses \n3 -> list all houses\n4 -> list house by id\n5 -> book house by id\n6 -> unbook house / vacant house by id\n7 -> allot house by id");
                            inp = sc.nextInt();
                            if (inp == 1){
                                fetchunbookedhousedetails();
                            } else if (inp == 2){
                                fetchvacanthousedetails();
                            }else if (inp == 3){
                                listallhouses();
                            } else if (inp == 4){
                                System.out.println("enter house id: ");
                                int id = sc.nextInt();
                                gethousebyid(id);
                            } else if (inp == 5){
                                int id = sc.nextInt();
                                updatehouse(id, true,true);
                            } else if (inp == 6) {
                                int id = sc.nextInt();
                                updatehouse(id, false, true);
                            } else if (inp == 7){
                                int id = sc.nextInt();
                                updatehouse(id, false, false);

                            } else {
                                System.out.println("invalid input");
                            }
                        }else if (inp == 2){
                            System.out.println("");

                        }


                    } else if (inp == 3) {
                        delhouse();
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            } else if (login == 2){
                System.out.println("booking yet to be added");
                login = 0;

            } else {
                System.out.println("user account error occured");
                login = 0;
            }
        }

        System.out.println("Closing Home Rental System");
//        house h = house.gethousebyid(2);
//        if (h != null) {
//            h.display();
//        } else {
//            System.out.println("no data found");
//        }


    }

    public static void addhouse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter house name:");
        String name = sc.nextLine();
        System.out.println("Enter house id: ");
        int id = sc.nextInt();
        System.out.println("Enter house address:");
        String address = sc.nextLine();
        createhouse(name, id, address, false, true);
    }
    public static void delhouse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter house ID for deletion: ");
        int id = sc.nextInt();
        deletehouse(id);
    }


}

