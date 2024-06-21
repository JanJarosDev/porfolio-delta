import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.withType

class CollectSarifPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val lintMergeTask by target.tasks.register(MERGE_LINT_TASK_NAME, ReportMergeTask::class.java) {
            group = JavaBasePlugin.VERIFICATION_GROUP
            output.set(project.layout.buildDirectory.file("lint-merged.sarif"))
        }
        val detektMergeTask by target.tasks.register(MERGE_DETEKT_TASK_NAME, ReportMergeTask::class.java) {
            group = JavaBasePlugin.VERIFICATION_GROUP
            output.set(project.layout.buildDirectory.file("detekt-merged.sarif"))
        }
    }

    companion object {
        const val MERGE_LINT_TASK_NAME: String = "mergeLintSarif"
        const val MERGE_DETEKT_TASK_NAME: String = "mergeDetektSarif"
    }
}