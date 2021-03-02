package server;

import java.sql.SQLException;

public interface AuthService {
    String getNicknameByLoginAndPassword(String login, String password);
    void changeNickname(String oldNickname, String newNickname) throws SQLException;
    boolean registration(String login, String password, String nickname);
    void closeDB() throws SQLException;
}
