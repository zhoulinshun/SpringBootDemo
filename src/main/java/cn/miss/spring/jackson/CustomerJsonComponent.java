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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static class DateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString("");
        }
    }

    public static class DateDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            final String s = ctxt.readValue(p, String.class);
            try {
                return new SimpleDateFormat().parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return ctxt.readValue(p, Date.class);
        }
    }

}
