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

**primary key** : group\_name

group\_name

group\_info &lt;text\(255\)&gt;

is\_teacher &lt;boolean&gt;

 

