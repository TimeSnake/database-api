package de.timesnake.database.core.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProtectionAgreement implements de.timesnake.database.util.user.DataProtectionAgreement {

    private final Date date;
    private final String version;

    public DataProtectionAgreement(Date date, String version) {
        this.date = date;
        this.version = version;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(date) + ";" + version;
    }


}
