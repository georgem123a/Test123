package example.db;

import example.dto.IBAN;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

public class IBANStringType extends AbstractSingleColumnStandardBasicType<IBAN> {

    private static final String NAME = "IBANString";

    public IBANStringType() {
        super(VarcharTypeDescriptor.INSTANCE, IBANDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
