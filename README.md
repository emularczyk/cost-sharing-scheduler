# Working with the H2 database

To run the program:
1. Run the application
2. Go to http://localhost:8080/schedulers

To display the project's H2 database:
1. Run the application.
2. Go to http://localhost:8080/console
3. In the form set:
  Driver Class as: org.h2.Driver
  JDBC URL as: jdbc:h2:<path_to_db_files>\database;MODE=MySQL
  , where <path_to_db_files> is the path to cost-sharing-scheduler
  e.g. jdbc:h2:C:\Users\Kornel\Desktop\Studies\Cost-sharing-scheduler/cost-sharing-scheduler\database;MODE=MySQL
  !!!Remaining fields should be left blank!!!
4. Press the connect button. to connect to the base.
