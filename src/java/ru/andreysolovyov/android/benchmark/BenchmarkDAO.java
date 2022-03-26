package ru.andreysolovyov.android.benchmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author andrey
 */
@Stateful
public class BenchmarkDAO {

    @Resource(name = "jdbc/AndroidBenchmark")
    protected DataSource ds;
    protected Connection connection;
    private static final String EXCEPTION = "Server-side fault";
    private static final String ADD_RESULTS = "INSERT INTO RESULTS "
            + "(model,  intOps, floatOps, doubleOps, overallMark, submissionDate) "
            + "values(?, ?, ?, ?, ?, ?);";

    private static final String GET_NEAREST_RESULTS = "(SELECT model, "
            + "AVG(intOps) AS intOps, AVG(floatOps) AS floatOps, AVG(doubleOps) "
            + "AS doubleOps, AVG(overallMark) AS overallMark  FROM RESULTS "
            + "where overallMark > ? GROUP BY model order by overallMark desc limit 5 ) "
            + "UNION ALL (SELECT model, AVG(intOps) AS intOps, AVG(floatOps) "
            + "AS floatOps, AVG(doubleOps) AS doubleOps, AVG(overallMark) "
            + "AS overallMark  FROM RESULTS where overallMark < ? "
            + "GROUP BY model order by overallMark desc limit 5)";

    @PostConstruct
    public void init() {
        try {
            connection = ds.getConnection();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex.getMessage());
            Logger.getLogger(BenchmarkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex.getMessage());
            Logger.getLogger(BenchmarkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String addResults(BenchmarkResults newResults) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(ADD_RESULTS);
            ps.setString(1, newResults.model);
            ps.setInt(2, newResults.intOps);
            ps.setInt(3, newResults.floatOps);
            ps.setInt(4, newResults.doubleOps);
            ps.setInt(5, newResults.overallMark);
            ps.setTimestamp(6, new Timestamp((new Date()).getTime()));

            ps.executeUpdate();
        } catch (Exception se) {
            System.out.println();
            se.printStackTrace();
            return EXCEPTION;
        } finally {
            try {
                ps.close();
            } catch (SQLException se) {
                System.out.println("SQL Exception closing ps");
                se.printStackTrace();
                return EXCEPTION;
            } catch (NullPointerException npe) {
                System.out.println("Null pointer exception");
                npe.printStackTrace();
                return EXCEPTION;
            }
        }
        return "OK";
    }
    
    public ArrayList<BenchmarkResults> getNearestResults(int overallMark) {
        PreparedStatement ps = null;
        ArrayList<BenchmarkResults> nearestResultsList = new ArrayList<BenchmarkResults>();
        try {
            ps = connection.prepareStatement(GET_NEAREST_RESULTS);
            ps.setInt(1, overallMark);
            ps.setInt(2, overallMark);

            ResultSet nearestResults = ps.executeQuery();
            if (nearestResults != null) {
                while (nearestResults.next()) {
                    BenchmarkResults br = new BenchmarkResults(
                            nearestResults.getString("model"),
                            nearestResults.getInt("intOps"),
                            nearestResults.getInt("floatOps"),
                            nearestResults.getInt("doubleOps"),
                            nearestResults.getInt("overallMark")
                            );
                    nearestResultsList.add(br);
                }
            }
        } catch (Exception se) {
            System.out.println();
            se.printStackTrace();
            return null;
        } finally {
            try {
                ps.close();
            } catch (SQLException se) {
                System.out.println("SQL Exception closing ps");
                se.printStackTrace();
                return null;
            } catch (NullPointerException npe) {
                System.out.println("Null pointer exception");
                npe.printStackTrace();
                return null;
            }
        }
        return nearestResultsList;
    }
}
