package de.timesnake.database.core.user;

import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Override
    public Date getDate() {
        return date;
    }

    @NotNull
    @Override
    public String getVersion() {
        return version;
    }

    @NotNull
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(date) + ";" + version;
    }


}
