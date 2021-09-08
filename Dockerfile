# 基础镜像
FROM java:8
# 作者
MAINTAINER shanghaifei
# 将shf-id-generator-server模块target文件夹下的jar拷贝到容器内的根目录
ADD ./shf-id-generator-server/target/shf-id-generator-server-1.0.0.jar /shf-id-generator-server-1.0.0.jar
# 端口设置
EXPOSE 9192 9192
# 执行命令
CMD ["java","-jar","/shf-id-generator-server-1.0.0.jar"]