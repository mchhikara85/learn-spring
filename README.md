--Install Docker Desktop  

--Install kubectl  

-- Installing Minikube for MacOS  
minikube start --driver=docker

--Accessing minikube dashboard  
minikube addons list  
minikube addons enable metrics-server  
minikube addons enable dashboard  
minikube dashboard  

--Installing ArgoCD on minikube  
kubectl create namespace argocd  
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml  
kubectl get all -n argocd  

--Accessing ArogoCD in browser  
kubectl port-forward svc/argocd-server -n argocd 8080:443  
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
