package homo;

import static homo.uisql.fl;
import static homo.uisql.paillier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class dbconnect {

    private static final String FILENAME = "encryption key location";
    static pailli paillier;
    static aes aesen;
    static file fl;
    static BigInteger r;

    public static void main(String[] args) {
        try {
            paillier = new pailli();
        } catch (Exception e) {
            e.printStackTrace();

        }
//        Vector[][] contactListNames1 = new Vector[249][25];
//        Vector<Vector<Object>> contactListNames1 = new Vector<Vector<Object>>();
        String[][] contactListNames1 = new String[249][25];
        String[][] contactListNames = new String[249][25];
//        List rowValues = new ArrayList();

        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
//        0        Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare", "root", "Pass@123");
            Statement st = con.createStatement();

            //st.execute("create student(r_no int NOT NULL, nam varchar(20) NOT NULL, sapid int NOT NULL, PRIMARY KEY(sapid))");
            //st.executeUpdate("insert into student values(01,'prakhar',500037782)");
            //st.executeUpdate("insert into student values(02,'kndheeraj',500038333)");
            //st.executeUpdate("insert into student values(03,'chaitanya',500032442)");
            // st.execute("delete from studen where rno=500032442");
            // st.execute("alter table stundata modify id  DECIMAL(65,0)");
            //st.execute("alter table stundata modify sap DECIMAL(65,0)");
            //st.executeUpdate("insert into stundata values(52315609810444231184751228032267191340672981634444423358657636904321213535402167202116416199798297772769791997756983071804835671880409459083309238259328158089779996596657778837643237109990366861037901981205010188500471268291635010940574799551696634605974212652851072079290122426964571268054032095178987166244,55712821074666375008999654345253631123544390482789509687322985316673841915133820961274676183308229317015959189714730953294642608928222356524688119706231883802307772060180485692557526512355392822836131817613273156453879985582491408688329412413592806364099206298108481629081450017654300780350881403382304104966,11615623247546257781825666529429881699648474191769567006381839288551025477960618511056446693044600851336881313537007732998934775132914483399741371522523554143322288830174239566367161949578561578908363794990001619212417412968838269669709613052108220880835405563232361081454190395673211159317011219433882506016)");
            st.executeQuery("select * from ckidney");
//            st.executeQuery("select * from ckidneyen");
            ResultSet rs = st.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int i = 0;
            String qry[] = new String[249];
//                qry[]="insert into ckidneyen values("; 
            //int rowNumber = rs.isLast();
//            BigInteger a1 = new BigInteger(a);
//            BigInteger ena = paillier.Encryption(a1, r);
//            BigInteger enb = paillier.EncrypStr(b, r);
//            BigInteger c1 = new BigInteger(c);
//            BigInteger enc = paillier.Encryption(c1, r);
//            String end = aesen.encrypt(d);

            while (rs.next()) {
                int k = 1;
                int j = 0;
                qry[i] = "insert into ckidneyen values(";
                Vector<Object> vector = new Vector<Object>();
                BigInteger enc = BigInteger.ZERO;
                String str;
                while (k <= columnsNumber) {
//                    System.out.println(k);
                    vector.add(rs.getObject(k));
                    contactListNames[i][j] = vector.elementAt(j).toString();
//                    String str = rs.getString(k);
                    if (isInteger(vector.elementAt(j).toString())) {
                        BigInteger a1 = new BigInteger(vector.elementAt(j).toString());
                        enc = paillier.Encryption(a1);
//                        System.out.println("decrpted info int = " + paillier.Decryption(enc));
                        contactListNames1[i][j] = enc.toString();
                        if (k != columnsNumber) {
                            qry[i] += contactListNames1[i][j] + ",";
                        } else {
                            qry[i] += contactListNames1[i][j] + ")";
                        }
                    } else {
                        if (k != columnsNumber) {
                            qry[i] += "'"+contactListNames1[i][j]+"'" + ",";
                        } else {
                            qry[i] += "'"+contactListNames1[i][j]+"'"  + ")";
                        }
                        //enc = paillier.EncrypStr(vector.elementAt(j).toString(), r);
                        str = vector.elementAt(j).toString();
//                        System.out.println("decrpted info str = " + paillier.DecrpyStr(enc));
                        contactListNames1[i][j] = str;
                    }
//                    BigInteger enc = paillier.EncrypStr(vector.elementAt(j).toString(), r);
//                    System.out.println("vector = "+vector.elementAt(j));

//                    if(str.matches("[0-9]+\\.\\d+")){
//                        System.out.println("str = "+str);
//                         BigInteger a1;
//                        if(str.contains(".")){
//                            a1 = BigDecimal.valueOf(Double.parseDouble(str)).toBigInteger();
//                        }else{
//                            a1 = BigDecimal.valueOf(Integer.parseInt(str)).toBigInteger();
//                        }
//                        System.out.println("a1 = "+a1);
//                        enc = paillier.Encryption(a1, r);
//                         System.out.println("aaacontactListNames1["+i+"]["+j+"] = "+enc);
//                        contactListNames1[i][j].add(enc);
//                        System.out.println("contactListNames1["+i+"]["+j+"] = "+enc);
//                    }else{
//                        
//                    }   
                    System.out.println(contactListNames[i][j]);
                    j++;
                    k++;
                }
                System.out.println("qry = " + qry[i]);
                Statement st1 = con.createStatement();
                st1.executeUpdate(qry[i]);
//                    st1.close();
//                    con.close();
                i++;
//                    rowValues.add(rs.getString(1));
//                    System.out.println("Id is " + rs.getString(1));
//                    System.out.println("Name is " + rs.getString(2));
//                    System.out.println("Sapid is " + rs.getString(3));
                System.out.println("----------------------------");
            }

            try {

//                st1.execute(qry);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            contactListNames = (String[][]) rowValues.toArray(new String[rowValues.size()]);
//            int readlist = contactListNames.length;
//            while(readlist>=0){
//                System.out.println(contactListNames[readlist-1]);
//                readlist--;
//            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in connection" + e);
            e.printStackTrace();
        }

    }

    Connection setConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
