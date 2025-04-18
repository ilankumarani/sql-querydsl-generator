package com.ilan.dialect;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.internal.DdlTypeImpl;
import org.hibernate.type.descriptor.sql.spi.DdlTypeRegistry;

public class CustomH2Dialect extends H2Dialect {

    private static final DatabaseVersion customVersion = DatabaseVersion.make(1, 4, 200);

    public CustomH2Dialect() {
        super();
    }

    public CustomH2Dialect(DatabaseVersion version) {
        super(customVersion);
    }

    public CustomH2Dialect(DialectResolutionInfo info) {
        this(customVersion);
        this.registerKeywords(info);
    }


    @Override
    protected void registerColumnTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.registerColumnTypes(typeContributions, serviceRegistry);
        DdlTypeRegistry ddlTypeRegistry = typeContributions.getTypeConfiguration().getDdlTypeRegistry();
        if (this.getVersion().isBefore(2)) {
            ddlTypeRegistry.addDescriptor(new DdlTypeImpl(2003, "array", this));
        }

        ddlTypeRegistry.addDescriptor(new DdlTypeImpl(3000, "uuid", this));
        ddlTypeRegistry.addDescriptor(new DdlTypeImpl(3200, "geometry", this));

        if (this.getVersion().isSameOrAfter(1, 4, 198)) {
            ddlTypeRegistry.addDescriptor(new DdlTypeImpl(3100, "interval second($p,$s)", this));
        }

        if (this.getVersion().isSameOrAfter(1, 4, 200)) {
            ddlTypeRegistry.addDescriptor(new DdlTypeImpl(3001, "json", this));
            ddlTypeRegistry.addDescriptor(new DdlTypeImpl(1111, "json", this));
        }

    }
}
