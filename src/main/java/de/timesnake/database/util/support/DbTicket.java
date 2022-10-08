/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.util.support;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface DbTicket extends DbCached<DbTicket> {

    boolean exists();

    @NotNull
    Integer getId();

    @NotNull
    String getUuid();

    @NotNull
    String getName();

    @NotNull
    String getMessage();

    void setMessage(String message);

    @Nullable
    String getAnswer();

    void setAnswer(String answer);

    @NotNull
    Status.Ticket getStatus();

    void setStatus(Status.Ticket status);

    @NotNull
    Date getDate();

    @NotNull
    String getDateString();

}
