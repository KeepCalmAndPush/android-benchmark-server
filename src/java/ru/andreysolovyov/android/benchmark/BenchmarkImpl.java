/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.android.benchmark;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author andrey
 */

@Stateless
@WebService(serviceName="ws",
    endpointInterface = "ru.andreysolovyov.android.benchmark.IBenchmark")
public class BenchmarkImpl implements IBenchmark{
    @EJB
    BenchmarkDAO dao;

    /*
    public List<BenchmarkResults> getResults(int overallMark){
        ArrayList<BenchmarkResults> results = new ArrayList<BenchmarkResults>();

        results = dao.getNearestResults(overallMark);

        /*BenchmarkResults br = new BenchmarkResults("Digma", 123, 234, 345, 1000);
        results.add(br);
        br = new BenchmarkResults("Samsung Galaxy", 123, 234, 345, 900);
        results.add(br);
        br = new BenchmarkResults("iPhone 4", 123, 234, 345, 800);
        results.add(br);
        br = new BenchmarkResults("iPhone 5", 123, 234, 345, 700);
        results.add(br);
        br = new BenchmarkResults("HTC One", 123, 234, 345, 600);
        results.add(br);
         * 

        //DB read
        return results;
    }
    
    
    public String addResults(BenchmarkResults newResults){
        System.out.println("Результаты " + newResults);
        String response = dao.addResults(newResults);
        return response;
    }
*/
    @Override
    public List<BenchmarkResults> submitResults(BenchmarkResults newResults) {
        dao.addResults(newResults);
        return dao.getNearestResults(newResults.overallMark);
    }
 
}
