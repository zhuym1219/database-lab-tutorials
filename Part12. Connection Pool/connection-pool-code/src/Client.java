import Util.OrdinaryUtil;
import Util.ProxoolUtil;
import dao.DataManipulation;
import dao.DatabaseManipulation;

public class Client {

    public static void main(String[] args) {

        try {
            dbRequestArrived(3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dbRequestArrived(5);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void dbRequestArrived(int count) {
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                DataManipulation dm = new DatabaseManipulation(ProxoolUtil.getInstance());
                dm.getConnection();
                dm.findStationsByLine(1);
                dm.closeConnection();
            }).start();
        }
    }
}

