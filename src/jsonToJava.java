import java.sql.Connection;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
ArrayList<CustomerDetails> a = new ArrayList();

conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");

//object of statement class will help execute queries
Statement st=conn.createStatement();
ResultSet rs = st.executeQuery("Select * from CustomerInfo where Location = 'Asia';");
while (rs.next())//setting pointer to particular row
{
	//POJO Plain old java object
// need different java objects
	CustomerDetails c = new CustomerDetails();
	c.setCourseName(rs.getString(1));
	c.setPurchasedDate(rs.getString(2));
	c.setAmount(rs.getInt(3));
	c.setLocation(rs.getString(4));	

	a.add(c);
	
}

for(int i=0; i <a.size();i++)
{
	
ObjectMapper o = new ObjectMapper();
o.writeValue(new File("C:\\Users\\sammi\\eclipse-workspace\\JsonJav\\customerinfo"+i+".json"),a.get(i));
}
conn.close();
	}

}
