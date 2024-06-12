--Install Docker Desktop  

-- Installing Minikube for MacOS  
brew install minikube  

--start with docker  
minikube start --driver=docker  

--start with qemu  
brew install qemu  
brew install socket_vmnet  
brew tap homebrew/services  
HOMEBREW=$(which brew) && sudo ${HOMEBREW} services start socket_vmnet  
minikube start --driver=qemu --network=socket_vmnet

--Enable minikube addons  
minikube addons list  
minikube addons enable metrics-server  
minikube addons enable dashboard  
minikube addons enable ingress  

--Accessing minikube dashboard  
minikube dashboard  

--Accessing your API externally  
Use service type as NodePort which will allow you to access the service with Node-IP:NodePort  
If you are using minikube with Docker driver then you have to start the tunnel with following command:  
minikube service service-name -n namespace  
If you are accessing using Ingress then you need to update host configuration on our workstation to the Minikube IP for those URLs:
sudo vim /etc‌‌/‌hosts

--Installing ArgoCD on minikube  
kubectl create namespace argocd  
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml  
kubectl create namespace argo-rollouts  
kubectl apply -n argo-rollouts -f https://github.com/argoproj/argo-rollouts/releases/latest/download/install.yaml  
kubectl get all -n argocd  

--Accessing ArogoCD in browser  
kubectl port-forward svc/argocd-server -n argocd 8080:443  
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
