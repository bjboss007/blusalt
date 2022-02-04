package com.example.customerservice.config;

import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
public class AppConfig implements InitializingBean {

    @Value("${auto.load.file.settings:customer.csv}")
    private String customersFile;

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public void afterPropertiesSet() throws Exception {
        loadCustomers();
    }

    private void loadCustomers(){
        if(customerRepo.count() <= 0){
            List<Customer> customers = loadObjectList(Customer.class, customersFile);
            customerRepo.saveAll(customers);
        }

    }

    public <T> List<T> loadObjectList(Class<T> type, String filename){
        try{
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper().configure(CsvParser.Feature.ALLOW_TRAILING_COMMA, true)
                    .configure(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS, false)
                    .configure(CsvParser.Feature.SKIP_EMPTY_LINES, true)
                    .configure(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS, true)
                    .configure(CsvParser.Feature.TRIM_SPACES, true)
                    .configure(CsvParser.Feature.WRAP_AS_ARRAY, false)
                    .configure(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE, true);

            File file  = new ClassPathResource(filename).getFile();
            MappingIterator<T> readValues =
                    mapper.readerWithTypedSchemaFor(type).with(bootstrapSchema).readValues(file);

            return  readValues.readAll();

        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("funding-topic")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }
}
