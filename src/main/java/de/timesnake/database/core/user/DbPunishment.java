/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.util.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.User;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.PunishType;
import de.timesnake.library.basic.util.Punishment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class DbPunishment extends DbPlayer implements de.timesnake.database.util.user.DbPunishment {

  public DbPunishment(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
    super(databaseConnector, uuid, nameTable);
  }

  @Override
  public void delete() {
    super.deleteWithKey(() -> Channel.getInstance().sendMessage(
        new ChannelUserMessage<>(this.getUniqueId(), MessageType.User.PUNISH)));
  }

  @Nullable
  @Override
  public PunishType getType() {
    return super.getFirstWithKey(Column.User.PUNISH_TYPE);
  }

  @Override
  public void setType(PunishType type) {
    super.setWithKey(type, Column.User.PUNISH_TYPE);
  }

  @Nullable
  @Override
  public LocalDateTime getDate() {
    return super.getFirstWithKey(Column.User.PUNISH_DATE);
  }

  @Override
  public void setDate(LocalDateTime date) {
    super.setWithKey(date, Column.User.PUNISH_DATE);
  }

  @Override
  public @NotNull Duration getDuration() {
    return super.getFirstWithKey(User.PUNISH_DURATION);
  }

  @Override
  public void setDuration(Duration duration) {
    super.setWithKey(duration, User.PUNISH_DURATION);
  }

  @Nullable
  @Override
  public String getCastigator() {
    return super.getFirstWithKey(Column.User.PUNISH_CASTIGATOR);
  }

  @Override
  public void setCastigator(String castigator) {
    super.setWithKey(castigator, Column.User.PUNISH_CASTIGATOR);
  }

  @Nullable
  @Override
  public String getReason() {
    return super.getFirstWithKey(Column.User.PUNISH_REASON);
  }

  @Override
  public void setReason(String reason) {
    super.setWithKey(reason, Column.User.PUNISH_REASON);
  }

  @Override
  public Punishment asPunishment() {
    ColumnMap columnMap = super.getFirstWithKey(Set.of(User.UUID, User.PUNISH_TYPE, User.PUNISH_DATE,
        User.PUNISH_DURATION, User.PUNISH_CASTIGATOR, User.PUNISH_REASON));
    return new Punishment(columnMap.get(User.UUID), columnMap.get(User.PUNISH_TYPE), columnMap.get(User.PUNISH_DATE),
        columnMap.get(User.PUNISH_DURATION), columnMap.get(User.PUNISH_CASTIGATOR), columnMap.get(User.PUNISH_REASON));
  }
}
