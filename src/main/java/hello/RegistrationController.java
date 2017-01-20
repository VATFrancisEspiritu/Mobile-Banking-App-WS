package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final String dbUrl = "jdbc:mysql://localhost/mobilebankingapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  @RequestMapping("/registerUser")
  public String getUserCount(@RequestParam final String pEMail, final String pFullname, final String pCCNumber, final String pMobileNumber, final String pPassword, final String pBirthdate) {
    final String numusers = "";
    final String insertQuery = "INSERT INTO user_details (EMAIL, NAME, CREDIT_CARD_NUMBER, PHONE_NUMBER, PASSWORD, BIRTHDATE, IS_VERIFIED)" + "VALUES ('" + pEMail + "', '" + pFullname + "', "
        + pCCNumber + ", '" + pMobileNumber + "', '" + pPassword + "', '" + pBirthdate + "', 0)";
    final String userName = "root", password = "root";
    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      final Connection con = DriverManager.getConnection(this.dbUrl, userName, password);
      final Statement stmt = con.createStatement();
      stmt.executeUpdate(insertQuery);

    } catch (final ClassNotFoundException e) {

    } catch (final SQLException e) {
      e.printStackTrace();
    } finally {
    }
    return numusers;

  }
}
