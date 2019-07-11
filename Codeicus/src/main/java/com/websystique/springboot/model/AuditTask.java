package com.websystique.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TB_AUDIT_TASK")
public class AuditTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "OPERATION", nullable = false)
    private String operation;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "PARAMETERS")
    private String parameters;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ERROR_DETAIL")
    private String errorDetail;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getOperation() {
	return operation;
    }

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getParameters() {
	return parameters;
    }

    public void setParameters(String parameters) {
	this.parameters = parameters;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getErrorDetail() {
	return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
	this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("AuditTask [id=");
	builder.append(id);
	builder.append(", operation=");
	builder.append(operation);
	builder.append(", date=");
	builder.append(date);
	builder.append(", parameters=");
	builder.append(parameters);
	builder.append(", status=");
	builder.append(status);
	builder.append(", errorDetail=");
	builder.append(errorDetail);
	builder.append("]");
	return builder.toString();
    }

}
