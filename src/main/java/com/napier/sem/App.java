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
        //connect to database
        Connection con = databaseConnect();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue");
        String input = scanner.nextLine();

        System.out.println("Press enter for report 26:");
        input = scanner.nextLine();
        report26(con);

        System.out.println("Enter input for report 27:");
        input = scanner.nextLine();
        report27(con, input);

        System.out.println("Enter input for report 28:");
        input = scanner.nextLine();
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

    //method to initialise database
    public static Connection databaseConnect(){
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
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
                // Return the connection
                return con;
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        } else {
            System.out.println("Connection failed.");
        }
        return null;
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

    public static void report2(Connection con, String continent) {
    // SQL query to select country names in a specific continent, ordered by population in descending order
    String query = "SELECT Name FROM country WHERE Continent = ? ORDER BY Population DESC";

    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, continent); // Set the continent parameter in the query

        System.out.println("Countries in " + continent + " organized by largest population to smallest:");
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("Name"); // Retrieve the country name from the ResultSet

                System.out.println("Name: " + name); // Print the country name
            }
        }
    } catch (SQLException e) {
        System.out.println("Database access error:");
        e.printStackTrace();
    }
    }

    public static void report3(Connection con, String region) {
    // SQL query to select country names in a specific region, ordered by population in descending order
    String query = "SELECT Name FROM country WHERE Region = ? ORDER BY Population DESC";

    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, region); // Set the region parameter in the query

        System.out.println("Countries in " + region + " organized by largest population to smallest:");
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("Name"); // Retrieve the country name from the ResultSet

                System.out.println("Name: " + name); // Print the country name
            }
        }
    } catch (SQLException e) {
        System.out.println("Database access error:");
        e.printStackTrace();
    }
    }

    public void report4(Object o) {
    }

    public void report5(Object o) {
    }

    public void report6(Object o) {
    }

    public void report7(Object o) {
        String query = "SELECT Name, Population FROM city ORDER BY Population DESC";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Cities in the world organized by largest population to smallest:");
            while (rs.next()) {
                String name = rs.getString("Name"); // Retrieve the city name from the ResultSet
                int population = rs.getInt("Population"); // Retrieve the population of the city

                System.out.println("City: " + name + ", Population: " + population);
            }
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    public void report8(Object o) {
    }

    public void report9(Object o) {
    }

    public void report10(String countryName) throws SQLException {
    String query = "SELECT Name, Population FROM city WHERE CountryCode = ? ORDER BY population DESC";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, countryCode);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report11(String districtName) throws SQLException {
    String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY population DESC";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, districtName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report12(int N) throws SQLException {
    String query = "SELECT Name, Population FROM city ORDER BY Population DESC LIMIT ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report13(String continent, int N) throws SQLException {
    String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY population DESC LIMIT ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, continent);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report14(String region, int N) throws SQLException {
    String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY population DESC LIMIT ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, region);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report15(String countryCode, int N) throws SQLException {
    String query = "SELECT Name, Population FROM city WHERE CountryCode = ? ORDER BY population DESC LIMIT ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, countryCode);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report16(String district, int N) throws SQLException {
    String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY population DESC LIMIT ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, district);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report17() throws SQLException {
    String query = "SELECT Name, Population FROM country WHERE Capital = ? ORDER BY population DESC";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }


    public void report18(String continent) throws SQLException {
    String query = "SELECT Name, Population FROM country WHERE Capital = ? AND continent = ? ORDER BY population DESC";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, continent);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Name") + ": " + rs.getInt("Population"));
        }
    }
    }



    public void report19(String region) {
    String sql = "SELECT city_name, population FROM Cities WHERE is_capital = TRUE AND region = ? ORDER BY population DESC;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, region);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("city_name") + "\t" + rs.getInt("population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public void report20(int N) {
    String query = "SELECT city_name, population FROM Cities WHERE is_capital = TRUE ORDER BY population DESC LIMIT ?;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("city_name") + "\t" + rs.getInt("population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public void report21(String continent, int N) {
    String query = "SELECT city_name, population FROM Cities WHERE is_capital = TRUE AND continent = ? ORDER BY population DESC LIMIT ?;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, continent);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("city_name") + "\t" + rs.getInt("population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public void report22(String region, int N) {
    String query = "SELECT city_name, population FROM Cities WHERE is_capital = TRUE AND region = ? ORDER BY population DESC LIMIT ?;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, region);
        pstmt.setInt(2, N);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("city_name") + "\t" + rs.getInt("population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public void report23() {
    String query = "SELECT continent, SUM(population) AS total_population, SUM(city_population) AS urban_population, (SUM(population) - SUM(city_population)) AS rural_population FROM Countries GROUP BY continent;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("continent") + "\t" +
                               rs.getInt("total_population") + "\t" +
                               rs.getInt("urban_population") + "\t" +
                               rs.getInt("rural_population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }



    public void report24() {
    String query = "SELECT region, SUM(population) AS total_population, SUM(city_population) AS urban_population, (SUM(population) - SUM(city_population)) AS rural_population FROM Countries GROUP BY region;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("region") + "\t" +
                               rs.getInt("total_population") + "\t" +
                               rs.getInt("urban_population") + "\t" +
                               rs.getInt("rural_population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public void report25() {
    String query = "SELECT country_name, population, city_population, (population - city_population) AS rural_population FROM Countries;";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("country_name") + "\t" +
                               rs.getInt("population") + "\t" +
                               rs.getInt("city_population") + "\t" +
                               rs.getInt("rural_population"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }


    public static void report26(Connection con) {
        //query string to return the total sum of the populations in the world
        String query = "SELECT SUM(Population) FROM country";

        //try catch statement to send and return the result of the first query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //printing report results
            System.out.println("Population report for the world");
            System.out.println(rs.getInt("Population"));
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }

    public static void report27(Connection con, String targetContinent) {
        //query string to return the total sum of the populations in a given continent
        String query = "SELECT SUM(Population) FROM country WHERE Continent = "+targetContinent;

        //try catch statement to send and return the result of the first query
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //printing report results
            System.out.println("Population report for the region: "+targetContinent);
            System.out.println(targetContinent);
            System.out.println(rs.getInt("Population"));
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
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

                        //try catch statement to store the population values
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

                //resetting query
                query = "SELECT CountryCode FROM countryLanguage WHERE Language = ";
            }

            //sorting one of the two arrays from highest to lowest
            Arrays.sort(languagePop, Collections.reverseOrder());
            System.out.println("Languages spoken by population from greatest to smallest:");

            //loop to print the population values in the correct order and assigned to the correct language by using the languagePopStorage as a reference for which value is for which language
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
