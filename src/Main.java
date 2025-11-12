import Connection.JDBC;
import UI.HomeFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        JDBC.getConnection();
        HomeFrame frame = new HomeFrame();
        frame.setVisible(true);
    }
}
