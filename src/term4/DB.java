package term4;

import java.sql.*;

public class DB {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static final int CAPITAL = 1;
    private static final int WOODEN = 2;

    public static void connect() throws ClassNotFoundException, SQLException {
        final String URL = "jdbc:mysql://localhost:3306/houses";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, "root", "1337");
    }

    public static void create() throws SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS houses (" +
                "id DOUBLE, " +
                "type INT, " +
                "x INT, " +
                "y INT, " +
                "speed INT);");

    }

    public static void write() throws SQLException {
        statement.execute("DELETE FROM houses");
        int type = -1;
        for (int i = 0; i < Singleton.getSingleton().getHouseVector().size(); i++) {
            if(Singleton.getSingleton().getHouse(i).getTypeOfHouse() == TypeOfHouse.CAPITAL){
                if(Main.habitat.getMyComponent().getjCheckBoxCapitalDB().isSelected()) {
                    type = CAPITAL;
                    statement.execute("INSERT INTO houses " +
                            "(id, type, x, y, speed) VALUES" +
                            " ('" + Singleton.getSingleton().getHouse(i).getId() + "', '" + type + "', '" + Singleton.getSingleton().getHouse(i).getX() + "','" +
                            Singleton.getSingleton().getHouse(i).getY() + "','" + Singleton.getSingleton().getHouse(i).getSpeed() + "');");

                }
            }
            else {
                if(Main.habitat.getMyComponent().getjCheckBoxWoodenDB().isSelected()) {
                    type = WOODEN;
                    statement.execute("INSERT INTO houses " +
                            "(id, type, x, y, speed) VALUES" +
                            " ('" + Singleton.getSingleton().getHouse(i).getId() + "', '" + type + "', '" + Singleton.getSingleton().getHouse(i).getX() + "','" +
                            Singleton.getSingleton().getHouse(i).getY() + "','" + Singleton.getSingleton().getHouse(i).getSpeed() + "');");

                }
            }
        }
    }

    public static void read() throws SQLException {
        Singleton.getSingleton().clearArea();
        MyComponent.setCount(0);
        if(Main.habitat.getMyComponent().getjCheckBoxWoodenDB().isSelected() &&
           Main.habitat.getMyComponent().getjCheckBoxCapitalDB().isSelected()) {
            resultSet = statement.executeQuery("SELECT * FROM houses");
        }
        else if(Main.habitat.getMyComponent().getjCheckBoxCapitalDB().isSelected()){
            resultSet = statement.executeQuery("SELECT * FROM houses WHERE type = '" + CAPITAL + "'");
        }
        else if(Main.habitat.getMyComponent().getjCheckBoxWoodenDB().isSelected()){
            resultSet = statement.executeQuery("SELECT * FROM houses WHERE type = '" + WOODEN + "'");
        }
        else if(!Main.habitat.getMyComponent().getjCheckBoxWoodenDB().isSelected() &&
                !Main.habitat.getMyComponent().getjCheckBoxCapitalDB().isSelected()) {
            return;
        }

        while (resultSet.next()){
            if (resultSet.getInt("type") == CAPITAL) {

                AbstractFactory abstractFactory = ConcreteFactory.concreteFactory(TypeOfHouse.CAPITAL);
                House house = abstractFactory.createHouse();
                house.setTimeOfBirth(MyComponent.getTime());
                Singleton.getSingleton().getHouseVector().add(house);
                house.setTimeOfBirth(MyComponent.getTime());
                house.setPosition(resultSet.getInt("x"), resultSet.getInt("y"));
                house.setSpeed(resultSet.getInt("speed"));
                MyComponent.setCount(Singleton.getSingleton().getHouseVector().size());
            }
            else if(resultSet.getInt("type") == WOODEN){
                AbstractFactory abstractFactory = ConcreteFactory.concreteFactory(TypeOfHouse.WOODEN);
                House house = abstractFactory.createHouse();
                house.setTimeOfBirth(MyComponent.getTime());
                Singleton.getSingleton().getHouseVector().add(house);
                house.setTimeOfBirth(MyComponent.getTime());
                house.setPosition(resultSet.getInt("x"), resultSet.getInt("y"));
                house.setSpeed(resultSet.getInt("speed"));
                MyComponent.setCount(Singleton.getSingleton().getHouseVector().size());
            }
        }
    }

    public static void close() throws SQLException {
        connection.close();
        statement.close();
        if(resultSet != null){
            resultSet.close();
        }
    }
}
