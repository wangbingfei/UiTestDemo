package com.zmc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class DBConnection
{
    String driver = "com.mysql.jdbc.Driver";
    String url= "jdbc:mysql://192.168.22.251:3306/mstar_integration?useUnicode=true&characterEncoding=utf8";
    String user = "root";
    String password = "isginte123";
    PreparedStatement ps;
    ResultSet rs;
    Connection conn;


    public static void main(String[] args) throws UnsupportedEncodingException
    {

        DBConnection dbConnection = new DBConnection();
        dbConnection.select();
        adb();
    }

    public static void adb()
    {
        try
        {
			/*
			 终端查询设备名称
			 路径为sdk路径
			 */
            String cmd = "/Users/wangbingfei/Library/Android/sdk/platform-tools/adb devices";
            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader ir  = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String line;
            while((line = input.readLine()) != null)
            {
                System.out.println(line);
            }

            //String command = "adb shell input tap 996 1582";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }

    }


    public Connection getConnection()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("找不到驱动！");
        }
        try
        {
            conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("数据库连接失败！");

        }
        return conn;
    }

    public void select() throws UnsupportedEncodingException
    {
        String name = null ;
        String id = null;
        String sql = "select * from u_user where user_id=26133";
        conn = getConnection();//此处为通过自己写的方法getConnection()获得连接
        try
        {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                // m1 = rs.getInt(1);//或者为rs.getString(1)，根据数据库中列的值类型确定，参数为第一列
                name = rs.getString("nike_name");
                id = rs.getString("user_id");
            }

            System.out.println("------------—");
            System.out.println(" 数据库查询结果:");
            System.out.println("-------------");
            System.out.println(" nike_name");
            System.out.println("-------------");
            System.out.println(name);

            String updatenike_name = null;
            ps = conn.prepareStatement("update u_user set nike_name = ? where user_id = 26133");
            ps.setString(1, "王秉飞");
            ps.executeUpdate();

            ps = conn.prepareStatement("select * from u_user where user_id=26133");
            rs = ps.executeQuery();
            while(rs.next())
            {
                updatenike_name = rs.getString("nike_name");
            }

            System.out.println("-----------—");
            System.out.println("数据库修改结果:");
            System.out.println("------------");
            System.out.println(" nike_name");
            System.out.println("------------");
            System.out.println(updatenike_name);

            rs.close();
            ps.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
