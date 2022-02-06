package de.timesnake.database.util.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DataProtectionAgreement {

    /**
     * Converts the data-protection-agreement from the string
     *
     * @param string The string to convert from
     * @return the {@link DataProtectionAgreement}
     */
    static DataProtectionAgreement fromString(String string) {
        if (string != null) {
            String[] args = string.split(";");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = df.parse(args[0]);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            return new de.timesnake.database.core.user.DataProtectionAgreement(date, args[1]);
        }
        return null;
    }

    /**
     * Creates a database-protection-agreement
     *
     * @param date    The date of the agreement
     * @param version The accepted version
     * @return the {@link DataProtectionAgreement}
     */
    static DataProtectionAgreement create(Date date, String version) {
        return new de.timesnake.database.core.user.DataProtectionAgreement(date, version);
    }

    /**
     * Gets the date of the agreement
     *
     * @return the {@link Date}
     */
    Date getDate();

    /**
     * Gets the version of the agreement
     *
     * @return the version
     */
    String getVersion();

    /**
     * Converts the agreement to a {@link String}
     *
     * @return the string
     */
    String toString();
}
