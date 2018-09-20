package cn.miss.spring.jackson;

import cn.miss.spring.bean.Customer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/20.
 */
@JsonComponent
public class CustomerJsonComponent {

    public static class CustomerSerializer extends JsonSerializer<Customer> {

        @Override
        public void serialize(Customer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeObject(value);
        }
    }

    public static class CustomerDeserializer extends JsonDeserializer<Customer> {

        @Override
        public Customer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return ctxt.readValue(p, Customer.class);
        }
    }

}
