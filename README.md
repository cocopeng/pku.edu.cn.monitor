监控工程项目

在src/main/java/pku/edu/cn/logmine/MineThreadListener.java里面，static String FILENAME定义了日志的位置，需要先在这个位置建立好日志文件

由于数据库触发器要把数据库的变化推送给后台，所以t_data_center,t_data_access_node,t_user_system,t_metadata_node,logs这几张表，进行更改的时候，如果操作失败，先查看下监控程序时候已经允许。监控程序部署在tomcat中，在192.170.20.178 /usr/local/tomcat7/webapps