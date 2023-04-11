# cost-sharing-scheduler

# Working with the H2 database

1. Go to http://localhost:8080/console.
2. In the form set:
Driver as : org.h2.Driver 
JDBC url as : jdbc:h2:<path_to_db_files>\database;MODE=MySQL
, where <path_to_db_files> is path to your database.mv.db file.
3. Connect and modify database.
Hint: H2 syntax is very rigorous (in contrast to mySql), so any inaccurate entry in your code may cause error.
