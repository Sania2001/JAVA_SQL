package com.abstr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int options;
        while (true) {
            System.out.println("1. Add Book Details");
            System.out.println("2. View all Books");
            System.out.println("3. Delete details of a book");
            System.out.println("4. Exit");
            System.out.println("Select an option");
            options = S.nextInt();

            switch(options){
                case 1:
                    String Name, Author, Publisher, Distributor, Price;
                    System.out.println("Enter name:");
                    Name = S.next();
                    System.out.println("Author Name:");
                    Author = S.next();
                    System.out.println("Publisher name:");
                    Publisher = S.next();
                    System.out.println("Distributor name:");
                    Distributor = S.next();
                    System.out.println("Price");
                    Price = S.next();

                    try{
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO `books`(`Name`, `Author`, `Publisher`, `Distributor`, `Price`) VALUES('"+ Name +"','"+Author+"','"+Publisher+"','"+Distributor+"',"+Price+")");
                        System.out.println("Successful");


                    }
                    catch(Exception object){
                        System.out.println(object);
                    }
                    break;
                case 2:
                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT `id`, `Name`, `Author`, `Publisher`, `Distributor`, `Price` FROM `books` WHERE 1");
                        while(rs.next()){
                            System.out.println("Name = " + rs.getString("Name"));
                            System.out.println("Author = " + rs.getString("Author"));
                            System.out.println("Publisher = " + rs.getString("Publisher"));
                            System.out.println("Distributor = " + rs.getString("Distributor"));
                            System.out.println("Price = " + rs.getInt("Price"));

                        }

                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    try{
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = con.createStatement();
                        int admno;
                        System.out.println("Enter");
                        admno = S.nextInt();
                        stmt.executeUpdate("DELETE FROM `books` WHERE 'Price'= " + admno);
                        System.out.println("Deleted !");
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Invalid");

            }

    }
}}
