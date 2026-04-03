package com.novahorizonte.backend.chat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = {
    "AI_PROVIDER=mock"
})
@AutoConfigureMockMvc
class ChatControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveResponderContratoDoFrontend() throws Exception {
        String payload = """
            {
              "message": "Quais são os gargalos principais?",
              "sessionId": "sessao-front-1"
            }
            """;

        mockMvc.perform(post("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sessionId").value("sessao-front-1"))
            .andExpect(jsonPath("$.answer").isNotEmpty())
            .andExpect(jsonPath("$.contextUsed.dashboardResumo").isNotEmpty())
            .andExpect(jsonPath("$.contextUsed.processosCriticos").isArray())
            .andExpect(jsonPath("$.contextUsed.indicadoresPessoas").isArray())
            .andExpect(jsonPath("$.contextUsed.planejamentoAtivo").isArray())
            .andExpect(jsonPath("$.confidence").isNumber());
    }

    @Test
    void deveRetornarBadRequestQuandoMessageAusente() throws Exception {
        String payload = """
            {
              "sessionId": "sessao-sem-message"
            }
            """;

        mockMvc.perform(post("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("VALIDATION_ERROR"));
    }
}
