package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private List<UserData> users;


    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    public SimpleAuthService() throws SQLException, ClassNotFoundException{
        setConnection();
        createDb();
        writeDB();
        readDB();

    }

    public static void setConnection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:TEST1.s2db");
    }

    public static void createDb() throws SQLException {
        statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE if not exists 'users'" +
                        "('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'login' text, 'password' text, 'nickname' text);");
    }

    public static void writeDB() throws SQLException {
        if (statement.executeQuery("select count(*) from users") == null) {
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('qwe', 'qwe', 'qwe')");
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('asd', 'asd', 'asd')");
            statement.execute("INSERT INTO 'users' ('login', 'password', 'nickname') VALUES ('zxc', 'zxc', 'zxc')");
        }
    }

    public void readDB() throws SQLException {
        users = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            users.add(new UserData(login, password, nickname));
        }
    }

    @Override
    public void closeDB() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.nickname;
            }
        }

        return null;
    }

    @Override
    public void changeNickname(String oldNickname, String newNickname) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET nickname = ? WHERE nickname = ?");
        preparedStatement.setString(1, newNickname);
        preparedStatement.setString(2, oldNickname);
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData user : users) {
            if (user.login.equals(login) || user.nickname.equals(nickname)) {
                return false;
            }
        }
        users.add(new UserData(login, password, nickname));
        return true;
    }
}
