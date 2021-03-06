package io.redskap.swagger.brake.core.rule.response;

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
public class ResponseTypeEnumValueDeletedBreakingChange implements BreakingChange {
    private final String path;
    private final HttpMethod method;
    private final String enumValue;

    @Override
    public String getMessage() {
        return format("Enum value %s has been deleted in %s %s", enumValue, method, path);
    }
}
