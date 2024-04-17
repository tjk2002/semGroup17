package com.napier.sem;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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

               report1(con);

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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input for report 28:");
        String input = scanner.nextLine();
        report28(con, input);

        System.out.println("Enter input for report 29:");
        input = scanner.nextLine();
        report29(con, input);

        System.out.println("Enter input for report 30:");
        input = scanner.nextLine();
        report30(con, input);

        System.out.println("Enter input for report 31:");
        input = scanner.nextLine();
        report31(con, input);

        System.out.println("Press enter for report 32:");
        input = scanner.nextLine();
        report32(con);
    }

    //methods for all reports
    public static void report1(Connection con) {
        // Simplified query to select only country names, ordered by population in descending order
        String query = "SELECT Name FROM country ORDER BY Population DESC";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Countries in the world organized by largest population to smallest:");
            while (rs.next()) {
                String name = rs.getString("Name"); // Retrieve the country name from the ResultSet

                System.out.println("Name: " + name); // Print the country name
            }
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
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

    //method that takes a target region and returns a population report
    public static void report28(Connection con, String targetRegion) {
        int popInCity = 0;
        //query string to return the total sum of the populations in a given region
        String query = "SELECT SUM(Population) FROM country WHERE Region = "+targetRegion;
        //query string to return the codes of all countries in a given region
        String query2 = "SELECT Code FROM country WHERE Region = "+targetRegion;

        //try catch statement to send and return the result of the first query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //try catch statement to send and return the result of the second query
            try(Statement stmt2 = con.createStatement(); ResultSet rs2 = stmt2.executeQuery(query2)){
                //loop through all codes from result
                while(rs2.next()){
                    //query string to return the sum of the populations in a given country that are in a city
                    String query3 = "SELECT SUM(Population) FROM city WHERE CountryCode = "+rs2.getString("Code");
                    //try catch statement to send and return the result of the third query
                    try(Statement stmt3 = con.createStatement(); ResultSet rs3 = stmt3.executeQuery(query3)){
                        //collect the total cum of the populations of all cities within the country
                        popInCity += rs3.getInt("Population");
                    } catch(SQLException e){
                        System.out.println("Database access error:");
                        e.printStackTrace();
                    }
                }
            } catch(SQLException e){
                System.out.println("Database access error:");
                e.printStackTrace();
            }

            //printing report results
            System.out.println("Population report for the region: "+targetRegion);
            System.out.println(targetRegion);
            System.out.println(rs.getInt("Population"));
            System.out.println("Total in city: "+popInCity+" (%"+((popInCity / rs.getInt("Population")) * 100)+")");
            System.out.println("Total not in city: "+(rs.getInt("Population") - popInCity)+" (%"+(((rs.getInt("Population") - popInCity) / rs.getInt("Population")) * 100)+")");
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    //method that takes a target country and returns a population report
    public static void report29(Connection con, String targetCountry) {
        int popInCity = 0;
        //query string to return the population of a given country
        String query = "SELECT SUM(Population) FROM country WHERE Name = "+targetCountry;
        //query string to return the code of the given country
        String query2 = "SELECT Code FROM country WHERE Name = "+targetCountry;

        //try catch statement to send and return the result of the first query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //try catch statement to send and return the result of the second query
            try(Statement stmt2 = con.createStatement(); ResultSet rs2 = stmt.executeQuery(query2)){
                //query string to return the total sum of the populations in a given country that are in a city
                String query3 = "SELECT SUM(Population) FROM city WHERE CountryCode = "+rs2.getString("Code");
                //try catch statement to send and return the result of the third query
                try(Statement stmt3 = con.createStatement(); ResultSet rs3 = stmt.executeQuery(query3)){
                    popInCity = rs3.getInt("Population");
                } catch (SQLException e) {
                    System.out.println("Database access error:");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Database access error:");
                e.printStackTrace();
            }

            //printing report results
            System.out.println("Population report for the country: "+targetCountry);
            System.out.println(targetCountry);
            System.out.println(rs.getInt("Population"));
            System.out.println("Total in city: "+popInCity+" (%"+((popInCity / rs.getInt("Population")) * 100)+")");
            System.out.println("Total not in city: "+(rs.getInt("Population") - popInCity)+" (%"+(((rs.getInt("Population") - popInCity) / rs.getInt("Population")) * 100)+")");
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    //method that takes a target district and returns a population report
    public static void report30(Connection con, String targetDistrict) {
        //query string to return the total sum of the populations of a given district
        String query = "SELECT SUM(Population) FROM city WHERE District = "+targetDistrict;

        //try catch statement to send and return the result of the query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //printing report results
            System.out.println("Population report for the district: "+targetDistrict);
            System.out.println(targetDistrict);
            System.out.println(rs.getInt("Population"));
            System.out.println("Total in city: "+rs.getInt("Population")+" (%100)");
            System.out.println("Total not in city: 0 (%0)");
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    //method that takes a target city and returns a population report
    public static void report31(Connection con, String targetCity) {
        //query string to return the population of a given city
        String query = "SELECT SUM(Population) FROM city WHERE District = "+targetCity;

        //try catch statement to send and return the result of the query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Population report for the city: "+targetCity);
            System.out.println(targetCity);
            System.out.println(rs.getInt("Population"));
            System.out.println("Total in city: "+rs.getInt("Population")+" (%100)");
            System.out.println("Total not in city: 0 (%0)");
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    //method that takes some target languages and returns them in order of population
    public static void report32(Connection con) {
        Integer[] languagePop = {0, 0, 0, 0, 0};
        int[] languagePopStorage = {0, 0, 0, 0, 0};
        String[] languages = {"Chinese", "English", "Hindi", "Spanish", "Arabic"};
        //query string to return the country codes for countries that speak the required languages
        String query = "SELECT CountryCode FROM countryLanguage WHERE Language = ";
        //query string to return the population of the world
        String query2 = "SELECT SUM(Population) FROM country";

        //try catch statement to send and return the result of the second query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query2)){
            //loop to run the try catch statement to send and return the result of the first query for each of the required languages
            for(int i=0; i<5; i++){
                query += languages[i];

                try (Statement stmt2 = con.createStatement(); ResultSet rs2 = stmt.executeQuery(query)){
                    //loop to run the try catch statement to send and return the result of the third query for each of the country codes
                    while(rs2.next()){
                        String query3 = "SELECT SUM(Population) FROM country WHERE Code = "+rs2.getString("CountryCode");

                        try (Statement stmt3 = con.createStatement(); ResultSet rs3 = stmt.executeQuery(query)){
                            languagePop[i] += rs3.getInt("Population");
                            languagePopStorage[i] += rs3.getInt("Population");
                        } catch (SQLException e) {
                            System.out.println("Database access error:");
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Database access error:");
                    e.printStackTrace();
                }

                query = "SELECT CountryCode FROM countryLanguage WHERE Language = ";
            }

            Arrays.sort(languagePop, Collections.reverseOrder());
            System.out.println("Languages spoken by population from greatest to smallest:");

            for(int i=0; i<5; i++){
                int u = 0;
                int found = 0;

                while(found != 1){
                    if(languagePop[u] == languagePopStorage[i]){
                        System.out.println(languages[i]+": "+languagePop[u]+ " (%"+((languagePop[u] / rs.getInt("Population")) * 100)+")");
                        found = 1;
                    }
                    else{
                        u += 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }
}