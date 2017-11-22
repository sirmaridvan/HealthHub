package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/22/2017.
 */

public class MedicalAnalysis {
    String reportName;

    public String getReportUri() {
        return reportUri;
    }

    public void setReportUri(String reportUri) {
        this.reportUri = reportUri;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String reportUri;
    String date;
}
