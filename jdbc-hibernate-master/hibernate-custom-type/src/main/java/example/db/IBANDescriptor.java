package example.db;

import example.dto.IBAN;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

public class IBANDescriptor extends AbstractTypeDescriptor<IBAN> {

    public static final IBANDescriptor INSTANCE = new IBANDescriptor();

    public IBANDescriptor() {
        super(IBAN.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public IBAN fromString(String string) {
        return string == null ? null : IBAN.of(string);
    }

    @Override
    public IBAN wrap(Object value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return IBAN.of((String) value);
        }
        throw new RuntimeException("Unable to resolve IBAN from type " + value.getClass().getCanonicalName());
    }

    @Override
    public Object unwrap(IBAN value, Class type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isAssignableFrom(type)) {
            return value.toString();
        }
        throw new RuntimeException("Unable to save IBAN as type " + type.getCanonicalName());
    }
}
