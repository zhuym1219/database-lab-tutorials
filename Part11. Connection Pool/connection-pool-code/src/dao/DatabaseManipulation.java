package dao;

import Util.DBUtil;
import Util.ProxoolUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulation implements DataManipulation {
    private Connection con;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private DBUtil util;

    public DatabaseManipulation(DBUtil util) {
        this.util = util;
    }

    public List<String> findStationsByLine(int lineId) {
        List<String> stations = new ArrayList<>();
        String sql = "select ld.num, s.english_name, s.chinese_name\n" +
                "from line_detail ld\n" +
                "         join stations s on ld.station_id = s.station_id\n" +
                "where ld.line_id = ?" +
                "order by ld.num;";
        try {
            Thread.sleep(2000);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, lineId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stations.add(String.format("%d, %s, %s", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public void getConnection() {
        con = this.util.getConnection();
        System.out.println("------Thread " + Thread.currentThread().getId() + " visiting DB!------");
        System.out.println(this.util.getConnectState());

    }

    public void closeConnection() {
        this.util.closeConnection(con, preparedStatement, resultSet);
        System.out.println("------Thread " + Thread.currentThread().getId() + " close DB!------");
    }


}
