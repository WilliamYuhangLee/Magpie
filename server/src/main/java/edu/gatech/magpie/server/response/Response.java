package edu.gatech.magpie.server.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    @Getter
    @Accessors(chain = true)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Error {
        private Date timestamp;
        private String message;

        Error(String message) {
            this.timestamp = new Date();
            this.message = message;
        }
    }

    private HttpStatus status;
    private T payload;
    private Object error;
    private Object metadata;

    public static <S> Response<S> withStatus(HttpStatus status) {
        Response<S> response = new Response<>();
        response.setStatus(status);
        return response;
    }

    public Response<T> addError(String message) {
        return this.setError(new Error(message));
    }

    public Response<T> addError(Exception exception) {
        return addError(exception.getMessage());
    }
}

