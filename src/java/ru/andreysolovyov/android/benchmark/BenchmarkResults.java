/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.andreysolovyov.android.benchmark;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrey
 */
@XmlRootElement
public class BenchmarkResults implements Serializable {

    BenchmarkResults() {
    }

    BenchmarkResults(String m,
            int iop,
            int fop,
            int dop,
            int om) {
        model = m;
        intOps = iop;
        floatOps = fop;
        doubleOps = dop;
        overallMark = om;
    }
    @XmlElement(required=true)
    String model;
    @XmlElement(required=true)
    int intOps;
    @XmlElement(required=true)
    int floatOps;
    @XmlElement(required=true)
    int doubleOps;
    @XmlElement(required=true)
    int overallMark;

    public String toString() {
        return model + " " + intOps + " " + floatOps + " " + doubleOps + " " + overallMark;
    }
}
