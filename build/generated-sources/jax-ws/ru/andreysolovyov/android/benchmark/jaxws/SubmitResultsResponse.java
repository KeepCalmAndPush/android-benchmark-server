
package ru.andreysolovyov.android.benchmark.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "submitResultsResponse", namespace = "http://benchmark.android.andreysolovyov.ru/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitResultsResponse", namespace = "http://benchmark.android.andreysolovyov.ru/")
public class SubmitResultsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<ru.andreysolovyov.android.benchmark.BenchmarkResults> _return;

    /**
     * 
     * @return
     *     returns List<BenchmarkResults>
     */
    public List<ru.andreysolovyov.android.benchmark.BenchmarkResults> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<ru.andreysolovyov.android.benchmark.BenchmarkResults> _return) {
        this._return = _return;
    }

}
