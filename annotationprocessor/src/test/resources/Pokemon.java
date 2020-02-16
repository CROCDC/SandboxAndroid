import com.cr.o.cdc.annotations.GraphQlRequest;
import com.cr.o.cdc.annotations.Input;

@GraphQlRequest(url = "url", name = "pokemon", nullable = true, inputs = {@Input(name = "name", type = String.class)})
public class Pokemon {
    private String name;

    Pokemon(String name) {
        this.name = name;
    }
}
