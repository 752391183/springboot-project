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

● 老师与方向之间是多对多的关系
 
登陆：

1.学生通过学号，密码登陆(密码同学号)

2.老师通过员工号，密码登陆(设置初始密码，可修改)

业务逻辑功能：

StudentService：

1.添加学生信息，课程信息，方向信息

2.获取指定id的老师信息（通过输入名字查找对应老师的信息，前端返回id，基于id查询）

3.选取老师

TeacherService：

添加老师信息

对申请的学生进行系统初步筛选

1.同意成为导师的申请

2.获取指定id的学生信息

3.提前预定一个学生

4.获取互选成功的所有学生信息

5.老师修改密码

6.更新可选学生人数

4.学生导师互选的结果(成功将学生whetherSelected设置为true，同时将老师的haveSeletedNumber+1，返回true;不成功返回false)
~~~


### 过程中遇到的问题
~~~
● 将Students表中的主键错写成自增长，当测试时又重新输入了学号，导致异常
  
  解决办法：新添加一个number(Integer)属性来存储学号,同时声明学号的唯一约束

  @Id

   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Integer id;
   @Column(unique = true)
   private Integer number;


● 需增加时间戳属性来使整体更完整
@Column(columnDefinition = "timestamp default current_timestamp",
        insertable = false,
        updatable = false)
private LocalDateTime insertTime;
@Column(columnDefinition = "timestamp default current_timestamp"+
        " on update current_timestamp",
        insertable = false,
        updatable = false)
private LocalDateTime updateTime;


●需在main主函数上声明BaseRepository持久层
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)


●将所有的OneToMany全部声明为级联（删除）以及即时加载，
 方便后期进行删除以及从one端对many端对象的访问
  cascade = CascadeType.REMOVE,fetch = FetchType.EAGER

●当用POST请求传值时的请求体不应该为一个简单的数据类型，而是封装着这个属性的类型，或者通过Map<String,..>data ,data.get("..") 拿到键所对应的值

●由于最初设计的时候没有将老师和学生相同属性分离出一个User类，导致在设置登录的时候必须得现行对是老师登录还是学生登录进行判断，正常化设计都该是以一个User身份登录这样既不需要判断是老师登录还是学生登录，也可以在登录控制层中通过Token中的角色判断出是老师还是学生

●将所有持久层的返回值设为Optional容器，防止未查到而报出的空指针错误，同时在调用的时候如果需要返回集合需要返回.orelse(空的集合)，采用的是返回一个空元素的List.of()，但也可以返回Collection.emptyList()，具体的区别如下：
 
 https://blog.csdn.net/azylmioplr32433/article/details/101687669

●对二次请求的学生进行对同一个老师的多次请求该如何？不能总返给老师让老师拒绝接受吧。 如何判断是同一个学生对同一个老师的多次请求？ 通过切面，去切选取这个老师的方法，同时需要声明一个新类，用于封装第一次这个学生的id和选择对应老师的信息，从而当此id的学生第二次请求时可以进行判断是否存在一个老师的id是此时选取老师的id;



~~~