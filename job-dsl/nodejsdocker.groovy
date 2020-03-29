job('NodeJS Docker Example') {
    scm {
        git('https://github.com/Someshwar-Thogu/docker-demo.git')
        {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Someshwar')
            node / gitConfigEmail('someshwar@srt.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('someshdh/first-docker-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('sdockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
