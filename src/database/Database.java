/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Raja Shahab
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    static void showAll(Connection con) throws Exception{
        Statement st = con.createStatement();
        String query ="Select * From user";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getInt("id")+" "+rs.getString("name"));
        }
        st.close();
    }
    static void findUserById(Connection con) throws Exception{
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        String query ="Select * from user where id = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("id")+" "+rs.getString("name"));
        }
        st.close();
    }
    static void deleteUserById(Connection con) throws Exception{
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        String query ="DELETE from user where id = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        int count = st.executeUpdate();
        System.out.println(count+" row/s affected");
        st.close();
    }
    static void insertUser(Connection con)throws Exception{
        Scanner scan = new Scanner(System.in);
        String Name = scan.nextLine();
        String query = "Insert Into user(name) values(?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, Name);
        st.executeUpdate();
        showAll(con);
        st.close();
        
    }
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/database";
        String user ="root";
        String pass = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,user,pass);
        int condition;
        Scanner scan = new Scanner(System.in);
        while(true){   
            System.out.println("========================================================");
            System.out.println("1 to show all \n2 for find \n3 for insert \n4 for delete \n5 to exit");
            System.out.println("========================================================");
            
            condition = scan.nextInt();
            
            switch(condition){
                case 1:
                    showAll(con);
                    break;
                case 2:
                    findUserById(con);
                    break;
                case 3:
                    insertUser(con);
                    break;
                case 4:
                    deleteUserById(con);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter valid option");
                    break;
            }
         }
    }
}
