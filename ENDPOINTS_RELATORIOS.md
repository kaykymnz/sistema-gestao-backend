# 📚 Documentação dos Endpoints de Relatórios

## Base URL
```
http://localhost:8080/api/relatorios
```

---

## 🔍 GET - Listar Todos os Relatórios

**Endpoint:** `GET /api/relatorios`

**Descrição:** Retorna lista de todos os relatórios

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "titulo": "Diagnóstico Estratégico Institucional - 2026",
    "descricao": "Análise completa da maturidade organizacional da UNH",
    "dataCriacao": "2026-03-15",
    "dataUltimaAtualizacao": "2026-03-20",
    "tipo": "diagnostico",
    "confiabilidade": 92,
    "responsavel": "Pró-Reitoria de Planejamento",
    "status": "publicado",
    "diagnostico": { ... },
    "sugestoes": [ ... ],
    "classificacaoMaturidade": { ... }
  }
]
```

---

## 🔍 GET - Buscar Relatório por ID

**Endpoint:** `GET /api/relatorios/{id}`

**Descrição:** Retorna um relatório específico

**Parâmetros:**
- `id` (path, obrigatório): ID do relatório

**Response (200 OK):**
```json
{
  "id": 1,
  "titulo": "Diagnóstico Estratégico Institucional - 2026",
  ...
}
```

**Response (404 NOT FOUND):** Se relatório não existir

---

## 🔍 GET - Filtrar por Tipo

**Endpoint:** `GET /api/relatorios/tipo/{tipo}`

**Descrição:** Retorna relatórios filtrados por tipo

**Parâmetros:**
- `tipo` (path, obrigatório): "diagnostico", "analise", ou "predicao"

**Exemplo:**
```
GET /api/relatorios/tipo/diagnostico
```

**Response (200 OK):**
```json
[
  { id: 1, tipo: "diagnostico", ... }
]
```

---

## ➕ POST - Criar Novo Relatório

**Endpoint:** `POST /api/relatorios`

**Descrição:** Cria um novo relatório

**Request Body:**
```json
{
  "titulo": "Novo Relatório de Análise",
  "descricao": "Análise de processos",
  "tipo": "analise",
  "diagnostico": {
    "resumoExecutivo": "...",
    "achados": [ ... ],
    "pontosFortesIdentificados": [ ... ],
    "areaCritica": [ ... ],
    "oportunidadesIdentificadas": [ ... ]
  },
  "sugestoes": [ ... ],
  "classificacaoMaturidade": { ... }
}
```

**Response (201 CREATED):**
```json
{
  "id": 3,
  "titulo": "Novo Relatório de Análise",
  "dataCriacao": "2026-04-02",
  "dataUltimaAtualizacao": "2026-04-02",
  "confiabilidade": 85,
  "status": "publicado",
  ...
}
```

**Response (400 BAD REQUEST):** Se ainda faltarem campos obrigatórios

---

## 📝 PUT - Atualizar Relatório

**Endpoint:** `PUT /api/relatorios/{id}`

**Descrição:** Atualiza um relatório existente

**Parâmetros:**
- `id` (path, obrigatório): ID do relatório

**Request Body:**
```json
{
  "titulo": "Relatório Atualizado",
  "descricao": "Descrição modificada",
  "status": "arquivado",
  "diagnostico": { ... },
  "sugestoes": [ ... ]
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "titulo": "Relatório Atualizado",
  "dataUltimaAtualizacao": "2026-04-02",
  ...
}
```

**Response (404 NOT FOUND):** Se relatório não existir

---

## 🗑️ DELETE - Remover Relatório

**Endpoint:** `DELETE /api/relatorios/{id}`

**Descrição:** Remove um relatório

**Parâmetros:**
- `id` (path, obrigatório): ID do relatório

**Response (204 NO CONTENT):** Sucesso (sem corpo)

**Response (404 NOT FOUND):** Se relatório não existir

---

## 🤖 POST - Gerar Novo Relatório por IA

**Endpoint:** `POST /api/relatorios/gerar`

**Descrição:** Gera novo relatório com base em parâmetros (simula IA)

**Request Body:**
```json
{
  "dataInicio": "2026-03-01",
  "dataFim": "2026-04-02",
  "filtro": "processos",
  "incluirComparativo": true
}
```

**Parâmetros opcionais:**
- `filtro`: "processos", "pessoas", "estrategia", "governanca", "financeiro"
- `incluirComparativo`: true/false (default: true)

**Response (201 CREATED):**
```json
{
  "id": 3,
  "titulo": "Relatório Diagnóstico - 2026-04-02",
  "descricao": "Análise diagnóstica do período de 2026-03-01 a 2026-04-02",
  "dataCriacao": "2026-04-02",
  "tipo": "diagnostico",
  "confiabilidade": 85,
  "status": "rascunho",
  ...
}
```

---

## 📊 Estrutura Completa de um Relatório

```json
{
  "id": 1,
  "titulo": "string",
  "descricao": "string",
  "dataCriacao": "date",
  "dataUltimaAtualizacao": "date",
  "tipo": "diagnostico|analise|predicao",
  "confiabilidade": 0-100,
  "responsavel": "string",
  "status": "rascunho|publicado|arquivado",
  
  "diagnostico": {
    "resumoExecutivo": "string",
    "achados": [
      {
        "titulo": "string",
        "descricao": "string",
        "impacto": "alto|medio|baixo",
        "evidencia": "string",
        "categoria": "string"
      }
    ],
    "pontosFortesIdentificados": ["string"],
    "areaCritica": ["string"],
    "oportunidadesIdentificadas": ["string"]
  },
  
  "sugestoes": [
    {
      "id": 1,
      "titulo": "string",
      "descricao": "string",
      "prioridade": "alta|media|baixa",
      "impactoEstimado": "transformador|significativo|moderado",
      "esforco": 0-100,
      "retornoOnInvestimento": 0-100,
      "diasParaImplementacao": number,
      "responsavel": "string",
      "status": "proposta|em_analise|aprovada|implementando|concluida"
    }
  ],
  
  "classificacaoMaturidade": {
    "score": 0-100,
    "niveau": "iniciante|em_desenvolvimento|operacional|otimizado|lider_industria",
    "comparativoSetor": 0-100,
    "trajetoriaMelhoria": {
      "dataAtual": "date",
      "scoreAtual": 0-100,
      "scoreProjetado12Meses": 0-100,
      "caminhoMelhor": ["string"]
    }
  }
}
```

---

## 🔧 Dados Mockados Disponíveis

### Relatório 1: Diagnóstico Estratégico
- **ID:** 1
- **Tipo:** diagnostico
- **Score:** 68/100
- **Nível:** em_desenvolvimento
- **Sugestões:** 7 recomendações

### Relatório 2: Análise de Processos
- **ID:** 2
- **Tipo:** analise
- **Score:** 52/100
- **Nível:** iniciante
- **Sugestões:** 3 recomendações

---

## 🚀 Como Testar

### Via cURL

**Listar todos:**
```bash
curl -X GET http://localhost:8080/api/relatorios
```

**Buscar por ID:**
```bash
curl -X GET http://localhost:8080/api/relatorios/1
```

**Criar novo relatório:**
```bash
curl -X POST http://localhost:8080/api/relatorios \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Novo Relatório",
    "descricao": "...",
    "tipo": "diagnostico"
  }'
