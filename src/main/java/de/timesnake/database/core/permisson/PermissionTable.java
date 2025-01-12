/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.permisson;

import de.timesnake.channel.util.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class PermissionTable extends DefinitionAndQueryTool {

  protected PermissionTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable, Column.Permission.ID);
    super.addColumn(Column.Permission.NAME);
    super.addColumn(Column.Permission.PERMISSION);
    super.addColumn(Column.Permission.MODE);
  }

  protected void addPermission(String name, String permission, Status.Permission mode) {
    super.addEntryWithAutoIdSynchronized(Column.Permission.ID,
        new Entry<>(name, Column.Permission.NAME),
        new Entry<>(permission, Column.Permission.PERMISSION),
        new Entry<>(mode, Column.Permission.MODE));
  }

  protected void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute) {
    super.addEntryWithAutoId(Column.Permission.ID, syncExecute,
        new Entry<>(name, Column.Permission.NAME),
        new Entry<>(permission, Column.Permission.PERMISSION),
        new Entry<>(mode, Column.Permission.MODE));
  }

  protected void addPermission(UUID uuid, String permission, Status.Permission mode) {
    this.addPermission(uuid.toString(), permission, mode,
        () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid, MessageType.User.PERMISSION)));
  }

  protected void removePermission(String name, String permission) {
    super.deleteEntry(new Entry<>(permission, Column.Permission.PERMISSION), new Entry<>(name, Column.Permission.NAME));
  }

  protected void removePermission(String name, String permission, SyncExecute syncExecute) {
    super.deleteEntry(syncExecute, new Entry<>(permission, Column.Permission.PERMISSION),
        new Entry<>(name, Column.Permission.NAME));
  }

  protected void removePermission(UUID uuid, String permission) {
    this.removePermission(uuid.toString(), permission,
        () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid, MessageType.User.PERMISSION)));
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public boolean containsPermission(Integer id) {
    return super.getFirst(Column.Permission.NAME, new Entry<>(id, Column.Permission.ID)) != null;
  }

  public boolean containsPermission(String name, String permission) {
    return super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
        new Entry<>(permission, Column.Permission.PERMISSION)) != null &&
        super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
            new Entry<>(permission, Column.Permission.PERMISSION)) != 0;
  }

  @Nullable
  public Integer getIdFromName(String name, String permission) {
    return super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
        new Entry<>(permission, Column.Permission.PERMISSION));
  }

  @Nullable
  public Collection<Integer> getIdsFromName(String name) {
    return super.get(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME));
  }

  @NotNull
  public Collection<DbPermission> getCachedPermissions(String name) {
    return super.get(Set.of(Column.Permission.ID, Column.Permission.PERMISSION, Column.Permission.MODE),
            new Entry<>(name, Column.Permission.NAME)).stream()
        .map(m -> ((DbPermission) new DbCachedPermission(m)))
        .toList();
  }
}
