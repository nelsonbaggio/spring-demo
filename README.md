# Spring Boot + Logback + HttpInterceptor + Correlation Identifier

Um conceito importante que eu aprendi no meu trabalho atual é a utilização de um [Correlation Identifier](https://blog.rapid7.com/2016/12/23/the-value-of-correlation-ids/) para identificar unicamente cada requisição que passa por um determinado serviço em uma aplicação altamente distribuída, e apresentar esse corelation id (ou CID) nos logs é uma ferramenta poderosa para *troubleshooting*.

Nessa primeira versão da minha sandbox para treinar algumas coisas relacionadas a Spring eu queria automatizar a criação do CID e também a sua aparição nos logs.

- Primeiro eu segui esse [tutorial](https://dzone.com/articles/correlation-id-for-logging-in-microservices) que me deu as primeiras informações. Eu precisava implementar um **interceptor de http** (o **CorrelationInterceptor.java** desse projeto) para capturar o CID que eventualmente poderia estar sendo passado em um header da requisição, por exemplo vindo de outro serviço.

- Depois do interceptor não funcionar de primeiro (claro!) vi que era necessário implementar uma configuração baseada na classe [WebMvcConfigurationSupport](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html)

- Quase Pronto! Via *debug* deu pra perceber que o *interceptor* agora estava funcionando e que o [MDC](http://logback.qos.ch/manual/mdc.html) estava configurado com o CID agora falta padronizar o log para recebe-lo.

- Com a ajuda desse [tutorial](https://www.baeldung.com/spring-boot-logging) eu criei meu arquivo **logback-spring.xml**.

Pronto! Agora os logs estão funcionando :)

## Veja você também:

Para rodar a aplicação (depois de clonar o projeto):

``` shell
./gradlew bootRun
```

Para testar a chamada da API passando um UUID como header e ver os logs acontecerem:
``` shell
curl -v -H "X-Correlation-Id: $(uuidgen)" localhost:8080
```

## Outras referências:

- Para brincar com as cores do log: http://logback.qos.ch/manual/layouts.html#coloring
- Para sumarizar o tamanho dos pacotes: http://logback.qos.ch/manual/layouts.html#conversionWord
- Mais informações para o console appender: https://lankydan.dev/2019/01/09/configuring-logback-with-spring-boot
- Mais sobre Spring MVC Handler: https://www.baeldung.com/spring-mvc-handlerinterceptor
- Leia sobre Logback: http://logback.qos.ch/ 