# database-api

## Setup

This module is part of the plugin-project [1]. You can use it as standalone project or as bundle.

### Standalone

Clone this project and enter your gitlab credentials in your gradle user home
properties (`~/.gradle/gradle.propreties`):

```
timesnakeUser=<user>
timesnakePassword=<access_token>
```

Replace `<user>` with your gitlab username and `<access_token>` with an access-token.

Run `gradle build` to build it.

### Bundle

To use this project in the multimodule plugin-project, read the setup guide in the root module [1].

## Testing

To test this module, you must set up a test server, therefore read regarding guide in the root module [1].

## Usage

### Queries

Read queries are executed synchronously. So a query can cause a slight delay in the execution.
Performing multiple queries should be done asynchronously to prevent server lags. Some data objects
are supporting
caching to prevent lags.

Write/Update queries are executed asynchronously. So read queries, which are executed shortly after
the update could
fetch an old invalid value. To prevent this, a channel listener should be used to listen for
updates. You can find more
about that in the _channel-api_.

### Caching

All objects with an instance of [DbCached] can be cached. To cache all data simply call the method
`toLocal()`. Caching data only performs one database query, so it is much faster compared to
querying each value
separately. To get the original database object call `toDatabase()`. Each call of `toLocal()` will
refresh the data from the database. Cached objects should only be used for a short time, because the
data could be
invalided by database updates.

All methods marked with the [NotCached] annotation (mostly set or update methods) require a database
query.

[DbCached]: /src/main/java/de/timesnake/database/util/object/DbCached.java

[NotCached]: /src/main/java/de/timesnake/database/util/object/NotCached.java
[root module](https://git.timesnake.de/timesnake/minecraft/plugin-root-project)

## Code Style

The code style guide can be found in the plugin root project [1].

## License

The source is licensed under the GNU GPLv2 license that can be found in the [LICENSE](LICENSE)
  file.

[1] https://git.timesnake.de/timesnake/minecraft/plugin-root-project