package cn.miss.spring.util.config;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/7/10.
 */
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        if (statusCode == HttpStatus.UNAUTHORIZED || statusCode == HttpStatus.BAD_REQUEST) {
            return false;
        }
        return statusCode.series() == HttpStatus.Series.CLIENT_ERROR || statusCode.series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        final String errorText = IOUtils.toString(response.getBody());
        switch (statusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(statusCode, errorText, response.getHeaders(), this.getResponseBody(response), this.getCharset(response));
            case SERVER_ERROR:
                throw new HttpServerErrorException(statusCode, errorText, response.getHeaders(), this.getResponseBody(response), this.getCharset(response));
            default:
                throw new RestClientException("Unknown status code [" + statusCode + "],errorText[" + errorText + "]");
        }
    }

}
