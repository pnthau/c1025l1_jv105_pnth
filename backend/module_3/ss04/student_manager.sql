create table classes(id int auto_increment primary key,
class_name varchar(60) not null,
start_date datetime not null,
`status` bit 
);

create table students(id int auto_increment primary key,
student_name varchar(30) not null,
address varchar(50),
phone varchar(20),
`status` bit,
class_id int not null,
FOREIGN KEY (class_id) REFERENCES classes (id)
);

create table subjects(id int auto_increment primary key,
sub_name varchar(30) not null,
credit tinyint not null default 1 check(credit >= 1),
`status` bit default 1
);

create table mark(id int auto_increment primary key,
sub_id int not null ,
student_id int not null ,
mark float default 0 check(mark between 0 and 100),
exam_times tinyint default 1,
FOREIGN KEY (sub_id) REFERENCES subjects (id),
FOREIGN KEY (student_id) REFERENCES students (id)
);


INSERT INTO classes
VALUES (1, 'A1', '2008-12-20', 1);
INSERT INTO classes
VALUES (2, 'A2', '2008-12-22', 1);
INSERT INTO classes
VALUES (3, 'B3', current_date, 0);


INSERT INTO students (student_name, address, phone, `status`, class_id)
VALUES ('Hung', 'Ha Noi', '0912113113', 1, 1);
INSERT INTO students (student_name, address,`status`, class_id)
VALUES ('Hoa', 'Hai phong', 1, 1);
INSERT INTO students (student_name, address, phone, `status`, class_id)
VALUES ('Manh', 'HCM', '0123123123', 0, 2);


INSERT INTO subjects
VALUES (1, 'CF', 5, 1),
 (2, 'C', 6, 1),
 (3, 'HDJ', 5, 1),
 (4, 'RDBMS', 10, 1);

INSERT INTO mark (sub_id, student_id, mark, exam_times)
VALUES (1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);

-- create index idx_student_name on students(student_name);
-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’
select s.student_name from students s
where s.student_name like 'h%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.
select * from classes c
where month(c.start_date) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select * from subjects sub
where sub.credit between 3 and 5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
UPDATE students s
SET s.class_id = 2
WHERE s.student_name = 'Hung';

-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. 
-- nếu trùng sắp theo tên tăng dần.
select s.student_name, sub.sub_name, m.* from students s 
inner join mark  m on m.student_id = s.id
inner join subjects sub on m.sub_id = sub.id ;


-- ss4 -------------------------------------------------------------------------------
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select sub.* from subjects  sub
where sub.credit = (select max(credit) from subjects);
-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select sub.* from subjects sub
inner join mark m on m.subject_id = sub.id
where m.mark = (select max(m2.mark) from mark m2);
-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
select s.id, s.student_name,s.address,s.phone, avg(m.mark) as student_avg from students
inner join mark m on m.student_id = s.id
group by s.id, s.student_name, s.address, s.phone
order by student_avg desc