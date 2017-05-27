import hudson.model.FreeStyleProject
import hudson.plugins.git.BranchSpec
import hudson.plugins.git.extensions.impl.RelativeTargetDirectory
import hudson.plugins.git.extensions.impl.SparseCheckoutPath
import hudson.plugins.git.extensions.impl.SparseCheckoutPaths
import hudson.plugins.git.GitSCM
import hudson.tasks.Shell
import hudson.triggers.SCMTrigger
import javaposse.jobdsl.plugin.ExecuteDslScripts
import javaposse.jobdsl.plugin.*
import jenkins.model.Jenkins
import org.jenkinsci.plugins.multiplescms.MultiSCM
import hudson.plugins.ws_cleanup.PreBuildCleanup
import hudson.model.ChoiceParameterDefinition
import hudson.model.ParametersDefinitionProperty
import org.jenkinsci.plugins.envinject.EnvInjectBuildWrapper
import org.jenkinsci.plugins.envinject.EnvInjectJobPropertyInfo

def job = { name ->
    jenkins = Jenkins.instance

    project = new FreeStyleProject(jenkins, name)
    pollTrigger = new SCMTrigger("*/5 * * * *")
    project.with {
        scm = new MultiSCM({
            ciScm = new GitSCM("git@github.com:EpamHackathon/DemoSpringBootApp.git")
            ciScm.userRemoteConfigs[0].credentialsId = 'github-username-token'
            ciScm.branches = [new BranchSpec('$BRANCH_NAME')]
            [ciScm]
        }())

        cleanupWrapper = new PreBuildCleanup([], false, "", "")

        buildWrappersList.addAll([cleanupWrapper])

        addProperty(new ParametersDefinitionProperty(
            new StringParameterDefinition("BRANCH_NAME", "master", "")))

        dslBuilder = new ExecuteDslScripts()
        dslBuilder.setTargets('ci/pipelineJobs.groovy')

        buildersList.addAll([dslBuilder])
        createTransientActions()
        addTrigger(pollTrigger)
    }
    jenkins.add(project, name)
    pollTrigger.start(project, true)
}

job("DSL")
