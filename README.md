# 콘티 서비스 백엔드

## local 에서
### docker build
docker build --build-arg DEPENDENCY=build/dependency -t piamenia/jungang --platform linux/amd64 --no-cache .
### docker push
docker push piamenia/jungang

## 서버에서
### docker pull
{sudo} docker pull piamenia/jungang
### docker run
{sudo} docker run -d -p 8081:8081 piamenia/jungang
