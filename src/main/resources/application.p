spring.application.name=movies-reate

# DataSource Configuration
spring.datasource.driver-class-name=org.postgresql.Driver


spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


spring.sql.init.platform=postgres


spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.properties.javax.persistence.processing.querydsl=true



logging.level.org.springframework=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
