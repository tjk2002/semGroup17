package com.napier.sem;

import java.sql.*;
import java.util.Objects;

public class UnitTests {
    //test if the database has been connected
    public boolean testDatabaseConnect(Connection connection){
        return connection != null;
    }

    //test for user input
    public boolean testInput(String input){
        return !Objects.equals(input, "");
    }

    //test for report output
    public boolean testReportOutput(String output){
        return !Objects.equals(output, "");
    }

    //test for the order of the output (largest to smallest)
    public boolean testOutputOrderLtS(Report[] output){
        int orderTracker = output[0].population;

        for(int i=0;i<output.length;i++){
            if(output[i].population <= orderTracker){
                orderTracker = output[i].population;
            }
            else{
                return false;
            }
        }

        return true;
    }

    //test for the order of the output (smallest to largest)
    public boolean testOutputOrderStL(Report[] output){
        int orderTracker = output[0].population;

        for(int i=0;i<output.length;i++){
            if(output[i].population >= orderTracker){
                orderTracker = output[i].population;
            }
            else{
                return false;
            }
        }

        return true;
    }
}
