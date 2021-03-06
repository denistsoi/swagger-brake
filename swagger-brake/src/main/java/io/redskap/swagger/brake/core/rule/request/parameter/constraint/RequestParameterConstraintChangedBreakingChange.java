package io.redskap.swagger.brake.core.rule.request.parameter.constraint;

import static java.lang.String.format;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestParameterConstraintChangedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String attributeName;
    private final RequestParameterConstraintChange constraintChange;

    @Override
    public String getMessage() {
        return format("The %s constraint for attribute %s was changed in %s %s from %s to %s ", constraintChange.getAttribute(),
            attributeName, method, path, constraintChange.getOldValue(), constraintChange.getNewValue());
    }
}
