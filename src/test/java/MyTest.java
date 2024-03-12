import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

class MyTest {

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "example");
    }

    @Test
    public void testRetrieveCityByName() throws SQLException {
        String cityName = "Harare";
        try (Connection con = connect()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM city WHERE Name = ?");
            pstmt.setString(1, cityName);
            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next(), "City not found");
            assertEquals("ZWE", rs.getString("CountryCode"));
            assertEquals("Harare", rs.getString("District"));
            assertEquals(1410000, rs.getInt("Population"));
        }
    }
}
