package ua.kislov.shop_back.config.jackson_config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ua.kislov.shop_back.model.OrderProduct;
import ua.kislov.shop_back.model.ShopOrder;

import java.io.IOException;

class ShopOrderSerializer extends JsonSerializer<ShopOrder> {
    @Override
    public void serialize(ShopOrder value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject(); // Начало объекта

        gen.writeNumberField("orderId", value.getOrderId()); // Сериализация поля orderId

        // Сериализация поля shopClient без поля shoppingCart
        gen.writeObjectFieldStart("shopClient");
        gen.writeNumberField("id", value.getShopClient().getId());
        gen.writeStringField("clientName", value.getShopClient().getClientName());
        gen.writeStringField("surname", value.getShopClient().getSurname());
        gen.writeStringField("email", value.getShopClient().getEmail());
        gen.writeStringField("number", value.getShopClient().getNumber());
        gen.writeEndObject();

        if (value.getOrderProductsList() != null) {
            gen.writeFieldName("orderProductsList");
            gen.writeStartArray();
            for (OrderProduct orderProduct : value.getOrderProductsList()) {
                // Сериализуем каждый элемент списка отдельно
                gen.writeObject(orderProduct);
            }
            gen.writeEndArray();
        }

        gen.writeEndObject(); // Конец объекта
    }
}