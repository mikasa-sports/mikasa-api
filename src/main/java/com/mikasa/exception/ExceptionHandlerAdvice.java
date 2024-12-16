package com.mikasa.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlerAdvice extends DataFetcherExceptionResolverAdapter {

  private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

  @Override
  protected List<GraphQLError> resolveToMultipleErrors(@NotNull Throwable ex,
      @NotNull DataFetchingEnvironment env) {
    if (ex instanceof BaseException e) {
      return handleBase(e);
    }
    return super.resolveToMultipleErrors(ex, env);
  }

  public List<GraphQLError> handleBase(BaseException ex) {
    Error error = ex.getError();
    AtomicReference<String> logMessageReference = new AtomicReference<>(error.getLogMessage());
    error.getLogInputParameterNames()
        .forEach(name -> logMessageReference.set(logMessageReference.get()
            .replace("[" + name + "]", ex.getLogMessageParameters().get(name))));

    log.atLevel(error.getLevel()).log(logMessageReference.get());

    return List.of(GraphQLError.newError()
        .message(error.getMessage())
        .errorType(ErrorClassification.errorClassification(error.getCode().toString()))
        .build());
  }
}
