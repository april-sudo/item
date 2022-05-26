# item

## Running in local development environment

```
mvn spring-boot:run
```

## Packaging and Running in docker environment

```
mvn package -B
docker build -t username/item:v1 .
docker run username/item:v1
```

## Push images and running in Kubernetes

```
docker login 
# in case of docker hub, enter your username and password

docker push username/item:v1
```

Edit the deployment.yaml under the /kubernetes directory:
```
    spec:
      containers:
        - name: item
          image: username/item:latest   # change this image name
          ports:
            - containerPort: 8080

```

Apply the yaml to the Kubernetes:
```
kubectl apply -f kubernetes/deployment.yml
```

See the pod status:
```
kubectl get pods -l app=item
```

If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
```
# new terminal
kubectl port-forward deploy/item 8080:8080

# another terminal
http localhost:8080
```

If you have any problem on running the pod, you can find the reason by hitting this:
```
kubectl logs -l app=item
```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
1. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

## test

http :8083/items 
http :8083/items name="tv" price=2000 status="CREATED"
http DELETE :8083/items/1


http :8083/reviews itemId=1 score=100
http PATCH :8083/reviews/2 itemId=1 score=80
http DELETE :8083/reviews/1

http DELETE :8083/items/2

## health check
http :8083/actuator/health

{
    "groups": [
        "liveness",
        "readiness"
    ],
    "status": "UP"
}

## Auto Scaling 
kubectl autoscale deployment item --cpu-percent=20 --min=1 --max=3
kubectl exec -it siege -- /bin/bash
siege -c1 -t2S -v http://items:8080/items
exit