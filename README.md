--Install Docker Desktop  

--Install kubectl  

-- Installing Minikube for MacOS  
minikube start --driver=docker

--Enable minikube addons  
minikube addons list  
minikube addons enable metrics-server  
minikube addons enable dashboard  

--Accessing minikube dashboard
minikube dashboard  

--Accessing your API externally  
Use service type as NodePort which will allow you to access the service with <</Node-IP>>:<<NodePort>>  
If you are using minikube with Docker driver then you have to start the tunnel with following command:  
minikube service <service-name> -n <<namespace>>  

--Installing ArgoCD on minikube  
kubectl create namespace argocd  
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml  
kubectl get all -n argocd  

--Accessing ArogoCD in browser  
kubectl port-forward svc/argocd-server -n argocd 8080:443  
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
