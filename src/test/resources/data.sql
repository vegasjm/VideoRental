INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (1,3,'DONALD', 'TRUMP');
INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (2,1,'BARACK', 'OBAMA');
INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (3,2,'BILL', 'CLINTON');
INSERT INTO CUSTOMER (ID, BONUS, FIRST_NAME, LAST_NAME) VALUES (4,1,'VLADIMIR', 'ULIANOV');
INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (1,1,'The Godfather', 'Lorem ipsum...');
INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (2,2,'The Lord of The Rings', 'Lorem ipsum...');
INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (3,1,'Forest Gump', 'Lorem ipsum');
INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (4,2,'Pulp Fiction', 'Lorem ipsum');
INSERT INTO MOVIES (ID, MOVIETYPEID, TITLE, DESCRIPTION) VALUES (5,3,'Spartacus', 'Lorem ipsum');
INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (1,'New release');
INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (2,'Regular Film');
INSERT INTO MOVIETYPES (ID, DESCRIPTION) VALUES (3,'Old Film');
INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) VALUES (1,1,1,1,0,40,0);
INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) VALUES (2,1,2,4,3,240,0);
INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) VALUES (3,2,2,7,0,280,0);
INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) VALUES (4,3,3,6,2,320,0);
INSERT INTO TRANSACTIONS (ID,CUSTOMER_ID,MOVIE_ID,N_DAYS,N_EXTRA_DAYS,PRICE,IS_SETTLED) VALUES (5,4,4,2,1,120,0);