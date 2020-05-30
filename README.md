# visitor-application

### Description:
This project will demonstrate how to build and deploy dockerize application into kubernetes cluster (For demo used minikube).

### Dependencies:
To run this project you need to install below componenets in your system.
1. JDK-11
2. Redis server (Docker image should be ok)
3. Docker
4. Minicube
5. kubectl/kubeadm

### Build and Push Docker Image:
1. In this assignment, I am building a micro service and it's docker image pushing into my own docker hub repository. I have created a docker repository called "rlekkalaa/visitor-app".
2. Created a build.sh script to build and push the docker images. 
3. Before running the build.sh make sure you have login into the docker hub by running below command.
    * docker login
4. Next run the build.sh and make sure you have docker hub repository created to push your docker images.
     * ./build.sh <repository>
     * Ex: ./build.sh rlekkalaa/visitor-app
5. On successful build you will be able to see the docker image on your docker hub reposiotry.

### Deploy Instructions

Note: You can directly deploy by skipping build step. By default deployment script will use my docker image from my docker hub.

To deploy the successful docker images to kubernetes I have build two solutions use any one of them based on your environment configured on your system.
1. Kubernetes service Deployment
2. Jenkins Pipeline
###### Kubernetes service Deployment
* ./deploy.sh or 
* kubectl create -f microservice-deployment.yml
###### Jenkins Pipeline
* Imort the Jenkinsfile to your Jenkins pipline and run the deployment through Jenkins
### Test Instructions
On successful deployment you can run below script to test weather your deployed services are working expected or not. 
* ./test.sh

### CI/CD Work flow:
<img src="images/CI:CD Work flow.png" center="ture" width="1500">

### Jenkins Pipeline:
<img src="images/Jenkins Pipeline.png" center="ture" width="1500">

### After successful Jenkins Pipeline run:
<img src="images/Jenkinspipeline.png" center="ture" width="1500">

### AWS deployment solution
There are different solutions we can build with AWS And Azure. As a example attaching one of the AWS solution.
<img src="images/aws.png" center="ture" width="1500">

Note: Not only with the kuberentes we can also deploy the generated images into docker data center (DDC) by having docker-compose file.