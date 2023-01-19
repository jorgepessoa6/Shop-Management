package resource.contracts;

import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;


@Provider("Shop Service")
@PactFolder("pacts")
public class ShopServiceContractTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8081, "/graphql"));
    }

    @Pact(provider = "Shop Service", consumer = "Promotion Client")
    public String createPact(/*PactDslWithProvider builder*/) {
        /*return builder
                .given("a list of products exists")
                .uponReceiving("a request for the list of products")
                .path("/products")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("[{\"name\":\"Product 1\",\"price\":9.99,\"description\":\"A great product\"},{\"name\":\"Product 2\",\"price\":19.99,\"description\":\"Another great product\"}]")
                .toPact();*/
        return "";
    }
}
