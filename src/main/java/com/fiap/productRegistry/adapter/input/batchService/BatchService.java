package com.fiap.productRegistry.adapter.input.batchService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fiap.productRegistry.exceptionHandler.CsvValidatorException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchService {

	private final JobLauncher jobLauncher;
	private final Job job;

	public BatchService(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;

	}

	@Async
	public BatchStatus batchAsyncRoutine() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		try {
		//CVS validation to input the correct data inside the database
		this.csvValidator();
		JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		JobExecution run = jobLauncher.run(job, jobParameters);
		BatchService.log.info("OUT - Async routine batch processment");
		return run.getStatus();
		
		}catch(Exception e){
			e.printStackTrace();
			 throw new CsvValidatorException("Validation error");

		}

	}

	public void csvValidator() throws Exception {
		BatchService.log.info("IN - CSV validator");
		String csvFile = "src/main/resources/productEntity.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String dateString = br.readLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            LocalDate currentDate = LocalDate.now();
            		
            if (date.equals(currentDate)) {
        		BatchService.log.info("validation sucess");

            }else {
        		BatchService.log.error("validation unsucessfull");
        		throw new CsvValidatorException("Date validation unsuccessful");
            }
    		BatchService.log.info("OUT - CSV validator");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				 throw new CsvValidatorException("Validation error");
			} 
	}
}
