package E2E.tests;

import E2E.DBconnection.dbConnection;
import E2E.TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBDataTest extends BaseTest{

    dbConnection db = new dbConnection();

    @Test(enabled = true)
    public void loginDBData() throws SQLException, IOException {
        Map<String,String> data = getQueries();
        String query = data.get("getCred");
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,2);
        ResultSet rs = ps.executeQuery();
        System.out.println(rs);
        while(rs.next()) {
            signIn.LoginUser(rs.getString("username"), rs.getString("password"));
        }
    }

    @Test
    public void MultipleValue() throws SQLException, IOException {
        List<String> idsList = new ArrayList<>(); //Creating empty list

        Map<String,String> data = getQueries();
        String idQuery = data.get("getIds"); //to get all ids

        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(idQuery);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            idsList.add(rs.getString("id"));
        }

        String nameQuery = data.get("getAllNames");
       String placeHolders = idsList.stream()
                .map(x->"?")
                .collect(Collectors.joining(","));

       nameQuery = nameQuery.replace("?",placeHolders);
        PreparedStatement ps1 = con.prepareStatement(nameQuery);

        for(int i=0;i<idsList.size();i++) {
            ps1.setInt(i + 1, Integer.parseInt(idsList.get(i)));
        }
        ResultSet rs1 = ps1.executeQuery();
        while(rs1.next())
        {
           System.out.println(rs1.getString("name"));
        }
    }
}
