package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  private final String dbUrl = "jdbc:mysql://localhost/mobilebankingapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") final String name) {
    return new Greeting(this.counter.incrementAndGet(), String.format(template, this.getUserCount()));
  }

  @SuppressWarnings("finally")
  public String getUserCount() {
    String numusers = "";
    final String query = "Select * FROM user_details";
    final String userName = "root", password = "root";
    try {

      Class.forName("com.mysql.jdbc.Driver");
      final Connection con = DriverManager.getConnection(this.dbUrl, userName, password);
      final Statement stmt = con.createStatement();
      final ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        numusers = numusers + rs.getString("name");
      }
      con.close();
    } // end try

    catch (final ClassNotFoundException e) {
      e.printStackTrace();
    }

    catch (final SQLException e) {
      e.printStackTrace();
    } finally {
      return numusers;
    }

  }
}
