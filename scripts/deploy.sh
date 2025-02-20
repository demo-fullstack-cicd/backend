#!/bin/bash
set -eux

# S3에서 .env 파일 다운로드 (EC2 홈 디렉토리로 다운로드)
aws s3 cp s3://${S3_BUCKET_NAME}/.env /home/ec2-user/.env

# 다운로드한 .env 파일의 권한을 ec2-user에게 부여
sudo chown ec2-user:ec2-user /home/ec2-user/.env

# .env 파일이 존재하는지 확인 후 환경 변수 로드
if [ -f "/home/ec2-user/.env" ]; then
    set -a  # 자동으로 export 처리
    source /home/ec2-user/.env
    set +a
else
    echo "🚨 .env 파일이 존재하지 않습니다!"
    exit 1
fi

# Docker 로그인
docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"

# Docker 이미지 pull
docker pull "${DOCKER_USERNAME}/docker-test:latest"

# 기존 컨테이너 중지 및 삭제
if [ "$(docker ps -q -f name=spring-app)" ]; then
  docker stop spring-app
  docker rm spring-app
fi

# docker-compose 실행
cd /home/ec2-user/app
docker-compose up -d --no-deps app

# 불필요한 이미지 삭제
docker image prune -a -f
