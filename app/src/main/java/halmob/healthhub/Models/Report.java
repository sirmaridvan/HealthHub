package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/30/2017.
 */

public class Report {
    private String reportName;
    private String reportNotes;
    private String reportUri;
    private String date;

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

    public String getReportNotes() { return reportNotes; }

    public void setReportNotes(String reportNotes) { this.reportNotes = reportNotes; }
}
