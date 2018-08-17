create table mi_user(
  email varchar(30) primary key,
  password varchar(30) not null,
  username varchar(30) not null
  );

create table music_template(
  music_template_id int primary key auto_increment,
  owner varchar(30) not null,
  music_title varchar(30) not null,
  musician varchar(30),
  constraint fk_musictemplate_miuser_email foreign key (owner) references mi_user (email) on delete cascade
);

create table music_template_guide(
  music_template_id int,
  play_time time,
  comment text(255),
  constraint pk_musictemplateguide_templateid_playtime
  primary key (music_template_id, play_time),
  constraint fk_musictemplateguide_templateid_playtime
  foreign key (music_template_id) references music_template (music_template_id)
  on delete cascade
);

create table music_template_practice(
  student_email varchar(30),
  inner_filename varchar(255),
  music_template_id int,
  music_template_practice_id int primary key auto_increment,
  constraint fk_musictemplatepractice_miuser_email foreign key (student_email) references mi_user (email) on delete cascade,
  constraint fk_musictemplatepractice_mifile_innerfilename foreign key (inner_filename) references mi_file (inner_filename) on delete cascade,
  constraint fk_musictemplatepractice_musictemplate_musictemplateid foreign key (music_template_id) references music_template (music_template_id) on delete cascade
);

create table mi_notification(
  mi_notification_id int primary key,
  music_template_id int,
  regist_date_time datetime,
  type varchar(30) not null,
  comment text(255) not null,
  constraint fk_minotification_musictemplate_musictemplateid foreign key (mi_notification_id) references music_template (music_template_id) on delete cascade
);

create trigger mi_notification_datetimeInsert
  before insert on mi_notification for each row
  set new.regist_date_time = now();

create table music_template_assignment(
  inner_filename varchar(255) primary key,
  music_template_id int,
  constraint fk_musictemplateassignment_musictemplate_musictamplateid foreign key (music_template_id) references music_template (music_template_id) on delete cascade
);

create table music_template_wrong(
  assignment_inner_filename varchar(255),
  play_time time,
  constraint pk_musicteplatewrong primary key (assignment_inner_filename, play_time)
);

create table mi_file(
  owner varchar(30),
  inner_filename varchar(255) primary key,
  outter_filename varchar(255),
  constraint fk_mifile_miuser_owner foreign key (owner) references mi_user (email) on delete cascade
);


/*query examples*/
insert into mi_user values('jinzzam@namol.ppam', '123', '박유진');

insert into mi_user ('email', 'password', 'username')
  values ('namolppam@pocket.mon', '1234', '나몰빼미');

delete table mi_user;

insert into music_template (owner, music_title, musician) values
    ('namolppam@pocket.mon', '나몰나몰송', '나몰빼미');

select * from mi_user WHERE username like '%유%';
