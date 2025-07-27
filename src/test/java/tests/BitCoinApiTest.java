package tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BitCoinApiTest {
    @Test
    public void validateBitcoinData() {
        // Step 1: Send GET Request
        Response response = RestAssured
                .given()
                .baseUri("https://api.coingecko.com/api/v3")
                .when()
                .get("/coins/bitcoin")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();

        // Step 2a: Verify 3 BPIs
        assertThat(response.path("market_data.current_price.usd"), notNullValue());
        assertThat(response.path("market_data.current_price.gbp"), notNullValue());
        assertThat(response.path("market_data.current_price.eur"), notNullValue());

        // Step 2b: Market Cap and Total Volume
        Double marketCapUSD = jsonPath.getDouble("market_data.market_cap.usd");
        Double volumeUSD = jsonPath.getDouble("market_data.total_volume.usd");
        assertThat(response.path("market_data.market_cap.usd"), notNullValue());
        assertThat(response.path("market_data.total_volume.usd"), notNullValue());
        System.out.println("\nMarket Cap (USD): " + marketCapUSD);
        System.out.println("Total Volume (USD): " + volumeUSD);

        // Step 2c: Price change in last 24h
        Double change24h = jsonPath.getDouble("market_data.price_change_percentage_24h");
        System.out.println("\nPrice Change (24h): " + change24h + "%");
        assertThat(change24h, notNullValue());

        // Step 2d: Homepage URL is not empty
        String homepage = response.path("links.homepage[0]");
        System.out.println("\nHomepage URL: " + homepage);
        assertThat(homepage, allOf(notNullValue(), not(isEmptyString())));

    }
}
