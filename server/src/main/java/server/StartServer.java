package server;

import java.sql.SQLException;

public class StartServer {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Server();

//      TODO
//        1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
//        2. После загрузки клиента показывать ему последние 100 строк чата.
    }
}
