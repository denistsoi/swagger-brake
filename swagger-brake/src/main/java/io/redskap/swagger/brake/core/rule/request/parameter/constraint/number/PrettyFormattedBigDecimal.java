package io.redskap.swagger.brake.core.rule.request.parameter.constraint.number;

import java.math.BigDecimal;

import io.redskap.swagger.brake.core.util.DecimalFormatter;

public class PrettyFormattedBigDecimal extends BigDecimal {
    public PrettyFormattedBigDecimal(BigDecimal value) {
        super(value.unscaledValue(), value.scale());
    }

    @Override
    public String toString() {
        return DecimalFormatter.format(new BigDecimal(this.unscaledValue(), this.scale()));
    }
}
