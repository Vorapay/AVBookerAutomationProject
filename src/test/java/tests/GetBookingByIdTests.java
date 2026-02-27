package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.clients.APIClient;
import core.models.BookingById;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetBookingByIdTests {
    private APIClient apiClient;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        apiClient = new APIClient();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testGetBookingById() throws Exception {
        Response response = apiClient.getBookingById(1);
        assertThat(response.getStatusCode()).isEqualTo(200);

        String responseBody = response.getBody().asString();
        //List<BookingById> bookingByIds = objectMapper.readValue(responseBody, new TypeReference<List<BookingById>>() {});
        BookingById bookingById = objectMapper.readValue(responseBody, BookingById.class);

        //assertThat(bookingById).isNotNull();

        // for (BookingById bookingById : bookingByIds) {
        assertThat(bookingById.getFirstname()).isNotNull();
        assertThat(bookingById.getLastname()).isNotNull();
        assertThat(bookingById.getTotalprice()).isGreaterThan(0);
        assertThat(bookingById.getAdditionalneeds()).isNotNull();
        assertThat(bookingById.isDepositpaid()).isTrue();
        assertThat(bookingById.getBookingdates()).isNotNull();
        assertThat(bookingById.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingById.getBookingdates().getCheckout()).isNotNull();
        assertThat(bookingById.getBookingdates().getCheckin()).matches("\\d{4}-\\d{2}-\\d{2}");
        assertThat(bookingById.getBookingdates().getCheckout()).matches("\\d{4}-\\d{2}-\\d{2}");
        // }
    }
}
