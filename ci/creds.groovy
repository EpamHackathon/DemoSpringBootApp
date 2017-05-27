import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*
import org.jenkinsci.plugins.plaincredentials.*
import org.jenkinsci.plugins.plaincredentials.impl.*
import hudson.util.Secret

domain = Domain.global()
store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

secretText = new StringCredentialsImpl(
CredentialsScope.GLOBAL,
"dockerhub_creds",
"DockerHub credentials: Secret Text",
Secret.fromString("-u burakovsky -p 6ypa4ok"))

gitHubusernameAndPassword = new UsernamePasswordCredentialsImpl(
CredentialsScope.GLOBAL,
"github-username-password", "GitHub creds: Username and Password",
"jenkinshack",
"changeme123"
)
gitHubusernameAndToken = new UsernamePasswordCredentialsImpl(
CredentialsScope.GLOBAL,
"github-username-token", "GitHub creds: Username and Token",
"jenkinshack",
"5278b5049f32b432d29e2ecc3244c6cfa134fd0b"
)
store.addCredentials(domain, secretText)
store.addCredentials(domain, usernameAndPassword)
store.addCredentials(domain, usernameAndToken)