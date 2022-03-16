package io.devcon5.jsonb.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Simple wrapper that will look for @JsonbProperty before delegating to the supplied {@link AnnotationIntrospector}
 */
public class JsonbAnnotationIntrospector extends AnnotationIntrospector {
    private final AnnotationIntrospector delegate;

    public JsonbAnnotationIntrospector(final AnnotationIntrospector delegate) {
        this.delegate = delegate;
    }

    @Override
    public Version version() {
        return com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION;
    }

    @Override
    public PropertyName findNameForSerialization(final Annotated annotated) {
        final JsonbProperty annotation = _findAnnotation(annotated, JsonbProperty.class);
        if (annotation != null) {
            return PropertyName.construct(annotation.value(), null);
        }

        return delegate.findNameForSerialization(annotated);
    }

    @Override
    public PropertyName findNameForDeserialization(final Annotated annotated) {
        final JsonbProperty annotation = _findAnnotation(annotated, JsonbProperty.class);
        if (annotation != null) {
            return PropertyName.construct(annotation.value(), null);
        }

        return delegate.findNameForDeserialization(annotated);
    }
}
