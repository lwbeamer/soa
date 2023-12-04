## Перед запуском
Установите сертификат в trust store своего jdk  
```shell
keytool -import -alias soa -file soa.crt -keystore $JAVA_HOME/lib/security/cacerts
```
> Базовый ключ для файла cacerts - `changeit` \
> Также обеспечьте все используемые jdk данным сертификатом 

Также в config сервисе установите необходимые переменные окружения в файле `application.yml`