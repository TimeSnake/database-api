/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.core.ServerChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.User;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class DbPunishment extends DbPlayer implements de.timesnake.database.util.user.DbPunishment {

  public DbPunishment(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
    super(databaseConnector, uuid, nameTable);
  }

  @Override
  public void delete() {
    super.deleteWithKey(() -> ServerChannel.getInstance().sendMessage(
        new ChannelUserMessage<>(this.getUniqueId(), MessageType.User.PUNISH)));
  }

  @Nullable
  @Override
  public Type.Punishment getType() {
    return super.getFirstWithKey(Column.User.PUNISH_TYPE);
  }

  @Override
  public void setType(Type.Punishment type) {
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

}
