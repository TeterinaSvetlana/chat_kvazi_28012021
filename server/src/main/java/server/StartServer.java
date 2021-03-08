package server;

import java.sql.SQLException;

public class StartServer {
    private static final Object mon = new Object();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        new Server();

        // TODO 3.6
    }
}
