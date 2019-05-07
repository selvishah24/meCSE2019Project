package homo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class createTable {

//  private static final String EMPLOYEE_TABLE = "create table  ( "
//      + "   age INT(11), al int(11),  VARCHAR(20), "
//      + "   title VARCHAR(20), salary INT )";

  public static Connection getConnection() throws Exception {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/healthcare";
    String username = "root";
    String password = "Pass@123";
    Class.forName(driver);
    Connection con = DriverManager.getConnection(url, username, password);
    return con;
  }

  public static void main(String args[]) {
    Connection con = null;
    Statement st = null;
    String str[][]=new String[1][25];
    int i = 0;
    String num[] = new String[25];
    while(i<25){
        num[i] = "'"+i+"'" ;
        str[0][i]= num[i];
        i++;
    }
    
    try {
      con = getConnection();
      st = con.createStatement();
//      st.executeUpdate(EMPLOYEE_TABLE);
            st.execute("insert into ckidneyen values("+str[0][0]+","+str[0][1]+","+str[0][2]+","+str[0][3]+","+str[0][4]+","+str[0][5]+","+str[0][6]+","+str[0][7]+","+str[0][8]+","+str[0][9]+","+str[0][10]+","+str[0][11]+","+str[0][12]+","+str[0][13]+","+str[0][14]+","+str[0][15]+","+str[0][16]+","+str[0][17]+","+str[0][18]+","+str[0][19]+","+str[0][20]+","+str[0][21]+","+str[0][22]+","+str[0][23]+","+str[0][24]+")");
//      st.executeUpdate("insert into Employ(id, firstName) values(200, 'B')");
//      System.out.println("CreateEmployeeTableMySQL: main(): table created.");
    } catch (ClassNotFoundException e) {
      System.out.println("error: failed to load MySQL driver.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("error: failed to create a connection object.");
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("other error:");
      e.printStackTrace();
    } finally {
      try {
        st.close();
        con.close();        
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}