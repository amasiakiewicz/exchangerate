package com.casinoroyale.exchangerate.exchangerate.domain;

import com.casinoroyale.exchangerate.exchangerate.dto.UpdateExchangeRateNoticeDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class ExchangeRateConfiguration {
    
    @Bean
    ExchangeRateFacade exchangeRateFacade(final KafkaTemplate<String, UpdateExchangeRateNoticeDto> kafkaTemplate) {
        return new ExchangeRateFacade(kafkaTemplate);
    }

}
