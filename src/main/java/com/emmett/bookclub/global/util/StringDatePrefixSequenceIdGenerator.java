package com.emmett.bookclub.global.util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class StringDatePrefixSequenceIdGenerator extends SequenceStyleGenerator {
    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    public static final String DATE_FORMAT_PARAMETER = "dateFormat";
    public static final String DATE_FORMAT_DEFAULT = "YYYYMMDD";
    private String dateFormat;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%05d";
    private String numberFormat;

    public static final String DATE_NUMBER_SEPARATOR_PARAMETER  = "dateNumberSeparator";
    public static final String DATE_NUMBER_SEPARATOR_DEFAULT   = "";
    private String dateNumberSeparator;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String datePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
        return valuePrefix + datePrefix + dateNumberSeparator + String.format(numberFormat, super.generate(session,object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);

        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
        dateFormat = ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
        dateNumberSeparator = ConfigurationHelper.getString(DATE_NUMBER_SEPARATOR_PARAMETER, params, DATE_NUMBER_SEPARATOR_DEFAULT);
    }
}
