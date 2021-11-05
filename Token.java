package cinema;

import java.util.UUID;

public class Token {
    private final UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Token)) {
            return false;
        }
        Token t = (Token) o;
        return token.toString().equals(t.token.toString());
    }
}
