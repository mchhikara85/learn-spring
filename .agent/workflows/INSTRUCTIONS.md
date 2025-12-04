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
     - Follow existing code patterns and conventions
     - Keep methods focused and single-purpose
     - Use meaningful variable and method names
     - Add comments for complex logic
     - Ensure proper error handling

5. **Create Unit Tests**
   - **All code changes must include unit tests.**
   - Write comprehensive test cases covering both success and error scenarios.
   - Follow the existing test patterns in the codebase.
   - Mock dependencies appropriately.
   - **Test locations:** Place tests in the standard test directory for the language/framework (e.g., `src/test/java/...` for Java, `tests/` for Python).
   - **Testing Standards:**
     - Minimum code coverage: Aim for high coverage of new code
     - Test both happy path, edge cases and error scenarios
     - Use descriptive test method names
     - Keep tests independent and isolated

6. **Run All Tests**
   - Before committing, ensure all tests pass.
   - Command: `mvn test` (Spring Boot) or `npm test` (Angular/JavaScript)
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