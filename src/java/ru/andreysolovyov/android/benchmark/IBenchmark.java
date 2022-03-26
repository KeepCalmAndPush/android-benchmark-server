/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.andreysolovyov.android.benchmark;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author andrey
 */
@WebService
public interface IBenchmark {
   /* @WebMethod
    public List<BenchmarkResults> getResults(@WebParam(name = "overallMark") int overallMark);

    @WebMethod
    public String addResults(@WebParam(name = "newResults") BenchmarkResults newResults);
    * 
    */

    @WebMethod
    public List<BenchmarkResults> submitResults(@WebParam(name = "newResults") BenchmarkResults newResults);
}
