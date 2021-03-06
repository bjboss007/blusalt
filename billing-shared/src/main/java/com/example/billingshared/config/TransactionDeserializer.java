package com.example.billingshared.config;


import com.example.billingshared.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;


import java.util.Map;

public class TransactionDeserializer implements Deserializer<Transaction> {

    @Override
    public Transaction deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (bytes == null){
                return null;
            }

            return objectMapper.readValue(bytes, Transaction.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SerializationException("Error when deserializing byte[] to Product");
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }
}

