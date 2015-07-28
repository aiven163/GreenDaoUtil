# GreenDaoUtil

    在我们做Android开发的时候经常会用到数据库，我想很多人都知道，每每用到数据库，对其的操作就是一个让人头大的问题，
  如果我们自行编写一个类来操作数据库，那么我们就需要编写各种增删改查方法，甚至数据库的管理，很容易出现各种奇葩的问题，也很难控制。
  所以网上出现了很多开源的Sqlite工具库，比如FinalDB、AHibernat、Sqlite ORM 、GreenDao 等等，绝大部分的实现方式都差不多。但综合操作
  和性能效率上来看，GreenDao稍微好一点。<br>
    对于GreenDao的介绍请看官网:<bt/>
    [GeenDao如何使用](http://greendao-orm.com/documentation/how-to-get-started/) <br/>
    [GeenDao的GitHub下载地址](https://github.com/greenrobot/greenDAO)<br/>
    
    看了一些GeenDao的使用介绍文章，发现其优势，但是也发现要利用其生成持久层代码比较麻烦，需要县编写Java类来描述我们的表结构。
  如果表少到还是好，但是如果表比较多，编写Java代码可能出错，也不容易理清表与表之间的关系。最近没事，就想整理一下，把GeenDao需要编写的
  Java代码想找一个什么东西替换，所以想用到XML描述。虽然也少不了写代码，但是有了键值对，表与表之间的关系还是很容易看出来。
  要了解我这个GreenDao工具，要先了解GeenDao是什么，是干嘛用的。<br/>
