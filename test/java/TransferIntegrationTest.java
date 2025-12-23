import com.r1xchange.wallet.miniwalletservice.MiniwalletserviceApplication;
import com.r1xchange.wallet.miniwalletservice.TestSecurityConfig;
import com.r1xchange.wallet.miniwalletservice.common.model.Wallet;
import com.r1xchange.wallet.miniwalletservice.common.repo.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = {MiniwalletserviceApplication.class, TestSecurityConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class TransferIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletRepository walletRepository;

    @BeforeEach
    void setup() {
        walletRepository.deleteAll();

        walletRepository.save(new Wallet("d8eb8876-6808-4f8e-9407-cf4f3c89c861", BigDecimal.valueOf(100)));
        walletRepository.save(new Wallet("530c6c25-69b8-40cc-bb8c-4f7d5786615", BigDecimal.valueOf(50)));
    }

    @Test
    void testTransferMoney() throws Exception {
        String payload = """
                {
                  "fromUserId": "d8eb8876-6808-4f8e-9407-cf4f3c89c861",
                  "toUserId": "530c6c25-69b8-40cc-bb8c-4f7d5786615",
                  "amount": 50
                }
                """;

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk());

        Wallet alice = walletRepository.findById("d8eb8876-6808-4f8e-9407-cf4f3c89c861").orElseThrow();
        Wallet bob = walletRepository.findById("530c6c25-69b8-40cc-bb8c-4f7d5786615").orElseThrow();

        assertEquals(BigDecimal.valueOf(50), alice.getBalance());
        assertEquals(BigDecimal.valueOf(100), bob.getBalance());
    }
}
