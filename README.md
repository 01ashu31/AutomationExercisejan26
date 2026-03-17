## Automation Exercise - UI Test Framework

### What is implemented

- **Cross-browser execution** with Chrome, Firefox, and Edge.
- **Parallel execution** at TestNG suite level (`parallel="tests"` with `thread-count="3"`).
- **Jenkins CI/CD pipeline** with:
  - single-browser mode,
  - cross-browser matrix mode (parallel),
  - JUnit + Extent report archiving,
  - GitHub webhook trigger support via `githubPush()`.

### Run locally

```bash
mvn clean test
```

Useful overrides:

```bash
mvn test -Dbrowser=chrome -Dheadless=true -DsuiteXmlFile=src/test/resources/testng.xml
```

### Jenkins webhook setup (GitHub -> Jenkins)

1. In Jenkins, ensure the job is reachable (public URL or via tunnel/VPN).
2. In GitHub repo settings, add webhook:
   - **Payload URL**: `https://<your-jenkins-url>/github-webhook/`
   - **Content type**: `application/json`
   - **Events**: "Just the push event" (or selected PR events as needed)
3. In Jenkins job/pipeline, `githubPush()` trigger in `Jenkinsfile` handles incoming pushes.
4. Validate from GitHub webhook delivery logs (response should be HTTP 200).

### Notes

- `headless=true` is enabled by default for CI stability.
- Browser can be selected by TestNG parameter (`browser`) or Maven system property (`-Dbrowser=...`).
