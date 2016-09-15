/*
 * Copyright (c) 2016 Santanu Sinha <santanu.sinha@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.appform.jsonrules;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.appform.jsonrules.expressions.composite.AndExpression;
import io.appform.jsonrules.expressions.composite.NotExpression;
import io.appform.jsonrules.expressions.composite.OrExpression;
import io.appform.jsonrules.expressions.equality.EqualsExpression;
import io.appform.jsonrules.expressions.equality.InExpression;
import io.appform.jsonrules.expressions.equality.NotEqualsExpression;
import io.appform.jsonrules.expressions.equality.NotInExpression;
import io.appform.jsonrules.expressions.numeric.GreaterThanEqualsExpression;
import io.appform.jsonrules.expressions.numeric.GreaterThanExpression;
import io.appform.jsonrules.expressions.numeric.LessThanEqualsExpression;
import io.appform.jsonrules.expressions.numeric.LessThanExpression;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A base expression
 */
@Data
@EqualsAndHashCode
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "equals", value = EqualsExpression.class),
        @JsonSubTypes.Type(name = "not_equals", value = NotEqualsExpression.class),
        @JsonSubTypes.Type(name = "in", value = InExpression.class),
        @JsonSubTypes.Type(name = "not_in", value = NotInExpression.class),

        @JsonSubTypes.Type(name = "greater_than", value = GreaterThanExpression.class),
        @JsonSubTypes.Type(name = "greater_than_equals", value = GreaterThanEqualsExpression.class),
        @JsonSubTypes.Type(name = "less_than", value = LessThanExpression.class),
        @JsonSubTypes.Type(name = "less_than_equals", value = LessThanEqualsExpression.class),

        @JsonSubTypes.Type(name = "and", value = AndExpression.class),
        @JsonSubTypes.Type(name = "or", value = OrExpression.class),
        @JsonSubTypes.Type(name = "not", value = NotExpression.class),
})
public abstract class Expression {
    private final ExpressionType type;

    protected Expression(ExpressionType type) {
        this.type = type;
    }

    abstract public boolean evaluate(ExpressionEvaluationContext context);
}