package com.videorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Boolean initDataLoad() {
        try {

            jdbcTemplate.execute("DROP TABLE IF EXISTS CUSTOMER");
            jdbcTemplate.update(
                    "CREATE TABLE CUSTOMER(" +
                            "ID INTEGER Primary key, " +
                            "BONUS INTEGER, " +
                            "FIRST_NAME varchar(30) not null, " +
                            "LAST_NAME varchar(30) not null)"
            );
            jdbcTemplate.update("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (1,3,'DONALD', 'TRUMP')");
            jdbcTemplate.update("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (2,1,'BARACK', 'OBAMA')");
            jdbcTemplate.update("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (3,2,'BILL', 'CLINTON')");
            jdbcTemplate.update("INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (4,1,'VLADIMIR', 'ULIANOV')");

            jdbcTemplate.execute("DROP TABLE IF EXISTS MOVIES");
            jdbcTemplate.update(
                    "CREATE TABLE MOVIES(" +
                            "ID INTEGER Primary key, " +
                            "MOVIETYPEID INTEGER, " +
                            "TITLE varchar(30) not null, " +
                            "DESCRIPTION varchar(200) not null)"
            );
            jdbcTemplate.update("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (1,1,'The Godfather', 'Lorem ipsum...')");
            jdbcTemplate.update("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (2,2,'The Lord of The Rings', 'Lorem ipsum...')");
            jdbcTemplate.update("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (3,1,'Forest Gump', 'Lorem ipsum')");
            jdbcTemplate.update("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (4,2,'Pulp Fiction', 'Lorem ipsum')");
            jdbcTemplate.update("INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (5,3,'Spartacus', 'Lorem ipsum')");

            jdbcTemplate.execute("DROP TABLE IF EXISTS MOVIETYPES");
            jdbcTemplate.update(
                    "CREATE TABLE MOVIETYPES(" +
                            "ID INTEGER Primary key, " +
                            "DESCRIPTION varchar(200) not null)"
            );
            jdbcTemplate.update("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (1,'New release')");
            jdbcTemplate.update("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (2,'Regular Film')");
            jdbcTemplate.update("INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (3,'Old Film')");

            jdbcTemplate.execute("DROP TABLE IF EXISTS TRANSACTIONS");
            jdbcTemplate.update(
                    "CREATE TABLE TRANSACTIONS(" +
                            "ID INTEGER Primary key, " +
                            "CUSTOMER_ID INTEGER, " +
                            "MOVIE_ID INTEGER, " +
                            "N_DAYS INTEGER, " +
                            "N_EXTRA_DAYS INTEGER, " +
                            "PRICE INTEGER, " +
                            "IS_SETTLED INTEGER)"
            );
            jdbcTemplate.update("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (1,1,1,1,0,40,0)");
            jdbcTemplate.update("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (2,1,2,4,3,240,0)");
            jdbcTemplate.update("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (3,2,2,7,0,280,0)");
            jdbcTemplate.update("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (4,3,3,6,2,320,0)");
            jdbcTemplate.update("INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) " +
                    "VALUES (5,4,4,2,1,120,0)");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
