package com.casinoroyale.exchangerate.exchangerate.domain;

import static com.casinoroyale.exchangerate.ExchangeRateApplication.DEFAULT_ZONE_OFFSET;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PACKAGE;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.casinoroyale.exchangerate.exchangerate.dto.UpdateExchangeRateNoticeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor(access = PACKAGE)
@Slf4j
class ExchangeRateFacade {

    private static final String EXCHANGE_RATE_UPDATED_TOPIC = "ExchangeRateUpdated";
    
    private final KafkaTemplate<String, UpdateExchangeRateNoticeDto> kafkaTemplate;

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}", initialDelayString = "${initialDelay.in.milliseconds}")
    void sendCurrencyUpdate() {
        final String currency = "USD";

        final BigDecimal rate = valueOf(nextDouble(0.9, 1.3)).setScale(4, HALF_UP);
        final OffsetDateTime date = OffsetDateTime.now(DEFAULT_ZONE_OFFSET);
        final UpdateExchangeRateNoticeDto updateExchangeRateNoticeDto = new UpdateExchangeRateNoticeDto(rate, date);
        
        kafkaTemplate.send(EXCHANGE_RATE_UPDATED_TOPIC, currency, updateExchangeRateNoticeDto);
    }

}
