package eu.acewolf.playerhider.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatenbankMethods {
    public boolean isExist(String uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM playerhidermanager WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception exception) {
            return false;
        }
    }

    public void addDatabase(String uuid){
        try {
            if (!isExist(uuid)) {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO playerhidermanager (UUID,MODE) VALUES (?,?)");
                ps.setString(1, uuid);
                ps.setInt(2, 1);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMode(String uuid, Integer mode){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE playerhidermanager SET " + "MODE" + " = ? WHERE UUID = ?");
            ps.setInt(1, mode);
            ps.setString(2, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getMode(String uuid){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM playerhidermanager WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt("MODE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
