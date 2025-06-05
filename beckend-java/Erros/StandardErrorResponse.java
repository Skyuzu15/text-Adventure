package Erros;

public class StandardErrorResponse {
        private String error;
        private String message;

        public StandardErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
}
