# Algusto

A recipe finder built around what's actually in your kitchen. Give it a list of ingredients and it returns recipes you can make with exactly what you have — no substitutions, no missing items.

Work in progress.

---

## Stack

**Backend**
- Java 25 + Spring Boot 4
- Spring Data JPA
- H2 (in-memory, auto-seeded on startup)
- Maven

---

## Running locally

### Backend

Requires Java 25+. No database setup needed — H2 runs in-memory and seeds itself.

```bash
git clone https://github.com/ctr305/algusto.git
cd algusto
./mvnw spring-boot:run
```

API runs at `http://localhost:8080`. An H2 console for poking around the database is available at `http://localhost:8080/h2-console`.

---

## API

### Recipes

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/recipes` | Get all recipes |
| `POST` | `/api/recipes` | Create a recipe |
| `POST` | `/api/recipes/search` | Search by ingredients |

### Ingredients

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/ingredients` | Get all ingredients |

---

### Searching by ingredients

POST a JSON array of ingredient names. Only returns recipes where every required ingredient is covered by your list — so if a recipe needs eggs and butter, you need both in your list for it to show up.

```http
POST /api/recipes/search
Content-Type: application/json

["eggs", "butter", "salt"]
```

Input is case-insensitive and whitespace-tolerant.

```json
[
  {
    "id": 1,
    "name": "Scrambled Eggs",
    "difficulty": "easy",
    "prepTime": 10,
    "dishType": "main",
    "instructions": "Beat eggs, melt butter in pan...",
    "ingredients": [...]
  }
]
```

### Creating a recipe

```http
POST /api/recipes
Content-Type: application/json

{
  "name": "Omelette",
  "difficulty": "easy",
  "prepTime": 15,
  "dishType": "main",
  "instructions": "Beat eggs and cook in a buttered pan...",
  "ingredients": [
    { "name": "eggs", "measurementUnit": "unit" },
    { "name": "butter", "measurementUnit": "grams" }
  ]
}
```

---

## Tests

```bash
./mvnw test
```

The test suite is split into two layers. Service tests use Mockito to test the business logic in isolation — input normalization, null handling, edge cases. Repository tests use `@DataJpaTest` to run the ingredient search query against a real H2 instance, covering things like partial ingredient matches, supersets, and case-insensitive stored data.

---

## What's next

- DTO layer (currently the API accepts and returns JPA entities directly)
- Input validation and proper error responses
- Spring profiles to support swapping H2 out for PostgreSQL for a live deployment
- Ingredient quantity tracking per recipe

---

## License

[LGPL-3.0](LICENSE)
