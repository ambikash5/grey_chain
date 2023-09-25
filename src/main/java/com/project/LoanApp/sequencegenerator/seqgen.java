package com.project.LoanApp.sequencegenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.ReturningWork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class seqgen implements IdentifierGenerator {


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String prefix ="L";
        String suffix="";
        try{
            Connection con = sharedSessionContractImplementor.doReturningWork(new ReturningWork<Connection>() {
                @Override
                public Connection execute(Connection connection) throws SQLException {
                    return connection;
                }
            });
            Statement stm = con.createStatement();
            String query = "SELECT nextval('loan_tbl_sequence')";
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                int seqval= rs.getInt(1);
                suffix = String.valueOf(seqval);
            }
        }

        catch (Exception e){
            e.printStackTrace();

        }
        return prefix+suffix;
    }


}
