# Instructions for contributing to learn-spring

Thank you for contributing to this project! This document outlines the standard development workflow that applies to all Jira stories and code changes.

## Development Workflow

When working on any Jira story, follow these steps:

### 1. Jira Workflow

Before starting any work:
1.  **Assign the Jira story to yourself.**
2.  **Move the status to "In Progress".**

### 2. Create a Feature Branch

Always create a new branch for your changes. Use a descriptive branch name that includes the Jira ticket number:

```bash
git checkout -b feature/SCRUM-XXX-brief-description
```

**Branch naming conventions:**
- Feature: `feature/SCRUM-XXX-description`
- Bug fix: `bugfix/SCRUM-XXX-description`
- Hotfix: `hotfix/SCRUM-XXX-description`

### 3. Implement the Feature

Write clean, maintainable code that follows the existing patterns in the codebase.

### 4. Create Unit Tests

**All code changes must include JUnit unit tests.**

- Write comprehensive test cases covering both success and error scenarios
- Follow the existing test patterns in the codebase
- Use `@WebMvcTest` for controller tests
- Use `MockMvc` for HTTP request simulation
- Mock dependencies appropriately

**Example test location:**
- Controller tests: `src/test/java/com/manjeet/learnspring/controller/`
- Repository tests: `src/test/java/com/manjeet/learnspring/data/repository/`

### 5. Run All Tests

Before committing, ensure all tests pass:

```bash
./mvnw test
```

**Requirements:**
- All existing tests must continue to pass (no regression)
- All new tests must pass
- Zero test failures or errors

### 6. Commit Your Changes

Write clear, descriptive commit messages:

```bash
git add .
git commit -m "feat(SCRUM-XXX): Brief description of changes

- Detailed point 1
- Detailed point 2
- All tests passing"
```

**Commit message format:**
- `feat(SCRUM-XXX):` for new features
- `fix(SCRUM-XXX):` for bug fixes
- `test(SCRUM-XXX):` for test-only changes
- `refactor(SCRUM-XXX):` for refactoring

### 7. Push Your Branch

```bash
git push -u origin feature/SCRUM-XXX-description
```

### 8. Create a Pull Request

Create a pull request against the `main` branch with:

**PR Title:**
```
feat(SCRUM-XXX): Brief description
```

**PR Description should include:**
- Summary of changes
- List of specific modifications
- Test results (e.g., "All tests passing (X/X ✅)")
- Reference to the Jira story (e.g., "Closes SCRUM-XXX")

**Example PR description:**
```markdown
## Description
Brief description of what this PR accomplishes.

## Changes
- Added new endpoint for XYZ
- Updated ABC to handle edge case
- Added unit tests for success and error scenarios

## Testing
✅ All tests passing (9/9)
- Existing tests continue to pass
- New tests verify both success and error cases

## Related Issue
Closes SCRUM-XXX
```

### 9. Complete the Story

Once your Pull Request is merged:
1.  **Add label "Promoted to QA" to the Jira story.**

## Code Quality Standards

- Follow existing code patterns and conventions
- Keep methods focused and single-purpose
- Use meaningful variable and method names
- Add comments for complex logic
- Ensure proper error handling

## Testing Standards

- Minimum code coverage: Aim for high coverage of new code
- Test both happy path and error scenarios
- Use descriptive test method names (e.g., `testUpdateNonExistentRoom`)
- Keep tests independent and isolated

## Questions?

If you have questions about the development workflow, reach out to the team lead or check existing PRs for examples.

---

**Remember:** These steps apply to EVERY Jira story. The Jira story description should focus only on the functional requirements, not these standard workflow steps.
