# # encoding: utf-8
# # !/usr/bin/python3
#
# import traceback
# import dbOperate as db
#
# try:
#     # 插入单条数据
#     sql = 'INSERT INTO user values("%d","%s")' % (1, "jack")
#
#     # 不建议直接拼接sql，占位符方面可能会出问题，execute提供了直接传值
#     value = [2, 'John']
#     cursor.execute('INSERT INTO test values(%s,%s)', value)
#
#     # 批量插入数据
#     values = []
#     for i in range(3, 20):
#         values.append((i, 'kk' + str(i)))
#     cursor.executemany('INSERT INTO user values(%s,%s)', values)
#
#     # 查询数据条目
#     count = cursor.execute('SELECT * FROM %s' % TABLE_NAME)
#     print 'total records: %d' % count
#     print 'total records:', cursor.rowcount
#
#     # 获取表名信息
#     desc = cursor.description
#     print "%s %3s" % (desc[0][0], desc[1][0])
#
#     # 查询一条记录
#     print 'fetch one record:'
#     result = cursor.fetchone()
#     print result
#     print 'id: %s,name: %s' % (result[0], result[1])
#
#     # 查询多条记录
#     print 'fetch five record:'
#     results = cursor.fetchmany(5)
#     for r in results:
#         print r
#
#
#
# fetchone()的使用:
#
# cursor.execute(select username,password,nickname from user where id='%s'  %(input)
#
# result=cursor.fetchone();  此时我们可以通过result[0],result[1],result[2]得到username,password,nickname
#
# fetchall()的使用:
#
# cursor.execute(select * from user)
#
# result=cursor.fetchall();此时select得到的可能是多行记录,那么我们通过fetchall得到的就是多行记录,是一个二维元组
#
# ((username1,password1,nickname1),(username2,password2,nickname2),(username3,password3,nickname))
#
#     # 查询所有记录
#     # 重置游标位置，偏移量:大于0向后移动;小于0向前移动，mode默认是relative
#     # relative:表示从当前所在的行开始移动; absolute:表示从第一行开始移动
#     cursor.scroll(0, mode='absolute')
#     results = cursor.fetchall()
#     for r in results:
#         print r
#
#     cursor.scroll(-2)
#     results = cursor.fetchall()
#     for r in results:
#         print r
#
#     # 更新记录
#     cursor.execute('UPDATE %s SET name = "%s" WHERE id = %s' % (TABLE_NAME, 'Jack', 1))
#     # 删除记录
#     cursor.execute('DELETE FROM %s WHERE id = %s' % (TABLE_NAME, 2))
#
#     # 如果没有设置自动提交事务，则这里需要手动提交一次
#     conn.commit()
# except:
#
#     traceback.print_exc()
#     # 发生错误时会滚
#     conn.rollback()
# finally:
#     # 关闭游标连接
#     cursor.close()
#     # 关闭数据库连接
#     conn.close()
#
# 查询时返回字典结构
#
# MySQLdb默认查询结果都是返回tuple，通过使用不同的游标可以改变输出格式，这里传递一个cursors.DictCursor参数。
#
# import MySQLdb.cursors
#
# conn = MySQLdb.connect(host='localhost', user='root', passwd='root', db='test', cursorclass=MySQLdb.cursors.DictCursor)
# cursor = conn.cursor()
#
# cursor.execute('select * from user')
# r = cursor.fetchall()
# print r
# # 当使用位置参数或字典管理参数时，必须导入MySQLdb.cursors模块
#
# # 也可以用下面的写法
# import MySQLdb as mdb
#
# conn = mdb.connect('localhost', 'root', 'root', 'test')
# cursor = conn.cursor(cursorclass=mdb.cursors.DictCursor)
#
# cursor.execute('select * from user')
# r = cursor.fetchall()
# print r
#
#
# # MySQLdb取回大结果集的技巧
# #
# # 普通的操作无论是fetchall()还是fetchone()都是先将数据载入到本地再进行计算，大量的数据会导致内存资源消耗光。解决办法是使用SSCurosr光标来处理。
# #
# # 解决mysqldb查询大量数据导致内存使用过高的问题
# # MySQLdb取回大结果集的技巧