package com.videorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Component
public class DataLoadServiceImpl implements DataLoadService{

    @Autowired
    private DataSource dataSource;

    @Override
    public Boolean initDataLoad() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS CUSTOMER");
            statement.executeUpdate(
                    "CREATE TABLE CUSTOMER(" +
                            "ID INTEGER Primary key, " +
                            "BONUS INTEGER, " +
                            "FIRST_NAME varchar(30) not null, " +
                            "LAST_NAME varchar(30) not null)"
            );
            statement.executeUpdate("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (1,0,'DONALD', 'TRUMP')");
            statement.executeUpdate("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (2,0,'BARACK', 'OBAMA')");
            statement.executeUpdate("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (3,0,'BILL', 'CLINTON')");
            statement.executeUpdate("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (4,0,'VLADIMIR', 'ULIANOV')");

            statement.execute("DROP TABLE IF EXISTS MOVIES");
            statement.executeUpdate(
                    "CREATE TABLE MOVIES(" +
                            "ID INTEGER Primary key, " +
                            "MOVIETYPEID INTEGER, " +
                            "TITLE varchar(30) not null, " +
                            "DESCRIPTION varchar(200) not null)"
            );
            statement.executeUpdate("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (1,1,'The Godfather', 'Lorem ipsum...')");
            statement.executeUpdate("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (2,2,'The Lord of The Rings', 'Lorem ipsum...')");
            statement.executeUpdate("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (3,1,'Forest Gump', 'Lorem ipsum')");
            statement.executeUpdate("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (4,2,'Pulp Fiction', 'Lorem ipsum')");
            statement.executeUpdate("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (5,3,'Spartacus', 'Lorem ipsum')");

            statement.execute("DROP TABLE IF EXISTS MOVIETYPES");
            statement.executeUpdate(
                    "CREATE TABLE MOVIETYPES(" +
                            "ID INTEGER Primary key, " +
                            "DESCRIPTION varchar(200) not null)"
            );
            statement.executeUpdate("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (1,'New release')");
            statement.executeUpdate("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (2,'Regular Film')");
            statement.executeUpdate("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (3,'Old Film')");

            statement.execute("DROP TABLE IF EXISTS TRANSACTIONS");
            statement.executeUpdate(
                    "CREATE TABLE TRANSACTIONS(" +
                            "ID INTEGER Primary key, " +
                            "CUSTOMER_ID INTEGER, " +
                            "MOVIE_ID INTEGER, " +
                            "N_DAYS INTEGER, " +
                            "N_EXTRA_DAYS INTEGER, " +
                            "PRICE INTEGER, " +
                            "IS_SETTLED INTEGER)"
            );
            statement.executeUpdate("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (1,1,1,1,0,40,0)");
            statement.executeUpdate("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (2,1,2,4,3,240,0)");
            statement.executeUpdate("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (3,2,2,7,0,280,0)");
            statement.executeUpdate("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (4,3,3,6,2,320,0)");
            statement.executeUpdate("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (5,4,4,2,1,120,0)");
            statement.close();
            connection.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
