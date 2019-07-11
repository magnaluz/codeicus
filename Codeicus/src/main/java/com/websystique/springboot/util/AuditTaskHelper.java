package com.websystique.springboot.util;

import java.util.Date;

import com.websystique.springboot.model.AuditTask;

public class AuditTaskHelper {

    public static AuditTask createErrorAudit(TaskOperationEnum operation, String parameters, String errorDetail) {

	AuditTask auditTask = new AuditTask();
	auditTask.setDate(new Date());
	auditTask.setOperation(operation.getText());
	auditTask.setParameters(parameters);
	auditTask.setStatus("ERROR");
	auditTask.setErrorDetail(errorDetail);

	return auditTask;
    }

    public static AuditTask createSuccessAudit(TaskOperationEnum operation, String parameters) {

	AuditTask auditTask = new AuditTask();
	auditTask.setDate(new Date());
	auditTask.setOperation(operation.getText());
	auditTask.setParameters(parameters);
	auditTask.setStatus("SUCCESS");

	return auditTask;
    }

}
