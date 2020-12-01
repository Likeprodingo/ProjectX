package by.shibaev.university.model.pool;

public class ConnectionProperties {
    public static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/university?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    public static final String USERNAME= "root";
    public static final String PASSWORD= "root";

    private ConnectionProperties(){}
}
