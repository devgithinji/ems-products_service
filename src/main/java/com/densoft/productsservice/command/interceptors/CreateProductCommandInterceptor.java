package com.densoft.productsservice.command.interceptors;

import com.densoft.productsservice.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return ((integer, commandMessage) -> {
            log.info("Intercepted command: " + commandMessage.getPayloadType());
            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) commandMessage.getPayload();

                if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price cannot be less or equal to zero");
                }

                if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
                    throw new IllegalArgumentException("Title cannot be empty");
                }
            }
            return commandMessage;
        });
    }
}
