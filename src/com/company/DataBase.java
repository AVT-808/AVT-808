package com.company;

import com.company.Habitat.Pet.Creatures.Cat;
import com.company.Habitat.Pet.Creatures.Dog;
import com.company.Single.Singleton;

import java.sql.*;

public class DataBase {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void connect() throws ClassNotFoundException, SQLException {
        final String URL = "jdbc:mysql://localhost:3306/database?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, "root", "root");
    }
    public static void create() throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate("drop table pets");
        statement.executeUpdate("create table pets(" +
                "ID int not null auto_increment" +
                ",type int not null" +
                ",x int not null" +
                ",y int not null" +
                ",idPet int not null" +
                ",primary key(id)" +
                ");");
    }
    public static void saveALL() throws SQLException {
        int how = 0;
        for (int i = 0;i<Singleton.getInstance().sizeArray();i++){
            if(Singleton.getInstance().Get(i) instanceof Cat){
                how = 1;
            }else {
                how = 2;
            }
            int petX = Singleton.getInstance().Get(i).getX();
            int petY = Singleton.getInstance().Get(i).getY();
            int id = Singleton.getInstance().Get(i).getID();
            String petsSettings = "insert into pets (type,x,y,idPet) values ("+how+","+petX+","+petY+","+id+")";
            statement.executeUpdate(petsSettings);
        }
    }
    public static void saveCats() throws SQLException {
        int how = 0;
        for (int i = 0;i<Singleton.getInstance().sizeArray();i++){
            if(Singleton.getInstance().Get(i) instanceof Cat){
                how = 1;
                int petX = Singleton.getInstance().Get(i).getX();
                int petY = Singleton.getInstance().Get(i).getY();
                int id = Singleton.getInstance().Get(i).getID();
                String petsSettings = "insert into pets (type,x,y,idPet) values ("+how+","+petX+","+petY+","+id+")";
                statement.executeUpdate(petsSettings);
            }
        }
    }
    public static void saveDogs() throws SQLException {
        int how = 0;
        for (int i = 0;i<Singleton.getInstance().sizeArray();i++){
            if(Singleton.getInstance().Get(i) instanceof Cat){
                how = 2;
                int petX = Singleton.getInstance().Get(i).getX();
                int petY = Singleton.getInstance().Get(i).getY();
                int id = Singleton.getInstance().Get(i).getID();
                String petsSettings = "insert into pets (type,x,y,idPet) values ("+how+","+petX+","+petY+","+id+")";
                statement.executeUpdate(petsSettings);
            }
        }
    }

    public static void downloadALL(int time) throws SQLException {
        Cat myCat;
        Dog myDog;
        resultSet = statement.executeQuery("select * from pets;");
        Singleton.getInstance().Clear();
        Singleton.getInstance().getWindow().quantityPet=0;
        while(resultSet.next()){
            if(resultSet.getInt(2) == 1) {
                myCat = new Cat(resultSet.getInt(4),time);
                myCat.setX(resultSet.getInt(3));
                myCat.setY(resultSet.getInt(4));
                Singleton.getInstance().getArray().add(myCat);
                Singleton.getInstance().getWindow().quantityPet++;
            }else {
                myDog = new Dog(resultSet.getInt(4),time);
                myDog.setX(resultSet.getInt(3));
                myDog.setY(resultSet.getInt(4));
                Singleton.getInstance().getArray().add(myDog);
                Singleton.getInstance().getWindow().quantityPet++;
            }
        }
    }
    public static void downloadCats(int time) throws SQLException {
        Cat myCat;
        resultSet = statement.executeQuery("select * from pets where type=1;");
        Singleton.getInstance().Clear();
        Singleton.getInstance().getWindow().quantityPet=0;
        while(resultSet.next()){
            myCat = new Cat(resultSet.getInt(4),time);
            myCat.setX(resultSet.getInt(3));
            myCat.setY(resultSet.getInt(4));
            Singleton.getInstance().getArray().add(myCat);
            Singleton.getInstance().getWindow().quantityPet++;
        }
    }
    public static void downloadDogs(int time) throws SQLException {
        Dog myDog;
        resultSet = statement.executeQuery("select * from pets where type=2;");
        Singleton.getInstance().Clear();
        Singleton.getInstance().getWindow().quantityPet=0;
        while(resultSet.next()){
            myDog = new Dog(resultSet.getInt(4),time);
            myDog.setX(resultSet.getInt(3));
            myDog.setY(resultSet.getInt(4));
            Singleton.getInstance().getArray().add(myDog);
            Singleton.getInstance().getWindow().quantityPet++;
        }
    }
}
