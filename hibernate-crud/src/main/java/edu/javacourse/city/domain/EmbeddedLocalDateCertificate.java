package edu.javacourse.city.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class EmbeddedLocalDateCertificate {
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
