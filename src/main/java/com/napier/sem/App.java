package com.napier.sem;

import java.sql.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 3;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(5000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");

                PreparedStatement p = con.prepareStatement("SELECT name FROM city WHERE ID=1");
                try (ResultSet rs = p.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("name = " + rs.getString("name"));
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error: " + e.toString());
                }

                // Wait a bit
                Thread.sleep(5000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    //methods for all reports
    public void report1(Object o) {
    }

    public void report2(Object o) {
    }

    public void report3(Object o) {
    }

    public void report4(Object o) {
    }

    public void report5(Object o) {
    }

    public void report6(Object o) {
    }

    public void report7(Object o) {
    }

    public void report8(Object o) {
    }

    public void report9(Object o) {
    }

    public void report10(Object o) {
    }

    public void report11(Object o) {
    }

    public void report12(Object o) {
    }

    public void report13(Object o) {
    }

    public void report14(Object o) {
    }

    public void report15(Object o) {
    }

    public void report16(Object o) {
    }

    public void report17(Object o) {
    }

    public void report18(Object o) {
    }

    public void report19(Object o) {
    }

    public void report20(Object o) {
    }

    public void report21(Object o) {
    }

    public void report22(Object o) {
    }

    public void report23(Object o) {
    }

    public void report24(Object o) {
    }

    public void report25(Object o) {
    }

    public void report26(Object o) {
    }

    public void report27(Object o) {
    }

    public void report28(Object o) {
    }

    public void report29(Object o) {
    }

    public void report30(Object o) {
    }

    public void report31(Object o) {
    }

    public void report32(Object o) {
    }

    public void report33(Object o) {
    }

    public void report34(Object o) {
    }

    public void report35(Object o) {
    }

    public void report36(Object o) {
    }
}