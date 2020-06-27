# Springboot课程设计

### 实现毕业设计双向的选择（要求）

~~~java~~~
教师类：
● 登录：导入多门课程成绩单EXCEL表格（前端CS实现）

● 为课程设置最低分数和权重：保密算法

● 设置实际指导学生的数量

● 设置加权后，有资格学生数量范围

● 查看当前已接收学生

● 各参数皆可修改

学生类：

● 输入学号：载入曾经选修的课程

● 提交和提醒确认

● 匹配，返回成功的说明，同时进行更新数据；不匹配，返回友好的失败提醒信息

● 达到最大的数后，关闭提交，显示友好的提醒信息

● 后台并发判断(当多人同时提交时，如何进行数据的处理)

~~~



### 开发的环境

~~~java~~~
●idea2019.3.3
●Git 2.24 
●springboot 2.2.5
●github
●MySQL 8.0.17
●Node 12.16
●Vue 2.2
●VSCode 1.42
~~~

### 设计的整体思想

~~~
● 学生与老师是多对一的关系

● 课程与学生之间是多对多的关系，需要建立课程与学生之间的中间表来表示他们之间的关系

● 学生和课程方向之间是多对多的关系，也需要建立学生和方向之间的中间表来表示他们之间的关系
~~~

### 表情况

~~~java~~~
● 学生(Students):主键(int):id 名字(String):name 是否被选中(Boolean):whetherSelected

● 课程(Course)：主键(int):id 课程名(String)：name 权值(Float)：value 课程最低分(Float):mixGrade

● 老师(Teacher):主键(int):id 教师名(String)：name 可以选择学生的数量(int):selectNumber 登陆账号(int):password

● 方向(Direction):主键(int):id 课程名(String)：name 方向的权重(Float):value

● 学生与课程之间的中间表(Elective):主键(int):id 具体信息(String):detail 课程成绩(int):grade

● 学生与方向之间的中间表(DirectionElective):主键(int):id 具体信息(String):detail

~~~

### 过程中遇到的问题

● 将Students表中的主键错写成自增长，当测试时又重新输入了学号，导致异常
  
  解决办法：新添加一个number(Integer)属性来存储学号,同时声明学号的唯一约束

  @Id

   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Integer id;
   @Column(unique = true)
   private Integer number;


● 需增加时间戳属性来使整体更完整

```
@Column(columnDefinition = "timestamp default current_timestamp",
        insertable = false,
        updatable = false)
private LocalDateTime insertTime;
@Column(columnDefinition = "timestamp default current_timestamp"+
        " on update current_timestamp",
        insertable = false,
        updatable = false)
private LocalDateTime updateTime;
```

●需在main主函数上声明BaseRepository持久层

```
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
```

●将所有的OneToMany全部声明为级联（删除）以及即时加载，
 方便后期进行删除以及从one端对many端对象的访问
~~~
  cascade = CascadeType.REMOVE,fetch = FetchType.EAGER
~~~


