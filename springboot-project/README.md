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
●idea2019.3
●git 
●springboot 2.2.5
●github
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

  @Id

   ~~@GeneratedValue(strategy = GenerationType.IDENTITY)~~

   private int id;

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



## 老师的指导

~~~java~~~
●初步的业务逻辑，自己整理，划分，创建业务逻辑组件，业务方法

有的加了注意提示，有的没加

●所有添加的，都得能修改 

●添加教师。直接实例化给定教师对象。用于系统初始化时调用，但仍需抽象。启动时如何初始化数据，比如初始化管理员账号密码后期讨论

●修改密码。

●修改指定教师其他信息。学生数，范围数

●添加毕设方向。名称，不在声明权重，无法限制学生乱选，但互选成功后，教师课查看

●修改毕设方向

●创建课程。包括课程名称，权重

●添加指定课程学生成绩单。为指定课程添加学生，课程id，学生集合；查询学生是否已存在，不存在创建学生，创建关联对象

●修改/重修添加指定课程学生成绩单。整合入上一逻辑？

●添加指定学生。添加已提前敲定的学生，直接占用导师名额；查询学生是否存在

●登录。基于工号，密码登录

●启动互选。提供启动互选功能，按当前权重，拉出指定教师，范围数学生，在控制层保存，互选实际执行实际很短，最多1-2天，未避免反复查询，直接缓存有资格学生到缓存。但一旦修改范围数，权重，必须重修计算加载

●算法，jpql/sql语句太复杂解决不了，直接把所有学生拉出，用java计算，排序

●全部学生排名。按各种权重，计算出学生排名。简单算法，学生每门课程成绩加权，除以课程数，

●指定范围学生排名。范围控制层传入，别在这直接调用教师范围数
~~~

