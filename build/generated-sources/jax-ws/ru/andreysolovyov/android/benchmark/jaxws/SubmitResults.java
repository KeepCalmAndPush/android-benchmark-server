
package ru.andreysolovyov.android.benchmark.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "submitResults", namespace = "http://benchmark.android.andreysolovyov.ru/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitResults", namespace = "http://benchmark.android.andreysolovyov.ru/")
public class SubmitResults {

    @XmlElement(name = "newResults", namespace = "")
    private ru.andreysolovyov.android.benchmark.BenchmarkResults newResults;

    /**
     * 
     * @return
     *     returns BenchmarkResults
     */
    public ru.andreysolovyov.android.benchmark.BenchmarkResults getNewResults() {
        return this.newResults;
    }

    /**
     * 
     * @param newResults
     *     the value for the newResults property
     */
    public void setNewResults(ru.andreysolovyov.android.benchmark.BenchmarkResults newResults) {
        this.newResults = newResults;
    }

}
