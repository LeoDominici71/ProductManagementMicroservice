package com.fiap.productRegistry.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;
import com.fiap.productRegistry.adapter.output.postgreRepository.repository.ProductsRepository;

@Configuration
public class BatchConfiguration {
	
	private final ProductsRepository repository;
	
	public BatchConfiguration(ProductsRepository repository) {
		this.repository = repository;
	}
	
	
	
	public FlatFileItemReader<ProductEntity> itemReader(){
		FlatFileItemReader<ProductEntity> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/productEntity.csv"));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(2);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
	
	private LineMapper<ProductEntity> lineMapper(){
		DefaultLineMapper<ProductEntity> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(";");
		tokenizer.setNames("name", "description", "stock", "price");
		tokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<ProductEntity> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(ProductEntity.class);
		lineMapper.setFieldSetMapper(mapper);
		lineMapper.setLineTokenizer(tokenizer);
		return lineMapper;
	}
	
	@Bean
	public RepositoryItemWriter<ProductEntity> itemWriter(){
		RepositoryItemWriter<ProductEntity> writer = new RepositoryItemWriter<>();
		writer.setRepository(repository);
		writer.setMethodName("save");
		return writer;
	}
	
	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager plataformTransactionManager) {
		return new StepBuilder("csv-step", jobRepository).<ProductEntity, ProductEntity>chunk(10, plataformTransactionManager).reader(itemReader()).writer(itemWriter()).build();
	}

	@Bean
	public Job processProduct(JobRepository jobRepository, PlatformTransactionManager plataformTransactionManager) {
		return new JobBuilder("csv-job", jobRepository).flow(step(jobRepository, plataformTransactionManager)).end().build();
	}


}
