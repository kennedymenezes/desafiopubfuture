package br.com.desafiopubfuture.swagger;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Interceptador extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String txId = RandomStringUtils.randomAlphanumeric(8);
        MDC.clear();
        MDC.put("tx-id", txId);
        long executionStartTime = System.currentTimeMillis();
        request.setAttribute("start-time", executionStartTime);

        if (handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            String controllerName = handlerMethod.getBeanType().getName();
            String actionName = handlerMethod.getMethod().getName();

            StringBuilder dadosRequest = new StringBuilder();
            if (request != null) {
                dadosRequest.append(" | RequestURI : ").append(request.getRequestURI()).append(" | RemoteHost : ")
                        .append(request.getRemoteHost()).append(" | RemoteAddr : ").append(request.getRemoteAddr());
            }

            log.info("BEGIN servico " + controllerName + "." + actionName + dadosRequest);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            log.error("[afterCompletion]", ex);
        }

        long executionStartTime = (Long) request.getAttribute("start-time");

        long renderingStartTime = System.currentTimeMillis();
        long renderingDuration = renderingStartTime - executionStartTime;

        LocalDateTime duration = LocalDateTime.ofInstant(Instant.ofEpochMilli(renderingDuration),
                ZoneId.of("America/Sao_Paulo"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
        // Total Time including the Request Time + Response Time.
        String elapsedTime = duration.format(formatter);

        log.info("END  |  --- Tempo decorrido no request/response: {} milisegundos", elapsedTime);
    }
}
