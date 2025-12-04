```mermaid
graph TD
  A[Parabank UI] -->|Selenium POM| B[TestNG Suites]
  B -->|Page Objects| C[Login/Overview/Transfer/BillPay]
  B -->|DDT| D[CSV + MySQL Fixtures]
  B -->|Reports| E[Extent & Allure]
  B -->|CI Trigger| F[Jenkins Pipeline]
  F -->|Docker Compose| G[Selenium Grid + MySQL]
  E -->|Artifacts| H[TestRail/Jira Export]
```

- UI flows chain through strongly typed page objects for encapsulation.
- CSV + SQL seeds drive permutations; MySQL ready for future DB verification steps.
- Reporting listener attaches screenshots to both Extent & Allure outputs.
- Jenkins orchestrates Dockerized grid + DB, then archives artifacts for auditors.
