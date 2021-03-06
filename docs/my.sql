create table mi_user(
  email varchar(30) primary key,
  password varchar(30) not null,
  username varchar(30) not null
  );

create table music_template(
  music_template_id int primary key,
  owner varchar(30) not null,
  music_title varchar(30) not null,
  musician varchar(30),
  guide varchar(255),
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
  music_template_practice_id int,
  music_template_id int,
  student_email varchar(30),
  inner_filename varchar(255),
  is_done boolean default false,
  complete_percent int not null,
  constraint pk_musictemplatepractice_templatepracticeid_templateid primary key (music_template_practice_id, music_template_id),
  constraint fk_musictemplatepractice_miuser_email foreign key (student_email) references mi_user (email) on delete cascade,
  constraint fk_musictemplatepractice_mifile_innerfilename foreign key (inner_filename) references mi_file (inner_filename) on delete cascade,
  constraint fk_musictemplatepractice_musictemplate_musictemplateid foreign key (music_template_id) references music_template (music_template_id) on delete cascade
);

create table mi_notification(
  mi_notification_id int primary key,
  music_template_id int not null,
  regist_date_time timestamp,
  type varchar(30) not null,
  comment text(255) not null
);

create table music_template_assignment(
  inner_filename varchar(255) primary key,
  music_template_id int,
  student_email varchar(30) not null,
  to_do_count int not null,
  done_count int not null,
  success_percent int not null,
  constraint fk_musictemplateassignment_musictemplate_musictamplateid foreign key (music_template_id) references music_template (music_template_id) on delete cascade,
  constraint fk_misictemplateassignment_mistudent_studentemail foreign key (student_email) references mi_student (student_email) on delete cascade
);

create table music_template_wrong(
  music_template_id int,
  music_template_practice_id int,
  student_email varchar(30),
  wrong_time_start varchar(30),
  wrong_time_end varchar(30),
  comment varchar(255)
);

create table mi_file(
  owner varchar(30),
  inner_filename varchar(255) primary key,
  outter_filename varchar(255),
  constraint fk_mifile_miuser_owner foreign key (owner) references mi_user (email) on delete cascade
);

create table mi_group(
  group_name varchar(50) primary key,
  place varchar(255),
  info varchar(255),
  instruments varchar(255),
  genre varchar(255)
);

create table mi_teacher(
  teacher_email varchar(30),
  group_name varchar(50),
  constraint fk_miteacher_miuser_teacheremail foreign key (teacher_email) references mi_user (email) on delete cascade,
  constraint fk_miteacher_migroup_groupname foreign key (group_name) references mi_group (group_name) on delete cascade
);

create table mi_student(
  student_email varchar(30),
  group_name varchar(50),
  constraint fk_mistudent_miuser_studentemail foreign key (student_email) references mi_user (email) on delete cascade,
  constraint fk_mistudent_migroup_groupname foreign key (group_name) references mi_group (group_name) on delete cascade
);
