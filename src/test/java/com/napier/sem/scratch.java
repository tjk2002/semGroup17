package com.napier.sem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;

class MyTest{
    //defining static variables for tests
    static App app;
    static Connection databaseConnection;
    static String input;

    //assigning static variables with values before tests
    @BeforeAll
    static void init(){
        app = new App();
        databaseConnection = null;
        input = "";
    }


    //test if the database is connected
    @Test
    void testDatabaseConnect(){
        assertNull(databaseConnection);
    }

    //test for user input
    @Test
    void testInput(){
        assertNotNull(input);
    }

    //test all reports for null
    @Test
    void testReportNull(){
        app.report1(null);
        app.report2(null);
        app.report3(null);
        app.report4(null);
        app.report5(null);
        app.report6(null);
        app.report7(null);
        app.report8(null);
        app.report9(null);
        app.report10(null);
        app.report11(null);
        app.report12(null);
        app.report13(null);
        app.report14(null);
        app.report15(null);
        app.report16(null);
        app.report17(null);
        app.report18(null);
        app.report19(null);
        app.report20(null);
        app.report21(null);
        app.report22(null);
        app.report23(null);
        app.report24(null);
        app.report25(null);
        app.report26(null);
        app.report27(null);
        app.report28(databaseConnection, input);
        app.report29(databaseConnection, input);
        app.report30(databaseConnection, input);
        app.report31(databaseConnection, input);
        app.report32(databaseConnection);
    }
}