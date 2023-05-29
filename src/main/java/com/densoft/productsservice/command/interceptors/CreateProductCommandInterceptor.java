package com.densoft.productsservice.command.interceptors;

import com.densoft.productsservice.command.CreateProductCommand;
import com.densoft.productsservice.core.data.ProductLookupEntity;
import com.densoft.productsservice.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return ((integer, commandMessage) -> {
            log.info("Intercepted command: " + commandMessage.getPayloadType());
            if (CreateProductCommand.class.equals(commandMessage.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) commandMessage.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if (productLookupEntity != null) {
                    throw new IllegalStateException(String.format("Product with productId %s or title %s already exists",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }

            }
            return commandMessage;
        });
    }
}
