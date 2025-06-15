package io.ilan.config;

import lombok.Getter;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringValueResolver;

import static io.ilan.config.SpellResolver.BEAN_NAME;

@Configuration(BEAN_NAME)
public class SpellResolver implements EmbeddedValueResolverAware {

    /**
     * Bean name for this class
     */
    public static final String BEAN_NAME = "manualSpellExpression";

    @Getter
    private static StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    public String resolve(String value) {
        return resolver.resolveStringValue(value);
    }


}
