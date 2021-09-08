#删除所有组件
kubectl delete -n dev deployment shf-id-generator-server
kubectl delete -n dev service shf-id-generator-server
kubectl delete -n dev ingress shf-id-generator-server