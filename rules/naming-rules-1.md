---
description: '자세한 내용은 docs/my.sql , docs/dummy.sql 참고'
---

# DB TABLES

## DB

\(따로 표기 없으면 varchar\(30\)\);

### mi\_user

**primary key** : email

email

password / not null

username / not null

### music\_template

**primary key** : music\_template\_id

music\_template\_id &lt;int&gt; / auto\_increment

owner / not null

music\_title / not null

musician / null이면 작자미상

path / not null

**foreign key** : owner &lt;= mi\_user\(email\)

### music\_template\_guide

**primary key** : music\_template\_id & play\_time

music\_template\_id &lt;int&gt;

play\_time &lt;time&gt;

comment &lt;text\(225\)&gt;

**foreign key** : music\_template\_id &lt;= music\_template\(music\_template\_id\)

### music\_template\_practice

**primary key** : music\_template\_practice\_id

music\_template\_practice\_id &lt;int&gt; / auto\_increment

student\_email

inner\_filename &lt;varchar\(255\)&gt;

music\_template\_id &lt;int&gt;

**foreign key** : music\_template\_id &lt;= music\_template \(music\_template\_id\)

**foreign key** : student\_email &lt;= mi\_user \(email\)

**foreign key** : inner\_filename &lt;= mi\_file \(inner\_filename\)

### mi\_notification

**primary key** : mi\_notification\_id

mi\_notification\_id &lt;int&gt;

music\_template\_id &lt;int&gt;

regist\_date\_time &lt;datetime&gt; / default current\_timestamp\(현재시간\)

type / not null

comment &lt;text\(255\)&gt; / not null

**foreign key** : music\_template\_id &lt;= music\_template\(music\_template\_id\)

### music\_template\_assignment

**primary key** : inner\_filename

inner\_filename &lt;varchar\(255\)&gt;

music\_template\_id &lt;int&gt;

**foreign key** : music\_template\_id &lt;= music\_template\(music\_template\_id\)

### music\_template\_wrong

**primary key** : assignment\_inner\_filename & play\_time

assignment\_inner\_filename &lt;varchar\(255\)&gt;

play\_time &lt;time&gt;

### mi\_file

**primary key** : inner\_filename

inner\_filename &lt;varchar\(255\)&gt;

outter\_filename &lt;varchar\(255\)&gt;

owner

**foreign key** : owner &lt;= mi\_user\(email\)

### mi\_group

primary key : name &lt;varchar\(50\)&gt;

info &lt;varchar\(255\)&gt;

instruments &lt;varchar\(255\)&gt;

genres &lt;varchar\(255\)&gt;

### mi\_teacher

teacher\_email &lt;varchar&gt;

group\_name &lt;varchar\(50\)&gt;

### mi\_student

student\_email &lt;varchar&gt;

group\_name &lt;varchar\(50\)&gt;

### 공지-유진 

더이상 추가하기 귀찮으니 docs/my.sql, docs/dummy.sql 파일 참고해서 알아서 각자 db에 추가하고 깃북에도 그에 맞게 수정해주면 좋겠음. 



