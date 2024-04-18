package com.napier.sem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IntegrationTest {
    static App app;

    @BeforeAll
    static void init(){
        //attempt connection to database
        Connection con = App.databaseConnect();
    }

    //testing if database can retrieve the first entry in the city table
    @Test
    void databaseRetrieveTest(){
        Connection con = App.databaseConnect();

        String query = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE ID = 1";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            assertEquals(rs.getInt("ID"), 1);
            assertEquals(rs.getString("Name"), "Kabul");
            assertEquals(rs.getString("CountryCode"), "AFG");
            assertEquals(rs.getString("District"), "Kabol");
            assertEquals(rs.getInt("Population"), 1780000);
        } catch (SQLException e) {
            System.out.println("Database access error:");
            e.printStackTrace();
        }
    }
}
