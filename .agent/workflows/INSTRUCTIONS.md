---
description: Implement a Jira story following a standard development workflow
---

# Development Workflow

// turbo-all

**IMPORTANT:** Steps 1 and 2 (Jira interactions) are **ONLY** required when the user explicitly asks to "Implement story XXX" or similar. If the request is not related to a specific Jira story implementation, **SKIP** steps 1 and 2 and proceed to the relevant technical steps (e.g., Step 3).

When working on a Jira story implementation, follow these steps:

1. **Get Jira Story Details**
   - Use `mcp0_jira_get_issue` to retrieve the story details if not already known.

2. **Assign Story & Update Status**
   - **Assign to Self:**
     - Find your Jira account ID using `mcp0_jira_search_users` (search for your name or email).
     - Assign the issue to yourself using `mcp0_jira_assign_issue`.
   - **Update Status:**
     - Change the issue status to "In Progress" using `mcp0_jira_update_status`.

3. **Create a Feature Branch**
   - Always create a new branch for your changes.
   - **Branch naming convention:** `feature/<issue-key>-<long-timestamp>`
   - Use a long timestamp (e.g., Unix timestamp or YYYYMMDDHHMMSS) to ensure uniqueness.
   - Command: `git checkout -b feature/<issue-key>-<timestamp>`

4. **Implement the Feature**
   - Write clean, maintainable code that follows the existing patterns in the codebase.
   - **Code Quality Standards:**
     - **General:**
       - Follow existing code structure, patterns, and conventions.
       - Follow SOLID principles and recommended design patterns.
       - Use meaningful variable, method, and class names.
       - Add comments for complex logic (explain *why*, not just *what*).
       - Ensure proper error handling (no empty catch blocks).
     - **Java / Spring Boot Specifics:**
       - **Dependency Injection:** Use Constructor Injection over Field Injection (`@Autowired` on fields).
       - **DTOs:** Use DTOs for API requests/responses; do not expose Entities directly.
       - **Logging:** Use SLF4J/Lombok `@Slf4j` for logging. Avoid `System.out.println`.
       - **Exceptions:** Use Global Exception Handling (`@ControllerAdvice`) for consistent API errors.
       - **Streams:** Use Java Streams API for collection processing where readable.
     - **JavaScript / Angular Specifics:**
       - **Variables:** Use `const` by default, `let` only when reassignment is needed. Avoid `var`.
       - **Typing:** Use strict TypeScript typing. Avoid `any` whenever possible.
       - **Async:** Use `async/await` for cleaner asynchronous code over raw Promises.
       - **RxJS:** Manage subscriptions properly (use `async` pipe or `takeUntil/destroyRef`) to avoid memory leaks.
       - **Structure:** Keep Components focused on view logic; move business logic to Services.

5. **Create Unit Tests**
   - **Testing Standards:**
     - **General:**
       - **All code changes must include unit tests.**
       - Aim for high code coverage (90%+).
       - Test happy paths, edge cases, and error scenarios.
       - Keep tests independent and isolated (no shared state).
       - Use descriptive test method names.
     - **Java / Spring Boot Specifics:**
       - **Frameworks:** Use **JUnit 5** and **Mockito**.
       - **Assertions:** Use **AssertJ** for fluent assertions (`assertThat(...)`).
       - **Controllers:** Use `@WebMvcTest` with `MockMvc` for slice testing.
       - **Services:** Use `@ExtendWith(MockitoExtension.class)` for pure unit tests.
       - **Repositories:** Use `@DataJpaTest` for integration-style DB tests.
     - **JavaScript / Angular Specifics:**
       - **Frameworks:** Use **Jasmine** & **Karma** (standard) or **Jest**.
       - **Setup:** Use `TestBed` for configuring testing modules.
       - **Components:** Test component creation (`toBeTruthy()`) and DOM interactions.
       - **Mocks:** Mock child components and services using `jasmine.createSpyObj`.

6. **Run All Tests**
   - Before committing, ensure all tests pass.
   - **Java / Spring Boot:**
     - Command: `mvn test` or `./mvnw test`.
     - Verify no build failures.
   - **JavaScript / Angular:**
     - Command: `ng test --watch=false --browsers=ChromeHeadless` (for CI/one-off run).
     - Ensure all specs pass.
   - **Requirements:**
     - All existing tests must continue to pass (no regression).
     - All new tests must pass.
     - Zero test failures or errors.

7. **Commit Your Changes**
   - Write clear, descriptive commit messages.
   - Commands:
     ```bash
     git add .
     git commit -m "feat(<issue-key>): Brief description of changes
     
     - Detailed point 1
     - Detailed point 2
     - All tests passing"
     ```
   - **Commit message format:**
     - `feat(<issue-key>):` for new features
     - `fix(<issue-key>):` for bug fixes
     - `test(<issue-key>):` for test-only changes
     - `refactor(<issue-key>):` for refactoring

8. **Push Your Branch**
   - Command: `git push -u origin feature/<issue-key>-<description>`

9. **Create a Pull Request**
   - Create a pull request against the `main` (or default) branch.
   - **PR Title:** `feat(<issue-key>): Brief description`
   - **PR Description should include:**
     - Summary of changes
     - List of specific modifications
     - Test results
     - Reference to the Jira story (e.g., "Closes <issue-key>")