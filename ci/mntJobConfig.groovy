organizationFolder('EpamHackathon') {
    description('This contains branch source jobs for Hackaton')
    displayName('EpamHackathon')
  
    organizations {
        github {
            apiUri("GitHub")
            // Specify the name of the GitHub Organization or GitHub User Account.      
            repoOwner("EpamHackathon")
          
            // Credentials used to scan branches and pull requests, check out sources and mark commit statuses.
            scanCredentialsId("token")
          
            // Credentials used to check out sources during a build.
            checkoutCredentialsId("token")
          
            // Whether to build branches defined in the origin (primary) repository, not associated with any pull request.
            buildOriginBranch(true)
          
            // Whether to build branches defined in the origin (primary) repository for which pull requests happen to have been filed.
            buildOriginBranchWithPR(false)
                
            // Whether to build pull requests filed from branches in the origin repository.
            buildOriginPRHead(true)
          
            // Whether to build pull requests filed from branches in the origin repository.
            buildOriginPRMerge(false)
          
            // Whether to build pull requests filed from forks of the main repository.
            buildForkPRHead(true)
          
            // Whether to build pull requests filed from forks of the main repository.
            buildForkPRMerge(false)
          
            // Regular expression to specify what repositories one wants to include
            pattern('.*SpringBoot.*')      
        }
    }  
  
    triggers {
        periodic(86400)
    }
}
