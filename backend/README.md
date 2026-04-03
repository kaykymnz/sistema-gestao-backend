# Backend - Sistema Gestão (Chat IA)

## Endpoint de Chat (contrato frontend)

- **POST** `/api/chat`
- **Request**

```json
{
  "message": "string obrigatória",
  "sessionId": "string opcional"
}
```

- **Response**

```json
{
  "sessionId": "string obrigatória",
  "answer": "string obrigatória",
  "contextUsed": {
    "dashboardResumo": "string",
    "processosCriticos": ["string"],
    "indicadoresPessoas": ["string"],
    "planejamentoAtivo": ["string"]
  },
  "confidence": 0.0
}
```

## Variáveis de ambiente

| Variável | Obrigatória | Padrão | Descrição |
|---|---|---|---|
| `AI_PROVIDER` | Não | `mock` | Provedor da IA (`mock` ou `openai`). |
| `AI_API_KEY` | Apenas `openai` | vazio | Chave da API do provedor. |
| `AI_MODEL` | Não | `gpt-4o-mini` | Modelo utilizado no provedor. |
| `AI_TIMEOUT_MS` | Não | `8000` | Timeout da chamada da IA em milissegundos. |
| `AI_TEMPERATURE` | Não | `0.2` | Temperatura de geração. |
| `AI_MAX_TOKENS` | Não | `500` | Limite de tokens na resposta. |

## Exemplos cURL

### Request com sessionId

```bash
curl -X POST 'http://localhost:8080/api/chat' \
  -H 'Content-Type: application/json' \
  -d '{
    "message": "Quais ações priorizar no curto prazo para reduzir gargalos?",
    "sessionId": "sessao-frontend-abc"
  }'
```

### Request sem sessionId (backend gera automaticamente)

```bash
curl -X POST 'http://localhost:8080/api/chat' \
  -H 'Content-Type: application/json' \
  -d '{
    "message": "Me dê recomendações para pessoas e planejamento da semana."
  }'
```
