---

## `CONTRIBUTING.md`

```markdown
# Contributing to SoundSphere

Thank you for contributing to SoundSphere!  
This document describes the version control workflow, commit conventions, and pull request process for this repository.

---

## Branch Strategy

We follow a **Trunk-Based Development** approach:

- **main**  
  Always stable and deployable. All production-ready code lives here.

- **feat/**  
  For new features. Example: `feat/session-tracking`

- **fix/**  
  For bug fixes. Example: `fix/authentication-timeout`

- **docs/**  
  For documentation-only changes. Example: `docs/update-readme`

- **chore/**  
  For build, CI/CD, or configuration tasks. Example: `chore/ci-pipeline`

---

## Commit Message Convention

We use **Conventional Commits** to keep history consistent:

```

<type>(optional scope): <short summary>

```

### Types
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation only changes
- `style`: Code style changes (no logic impact)
- `refactor`: Code changes that neither fix a bug nor add a feature
- `test`: Adding or updating tests
- `chore`: Build process, tools, CI/CD changes

### Examples
```

feat: add practice session completion endpoint
fix(auth): handle expired refresh tokens properly
docs: update system architecture diagram in README
chore(ci): add GitHub Actions workflow for backend build

````

---

## Workflow

1. **Create a new branch** from `main`:
   ```bash
   git checkout -b feat/session-tracking
````

2. **Commit your changes** following the convention:

   ```bash
   git add .
   git commit -m "feat: add session tracking entity and repository"
   ```

3. **Push your branch** to GitHub:

   ```bash
   git push -u origin feat/session-tracking
   ```

4. **Open a Pull Request (PR)** on GitHub:

    * Use the PR template provided in `.github/PULL_REQUEST_TEMPLATE.md`.
    * Ensure all CI checks pass.
    * Keep PRs small and focused (recommended <300 lines).

5. **Review and merge**:

    * All PRs should be reviewed before merging into `main`.
    * Use **Squash and Merge** to keep commit history clean.

---

## Releases

* Tag versions using [Semantic Versioning](https://semver.org/):

    * `vMAJOR.MINOR.PATCH` (e.g., `v0.1.0`)
* Example:

  ```bash
  git tag -a v0.1.0 -m "SoundSphere v0.1.0"
  git push origin v0.1.0
  ```

---

## Guidelines

* Write clear, descriptive commit messages.
* Keep PRs small and focused.
* Add/update tests when making code changes.
* Update documentation (README, API docs) if behavior changes.
* Make sure CI/CD pipeline passes before requesting review.

---

## Questions?

If you have any questions about the workflow or contribution process, feel free to open an issue or start a discussion.

```
- 定義分支策略（main + feat/* + fix/* + docs/* + chore/*）  
- 規範 commit message（Conventional Commits）  
- 描述 PR 流程（建立分支 → commit → push → PR → review → squash merge）  
- 定義 release 規範（semantic versioning + tag）  
```