```

**Atualizar:**
```bash
curl -X PUT http://localhost:8080/api/relatorios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "status": "arquivado"
  }'
```

**Deletar:**
```bash
curl -X DELETE http://localhost:8080/api/relatorios/1
```

---

## ✅ Status Codes

| Código | Descrição |
|--------|-----------|
| 200 | OK - Requisição bem-sucedida |
| 201 | Created - Novo recurso criado |
| 204 | No Content - Sucesso sem corpo (DELETE) |
| 400 | Bad Request - Dados inválidos |
| 404 | Not Found - Recurso não encontrado |
| 500 | Internal Server Error - Erro do servidor |

---

## 🔄 Fluxo de Dados Frontend ↔ Backend

```
Angular (Frontend)
       ↓
RelatorioService (http.get, http.post, etc)
       ↓
HTTP Request → http://localhost:8080/api/relatorios
       ↓
RelatorioController (Spring Boot)
       ↓
RelatorioService (Java)
       ↓
ArrayList com dados mockados
       ↓
Resposta JSON
       ↓
Angular recebe e renderiza em componentes
```

---

## 🔗 Integração Frontend

No Angular (`relatorio.service.ts`), está configurado:
- ✅ Base URL: `http://localhost:8080/api/relatorios`
- ✅ Métodos: `getRelatorios()`, `getRelatorioPorId()`, `gerarRelatorio()`, `atualizarRelatorio()`
- ✅ CORS: Já configurado no backend (`CorsConfig.java`)

**Para usar:**
1. Inicie o backend Spring: `java -jar backend.jar` ou via IDE
2. Frontend já está apontando para o backend
3. Dados fluem automaticamente via HTTP REST
