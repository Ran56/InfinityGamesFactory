export CATALINA_OPTS="$CATALINA_OPTS -Dlogging.level.com.infinity=DEBUG"
export CATALINA_OPTS="$CATALINA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export CATALINA_OPTS="$CATALINA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
export CATALINA_OPTS="$CATALINA_OPTS -Ddatabase.url=jdbc:postgresql://${DB_URL}:5432/${DB_NAME}"
export CATALINA_OPTS="$CATALINA_OPTS -Ddatabase.user=${DB_USER}"
export CATALINA_OPTS="$CATALINA_OPTS -Ddatabase.password=${DB_PASSWORD}"
export CATALINA_OPTS="$CATALINA_OPTS -Dsecret.key=Aa123456"
export CATALINA_OPTS="$CATALINA_OPTS -DbucketName=infinity-s3-bucket-1"
export CATALINA_OPTS="$CATALINA_OPTS -Dspring.profiles.active=dev"
export CATALINA_OPTS="$CATALINA_OPTS -DqueueName=infinity-standard-queue"
export CATALINA_OPTS="$CATALINA_OPTS -Djms.queue.name=infinity-standard-queue"