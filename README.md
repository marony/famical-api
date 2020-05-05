# サーバー Scala Play

## タスク
- [ ] Flywayのマイグレーションが動いてない
- [ ] Skinny ORMの使い方
[scalikejdbc-cookbook/12_skinny_orm.md at master · scalikejdbc/scalikejdbc-cookbook](https://github.com/scalikejdbc/scalikejdbc-cookbook/blob/master/ja/12_skinny_orm.md)

## 作業履歴
[Getting Started with Play Framework](https://www.playframework.com/getting-started)

### プロジェクトの作成
```
$ cd server
$ sbt new playframework/play-scala-seed.g8
```

### お試し起動
$ cat conf/application.conf
```
play.filters.hosts {
    # 有効なホスト名を設定（デフォルト値：localhost, .local）
    allowed = ["."]
}
```

```
$ sbt run
```

### データベースのセットアップ
Linux
```
$ yay -S postgresql
$ sudo -u postgres -i initdb --locale $LANG -E UTF8 -D '/var/lib/postgres/data'
$ sudo systemctl enable postgresql
$ sudo systemctl start postgresql
```

```
$ sudo passwd postgres
```

Mac
```
$ brew install postgresql
$ pg_ctl -D /usr/local/var/postgres start
```

### データベースの作成
Linux
```
$ sudo su - postgres
postgres$ createuser --interactive
postgres$ createdb famical
```

Mac
```
psql -U famical
```

### パスワードの設定
```
postgres$ psql -d famical
famical=# alter role famical with password 'famical##';
famical=# \q
postgres$ exit
$ sudo systemctl restart postgresql
$ psql -U famical -d famical
```

パスワード入れなくてもログインできちゃう
/var/lib/postgres/data/pg_hba.conf
の中の
trust
のせい

Mac
```
$ alter role famical with password 'famical123';
```

### テーブル作成
家族とユーザー
```
famical=# CREATE TABLE families (
    id INTEGER PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    inserted_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL);
famical=# INSERT INTO families (id, name, inserted_at, updated_at) VALUES (1, '多田家', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

famical=# CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    family_id INTEGER NOT NULL REFERENCES families(id),
    family_owner BOOLEAN NOT NULL,
    name VARCHAR(256) NOT NULL,
    inserted_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL);
famical=# CREATE INDEX IX_family ON users(family_id);

famical=# INSERT INTO users (id, family_id, name, inserted_at, updated_at) VALUES (1, 1, true, 'ぱぱち', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
famical=# INSERT INTO users (id, family_id, name, inserted_at, updated_at) VALUES (2, 1, false, 'ままち', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

認証
```
famical=# CREATE TABLE authentications (
    user_id INTEGER PRIMARY KEY REFERENCES users(id),
    session_id CHAR(32) UNIQUE,
    session_expire_at TIMESTAMP,
    login_id VARCHAR(256) NOT NULL,
    salt VARCHAR(16) NOT NULL,
    password CHAR(128) NOT NULL,
    inserted_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL);
famical=# CREATE INDEX IX_login_id ON authentications(login_id);
famical=# CREATE INDEX IX_session_id ON authentications(session_id);
```


### その他コマンド
- ユーザー一覧
```
famical=# \du
```

- テーブル一覧
```
famical=# \dt
```

- データベース一覧
```
famical=# ¥l
```

### VSCode
- Scala Language Server
- Scala(sbt)
