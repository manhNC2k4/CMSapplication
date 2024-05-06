# Sử dụng một base image cho Java
FROM openjdk:17

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR vào image
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]