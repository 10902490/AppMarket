import org.gradle.api.Project

const val GitVersionCodeFallback = 0

/** 提交总数，用作 versionCode（等价于 `git rev-list --count HEAD`）。失败时返回回退值。 */
fun Project.getGitVersionCode(): Int =
    runCatching {
        providers.exec {
            commandLine("git", "rev-list", "--count", "HEAD")
        }.standardOutput.asText.get().trim().toInt()
    }.getOrDefault(GitVersionCodeFallback)

/**
 * 先尝试 git 提交数。大于等于 [ProjectConfig.VERSION_CODE] 时采用该值，
 * 本地构建还会把常量回写到 [ProjectConfig]；失败或小于现有值时沿用 VERSION_CODE。
 */
fun Project.resolveVersionCode(): Int {
    val baseline = ProjectConfig.VERSION_CODE
    val gitCode = getGitVersionCode()
    if (gitCode == GitVersionCodeFallback || gitCode < baseline) return baseline
    if (gitCode > baseline && !isCiBuild()) {
        updateProjectVersionCode(gitCode)
    }
    return gitCode
}

private fun Project.isCiBuild(): Boolean =
    providers.environmentVariable("CI").orNull.toBoolean() ||
            providers.environmentVariable("GITHUB_ACTIONS").orNull.toBoolean()

private fun Project.updateProjectVersionCode(versionCode: Int) {
    val file = rootProject.file("buildSrc/src/main/kotlin/ProjectConfig.kt")
    val current = file.readText()
    val updated = current.replace(
        Regex("""const val VERSION_CODE = \d+"""),
        "const val VERSION_CODE = $versionCode",
    )
    if (updated != current) file.writeText(updated)
}
