package com.example.billing.Config;

import com.example.billing.dto.FundAccountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class FundAccountDeserializer implements Deserializer<FundAccountDTO> {

    @Override
    public FundAccountDTO deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (bytes == null){
                System.out.println("Null received at deserializing");
                return null;
            }

            return objectMapper.readValue(bytes, FundAccountDTO.class);
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

