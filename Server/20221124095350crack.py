apiVersion: v1

kind: Service

metadata:

  name: nginx-service

spec:

  type: NodePort

  selector:

    app: nginx-app

    type: front-end

  ports:

    - port: 80

      targetPort: 80

      nodePort: 30011
      
  externalIPs: 
    - 192.168.56.2
---

apiVersion: apps/v1

kind: Deployment

metadata:

  name: nginx-deployment

  labels:

    app: nginx-app

    type: front-end

spec:

  replicas: 3

  selector:

    matchLabels:

      app: nginx-app

      type: front-end

  template:

    metadata:

      labels:

        app: nginx-app

        type: front-end

    spec:

      containers:

        - name: nginx-container

          image: nginx:latest

