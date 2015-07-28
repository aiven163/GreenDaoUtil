# GreenDaoUtil

  在我们做Android开发的时候经常会用到数据库，我想很多人都知道，每每用到数据库，对其的操作就是一个让人头大的问题，如果我们自行编写一个类来操作数据库，那么我们就需要编写各种增删改查方法，甚至数据库的管理，很容易出现各种奇葩的问题，也很难控制。所以网上出现了很多开源的Sqlite工具库，比如FinalDB、AHibernat、Sqlite ORM 、GreenDao 等等，绝大部分的实现方式都差不多。但综合操作和性能效率上来看，GreenDao稍微好一点。<br>
    对于GreenDao的介绍请看官网:<bt/>
    [GeenDao如何使用](http://greendao-orm.com/documentation/how-to-get-started/) <br/>
    [GeenDao的GitHub下载地址](https://github.com/greenrobot/greenDAO)<br/>
    
  看了一些GeenDao的使用介绍文章，发现其优势，但是也发现要利用其生成持久层代码比较麻烦，需要县编写Java类来描述我们的表结构。如果表少到还是好，但是如果表比较多，编写Java代码可能出错，也不容易理清表与表之间的关系。最近没事，就想整理一下，把GeenDao需要编写的
  Java代码想找一个什么东西替换，所以想用到XML描述。虽然也少不了写代码，但是有了键值对，表与表之间的关系还是很容易看出来。
  要了解我这个GreenDao工具，要先了解GeenDao是什么，是干嘛用的。<br/>
  
  #使用
  
  首先来看看工具的面貌<br/>
  ![](https://github.com/aiven163/GreenDaoUtil/blob/master/sceen_shot/short1.png)  <br>
  
  ###具体应用如下:<br>
  
  * 编写数据库表关系描述文件,我这里给一个例子(消费者与订单关系表)<br/>
    ```Xml
    <?xml version="1.0" encoding="utf-8"?>
    <Db>
    	<version>1</version>	
    	<outPath>D://out</outPath>
    	<package>com.aiven</package>
    	<Tables>
    			<table name="Customer">
    					<column name="CustId">
    						<type>Long</type>
    						<primaryKey>true</primaryKey>
    						<autoIncreament>true</autoIncreament>
    					</column>
    					<column name="nick">
    						<type>String</type>
    						<notNull>true</notNull>
    						<unique>true</unique>
    					</column>		
    					<column name="telephoneNum">
    						<type>String</type>
    						<unique>true</unique>
    					</column>
    			</table>
    			
    			<table name="Order">
    				<column name="id">
    					<type>Long</type>
    					<primaryKey>true</primaryKey>
    					<autoIncreament>true</autoIncreament>
    				</column>
    				<column name="orderNum">
    					<type>String</type>	
    					<notNull>true</notNull>
    					<unique>true</unique>
    				</column>
    				<column name="belong">
    					<type>Long</type>
    					<notNull>true</notNull>
    					<foreignKey>true</foreignKey>
    					<foreignKeyTable>Customer</foreignKeyTable>
    					<foreignKeyColumn>CustId</foreignKeyColumn>
    				</column>
    			</table>
    	</Tables>
    </Db>
    ```
    对于这个xml文件中的各个字段描述，需要的朋友可以下载后查看doc文档，里面有详细的介绍。生成成功后会有对应的Java类生成:<br/>
      ![](https://github.com/aiven163/GreenDaoUtil/blob/master/sceen_shot/shot2.png)  <br>
      
 这里面生成了对应的数据库 操作类和对应的Java Bean实体类。至于怎么应用到Android项目中，就去看GreenDao的API吧
