job('NodeJS example') {  // name of the and initial tag to start
	scm {  // software configuration manager git
		git('git@github.com:ankitgiri45/JenkinsDockerNodeJS.git') { node ->
			node / gitConfigName('ankitgiri45')
			node / gitConfigEmail('ankitgiri45@gmail.com')
		}
	}
	
	triggers { // trigger build 
		scm('H/5 * * * *')  // pull every 5 min and build if there is a change
	}
	
	wrappers {
		nodejs('NodeJS') // this is the name of the NodeJS installation in
				       // Manage Jenkins -> Configure Tools ->NodeJS Installations ->Name
	}
	
	steps { // build steps
		dockerBuildAndPublish{
			repositoryName('8130533943/nodejs_docker_jenkins_seed_version')
			tag('${GIT_REVISION, length=9}')
			registryCredentials('dockerhub')
			forcePull(false)
			forceTag(false)
			createFingerprints(false)
			skipDecorate()
		}
	}
}
