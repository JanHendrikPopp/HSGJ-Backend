package de.hb.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.expr.NewArray;

import javax.validation.ConstraintViolation;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("errors")
public class Errors {
	private String id;
	private List<Map<String, String>> errors;

	public Errors(String id, Set<ConstraintViolation<?>> violations) {
		Map<String, String> errors = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			errors.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		List<Map<String, String>> errorList = new ArrayList();
		errorList.add(errors);
		
		this.errors = errorList;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<Map<String, String>> getErrors() {
		return errors;
	}
}