package io.redskap.swagger.brake.core.rule.request.parameter.constraint.number;

import java.math.BigDecimal;
import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.NumberRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import io.redskap.swagger.brake.core.util.BigDecimalComparator;
import org.springframework.stereotype.Component;

@Component
class NumberMaximumConstraint implements RequestParameterConstraint<NumberRequestParameter> {
    public static final String MAXIMUM_ATTRIBUTE_NAME = "maximum";
    public static final String EXCLUSIVE_MAXIMUM_ATTRIBUTE_NAME = "exclusiveMaximum";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(NumberRequestParameter oldRequestParameter, NumberRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            if (oldRequestParameter.isNumberTyped() && newRequestParameter.isNumberTyped()) {
                BigDecimal oldMaximum = oldRequestParameter.getMaximum();
                BigDecimal newMaximum = newRequestParameter.getMaximum();
                if (oldMaximum == null && newMaximum != null) {
                    result = new RequestParameterConstraintChange(MAXIMUM_ATTRIBUTE_NAME,
                        null,
                        new PrettyFormattedBigDecimal(newMaximum)
                    );
                }
                if (oldMaximum != null && newMaximum != null) {
                    boolean oldExclusiveMaximum = oldRequestParameter.isExclusiveMaximum();
                    boolean newExclusiveMaximum = newRequestParameter.isExclusiveMaximum();
                    if (oldExclusiveMaximum == newExclusiveMaximum && BigDecimalComparator.isLessThan(newMaximum, oldMaximum)) {
                        result = new RequestParameterConstraintChange(MAXIMUM_ATTRIBUTE_NAME,
                            new PrettyFormattedBigDecimal(oldMaximum),
                            new PrettyFormattedBigDecimal(newMaximum)
                        );
                    } else {
                        BigDecimal oldMaxWithExclusivity = oldMaximum.subtract(oldExclusiveMaximum ? BigDecimal.ONE : BigDecimal.ZERO);
                        BigDecimal newMaxWithExclusivity = newMaximum.subtract(newExclusiveMaximum ? BigDecimal.ONE : BigDecimal.ZERO);
                        if (BigDecimalComparator.isLessThan(newMaxWithExclusivity, oldMaxWithExclusivity)) {
                            result = new RequestParameterConstraintChange(EXCLUSIVE_MAXIMUM_ATTRIBUTE_NAME,
                                oldExclusiveMaximum,
                                newExclusiveMaximum
                            );
                        }
                    }
                }
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Class<NumberRequestParameter> handledRequestParameter() {
        return NumberRequestParameter.class;
    }
}
