package k_ira.usersubscriptionservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import k_ira.usersubscriptionservice.entity.Subscription;
import k_ira.usersubscriptionservice.request.SubscriptionRequest;
import k_ira.usersubscriptionservice.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
class SubscriptionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionController subscriptionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
    }

    @Test
    void addSubscriptionSuccess() throws Exception {
        Subscription subscription = Subscription.builder().id(1L).title("Netflix").build();
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest("Netflix", 1L);

        when(subscriptionService.addSubscription(any())).thenReturn(subscription);

        mockMvc.perform(post("/api/v1/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(subscriptionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Netflix"));
    }

    @Test
    void getSubscriptions_success() throws Exception {
        List<Subscription> subs = List.of(Subscription.builder().id(1L).title("Spotify").build());

        when(subscriptionService.getUserSubscriptions(1L)).thenReturn(subs);

        mockMvc.perform(get("/api/v1/users/1/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spotify"));
    }

    @Test
    void deleteSubscription_success() throws Exception {
        mockMvc.perform(delete("/api/v1/subscriptions/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getTopSubscriptions_returns200WithList() throws Exception {
        List<String> mockSubscriptions = List.of("YouTube Premium", "Netflix", "Spotify");

        when(subscriptionService.getTopSubscriptions()).thenReturn(mockSubscriptions);

        mockMvc.perform(get("/api/v1/subscriptions/top"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0]").value("YouTube Premium"))
                .andExpect(jsonPath("$[1]").value("Netflix"))
                .andExpect(jsonPath("$[2]").value("Spotify"));
    }
}