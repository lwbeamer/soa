package itmo.corp.secondaryservice.exception;

import itmo.corp.secondaryservice.dto.Error;

public class ClientException extends RuntimeException{

    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ClientException(Error error) {
        this.error = error;
    }
}
