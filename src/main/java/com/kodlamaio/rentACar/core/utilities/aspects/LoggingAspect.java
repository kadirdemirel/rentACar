package com.kodlamaio.rentACar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution (* com.kodlamaio.rentACar.business.concretes.*.*(..))")
	public void log(JoinPoint joinPoint) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append("\n{");
			stringBuilder.append("\n" + "\"date\":" + objectMapper.writeValueAsString(LocalDate.now().toString()));
			stringBuilder.append("\n" + "\"className\":"
					+ objectMapper.writeValueAsString(joinPoint.getTarget().getClass().getSimpleName()));
			stringBuilder
					.append("\n" + "\"methodName\"" + objectMapper.writeValueAsString(signature.getMethod().getName()));

			if (signature.getMethod().getName() != "getAll") {
				stringBuilder.append("\n" + "\"parameters\":" + objectMapper.writeValueAsString(joinPoint.getArgs()));

			} else {
				stringBuilder.append("\n" + "\"parameters\":" + "none");
			}

			stringBuilder.append("\n" + "}");
			File file = new File("C:\\json\\operations.json");

			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
				bufferedWriter.write(stringBuilder.toString());

			} catch (IOException e) {
				System.out.println("Unable to read file " + file.toString());
			}

		} catch (Throwable e) {

			e.printStackTrace();
		}
		;
	}
}